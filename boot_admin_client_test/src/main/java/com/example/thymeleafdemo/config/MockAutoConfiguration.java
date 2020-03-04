package com.example.thymeleafdemo.config;

import com.netflix.discovery.DiscoveryClient;
import feign.Client;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.ribbon.SpringClientFactory;
import org.springframework.cloud.openfeign.ribbon.CachingSpringLoadBalancerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@ConditionalOnProperty(prefix = MockProperties.MOCK_PREFIX, name = "enabled", havingValue = "true")
@EnableConfigurationProperties(value = {MockProperties.class})
@Configuration
public class MockAutoConfiguration {
 
 
    @Bean
    public Client feignClient(CachingSpringLoadBalancerFactory cachingFactory,
                              SpringClientFactory clientFactory, @Autowired(required = false) DiscoveryClient discoveryClient, MockProperties mockProperties) {
 
        return new MockLoadBalancerFeignClient(new Client.Default(null, null),
                cachingFactory, clientFactory, mockProperties, discoveryClient);
    }
 
//    @Bean
//    public MockFeignInterceptor mockFeignInterceptor() {
//        return new MockFeignInterceptor();
//    }
}