package com.temp.jpa.jpa.dao;

import com.temp.jpa.ApplicationStart;
import com.temp.jpa.jpa.entity.OrderRepaymentPlan;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.Async;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.math.BigDecimal;
import java.time.LocalTime;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TableApplicationStart.class)
@WebAppConfiguration
public class OrderRepaymentPlanDaoTest {
    @Autowired
    private OrderRepaymentPlanDao orderRepaymentPlanDao;


    @Test
    public void repayPlan() {
        OrderRepaymentPlan build = OrderRepaymentPlan.builder()
                .account(new BigDecimal(2000))
                .createIime(LocalTime.now())
                .orderNo("000001")
                .repayMentNum(5)
                .build();
        orderRepaymentPlanDao.save(build);
    }


}