package com.temp.springcloud.environment;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import javax.sql.DataSource;

//@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
@SpringBootApplication
public class EnvironmentApplication {

    public static void main(String[] args) {
//        SpringApplication.run(ThymeleafDemoApplication.class);


        ConfigurableApplicationContext run = SpringApplication.run(EnvironmentApplication.class, args);
        ConfigurableListableBeanFactory beanFactory = run.getBeanFactory();
//        run.close();
        DataSource bean = beanFactory.getBean(DataSource.class);
        System.out.println(bean);



    }
}
