package com.lw.common.security.domain;

import java.io.Serializable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;

/**
* Description:    java类作用描述<br>
* author:     lw
* date:     2019/5/26 17:26
* Version:        1.0
*/
@Getter
@Setter
public class User implements Serializable {

    private Long id;

    @NotBlank
    private String username;

    private String avatar;

    @NotBlank
    @Pattern(regexp = "([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}",message = "格式错误")
    private String email;

    @NotNull
    private Boolean enabled;

    private String password;

    private Timestamp createTime;

    private Date lastPasswordResetTime;

    private Set<Role> roles;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", avatar='" + avatar + '\'' +
                ", email='" + email + '\'' +
                ", enabled=" + enabled +
                ", password='" + password + '\'' +
                ", createTime=" + createTime +
                ", lastPasswordResetTime=" + lastPasswordResetTime +
                '}';
    }
}