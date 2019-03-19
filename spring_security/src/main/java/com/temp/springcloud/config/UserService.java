package com.temp.springcloud.config;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    public User loadUserByUsername(String s) {
        User u = new User();
        u.setName("admin");
        u.setPassword("admin");
        return u;
    }
}
