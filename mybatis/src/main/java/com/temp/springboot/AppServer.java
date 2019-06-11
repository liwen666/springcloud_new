package com.temp.springboot;

import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.embedded.tomcat.TomcatWebServer;
import org.springframework.context.ConfigurableApplicationContext;

import javax.sql.DataSource;

//@EnableFeignClients
//@ServletComponentScan
@SpringBootApplication
//@MapperScan({"com.temp.springboot.example.dao"})
//匹配一级或多级目录  使用 **
@MapperScan({"com.temp.springboot.**.dao"})
public class AppServer extends TomcatServletWebServerFactory {

    public static void main(String[] args) {
//        SpringApplication.run(AppServer.class, args);
        ConfigurableApplicationContext run = SpringApplication.run(AppServer.class, args);
        ConfigurableListableBeanFactory beanFactory = run.getBeanFactory();
//        run.close();
        DataSource bean = beanFactory.getBean(DataSource.class);
        System.out.println(bean);

    }


    @Override
    protected TomcatWebServer getTomcatWebServer(Tomcat tomcat) {
        StringBuilder ports = new StringBuilder();
        Connector[] var3 = tomcat.getService().findConnectors();
        int var4 = var3.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            Connector connector = var3[var5];
            connector.setPort(8090);
        }
        return super.getTomcatWebServer(tomcat);
    }



}