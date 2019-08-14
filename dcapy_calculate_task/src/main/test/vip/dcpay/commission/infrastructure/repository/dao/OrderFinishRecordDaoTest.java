package vip.dcpay.commission.infrastructure.repository.dao;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import vip.dcpay.commission.RunApplication;
import vip.dcpay.commission.domain.constant.CaculateContant;
import vip.dcpay.commission.infrastructure.repository.model.MerchantDayAmount;
import vip.dcpay.commission.infrastructure.repository.model.OrderFinishRecord;
import vip.dcpay.dto.mq.MessageBasic;
import vip.dcpay.dto.mq.OrderFinishMsgDto;
import vip.dcpay.util.date.DateUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RunApplication.class)
public class OrderFinishRecordDaoTest {
    @Autowired
    private OrderFinishRecordDao orderFinishRecordDao;


    @Test
    public void limit() {
        List<OrderFinishRecord> orderFinishRecords = orderFinishRecordDao.selectList(Wrappers.<OrderFinishRecord>lambdaQuery().eq(OrderFinishRecord::getOrderStatus,100).apply(" 1=1 limit "+ CaculateContant.ORDER_FINISH_CAULATE));
        System.out.println(orderFinishRecords.size());
        System.out.println("=========================================================================================");
    }

    @Test
    public void insert() {
//        OrderFinishRecord build = OrderFinishRecord.builder().merchantId(10l).orderNo("adfdsa").recordId(10000l).income(new BigDecimal(100)).customerId(1002l).createTime(new Date()).simpleTime(DateUtils.parse("2019-08-14 16:32:25")).build();

        for(int i = 0;i<10;i++){
            OrderFinishRecord build = OrderFinishRecord.builder().createTime(new Date()).customerId((long) i).income(new BigDecimal(i))
                    .orderNo(i+"").orderStatus(100).orderType(1).transactionAmount(new BigDecimal(100)).payWay("wexin").recordId((long) i)
                    .merchantId((long) i).simpleTime(new Date()).build();
//            MessageBasic build1 = MessageBasic.builder().body(JSON.toJSONString(build)).build();
            orderFinishRecordDao.insert(build);
//            channel.basicPublish(EXCHANGE_NAME, toOrderFinish_KEY, null,JSON.toJSONBytes(build1) );
        }

    }

    @Test
    public void delete() {

        orderFinishRecordDao.delete(Wrappers.<OrderFinishRecord>lambdaQuery().in(OrderFinishRecord::getId,new ArrayList(){{add(45);add(46);}}));
    }
}