package jrx.batch.dataflow.batch.job;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import jrx.batch.dataflow.SpringbootDataflowServerApplication;
import jrx.batch.dataflow.domain.config.batch.TaskCommandRunner;
import jrx.batch.dataflow.domain.listener.SimpleStepExecutionListener;
import jrx.batch.dataflow.domain.service.ITaskExecutionService;
import jrx.batch.dataflow.domain.service.impl.TaskExecutionServiceImpl;
import jrx.batch.dataflow.infrastructure.model.TaskExecution;
import lombok.extern.java.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.dataflow.server.service.TaskExecutionCreationService;
import org.springframework.cloud.dataflow.server.service.TaskExecutionService;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yuanxingyu
 * @date 2018/3/28
 */
@Log
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringbootDataflowServerApplication.class)
public class SimpleTaskTest {
    @Autowired
    private ConfigurableApplicationContext applicationContext;
    @Autowired
    private TaskExecutionCreationService taskExecutionRepositoryService;

    @Autowired
    ITaskExecutionService taskExecutionServiceImpl;



    private static final Logger logger = LoggerFactory.getLogger(SimpleTaskTest.class);


    @Test
    public void name() {
        taskExecutionServiceImpl.save(TaskExecution.builder().taskName("testsimp").startTime(LocalDateTime.now()).parentExecutionId(3242343l).build());
    }

    @Test
    public void createtask() {
        org.springframework.cloud.task.repository.TaskExecution simpletask1 = taskExecutionRepositoryService.createTaskExecution("simpletask1");
        System.out.println(JSON.toJSONString(simpletask1));
        TaskExecution build = TaskExecution.builder().taskName(simpletask1.getTaskName())
                .parentExecutionId(11111l)
                .taskExecutionId(simpletask1.getExecutionId())
                .startTime(LocalDateTime.now()).build();
        boolean update = taskExecutionServiceImpl.update(build, Wrappers.<TaskExecution>lambdaUpdate().eq(TaskExecution::getTaskExecutionId, build.getTaskExecutionId()));

    }
}
