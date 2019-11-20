package jrx.batch.dataflow.application;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import jrx.batch.dataflow.domain.service.ITaskDefinitionsService;
import jrx.batch.dataflow.domain.service.ITaskExecutionService;
import jrx.batch.dataflow.domain.service.ITaskTaskBatchService;
import jrx.batch.dataflow.infrastructure.model.TaskDefinitions;
import jrx.batch.dataflow.infrastructure.model.TaskExecution;
import jrx.batch.dataflow.util.BatachNodeContextUtils;
import jrx.batch.dataflow.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 前端控制器
 * 自定义
 * aaaaaaaaaaaaaaaaaaaaa
 * </p>
 *
 * @author schedule
 * @since 2019-11-12
 */
@RestController
@RequestMapping("/infrastructure/check")
public class TaskJobStatusCheckController {



    @Autowired
    private ITaskDefinitionsService taskDefinitionsService;
    @Autowired
    private ITaskTaskBatchService taskTaskBatchService;
    @Autowired
    private ITaskExecutionService taskExecutionService;
    /**s
     * 根据任务执行调度 parentId查询 job数据
     * @param taskExecuteId
     * @return
     */
    @GetMapping(value = "/status/task/{parentId}")
    public JsonResult taskExecuteId(@PathVariable("parentId") String taskExecuteId) {
        List<Map> maps = taskTaskBatchService.listJobById(taskExecuteId);
        return JsonResult.success(maps);
    }

    /**s
     * 根据任务执行调度 parentId查询 task数据
     * @return
     */
    @GetMapping(value = "/execution/task/{parentId}")
    public JsonResult executionTask(@PathVariable("parentId") String parentId) {
        TaskExecution one = taskExecutionService.getOne(Wrappers.<TaskExecution>lambdaQuery().eq(TaskExecution::getParentExecutionId, parentId));
        return JsonResult.success(one);
    }

}
