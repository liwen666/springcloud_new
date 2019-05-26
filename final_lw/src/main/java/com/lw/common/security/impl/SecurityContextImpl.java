//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.lw.common.security.impl;

import com.lw.common.security.Authentication;
import com.lw.common.security.SecurityContext;

public class SecurityContextImpl implements SecurityContext {
    private static final long serialVersionUID = 510L;
    private Authentication authentication;

    public SecurityContextImpl() {
    }

    public SecurityContextImpl(Authentication authentication) {
        this.authentication = authentication;
    }

    public boolean equals(Object obj) {
        if (obj instanceof SecurityContextImpl) {
            SecurityContextImpl test = (SecurityContextImpl)obj;
            if (this.getAuthentication() == null && test.getAuthentication() == null) {
                return true;
            }

            if (this.getAuthentication() != null && test.getAuthentication() != null && this.getAuthentication().equals(test.getAuthentication())) {
                return true;
            }
        }

        return false;
    }

    public Authentication getAuthentication() {
        return this.authentication;
    }

    public int hashCode() {
        return this.authentication == null ? -1 : this.authentication.hashCode();
    }

    public void setAuthentication(Authentication authentication) {
        this.authentication = authentication;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        if (this.authentication == null) {
            sb.append(": Null authentication");
        } else {
            sb.append(": Authentication: ").append(this.authentication);
        }

        return sb.toString();
    }
}
