package com.lw.common.exception.impl;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BadRequestException extends RuntimeException{
    public BadRequestException(String msg){
        super(msg);
    }
}
