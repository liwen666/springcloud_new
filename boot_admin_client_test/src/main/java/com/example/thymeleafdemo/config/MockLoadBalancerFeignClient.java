package com.example.thymeleafdemo.config;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.client.config.CommonClientConfigKey;
import com.netflix.client.config.IClientConfig;
import com.netflix.discovery.DiscoveryClient;
import com.netflix.hystrix.exception.HystrixRuntimeException;
import com.netflix.loadbalancer.Server;
import feign.Client;
import feign.Request;
import feign.Response;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.netflix.ribbon.SpringClientFactory;
import org.springframework.cloud.openfeign.ribbon.CachingSpringLoadBalancerFactory;
import org.springframework.cloud.openfeign.ribbon.LoadBalancerFeignClient;

import java.io.IOException;
import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Slf4j
public class MockLoadBalancerFeignClient extends LoadBalancerFeignClient {

    private MockProperties mockProperties;

    private DiscoveryClient discoveryClient;

    private Client delegate;
    private CachingSpringLoadBalancerFactory lbClientFactory;
    private SpringClientFactory clientFactory;

    public MockLoadBalancerFeignClient(Default delegate,
                                       CachingSpringLoadBalancerFactory lbClientFactory,
                                       SpringClientFactory clientFactory, MockProperties mockProperties, DiscoveryClient discoveryClient) {
        super(delegate, lbClientFactory, clientFactory);
        this.delegate = delegate;
        this.lbClientFactory = lbClientFactory;
        this.clientFactory = clientFactory;

        this.mockProperties = mockProperties;
        this.discoveryClient = discoveryClient;
        log.info("mock feign 负载均衡器初始化");
    }

    /**
     * 1. 如果配置 mock全局属性(默认false),则请求的所有服务都走 mock 服务器
     * 2. 请求的服务在mock服务列表中,则请求走mock服务器
     * 3. 请求的服务不在 mock 服务列表中,则先从直连配置获取服务信息，没有则从注册心上获取服务信息,请求转发到注册中心上的服务
     * 4. 请求的服务不在 mock 服务列表中,也没有开启全局 mock 功能,则请求服务走 直连配置和注册中心相结合
     *
     * @param request
     * @param options
     * @return
     * @throws IOException
     */
    @Override
    public Response execute(Request request, Request.Options options) throws IOException {
        String url = request.url();
        URI uri = URI.create(url);
        String clientName = uri.getHost();
        String[] mockServer = mockProperties.getIpAddress().split(":");
        //请求的客户名称转为小写
        clientName = clientName.toUpperCase();
        String newUrl = null;

        // 从全局中获取该服务名称的配置信息
        com.netflix.client.config.IClientConfig clientConfig = this.clientFactory.getClientConfig(clientName);
        if (null != clientConfig && mockProperties.getGlobal()) {
            // 配置当前服务的ip地址信息
//            clientConfig.set(CommonClientConfigKey.ListOfServers, mockProperties.getIpAddress());
            // 获取当前服务的负载均衡器，对当前服务的负载均衡器添加服务ip地址信息
            if (this.clientFactory.getLoadBalancer(clientName).getAllServers().isEmpty()) {
                this.clientFactory.getLoadBalancer(clientName).
                        addServers(Arrays.asList(new Server(mockServer[0], Integer.parseInt(mockServer[1]))));
            }

            // 重新构建请求 URL
            newUrl = this.getNewRequestUrl(url, clientName);
            log.info("请求的 {} 服务已开启全局 mock 功能,服务地址：{}", clientName, this.clientFactory.getLoadBalancer(clientName).getAllServers());
        } else {
            //获取 配置 mock 服务的列表
            String services = this.mockProperties.getServices();
            if (StringUtils.isNotBlank(services)) {
                //  配置 mock 服务的列表转为小写
                services = services.toUpperCase();
                // mock 服务列表
                List<String> service = Arrays.asList(services.split(","));
                // 当前服务是否在 mock 服务列表中
                if (service.contains(clientName)) {
                    log.info("请求的 {} 服务在 mock 服务列表中,服务地址：{}", clientName, clientConfig.get(CommonClientConfigKey.ListOfServers));
                    newUrl = this.getNewRequestUrl(url, clientName);
                } else {
                    // 服务直连情况 加 注册中心 处理
                    newUrl = getServiceInfoFromDiscoveryClient(url, clientName, clientConfig);
                }
            } else {
                if (mockProperties.getServicesMap().isEmpty()) {
                    String msg = "没有配置 mock 服务列表，也没有开启全局mock功能,也没有配置服务直连，请检查配置或关闭mock功能";
                    throw new HystrixRuntimeException(HystrixRuntimeException.FailureType.BAD_REQUEST_EXCEPTION, null, msg, null, null);
                }

                // 服务直连情况 加 注册中心 处理
                newUrl = getServiceInfoFromDiscoveryClient(url, clientName, clientConfig);
            }
        }
        return this.getResponse(request, options, newUrl);
    }

