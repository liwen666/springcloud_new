package jrx.anyest.table.jpa.dao;

import jrx.anyest.table.jpa.entity.TableCodeRelation;
import jrx.anyest.table.jpa.entity.TableImportSort;
import org.springframework.stereotype.Repository;

/**
 * @author xinre
 * @date 2019/5/25 14:20
 */
@Repository
public interface TableImportSortRepository extends BaseDao<TableImportSort> {

    TableImportSort findByTableCodeName(String tableName);

}
