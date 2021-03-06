package jrx.anyest.table.service.handler;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import jrx.anyest.table.constant.TableConstants;
import jrx.anyest.table.jpa.entity.TableCodeConfig;
import jrx.anyest.table.jpa.entity.TableParamConfig;
import jrx.anyest.table.jpa.enums.HandlerParam;
import jrx.anyest.table.service.TableDataCodeCacheManager;
import jrx.anyest.table.service.TableDataHandler;
import jrx.anyest.table.service.TablePropertiesThreadLocalHolder;
import jrx.anyest.table.utils.TableSqlBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 描述
 * </p>
 *
 * @author lw
 * @since 2019/5/26 23:40
 */
@Slf4j
@Service("defaultTableDataHandler")
public class DefaultTableDataHandler implements TableDataHandler {
    @Override
    public boolean codeInit(String tableName, Map<String, Object> data, Map<String, Object> param) {
        if (tableName.equals("meta_category")) {
            /**
             * 过滤掉不是当前项目的分类
             */
            if ("RULE".equals(data.get("category_type")) || "SCORECARD".equals(data.get("category_type")) || "RULETREE".equals(data.get("category_type"))
                    || "STRATEGY".equals(data.get("category_type")) || "RULESET".equals(data.get("category_type")) || "SCRIPT".equals(data.get("category_type"))
                    || "MATRIX".equals(data.get("category_type"))) {
                /**
                 * 如果时项目外导出不需要project_id,初始化时排除所有项目内的分类数据
                 */
                if (!param.containsKey("projectId")) {
                    return false;
                }
                for (Map.Entry<String, Object> map : param.entrySet()) {
                    Object o = data.get(TableSqlBuilder.toUnderScore(map.getKey()));
                    if (o != null && !o.toString().equals(map.getValue().toString())) {
                        return false;
                    }
                }
            }

        }

        return true;
    }

    @Override
    public String codeProcess(String tableName, String column, Object value) {
        String tableCodeUuid = TablePropertiesThreadLocalHolder.getProperties("table_code_uuid");
        //分类表的二次code转换
        if ("meta_category".equals(tableName) && "parent_id".equals(column) ) {
            return TableDataCodeCacheManager.idToCode.get(tableCodeUuid).get(tableName + TableConstants.CODE_SEPATATION + value);
        }
        //资源管理表的二次code转换
        if ("res_resource_set_item".equals(tableName) && "resource_id".equals(column)) {
            return getInfoCode(value, tableCodeUuid,tableName);
        }
        //数据集的code二次转换
        if ("meta_data_object_info".equals(tableName) && "data_source_id".equals(column)) {
            String code = TableDataCodeCacheManager.idToCode.get(tableCodeUuid).get("meta_data_source_info" + TableConstants.CODE_SEPATATION + value) ;
            return code;
        }

        //分类信息表的code二次转换
        if ("meta_category".equals(tableName) && "parent_id".equals(column)&&!value.toString().equals("0")) {
            String code = TableDataCodeCacheManager.idToCode.get(tableCodeUuid).get("meta_category" + TableConstants.CODE_SEPATATION + value) ;
            return code;
        }

        //字段表的code二次转换
        if ("meta_object_field".equals(tableName) && "resource_object_id".equals(column)) {
            return getInfoCode(value, tableCodeUuid,tableName);
        }

        return value.toString();
    }

    private String getInfoCode(Object value, String tableCodeUuid,String tableName) {
        String code = TableDataCodeCacheManager.idToCode.get(tableCodeUuid).get("meta_model_object_info" + TableConstants.CODE_SEPATATION + value) == null ?
            TableDataCodeCacheManager.idToCode.get(tableCodeUuid).get("meta_topic_object_info" + TableConstants.CODE_SEPATATION + value) == null ?
                    TableDataCodeCacheManager.idToCode.get(tableCodeUuid).get("meta_data_object_info" + TableConstants.CODE_SEPATATION + value) :
                    TableDataCodeCacheManager.idToCode.get(tableCodeUuid).get("meta_topic_object_info" + TableConstants.CODE_SEPATATION + value) :
            TableDataCodeCacheManager.idToCode.get(tableCodeUuid).get("meta_model_object_info" + TableConstants.CODE_SEPATATION + value);
        if(code==null) log.error("code转换异常 找不到对应的对象 primaryTable:{}, relationId{} ",tableName,value);
        return code;
    }

