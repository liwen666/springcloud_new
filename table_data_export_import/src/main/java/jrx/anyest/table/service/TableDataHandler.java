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
     * code初始化处理
     */

    String codeInit(Object object,String tableName);

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
