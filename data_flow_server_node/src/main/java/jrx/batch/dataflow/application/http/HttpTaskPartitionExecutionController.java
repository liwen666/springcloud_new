package jrx.batch.dataflow.application.http;


import jrx.batch.dataflow.domain.config.batch.JrxBatchProperties;
import jrx.batch.dataflow.domain.constant.TaskContants;
import jrx.batch.dataflow.domain.service.IHttpBatchService;
import jrx.batch.dataflow.domain.service.ITaskDefinitionsService;
import jrx.batch.dataflow.domain.service.remote.JobExecuteClient;
import jrx.batch.dataflow.domain.service.remote.RemoteClientHolder;
import jrx.batch.dataflow.util.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.dataflow.core.ApplicationType;
import org.springframework.cloud.dataflow.server.service.TaskExecutionService;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

/**
 * <p>
 *  描述
 * </p>
 *
 * @author lw
 * @since  2020/3/6 21:04
 */
@Lazy
@Slf4j
@RestController
@RequestMapping("/http/batch/task")
public class HttpTaskPartitionExecutionController {
    @Autowired
    private TaskExecutionService taskExecutionService;

    @Autowired
    private ITaskDefinitionsService taskDefinitionsService;

    @Autowired
    private RemoteClientHolder remoteClientHolder;
    @Autowired
    private IHttpBatchService httpBatchService;


    @PostMapping(value = "/execute", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public JsonResult executeTaskPartition(@RequestParam("parentId") String parentId, @RequestParam("taskDefine") String taskDefine, @RequestPart("properties") Map<String, String> properties, @RequestPart("arguments") List<String> arguments) {
        log.info("通过http执行job parentId:{},  taskDefine:{},  properties:{},  arguments:{}", parentId, taskDefine, properties, arguments);
        /**
         * 创建任务
         */
        String serverPortParam = null;
        for (String param : arguments) {
            if (param.startsWith("--server.port=")) {
                serverPortParam = param;
            }
        }
        if (null == serverPortParam) {
            log.error("请配置jobServer 服务启动端口参数 如：[--server.port=xxxx]");
            return JsonResult.error("请配置jobServer 服务启动端口参数 如：[--server.port=xxxx]");
        }
        long taskExecutionId = 0;
        taskExecutionId = httpBatchService.createTaskExecution(taskDefine, parentId);
        arguments.add("--jrx.batch.job.server.batch_datasource=" + JrxBatchProperties.properties.get("JOB_SERVER_DB"));//指定服务启动数据源
        arguments.add("--spring.cloud.nacos.discovery.service=" + taskDefine);//指定服务名
        arguments.add("--spring.cloud.task.executionid=" + taskExecutionId);//关联任务
        JobExecuteClient jobExecuteClient;
        try {
            /**
             * 获取客户端
             */
            jobExecuteClient = remoteClientHolder.getClient(taskDefine, JobExecuteClient.class);
        } catch (Throwable e) {
            log.info("-----job执行异常 jobService:{} ,errorMsg:{}", taskDefine, e.getMessage());
            e.printStackTrace();
            /**
             * 启动服务
             */
            log.info("-----准备自动启动jobService服务 启动配置 arguments：{}", arguments);
            properties.put("app.type", ApplicationType.app.name());
            long executeTask = taskExecutionService.executeTask(taskDefine, properties, arguments);
            try {
                Thread.sleep(TaskContants.LAUNCH_SLEEP_DELAY);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            jobExecuteClient = remoteClientHolder.getClient(taskDefine, JobExecuteClient.class);
        }
        httpBatchService.execeJob(parentId, taskExecutionId, arguments, jobExecuteClient);
        return JsonResult.success(taskExecutionId);
    }

}
