package com.lw.common.config;

import com.lw.common.security.AuthenticationManager;
import com.lw.common.security.SecurityConfigurer;
import com.lw.common.security.WebSecurityConfigurer;
import com.lw.common.security.filter.JwtAuthorizationTokenFilter;
import com.lw.common.security.impl.AuthenticationManagerDelegator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Description:    java类作用描述
 * author:     lw
 * date:     2019/5/26$ 15:55$
 * Version:        1.0
 */
@Configuration
public class WebSecurityConfig implements WebSecurityConfigurer {
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return new AuthenticationManagerDelegator();
    }

}
