package com.temp.springcloud.config;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
 
import java.util.ArrayList;
import java.util.List;
 
/**
 * 认证和授权
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {
 
    @Autowired
    private UserService userService;
 
    @Autowired
    private MenuService menuService;
 
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
 
        //--------------------认证账号
        UserSecurity user = userService.loadUserByUsername(s);
        if (user == null) {
            throw new UsernameNotFoundException("账号不存在");
        }
 
 
        //-------------------开始授权
        List<Menu> menus = menuService.getMenusByUserId(user.getId());
        List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
        for (Menu menu : menus) {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(menu.getUrl());
            //此处将权限信息添加到 GrantedAuthority 对象中，在后面进行全权限验证时会使用GrantedAuthority 对象。
            grantedAuthorities.add(grantedAuthority);
        }
        user.getAuthorities().addAll(grantedAuthorities);
        return user;
    }
 
 
}