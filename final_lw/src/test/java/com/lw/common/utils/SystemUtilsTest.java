package com.lw.common.utils;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.LinkedHashMap;

import static org.junit.Assert.*;

public class SystemUtilsTest {
    public static void main(String[] args) throws IOException {
        URL resource = SystemUtilsTest.class.getClassLoader().getResource("");
        System.out.println(resource.getPath());
        System.out.println("******************************************************");

    }
    @Test
    public void testyam(){
        InputStream in = YamlUtil.class.getClassLoader().getResourceAsStream("application.yml");
        LinkedHashMap<?, ?> load = (LinkedHashMap<?, ?>) new Yaml().load(in);
        System.out.println(JSON.toJSON(load));
    }

}