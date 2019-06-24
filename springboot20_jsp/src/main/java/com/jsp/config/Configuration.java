package com.jsp.config;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "test")
@Data
public class Configuration {

    private String name;

    private String age;

    private Long tel;

    // setter getter
}