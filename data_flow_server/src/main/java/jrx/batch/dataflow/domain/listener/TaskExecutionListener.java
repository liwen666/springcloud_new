package jrx.batch.dataflow.domain.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.DefaultApplicationArguments;
import org.springframework.cloud.task.listener.annotation.AfterTask;
import org.springframework.cloud.task.listener.annotation.BeforeTask;
import org.springframework.cloud.task.listener.annotation.FailedTask;
import org.springframework.cloud.task.repository.TaskExecution;
import org.springframework.cloud.task.repository.support.SimpleTaskExplorer;
import org.springframework.util.StringUtils;

import java.util.Arrays;

/**
 * @author yuanxingyu
 * @date 2018/3/28
 */
public class TaskExecutionListener {

    private static final Logger logger = LoggerFactory.getLogger(TaskExecutionListener.class);

//    @Autowired
//    private JobOperator jobOperator;
//
//    @Autowired
//    private JobExplorer jobExplorer;
//
//    @Autowired
//    private SimpleTaskExplorer simpleTaskExplorer;

    @Autowired
    private DefaultApplicationArguments applicationArguments;



    @BeforeTask
    public void beforeTask(TaskExecution taskExecution) {
        String args = Arrays.toString(applicationArguments.getSourceArgs());
        logger.info("=====start running task: {}, \n , taskArguments: {}", taskExecution, args);
    }

    @AfterTask
    public void afterTask(TaskExecution taskExecution) {
        logger.info("=====task complete: executionId: {}, taskName: {}, arguments: {}, exitCode: {}",
                taskExecution.getExecutionId(),
                taskExecution.getTaskName(),
                taskExecution.getArguments(),
                taskExecution.getExitCode()
        );
        taskExecution.setExitMessage("task complete");
    }

    @FailedTask
    public void failTask(TaskExecution taskExecution, Throwable throwable) {
        logger.error("=====task error exception, {} error msg: {}", taskExecution, throwable.getMessage());
        if (StringUtils.isEmpty(taskExecution.getErrorMessage())) {
            taskExecution.setErrorMessage(throwable.getMessage());
        }
    }
}
