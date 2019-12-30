//package com.example.thymeleafdemo.demo;
//
//import com.alibaba.fastjson.JSON;
//import org.apache.rocketmq.common.message.Message;
//import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
//import org.apache.rocketmq.spring.core.RocketMQListener;
//import org.springframework.stereotype.Service;
//
//import javax.annotation.PostConstruct;
//
//@Service
//@RocketMQMessageListener(
//        topic = "ANY_BATCH_JOB_RESULT_STATUE_TOPIC",
//        consumerGroup = "anybatch_customer_group_exec_node",
//        accessKey = "BatchTaskMQ",
//        secretKey = "batch123456"
//
//)
//public class MyConsumer implements RocketMQListener<String> {
//
//    @PostConstruct
//    public void test(){
//        System.out.println("-----");
//    }
//
//    @Override
//    public void onMessage(String o) {
//        System.out.println("******************************************************");
//
//        System.out.println(JSON.toJSONString(o));
//
//    }
//}
