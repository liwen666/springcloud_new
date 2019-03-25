package com.architect.all.spring.security.config.java;

import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource(value = "classpath:com/architect/all/spring/security/config/mybatis/mybatis.yml")
public class MybatisConfig implements EnvironmentAware {
    private Environment env;
 

    // 此处env默认读取的应该是application.properties文件 -- 这个待我springboot框架搭好后补充
     @Override
    public void setEnvironment(Environment env) {
        // 此处将注入都env赋值给类的成员变量env
        this.env = env;
         System.out.println(env.getProperty("config.path")+"---------------------------");
         System.out.println(env.getProperty("mybatis.path")+"---------------------------");
    }
}