package vip.dcpay.commission.infrastructure.repository;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import vip.dcpay.commission.domain.constant.CaculateContant;
import vip.dcpay.commission.domain.repository.IAgentRepository;
import vip.dcpay.commission.infrastructure.repository.dao.MerchantDayAmountDao;
import vip.dcpay.commission.infrastructure.repository.dao.OrderFinishRecordDao;
import vip.dcpay.commission.infrastructure.repository.model.MerchantDayAmount;
import vip.dcpay.commission.infrastructure.repository.model.OrderFinishRecord;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

;

@Repository
public class AgentRepository implements IAgentRepository {
    @Autowired(required = false)
    private OrderFinishRecordDao orderFinishRecordDao;
    @Autowired(required = false)
    private MerchantDayAmountDao merchantDayAmountDao;

    public void insertRecord(OrderFinishRecord orderFinishRecord) {
        orderFinishRecordDao.insert(orderFinishRecord);
    }

    public List<OrderFinishRecord> selectOrderFinishList() {
        return orderFinishRecordDao.selectList(Wrappers.<OrderFinishRecord>lambdaQuery().apply(" 1=1 limit " + CaculateContant.ORDER_FINISH_CAULATE));
    }

    @Transactional
    public int updateMerchantDayAmount(List<MerchantDayAmount> merchantDayAmounts, List<OrderFinishRecord> orderFinishRecords) {
//        更新实时数据
       for(MerchantDayAmount amountDay:merchantDayAmounts){

           MerchantDayAmount merchantDayAmount = merchantDayAmountDao.selectOne(Wrappers.<MerchantDayAmount>lambdaQuery().eq(MerchantDayAmount::getCreateTime, amountDay.getCreateTime()).eq(MerchantDayAmount::getMerchantId, amountDay.getMerchantId()));
           //表示更新数据
           if (null!=merchantDayAmount) {
               merchantDayAmount.setDayAmountSum(merchantDayAmount.getDayAmountSum().add(amountDay.getDayAmountSum()));
               merchantDayAmount.setRemark(merchantDayAmount.getRemark()+1);
               merchantDayAmount.setModifyTime(null);
               int update = merchantDayAmountDao.update(merchantDayAmount, Wrappers.<MerchantDayAmount>lambdaUpdate().eq(MerchantDayAmount::getMerchantId, merchantDayAmount.getMerchantId()).eq(MerchantDayAmount::getCreateTime, merchantDayAmount.getCreateTime()).eq(MerchantDayAmount::getRemark, merchantDayAmount.getRemark() - 1));
               while (update==0){
                   MerchantDayAmount merchantDayAmount1 = merchantDayAmountDao.selectOne(Wrappers.<MerchantDayAmount>lambdaQuery().eq(MerchantDayAmount::getCreateTime, amountDay.getCreateTime()).eq(MerchantDayAmount::getMerchantId, 1));
                   merchantDayAmount1.setDayAmountSum(merchantDayAmount1.getDayAmountSum().add(amountDay.getDayAmountSum()));
                   merchantDayAmount1.setRemark(merchantDayAmount1.getRemark()+1);
                   update = merchantDayAmountDao.update(merchantDayAmount1, Wrappers.<MerchantDayAmount>lambdaUpdate().eq(MerchantDayAmount::getMerchantId, merchantDayAmount1.getMerchantId()).eq(MerchantDayAmount::getCreateTime, merchantDayAmount1.getCreateTime()).eq(MerchantDayAmount::getRemark, merchantDayAmount1.getRemark() - 1));
               }
           }else{
               //插入新数据
               merchantDayAmountDao.insert(amountDay);
           }
       }
       //删除增量表数据
        List<Long> collect = orderFinishRecords.stream().map(OrderFinishRecord::getId).collect(Collectors.toList());
       if(collect!=null&&collect.size()>0){
           int delete = orderFinishRecordDao.delete(Wrappers.<OrderFinishRecord>lambdaQuery().in(OrderFinishRecord::getId, collect));
       }
          return 1;
    }
}
