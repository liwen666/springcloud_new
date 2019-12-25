package jrx.batch.dataflow.domain.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import jrx.batch.dataflow.domain.enums.CodeEnums;
import jrx.batch.dataflow.domain.service.IHttpBatchService;
import jrx.batch.dataflow.domain.service.ITaskExecutionService;
import jrx.batch.dataflow.domain.service.ITaskTaskBatchService;
import jrx.batch.dataflow.domain.service.remote.JobExecuteClient;
import jrx.batch.dataflow.domain.service.remote.RemoteClient;
import jrx.batch.dataflow.domain.service.remote.RemoteClientHolder;
import jrx.batch.dataflow.infrastructure.model.TaskExecution;
import jrx.batch.dataflow.util.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.BatchStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.dataflow.server.service.TaskExecutionCreationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 描述
 * </p>
 *
 * @author lw
 * @since 2019/5/26 23:40
 */
@Slf4j
@Service
@Transactional
public class HttpBatchServiceImpl implements IHttpBatchService {
    @Autowired
    private ITaskExecutionService taskExecutionServiceImpl;
    @Autowired
    private TaskExecutionCreationService taskExecutionRepositoryService;


    @Autowired
    private ITaskExecutionService taskExecutionService;
    @Autowired
    private ITaskTaskBatchService taskTaskBatchService;


    @Override
    public long createTaskExecution(String taskName, String parentId) {

        org.springframework.cloud.task.repository.TaskExecution taskExecution = taskExecutionRepositoryService.createTaskExecution(taskName);
        TaskExecution build = TaskExecution.builder().taskName(taskExecution.getTaskName())
                .parentExecutionId(Long.parseLong(parentId))
                .taskExecutionId(taskExecution.getExecutionId())
                .startTime(LocalDateTime.now()).build();
        taskExecutionServiceImpl.update(build, Wrappers.<TaskExecution>lambdaUpdate().eq(TaskExecution::getTaskExecutionId, build.getTaskExecutionId()));
        return taskExecution.getExecutionId();
    }

    @Override
    public void execeJob(String parentId, long taskExecutionId, List<String> arguments, RemoteClient remoteClient) {
        JsonResult result = null;
        try {
            result = ((JobExecuteClient) remoteClient).executeRemoteJob(arguments);
        } catch (Exception e) {
            e.printStackTrace();
            checkTask(taskExecutionId, parentId);
        }
        //失败做一下查询确认
        if (null == result || result.getCode() == CodeEnums.EROOR.code()) {
            log.error("===job执行失败，errorMsg:{},准备做失败数据检查。",result.getMessage());
            checkTask(taskExecutionId, parentId);
        }
    }


    private void checkTask(long taskExecutionId, String parentId) {
        List<Map> maps = taskTaskBatchService.listJobById(parentId);
        if (CollectionUtils.isEmpty(maps)) {
            log.error("未查询到任务对应的job信息：parentId:{}", parentId);
        }
        Map map = maps.get(0);
        String status = (String) map.get("STATUS");
        if (BatchStatus.COMPLETED.name().equals(status)) {
            log.info("任务对应的job已经完成，taskExecutionId:{} parentId:{},jobId:{}", taskExecutionId, parentId, map.get("JOB_EXECUTION_ID"));
            TaskExecution task = TaskExecution.builder().
                    endTime(LocalDateTime.now())
                    .exitCode(0)
                    .errorMessage(null)
                    .build();
            taskExecutionService.update(task, Wrappers.<TaskExecution>lambdaUpdate().eq(TaskExecution::getTaskExecutionId, taskExecutionId));
        }
    }
}
