package jrx.batch.dataflow.batch;

import com.alibaba.fastjson.JSON;
import jrx.batch.dataflow.SpringbootDataflowServerApplication;
import jrx.batch.dataflow.infrastructure.dao.TaskExecutionMapper;
import lombok.extern.java.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.dataflow.core.AppRegistration;
import org.springframework.cloud.dataflow.core.ApplicationType;
import org.springframework.cloud.dataflow.core.TaskDefinition;
import org.springframework.cloud.dataflow.core.TaskDeployment;
import org.springframework.cloud.dataflow.registry.repository.AppRegistrationRepository;
import org.springframework.cloud.dataflow.server.config.features.LocalPlatformProperties;
import org.springframework.cloud.dataflow.server.repository.TaskDefinitionRepository;
import org.springframework.cloud.dataflow.server.repository.TaskDeploymentRepository;
import org.springframework.cloud.dataflow.server.service.TaskExecutionService;
import org.springframework.cloud.deployer.spi.core.AppDeploymentRequest;
import org.springframework.cloud.deployer.spi.local.LocalDeployerProperties;
import org.springframework.cloud.deployer.spi.local.LocalTaskLauncher;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;

@Log
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringbootDataflowServerApplication.class)
//@EnableConfigurationProperties(LocalDeployerProperties.class)
public class TaskServiceTest {
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


    /***************************************/
    @Test
    public void localDeployerProperties() {
        System.out.println(JSON.toJSONString(localDeployerProperties));
    }

    @Test
    public void localPlatformProperties() {
        System.out.println(JSON.toJSONString(localPlatformProperties));
    }

    @Test
    public void taskDefinitionRepository() {
        TaskDefinition taskDefinition = new TaskDefinition("simple_job","simplejob");
        TaskDefinition save = taskDefinitionRepository.save(taskDefinition);
        System.out.println(save.getTaskName());
    }

    /**
     * 注册应用
     *
     * @throws URISyntaxException
     */
    @Test
    public void appRegistrationRepository() throws URISyntaxException {

        AppRegistration appRegistration = new AppRegistration();
        appRegistration.setDefaultVersion(true);
        appRegistration.setType(ApplicationType.task);
        appRegistration.setUri(new URI("file:///C:/Users/liwen/Desktop/jrx/taskjar/b01-simple-job-1.0.0.jar"));
        appRegistration.setName("simplejob");
        AppRegistration save = appRegistrationRepository.save(appRegistration);
        System.out.println(JSON.toJSONString(save));
        AppRegistration simplejob = appRegistrationRepository.findAppRegistrationByNameAndTypeAndDefaultVersionIsTrue("simplejob", ApplicationType.task);

        System.out.println(JSON.toJSONString(simplejob));
    }

    /*********************************************/


    @Test
    public void execBatchTask() {
        long executeTask = taskService.executeTask("simple_job", new HashMap<String, String>() {{
//            put("app.test", "1");//deployment property keys starting with 'app.', 'deployer.' or, 'scheduler.' allowed, got 'param.test'
            /**
             * 测参数可以选择执行任务的launcher
             * 见配置项中的平台配置，配置项没有则为default
             */
//            put("spring.cloud.dataflow.task.platformName", "default");
            put("spring.cloud.dataflow.task.platformName", "local");
        }}, new ArrayList<String>() {{
            ///执行任务传入的参数
            add("param = test");//此参数表示执行这个任务时指定平台是什么，如果和task_deployment中的不一致任务无法执行
        }});
        System.out.println("******************************************************");

        System.out.println(executeTask);
    }


    @Test
    public void taskDeploymentRepository() {
        TaskDeployment taskDeployment = new TaskDeployment();
        taskDeployment.setPlatformName("local");
        taskDeployment.setTaskDefinitionName("simple_job");
        taskDeployment.setTaskDeploymentId("32193298948291sss");
        taskDeploymentRepository.save(taskDeployment);
        TaskDeployment byTaskDeploymentId = taskDeploymentRepository.findByTaskDeploymentId("32193298948291");

        System.out.println(JSON.toJSONString(byTaskDeploymentId));
    }

