//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.example.thymeleafdemo.config;

import feign.Request;
import feign.Retryer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Configuration
//@ConditionalOnProperty(
//    prefix = "anyest.client",
//    name = {"enable"},
//    havingValue = "true"
//)
@EnableFeignClients(
    basePackages = {"com.example.thymeleafdemo.client"}
)
@EnableDiscoveryClient(
    autoRegister = true
)
public class ClientConfiguration {
    private static final Logger log = LoggerFactory.getLogger(ClientConfiguration.class);
    @Autowired
    private DiscoveryClient discoveryClient;
    @Autowired
    private FeignConfig feignConfig;
    @Autowired
    private ObjectFactory<HttpMessageConverters> messageConverters;

    public ClientConfiguration() {
    }

    @Bean
    public Request.Options options() {
        return new Request.Options(this.feignConfig.connectTimeout, this.feignConfig.readTimeout);
    }

    @Bean
    public Retryer feignRetryer() {
        return new Retryer.Default();
    }

    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
        List<HttpMessageConverter<?>> messageConverters = new ArrayList();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
        messageConverters.add(converter);
        restTemplate.setMessageConverters(messageConverters);
        return restTemplate;
    }
}
