package com.anji.springbootrabbitmq.basic;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitConfig {

    //新订单通知
    public static final String SCHEDULE_QUEUE = "toNotifyScheduleCenter";
    //新订单通知交换机
    public static final String SCHEDULE_EXCHANGE = "orderExchange";
    //新订单通知routing_key
    public static final String SCHEDULE_ROUTING_KEY = "toNotifyScheduleCenterKey";


    ////////////// 未使用
    private static final String  ORDER_PUSH_EXCHANGE= "orderPushExchange";//push

    public static final String  CANCEL_PUSH_QUEUE= "toPushCancelShowOrder";
    public static final String  CANCEL_PUSH_ROUTING_KEY= "toPushCancelShowOrderKey";

    public static final String  SHOW_PUSH_QUEUE= "toPushShowOrder";
    public static final String  SHOW_PUSH_ROUTING_KEY= "toPushShowOrderKey";


    @Bean
    public Queue schedule_queue() {
        return new Queue(SCHEDULE_QUEUE);
    }

    @Bean
    public DirectExchange schedule_exchange() {
        return new DirectExchange(SCHEDULE_EXCHANGE);
    }

    @Bean
    public Binding topicBinding() {
        return BindingBuilder.bind(schedule_queue()).to(schedule_exchange()).with(SCHEDULE_ROUTING_KEY);
    }


}