    @Test
    public void testLauncher() throws URISyntaxException, MalformedURLException, NoSuchFieldException, IllegalAccessException {
        String appData = "{\"commandlineArguments\":[\"1234\",\"--spring.cloud.task.executionid=52\"],\"definition\":{\"name\":\"simple_job\",\"properties\":{\"spring.datasource.username\":\"root\",\"spring.cloud.task.name\":\"simple_job\",\"spring.datasource.url\":\"jdbc:mysql://192.168.42.136:3306/batch_schedule_master?useSSL=false&useUnicode=true&characterEncoding=UTF-8\",\"spring.datasource.driverClassName\":\"com.mysql.jdbc.Driver\",\"spring.datasource.password\":\"root\"}},\"deploymentProperties\":{},\"resource\":{\"description\":\"URL [file:/C:/Users/liwen/Desktop/jrx/taskjar/b01-simple-job-1.0.0.jar]\",\"file\":\"C:\\\\Users\\\\liwen\\\\Desktop\\\\jrx\\\\taskjar\\\\b01-simple-job-1.0.0.jar\",\"filename\":\"b01-simple-job-1.0.0.jar\",\"inputStream\":{},\"open\":false,\"readable\":true,\"uRI\":\"file:/C:/Users/liwen/Desktop/jrx/taskjar/b01-simple-job-1.0.0.jar\",\"uRL\":\"file:/C:/Users/liwen/Desktop/jrx/taskjar/b01-simple-job-1.0.0.jar\"}}";
        AppDeploymentRequest appDeploymentRequest = JSON.parseObject(appData, AppDeploymentRequest.class);
        Resource resource = new UrlResource(new URI("file:/C:/Users/liwen/Desktop/jrx/taskjar/b01-simple-job-1.0.0.jar"));
        AppDeploymentRequest app = new AppDeploymentRequest(appDeploymentRequest.getDefinition(), resource);
        LocalDeployerProperties localDeployerProperties = new LocalDeployerProperties();
        localDeployerProperties.setJavaOpts("-Xms128M -Xmx128M");
        localDeployerProperties.setWorkingDirectoriesRoot("C:\\\\Users\\\\liwen\\\\Desktop\\\\jrx\\\\worktask");
        localDeployerProperties.setEnvVarsToInherit(new String[]{"TMP", "LANG", "LANGUAGE", "LC_.*", "PATH"});
        LocalTaskLauncher localTaskLauncher = new LocalTaskLauncher(localDeployerProperties);
        String launch = localTaskLauncher.launch(app);
        System.out.println(launch);
    }

    @Test
    public void testLog() {
        System.out.println("******************************************************");

        log.info("打印infor日志");
    }



    @Test
    public void getResource() throws MalformedURLException, FileNotFoundException {
//        Resource resource = ResourceUtils.getResource("file:/C:/Users/liwen/Desktop/jrx/taskjar/b01-simple-job-1.0.0.jar1", null);
//        Resource resource = ResourceUtils.getResource("http://localhost:8081/download", null);
//        URL url = ResourceUtils.getURL("http://localhost:8081/download");
//        String appData = "{\"commandlineArguments\":[\"1234\",\"--spring.cloud.task.executionid=52\"],\"definition\":{\"name\":\"simple_job\",\"properties\":{\"spring.datasource.username\":\"root\",\"spring.cloud.task.name\":\"simple_job\",\"spring.datasource.url\":\"jdbc:mysql://192.168.42.136:3306/batch_schedule_master?useSSL=false&useUnicode=true&characterEncoding=UTF-8\",\"spring.datasource.driverClassName\":\"com.mysql.jdbc.Driver\",\"spring.datasource.password\":\"root\"}},\"deploymentProperties\":{},\"resource\":{\"description\":\"URL [file:/C:/Users/liwen/Desktop/jrx/taskjar/b01-simple-job-1.0.0.jar]\",\"file\":\"C:\\\\Users\\\\liwen\\\\Desktop\\\\jrx\\\\taskjar\\\\b01-simple-job-1.0.0.jar\",\"filename\":\"b01-simple-job-1.0.0.jar\",\"inputStream\":{},\"open\":false,\"readable\":true,\"uRI\":\"file:/C:/Users/liwen/Desktop/jrx/taskjar/b01-simple-job-1.0.0.jar\",\"uRL\":\"file:/C:/Users/liwen/Desktop/jrx/taskjar/b01-simple-job-1.0.0.jar\"}}";
//        AppDeploymentRequest appDeploymentRequest = JSON.parseObject(appData, AppDeploymentRequest.class);
//        AppDeploymentRequest app = new AppDeploymentRequest(appDeploymentRequest.getDefinition(),resource);
//        LocalDeployerProperties localDeployerProperties = new LocalDeployerProperties();
//        localDeployerProperties.setJavaOpts("-Xms128M -Xmx128M");
//        localDeployerProperties.setWorkingDirectoriesRoot("C:\\\\Users\\\\liwen\\\\Desktop\\\\jrx\\\\worktask");
//        localDeployerProperties.setEnvVarsToInherit(new String[]{"TMP", "LANG", "LANGUAGE", "LC_.*","PATH"});
//        LocalTaskLauncher localTaskLauncher =new LocalTaskLauncher(localDeployerProperties);
//        String launch = localTaskLauncher.launch(app);
//        System.out.println(launch);
    }
}