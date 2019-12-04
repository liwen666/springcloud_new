package com.nacos;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.List;
import java.util.Properties;

import static org.junit.Assert.*;

public class TransferUtilsTest {


    @Test
    public void yamlToProperties() {
//        Properties properties = TransferUtils.yml2Properties("D:\\idea2018workspace\\springcloud_new\\nacos_base\\src\\main\\resources\\application-dev.yaml");
        Properties properties = TransferUtils.yml2Properties("D:\\idea2018workspace\\springcloud_new\\nacos_base\\src\\main\\resources\\test.yaml");
        System.out.println(JSON.toJSONString(properties));

    }

    @Test
    public void propertiesToYaml() {
        Properties properties = TransferUtils.yml2Properties("D:\\idea2018workspace\\springcloud_new\\nacos_base\\src\\main\\resources\\bootstrap.yaml");
        System.out.println(properties);
        TransferUtils.properties2Yaml(properties);

    }

    @Test
    public void listPros() {
        System.out.println(TransferUtils.listPros("D:\\idea2018workspace\\springcloud_new\\nacos_base\\src\\main\\resources\\test.yaml"));
    }
}