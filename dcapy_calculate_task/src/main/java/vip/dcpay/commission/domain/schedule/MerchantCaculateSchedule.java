package vip.dcpay.commission.domain.schedule;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import vip.dcpay.commission.infrastructure.repository.AgentRepository;
import vip.dcpay.commission.infrastructure.repository.model.MerchantDayAmount;
import vip.dcpay.commission.infrastructure.repository.model.OrderFinishRecord;
import vip.dcpay.log.sdk.MyLogManager;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class MerchantCaculateSchedule {

    @Autowired
    private AgentRepository agentRepository;

    @Scheduled(cron = "0/3 * * * * ?") // 每3秒执行一次
    public void calculateDayAmount() {
        List<OrderFinishRecord> orderFinishRecords = agentRepository.selectOrderFinishList();
        if(orderFinishRecords.size()==0){
            return;
        }
        List<MerchantDayAmount> merchantDayAmounts = caclcuteAmount(orderFinishRecords);
        MyLogManager.develop("==========累计商家流水数据： "+ JSON.toJSONString(merchantDayAmounts));
        //更新数据库数据  删除增量表数据
        int result = agentRepository.updateMerchantDayAmount(merchantDayAmounts,orderFinishRecords);
    }
    //根据订单日期累计订单成交额
    private List<MerchantDayAmount> caclcuteAmount(List<OrderFinishRecord> orderFinishRecords) {
        //对数据按商户分组  然后按照日期分组  进行日交易额统计
        Map<Long, Map<Date, List<OrderFinishRecord>>> collect = orderFinishRecords.stream().collect(Collectors.groupingBy(OrderFinishRecord::getMerchantId, Collectors.groupingBy(OrderFinishRecord::getSimpleTime)));
        //计算商户完成订单的金额累计
        List<MerchantDayAmount> merchantDayAmounts = new ArrayList<>();
        for (Map.Entry me : collect.entrySet()) {
            Map<Date, List<OrderFinishRecord>> value = (Map<Date, List<OrderFinishRecord>>) me.getValue();
            for (Map.Entry inner : value.entrySet()) {
                MerchantDayAmount merchantDayAmount = new MerchantDayAmount();
                merchantDayAmount.setMerchantId((Long) me.getKey());
                merchantDayAmount.setCreateTime((Date) inner.getKey());
                //累计成交额
                List<OrderFinishRecord> value1 = (List<OrderFinishRecord>) inner.getValue();
                BigDecimal sumAmount = new BigDecimal(0);
                for (OrderFinishRecord orderFinishRecord : value1) {
                    sumAmount = sumAmount.add(orderFinishRecord.getTransactionAmount());
                }
                merchantDayAmount.setDayAmountSum(sumAmount);
                merchantDayAmounts.add(merchantDayAmount);
            }
        }
        return merchantDayAmounts;
    }
}
