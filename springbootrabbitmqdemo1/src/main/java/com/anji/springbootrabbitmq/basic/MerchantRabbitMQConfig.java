package com.anji.springbootrabbitmq.basic;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Description:
 * author: chenqiang
 * date: 2018/5/28 20:55
 */
@Configuration
public class MerchantRabbitMQConfig {
    public final static String QUEUE_NAME = "merchant";
    public final static String ROUTING_KEY = "route-key1";
    public final static String EXCHANGES_NAME = "demo-exchanges1";


//    //完成订单队列
//    public static final String COMMISSION_QUEUE = NamesConstant.toOrderFinish_QUEUE;
//    //完成订单通知交换机
//    public static final String COMMISSION_EXCHANGE = NamesConstant.toOrderFinish;
//    //完成订单routing_key
//    public static final String COMMISSION_ROUTING_KEY = NamesConstant.toOrderFinish_KEY;
    @Bean
    public Queue queue1() {
        //是否持久化
        boolean durable = true;
        //仅创建者可以使用的私有队列，断开后自动删除
        boolean exclusive = false;
        //当所有消费客户端连接断开后，是否自动删除队列
        boolean autoDelete = false;
        return new Queue(QUEUE_NAME, durable, exclusive, autoDelete);
    }

    /**
     * 设置交换器，这里使用的是topic exchange
     *
     * @return
     */
    @Bean
    public TopicExchange exchange1() {
        //是否持久化
        boolean durable = true;
        // 当所有消费客户端连接断开后，是否自动删除队列
        boolean autoDelete = false;
        return new TopicExchange(EXCHANGES_NAME, durable, autoDelete);
    }

    //绑定路由
    @Bean
    public Binding binding1(Queue queue1, TopicExchange exchange1) {
        return BindingBuilder.bind(queue1).to(exchange1).with(ROUTING_KEY);
    }

    @Bean
    public SimpleMessageListenerContainer container(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
//        container.setQueueNames(QUEUE_NAME,"springboot.demo.test1");
        container.setQueueNames("springboot.demo.test1");
        container.setMessageListener(receiver());
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);       //设置手动，默认为Auto

        return container;
    }
//
    @Bean
    public MessageReceiver receiver() {
        return new MessageReceiver();
    }
}
