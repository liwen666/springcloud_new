package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

//@EnableEurekaServer
@SpringBootApplication
public class SpringBootEurekaApplication {

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(SpringBootEurekaApplication.class, args);

    }

}