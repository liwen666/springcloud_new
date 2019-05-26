package com.lw.common.security.impl;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.io.Serializable;

/**
* Description:    java类作用描述<br>
* author:     lw
* date:     2019/5/26 17:41
* Version:        1.0
*/
@Getter
@AllArgsConstructor
public class AuthenticationToken implements Serializable {

    private final String token;
}
