package com.example.component.controller;


import com.example.component.DynamicDataSource;
import com.example.component.HandlerDataSource;
import com.example.entity.DataSourceConfigDao;
import com.example.entity.DataSourceConfigEntity;
import com.example.entity.RoutSourceDao;
import com.example.entity.RoutSourceEntity;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.cglib.core.ReflectUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author sukang
 */
@RestController
@RequestMapping("")
public class DemoController {

    @Inject
    private DataSourceConfigDao dataSourceConfigDao;

    @Inject
    private RoutSourceDao routSourceDao;

    @Inject
    private ApplicationContext applicationContext;

    @GetMapping("/success")
    @DynamicDataSource
    public Object success(@RequestParam(name = "code") String code){

        Optional<RoutSourceEntity> sourceDtoById = routSourceDao.findById(1);
        String resp = "{\"status\":\"success\",\"text\":\"%s\"}";

        if (!sourceDtoById.isPresent()){
            return String.format(resp,"查询数据不存在");
        }
        return String.format(resp,sourceDtoById.get().getText());
    }

    @GetMapping("/data")
    public Object success2(){

        HandlerDataSource handlerDataSource = applicationContext.getBean(HandlerDataSource.class);


        return "{\"status\":\"success\"}";
    }



    @PostMapping("addDataSource")
    public Object loadingDataSource(){

        try {
            HandlerDataSource handlerDataSource = applicationContext.getBean(HandlerDataSource.class);


            Field targetDataSources  = handlerDataSource.getClass()
                    .getSuperclass().getDeclaredField("targetDataSources");
            targetDataSources.setAccessible(true);
            Field resolvedDataSources = handlerDataSource.getClass().getSuperclass()
                    .getDeclaredField("resolvedDataSources");
            resolvedDataSources.setAccessible(true);
            Map<Object, Object> o = (Map<Object, Object>)targetDataSources.get(handlerDataSource);

            Map<Object, DataSource> dataSourceMap = (Map<Object, DataSource>)resolvedDataSources.get(handlerDataSource);

            List<DataSourceConfigEntity> configDaoAll = dataSourceConfigDao.findAll();

            for (DataSourceConfigEntity dataSourceConfigEntity : configDaoAll) {
                if (!dataSourceMap.containsKey(dataSourceConfigEntity.getCode())
                    && !o.containsKey(dataSourceConfigEntity.getCode())
                ){
                    HikariDataSource hikariDataSource = DataSourceBuilder.create()
                            .type(HikariDataSource.class)
                            .url(dataSourceConfigEntity.getUrl())
                            .username(dataSourceConfigEntity.getUserName())
                            .password(dataSourceConfigEntity.getPwd())
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
                    hikariDataSource.setPoolName("hikariPool--".concat(String.valueOf(dataSourceConfigEntity.getId()))
                            .concat("--").concat(dataSourceConfigEntity.getCode()));//连接池名称


                    o.put(dataSourceConfigEntity.getCode(),hikariDataSource);
                    dataSourceMap.put(dataSourceConfigEntity.getCode(),hikariDataSource);

                    System.out.println("新数据源" + dataSourceConfigEntity.getCode() + "添加成功");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "{\"status\":\"success\"}";
    }

}
