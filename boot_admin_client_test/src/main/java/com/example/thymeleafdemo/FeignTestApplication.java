package com.example.thymeleafdemo;

import com.example.thymeleafdemo.config.ClientConfiguration;
import com.example.thymeleafdemo.config.FeignConfig;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;

import javax.sql.DataSource;

@EnableConfigurationProperties(FeignConfig.class)
@SpringBootApplication
@Import(ClientConfiguration.class)
public class FeignTestApplication{

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(FeignTestApplication.class, args);
        ConfigurableListableBeanFactory beanFactory = run.getBeanFactory();
        DataSource bean = beanFactory.getBean(DataSource.class);
        System.out.println(bean);

    }
}
