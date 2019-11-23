package com.temp.springboot.common.zookeeper.client;

import com.alibaba.fastjson.JSON;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.data.Stat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.ArrayList;
import java.util.Arrays;

@EnableScheduling
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class ClientStart {
    @Autowired
    private BaseZookeeper baseZookeeper;

    public static void main(String[] args) {


        ArrayList<String> list = new ArrayList<>();
        list.addAll(Arrays.asList(args));
        list.add("--job.runDate="+System.currentTimeMillis());
        list.add("--server.port=10001");
        list.add("--zookeeper.addr=172.16.102.23:2181");
        args = list.toArray(new String[]{});
        System.out.println("========client启动" + JSON.toJSONString(args));
            SpringApplication.run(ClientStart.class, args);

//        ConfigurableApplicationContext run = SpringApplication.run(ThymeleafDemoApplication.class, args);
//        ConfigurableListableBeanFactory beanFactory = run.getBeanFactory();
//        run.close();
//        DataSource bean = beanFactory.getBean(DataSource.class);
//        System.out.println(bean);



    }
    @Scheduled(fixedDelay = 1 * 3000, initialDelay = 2000)
    public void scheduleMsg() throws KeeperException, InterruptedException {
        String data = baseZookeeper.getData("/scheduler");
        System.out.println("================="+data+"==================");
//        Stat stat = baseZookeeper.exists("/scheduler",true);
//        baseZookeeper.setData("/scheduler",data+1);

    }
}
