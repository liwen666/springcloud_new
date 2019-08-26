package vip.dcpay.cache.domain.mq;

import com.alibaba.fastjson.JSON;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vip.dcpay.alert.sdk.enums.AlertLevelEnum;
import vip.dcpay.cache.domain.service.MerchantAlterService;
import vip.dcpay.cache.util.AlertUtil;
import vip.dcpay.dto.mq.MessageBasic;
import vip.dcpay.dto.mq.mechant.MerchantAlterMsgDto;
import vip.dcpay.log.sdk.MyLogManager;

//@Slf4j
@Component
class MerchantMsgReceiver {
    @Autowired
    private MerchantAlterService merchantAlterService;


    /**
     * 自动确认
     * @param msg
     */
    @RabbitListener(queues = "${merchant.dynamic.queue}")
    public void receiveMsg(String msg) {
        try {
            MyLogManager.info("======接收到商户更新消息: " + msg);

            MessageBasic messageBasic = JSON.parseObject(msg, MessageBasic.class);
            MerchantAlterMsgDto merchantAlterMsgDto = JSON.parseObject(messageBasic.getBody(), MerchantAlterMsgDto.class);
            //更新内存商户信息
            merchantAlterService.modifiMerchant(merchantAlterMsgDto);
        }catch (Exception e){
            e.printStackTrace();
            MyLogManager.error("商户更新消息异常：" + e.getMessage() + "," +msg);
            AlertUtil.sendAlertMsg("商户更新消息异常",e.getMessage() + "," + msg, AlertLevelEnum.ERROR);
        }
    }
}
