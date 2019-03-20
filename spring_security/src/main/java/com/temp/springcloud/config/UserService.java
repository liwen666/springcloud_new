package com.temp.springcloud.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private MenuService menuService;
    public UserSecurity loadUserByUsername(String s) {

        //-------------------开始授权
        List<Menu> menus = menuService.getMenusByUserId("test");
        List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
        for (Menu menu : menus) {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(menu.getUrl());
            //此处将权限信息添加到 GrantedAuthority 对象中，在后面进行全权限验证时会使用GrantedAuthority 对象。
            grantedAuthorities.add(grantedAuthority);
        }
        UserSecurity u = new UserSecurity("admin","admin",grantedAuthorities);
        u.setId("test");

        return u;
    }
}
