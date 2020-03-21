package com.temp.jpa.jpa.dao;

import com.temp.jpa.jpa.entity.OrderHistoryCount;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author xinre
 * @date 2019/5/25 14:20
 */
@Repository
public interface OrderHistoryCountDao extends BaseDao<OrderHistoryCount> {
    @Query(value = "select t from OrderHistoryCount t where t.idCard = ?1")
    OrderHistoryCount findUserHistory(String idCard);
}
