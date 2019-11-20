package jrx.batch.dataflow.batch.flow;

import com.alibaba.fastjson.JSON;
import jrx.batch.dataflow.SpringbootDataflowServerApplication;
import jrx.batch.dataflow.infrastructure.dao.TaskExecutionMapper;
import jrx.batch.dataflow.infrastructure.model.TaskExecution;
import lombok.extern.java.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.dataflow.core.AppRegistration;
import org.springframework.cloud.dataflow.core.ApplicationType;
import org.springframework.cloud.dataflow.core.TaskDefinition;
import org.springframework.cloud.dataflow.registry.repository.AppRegistrationRepository;
import org.springframework.cloud.dataflow.server.config.features.LocalPlatformProperties;
import org.springframework.cloud.dataflow.server.repository.TaskDefinitionRepository;
import org.springframework.cloud.dataflow.server.repository.TaskDeploymentRepository;
import org.springframework.cloud.dataflow.server.service.TaskExecutionService;
import org.springframework.cloud.dataflow.server.service.impl.TaskConfigurationProperties;
import org.springframework.cloud.deployer.spi.local.LocalDeployerProperties;
import org.springframework.cloud.deployer.spi.local.LocalTaskLauncher;
import org.springframework.cloud.deployer.spi.task.TaskLauncher;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;

@Log
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringbootDataflowServerApplication.class)
//@EnableConfigurationProperties(LocalDeployerProperties.class)
public class Other_Function {
    @Autowired
    private TaskExecutionService taskService;
    @Autowired
    private DataSource dataSource;
    @Autowired
    TaskLauncher taskLauncher;
    @Autowired
    TaskDeploymentRepository taskDeploymentRepository;
    @Autowired
    AppRegistrationRepository appRegistrationRepository;

    @Autowired
    LocalDeployerProperties localDeployerProperties;

    @Autowired
    TaskDefinitionRepository taskDefinitionRepository;

    @Autowired
    TaskExecutionMapper taskExecutionMapper;

    @Test
    public void logSpace() {

        Path workingDirectoriesRoot = localDeployerProperties.getWorkingDirectoriesRoot();
        System.out.println(workingDirectoriesRoot);

    }


}