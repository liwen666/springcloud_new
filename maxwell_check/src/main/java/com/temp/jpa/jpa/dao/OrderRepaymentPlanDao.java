package com.temp.jpa.jpa.dao;

import com.temp.jpa.jpa.entity.OrderAccount;
import com.temp.jpa.jpa.entity.OrderRepaymentPlan;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xinre
 * @date 2019/5/25 14:20
 */
@Repository
public interface OrderRepaymentPlanDao extends BaseDao<OrderRepaymentPlan> {
    @Query(value = "select t from OrderRepaymentPlan t where t.orderNo = ?1  ")
    OrderRepaymentPlan findPlan(String orderNo);

}
