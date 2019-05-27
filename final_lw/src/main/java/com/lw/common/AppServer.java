package com.lw.common;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lw.common.log.dao.LogMapper;
import com.lw.common.log.domain.Log;
import com.lw.common.log.service.ILogService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.context.annotation.ApplicationScope;

import javax.sql.DataSource;
import java.util.List;

@SpringBootApplication
@MapperScan({"com.lw.common.*.dao"})
public class AppServer {
    public static void main(String[] args) {
//                SpringApplication.run(AppServer.class);


        ConfigurableApplicationContext run = SpringApplication.run(AppServer.class, args);
        ConfigurableListableBeanFactory beanFactory = run.getBeanFactory();
//        DataSource bean = beanFactory.getBean(DataSource.class);
        String[] beanNamesForType = beanFactory.getBeanNamesForType(DataSource.class);
        System.out.println(JSONObject.toJSONString(beanNamesForType));
        LogMapper baseMapper = beanFactory.getBean(LogMapper.class);
        QueryWrapper<Log> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", "3085");
        System.out.println(queryWrapper.getSqlSegment());
        queryWrapper.getParamNameValuePairs().forEach((k,v)-> System.out.println("key = " + k + " ; value = " + v));
        List<Log> messageList = baseMapper.selectList(queryWrapper);
        System.out.println(messageList);
        run.close();

    }
}
