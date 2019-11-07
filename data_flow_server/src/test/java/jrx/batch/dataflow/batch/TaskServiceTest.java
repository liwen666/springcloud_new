package jrx.batch.dataflow.batch;

import com.alibaba.fastjson.JSON;
import jrx.batch.dataflow.SpringbootDataflowServerApplication;
import lombok.extern.java.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.dataflow.core.TaskDeployment;
import org.springframework.cloud.dataflow.server.repository.TaskDeploymentRepository;
import org.springframework.cloud.dataflow.server.service.TaskExecutionService;
import org.springframework.cloud.dataflow.server.service.impl.DefaultTaskExecutionService;
import org.springframework.cloud.deployer.spi.core.AppDeploymentRequest;
import org.springframework.cloud.deployer.spi.local.LocalDeployerProperties;
import org.springframework.cloud.deployer.spi.local.LocalTaskLauncher;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;

@Log
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringbootDataflowServerApplication.class)
public class TaskServiceTest {
    @Autowired
    private TaskExecutionService taskService;

    @Autowired
    TaskDeploymentRepository taskDeploymentRepository;

    @Test
    public void execBatchTask() {
        long demo1 = taskService.executeTask("simple_job", new HashMap<String, String>() {{
//            put("app.test", "1");//deployment property keys starting with 'app.', 'deployer.' or, 'scheduler.' allowed, got 'param.test'
//            put("param.test", "1");
        }}, new ArrayList<String>() {{
            add("1234");///执行任务传入的参数
        }});
        System.out.println(demo1);
    }


    @Test
    public void taskDeploymentRepository() {
        TaskDeployment byTaskDeploymentId = taskDeploymentRepository.findByTaskDeploymentId("00000092299");
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