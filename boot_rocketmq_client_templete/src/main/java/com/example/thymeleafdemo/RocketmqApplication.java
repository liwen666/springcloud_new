package com.example.thymeleafdemo;
 
//import com.example.thymeleafdemo.config.RocketMqCfgProperties;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author 18011618
 * @Description
 * @Date 11:02 2018/7/17
 * @Modify By
 */
@SpringBootApplication
public class RocketmqApplication {
    public static void main(String[] args) {
        SpringApplication.run(RocketmqApplication.class,args);
    }

    @Controller
    public class testC{

        @Autowired
        RocketMQTemplate rocketMQTemplate;

        @GetMapping("test")
        public String getStr(){
            System.out.println(rocketMQTemplate);
            return "MQ";
        }

    }
}