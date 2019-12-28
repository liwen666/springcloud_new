//package com.example.thymeleafdemo;
//
//import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.ConfigurableApplicationContext;
//
//import javax.sql.DataSource;
//
//@SpringBootApplication
//public class ThymeleafDemoApplication {
//
//    public static void main(String[] args) {
//        ConfigurableApplicationContext run = SpringApplication.run(ThymeleafDemoApplication.class, args);
//        ConfigurableListableBeanFactory beanFactory = run.getBeanFactory();
//        DataSource bean = beanFactory.getBean(DataSource.class);
//        System.out.println(bean);
//
//    }
//}
