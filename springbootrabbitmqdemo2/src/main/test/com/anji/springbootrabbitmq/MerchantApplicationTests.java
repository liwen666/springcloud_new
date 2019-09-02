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
public class MerchantApplicationTests {

    @Autowired
    private MessageSender sender;

    @Test
    public void aletrMerchant() throws InterruptedException {
        for(int i=0;i<11;i++){
            MerchantAlterItemEnum[] values = MerchantAlterItemEnum.values();
            for (MerchantAlterItemEnum merchantAlterItemEnum : values) {
                MessageBasic messageBasic = new MessageBasic();
                messageBasic.setBody(JSON.toJSONString(MerchantAlterMsgDto.builder().merchantId((long) i).alterItem(merchantAlterItemEnum).build()));
                sender.aletrMerchant(messageBasic);
            }
        }


    }
    @Test
    public void aletrMerchantOne1()  {
            MerchantAlterItemEnum[] values = MerchantAlterItemEnum.values();
            for (MerchantAlterItemEnum merchantAlterItemEnum : values) {
                MessageBasic messageBasic = new MessageBasic();
                messageBasic.setBody(JSON.toJSONString(MerchantAlterMsgDto.builder().merchantId(10l).alterItem(merchantAlterItemEnum).build()));
                sender.aletrMerchant(messageBasic);
            }
    }
    @Test
    public void aletrMerchantError()  {
            MessageBasic messageBasic = new MessageBasic();
            messageBasic.setBody(JSON.toJSONString(MerchantAlterMsgDto.builder().merchantId(7l).alterItem(MerchantAlterItemEnum.LOGIN).build()));
            sender.aletrMerchant(messageBasic);
    }

    @Test
    public void aletrMerchantAsset() throws InterruptedException {
        MessageBasic messageBasic = new MessageBasic();
        messageBasic.setType(MessageType.AlTER_ASSET_NOTIFY);
        AssetChangeMsgDto cny = AssetChangeMsgDto.builder().accountId(1l).accountType(AccountTypeEnum.MERCHANT.code()).currency("CNY").build();
        AssetChangeMsgDto rmb = AssetChangeMsgDto.builder().accountId(2l).accountType(AccountTypeEnum.MERCHANT.code()).currency("rmb").build();
        AssetChangeMsgDto rmb1 = AssetChangeMsgDto.builder().accountId(3l).accountType(AccountTypeEnum.OUTER_PLATFORM.code()).currency("rmb").build();

        messageBasic.setBody(JSON.toJSONString(new ArrayList<AssetChangeMsgDto>(){{
            add(rmb1);
            add(cny);add(rmb);}}));
        sender.aletrMerchantAsset(messageBasic);

    }

    @Test
    public void aletrMerchantOne() throws InterruptedException {
        MessageBasic messageBasic = new MessageBasic();
        messageBasic.setBody(JSON.toJSONString(MerchantAlterMsgDto.builder().merchantId(1l).alterItem(MerchantAlterItemEnum.REALNAME).build()));
        sender.aletrMerchant(messageBasic);
    }



}
