package vip.dcpay.commission.domain.mp;

import com.alibaba.fastjson.JSON;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vip.dcpay.alert.sdk.enums.AlertLevelEnum;
import vip.dcpay.commission.aop.annotation.CalculateLog;
import vip.dcpay.commission.config.RabbitConfig;
import vip.dcpay.commission.domain.service.MerchantCaculateManager;
import vip.dcpay.commission.infrastructure.repository.model.OrderFinishRecord;
import vip.dcpay.commission.util.AlertUtil;
import vip.dcpay.dto.mq.MessageBasic;
import vip.dcpay.dto.mq.OrderFinishMsgDto;
import vip.dcpay.log.sdk.MyLogManager;

import java.io.UnsupportedEncodingException;

@Component
public class OrderFinishReceiver {
    @Autowired
    private MerchantCaculateManager merchantCaculateManager;

    @CalculateLog(description = "接收订单消息")
    @RabbitListener(queues = RabbitConfig.COMMISSION_QUEUE)
//    public void receiveMsg(byte[] msg) {
    public void receiveMsg(byte[] msg1) {
        String msg = null;
        try {
            msg = new String(msg1,"utf8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        OrderFinishMsgDto orderFinishMsgDto = null;
        try {

            MyLogManager.develop("========接收到完成订单的消息    "+ msg);
            MessageBasic messageBasic = JSON.parseObject(msg, MessageBasic.class);
            orderFinishMsgDto = JSON.parseObject(messageBasic.getBody(), OrderFinishMsgDto.class);

        } catch (Exception e) {
            e.printStackTrace();
            MyLogManager.error("=========订单消息异常：" + e.getMessage() + "," + msg);
            AlertUtil.sendAlertMsg("=========订单消息异常", e.getMessage() + "," + msg, AlertLevelEnum.ERROR);
        }
        //过滤，只留下玩家充值并且是完成状态的订单 并且时间不能为空 订单交易金额不为空
        if (orderFinishMsgDto != null&&1==orderFinishMsgDto.getOrderType()&&100==orderFinishMsgDto.getOrderStatus()
                &&null!=orderFinishMsgDto.getTransactionAmount()&&null!=orderFinishMsgDto.getCreateTime()) {
            OrderFinishRecord orderFinishRecord = merchantCaculateManager.translatorToModel(orderFinishMsgDto);
            merchantCaculateManager.insertRecord(orderFinishRecord);
        }else{
            MyLogManager.develop("=========订单不计算金额累计："+JSON.toJSONString(orderFinishMsgDto));
        }

    }
}