package com.temp.springcloud.common.exception;

import lombok.Getter;

/**
 * @author LW
 * @date 2019.3.20
 */
@Getter
public class BadRequestException extends RuntimeException{
    public BadRequestException(String msg){
        super(msg);
    }
}
