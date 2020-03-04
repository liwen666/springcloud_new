package com.example.thymeleafdemo.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.yaml.snakeyaml.Yaml;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 *  描述
 * </p>
 *
 *
 * @author lw
 * @since  2019/12/5 14:55
 */
@Slf4j
public class YamlUtil {

    @SuppressWarnings("unchecked")
    public static void setProperty(Map map, Object qualifiedKey, Object value) {
        if (map != null && qualifiedKey != null) {
            String input = String.valueOf(qualifiedKey);
            if (!input.equals("")) {
                if (input.contains(".")) {
                    int index = input.indexOf(".");
                    String left = input.substring(0, index);
                    String right = input.substring(index + 1, input.length());
                    if (map.get(left) == null) map.put(left, new HashMap<>());
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
}