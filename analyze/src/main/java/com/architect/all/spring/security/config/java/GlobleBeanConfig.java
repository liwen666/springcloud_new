package com.architect.all.spring.security.config.java;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.Data;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
@Data
@ConfigurationProperties(prefix = "globle-config")
public class GlobleBeanConfig {
    @Autowired
    @Qualifier("dataSource1")
    private DataSource dataSource;
    private String configLocation;

    @Bean(name="dataSource1")
    public DataSource getDataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        druidDataSource.setUrl("jdbc:mysql://192.168.42.136:3306/eladmin?useUnicode=true&useSSL=false&characterEncoding=utf8&serverTimezone=GMT%2B8");
        druidDataSource.setUsername("root");
        druidDataSource.setPassword("root");
        druidDataSource.setMaxActive(50);
        druidDataSource.setMinIdle(10);
        druidDataSource.setInitialSize(10);
        druidDataSource.setMaxWait(10000);
        druidDataSource.setTimeBetweenEvictionRunsMillis(60000);
        druidDataSource.setValidationQuery("select * from user");
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
    @Bean
    public SqlSessionFactory getSqlSessionFactory() {
        try {
            SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
            sqlSessionFactoryBean.setDataSource(dataSource);
            String packageSearchPath = "classpath*:" + "com.architect.all.spring.security.src".replaceAll("\\.", "/") + "/**/*Mapper.xml";

            Resource[] resources = new PathMatchingResourcePatternResolver().getResources(packageSearchPath);
            sqlSessionFactoryBean.setMapperLocations(resources);
            System.out.println(configLocation+"mybatis  配置");
//            sqlSessionFactoryBean.setConfigLocation(configLocation);
//            sqlSessionFactoryBean.setPlugins(findPlugins());
            return sqlSessionFactoryBean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
