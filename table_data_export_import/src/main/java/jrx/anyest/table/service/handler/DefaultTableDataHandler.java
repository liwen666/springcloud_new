package jrx.anyest.table.service.handler;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import jrx.anyest.table.jpa.enums.HandlerParam;
import jrx.anyest.table.service.TableDataHandler;
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
    public String codeInit(String tableName, String columnName, Object value) {
        if (tableName.equals("res_resource_set_item")) {

        }

        return value.toString();
    }

    @Override
    public void conversionData(Map data, HandlerParam param) {

        System.out.println("处理数据-----------" + JSON.toJSONString(data));
    }

    @Override
    public List<Map<String, Object>> filterData(String tableName, List<Map<String, Object>> data, Map<String, Object> exetraParam) {
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
                resourceObj.forEach((k,v)->{
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
