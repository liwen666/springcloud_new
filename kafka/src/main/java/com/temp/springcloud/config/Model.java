package com.temp.springcloud.config;

import java.lang.annotation.*;
 
/**
 * @ClassName Model
 * @Description 转换类实体注解
 * @Date 2019/3/28 0028 11:10
 * @Version 0.1
 **/
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Model {
    /**
     * 表名
     * @return
     */
    String tableName() default "";
}
