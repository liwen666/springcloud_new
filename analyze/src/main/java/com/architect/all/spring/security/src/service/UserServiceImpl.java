package com.architect.all.spring.security.src.service;

import com.architect.all.spring.security.src.dao.UserMapper;
import com.architect.all.spring.security.src.domin.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl {
    @Autowired
    private UserMapper userMapper;
    public User findByUsername(String username) {
        return null;
    }

    public User findByEmail(String username) {
        return null;
    }

    public User findUser(String s) {
        return userMapper.findUser(s);
    }
}
