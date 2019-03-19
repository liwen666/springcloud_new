package com.temp.springcloud.config;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@Data
public class User {
    private int id;
    private String name;
    private String password;
    List<GrantedAuthority> authorities;
}
