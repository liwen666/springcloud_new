package vip.dcpay.commission.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vip.dcpay.commission.infrastructure.repository.AgentRepository;
import vip.dcpay.commission.infrastructure.repository.model.OrderFinishRecord;
import vip.dcpay.commission.infrastructure.translator.AgentTranslator;
import vip.dcpay.dto.mq.OrderFinishMsgDto;

/**
 * @Auther: liq
 * @Date: 2019/7/6 18:50
 * @Description:
 */
@Service
public class MerchantCaculateManager {
    @Autowired
    private AgentRepository agentRepository;
    @Autowired
    private AgentTranslator agentTranslator;
   public OrderFinishRecord translatorToModel(OrderFinishMsgDto orderFinishMsgDto){

        return OrderFinishRecord.builder().income(orderFinishMsgDto.getIncome())
                .recordId(orderFinishMsgDto.getRecordId())
                .orderNo(orderFinishMsgDto.getOrderNo())
                .createTime(orderFinishMsgDto.getCreateTime())
                .simpleTime(orderFinishMsgDto.getCreateTime())
                .merchantId(orderFinishMsgDto.getMerchantId())
                .transactionAmount(orderFinishMsgDto.getTransactionAmount())
                .payWay(orderFinishMsgDto.getPayWay())
                .build();
    }

    public void insertRecord(OrderFinishRecord orderFinishRecord) {
        agentRepository.insertRecord(orderFinishRecord);
    }
}
