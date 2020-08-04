package jrx.anyest.table.service.handler;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import jrx.anyest.table.jpa.enums.HandlerParam;
import jrx.anyest.table.service.TableDataHandler;
import org.springframework.stereotype.Service;

import java.util.Iterator;
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
@Service("defaultTableDataHandler")
public class DefaultTableDataHandler implements TableDataHandler {
    @Override
    public String codeInit(Object object,String tableName) {

        return object.toString();
    }

    @Override
    public void conversionData(Map data, HandlerParam param) {

        System.out.println("处理数据-----------" + JSON.toJSONString(data));
    }

    @Override
    public List<Map<String, Object>> filterData(String tableName, List<Map<String, Object>> data, Map<String, Object> exetraParam) {
        /**
         * 版本过滤只针对项目内的表
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
        return data;
    }
}
