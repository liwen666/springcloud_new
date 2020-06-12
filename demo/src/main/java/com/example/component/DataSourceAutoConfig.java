package com.example.component;


import com.example.entity.DataSourceConfigEntity;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.CollectionUtils;

import javax.inject.Named;
import javax.sql.DataSource;
import java.sql.Connection;
import java.util.*;

/**
 * @author sukang
 */

@Configuration
public class DataSourceAutoConfig {

    @ConfigurationProperties(prefix = "spring.datasource.default")
    @Bean(name = "defaultDataSource")
    public DataSource firstSource(){
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

    @Bean(name = "routeDateSource")
    public HandlerDataSource dataSource(@Named(value = "defaultDataSource") DataSource defaultDataSource
                                        ) {

        JdbcTemplate jdbcTemplate = new JdbcTemplate(defaultDataSource);
        BeanPropertyRowMapper<DataSourceConfigEntity> rowMapper =
                new BeanPropertyRowMapper<>(DataSourceConfigEntity.class);
        List<DataSourceConfigEntity> configEntities = new ArrayList<>();
        DataSourceConfigEntity dataSourceConfigEntity = new DataSourceConfigEntity();
        dataSourceConfigEntity.setCode("source1");
        dataSourceConfigEntity.setUrl("jdbc:mysql://192.168.42.136:3306/souce1?serverTimezone=Hongkong&useUnicode=true&useSSL=false&characterEncoding=utf8");
        dataSourceConfigEntity.setPwd("root");
        dataSourceConfigEntity.setUserName("root");
        configEntities.add(dataSourceConfigEntity);
        DataSourceConfigEntity dataSourceConfigEntity2 = new DataSourceConfigEntity();
        dataSourceConfigEntity2.setCode("source2");
        dataSourceConfigEntity2.setUrl("jdbc:mysql://192.168.42.136:3306/source2?serverTimezone=Hongkong&useUnicode=true&useSSL=false&characterEncoding=utf8");
        dataSourceConfigEntity2.setPwd("root");
        dataSourceConfigEntity2.setUserName("root");
        configEntities.add(dataSourceConfigEntity2);
        Map<Object, Object> targetDataSources = new HashMap<>(
                CollectionUtils.isEmpty(configEntities) ? 1 : configEntities.size());
        configEntities.forEach(t -> {
            HikariDataSource hikariDataSource;
            try {

                hikariDataSource = DataSourceBuilder.create()
                      .type(HikariDataSource.class)
                      .url(t.getUrl())
                      .username(t.getUserName())
                      .password(t.getPwd())
//                      .driverClassName("com.mysql.cj.jdbc.Driver")
                      .driverClassName("com.mysql.jdbc.Driver")
                      .build();

                //配置Hikari连接池
                hikariDataSource.setConnectionTestQuery("select 1");//连接查询语句设置
                hikariDataSource.setAutoCommit(true);//update自动提交设置
                hikariDataSource.setConnectionTimeout(3000);//连接超时时间设置
                hikariDataSource.setIdleTimeout(3000);//连接空闲生命周期设置
                hikariDataSource.setIsolateInternalQueries(false);//执行查询启动设置
                hikariDataSource.setMaximumPoolSize(3000);//连接池允许的最大连接数量
                               hikariDataSource.setMaxLifetime(1800000);//检查空余连接优化连接池设置时间,单位毫秒
                hikariDataSource.setMinimumIdle(12);//连接池保持最小空余连接数量
                hikariDataSource.setPoolName("hikariPool--".concat(String.valueOf(t.getId()))
                        .concat("--").concat(t.getCode()));//连接池名称
                targetDataSources.put(t.getCode(),hikariDataSource);

                Connection connection = hikariDataSource.getConnection();
                if (connection != null){
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("数据源code为" + t.getCode() + "的数据源建立异常");
            }
        });
        return new HandlerDataSource(defaultDataSource, targetDataSources);
    }


}
