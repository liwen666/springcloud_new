//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.lw.common.security;


import com.lw.common.exception.impl.AuthenticationException;

public interface AuthenticationManager {
    Authentication authenticate(Authentication var1) throws AuthenticationException;
}
