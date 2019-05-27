//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.lw.common.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.lw.common.security.impl.SimpleGrantedAuthority;
import org.springframework.util.StringUtils;

public abstract class AuthorityUtils {
    public static final List<GrantedAuthority> NO_AUTHORITIES = Collections.emptyList();

    public AuthorityUtils() {
    }

    public static List<GrantedAuthority> commaSeparatedStringToAuthorityList(String authorityString) {
        return createAuthorityList(StringUtils.tokenizeToStringArray(authorityString, ","));
    }

    public static Set<String> authorityListToSet(Collection<? extends GrantedAuthority> userAuthorities) {
        Set<String> set = new HashSet(userAuthorities.size());
        Iterator var2 = userAuthorities.iterator();

        while(var2.hasNext()) {
            GrantedAuthority authority = (GrantedAuthority)var2.next();
            set.add(authority.getAuthority());
        }

        return set;
    }

    public static List<GrantedAuthority> createAuthorityList(String... roles) {
        List<GrantedAuthority> authorities = new ArrayList(roles.length);
        String[] var2 = roles;
        int var3 = roles.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            String role = var2[var4];
            authorities.add(new SimpleGrantedAuthority(role));
        }

        return authorities;
    }
}
