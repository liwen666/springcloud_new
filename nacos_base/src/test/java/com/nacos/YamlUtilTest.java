package com.nacos;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.*;

@Slf4j
public class YamlUtilTest {


    @Test
    public void yaml() throws FileNotFoundException {
        Yaml yaml = new Yaml();
//        Map load = yaml.load("{\"spring\":{\"profiles\":\"dev\",\"cloud\":{\"nacos\":{\"config\":{\"enabled\":true,\"server-addr\":\"172.16.101.29:8848,172.16.101.30:8848,172.16.101.31:8848\",\"namespace\":\"78ed2bf8-ebda-410a-856d-48c14f81f1dd\",\"file-extension\":\"yaml\",\"group\":\"DEFAULT_GROUP\"},\"discovery\":{\"server-addr\":\"172.16.101.29:8848, 172.16.101.30:8848, 172.16.101.31:8848\",\"namespace\":\"78ed2bf8-ebda-410a-856d-48c14f81f1dd\"}}}}}\n");
        Map load2 = yaml.load("{\"spring\":{\"application\":{\"name\":\"testnacos\"},\"profiles\":{\"active\":\"dev\"}}}");

//        load.putAll(load2);
        Map<?, ?> map = YamlUtil.loadYaml("application-dev.yaml");
        System.out.println(map);
        System.out.println(load2);
        Object property = YamlUtil.getProperty(load2, "spring.application.name");
        System.out.println(property);


    }

    @Test
    public void dumpYaml() throws IOException {
        Map<?, ?> map = YamlUtil.loadYaml("bootstrap.yaml");
        System.out.println(JSON.toJSONString(map));
        YamlUtil.dumpYaml("test", map);

    }

    @Test
    public void load() throws IOException {
        Map<?, ?> map = YamlUtil.loadYaml("test.yaml");
        System.out.println(JSON.toJSONString(map));
        Map<String, Object> resPor = new HashMap<>();
        resPor = converMapToProperties(map, resPor, "");

        Map conver = converPropertiesToMap(resPor);
        YamlUtil.dumpYaml("test", conver);

    }

    private Map converPropertiesToMap(Map<String, Object> resPor) {
        Map m = new HashMap();
        Set<String> strings = resPor.keySet();
        for (String key : strings) {
            YamlUtil.setProperty(m, key, resPor.get(key));
        }
        return m;
    }

    private Map<String, Object> converMapToProperties(Map<?, ?> map, Map<String, Object> resPor, String prefixKey) {
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

    @Test
    public void loadMautiple() throws IOException {
        FileInputStream f = new FileInputStream("D:\\idea2018workspace\\springcloud_new\\nacos_base\\src\\main\\resources\\data.yaml");
        byte[] cache = new byte[f.available()];
        f.read(cache);
        String data = new String(cache, "utf-8");
        String[] split = data.split("---");
        Map result = new HashMap();
//        for (String str : split) {
        Map<?, ?> map = YamlUtil.loadYamlByContext(split[0]);

//        }


        System.out.println(JSON.toJSONString(map));
        YamlUtil.dumpYaml("test", map);

    }

    @Test
    public void testAllMechtod() throws IOException {
        FileInputStream f = new FileInputStream("D:\\idea2018workspace\\springcloud_new\\nacos_base\\src\\main\\resources\\data.yaml");
        byte[] cache = new byte[f.available()];
        f.read(cache);
        String data = new String(cache, "utf-8");
        String[] split = data.split("---");
        Map result = new HashMap();
        for (String str : split) {
            Map<?, ?> map = YamlUtil.loadYamlByContext(str);
            Map<String, Object> stringObjectMap = YamlUtil.converMapToProperties(map);
            result.putAll(stringObjectMap);
        }
        Map map = YamlUtil.converPropertiesToMap(result);
        YamlUtil.dumpYaml("test", map);
    }

    @Test
    public void collatingCfg() {
        String context = "";
        Map map = YamlUtil.collatingCfg(context);
    }
}