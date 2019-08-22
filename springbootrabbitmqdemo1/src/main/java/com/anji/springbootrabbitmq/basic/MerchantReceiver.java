package com.anji.springbootrabbitmq.basic;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

//@Slf4j
@Component
public class MerchantReceiver {


    /**
     * 自动确认
     * @param msg
     */
    @RabbitListener(queues = "merchant")
    public void receiveMsg(String msg) {
        System.out.println("=========================================================================================");
        System.out.println(msg);
    }
}
