package com.architect.all.spring.security;

import com.architect.all.spring.security.config.java.ArchitectSqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

/**
 * 默认用户 user
 * 密码  启动时  会有显示
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.architect.all.spring.security.src", "com.architect.all.spring.security.config"})
@MapperScan("com.architect.all.spring.security.src")
public class AppRunSpring {
    public static void main(String[] args) {
        //变更配置文件读取位置启动
        ConfigurableApplicationContext run =  new SpringApplicationBuilder(AppRunSpring.class)
                .properties("spring.config.location=classpath:com/architect/all/spring/security/config/application-me.yml").run(args);
        ConfigurableListableBeanFactory beanFactory = run.getBeanFactory();
        DataSource bean = beanFactory.getBean(DataSource.class);
        DataSource bean2 = (DataSource) beanFactory.getBean("dataSource1");
        System.out.println(bean+"数据源");
        System.out.println(bean2+"数据源");
//        ArchitectSqlSessionFactoryBean bean3 = beanFactory.getBean(ArchitectSqlSessionFactoryBean.class);
//        System.out.println(bean3);
        System.out.println("-------------");
        //读取默认配置文件启动

//        SpringApplication.run(AppRunSpring.class);
    }
}
