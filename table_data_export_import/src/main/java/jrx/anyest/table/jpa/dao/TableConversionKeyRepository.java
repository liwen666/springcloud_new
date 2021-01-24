package jrx.anyest.table.jpa.dao;

import jrx.anyest.table.jpa.entity.TableConversionKey;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xinre
 * @date 2019/5/25 14:20
 */
@Repository
public interface TableConversionKeyRepository extends BaseDao<TableConversionKey> {

    TableConversionKey findTableConversionKeyByTableCodeNameAndConversionKey(String tableCode, String key);

//    @Query(value = "select conversionKey,tableCodeName from TableConversionKey where conversionKey like '%|%' and tableCodeName='res_strategy_node'")
    @Query(value = "select * from table_conversion_key where conversion_key like '%|%' and table_code_name='res_strategy_node'", nativeQuery = true)
    List<TableConversionKey> findConverRelation();
}
