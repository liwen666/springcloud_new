package jrx.batch.dataflow.domain.config.batch;

import jrx.batch.dataflow.domain.config.system.PropertiesThreadLocalHolder;
import jrx.batch.dataflow.domain.enums.BatchNodePropertiesKey;
import jrx.batch.dataflow.domain.service.ITaskExecutionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.dataflow.audit.service.AuditRecordService;
import org.springframework.cloud.dataflow.core.*;
import org.springframework.cloud.dataflow.rest.util.ArgumentSanitizer;
import org.springframework.cloud.dataflow.rest.util.DeploymentPropertiesUtils;
import org.springframework.cloud.dataflow.server.job.LauncherRepository;
import org.springframework.cloud.dataflow.server.repository.TaskDeploymentRepository;
import org.springframework.cloud.dataflow.server.service.TaskExecutionCreationService;
import org.springframework.cloud.dataflow.server.service.TaskExecutionInfoService;
import org.springframework.cloud.dataflow.server.service.impl.DefaultTaskExecutionService;
import org.springframework.cloud.dataflow.server.service.impl.TaskAppDeploymentRequestCreator;
import org.springframework.cloud.dataflow.server.service.impl.TaskExecutionInformation;
import org.springframework.cloud.deployer.spi.core.AppDeploymentRequest;
import org.springframework.cloud.deployer.spi.task.TaskLauncher;
import org.springframework.cloud.task.repository.TaskExecution;
import org.springframework.cloud.task.repository.TaskRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * <p>
 * 描述
 * </p>
 *
 * @author tx
 * @since 2019/5/26 23:40
 */
@Service("taskExecutionService")
@Transactional
@Slf4j
public class JRXTaskExecutionService extends DefaultTaskExecutionService {
    private final LauncherRepository launcherRepository;
    private final TaskExecutionCreationService taskExecutionRepositoryService;
    protected final AuditRecordService auditRecordService;
    private final TaskRepository taskRepository;
    private final TaskExecutionInfoService taskExecutionInfoService;
    private final TaskDeploymentRepository taskDeploymentRepository;
    private final ArgumentSanitizer argumentSanitizer = new ArgumentSanitizer();
    private final TaskAppDeploymentRequestCreator taskAppDeploymentRequestCreator;

    public JRXTaskExecutionService(LauncherRepository launcherRepository, AuditRecordService auditRecordService, TaskRepository taskRepository, TaskExecutionInfoService taskExecutionInfoService, TaskDeploymentRepository taskDeploymentRepository, TaskExecutionCreationService taskExecutionRepositoryService, TaskAppDeploymentRequestCreator taskAppDeploymentRequestCreator) {

        super(launcherRepository, auditRecordService, taskRepository, taskExecutionInfoService, taskDeploymentRepository, taskExecutionRepositoryService, taskAppDeploymentRequestCreator);
        this.launcherRepository = launcherRepository;
        this.taskExecutionRepositoryService = taskExecutionRepositoryService;
        this.auditRecordService = auditRecordService;
        this.taskRepository = taskRepository;
        this.taskExecutionInfoService = taskExecutionInfoService;
        this.taskDeploymentRepository = taskDeploymentRepository;
        this.taskAppDeploymentRequestCreator = taskAppDeploymentRequestCreator;
    }


    @Override
    public long executeTask(String taskName, Map<String, String> taskDeploymentProperties, List<String> commandLineArgs) {
        log.info("======开始执行任务 taskName: {}", taskName);
        String platformName = (String) taskDeploymentProperties.get("spring.cloud.dataflow.task.platformName");
        if (!StringUtils.hasText(platformName)) {
            platformName = "default";
        }

        if (taskDeploymentProperties.containsKey("spring.cloud.dataflow.task.platformName")) {
            taskDeploymentProperties.remove("spring.cloud.dataflow.task.platformName");
        }

        DeploymentPropertiesUtils.validateDeploymentProperties(taskDeploymentProperties);
        TaskLauncher taskLauncher = findTaskLauncher(platformName);
        TaskDeployment existingTaskDeployment = taskDeploymentRepository.findTopByTaskDefinitionNameOrderByCreatedOnAsc(taskName);
        if (existingTaskDeployment != null && !existingTaskDeployment.getPlatformName().equals(platformName)) {
            throw new IllegalStateException(String.format("Task definition [%s] has already been deployed on platform [%s].  Requested to deploy on platform [%s].", taskName, existingTaskDeployment.getPlatformName(), platformName));
        } else {
            TaskExecutionInformation taskExecutionInformation = this.taskExecutionInfoService.findTaskExecutionInformation(taskName, taskDeploymentProperties);
            TaskExecution taskExecution = this.taskExecutionRepositoryService.createTaskExecution(taskName);
            AppDeploymentRequest request = this.taskAppDeploymentRequestCreator.createRequest(taskExecution, taskExecutionInformation, commandLineArgs, platformName);
            String id = taskLauncher.launch(request);
            if (!StringUtils.hasText(id)) {
                throw new IllegalStateException("Deployment ID is null for the task:" + taskName);
            } else {
                this.updateExternalExecutionId(taskExecution.getExecutionId(), id);
                TaskDeployment taskDeployment = new TaskDeployment();
                taskDeployment.setTaskDeploymentId(id);
                taskDeployment.setPlatformName(platformName);
                taskDeployment.setTaskDefinitionName(taskName);
                this.taskDeploymentRepository.save(taskDeployment);
                this.auditRecordService.populateAndSaveAuditRecordUsingMapData(AuditOperationType.TASK, AuditActionType.DEPLOY, taskExecutionInformation.getTaskDefinition().getName(), this.getAudited(taskExecutionInformation.getTaskDefinition(), taskExecutionInformation.getTaskDeploymentProperties(), request.getCommandlineArguments()));
                return taskExecution.getExecutionId();
            }
        }
    }

    private TaskLauncher findTaskLauncher(String platformName) {
        Launcher launcher = launcherRepository.findByName(platformName);
        if (launcher == null) {
            List<String> launcherNames = (List) StreamSupport.stream(this.launcherRepository.findAll().spliterator(), false).map(Launcher::getName).collect(Collectors.toList());
            throw new IllegalStateException(String.format("No Launcher found for the platform named '%s'.  Available platform names are %s", platformName, launcherNames));
        } else {
            TaskLauncher taskLauncher = launcher.getTaskLauncher();
            if (taskLauncher == null) {
                throw new IllegalStateException(String.format("No TaskLauncher found for the platform named '%s'", platformName));
            } else {
                return taskLauncher;
            }
        }
    }

    private Map<String, Object> getAudited(TaskDefinition taskDefinition, Map<String, String> taskDeploymentProperties, List<String> commandLineArgs) {
        Map<String, Object> auditedData = new HashMap(3);
        auditedData.put("taskDefinitionDslText", this.argumentSanitizer.sanitizeTaskDsl(taskDefinition));
        auditedData.put("taskDeploymentProperties", this.argumentSanitizer.sanitizeProperties(taskDeploymentProperties));
        auditedData.put("commandLineArgs", this.argumentSanitizer.sanitizeArguments(commandLineArgs));
        return auditedData;
    }
}
