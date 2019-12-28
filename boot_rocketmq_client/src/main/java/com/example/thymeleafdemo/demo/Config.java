//package com.example.thymeleafdemo.demo;
//
//import org.apache.rocketmq.acl.common.AclClientRPCHook;
//import org.apache.rocketmq.acl.common.SessionCredentials;
//import org.apache.rocketmq.client.producer.DefaultMQProducer;
//import org.apache.rocketmq.remoting.RPCHook;
//import org.apache.rocketmq.spring.core.RocketMQTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.stereotype.Component;
//
///**
// * <p>
// * 描述
// * </p>
// *
// * @author lw
// * @since 2019/5/26 23:40
// */
//@Component
//public class Config {
//        @Autowired
//        RocketMqCfgProperties rocketMqCfgProperties;
//    @Bean
//    RocketMQTemplate rocketMQTemplate(){
//        RocketMQTemplate rocketMQTemplate = new RocketMQTemplate();
//        DefaultMQProducer producer = new DefaultMQProducer(rocketMqCfgProperties.getProducerGroup(), getAclRPCHook());
//        producer.setNamesrvAddr(rocketMqCfgProperties.getNamesrvaddr());
//        rocketMQTemplate.setProducer(producer);
//        rocketMQTemplate.setDefaultDestination("ANY_BATCH_JOB_RESULT_STATUE_TOPIC");
//        return rocketMQTemplate;
//    }
//
//    RPCHook getAclRPCHook() {
//        return new AclClientRPCHook(new SessionCredentials(rocketMqCfgProperties.getAccessKey(),rocketMqCfgProperties.getSecretKey()));
//    }
//}
