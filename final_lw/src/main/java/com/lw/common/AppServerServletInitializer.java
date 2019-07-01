package com.lw.common;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
public class AppServerServletInitializer extends SpringBootServletInitializer
{
    @Override

    protected SpringApplicationBuilder configure(SpringApplicationBuilder application)
    {
        return application.sources(AppServerServletInitializer.class);
    }
}
