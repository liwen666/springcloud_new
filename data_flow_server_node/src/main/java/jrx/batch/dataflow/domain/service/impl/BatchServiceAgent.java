package jrx.batch.dataflow.domain.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import jrx.batch.dataflow.domain.service.IBatchJobExecutionService;
import jrx.batch.dataflow.domain.service.IBatchStepExecutionService;
import jrx.batch.dataflow.domain.service.ITaskExecutionService;
import jrx.batch.dataflow.infrastructure.model.BatchJobExecution;
import jrx.batch.dataflow.infrastructure.model.BatchStepExecution;
import jrx.batch.dataflow.infrastructure.model.TaskExecution;
import org.springframework.batch.core.BatchStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 描述
 * </p>
 *
 * @author lw
 * @since 2019/5/26 23:40
 */
@Transactional
@Service
public class BatchServiceAgent {

    @Autowired
    private IBatchStepExecutionService batchStepExecutionService;
    @Autowired
    private IBatchJobExecutionService batchJobExecutionService;
   @Autowired
    private ITaskExecutionService taskExecutionService;


    public boolean updateJobAndStep(long executionId, String status) {
        BatchStepExecution build = BatchStepExecution.builder().status(status).build();
        if (BatchStatus.COMPLETED.name().equals(status)) {
            build.setEndTime(LocalDateTime.now());
            build.setLastUpdated(LocalDateTime.now());
        }
        boolean update = batchStepExecutionService.update(build, Wrappers.lambdaUpdate(BatchStepExecution.builder().stepExecutionId(executionId).build()));
        BatchJobExecution jobExecution = batchJobExecutionService.getById(batchStepExecutionService.getById(executionId).getJobExecutionId());
        List<BatchStepExecution> list = batchStepExecutionService.list(Wrappers.<BatchStepExecution>lambdaQuery().eq(BatchStepExecution::getJobExecutionId, jobExecution.getJobExecutionId()));
      List<String> stepStatus = new ArrayList<>();
        for(BatchStepExecution batchStepExecution:list){
            stepStatus.add(batchStepExecution.getStatus());
        }
        /**
         * 计算job状态并作更新
         */

        boolean job = false;
        if(stepStatus.contains(BatchStatus.STARTED.name())||stepStatus.contains(BatchStatus.STARTING.name())||stepStatus.contains(BatchStatus.STOPPING.name())){
            jobExecution.setStatus(BatchStatus.STARTED.name());
            jobExecution.setLastUpdated(LocalDateTime.now());
            job= batchJobExecutionService.updateById(jobExecution);
        }else if(stepStatus.contains(BatchStatus.FAILED.name())||stepStatus.contains(BatchStatus.UNKNOWN.name())){
            jobExecution.setStatus(BatchStatus.FAILED.name());
            jobExecution.setLastUpdated(LocalDateTime.now());
            job=batchJobExecutionService.updateById(jobExecution);
        }else{
            jobExecution.setLastUpdated(LocalDateTime.now());
            jobExecution.setEndTime(LocalDateTime.now());
            jobExecution.setStatus(BatchStatus.COMPLETED.name());
            job= batchJobExecutionService.updateById(jobExecution);
        }

        return update&&job;
    }
}
