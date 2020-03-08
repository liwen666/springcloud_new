package com.example;

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
    @RequestMapping("/admin/mock/boot-admin-client/getUser")
    public User getUserNew() {

        User user=new User("新路径","xxx");

        return user;
    }
}