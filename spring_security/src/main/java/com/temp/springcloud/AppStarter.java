package com.temp.springcloud;

import com.temp.springcloud.config.CustomUserDetailsService;
import com.temp.springcloud.config.UserService;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import javax.sql.DataSource;

@SpringBootApplication
@ComponentScan(basePackages = {"com.temp.springcloud.config"})
public class AppStarter {
    public static void main(String[] args) {

        ConfigurableApplicationContext run = SpringApplication.run(AppStarter.class, args);
        ConfigurableListableBeanFactory beanFactory = run.getBeanFactory();
        DataSource bean = beanFactory.getBean(DataSource.class);
        System.out.println(bean);
        System.out.println(beanFactory.getBean(UserService.class));
        CustomUserDetailsService bean1 = beanFactory.getBean(CustomUserDetailsService.class);
        System.out.println(bean1);
//        System.out.println(bean1.getUserService().loadUserByUsername("1"));
    }
}
