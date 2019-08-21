package com.anji.springbootrabbitmq.basic;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Description:
 * author: chenqiang
 * date: 2018/5/28 21:09
 */
@Component
public class MessageSender {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    private static final Logger log= LoggerFactory.getLogger(MessageSender.class);

    public void send() throws InterruptedException {
        //correlationData:消息ID
        CorrelationData correlationId=new CorrelationData(UUID.randomUUID().toString());

        // ConfirmListener是当消息无法发送到Exchange被触发，此时Ack为False，这时cause包含发送失败的原因，例如exchange不存在时
        // 需要在系统配置文件中设置 publisher-confirms: true
        if(!rabbitTemplate.isConfirmListener()){
            rabbitTemplate.setConfirmCallback(((correlationData, ack, cause) -> {
                if(ack){
                    log.info(">>>>>>> 消息id:{} 发送成功", correlationData.getId());
                }else {
                    log.info(">>>>>>> 消息id:{} 发送失败", correlationData.getId());
                }
            }));
        }

        // ReturnCallback 是在交换器无法将路由键路由到任何一个队列中，会触发这个方法。
        // 需要在系统配置文件中设置 publisher-returns: true
        rabbitTemplate.setReturnCallback(((message, replyCode, replyText, exchange, routingKey) -> {
            log.info("消息id:{} 发送失败",message.getMessageProperties().getCorrelationId());
        }));

//        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGES_NAME,RabbitMQConfig.ROUTING_KEY,">>>>> Hello World", correlationId);

//        rabbitTemplate.convertAndSend("orderFinishExchange","toOrderFinishKey",">>>>> Hello World", correlationId);
//        log.info("Already sent message");











        /************************************测生产试项目*************************************************************/
        rabbitTemplate.setQueue("toOrderFinish_QUEUE");
        ExecutorService es = Executors.newFixedThreadPool(5);//创建固定大小的线程
        for(int i=0;i<100;i++){
            //线程池可以 处理Callablel类型的任务  获取返回值
            /**
             * 线程是5个5个的执行任务
             * 这里下面两个处理任务的效果是一样的
             */
//                es.submit(task);//这个会返回一个future对象
            es.execute(new Thread(){
                @Override
                public void run() {

                    for (int i = 0; i < 10; i++) {
                        OrderFinishMsgDto build = OrderFinishMsgDto.builder().createTime(new Date()).customerId((long) i).income(new BigDecimal(i))
                                .orderNo(i+"").orderStatus(100).orderType(1).transactionAmount(new BigDecimal(100)).realPaymentAmount(new BigDecimal(100))
                                .orderFinishTime(new Date()).payWay("wexin").recordId((long) i)
                                .merchantId((long) i).build();
                        MessageBasic build1 = MessageBasic.builder().body(JSON.toJSONString(build)).build();
                            rabbitTemplate.convertAndSend("orderFinishExchange","toOrderFinishKey",JSON.toJSONString(build1), correlationId);

                    }
                }
            });

        }
        Thread.sleep(5000);
        es.shutdown();
        System.out.println("消息发送成功");
        /************************************测试项目*************************************************************/
   }

    public void send2() throws InterruptedException {
        //correlationData:消息ID
        CorrelationData correlationId=new CorrelationData(UUID.randomUUID().toString());

        // ConfirmListener是当消息无法发送到Exchange被触发，此时Ack为False，这时cause包含发送失败的原因，例如exchange不存在时
        // 需要在系统配置文件中设置 publisher-confirms: true
        if(!rabbitTemplate.isConfirmListener()){
            rabbitTemplate.setConfirmCallback(((correlationData, ack, cause) -> {
                if(ack){
                    log.info(">>>>>>> 消息id:{} 发送成功", correlationData.getId());
                }else {
                    log.info(">>>>>>> 消息id:{} 发送失败", correlationData.getId());
                }
            }));
        }

        // ReturnCallback 是在交换器无法将路由键路由到任何一个队列中，会触发这个方法。
        // 需要在系统配置文件中设置 publisher-returns: true
        rabbitTemplate.setReturnCallback(((message, replyCode, replyText, exchange, routingKey) -> {
            log.info("消息id:{} 发送失败",message.getMessageProperties().getCorrelationId());
        }));

//        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGES_NAME,RabbitMQConfig.ROUTING_KEY,">>>>> Hello World", correlationId);

//        rabbitTemplate.convertAndSend("orderFinishExchange","toOrderFinishKey",">>>>> Hello World", correlationId);
//        log.info("Already sent message");











        /************************************测生产试项目*************************************************************/
        rabbitTemplate.setQueue("toOrderFinish_QUEUE");
        ExecutorService es = Executors.newFixedThreadPool(5);//创建固定大小的线程
        for(int i=0;i<10;i++){
            //线程池可以 处理Callablel类型的任务  获取返回值
            /**
             * 线程是5个5个的执行任务
             * 这里下面两个处理任务的效果是一样的
             */
//                es.submit(task);//这个会返回一个future对象
            es.execute(new Thread(){
                @Override
                public void run() {

                    for (int i = 0; i < 10; i++) {
                        OrderFinishMsgDto build = OrderFinishMsgDto.builder().createTime(new Date()).customerId((long) i).income(new BigDecimal(i))
                                .orderNo(i+"").orderStatus(100).orderType(1).transactionAmount(new BigDecimal(100)).realPaymentAmount(new BigDecimal(100))
                                .orderFinishTime(new Date()).payWay("wexin").recordId((long) i)
                                .merchantId((long) i).build();
                        rabbitTemplate.convertAndSend("orderFinishExchange","toOrderFinishKey",JSON.toJSONString(build), correlationId);

                    }
                }
            });

        }
        Thread.sleep(5000);
        es.shutdown();
        System.out.println("消息发送成功");
        /************************************测试项目*************************************************************/
    }

