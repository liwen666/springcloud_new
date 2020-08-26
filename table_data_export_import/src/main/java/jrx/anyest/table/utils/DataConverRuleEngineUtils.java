package jrx.anyest.table.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import javax.persistence.Table;
import java.lang.annotation.Annotation;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>
 * 描述
 * </p>
 *
 * @author lw
 * @since 2020/5/29 16:20
 */


public class DataConverRuleEngineUtils {
    public static Logger logger = LoggerFactory.getLogger(DataConverRuleEngineUtils.class);

    public static Object getTableProperty(Map<?, ?> map, Object qualifiedKey) {
        if (String.valueOf(qualifiedKey).contains(".")) {
            String json = (String) map.get(String.valueOf(qualifiedKey).substring(0, String.valueOf(qualifiedKey).indexOf(".")));
            if (json != null && json.startsWith("[")) {
                List<Object> objects = new ArrayList<>();
                JSONArray jsonArray = JSON.parseArray(json);
                jsonArray.forEach(e -> {
                    Object property = getProperty((Map<?, ?>) e, String.valueOf(qualifiedKey).substring(String.valueOf(qualifiedKey).indexOf(".") + 1));
                    if (null == property) {
                        return;
                    }
                    if (property instanceof List) {
                        objects.addAll((Collection<?>) property);
                    } else {
                        objects.add(property);
                    }
                });
                if (CollectionUtils.isEmpty(objects)) {
                    return null;
                }
                return objects;
            }
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
                            Object property = getProperty((Map<?, ?>) jsonObject, right);
                            if (null != property) {
                                objects.add(property);
                            }
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

    public static void setProperty(Map map, Object qualifiedKey, Object value, Map<String, String> keyVaule, String replacePre) {
        Object property = getProperty(map, qualifiedKey);
        if (property == null) {
            return;
        }
        if (property instanceof String && StringUtils.isEmpty((String) property)) {
            return;
        }
        if (property instanceof List) {
            setArrayValue(map, qualifiedKey, keyVaule, replacePre);
        } else {
            boolean listFlag = false;
            if (String.valueOf(property).contains("]")) {
                listFlag = true;
            }
            String s = String.valueOf(property).replaceAll("\\[", "").replaceAll("]", "");
            if (null != keyVaule) {
                value = new StringBuffer();
                String[] split = s.split(",");
                for (String s1 : split) {
                    String s2;
                    if (!StringUtils.isEmpty(replacePre)) {
                        s2 = keyVaule.get(replacePre + s1);
                    } else {
                        s2 = keyVaule.get(s1);
                    }
                    if (null != s2) {
                        ((StringBuffer) value).append(s2 + ",");
                    } else {
                        throw new RuntimeException("数据转换未查询到对应key的值 key:" + s1);
                    }
                }
                value = value.toString().substring(0, value.toString().length() - 1);
            }
            if (listFlag) {
                setProperty(map, qualifiedKey, "[" + value + "]");

            } else {
                setProperty(map, qualifiedKey, value);
            }
        }
    }

    private static void setArrayValue(Map map, Object qualifiedKey, Map<String, String> keyVaule, String replacePre) {
        Object value;
        JSONArray jsonArray = new JSONArray();
        Object property2 = getProperty(map, String.valueOf(qualifiedKey).substring(0, String.valueOf(qualifiedKey).lastIndexOf(".")));
        if (property2 instanceof ArrayList) {
            List list = (List) property2;
            if(!CollectionUtils.isEmpty(list)&&list.get(0) instanceof JSONObject){
                for(Object o:list){
                    jsonArray.add(o);
                }
            }else {
                setArrayValue(map, String.valueOf(qualifiedKey).substring(0, String.valueOf(qualifiedKey).lastIndexOf(".")), keyVaule, replacePre);
            }
        } else {
            jsonArray = (JSONArray) property2;
        }
        for (Object jsonObject : jsonArray) {
            String subCode = String.valueOf(qualifiedKey).substring(String.valueOf(qualifiedKey).lastIndexOf(".") + 1);
            Object property1 = getProperty((Map<?, ?>) jsonObject, subCode);
            if (null != property1) {
                if (!StringUtils.isEmpty(replacePre)) {
                    value = keyVaule.get(replacePre + property1);
                } else {
                    value = keyVaule.get(property1);
                }
                if (null ==value) {
                    /**
                     * 支持相同的转换key不同的值
                     */
                    continue;
                }
                setProperty((Map) jsonObject, subCode, value);
            }
        }
    }

    public static void setPropertyTable(Map map, Object qualifiedKey, Object value, Map<String, String> keyVaule, String replacePre) {
        if (String.valueOf(qualifiedKey).contains(".")) {
            String left = String.valueOf(qualifiedKey).substring(0, String.valueOf(qualifiedKey).indexOf("."));
            String right = String.valueOf(qualifiedKey).substring(String.valueOf(qualifiedKey).indexOf(".") + 1);
            String json = (String) map.get(left);
            if (StringUtils.isEmpty(json)) {
                return;
            }
            if (json.startsWith("[")) {
                JSONArray jsonArray = JSON.parseArray(json);
                jsonArray.forEach(e -> {
                    setProperty((JSONObject) e, right, value, keyVaule, replacePre);
                });
                map.put(left, JSONObject.toJSONString(jsonArray));
                return;
            } else {
                JSONObject jsonObject = JSON.parseObject(json);
                setProperty(jsonObject, right, value, keyVaule, replacePre);
                map.put(left, JSONObject.toJSONString(jsonObject));
                return;
            }
        }
        setProperty(map, qualifiedKey, value, keyVaule, replacePre);
    }


    public static String getTableName(Object baseEntity) {
        Class<?> aClass = baseEntity.getClass();
        Annotation[] annotations = aClass.getAnnotations();
        for (Annotation annotation : annotations) {
            if (annotation.annotationType().getName().equals("javax.persistence.Table")) {
                String name = ((Table) annotation).name();
                return name;
            }
        }
        return null;
    }

    /**
     * 获取表名称
     */
    public static String getValue(String name, int index) {
        Pattern compile = Pattern.compile("([A-Za-z0-9_]+)@([A-Za-z0-9_-]+)");
        Matcher matcher = compile.matcher(name);
        boolean matches = matcher.matches();
        Assert.state(matches, "key不符合规则" + name);
        return matcher.group(index);

    }

}
