package jrx.batch.dataflow.infrastructure.dao;

import com.alibaba.fastjson.JSON;
import jrx.batch.dataflow.SpringbootDataflowServerApplication;
import jrx.batch.dataflow.infrastructure.model.TaskExecution;
import lombok.extern.java.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@Log
@ActiveProfiles("local")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringbootDataflowServerApplication.class)
public class TaskExecutionMapperTest {
    @Autowired
    private TaskExecutionMapper taskExecutionMapper;

    @Test
    public void name() {
        List<TaskExecution> taskExecutions = taskExecutionMapper.selectList(null);
        System.out.println(JSON.toJSONString(taskExecutions));
    }
}