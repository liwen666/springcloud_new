package com.example;

import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfigurationTest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

//import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

//@EnableEurekaServer
//@SpringBootApplication
@SpringBootApplication(exclude = MybatisAutoConfigurationTest.class)
public class SpringBootEurekaApplication {

    public static void main(String[] args) throws InterruptedException {
        ConfigurableApplicationContext run = SpringApplication.run(SpringBootEurekaApplication.class, args);
//        MybatisAutoConfigurationTest bean = run.getBean(MybatisAutoConfigurationTest.class);
//        System.out.println(bean);

    }

}