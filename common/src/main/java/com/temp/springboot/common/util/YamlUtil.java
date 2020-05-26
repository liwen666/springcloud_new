package com.temp.springboot.common.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.jute.compiler.JMap;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.net.URL;
import java.util.*;

/**
 * <p>
 * 描述
 * </p>
 *
 * @author lw
 * @since 2019/12/5 14:55
 */
@Slf4j
public class YamlUtil {
    public static Map<?, ?> loadYaml(String fileName) throws FileNotFoundException {
        InputStream in = YamlUtil.class.getClassLoader().getResourceAsStream(fileName);
        return StringUtils.isNotEmpty(fileName) ? (LinkedHashMap<?, ?>) new Yaml().load(in) : null;
    }

    public static void dumpYaml(String fileName, Map<?, ?> map) throws IOException {
        if (StringUtils.isNotEmpty(fileName)) {
            URL resource = YamlUtil.class.getClassLoader().getResource("");
            File dump = new File(resource.getPath() + fileName);
            if (!dump.exists()) {
                dump.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(YamlUtil.class.getClassLoader().getResource(fileName).getFile());
            DumperOptions options = new DumperOptions();
            options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
            Yaml yaml = new Yaml(options);
            yaml.dump(map, fileWriter);
        }
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
                        ArrayList<Object> objects = Lists.newArrayList();
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

    public static Map<String, Object> converMapToProperties(Map<?, ?> map, Map<String, Object> resPor, String prefixKey) {
        Set<?> objects = map.keySet();
        for (Object key : objects) {
            if (map.get(key) instanceof Map) {
                if (StringUtils.isEmpty(prefixKey)) {
                    converMapToProperties((Map<?, ?>) map.get(key), resPor, (String) key);
                } else {
                    converMapToProperties((Map<?, ?>) map.get(key), resPor, prefixKey + "." + (String) key);
                }
            } else {
                if (null == map.get(key)) {
                    log.warn("===配置文件值为空，配置项key:{} -> value:{}", prefixKey + "." + key, map.get(key));
                } else {
                    resPor.put(prefixKey + "." + (String) key, map.get(key));
                }
            }
        }
        return resPor;
    }


    public static Map converPropertiesToMap(Map<String, Object> resPor) {
        Map m = new HashMap();
        Set<String> strings = resPor.keySet();
        for (String key : strings) {
            YamlUtil.setProperty(m, key, resPor.get(key));
        }
        return m;
    }

    public static Map<?, ?> loadYamlByContext(String context) {
        return new Yaml().load(context);
    }

    public static Map<String, Object> converMapToProperties(Map<?, ?> map) {
        Map<String, Object> resPor = new HashMap<>();
        return converMapToProperties(map, resPor, "");
    }

    public static Map collatingCfg(String context) {
        String[] split = context.split("---");
        Map result = new HashMap();
        for (String str : split) {
            Map<?, ?> map = YamlUtil.loadYamlByContext(str);
            Map<String, Object> stringObjectMap = YamlUtil.converMapToProperties(map);
            result.putAll(stringObjectMap);
        }
        return YamlUtil.converPropertiesToMap(result);
    }

    public static void main(String[] args) {
//        String json = "{\n" +
//                "\t\"valueConditions\": [{\n" +
//                "\t\t\"groupOrder\": 1,\n" +
//                "\t\t\"default\": true,\n" +
//                "\t\t\"functions\": {},\n" +
//                "\t\t\"expressionUnits\": [{\n" +
//                "\t\t\t\"code\": \"b8779\",\n" +
//                "\t\t\t\"name\": \"base\",\n" +
//                "\t\t\t\"type\": \"field\"\n" +
//                "\t\t}, {\n" +
//                "\t\t\t\"term\": \"+\",\n" +
//                "\t\t\t\"type\": \"term\"\n" +
//                "\t\t}, {\n" +
//                "\t\t\t\"type\": \"input\",\n" +
//                "\t\t\t\"value\": \"777\"\n" +
//                "\t\t}],\n" +
//                "\t\t\"referFunctionCodes\": [],\n" +
//                "\t\t\"defaultKey\": \"default\",\n" +
//                "\t\t\"key\": \"default\"\n" +
//                "\t}]\n" +
//                "}";
//
//        String json2 = "{\n" +
//                "\t\t\t\"code\": \"b8779\",\n" +
//                "\t\t\t\"name\": \"base\",\n" +
//                "\t\t\t\"type\": {\"field\":\"aaa\"," +
//                "\"value\":\"jjjjjjjj\"}\n" +
//                "\t\t}";
//
////        JSONObject jsonObject = JSON.parseObject(json);
////        System.out.println(getProperty(jsonObject,"valueConditions"));
////        JSONObject jsonObject2 = JSON.parseObject(json2);
////        System.out.println(getProperty(jsonObject2,"code"));
////        System.out.println(getProperty(jsonObject2,"name"));
////     setProperty(jsonObject2,"type.ppp.fff","kkkkkk");
////        System.out.println(getProperty(jsonObject2,"type.ppp.fff"));
//        /**
//         * 如果存在数组对象
//         //         */
//        JSONObject jsonObject = JSON.parseObject(json);
//        System.out.println(getProperty(jsonObject, "valueConditions.expressionUnits.code"));
        Map<String, String> keyVaule = new HashMap();
        keyVaule.put("n6828", "ttttt");
        keyVaule.put("n6822", "hhhh");
//        setProperty(jsonObject, "valueConditions.expressionUnits.code", "jjjjj",keyVaule);
//
//        System.out.println(getProperty(jsonObject, "valueConditions.expressionUnits.code"));
//        System.out.println(JSON.toJSONString(jsonObject));

//        String script = "{\"functionType\":\"NORMAL_FUNCTION\",\"referFieldBids\":\"n6828,n6822\",\"scriptLanguage\":\"JAVA\",\"scripts\":\"console.log(cP.test(\\\"11010519880605371X\\\"));\"}";
//        JSONObject jsonObject = JSON.parseObject(script);
//        setProperty(jsonObject, "referFieldBids", "",keyVaule);
//        System.out.println(JSON.toJSONString(jsonObject));

        System.out.println("bbbcc123".replaceAll("[a-z]",""));
    }

    private static void setProperty(Map map, Object qualifiedKey, Object value, Map<String, String> keyVaule) {
        Object property = getProperty(map, qualifiedKey);
        if (property instanceof List) {
            JSONArray jsonArray = (JSONArray) (((List) getProperty(map, String.valueOf(qualifiedKey).substring(0, String.valueOf(qualifiedKey).lastIndexOf(".")))).get(0));
            for (Object jsonObject : jsonArray) {
                String subCode = String.valueOf(qualifiedKey).substring(String.valueOf(qualifiedKey).lastIndexOf(".") + 1);
                Object property1 = getProperty((Map<?, ?>) jsonObject, subCode);
                if (null != property1) {
                    value = keyVaule.get(property1);
                    setProperty((Map) jsonObject, subCode, value);
                }
            }
        } else {
            String s = String.valueOf(property);
            if (null!=keyVaule) {
                value = new StringBuffer("");
                String[] split = s.split(",");
                for (String s1 : split) {
                    ((StringBuffer) value).append(keyVaule.get(s1)+",");
                }
            }
            setProperty(map, qualifiedKey, value.toString().substring(0,value.toString().length()-1));
        }

    }

}