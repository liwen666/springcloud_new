package com.example.thymeleafdemo;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

/**
 * <p>
 * 描述
 * </p>
 *
 * @author lw
 * @since 2019/5/26 23:40
 */
@Service
public class TestDb implements ApplicationRunner {
@Autowired
private ApplicationContext applicationContext;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        DataSource bean = applicationContext.getBean(DataSource.class);
        System.out.println(bean.getClass().getName());
    }
}
