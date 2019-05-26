//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.lw.common.security.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.Serializable;

public class WebAuthenticationDetails implements Serializable {
    private static final long serialVersionUID = 510L;
    private final String remoteAddress;
    private final String sessionId;

    public WebAuthenticationDetails(HttpServletRequest request) {
        this.remoteAddress = request.getRemoteAddr();
        HttpSession session = request.getSession(false);
        this.sessionId = session != null ? session.getId() : null;
    }

    private WebAuthenticationDetails(String remoteAddress, String sessionId) {
        this.remoteAddress = remoteAddress;
        this.sessionId = sessionId;
    }

    public boolean equals(Object obj) {
        if (obj instanceof WebAuthenticationDetails) {
            WebAuthenticationDetails rhs = (WebAuthenticationDetails)obj;
            if (this.remoteAddress == null && rhs.getRemoteAddress() != null) {
                return false;
            } else if (this.remoteAddress != null && rhs.getRemoteAddress() == null) {
                return false;
            } else if (this.remoteAddress != null && !this.remoteAddress.equals(rhs.getRemoteAddress())) {
                return false;
            } else if (this.sessionId == null && rhs.getSessionId() != null) {
                return false;
            } else if (this.sessionId != null && rhs.getSessionId() == null) {
                return false;
            } else {
                return this.sessionId == null || this.sessionId.equals(rhs.getSessionId());
            }
        } else {
            return false;
        }
    }

    public String getRemoteAddress() {
        return this.remoteAddress;
    }

    public String getSessionId() {
        return this.sessionId;
    }

    public int hashCode() {
        int code = 7654;
        if (this.remoteAddress != null) {
            code *= this.remoteAddress.hashCode() % 7;
        }

        if (this.sessionId != null) {
            code *= this.sessionId.hashCode() % 7;
        }

        return code;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString()).append(": ");
        sb.append("RemoteIpAddress: ").append(this.getRemoteAddress()).append("; ");
        sb.append("SessionId: ").append(this.getSessionId());
        return sb.toString();
    }
}
