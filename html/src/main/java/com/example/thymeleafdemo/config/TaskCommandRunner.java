package com.example.thymeleafdemo.config;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.DefaultApplicationArguments;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Arrays;


/**
 * spring task 执行监听器，执行job任务
 *
 * @author yuanxingyu
 * @date 2018/3/28
 */
@Service
public class TaskCommandRunner implements CommandLineRunner {
    @Autowired
    private ConfigurableApplicationContext applicationContext;

    private static final Logger logger = LoggerFactory.getLogger(TaskCommandRunner.class);



    @Autowired
    private DefaultApplicationArguments applicationArguments;



    @Override
    public void run(String... args1) throws Exception {
        System.out.println(JSON.toJSONString(args1));
    }

}
