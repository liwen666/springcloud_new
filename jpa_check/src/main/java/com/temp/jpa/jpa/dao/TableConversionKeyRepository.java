package com.temp.jpa.jpa.dao;

import com.temp.jpa.jpa.entity.TableCodeConfig;
import com.temp.jpa.jpa.entity.TableConversionKey;
import org.springframework.stereotype.Repository;

/**
 * @author xinre
 * @date 2019/5/25 14:20
 */
@Repository
public interface TableConversionKeyRepository extends BaseDao<TableConversionKey> {

    TableConversionKey findTableConversionKeyByTableCodeNameAndConversionKey(String tableCode,String key);

}
