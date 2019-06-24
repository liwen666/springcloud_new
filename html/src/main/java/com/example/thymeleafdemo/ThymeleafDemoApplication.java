package com.example.thymeleafdemo;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;

import javax.sql.DataSource;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
//@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class ThymeleafDemoApplication {

    public static void main(String[] args) {
//        SpringApplication.run(ThymeleafDemoApplication.class);


        ConfigurableApplicationContext run = SpringApplication.run(ThymeleafDemoApplication.class, args);
        ConfigurableListableBeanFactory beanFactory = run.getBeanFactory();
//        run.close();
        DataSource bean = beanFactory.getBean(DataSource.class);
        System.out.println(bean);



    }
}
