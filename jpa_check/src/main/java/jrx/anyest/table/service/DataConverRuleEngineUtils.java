package jrx.anyest.table.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 描述
 * </p>
 *
 * @author lw
 * @since 2019/5/26 23:40
 */

public class DataConverRuleEngineUtils {
    public  static Logger logger = LoggerFactory.getLogger(DataConverRuleEngineUtils.class);

    public static Object getTableProperty(Map<?, ?> map, Object qualifiedKey) {
        if (String.valueOf(qualifiedKey).contains(".")) {
            String json = (String) map.get(String.valueOf(qualifiedKey).substring(0, String.valueOf(qualifiedKey).indexOf(".")));
            return getProperty(JSON.parseObject(json), String.valueOf(qualifiedKey).substring(String.valueOf(qualifiedKey).indexOf(".") + 1));
        }
        return getProperty(map, qualifiedKey);
    }

    public static Object getProperty(Map<?, ?> map, Object qualifiedKey) {
        if (map != null && !map.isEmpty() && qualifiedKey != null) {
            String input = String.valueOf(qualifiedKey);
            if (!"".equals(input)) {
                if (input.contains(".")) {
                    int index = input.indexOf(".");
                    String left = input.substring(0, index);
                    String right = input.substring(index + 1, input.length());
                    if (map.get(left) instanceof Map) {
                        return getProperty((Map<?, ?>) map.get(left), right);
                    } else {
                        JSONArray jsonArray = (JSONArray) map.get(left);
                        if (jsonArray == null) return null;
                        ArrayList<Object> objects = new ArrayList<>();
                        for (Object jsonObject : jsonArray) {
                            objects.add(getProperty((Map<?, ?>) jsonObject, right));
                        }
                        return objects;
                    }

                } else if (map.containsKey(input)) {
                    return map.get(input);
                } else {
                    return null;
                }
            }
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public static void setProperty(Map map, Object qualifiedKey, Object value) {
        if (map != null && qualifiedKey != null) {
            String input = String.valueOf(qualifiedKey);
            if (!input.equals("")) {
                if (input.contains(".")) {
                    int index = input.indexOf(".");
                    String left = input.substring(0, index);
                    String right = input.substring(index + 1, input.length());
                    if (map.get(left) == null) {
                        map.put(left, new HashMap<>());
                    } else {

                    }
                    setProperty((Map<?, ?>) map.get(left), right, value);
                } else {
                    ((Map<Object, Object>) map).put(qualifiedKey, value);
                }
            }
        }
    }

    public static void setProperty(Map map, Object qualifiedKey, Object value, Map<String, String> keyVaule, boolean replacePre) {
        Object property = getProperty(map, qualifiedKey);
        if(property==null){
            return;
        }
        if (property instanceof List) {
            JSONArray jsonArray = (JSONArray) (((List) getProperty(map, String.valueOf(qualifiedKey).substring(0, String.valueOf(qualifiedKey).lastIndexOf(".")))).get(0));
            for (Object jsonObject : jsonArray) {
                String subCode = String.valueOf(qualifiedKey).substring(String.valueOf(qualifiedKey).lastIndexOf(".") + 1);
                Object property1 = getProperty((Map<?, ?>) jsonObject, subCode);
                if (null != property1) {
                    if (replacePre) {
                        value = keyVaule.get(String.valueOf(property1).replaceAll("[a-z]", ""));
                    } else {
                        value = keyVaule.get(property1);
                    }
                    setProperty((Map) jsonObject, subCode, value);
                }
            }
        } else {
            String s = String.valueOf(property);
            if (null != keyVaule) {
                value = new StringBuffer("");
                String[] split = s.split(",");
                for (String s1 : split) {
                    String s2 = null;
                    if (replacePre) {
                        s2= keyVaule.get(s1.replaceAll("[a-z]", ""));
                    } else {
                       s2= keyVaule.get(s1);
                    }
                    if (null != s2) {
                        ((StringBuffer) value).append(s2 + ",");
                    }else{
                        logger.error("数据转换未查询到对应key的值,key:{}",s1);
                    }
                }
            }
            setProperty(map, qualifiedKey, value.toString().substring(0, value.toString().length() - 1));
        }

    }

    public static void setPropertyTable(Map map, Object qualifiedKey, Object value, Map<String, String> keyVaule, boolean replacePre) {
        if (String.valueOf(qualifiedKey).contains(".")) {
            String left = String.valueOf(qualifiedKey).substring(0, String.valueOf(qualifiedKey).indexOf("."));
            String right = String.valueOf(qualifiedKey).substring(String.valueOf(qualifiedKey).indexOf(".") + 1);
            String json = (String) map.get(left);
            if(json==null){
                return;
            }
            JSONObject jsonObject = JSON.parseObject(json);
            setProperty(jsonObject, right, value, keyVaule, replacePre);
            map.put(left, JSONObject.toJSONString(jsonObject));
            return;
        }
        setProperty(map, qualifiedKey, value, keyVaule, replacePre);
    }
}
