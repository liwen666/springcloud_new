package com.temp.jpa.jpa.dao;

import com.alibaba.fastjson.JSON;
import com.temp.jpa.ApplicationStart;
import com.temp.jpa.jpa.entity.OrderAccount;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ApplicationStart.class)
@WebAppConfiguration
public class OrderAccountDaoTest {
    @Autowired
    private OrderAccountDao orderAccountDao;

    /**
     * 借款订单
     */
    @Test
    public void newOrder() {
        String idCard="100001";
        String orderNo="000001";


        OrderAccount orderAccount = OrderAccount.builder()
                .account(new BigDecimal(10000))
                .borrowAccount(new BigDecimal(10000))
                .createTime(LocalTime.now())
                .userName("张三")
                .orderNo(orderNo)
                .idCard(idCard).build();

        orderAccountDao.save(orderAccount);

    }

    @Test
    public void name() {
        OrderAccount orderAccount =  orderAccountDao.findByOrderNo("1584764719562");
        System.out.println(JSON.toJSONString(orderAccount));
//        List<OrderAccount> userCount = orderAccountDao.findUserCount("100002");
//        System.out.println(JSON.toJSONString(userCount));
    }
}