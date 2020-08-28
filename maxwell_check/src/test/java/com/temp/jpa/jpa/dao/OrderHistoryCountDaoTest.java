package com.temp.jpa.jpa.dao;

import com.temp.jpa.ApplicationStart;
import com.temp.jpa.jpa.entity.OrderHistoryCount;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.math.BigDecimal;
import java.time.LocalTime;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TableApplicationStart.class)
@WebAppConfiguration
public class OrderHistoryCountDaoTest {
    @Autowired
    private OrderHistoryCountDao orderHistoryCountDao;

    /**
     * 用户交易累计金额
     */
    @Test
    public void historyAmount() {
        for(int i=0;i<1;i++){
            OrderHistoryCount orderHistoryCount = OrderHistoryCount.builder()
                    .borrowAmmount(new BigDecimal(10000))
                    .repayMentAmmount(BigDecimal.ZERO)
                    .createIime(LocalTime.now())
                    .idCard(IdGenerator.getNext())
                    .userName("张三")
                    .build();

            orderHistoryCountDao.save(orderHistoryCount);
        }

    }
}