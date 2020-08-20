package jrx.anyest.table.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
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
            if(json.startsWith("[")){
                List<Object> objects = new ArrayList<>();
                JSONArray jsonArray = JSON.parseArray(json);
                jsonArray.forEach(e->{
                    Object property = getProperty((Map<?, ?>) e, String.valueOf(qualifiedKey).substring(String.valueOf(qualifiedKey).indexOf(".") + 1));
                    if (null==property){return;}
                    if(property instanceof  List){
                        objects.addAll((Collection<?>) property);
                    }else{
                        objects.add(property);
                    }
                });
                if(CollectionUtils.isEmpty(objects)){
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
            JSONArray jsonArray;
            Object property2 = getProperty(map, String.valueOf(qualifiedKey).substring(0, String.valueOf(qualifiedKey).lastIndexOf(".")));
            if (property2 instanceof ArrayList) {
                jsonArray = (JSONArray) (((List) property2).get(0));
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
                    setProperty((Map) jsonObject, subCode, value);
                }
            }
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
                    setProperty((JSONObject)e, right, value, keyVaule, replacePre);
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

    public static void main(String[] args) {
        String data = "{\"node_id\":16433,\"content_code\":\"3\",\"create_time\":1597825951000,\"update_person\":\"xhp33\",\"update_time\":1597825951000,\"execute_mode\":\"ALWAYS\",\"height\":50,\"icon\":\"calc\",\"name\":\"衍生变量\",\"node_content\":\"[{\\\"bid\\\":\\\"f12176\\\",\\\"columnCode\\\":\\\"NODE_test_model\\\",\\\"description\\\":\\\"\\\",\\\"expressionUnits\\\":[{\\\"type\\\":\\\"input\\\",\\\"value\\\":\\\"4\\\"},{\\\"term\\\":\\\"+\\\",\\\"type\\\":\\\"term\\\"},{\\\"type\\\":\\\"input\\\",\\\"value\\\":\\\"3\\\"}],\\\"fieldCode\\\":\\\"NODE_test_model\\\",\\\"fieldId\\\":12176,\\\"fieldName\\\":\\\"test_模型字段\\\",\\\"fieldState\\\":\\\"ACTIVE\\\",\\\"fieldType\\\":\\\"FORMULA_FIELD\\\",\\\"functionParams\\\":{},\\\"functions\\\":{},\\\"import\\\":false,\\\"isKey\\\":false,\\\"objectType\\\":\\\"NODE_FEATURE\\\",\\\"position\\\":0,\\\"priority\\\":0,\\\"recorded\\\":false,\\\"referFieldBids\\\":\\\"\\\",\\\"referFunctionCodes\\\":\\\"\\\",\\\"relColumnCode\\\":\\\"NODE_test_model\\\",\\\"resourceObjectId\\\":11155,\\\"resourceObjectVersionId\\\":11156,\\\"valueType\\\":\\\"INTEGER\\\"}]\",\"node_key\":\"12190_20395\",\"node_type\":\"DERIVE_FIELD\",\"strategy_id\":16431,\"text\":\"衍生变量\",\"width\":150,\"x\":75,\"y\":125}";

        String congerKey = "node_content.ruleItemList.category.categoryId";
        JSONObject jsonObject = JSON.parseObject(data);
        setPropertyTable(jsonObject, congerKey, 1, null, null);

    }
}

class test {
    public static void main(String[] args) {
        String value ="{\"node_id\":16433,\"content_code\":\"3\",\"create_time\":1597825951000,\"update_person\":\"xhp33\",\"update_time\":1597825951000,\"execute_mode\":\"ALWAYS\",\"height\":50,\"icon\":\"calc\",\"name\":\"衍生变量\",\"node_content\":\"[{\\\"functionParams\\\":{},\\\"fieldName\\\":\\\"test_模型字段\\\",\\\"functions\\\":{},\\\"import\\\":false,\\\"expressionUnits\\\":[{\\\"type\\\":\\\"input\\\",\\\"value\\\":\\\"4\\\"},{\\\"term\\\":\\\"+\\\",\\\"type\\\":\\\"term\\\"},{\\\"type\\\":\\\"input\\\",\\\"value\\\":\\\"3\\\"}],\\\"fieldCode\\\":\\\"NODE_test_model\\\",\\\"referFunctionCodes\\\":\\\"\\\",\\\"isKey\\\":false,\\\"description\\\":\\\"\\\",\\\"relColumnCode\\\":\\\"NODE_test_model\\\",\\\"resourceObjectVersionId\\\":11156,\\\"priority\\\":0,\\\"recorded\\\":false,\\\"resourceObjectId\\\":11155,\\\"fieldState\\\":\\\"ACTIVE\\\",\\\"objectType\\\":\\\"NODE_FEATURE\\\",\\\"referFieldBids\\\":\\\"\\\",\\\"valueType\\\":\\\"INTEGER\\\",\\\"columnCode\\\":\\\"NODE_test_model\\\",\\\"position\\\":0,\\\"bid\\\":\\\"f12176\\\",\\\"fieldType\\\":\\\"FORMULA_FIELD\\\",\\\"fieldId\\\":12176}]\",\"node_key\":\"12190_20395\",\"node_type\":\"DERIVE_FIELD\",\"strategy_id\":16431,\"text\":\"衍生变量\",\"width\":150,\"x\":75,\"y\":125}";
        JSONObject jsonObject = JSON.parseObject(value);
        Object tableProperty = DataConverRuleEngineUtils.getTableProperty(jsonObject, "node_content.eventStatObjectInfoId");
        System.out.println(tableProperty);
    }
}