package com.architect.all.spring.security.src.controller;

import com.architect.all.spring.security.config.java.ServerProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired//依赖注入
    private ServerProperties serverProperties;

    @RequestMapping(value = "hello2", method = RequestMethod.GET)
    public String say2() {
        return "Hello Spring Boot!\n" + serverProperties.getName() + "\n" + serverProperties.getHost().getPort();
    }
}