package com.architect.all.spring.security.config.java;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 自定义bean并将属性注入
 */
@ConfigurationProperties(prefix = "server")
@Component
@Data
public class ServerProperties {
    private String name;

    private Host host;

    // ... getter and setters
    @Data
    public static class Host {

        private String ip;

        private int port;

        // ... getter and setters

    }

}