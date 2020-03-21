package com.example;

import com.example.conf.JrxMaxwellConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.kafka.listener.ContainerProperties;

import java.net.URL;

//@EnableEurekaServer
@SpringBootApplication
public class SpringBootEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootEurekaApplication.class, args);
    }

}