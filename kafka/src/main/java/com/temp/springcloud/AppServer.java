package com.temp.springcloud;

import com.temp.springcloud.sqlscript.controller.SqlFileExecutorControllerForJar;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;

//@EnableAutoConfiguration(exclude = {  //关闭security模块配置
//        SecurityAutoConfiguration.class
//})
@SpringBootApplication
@RestController
@ComponentScan(basePackages = {"com.temp.springcloud", "com.temp.springcloud.sqlscript"})
public class AppServer {
    public static void main(String[] args) throws Exception {
//        SpringApplication.run(AppServer.class);

        ConfigurableApplicationContext run = SpringApplication.run(AppServer.class, args);
        ConfigurableListableBeanFactory beanFactory = run.getBeanFactory();
        DataSource bean = beanFactory.getBean(DataSource.class);
        System.out.println(bean);
        SqlFileExecutorControllerForJar bean1    = beanFactory.getBean(SqlFileExecutorControllerForJar.class);
//        StreamToFile bean2 = beanFactory.getBean(StreamToFile.class);
//        System.out.println(bean2.getUser("n"));


        bean1.initSqlScriptSource(null,null);


    }
}
