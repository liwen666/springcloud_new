package com.architect.all.spring.security.config.java.security;

import lombok.Data;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
@Data
public class MyJdbcTokenRepositoryImpl extends JdbcTokenRepositoryImpl {
    private boolean createTableOnStartup;
    @Override
    protected void initDao() {
        if (this.createTableOnStartup) {
            this.getJdbcTemplate().execute("create  table if not exists persistent_logins (username varchar(64) not null, series varchar(64) primary key, token varchar(64) not null, last_used timestamp not null)");
        }
    }
}
