package com.anji.springbootrabbitmq;

import com.alibaba.fastjson.JSON;
import com.anji.springbootrabbitmq.basic.MessageBasic;
import com.anji.springbootrabbitmq.basic.MessageSender;
import com.anji.springbootrabbitmq.basic.MessageType;
import com.anji.springbootrabbitmq.basic.msg.AccountTypeEnum;
import com.anji.springbootrabbitmq.basic.msg.AssetChangeMsgDto;
import com.anji.springbootrabbitmq.basic.msg.MerchantAlterItemEnum;
import com.anji.springbootrabbitmq.basic.msg.MerchantAlterMsgDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ScheduleApplicationTests {

    @Autowired
    private MessageSender sender;


    @Test
    public void testReceiver() throws InterruptedException {
        sender.send();
    }

    @Test
    public void testReceiver2() throws InterruptedException {
        sender.send2();
    }

    @Test
    public void contextLoads() {
    }

    @Test
    public void sendSchedule() throws InterruptedException {
        sender.sendSchedule();
    }

    @Test
    public void sendScheduleAuto() throws InterruptedException {
//		sender.sendScheduleAuto(10,true);
//		for(int i=0;i<10;i++){
			sender.sendScheduleAuto("605706285423968256",10,true);
//        sender.sendScheduleAuto("605706285423968256", 10, false);
        Thread.sleep(3000);
//		}

//		推送这个商户可以完成推送  111111
    }


}