    public void sendSchedule() throws InterruptedException {
        //correlationData:消息ID
        CorrelationData correlationId=new CorrelationData(UUID.randomUUID().toString());

        // ConfirmListener是当消息无法发送到Exchange被触发，此时Ack为False，这时cause包含发送失败的原因，例如exchange不存在时
        // 需要在系统配置文件中设置 publisher-confirms: true
        if(!rabbitTemplate.isConfirmListener()){
            rabbitTemplate.setConfirmCallback(((correlationData, ack, cause) -> {
                if(ack){
                    log.info(">>>>>>> 消息id:{} 发送成功", correlationData.getId());
                }else {
                    log.info(">>>>>>> 消息id:{} 发送失败", correlationData.getId());
                }
            }));
        }

        // ReturnCallback 是在交换器无法将路由键路由到任何一个队列中，会触发这个方法。
        // 需要在系统配置文件中设置 publisher-returns: true
        rabbitTemplate.setReturnCallback(((message, replyCode, replyText, exchange, routingKey) -> {
            log.info("消息id:{} 发送失败",message.getMessageProperties().getCorrelationId());
        }));

//        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGES_NAME,RabbitMQConfig.ROUTING_KEY,">>>>> Hello World", correlationId);

//        rabbitTemplate.convertAndSend("orderFinishExchange","toOrderFinishKey",">>>>> Hello World", correlationId);
//        log.info("Already sent message");











        /************************************测生产试项目*************************************************************/
        rabbitTemplate.setQueue("toNotifyScheduleCenter");
        ExecutorService es = Executors.newFixedThreadPool(5);//创建固定大小的线程
//        for(int i=0;i<100;i++){
        //线程池可以 处理Callablel类型的任务  获取返回值
        /**
         * 线程是5个5个的执行任务
         * 这里下面两个处理任务的效果是一样的
         */
//                es.submit(task);//这个会返回一个future对象
        es.execute(new Thread(){
            @Override
            public void run() {

                for (int i = 0; i < 10; i++) {
                    ScheduleMsgDto build = ScheduleMsgDto.builder().orderType(1).amount(new BigDecimal(100)).invoice("605706285423968256").merchantIds(1+"")
                            .payWay("weixin").show(true).tradeType(1).build();
                    MessageBasic build1 = MessageBasic.builder().body(JSON.toJSONString(build)).build();
                    rabbitTemplate.convertAndSend("orderExchange","toNotifyScheduleCenterKey",JSON.toJSONString(build1), correlationId);

                }
            }
        });

//        }
        Thread.sleep(5000);
        es.shutdown();
        System.out.println("消息发送成功");
    }


