package com.example.thymeleafdemo.demo;

import com.alibaba.fastjson.JSON;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;

@Component
public class MyProducer {

    @Autowired
    RocketMQTemplate rocketMQTemplate;


    @PostConstruct
    public void produder() throws MQClientException, InterruptedException {
//        rocketMQTemplate.setDefaultDestination("ANY_BATCH_JOB_RESULT_STATUE_TOPIC");
//       rocketMQTemplate.send(new GenericMessage<String>("测试  rocketMQTemplate"));
        System.out.print("====ACL  发送 ");

        String msg = JSON.toJSONString(BatchJobExecution.builder()
                .jobExecutionId(1l)
                .status("COMPLETED")
                .endTime(new Date()).build());
        rocketMQTemplate.setDefaultDestination("ANY_BATCH_JOB_RESULT_STATUE_TOPIC");
        rocketMQTemplate.send(new GenericMessage<String>(msg));
    }

}