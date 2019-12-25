package jrx.batch.dataflow.application;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import feign.Param;
import feign.RequestLine;
import javafx.concurrent.Task;
import jrx.batch.dataflow.domain.service.ITaskDefinitionsService;
import jrx.batch.dataflow.infrastructure.model.TaskDefinitions;
import jrx.batch.dataflow.util.BatachNodeContextUtils;
import jrx.batch.dataflow.util.JrxRegxUtil;
import jrx.batch.dataflow.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.dataflow.core.ApplicationType;
import org.springframework.cloud.dataflow.server.controller.TaskDefinitionController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
@RequestMapping("/infrastructure/task-definitions")
public class TaskDefinitionsController {
    @Autowired
    private TaskDefinitionController taskDefinitionController;
    @Autowired
    private ITaskDefinitionsService taskDefinitionsService;


    @GetMapping(value = "/list")
    public JsonResult appListAll() {
        List<TaskDefinitions> list = taskDefinitionsService.list();
        String nodeName = BatachNodeContextUtils.getNodeName();
        if (null == nodeName) {
            return JsonResult.error("节点配置节点名称为空，请检查配置");
        }
        List<TaskDefinitions> collect = list.stream().map(e -> {
            e.setDefinitionName(nodeName + ":" + e.getDefinitionName());
            return e;
        }).collect(Collectors.toList());
        return JsonResult.success(collect);
    }


    @PostMapping("/http_task")
    public JsonResult saveNodeTask(@Param("name") String name, @Param("definition") String definition) {
        boolean accept = JrxRegxUtil.isAcceptTaskdefineName(name);
        if (!accept) {
            throw new RuntimeException("该任务定义不符合要求，需要以字母开头 taskDefingName:" + name);
        }
        TaskDefinitions taskDefinitions = taskDefinitionsService.getOne(Wrappers.<TaskDefinitions>lambdaQuery().eq(TaskDefinitions::getDefinitionName, name));
        if (null == taskDefinitions) {
            return JsonResult.success(taskDefinitionsService.save(TaskDefinitions.builder().definition(definition).definitionName(name).build()));
        }
        throw new RuntimeException("该任务已经注册不能重复注册 taskDefingName:" + name);
    }

}
