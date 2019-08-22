package com.anji.springbootrabbitmq.basic;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class DynamicMessageHandler {

    //支持自动声明绑定，声明之后自动监听队列的队列，此时@RabbitListener注解的queue和bindings不能同时指定，否则报错
    @RabbitListener(queues = "${spring.dynamic.queue}")
    public void handleMessage(Message message){
        System.out.println("====消费消息"+message.getMessageProperties().getConsumerQueue()+"===handleMessage");
        System.out.println(message.getMessageProperties());
        System.out.println(new String(message.getBody()));
        System.out.println("=========================================dynamic================================================");
    }


}