package com.example.thymeleafdemo;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;

@SpringBootApplication
public class ThymeleafDemoApplication {

    public static void main(String[] args) {

//            new SpringApplicationBuilder().sources(ThymeleafDemoApplication.class).web(WebApplicationType.NONE).run(args);
        ConfigurableApplicationContext run = SpringApplication.run(ThymeleafDemoApplication.class, args);
        ConfigurableListableBeanFactory beanFactory = run.getBeanFactory();
        DataSource bean = beanFactory.getBean(DataSource.class);
        System.out.println(bean);

    }
    @RestController
    @RequestMapping("/test")
    class Demo{
        @RequestMapping("/test")
        @ResponseBody
        public Object testServer(){
            System.out.println("访问成功");

            return new Exception("JKJFKJ");
        }
        @RequestMapping("/redis")
        @ResponseBody
        public Object testredis(){
            System.out.println("访问redis");
            RedisUtil.set("test","测试redis");
            System.out.println(RedisUtil.get("test"));
            return RedisUtil.get("test");
        }
    }
}
