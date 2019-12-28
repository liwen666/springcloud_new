//package com.example.thymeleafdemo.config;
//
//import com.alibaba.fastjson.JSON;
//import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
//import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
//import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
//import org.apache.rocketmq.common.message.MessageExt;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.PostConstruct;
//
///**
//* @Author 18011618
//* @Date 10:31 2018/7/18
//* @Function 模拟用户消息消费
//*/
//@Component
//public class UserConsumer {
//    @Autowired
//    RocketMqCfgProperties rocketMqCfgProperties;
//
//    @PostConstruct
//    public void consumer() {
//        System.out.println("==================================="+JSON.toJSONString(rocketMqCfgProperties));
//        System.err.println("init defaultMQPushConsumer");
//        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(rocketMqCfgProperties.getConumerGroup());
//        consumer.setNamesrvAddr(rocketMqCfgProperties.getNamesrvaddr());
//        try {
//            consumer.subscribe(rocketMqCfgProperties.getTopic(), "user-tag");
//            consumer.registerMessageListener((MessageListenerConcurrently) (list, context) -> {
//                try {
//                    for (MessageExt messageExt : list) {
//
//                        System.err.println("消费消息: " + new String(messageExt.getBody()));//输出消息内容
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    return ConsumeConcurrentlyStatus.RECONSUME_LATER; //稍后再试
//                }
//                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS; //消费成功
//            });
//            consumer.start();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }
//}