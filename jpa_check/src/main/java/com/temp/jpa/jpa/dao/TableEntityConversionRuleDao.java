package com.temp.jpa.jpa.dao;

import com.temp.jpa.jpa.entity.OrderHistoryCount;
import com.temp.jpa.jpa.entity.TableEntityConversionRule;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xinre
 * @date 2019/5/25 14:20
 */
@Repository
public interface TableEntityConversionRuleDao extends BaseDao<TableEntityConversionRule> {
    TableEntityConversionRule findById(String id);

    List<TableEntityConversionRule> findByTableCode(String tableCode);

    @Query(value = "select t from TableEntityConversionRule t ")
    List<TableEntityConversionRule>  findAllRule();
    TableEntityConversionRule findByEntityKeyAndTableCode(String entiryKey,String tableCode);

}
