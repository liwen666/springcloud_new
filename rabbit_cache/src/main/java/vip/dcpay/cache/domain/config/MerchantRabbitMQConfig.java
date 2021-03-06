package vip.dcpay.cache.domain.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import vip.dcpay.constant.mq.NamesConstant;

/**
 * Description: 商户信息监听MQ配置
 */
@Configuration
public class MerchantRabbitMQConfig {
    @Value("${merchant.dynamic.queue}")
    public  String QUEUE_NAME ;
    public final static String ROUTING_KEY = NamesConstant.toNotifyBusinessMerchantChangeKey_KEY;
    public final static String EXCHANGES_NAME = NamesConstant.toBusinessMerchantExchange;

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


//    /**
//     * 设置交换器，这里使用的是topic exchange
//     *
//     * @return
//     */
//    @Bean
//    public TopicExchange exchange1() {
//        //是否持久化
//        boolean durable = true;
//        // 当所有消费客户端连接断开后，是否自动删除队列
//        boolean autoDelete = false;
//        return new TopicExchange(EXCHANGES_NAME, durable, autoDelete);
//    }
    /**
     * 设置交换器，这里使用的是DIRECT exchange
     *
     * @return
     */
    @Bean
    public DirectExchange exchange1() {
        //是否持久化
        boolean durable = true;
        // 当所有消费客户端连接断开后，是否自动删除队列
        boolean autoDelete = false;
        return new DirectExchange(EXCHANGES_NAME, durable, autoDelete);
    }
//    //绑定路由
//    @Bean
//    public Binding binding1(Queue queue1, TopicExchange exchange1) {
//        return BindingBuilder.bind(queue1).to(exchange1).with(ROUTING_KEY);
//    }
    //绑定路由
    @Bean
    public Binding binding1(Queue queue1, DirectExchange exchange1) {
        return BindingBuilder.bind(queue1).to(exchange1).with(ROUTING_KEY);
    }

}
