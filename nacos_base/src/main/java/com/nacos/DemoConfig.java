package com.nacos;

//import org.springframework.boot.context.properties.ConfigurationProperties;

//import org.springframework.boot.context.properties.ConfigurationProperties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <p>
 * 描述
 * </p>
 *
 * @author tx
 * @since 2019/5/26 23:40
 */
@ConfigurationProperties(prefix = "test.demo")
public class DemoConfig {
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    String value;
}
