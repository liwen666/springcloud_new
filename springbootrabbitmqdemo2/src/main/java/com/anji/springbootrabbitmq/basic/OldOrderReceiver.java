package com.anji.springbootrabbitmq.basic;

import com.alibaba.fastjson.JSON;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

//@Slf4j
//@Component
public class OldOrderReceiver {


    /**
     * 自动确认
     * @param msg
     */
    @RabbitListener(queues = RabbitConfig.SCHEDULE_QUEUE)
    public void receiveMsg(String msg) {
        try {
            MessageBasic messageBasic = JSON.parseObject(msg, MessageBasic.class);

            ScheduleMsgDto scheduleMsgDto = JSON.parseObject(messageBasic.getBody(), ScheduleMsgDto.class);

            System.out.println(JSON.toJSONString(scheduleMsgDto));
            System.out.println("=========================================================================================");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
