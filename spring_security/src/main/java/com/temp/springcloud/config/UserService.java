package com.temp.springcloud.config;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService {
    public UserSecurity loadUserByUsername(String s) {

        UserSecurity u = new UserSecurity("admin","admin",new ArrayList<GrantedAuthority>());

        return u;
    }
}
