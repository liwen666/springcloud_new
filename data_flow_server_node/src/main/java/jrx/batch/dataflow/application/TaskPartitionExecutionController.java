package jrx.batch.dataflow.application;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import jrx.batch.dataflow.domain.config.system.PropertiesThreadLocalHolder;
import jrx.batch.dataflow.domain.enums.BatchNodePropertiesKey;
import jrx.batch.dataflow.domain.service.ITaskDefinitionsService;
import jrx.batch.dataflow.domain.service.ITaskExecutionService;
import jrx.batch.dataflow.infrastructure.model.TaskDefinitions;
import jrx.batch.dataflow.infrastructure.model.TaskExecution;
import jrx.batch.dataflow.util.BatachNodeContextUtils;
import jrx.batch.dataflow.util.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.dataflow.server.service.TaskExecutionService;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 前端控制器
 * 自定义
 * </p>
 *
 * @author schedule
 * @since 2019-11-12
 */
@Lazy
@Slf4j
@RestController
@RequestMapping("/batch/task")
public class TaskPartitionExecutionController {
    @Autowired
    private TaskExecutionService taskExecutionService;

    @Autowired
    private ITaskDefinitionsService taskDefinitionsService;

    @Autowired
    private ITaskExecutionService taskExecutionServiceImpl;


    @RequestMapping(value = "/execute", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public JsonResult executeTaskPartition(@RequestParam("parentId") String parentId, @RequestParam("taskDefine") String taskDefine, @RequestPart("properties") Map<String, String> properties, @RequestPart("arguments") List<String> arguments) {
        long executeTask;
        try {
            log.info("====开始执行任务： parentId:{},taskDefine:{},properties:{},argumes:{}",parentId,taskDefine,properties,arguments);
            arguments.add("--spring.cloud.task.parentExecutionId="+parentId);
//            String port =
//            arguments.add("--server.port="+port);
             executeTask = taskExecutionService.executeTask(taskDefine, properties, arguments);
            taskExecutionServiceImpl.update(TaskExecution.builder().parentExecutionId(Long.parseLong(parentId)).build(), Wrappers.<TaskExecution>lambdaUpdate().eq(TaskExecution::getTaskExecutionId,executeTask));
        } catch (Exception e) {
            e.printStackTrace();
           return JsonResult.error(e.getMessage());
        } finally {

        }
        return JsonResult.success(executeTask);
    }




    public void test() {
        System.out.println("加载完成");
    }
}
