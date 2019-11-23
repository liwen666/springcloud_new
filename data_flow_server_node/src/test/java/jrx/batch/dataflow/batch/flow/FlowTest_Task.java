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
import org.springframework.cloud.deployer.spi.local.LocalDeployerProperties;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;

@Log
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringbootDataflowServerApplication.class)
//@EnableConfigurationProperties(LocalDeployerProperties.class)
public class FlowTest_Task {
    @Autowired
    private TaskExecutionService taskService;
    @Autowired
    private DataSource dataSource;

    @Autowired
    TaskDeploymentRepository taskDeploymentRepository;
    @Autowired
    AppRegistrationRepository appRegistrationRepository;

    @Autowired
    LocalDeployerProperties localDeployerProperties;
    @Autowired
    LocalPlatformProperties localPlatformProperties;

    @Autowired
    TaskDefinitionRepository taskDefinitionRepository;

    @Autowired
    TaskExecutionMapper taskExecutionMapper;

    @Test
    public void name() {
        taskExecutionMapper.insert(TaskExecution.builder().taskExecutionId(1l).parentExecutionId(1l).taskName("22").build());
    }

    /**
     * 1 上传App 并注册
     *   附上版本号
     */
    @Test
    public void appRegistration() throws URISyntaxException {
        AppRegistration appRegistration = new AppRegistration();
        appRegistration.setVersion("1");
        appRegistration.setDefaultVersion(true);
        appRegistration.setType(ApplicationType.task);
        appRegistration.setUri(new URI("file://C:/Users/liwen/Desktop/jrx/taskjar/local.jar"));
        appRegistration.setName("test_task_only");
        AppRegistration save = appRegistrationRepository.save(appRegistration);
        System.out.println(JSON.toJSONString(save));
        AppRegistration simplejob = appRegistrationRepository.findAppRegistrationByNameAndTypeAndVersion("test_task_only", ApplicationType.task  ,"1");

        System.out.println(JSON.toJSONString(simplejob));
    }


    /**
     * 2 任务关联APP
     */
    @Test
    public void taskDefinitionRepository() {
        //任务定义 此处如果任务名称重复会更新任务  对应的app名称
        TaskDefinition taskDefinition = new TaskDefinition("taskTestOnly","test_task_only");
        TaskDefinition save = taskDefinitionRepository.save(taskDefinition);
        System.out.println(save.getTaskName());
    }

    /**
     * 3 执行任务
     */
    @Test
    public void execBatchTask() {
        long executeTask = taskService.executeTask("taskTestOnly", new HashMap<String, String>() {{
//            put("app.test", "1");//deployment property keys starting with 'app.', 'deployer.' or, 'scheduler.' allowed, got 'param.test'
            /**
             * 此参数可以选择执行任务的launcher
             * 见配置项中的平台配置，配置项没有则为default
             * 本系统配置了test 和local 两个平台
             */
            put("spring.cloud.dataflow.task.platformName", "default");
//            put("spring.cloud.dataflow.task.platformName", "local");
        }}, new ArrayList<String>() {{
            ///执行任务传入的参数
            add("param = test");//此参数表示执行这个任务时指定平台是什么，如果和task_deployment中的不一致任务无法执行
        }});
        System.out.println("******************************************************");

        System.out.println(executeTask);
    }

    /**
     * 3 执行任务
     */
    @Test
    public void execBatchTaskByController() {
        long executeTask = taskService.executeTask("test_node", new HashMap<String, String>() {{
//            put("app.test", "1");//deployment property keys starting with 'app.', 'deployer.' or, 'scheduler.' allowed, got 'param.test'
            /**
             * 此参数可以选择执行任务的launcher
             * 见配置项中的平台配置，配置项没有则为default
             * 本系统配置了test 和local 两个平台
             */
            put("spring.cloud.dataflow.task.platformName", "default");//此参数表示执行这个任务时指定平台是什么，如果和task_deployment中的不一致任务无法执行
//            put("spring.cloud.dataflow.task.platformName", "local");
        }}, new ArrayList<String>() {{
            ///执行任务传入的参数   @see TaskProperties
            add("--spring.cloud.task.executionid=30");
            add("--spring.cloud.task.parentExecutionId=666666666666666");
            add("param = test");
        }});
        System.out.println("******************************************************");

        System.out.println(executeTask);
    }



//    *********************************************************************************

//    失败测试


    @Test
    public void failAppRegister() throws URISyntaxException {
        AppRegistration appRegistration = new AppRegistration();
        appRegistration.setVersion("1");
        appRegistration.setDefaultVersion(true);
        appRegistration.setType(ApplicationType.task);
        appRegistration.setUri(new URI("file://C:/Users/liwen/Desktop/jrx/localtask/normal.jar"));
        appRegistration.setName("fail_task");
        AppRegistration save = appRegistrationRepository.save(appRegistration);
        System.out.println(JSON.toJSONString(save));
        AppRegistration simplejob = appRegistrationRepository.findAppRegistrationByNameAndTypeAndVersion("test_task_only", ApplicationType.task  ,"1");

        System.out.println(JSON.toJSONString(simplejob));
    }
    /**
     * 2 任务关联APP
     */
    @Test
    public void taskRelation() {
        //任务定义 此处如果任务名称重复会更新任务  对应的app名称
        TaskDefinition taskDefinition = new TaskDefinition("taskFailOnly","fail_task");
        TaskDefinition save = taskDefinitionRepository.save(taskDefinition);
        System.out.println(save.getTaskName());
    }

    /**
     * 3 执行任务
     */
    @Test
    public void execFailBatchTask() {
        long executeTask = taskService.executeTask("taskFailOnly", new HashMap<String, String>() {{
//            put("app.test", "1");//deployment property keys starting with 'app.', 'deployer.' or, 'scheduler.' allowed, got 'param.test'
            /**
             * 此参数可以选择执行任务的launcher
             * 见配置项中的平台配置，配置项没有则为default
             * 本系统配置了test 和local 两个平台
             */
            put("spring.cloud.dataflow.task.platformName", "default");
//            put("spring.cloud.dataflow.task.platformName", "local");
        }}, new ArrayList<String>() {{
            ///执行任务传入的参数
            add("param = test");//此参数表示执行这个任务时指定平台是什么，如果和task_deployment中的不一致任务无法执行
        }});
        System.out.println("******************************************************");

        System.out.println(executeTask);
    }
}