    public void sendScheduleAuto(String invoice,int num,boolean show) throws InterruptedException {
        //correlationData:消息ID
        CorrelationData correlationId=new CorrelationData(UUID.randomUUID().toString());

        // ConfirmListener是当消息无法发送到Exchange被触发，此时Ack为False，这时cause包含发送失败的原因，例如exchange不存在时
        // 需要在系统配置文件中设置 publisher-confirms: true
        if(!rabbitTemplate.isConfirmListener()){
            rabbitTemplate.setConfirmCallback(((correlationData, ack, cause) -> {
                if(ack){
                    log.info(">>>>>>> 消息id:{} 发送成功", correlationData.getId());
                }else {
                    log.info(">>>>>>> 消息id:{} 发送失败", correlationData.getId());
                }
            }));
        }

        // ReturnCallback 是在交换器无法将路由键路由到任何一个队列中，会触发这个方法。
        // 需要在系统配置文件中设置 publisher-returns: true
        rabbitTemplate.setReturnCallback(((message, replyCode, replyText, exchange, routingKey) -> {
            log.info("消息id:{} 发送失败",message.getMessageProperties().getCorrelationId());
        }));

//        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGES_NAME,RabbitMQConfig.ROUTING_KEY,">>>>> Hello World", correlationId);

//        rabbitTemplate.convertAndSend("orderFinishExchange","toOrderFinishKey",">>>>> Hello World", correlationId);
//        log.info("Already sent message");











        /************************************测生产试项目*************************************************************/
        rabbitTemplate.setQueue("toNotifyScheduleCenter");
        ExecutorService es = Executors.newFixedThreadPool(5);//创建固定大小的线程
//        for(int i=0;i<100;i++){
        //线程池可以 处理Callablel类型的任务  获取返回值
        /**
         * 线程是5个5个的执行任务
         * 这里下面两个处理任务的效果是一样的
         */
//                es.submit(task);//这个会返回一个future对象
        es.execute(new Thread(){
            @Override
            public void run() {

                for (int i = 0; i < num; i++) {
                    ScheduleMsgDto build = ScheduleMsgDto.builder().orderType(1).amount(new BigDecimal(100)).invoice(invoice)
                            .payWay("weixin").show(show).tradeType(1).build();
                    MessageBasic build1 = MessageBasic.builder().body(JSON.toJSONString(build)).build();
                    rabbitTemplate.convertAndSend("orderExchange","toNotifyScheduleCenterKey",JSON.toJSONString(build1), correlationId);

                }
            }
        });

//        }
        Thread.sleep(5000);
        es.shutdown();
        System.out.println("消息发送成功");
    }

    public void test() {
        //correlationData:消息ID
        CorrelationData correlationId=new CorrelationData(UUID.randomUUID().toString());

        // ConfirmListener是当消息无法发送到Exchange被触发，此时Ack为False，这时cause包含发送失败的原因，例如exchange不存在时
        // 需要在系统配置文件中设置 publisher-confirms: true
        if(!rabbitTemplate.isConfirmListener()){
            rabbitTemplate.setConfirmCallback(((correlationData, ack, cause) -> {
                if(ack){
                    log.info(">>>>>>> 消息id:{} 发送成功", correlationData.getId());
                }else {
                    log.info(">>>>>>> 消息id:{} 发送失败", correlationData.getId());
                }
            }));
        }

        // ReturnCallback 是在交换器无法将路由键路由到任何一个队列中，会触发这个方法。
        // 需要在系统配置文件中设置 publisher-returns: true
        rabbitTemplate.setReturnCallback(((message, replyCode, replyText, exchange, routingKey) -> {
            log.info("消息id:{} 发送失败",message.getMessageProperties().getCorrelationId());
        }));

        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGES_NAME,RabbitMQConfig.ROUTING_KEY,">>>>> Hello World test", correlationId);
        log.info("Already sent message");
    }

    public void testMerchant() {
        //correlationData:消息ID
        CorrelationData correlationId=new CorrelationData(UUID.randomUUID().toString());

        // ConfirmListener是当消息无法发送到Exchange被触发，此时Ack为False，这时cause包含发送失败的原因，例如exchange不存在时
        // 需要在系统配置文件中设置 publisher-confirms: true
        if(!rabbitTemplate.isConfirmListener()){
            rabbitTemplate.setConfirmCallback(((correlationData, ack, cause) -> {
                if(ack){
                    log.info(">>>>>>> 消息id:{} 发送成功", correlationData.getId());
                }else {
                    log.info(">>>>>>> 消息id:{} 发送失败", correlationData.getId());
                }
            }));
        }

        // ReturnCallback 是在交换器无法将路由键路由到任何一个队列中，会触发这个方法。
        // 需要在系统配置文件中设置 publisher-returns: true
        rabbitTemplate.setReturnCallback(((message, replyCode, replyText, exchange, routingKey) -> {
            log.info("消息id:{} 发送失败",message.getMessageProperties().getCorrelationId());
        }));

        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGES_NAME+"1",RabbitMQConfig.ROUTING_KEY+"1",">>>>> Hello World merchant", correlationId);
        log.info("Already sent message");
    }
}
