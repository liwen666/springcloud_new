package com.architect.all.spring.security.config.java;

import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({GlobleBeanConfig.class})
//@Import({GlobleBeanConfig.class,ArchitectSqlSessionFactoryBean.class})
public @interface EnableArchitectSqlSessionFactoryBean {
    boolean proxyTargetClass() default false;

    AdviceMode mode() default AdviceMode.PROXY;

    int order() default 2147483600;
}