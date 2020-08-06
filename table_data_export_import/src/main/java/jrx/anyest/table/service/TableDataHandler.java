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
     * @param data 数据对象
     * @param param 数据的过滤条件
     * @return
     */
    boolean codeInit(String tableName,Map<String ,Object> data,Map <String ,Object> param);

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
