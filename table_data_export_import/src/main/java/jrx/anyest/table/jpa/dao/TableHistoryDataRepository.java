package jrx.anyest.table.jpa.dao;

import jrx.anyest.table.jpa.entity.TableHistoryData;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xinre
 * @date 2019/5/25 14:20
 */
@Repository
public interface TableHistoryDataRepository extends BaseDao<TableHistoryData> {


    List<TableHistoryData> findByDataKey(String dataKey);
}
