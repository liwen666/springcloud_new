package com.example.thymeleafdemo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @ResponseBody
    @RequestMapping("/getUser")
    public User getUser() {

        User user=new User("小明","xxx");

        return user;
    }
    @ResponseBody
    @RequestMapping("/getChina")
    public String getChina() {


        return "中文";
    }
}