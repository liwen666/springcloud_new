//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.lw.common.security;

public interface SecurityContextHolderStrategy {
    void clearContext();

    SecurityContext getContext();

    void setContext(SecurityContext var1);

    SecurityContext createEmptyContext();
}
