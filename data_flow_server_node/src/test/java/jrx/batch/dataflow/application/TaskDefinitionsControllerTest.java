package jrx.batch.dataflow.application;

import com.alibaba.fastjson.JSON;
import jrx.batch.dataflow.SpringbootDataflowServerApplication;
import jrx.batch.dataflow.domain.service.ITaskDefinitionsService;
import jrx.batch.dataflow.infrastructure.model.TaskDefinitions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringbootDataflowServerApplication.class)
public class TaskDefinitionsControllerTest {
    @Autowired
    private ITaskDefinitionsService taskDefinitionsService;

    @Test
    public void name() {
        List<TaskDefinitions> list = taskDefinitionsService.list();
        System.out.println(JSON.toJSONString(list));
    }
}