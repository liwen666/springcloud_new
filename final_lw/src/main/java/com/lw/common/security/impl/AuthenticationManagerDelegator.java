package com.lw.common.security.impl;

import com.lw.common.exception.impl.AuthenticationException;
import com.lw.common.security.Authentication;
import com.lw.common.security.AuthenticationManager;

import java.util.Set;

public class AuthenticationManagerDelegator implements AuthenticationManager {
        private AuthenticationManager delegate;
        private final Object delegateMonitor = new Object();
        private Set<String> beanNames;


    @Override
    public Authentication authenticate(Authentication var1) throws AuthenticationException {
        return null;
    }
}