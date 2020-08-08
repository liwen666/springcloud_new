package jrx.anyest.table.service;

import jrx.anyest.table.jpa.entity.TableCodeConfig;
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
     * code 需要要做二次转换时用到
     * @param tableName
     * @param column
     * @param value
     * @return
     */
    String codeProcess(String tableName,String column,Object value);

    /**
     * 数据处理
     */

    void conversionData(Map data, HandlerParam param);

    /**
     * 数据过滤
     *
     */
    List<Map<String, Object>>  filterData(String tableName,List<Map<String, Object>> data, Map<String,Object> exetraParam);

    /**
     * 查询生成表数据的条件
     * @param tableCodeConfig
     * @param whereParam
     * @return
     */
    String getCondition(TableCodeConfig tableCodeConfig, Map<String, Object> whereParam);

    /**
     * 是否允许表数据生成code
     * @param tableCodeConfig
     * @param whereParam
     * @return
     */
    boolean enableCode(TableCodeConfig tableCodeConfig, Map<String, Object> whereParam);

    /**
     * 对code数据查询条件的最终处理
     * @param tableCodeConfig
     * @param ck
     * @return
     */
    String processDataSql(TableCodeConfig tableCodeConfig, String ck);
}
