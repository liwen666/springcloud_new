package jrx.batch.dataflow.domain.service;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import jrx.batch.dataflow.SpringbootDataflowServerApplication;
import jrx.batch.dataflow.infrastructure.model.BatchStepExecution;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringbootDataflowServerApplication.class)
public class IBatchStepExecutionServiceTest {

    @Autowired
    private  IBatchStepExecutionService stepExecutionService;

    @Test
    public void getSteptEntrity() {
        BatchStepExecution one = stepExecutionService.getOne(Wrappers.<BatchStepExecution>lambdaQuery().eq(BatchStepExecution::getStepExecutionId, 2l));
        System.out.println(JSON.toJSONString(one));
    }
}