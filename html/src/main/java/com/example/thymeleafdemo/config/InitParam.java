package com.example.thymeleafdemo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class InitParam implements ApplicationRunner {
@Autowired
private DataSource dataSource;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        UeduitorManager.dataSource=dataSource;
        System.out.println(dataSource);

    }
}
