package com.lw.common.utils;

import org.springframework.util.DigestUtils;
/**
* Description:    加密
* Author:     lw
* CreateTime:     2019/5/26 10:21
* Version:        1.0
*/
public class EncryptUtils {

    /**
     * 密码加密
     * @param password
     * @return
     */
    public static String encryptPassword(String password){
        return  DigestUtils.md5DigestAsHex(password.getBytes());
    }

    public static void main(String[] args) {
        System.out.println(encryptPassword("e10adc3949ba59abbe56e057f20f883e"));
    }
}
