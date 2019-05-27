//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.lw.common.security.impl;

import com.lw.common.security.Authentication;
import com.lw.common.security.AuthorityUtils;
import com.lw.common.security.GrantedAuthority;
import com.lw.common.security.UserDetails;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

public abstract class AbstractAuthenticationToken implements Authentication {
    private final Collection<GrantedAuthority> authorities;
    private Object details;
    private boolean authenticated = false;

    public AbstractAuthenticationToken(Collection<? extends GrantedAuthority> authorities) {
        if (authorities == null) {
            this.authorities = AuthorityUtils.NO_AUTHORITIES;
        } else {
            Iterator var2 = authorities.iterator();

            GrantedAuthority a;
            do {
                if (!var2.hasNext()) {
                    ArrayList<GrantedAuthority> temp = new ArrayList(authorities.size());
                    temp.addAll(authorities);
                    this.authorities = Collections.unmodifiableList(temp);
                    return;
                }

                a = (GrantedAuthority)var2.next();
            } while(a != null);

            throw new IllegalArgumentException("Authorities collection cannot contain any null elements");
        }
    }

    public Collection<GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    public String getName() {
        if (this.getPrincipal() instanceof UserDetails) {
            return ((UserDetails)this.getPrincipal()).getUsername();
//        } else if (this.getPrincipal() instanceof AuthenticatedPrincipal) {
//            return ((AuthenticatedPrincipal)this.getPrincipal()).getName();
        } else if (this.getPrincipal() instanceof Principal) {
            return ((Principal)this.getPrincipal()).getName();
        } else {
            return this.getPrincipal() == null ? "" : this.getPrincipal().toString();
        }
    }

    public boolean isAuthenticated() {
        return this.authenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }

    public Object getDetails() {
        return this.details;
    }

    public void setDetails(Object details) {
        this.details = details;
    }

    public void eraseCredentials() {
        this.eraseSecret(this.getCredentials());
        this.eraseSecret(this.getPrincipal());
        this.eraseSecret(this.details);
    }

    private void eraseSecret(Object secret) {

    }

    public boolean equals(Object obj) {
        if (!(obj instanceof AbstractAuthenticationToken)) {
            return false;
        } else {
            AbstractAuthenticationToken test = (AbstractAuthenticationToken)obj;
            if (!this.authorities.equals(test.authorities)) {
                return false;
            } else if (this.details == null && test.getDetails() != null) {
                return false;
            } else if (this.details != null && test.getDetails() == null) {
                return false;
            } else if (this.details != null && !this.details.equals(test.getDetails())) {
                return false;
            } else if (this.getCredentials() == null && test.getCredentials() != null) {
                return false;
            } else if (this.getCredentials() != null && !this.getCredentials().equals(test.getCredentials())) {
                return false;
            } else if (this.getPrincipal() == null && test.getPrincipal() != null) {
                return false;
            } else if (this.getPrincipal() != null && !this.getPrincipal().equals(test.getPrincipal())) {
                return false;
            } else {
                return this.isAuthenticated() == test.isAuthenticated();
            }
        }
    }

    public int hashCode() {
        int code = 31;

        GrantedAuthority authority;
        for(Iterator var2 = this.authorities.iterator(); var2.hasNext(); code ^= authority.hashCode()) {
            authority = (GrantedAuthority)var2.next();
        }

        if (this.getPrincipal() != null) {
            code ^= this.getPrincipal().hashCode();
        }

        if (this.getCredentials() != null) {
            code ^= this.getCredentials().hashCode();
        }

        if (this.getDetails() != null) {
            code ^= this.getDetails().hashCode();
        }

        if (this.isAuthenticated()) {
            code ^= -37;
        }

        return code;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString()).append(": ");
        sb.append("Principal: ").append(this.getPrincipal()).append("; ");
        sb.append("Credentials: [PROTECTED]; ");
        sb.append("Authenticated: ").append(this.isAuthenticated()).append("; ");
        sb.append("Details: ").append(this.getDetails()).append("; ");
        if (!this.authorities.isEmpty()) {
            sb.append("Granted Authorities: ");
            int i = 0;

            GrantedAuthority authority;
            for(Iterator var3 = this.authorities.iterator(); var3.hasNext(); sb.append(authority)) {
                authority = (GrantedAuthority)var3.next();
                if (i++ > 0) {
                    sb.append(", ");
                }
            }
        } else {
            sb.append("Not granted any authorities");
        }

        return sb.toString();
    }
}
