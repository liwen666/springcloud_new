package com.temp.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.temp.springcloud.config")
public class AppStarter {
    public static void main(String[] args) {
        SpringApplication.run(AppStarter.class);
    }
}
