package jrx.anyest.table.listener;

import jrx.anyest.table.jpa.dto.DataCheckResult;

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
     */
    void before(String tableName, Map<String, Object> data, DataCheckResult dataCheckResult);

    /**
     * 数据入库后的数据处理
     * @param tableName
     * @param data
     */
    void after(String tableName, Map<String ,Object> data);
}
