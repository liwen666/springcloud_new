package com.temp.jpa.jpa.dao;

import com.temp.jpa.jpa.entity.OrderAccount;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xinre
 * @date 2019/5/25 14:20
 */
@Repository
public interface OrderAccountDao extends BaseDao<OrderAccount> {
    @Query(value = "select t from OrderAccount t where t.idCard = ?1 ORDER BY t.createTime DESC ")
    List<OrderAccount > findUserCount(String idCard);

    OrderAccount findByOrderNo(String s);
}
