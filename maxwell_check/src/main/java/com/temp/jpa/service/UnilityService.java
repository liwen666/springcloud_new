package com.temp.jpa.service;

import com.alibaba.fastjson.JSON;
import com.temp.jpa.jpa.dao.OrderAccountDao;
import com.temp.jpa.jpa.dao.OrderHistoryCountDao;
import com.temp.jpa.jpa.dao.OrderRepaymentPlanDao;
import com.temp.jpa.jpa.entity.OrderAccount;
import com.temp.jpa.jpa.entity.OrderHistoryCount;
import com.temp.jpa.jpa.entity.OrderRepaymentPlan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalTime;

/**
 * <p>
 * 描述
 * </p>
 *
 * @author lw
 * @since 2019/5/26 23:40
 */
@Service
public class UnilityService {
    @Autowired
    private OrderAccountDao orderAccountDao;

    @Autowired
    private OrderHistoryCountDao orderHistoryCountDao;

    @Autowired
    private OrderRepaymentPlanDao orderRepaymentPlanDao;

    /**
     * 借款
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void brrowMoney(String userName, String idCard, String orderNo, BigDecimal brrowCount, Integer repayMentNum) {
//        transactionManager.begin();

        OrderAccount userCount = orderAccountDao.findUserCount(idCard).size() > 0 ? orderAccountDao.findUserCount(idCard).get(0) : null;
        System.out.println(JSON.toJSONString(userCount));
        System.out.println("**************************开始借款****************************");
        OrderHistoryCount userHistory = orderHistoryCountDao.findUserHistory(idCard);
        if (null != userCount) {
            orderAccountDao.save(OrderAccount.builder().account(userHistory.getBorrowAmmount().subtract(userHistory.getRepayMentAmmount()).add(brrowCount))
                    .orderNo(orderNo)
                    .idCard(idCard)
                    .borrowAccount(brrowCount)
                    .userName(userName)
                    .createTime(LocalTime.now()).build());
        } else {
            orderAccountDao.save(OrderAccount.builder().account(brrowCount)
                    .orderNo(orderNo)
                    .idCard(idCard)
                    .borrowAccount(brrowCount)
                    .userName(userName)
                    .createTime(LocalTime.now()).build());
        }
        System.out.println("***********************借款金额累计*******************************");
        OrderHistoryCount orderHistoryCount = orderHistoryCountDao.findUserHistory(idCard);
        if (orderHistoryCount != null) {
            orderHistoryCount.setBorrowAmmount(orderHistoryCount.getBorrowAmmount().add(brrowCount));
            orderHistoryCountDao.save(orderHistoryCount);

        } else {
            OrderHistoryCount orderHistoryCount1 = OrderHistoryCount.builder()
                    .borrowAmmount(brrowCount)
                    .repayMentAmmount(BigDecimal.ZERO)
                    .createIime(LocalTime.now())
                    .idCard(idCard)
                    .userName(userName)
                    .build();
            orderHistoryCountDao.save(orderHistoryCount1);
//            transactionManager.commit();;
        }
        System.out.println("*************************创建还款计划*****************************");

        /**
         * 还款计划
         */

        OrderRepaymentPlan build = OrderRepaymentPlan.builder()
                .account(brrowCount.divide(new BigDecimal(repayMentNum)))
                .orderNo(orderNo)
                .createIime(LocalTime.now())
                .repayMentNum(repayMentNum)
                .build();
        orderRepaymentPlanDao.save(build);

    }

    @Transactional
    public void reopay(String orderNo, Integer repayNum) {
        System.out.println("*************************修改还款计划*****************************");


        /**
         * 还款计划
         */
        BigDecimal repmentMoney = null;  //还款金额
        OrderRepaymentPlan plan = orderRepaymentPlanDao.findPlan(orderNo);
        if (!(plan.getRepayMentNum() > 0)) {
            throw new RuntimeException("该订单已还款完毕");

        }
        plan.setRepayMentNum(plan.getRepayMentNum() - repayNum);
        plan.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        repmentMoney = plan.getAccount().multiply(new BigDecimal(repayNum));
        orderRepaymentPlanDao.save(plan);

        /**
         * 修改累计账户
         */
        OrderAccount byOrderNo = orderAccountDao.findByOrderNo(orderNo);
        OrderHistoryCount userHistory = orderHistoryCountDao.findUserHistory(byOrderNo.getIdCard());
        userHistory.setRepayMentAmmount(userHistory.getRepayMentAmmount().add(repmentMoney));
        userHistory.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        orderHistoryCountDao.save(userHistory);

    }
}
