package vip.dcpay.commission.application.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import vip.dcpay.business.order.application.service.OrderSearchService;
import vip.dcpay.business.order.dto.OrderRecordDto;
import vip.dcpay.commission.RunApplication;
import vip.dcpay.enums.order.OrderTypeEnum;
import vip.dcpay.merchant.application.dto.RecursiveAgentDto;
import vip.dcpay.merchant.application.service.merchant.MerchantAgentService;
import vip.dcpay.vo.basic.Result;

import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RunApplication.class)
public class MerchantCommissionServiceImplTest {
    @Reference
    private OrderSearchService orderSearchService;
    @Autowired
    private MerchantCaculateServiceImpl merchantCommissionService;
    @Reference
    private MerchantAgentService merchantAgentService;

    @Test
    public void getOrder() {
        Result<List<OrderRecordDto>> orderAmountInOneDay = orderSearchService.statMerchantOrderAmountInOneDay(new Date(), OrderTypeEnum.getEnum(1));
        System.out.println(JSON.toJSONString(orderAmountInOneDay));
        Result<List<OrderRecordDto>> orderAmountInOneDay1 = orderSearchService.statSomeMerchantOrderAmountInOneDay(new HashSet<Long>() {{
        }}, new Date(), OrderTypeEnum.values());
        System.out.println(JSON.toJSONString(orderAmountInOneDay1));

    }


    @Test
    public void listAgent() {
        List<RecursiveAgentDto> chirdenAgentMap = merchantAgentService.findChirdenAgentMap(Collections.singletonList(1l));
        System.out.println("=========================================================================================");
        List<Long> collect = chirdenAgentMap.get(0).getAgentList().stream().map(e -> e.getMerchantId()).collect(Collectors.toList());
        System.out.println(JSON.toJSONString(collect));

    }

    @Test
    public void testDayAmount() {

    }

}