package com.architect.all.shiro;

import com.architect.all.spring.security.config.java.EnableArchitectSqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import javax.sql.DataSource;

/**
 * 默认用户 user
 * 密码  启动时  会有显示
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.architect.all.shiro.src", "com.architect.all.shiro.config"})
public class AppRunSpring {
    public static void main(String[] args) {
        //变更配置文件读取位置启动
        ConfigurableApplicationContext run =  new SpringApplicationBuilder(AppRunSpring.class)
                .properties("spring.config.location=classpath:com/architect/all/shiro/config/application-me.yml").run(args);
        ConfigurableListableBeanFactory beanFactory = run.getBeanFactory();
//        DataSource bean = beanFactory.getBean(DataSource.class);
//        DataSource bean2 = (DataSource) beanFactory.getBean("dataSource1");
//        System.out.println(bean+"数据源");
//        System.out.println(bean2+"数据源");
////        ArchitectSqlSessionFactoryBean bean3 = beanFactory.getBean(ArchitectSqlSessionFactoryBean.class);
////        System.out.println(bean3);
//        System.out.println("-------------");
        //读取默认配置文件启动

    }
}
