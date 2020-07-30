package jrx.anyest.table.jpa.dao;

import jrx.anyest.table.jpa.entity.TableCodeConfig;
import org.springframework.stereotype.Repository;

/**
 * @author xinre
 * @date 2019/5/25 14:20
 */
@Repository
public interface TableCodeConfigRepository extends BaseDao<TableCodeConfig> {


    TableCodeConfig findTableCodeConfigByTableCodeName(String codeName);
}
