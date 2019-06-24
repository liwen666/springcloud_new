package com.temp.springboot;

import com.alibaba.fastjson.JSON;
import com.temp.springboot.example.service.IBpmnTempService;
import lombok.extern.java.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AppServer.class)
@Log
public class AppServerTest {
    @Autowired
    private IBpmnTempService bpmnTempService;
    @Test
    public void getTemp() throws Exception {
        System.out.println(JSON.toJSONString(bpmnTempService.findTempList()));
    }

    @Test
    public void logTest() throws Exception {
        log.info("info测试{}");
    }
}