package jrx.anyest.table.service;

import jrx.anyest.table.jpa.enums.HandlerParam;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 描述
 * </p>
 *
 * @author tx
 * @since 2019/5/26 23:40
 */
public interface TableDataHandler {

    /**
     *
     * @param tableName 表名称
     * @param columnName  列名称
     * @param value 列值
     * @return
     */
    String codeInit(String tableName,String columnName,Object value);

    /**
     * 数据处理
     */

    void conversionData(Map data, HandlerParam param);

    /**
     * 数据过滤
     *
     */
    List<Map<String, Object>>  filterData(String tableName,List<Map<String, Object>> data, Map<String,Object> exetraParam);

}
