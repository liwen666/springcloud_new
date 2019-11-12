package com.temp.springcloud.config;

import java.lang.annotation.*;

/**
 * @ClassName ModelProperty
 * @Description 实体属性注解
 * @Date 2019/3/28 0028 11:13
 * @Version 0.1
 **/
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ModelProperty {
 
    /**
     * 对应的字段名
     * @return
     */
    String value() default "";
}