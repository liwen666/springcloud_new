package jrx.anyest.table.jpa.dao;

import jrx.anyest.table.jpa.entity.TableCodeConfig;
import jrx.anyest.table.jpa.entity.TableMarkInit;
import org.springframework.stereotype.Repository;

/**
 * @author xinre
 * @date 2019/5/25 14:20
 */
@Repository
public interface TableMarkInitRepository extends BaseDao<TableMarkInit> {

    TableMarkInit findByTableName(String tableName);

}
