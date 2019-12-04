package com.nacos.dao;

import com.nacos.Springboot2NacosConfigApplication;
import lombok.extern.java.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;


@Log
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Springboot2NacosConfigApplication.class)
public class ConfigInfoMapperTest
{

    @Autowired
    private ConfigInfoMapper configInfoMapper;

    @Test
    public void name() {
        List<ConfigInfo> configInfos = configInfoMapper.selectList(null);
    }
}