package com.lw.common.utils.el;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
* Description:    java类作用描述
* author:     lw
* date:     2019/5/26 12:22
* Version:        1.0
*/
public class RequestHolder {

    public static HttpServletRequest getHttpServletRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }
}
