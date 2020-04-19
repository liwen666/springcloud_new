package com.temp.jpa.jpa.dao;

import com.alibaba.fastjson.JSON;
import com.temp.jpa.ApplicationStart;
import com.temp.jpa.jpa.entity.OrderAccount;
import com.temp.jpa.jpa.entity.OrderHistoryCount;
import com.temp.jpa.jpa.entity.OrderRepaymentPlan;
import com.temp.jpa.service.UnilityService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.transaction.*;
import java.math.BigDecimal;
import java.time.LocalTime;

/**
 * <p>
 * 描述
 * </p>
 *
 * @author lw
 * @since 2019/5/26 23:40
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ApplicationStart.class)
@WebAppConfiguration

public class UnilityTest<TransactionConfiguration> {
    @Autowired
    private OrderAccountDao orderAccountDao;

    @Autowired
    private OrderHistoryCountDao orderHistoryCountDao;

    @Autowired
    private OrderRepaymentPlanDao orderRepaymentPlanDao;

    @Autowired
    UnilityService unilityService;
    /**
     * 借款
     */
    @Test
    public void brrowMoney() throws SystemException, NotSupportedException, HeuristicRollbackException, HeuristicMixedException, RollbackException {

    }

    @Test
    public void name() {
        for(int i=0;i<10;i++){
            String userName = "李四"+i;
            String idCard = "100002"+i;
            String orderNo = System.currentTimeMillis()+"";
            System.out.println(orderNo);
            BigDecimal brrowCount = new BigDecimal(10000);
            Integer repayMentNum = 5;
            unilityService.brrowMoney(userName,idCard,orderNo,brrowCount,repayMentNum);
        }

    }

    /**
     * 借款
     */
    @Test
    public void brrow() throws InterruptedException {
        String userName = "张三";
        String idCard = "100001";
        String orderNo = System.currentTimeMillis()+"";
        System.out.println(orderNo);
        BigDecimal brrowCount = new BigDecimal(10000);
        Integer repayMentNum = 5;
        for(int j=0;j<5;j++){
            new Thread(() -> {
                for(int i = 0;i<1000000;i++){
                    unilityService.brrowMoney(userName+i,IdGenerator.getNext(),orderNo,brrowCount,repayMentNum);
                }
            }).start();
        }

   Thread.sleep(20000000);

    }



    /**
     * 借款
     */
    @Test
    public void simple() throws InterruptedException {
        String userName = "张三";
        String idCard = "100001";
        String orderNo = System.currentTimeMillis()+"";
        System.out.println(orderNo);
        BigDecimal brrowCount = new BigDecimal(10000);
        Integer repayMentNum = 5;
        for(int j=0;j<5;j++){
            new Thread(() -> {
                for(int i = 0;i<1000000;i++){
                    unilityService.brrowSimple(userName+i,IdGenerator.getNext(),orderNo,brrowCount,repayMentNum);
                }
            }).start();
        }

        Thread.sleep(20000000);

    }
    /**
     * 还款
     */
    @Test
    public void reopay() {
//        String orderNo = "1584778236736";  //订单号
        String orderNo = "1584784128894";  //订单号
        Integer repayNum = 1;  //返还期数
        unilityService.reopay(orderNo,repayNum);
    }
}
