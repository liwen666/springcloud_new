package com.temp.mybatis.dao;


import com.temp.mybatis.AppStarter;
import com.temp.mybatis.model.FlinkJobConfig;
import jrx.data.hub.core.utils.JsonUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 *
 */
@ActiveProfiles("local")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppStarter.class)
public class FlinkAppConfigControllerTest {
    @Autowired
    private FlinkJobConfigMapper flinkJobConfigMapper;
    @Test
    public void name() {
        List<FlinkJobConfig> flinkJobConfigs = flinkJobConfigMapper.selectAll();
        System.out.println(JsonUtils.obj2StringPretty(flinkJobConfigs));
    }
}