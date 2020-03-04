package com.example.thymeleafdemo.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ConfigurationProperties(prefix = MockProperties.MOCK_PREFIX, ignoreUnknownFields = true)
public class MockProperties {
    public static final String MOCK_PREFIX = "mock.server";
 
    /**
     * 开启mock ,true:开启  false：关闭
     */
    @Getter
    @Setter
    private boolean enabled = false;
 
    /**
     * mock 服务的ip地址或域名 例：10.12.141.146:18081
     */
    @Getter
    @Setter
    private String ipAddress;
 
    /**
     * 如果每个服务的 mock server 的地址都一样的使用该配置，多个服务以 ,号 隔开 例:order-service,user-service
     */
    @Getter
    @Setter
    private String services;
 
    /**
     * 如果每个服务的 mock server 地址不一样，使用该配置,key:服务名  value: ip地址 ,例
     */
    @Getter
    @Setter
    private Map<String, String> servicesMap = new ConcurrentHashMap<>();
 
    /**
     * mock server 服务url
     */
    @Getter
    @Setter
    private String mockServerUrl;
 
 
    /**
     * 是否需要所有服务都用 mock
     */
 
 
    private boolean global = false;
 
    public boolean getGlobal() {
        return global;
    }
 
    public void setGlobal(boolean global) {
        this.global = global;
    }
}