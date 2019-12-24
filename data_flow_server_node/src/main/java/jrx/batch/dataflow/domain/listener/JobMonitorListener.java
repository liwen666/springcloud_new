package jrx.batch.dataflow.domain.listener;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import jrx.batch.dataflow.domain.config.system.PropertiesThreadLocalHolder;
import jrx.batch.dataflow.domain.service.ITaskExecutionService;
import jrx.batch.dataflow.domain.service.impl.TaskTaskBatchServiceImpl;
import jrx.batch.dataflow.infrastructure.model.TaskExecution;
import jrx.batch.dataflow.infrastructure.model.TaskTaskBatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

/**
 * 每一个job执行的监听器，对job执行完成做处理
 * @author yxy
 * @date 2018/6/18
 */
public class JobMonitorListener implements JobExecutionListener {
    @Autowired
    private TaskTaskBatchServiceImpl taskTaskBatch;
    @Autowired
    ITaskExecutionService taskExecutionServiceImpl;


    private static final Logger logger = LoggerFactory.getLogger(JobMonitorListener.class);

    @Override
    public void beforeJob(JobExecution jobExecution) {
        logger.info("=====before job 开始执行：executionId: {}, jobInstanceId: {}, 开始时间：{} ",
                jobExecution.getId(),
                jobExecution.getJobId(),
                jobExecution.getStartTime()
        );
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        logger.info("=====after job 执行完成：executionId: {}, jobInstanceId: {}, 结束时间：{} ",
                jobExecution.getId(),
                jobExecution.getJobId(),
                jobExecution.getEndTime()
        );
        /**
         * 更新任务
         */
        String taskExecutionId = PropertiesThreadLocalHolder.getProperties("--spring.cloud.task.executionid");
        PropertiesThreadLocalHolder.remove("--spring.cloud.task.executionid");
        TaskTaskBatch build = TaskTaskBatch.builder().jobExecutionId(jobExecution.getJobId()).taskExecutionId(Long.parseLong(taskExecutionId)).build();
        taskTaskBatch.save(build);
        TaskExecution taskExecution = TaskExecution.builder()
                .endTime(LocalDateTime.now())
                .exitCode(0)
                .exitMessage("task complete").build();
        boolean update = taskExecutionServiceImpl.update(taskExecution, Wrappers.<TaskExecution>lambdaUpdate().eq(TaskExecution::getTaskExecutionId, taskExecutionId));

    }
}
