package com.temp.springcloud.config;
 
import com.sun.javafx.collections.UnmodifiableListSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 认证和授权
 */
//@Component
public class CustomUserDetailsService implements UserDetailsService {
 
    @Autowired
    private UserService userService;
 
    @Autowired
    private MenuService menuService;

    public CustomUserDetailsService(UserService userService, MenuService menuService) {
        this.userService=userService;
        this.menuService= menuService;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
 
        //--------------------认证账号
        UserSecurity user = userService.loadUserByUsername(s);
        if (user == null) {
            throw new UsernameNotFoundException("账号不存在");
        }
 
 

//        authorities.add( grantedAuthorities.get(0));
//        authorities.addAll(grantedAuthorities);
        return user;
    }

    public static void main(String[] args) {
//        Set<GrantedAuthority> collection = new Collections.Un<GrantedAuthority>(;
//        List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
//        collection.addAll(grantedAuthorities);
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}