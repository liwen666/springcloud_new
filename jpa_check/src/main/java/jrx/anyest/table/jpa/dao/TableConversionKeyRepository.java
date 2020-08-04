package jrx.anyest.table.jpa.dao;

import jrx.anyest.table.jpa.entity.TableConversionKey;
import org.springframework.stereotype.Repository;

/**
 * @author xinre
 * @date 2019/5/25 14:20
 */
@Repository
public interface TableConversionKeyRepository extends BaseDao<TableConversionKey> {

    TableConversionKey findTableConversionKeyByTableCodeNameAndConversionKey(String tableCode,String key);

}
