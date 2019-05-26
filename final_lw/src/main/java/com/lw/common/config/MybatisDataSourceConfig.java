package com.lw.common.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.lw.common.config.system.H2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.SQLException;
/**
* Description:   mybatis配置<br>
* author:     lw
* date:     2019/5/26 12:23
* Version:        1.0
* <a href="https://www.baidu.com">百度</a>
*/
@Component
public class MybatisDataSourceConfig {

    @Autowired
    H2Service h2Service;


    @Bean("mysqlDataSource")
    public DataSource mysqlDataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
//        druidDataSource.setDriverClassName("com.mysql.jdbc.Driver");
//        show variables like "%time_zone%"  UTC 比北京早8小时;
        druidDataSource.setUrl("jdbc:mysql://192.168.42.136:3306/eladmin?serverTimezone=Hongkong&useUnicode=true&useSSL=false&characterEncoding=utf8");
        druidDataSource.setUsername("root");
        druidDataSource.setPassword("root");
//        基本属性 url、user、password
//                <property name="driverClassName" value="com.mysql.jdbc.Driver"/>
//        <property name="url" value="jdbc:mysql://192.168.9.180:3306/hqbpmn?useUnicode=true"/>
//        <property name="username" value="root"/>
//        <property name="password" value="root"/>
//
//                配置初始化大小、最小、最大
//                <property name="initialSize" value="10"/>
//        <property name="minIdle" value="10"/>
//        <property name="maxActive" value="50"/>
//                配置获取连接等待超时的时间
//                <property name="maxWait" value="60000"/>
//                配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
//                <property name="timeBetweenEvictionRunsMillis" value="60000"/>
//
//                配置一个连接在池中最小生存的时间，单位是毫秒
//                <property name="minEvictableIdleTimeMillis" value="300000"/>
//
//        <property name="validationQuery" value="SELECT 'x'"/>
//        <property name="testWhileIdle" value="true"/>
//        <property name="testOnBorrow" value="false"/>
//        <property name="testOnReturn" value="false"/>
//
//                打开PSCache，并且指定每个连接上PSCache的大小  如果用Oracle，则把poolPreparedStatements配置为true，mysql可以配置为false。
//        <property name="poolPreparedStatements" value="false"/>
//        <property name="maxPoolPreparedStatementPerConnectionSize" value="20"/>
//
//                配置监控统计拦截的filters
//                <property name="filters" value="wall,stat"/>
        return druidDataSource;
    }
    @Bean("h2DataSource")
    public DataSource h2DataSource() {
//        h2Service.startH2();
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName("org.h2.Driver");
        druidDataSource.setUrl("jdbc:h2:tcp://127.0.0.1:8043/mem:testbpmn");
        druidDataSource.setUsername("root");
        druidDataSource.setPassword("");

//        基本属性 url、user、password
//                <property name="driverClassName" value="com.mysql.jdbc.Driver"/>
//        <property name="url" value="jdbc:mysql://192.168.9.180:3306/hqbpmn?useUnicode=true"/>
//        <property name="username" value="root"/>
//        <property name="password" value="root"/>
//
//                配置初始化大小、最小、最大
//                <property name="initialSize" value="10"/>
//        <property name="minIdle" value="10"/>
//        <property name="maxActive" value="50"/>
//                配置获取连接等待超时的时间
//                <property name="maxWait" value="60000"/>
//                配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
//                <property name="timeBetweenEvictionRunsMillis" value="60000"/>
//
        druidDataSource.setInitialSize(10);
        druidDataSource.setMinIdle(10);
        druidDataSource.setMaxActive(50);
        druidDataSource.setMaxWait(60000);
        druidDataSource.setTimeBetweenEvictionRunsMillis(60000);
//                配置一个连接在池中最小生存的时间，单位是毫秒
//                <property name="minEvictableIdleTimeMillis" value="300000"/>
//
//        <property name="validationQuery" value="SELECT 'x'"/>
//        <property name="testWhileIdle" value="true"/>
//        <property name="testOnBorrow" value="false"/>
//        <property name="testOnReturn" value="false"/>
//
        druidDataSource.setValidationQuery("select 'x'");
        druidDataSource.setTestWhileIdle(true);
        druidDataSource.setTestOnBorrow(false);
        druidDataSource.setTestOnReturn(false);

//                打开PSCache，并且指定每个连接上PSCache的大小  如果用Oracle，则把poolPreparedStatements配置为true，mysql可以配置为false。
//        <property name="poolPreparedStatements" value="false"/>
//        <property name="maxPoolPreparedStatementPerConnectionSize" value="20"/>
//
//                配置监控统计拦截的filters
//                <property name="filters" value="wall,stat"/>
        try {
            druidDataSource.setFilters("wall,stat");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return druidDataSource;
    }

}
