package com.nacos.dao;

import com.nacos.Springboot2NacosConfigApplication;
import com.nacos.YamlUtil;
import lombok.extern.java.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;


//@Log
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest(classes = Springboot2NacosConfigApplication.class)
public class ConfigInfoMapperTest
{

    @Autowired
    private ConfigInfoMapper configInfoMapper;

    @Test
    public void name() {


        List<ConfigInfo> configInfos = configInfoMapper.selectList(null);
    }

    @Test
    public void conversion() throws IOException {
        FileInputStream fileInputStream = new FileInputStream(new File("D:\\idea2018workspace\\springcloud_new\\nacos_base\\src\\test\\java\\com\\nacos\\dao\\bootstrap.yaml"));
        byte [] cache = new byte[fileInputStream.available()];
        fileInputStream.read(cache);
        String utf8 = new String(cache, "utf8");
        Map<?, ?> map = YamlUtil.loadYamlByContext(utf8);



    }
}