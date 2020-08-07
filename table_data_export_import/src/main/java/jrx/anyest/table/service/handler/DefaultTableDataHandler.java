package jrx.anyest.table.service.handler;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import jrx.anyest.table.constant.TableConstants;
import jrx.anyest.table.jpa.enums.HandlerParam;
import jrx.anyest.table.service.TableDataCodeCacheManager;
import jrx.anyest.table.service.TableDataHandler;
import jrx.anyest.table.service.TablePropertiesThreadLocalHolder;
import jrx.anyest.table.utils.TableSqlBulider;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.codehaus.groovy.runtime.DefaultGroovyMethods.collect;
import static org.codehaus.groovy.runtime.DefaultGroovyMethods.retainAll;

/**
 * <p>
 * 描述
 * </p>
 *
 * @author lw
 * @since 2019/5/26 23:40
 */
@Service("defaultTableDataHandler")
public class DefaultTableDataHandler implements TableDataHandler {
    @Override
    public boolean codeInit(String tableName, Map<String, Object> data, Map<String, Object> param) {
        if (tableName.equals("res_resource_set_item")) {

        }
        if (tableName.equals("meta_category")) {
            /**
             * 过滤掉不是当前项目的分类
             */
            if ("RULE".equals(data.get("category_type"))||"SCORECARD".equals(data.get("category_type"))||"RULETREE".equals(data.get("category_type"))
            ||"STRATEGY".equals(data.get("category_type"))||"RULESET".equals(data.get("category_type"))||"SCRIPT".equals(data.get("category_type"))
                    ||"MATRIX".equals(data.get("category_type"))) {
                /**
                 * 如果时项目外导出不需要project_id,初始化时排除所有项目内的分类数据
                 */
                if(!param.containsKey("projectId")){
                   return false;
                }
                for(Map.Entry<String,Object> map:param.entrySet()){
                    Object o = data.get(TableSqlBulider.toUnderScore(map.getKey()));
                    if(o!=null&&!o.toString().equals(map.getValue().toString())){
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
        if("meta_category".equals(tableName)&&"parent_id".equals(column)&&(Integer) value!=0){
           return  TableDataCodeCacheManager.idToCode.get(tableCodeUuid).get(tableName+ TableConstants.CODE_SEPATATION+value);
        }

        return value.toString();
    }

    @Override
    public void conversionData(Map data, HandlerParam param) {

        System.out.println("处理数据-----------" + JSON.toJSONString(data));
    }

    @Override
    public List<Map<String, Object>> filterData(String
                                                        tableName, List<Map<String, Object>> data, Map<String, Object> exetraParam) {
        /**
         * 针对项目内的表数据过滤
         */
        switch (tableName) {
            case "res_rule":
            case "res_rule_set":
            case "res_rule_tree":
            case "res_strategy":
            case "res_matrix":
            case "res_score_card":
            case "res_script":
                return data.stream().filter(e -> {
                    boolean flag = true;
                    for (Map.Entry map : exetraParam.entrySet()) {
                        flag = e.get(map.getKey()) == map.getValue();
                    }
                    return flag;
                }).collect(Collectors.toList());

        }
        /**
         * 针对项目外的表数据过滤 只导出最新版本
         */
        switch (tableName) {
            case "meta_model_object":
            case "meta_data_object":
            case "meta_topic_object":
                List<Map<String, Object>> result = Lists.newArrayList();
                Map<Object, List<Map<String, Object>>> resourceObj = data.stream().collect(Collectors.groupingBy(e -> e.get("resource_id")));
                resourceObj.forEach((k, v) -> {
                    List<Map<String, Object>> version = v.stream().sorted(Comparator.comparing(e -> (Integer) e.get("version"), (x, y) -> {
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
}
