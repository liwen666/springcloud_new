package jrx.batch.dataflow.application;


import jrx.batch.dataflow.domain.service.ITaskDefinitionsService;
import jrx.batch.dataflow.infrastructure.model.TaskDefinitions;
import jrx.batch.dataflow.util.BatachNodeContextUtils;
import jrx.batch.dataflow.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
* <p>
    *  前端控制器
   自定义
    aaaaaaaaaaaaaaaaaaaaa
    * </p>
*
* @author schedule
* @since 2019-11-12
*/
@RestController
@RequestMapping("/infrastructure/task-definitions")
 public class TaskDefinitionsController {

 @Autowired
 private ITaskDefinitionsService taskDefinitionsService;


 @GetMapping(value = "/list")
 public JsonResult appListAll()  {
  List<TaskDefinitions> list = taskDefinitionsService.list();
  String nodeName = BatachNodeContextUtils.getNodeName();
  if (null==nodeName){
   return JsonResult.error("节点配置节点名称为空，请检查配置");
  }
  List<TaskDefinitions> collect = list.stream().map(e -> {
   e.setDefinitionName(nodeName + ":" + e.getDefinitionName());
   return e;
  }).collect(Collectors.toList());
  return JsonResult.success(collect);
 }

}
