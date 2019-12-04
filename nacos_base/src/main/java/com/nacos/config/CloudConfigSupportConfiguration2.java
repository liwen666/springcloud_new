package com.nacos.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class CloudConfigSupportConfiguration2 implements
        ApplicationContextInitializer<ConfigurableApplicationContext>, Ordered {
    private int order = Ordered.HIGHEST_PRECEDENCE + 11;
    Logger logger = LoggerFactory.getLogger(CloudConfigSupportConfiguration2.class);

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        ConfigurableEnvironment environment = applicationContext.getEnvironment();

        MutablePropertySources propertySources = environment.getPropertySources();
        logger.info("加载PropertySources源：" + propertySources.size() + "个");

                PropertySource backupSource = new MapPropertySource("backupSource", new HashMap(){{put("server.port","10010");}});
                propertySources.addFirst(backupSource);
    }

    @Override
    public int getOrder() {
        return order;
    }
}