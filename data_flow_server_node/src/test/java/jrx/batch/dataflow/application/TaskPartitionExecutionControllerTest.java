package jrx.batch.dataflow.application;

import jrx.batch.dataflow.SpringbootDataflowServerApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringbootDataflowServerApplication.class)
public class TaskPartitionExecutionControllerTest {
        @Autowired
    private TaskPartitionExecutionController taskPartitionExecutionController;

    @Test
    public void name() {

        taskPartitionExecutionController.test();
    }
}