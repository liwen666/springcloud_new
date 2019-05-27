//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.lw.common.security;

import com.lw.common.security.impl.SecurityContextImpl;
import org.springframework.util.Assert;

final class GlobalSecurityContextHolderStrategy implements SecurityContextHolderStrategy {
    private static SecurityContext contextHolder;

    GlobalSecurityContextHolderStrategy() {
    }

    public void clearContext() {
        contextHolder = null;
    }

    public SecurityContext getContext() {
        if (contextHolder == null) {
            contextHolder = new SecurityContextImpl();
        }

        return contextHolder;
    }

    public void setContext(SecurityContext context) {
        Assert.notNull(context, "Only non-null SecurityContext instances are permitted");
        contextHolder = context;
    }

    public SecurityContext createEmptyContext() {
        return new SecurityContextImpl();
    }
}
