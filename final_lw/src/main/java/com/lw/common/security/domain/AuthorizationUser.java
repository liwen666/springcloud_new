package com.lw.common.security.domain;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
* Description:    java类作用描述
* author:     lw
* date:     2019/5/26 12:53
* Version:        1.0
*/
@Getter
@Setter
public class AuthorizationUser {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @Override
    public String toString() {
        return "{username=" + username  + ", password= ******}";
    }
}
