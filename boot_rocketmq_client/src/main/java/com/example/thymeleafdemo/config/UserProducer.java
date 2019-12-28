//package com.example.thymeleafdemo.config;
//
//import com.alibaba.fastjson.JSON;
//import org.apache.rocketmq.client.producer.DefaultMQProducer;
//import org.apache.rocketmq.client.producer.SendResult;
//import org.apache.rocketmq.common.message.Message;
//import org.apache.rocketmq.remoting.common.RemotingHelper;
//import org.omg.CORBA.UNKNOWN;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.PostConstruct;
//
///**
// * @Author 18011618
// * @Date 10:31 2018/7/18
// * @Function 模拟用户消息发送
// */
//@Component
//public class UserProducer {
//    @Autowired
//    RocketMqCfgProperties rocketMqCfgProperties;
//
//    @PostConstruct
//    public void produder() {
////         DefaultMQProducer producer = new DefaultMQProducer(producerGroup);
//        DefaultMQProducer producer = new DefaultMQProducer(rocketMqCfgProperties.getProducerGroup());
//        producer.setNamesrvAddr(rocketMqCfgProperties.getNamesrvaddr());
//
////        Message ID	消息的全局唯一标识，由 MQ 系统自动生成，唯一标识某条消息。
////        Topic	消息主题，一级消息类型，通过 Topic 对消息进行分类。
////        Tag	消息标签，二级消息类型，用来进一步区分某个 Topic 下的消息分类。
////        messageTrackList
////        consumerGroup	消费集群名称
////        trackType	当前状态。取值如下
////        CONSUMED：已消费
////        CONSUMED_BUT_FILTERED：已被过滤
////        NOT_CONSUME_YET：暂未消费
////        NOT_ONLINE：客户端不在线
////        UNKNOWN：其他问题
////        Operation	重发消息、查看异常
//        try {
//            producer.start();
//            for (int i = 0; i < 100; i++) {
//                UserContent userContent = new UserContent(String.valueOf(i), "abc" + i);
//                String jsonstr = JSON.toJSONString(userContent);
//                System.out.println("发送消息:" + jsonstr);
//                Message message = new Message(rocketMqCfgProperties.getTopic(), "user-tag", jsonstr.getBytes(RemotingHelper.DEFAULT_CHARSET));
//                SendResult result = producer.send(message);
//                System.err.println("发送响应：MsgId:" + result.getMsgId() + "，发送状态:" + result.getSendStatus());
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            producer.shutdown();
//        }
//    }
//}