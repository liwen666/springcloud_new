package jrx.anyest.table.listener;

import jrx.anyest.table.jpa.dto.DataCheckResult;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Map;

/**
 * <p>
 * 描述
 * </p>
 *
 * @author lw
 * @since 2019/5/26 23:40
 */
public interface ITableImportListener {
    /**
     * 数据入库前置数据处理
     * 主要做code的转换处理
     * @param tableName
     * @param data
     * @param dataCheckResult
     * @param jdbcTemplate
     */
    void before(String tableName, Map<String, Object> data, DataCheckResult dataCheckResult, JdbcTemplate jdbcTemplate);

    /**
     * 数据入库后的数据处理
     * 比如恢复部分字段的对象版本ID
     * 将策略的衍生变量插入到字段表并恢复id
     * @param tableName
     * @param data
     */
    void after(String tableName, Map<String ,Object> data);

    /**
     * 监听器执行排序  数值小的优先执行
     * @return
     */
    int order();
}
