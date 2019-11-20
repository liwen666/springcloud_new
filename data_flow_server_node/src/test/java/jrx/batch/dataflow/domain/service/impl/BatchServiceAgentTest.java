package jrx.batch.dataflow.domain.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import jrx.batch.dataflow.SpringbootDataflowServerApplication;
import jrx.batch.dataflow.infrastructure.dao.BatchStepExecutionMapper;
import jrx.batch.dataflow.infrastructure.model.BatchStepExecution;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.BatchStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringbootDataflowServerApplication.class)
public class BatchServiceAgentTest {
    @Autowired
    private  BatchServiceAgent batchServiceAgent;

    @Autowired
    private BatchStepExecutionMapper batchStepExecutionMapper;

    @Test
    public void name() {
        boolean b = batchServiceAgent.updateJobAndStep(2, BatchStatus.COMPLETED.name());

    }

    @Test
    public void update() {
        BatchStepExecution build = BatchStepExecution.builder().stepExecutionId(1l).status(BatchStatus.COMPLETED.name()).build();
//        int update = batchStepExecutionMapper.update(build, Wrappers.<BatchStepExecution>lambdaUpdate(BatchStepExecution.builder().stepExecutionId(1l).build()));
        int update = batchStepExecutionMapper.update(build, Wrappers.lambdaUpdate());
    }
}