    /**
     * 1.如果有服务直接配置，则直接返回url
     * 2.如果不是直连，则从注册中上获取服务信息，并返回url
     *
     * @param url
     * @param clientName
     * @param clientConfig
     * @return
     */
    private String getServiceInfoFromDiscoveryClient(String url, String clientName, IClientConfig clientConfig) {
        String newUrl;

        //服务直连处理
        if (mockProperties.getServicesMap().size() > 0) {
            // 处理一些自定义的服务和ip地址，服务的直连情况
            Set<String> customServiceInfo = mockProperties.getServicesMap().keySet();
            if (customServiceInfo.contains(clientName.toUpperCase())) {
                log.info("请求的 {} 服务在直连列表中,服务地址：{}", clientName, mockProperties.getServicesMap().get(clientName));
                newUrl = url;
                return newUrl;
            }
        }


        if (null == this.discoveryClient) {
            String format = String.format("%s 服务没有配置在mock列表中，并且也没有开启注册中心功能,请检查配置", clientName);
            throw new HystrixRuntimeException(HystrixRuntimeException.FailureType.COMMAND_EXCEPTION, null, format, null, null);
        }


        // 获取 服务名的 服务信息
//        List<ServiceInstance> instances = this.discoveryClient.getInstances(clientName);
        List<InstanceInfo> instances = this.discoveryClient.getInstancesById(clientName);
        String host;
        if (null == instances || instances.isEmpty()) {
            host = String.format("%s 服务没有配置在mock列表中，也没有注册在住册中心上,请检查配置", clientName);
            throw new HystrixRuntimeException(HystrixRuntimeException.FailureType.COMMAND_EXCEPTION, null, host, null, null);
        }

        // 获取 服务在 注册中心的地址信息
        host = instances.get(0).getHostName() + ":" + instances.get(0).getPort();
        log.info("请求的 {} 服务在注则中心上,服务地址：{}", clientName, host);
        newUrl = url;

        if (null != clientConfig) {
//            clientConfig.set(CommonClientConfigKey.ListOfServers, host);
            // 获取当前服务的负载均衡器，对当前服务的负载均衡器添加服务ip地址信息
            if (this.clientFactory.getLoadBalancer(clientName).getAllServers().isEmpty()) {
                this.clientFactory.getLoadBalancer(clientName).
                        addServers(Arrays.asList(new Server(instances.get(0).getHostName(), instances.get(0).getPort())));
            }

        }
        return newUrl;
    }

    /**
     * 请求响应
     *
     * @param request
     * @param options
     * @param newUrl
     * @return
     * @throws IOException
     */
    private Response getResponse(Request request, Request.Options options, String newUrl) throws IOException {
        //重新构建 request　对象
        Request newRequest = Request.create(request.method(),
                newUrl, request.headers(), request.body(),
                request.charset());

        return super.execute(newRequest, options);
    }

    /**
     * 修改请求 url
     *
     * @param url
     * @param clientName
     * @return
     */
    private String getNewRequestUrl(String url, String clientName) {
        StringBuilder sb = new StringBuilder();
        sb.append(clientName);

        String mockServerUrl = mockProperties.getMockServerUrl();
        if (mockServerUrl.endsWith("/")) {
            sb.append(mockServerUrl);
        } else {
            sb.append(mockServerUrl).append("/");
        }
        sb.append(clientName.toLowerCase());

        String newUrl = url.replaceFirst(clientName, sb.toString());

        log.info("mock 服务重新构建请求 URL 地址：{}", newUrl);
        return newUrl;
    }
}