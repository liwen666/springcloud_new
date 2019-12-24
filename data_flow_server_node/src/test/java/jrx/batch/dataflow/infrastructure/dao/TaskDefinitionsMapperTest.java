package jrx.batch.dataflow.infrastructure.dao;

import jrx.batch.dataflow.SpringbootDataflowServerApplication;
import jrx.batch.dataflow.util.TaskExecutionUtils;
import lombok.extern.java.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.dataflow.core.ApplicationType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@Log
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringbootDataflowServerApplication.class)
public class TaskDefinitionsMapperTest {

    @Autowired
    private TaskDefinitionsMapper taskExecutionMapper;


    @Test
    public void name() {
        Integer http_job_server = taskExecutionMapper.getAppType("http_job_server");
        ApplicationType applicationType=TaskExecutionUtils.getApplicationType(http_job_server);
    }
}