    @Override
    public void conversionData(Map data, HandlerParam param) {

        System.out.println("处理数据-----------" + JSON.toJSONString(data));
    }

    @Override
    public List<Map<String, Object>> filterData(String
                                                        tableName, List<Map<String, Object>> data, Map<String, Map<String,Object>> extraParam) {
        /**
         * 针对项目内的表数据过滤
         * 拿到最新版本数据
         */
//        switch (tableName) {
//            case "res_rule":
//            case "res_rule_set":
//            case "res_rule_tree":
//            case "res_strategy":
//            case "res_matrix":
//            case "res_score_card":
//            case "res_script":
//                return data.stream().filter(e -> {
//                    boolean flag = true;
//                    for (Map.Entry map : extraParam.get(tableName).entrySet()) {
//                        flag = e.get(map.getKey()) == map.getValue();
//                    }
//                    return flag;
//                }).collect(Collectors.toList());
//
//        }
        /**
         * 针对项目外的表数据过滤 只导出最新版本
         */
        switch (tableName) {
            case "res_rule":
            case "res_rule_set":
            case "res_rule_tree":
            case "res_strategy":
            case "res_matrix":
            case "res_score_card":
            case "res_script":

            case "meta_model_object":
            case "meta_data_object":
            case "meta_topic_object":
                TableParamConfig tableParamConfig = TableDataCodeCacheManager.tableParamConfigs.get(tableName);
                List<Map<String, Object>> result = Lists.newArrayList();
                Map<Object, List<Map<String, Object>>> resourceObj = data.stream().collect(Collectors.groupingBy(e -> e.get(tableParamConfig.getResourceIdColumn())));
                resourceObj.forEach((k, v) -> {
                    List<Map<String, Object>> version = v.stream().sorted(Comparator.comparing(e -> (Integer) e.get(tableParamConfig.getVersionColumn()), (x, y) -> {
                        if (x > y) {
                            return -1;
                        }
                        return 1;
                    })).limit(1).collect(Collectors.toList());
                    result.addAll(version);
                });
                return result;

        }
        return data;
    }

    @Override
    public String getCondition(TableCodeConfig tableCodeConfig, Map<String, Object> whereParam) {
        /**
         * 分类信息表特殊处理
         */
        if (whereParam.get("projectId") != null && tableCodeConfig.getTableCodeName().equals("meta_category")) {
            return "  and (project_id=" + whereParam.get("projectId") +" or project_id is null) ";
        } else if (tableCodeConfig.getTableCodeName().equals("meta_category")) {
            return "  and category_type not in('RULE','SCORECARD','RULETREE','STRATEGY','RULESET','SCRIPT','MATRIX') ";
        }

        /**
         *  字段表的特殊处理 排除掉事件策略版本对象的字段
         */
        if("meta_object_field".equals(tableCodeConfig.getTableCodeName())){
            return "  and field_code not like 'NODE_%' ";
        }
        return null;
    }

    @Override
    public boolean enableCode(TableCodeConfig tableCodeConfig, Map<String, Object> whereParam) {
        /**
         * 如果参数中没有code需要的筛选条件就过滤掉code
         */
        if (!StringUtils.isEmpty(tableCodeConfig.getWhereSqlColumns())) {
            Object o = whereParam.get(tableCodeConfig.getWhereSqlColumns()) == null ? whereParam.get(TableSqlBuilder.toLowerCamel(tableCodeConfig.getWhereSqlColumns())) : whereParam.get(tableCodeConfig.getWhereSqlColumns());
            if (null == o) {
                log.info("code 配置缺少必要的列查询条件，,column:{}  tablename:{}", tableCodeConfig.getWhereSqlColumns(), tableCodeConfig.getTableCodeName());
                return false;
            }
        }

        return true;
    }

    @Override
    public String processDataSql(TableCodeConfig tableCodeConfig, String ck) {
        /**
         * 对分类表的特殊处理
         */
        if (tableCodeConfig.getTableCodeName().equals("meta_category")) {
            return ck + " order by parent_id asc";
        }
        return ck;
    }
}
