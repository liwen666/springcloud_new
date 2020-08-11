package jrx.anyest.table.jpa.dao;

import jrx.anyest.table.jpa.entity.TableCodeConfig;
import jrx.anyest.table.jpa.entity.TableParamConfig;
import org.springframework.stereotype.Repository;

/**
 * @author xinre
 * @date 2019/5/25 14:20
 */
@Repository
public interface TableParamConfigRepository extends BaseDao<TableParamConfig> {


    TableParamConfig findByTableCodeName(String tableName);
}
