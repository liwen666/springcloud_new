package com.temp.springcloud.config;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.List;

public class UserSecurity extends User {
    private String id;

    public UserSecurity(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }
    public UserSecurity(String id,String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.id= id;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
