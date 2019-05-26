//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.lw.common.security;

import com.lw.common.security.impl.SecurityContextImpl;
import org.springframework.util.Assert;

final class InheritableThreadLocalSecurityContextHolderStrategy implements SecurityContextHolderStrategy {
    private static final ThreadLocal<SecurityContext> contextHolder = new InheritableThreadLocal();

    InheritableThreadLocalSecurityContextHolderStrategy() {
    }

    public void clearContext() {
        contextHolder.remove();
    }

    public SecurityContext getContext() {
        SecurityContext ctx = (SecurityContext)contextHolder.get();
        if (ctx == null) {
            ctx = this.createEmptyContext();
            contextHolder.set(ctx);
        }

        return ctx;
    }

    public void setContext(SecurityContext context) {
        Assert.notNull(context, "Only non-null SecurityContext instances are permitted");
        contextHolder.set(context);
    }

    public SecurityContext createEmptyContext() {
        return new SecurityContextImpl();
    }
}
