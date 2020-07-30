package jrx.anyest.table.jpa.dao;

import jrx.anyest.table.jpa.entity.TableCodeRelation;
import org.springframework.stereotype.Repository;

/**
 * @author xinre
 * @date 2019/5/25 14:20
 */
@Repository
public interface TableCodeRelationRepository  extends BaseDao<TableCodeRelation> {

    TableCodeRelation findByPrimaryCodeKeyAndPrimaryTableNameAndSlaveTableName(String priKey,String priTable,String slaveTable);


}
