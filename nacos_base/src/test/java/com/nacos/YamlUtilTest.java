package com.nacos;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.yaml.snakeyaml.Yaml;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class YamlUtilTest

{


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
        YamlUtil.dumpYaml("test",map);

    }

    @Test
    public void load() throws IOException {
        Map<?, ?> map = YamlUtil.loadYaml("test.yaml");
        System.out.println(JSON.toJSONString(map));
        YamlUtil.dumpYaml("test",map);

    }




}