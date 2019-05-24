package com.example.thymeleafdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class ThymeleafDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ThymeleafDemoApplication.class, args);
    }
}
