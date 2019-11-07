package jrx.batch.dataflow.domain.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

/**
 * 每一个job执行的监听器，对job执行完成做处理
 * @author yxy
 * @date 2018/6/18
 */
public class JobMonitorListener implements JobExecutionListener {


    private static final Logger logger = LoggerFactory.getLogger(JobMonitorListener.class);

    @Override
    public void beforeJob(JobExecution jobExecution) {
        logger.info("before job 开始执行：executionId: {}, jobInstanceId: {}, 开始时间：{} ",
                jobExecution.getId(),
                jobExecution.getJobId(),
                jobExecution.getStartTime()
        );
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        logger.info("after job 执行完成：executionId: {}, jobInstanceId: {}, 结束时间：{} ",
                jobExecution.getId(),
                jobExecution.getJobId(),
                jobExecution.getEndTime()
        );

    }
}
