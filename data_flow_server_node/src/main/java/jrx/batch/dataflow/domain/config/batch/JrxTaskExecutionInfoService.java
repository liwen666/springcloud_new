//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package jrx.batch.dataflow.domain.config.batch;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.cloud.dataflow.core.AllPlatformsTaskExecutionInformation;
import org.springframework.cloud.dataflow.core.AppRegistration;
import org.springframework.cloud.dataflow.core.ApplicationType;
import org.springframework.cloud.dataflow.core.TaskDefinition;
import org.springframework.cloud.dataflow.core.TaskPlatform;
import org.springframework.cloud.dataflow.core.dsl.TaskNode;
import org.springframework.cloud.dataflow.core.dsl.TaskParser;
import org.springframework.cloud.dataflow.registry.service.AppRegistryService;
import org.springframework.cloud.dataflow.server.job.LauncherRepository;
import org.springframework.cloud.dataflow.server.repository.NoSuchTaskDefinitionException;
import org.springframework.cloud.dataflow.server.repository.TaskDefinitionRepository;
import org.springframework.cloud.dataflow.server.service.TaskExecutionInfoService;
import org.springframework.cloud.dataflow.server.service.impl.TaskConfigurationProperties;
import org.springframework.cloud.dataflow.server.service.impl.TaskExecutionInformation;
import org.springframework.cloud.dataflow.server.service.impl.TaskServiceUtils;
import org.springframework.cloud.task.repository.TaskExplorer;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service("taskExecutionInfoService")
public class JrxTaskExecutionInfoService implements TaskExecutionInfoService {
    private final DataSourceProperties dataSourceProperties;
    private final AppRegistryService appRegistryService;
    private final TaskExplorer taskExplorer;
    private final TaskDefinitionRepository taskDefinitionRepository;
    private final TaskConfigurationProperties taskConfigurationProperties;
    private final LauncherRepository launcherRepository;
    private final List<TaskPlatform> taskPlatforms;

    public JrxTaskExecutionInfoService(DataSourceProperties dataSourceProperties, AppRegistryService appRegistryService, TaskExplorer taskExplorer, TaskDefinitionRepository taskDefinitionRepository, TaskConfigurationProperties taskConfigurationProperties, LauncherRepository launcherRepository, List<TaskPlatform> taskPlatforms) {
        Assert.notNull(dataSourceProperties, "DataSourceProperties must not be null");
        Assert.notNull(appRegistryService, "AppRegistryService must not be null");
        Assert.notNull(taskDefinitionRepository, "TaskDefinitionRepository must not be null");
        Assert.notNull(taskExplorer, "TaskExplorer must not be null");
        Assert.notNull(taskConfigurationProperties, "taskConfigurationProperties must not be null");
        Assert.notNull(launcherRepository, "launcherRepository must not be null");
        Assert.notEmpty(taskPlatforms, "taskPlatform must not be empty or null");
        this.dataSourceProperties = dataSourceProperties;
        this.appRegistryService = appRegistryService;
        this.taskExplorer = taskExplorer;
        this.taskDefinitionRepository = taskDefinitionRepository;
        this.taskConfigurationProperties = taskConfigurationProperties;
        this.launcherRepository = launcherRepository;
        this.taskPlatforms = taskPlatforms;
    }

    public TaskExecutionInformation findTaskExecutionInformation(String taskName, Map<String, String> taskDeploymentProperties) {
        Assert.hasText(taskName, "The provided taskName must not be null or empty.");
        Assert.notNull(taskDeploymentProperties, "The provided runtimeProperties must not be null.");
        TaskExecutionInformation retData = new TaskExecutionInformation();
        retData.setTaskDeploymentProperties(taskDeploymentProperties);
        TaskDefinition taskDefinition = (TaskDefinition)this.taskDefinitionRepository.findById(taskName).orElseThrow(() -> {
            return new NoSuchTaskDefinitionException(taskName);
        });
        TaskParser taskParser = new TaskParser(taskDefinition.getName(), taskDefinition.getDslText(), true, true);
        TaskNode taskNode = taskParser.parse();
        if (taskNode.isComposed()) {
            taskDefinition = new TaskDefinition(taskDefinition.getName(), TaskServiceUtils.createComposedTaskDefinition(taskNode.toExecutableDSL(), this.taskConfigurationProperties));
            retData.setTaskDeploymentProperties(TaskServiceUtils.establishComposedTaskProperties(taskDeploymentProperties, taskNode));
        }

        taskDefinition = TaskServiceUtils.updateTaskProperties(taskDefinition, this.dataSourceProperties);
        AppRegistration appRegistration;

        if(ApplicationType.app.name().equals(taskDeploymentProperties.get("app.type"))){
            appRegistration = this.appRegistryService.find(taskDefinition.getRegisteredAppName(), ApplicationType.app);
        }else{
            appRegistration = this.appRegistryService.find(taskDefinition.getRegisteredAppName(), ApplicationType.task);
        }

        Assert.notNull(appRegistration, "Unknown task app: " + taskDefinition.getRegisteredAppName());
        retData.setTaskDefinition(taskDefinition);
        retData.setComposed(taskNode.isComposed());
        retData.setAppResource(this.appRegistryService.getAppResource(appRegistration));
        retData.setMetadataResource(this.appRegistryService.getAppMetadataResource(appRegistration));
        return retData;
    }

    public AllPlatformsTaskExecutionInformation findAllPlatformTaskExecutionInformation() {
        return new AllPlatformsTaskExecutionInformation(this.taskPlatforms);
    }
}
