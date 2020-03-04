package com.example.thymeleafdemo.config;

import com.google.common.base.CaseFormat;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.boot.env.OriginTrackedMapPropertySource;
import org.springframework.core.env.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class MockEnvironmentPostProcessor implements EnvironmentPostProcessor {


    private static final String PROPERTY_SOURCE_NAME = "defaultProperties";


    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        //获取mock属性配置，并绑定到MockProperties 对象中
        MockProperties target = new MockProperties();
//        RelaxedDataBinder binder = new RelaxedDataBinder(target,
//                MockProperties.MOCK_PREFIX);
//        binder.bind(new PropertySourcesPropertyValues(environment.getPropertySources()));
////
        StandardEnvironment standardEnvironment = (StandardEnvironment) environment;
        String batch_source = standardEnvironment.getProperty("mock.server.enabled");
        PropertySource<?> propertySource = standardEnvironment.getPropertySources().get("applicationConfig: [classpath:/application.yml]");
        if (null != propertySource) {
            converBean(target, propertySource);
        }

//        OriginTrackedMapPropertySource {name='applicationConfig: [classpath:/application.yml]'}
        boolean enabled = target.isEnabled();
        if (true == enabled) {
            System.out.println("mock server 构建环境属性");
            Map<String, Object> map = new HashMap<>();

            //对单个服务mock的处理
            if (StringUtils.isNotBlank(target.getServices())) {
                String[] services = target.getServices().split(",");
                for (String service : services) {
                    map.put(service.toUpperCase() + ".ribbon.listOfServers", target.getIpAddress());
                    System.out.println(String.format("对[%s]服务配置 mock地址[%s]", service, target.getIpAddress()));
                }
            }

            // 自定义 每个服务的ip地址 服务直连情况
            if (!target.getServicesMap().isEmpty()) {
                Map<String, String> servicesMap = target.getServicesMap();
                for (String key : servicesMap.keySet()) {
                    String ip = servicesMap.get(key);
                    map.put(key.toUpperCase() + ".ribbon.listOfServers", ip);
                    System.out.println(String.format("对[%s]服务配置直连地址[%s]", key, ip));
                }
            }

            // 服务重试切换次数
            map.put("ribbon.MaxAutoRetriesNextServer", 0);
            // 服务重试次数
            map.put("ribbon.MaxAutoRetries", 0);
            // ribbon 连接时间
            map.put("ribbon.connectTimeoutMillis", 10000);
            // ribbon 读取时间
            map.put("ribbon.readTimeoutMillis", 60000);
            // 对所有操作请求都不重试
            map.put("ribbon.OkToRetryOnAllOperations", false);

            //ribbon不使用eureka上的服务信息
            map.put("ribbon.eureka.enabled", false);
            // 关闭 sleuth 对 feign client 的包装
            map.put("spring.sleuth.feign.enabled", false);
            // 设置全局的超时时间 hystrix 熔断超时时间
            map.put("hystrix.command.default.execution.isolation.thread.timeoutInMilliseconds", 60000);
            addOrReplace(environment.getPropertySources(), map);

        }

    }

    private void converBean(MockProperties target, PropertySource<?> propertySource) {
        Class<? extends MockProperties> aClass = target.getClass();
        Annotation[] declaredAnnotations = aClass.getDeclaredAnnotations();
        String prefix = "";
        for (Annotation annotation : declaredAnnotations) {
            if (annotation instanceof ConfigurationProperties) {
                prefix = ((ConfigurationProperties) annotation).prefix();
            }
        }
        Field[] declaredFields = aClass.getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            System.out.println(field.getType().getName());
            if (field.getType().getName().equals("java.util.Map")) {
                try {
                    Map map = (Map) field.get(target);
                    map.put("BOOT-ADMIN-CLIENT","127.0.0.1:9800");

                    continue;
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                Object property = propertySource.getProperty(prefix + "." + CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_HYPHEN, field.getName()));
                continue;
            }
            Object property = propertySource.getProperty(prefix + "." + CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_HYPHEN, field.getName()));
            try {
                if (property != null) {
                    field.set(target, property);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }

    }

    private void addOrReplace(MutablePropertySources propertySources,
                              Map<String, Object> map) {
        MapPropertySource target = null;
        if (propertySources.contains(PROPERTY_SOURCE_NAME)) {
            PropertySource<?> source = propertySources.get(PROPERTY_SOURCE_NAME);
            if (source instanceof MapPropertySource) {
                target = (MapPropertySource) source;
                for (String key : map.keySet()) {
                    if (!target.containsProperty(key)) {
                        target.getSource().put(key, map.get(key));
                    }
                }
            }
        }
        if (target == null) {
            target = new MapPropertySource(PROPERTY_SOURCE_NAME, map);
        }
        if (!propertySources.contains(PROPERTY_SOURCE_NAME)) {
            propertySources.addLast(target);
        }
    }
}