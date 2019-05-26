//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.lw.common.security;

import javax.servlet.Filter;

public interface WebSecurityConfigurer<T extends SecurityBuilder<Filter>> extends SecurityConfigurer<Filter, T> {
    public AuthenticationManager authenticationManagerBean() throws Exception;
}
