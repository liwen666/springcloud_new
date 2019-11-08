package jrx.batch.dataflow.domain.config.batch;

import jrx.batch.dataflow.domain.listener.JobMonitorListener;
import jrx.batch.dataflow.domain.listener.SimpleStepExecutionListener;
import jrx.batch.dataflow.domain.listener.TaskExecutionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.configuration.annotation.BatchConfigurer;
import org.springframework.cloud.task.configuration.DefaultTaskConfigurer;
import org.springframework.cloud.task.configuration.TaskConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author yuanxingyu
 * @date 2018/3/28
 */
@Configuration
//@EnableTask
//@EnableBatchProcessing
public class TaskCommonConfigurationDefault {

    private static final Logger logger = LoggerFactory.getLogger(TaskCommonConfigurationDefault.class);


    @Bean
    public TaskCommandRunner taskCommandRunner() {
        return new TaskCommandRunner();
    }

    @Bean
    public TaskExecutionListener taskExecutionListener() {
        return new TaskExecutionListener();
    }


    @Bean
    public JobMonitorListener jobMonitorListener() {
        return new JobMonitorListener();
    }

    @Bean
    public SimpleStepExecutionListener stepMonitorListener() {
        return new SimpleStepExecutionListener();
    }


    @Bean
    public BatchConfigurer batchConfigurer(DataSource dataSource) {
        logger.info("==========BatchConfig initialize:");
        return new DefineBatchConfigurer(dataSource);
    }


    @Bean
    public TaskConfigurer taskConfigurer(DataSource dataSource) {
        logger.info("==========TaskConfig initialize.");
        return new DefaultTaskConfigurer(dataSource);
    }


}
