package jrx.anyest.table.listener;

import jrx.anyest.table.jpa.dto.DataCheckResult;
import jrx.anyest.table.jpa.entity.TableConversionKey;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 描述
 * </p>
 *
 * @author lw
 * @since 2019/5/26 23:40
 */
public interface ITableExportListener {
    /**
     * 数据导出前置处理
     * @param tableName  表名称
     * @param data  数据
     * @param idToCode  id到code的转换
     * @param jdbcTemplate  需要用到查询数据库的时候使用
     */
    int before(String tableName,Map<String, Map<String, Object>> data, Map<String, String> idToCode, JdbcTemplate jdbcTemplate, Map<String, List<TableConversionKey>> tableConversionKeys);


    /**
     * 监听器执行排序  数值小的优先执行
     * @return
     */
    int order();

    void after(String k, Map<String, Map<String, Object>> data, JdbcTemplate jdbcTemplate);

    String getDataSql(String tableName,String querySql);
}
