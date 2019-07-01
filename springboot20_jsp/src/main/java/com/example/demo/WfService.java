//package com.example.demo;
//
//import com.hq.bpmn.bpmnanalysis.bean.PiAndTaskListDomain;
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Map;
//
///**
// * 工作流server接口服务
// *
// * @author xinre
// * @date 2019/6/11 16:29
// */
////@ConditionalOnProperty(prefix = "hqbpmn.server", name = "enable", matchIfMissing = false)
////@FeignClient(name = "wfService", url = "${hqbpmn.server.address:http://127.0.0.1}")
//@FeignClient(name = "wfService", url = "http://localhost:9090")
//@RequestMapping("/wf/restapi/v1")
//public interface WfService {
//
//    //////////////////// model //////////////////////////////////////////////////
//
//    /**
//     * 获取模板信息
//     *
//     * @param zoning 区划
//     * @param year   年度
//     * @param appId  系统标识
//     * @return 模板信息
//     */
//    @GetMapping(value = "/model/{zoning}/{year}/{appid}/all")
//    @ResponseBody
//    Map<String, Object> getModelInfo(@PathVariable(name = "zoning") String zoning, @PathVariable(name = "year") String year, @PathVariable(name = "appid") String appId);
//
//
//    //////////////////// pd    //////////////////////////////////////////////////
//
//    //////////////////// pi    //////////////////////////////////////////////////
//
//    //////////////////// 流程定义模板服务接口   //////////////////////////////////////////////////
//    /*@PostMapping(value = "/selectTemplate/{appId}")
//    Map<String, Object> initBpmnTemplateDef(@PathVariable(name = "appId") String appId);*/
//
//    /**
//     * 查询流程模板
//     *
//     * @param id 模板Id
//     * @return 流程模板信息
//     */
//    @PostMapping(value = "/templateDef/{id}")
//    @ResponseBody
//    Map<String, Object> getTemplateDefById(@PathVariable(name = "id") String id);
//
//    /**
//     * 查询流程模板信息
//     *
//     * @param deploymentId 部署Id
//     * @return 流程模板信息
//     */
//    @PostMapping(value = "/templateDef/deployment/{id}")
//    @ResponseBody
//    Map<String, Object> getTemplateDefByDeploymentId(@PathVariable(value = "id") String deploymentId);
//
//    /**
//     * 查询部署的模板信息
//     *
//     * @param bpmnType 流程类型
//     * @return 流程模板信息
//     */
//    @PostMapping(value = "/templateDef/bpmnType/{code}")
//    @ResponseBody
//    Map<String, Object> listTemplateDefByBpmnType(@PathVariable(value = "code") String bpmnType);
//
//    /**
//     * 调用服务端Dao服务
//     * map   的方法名  methodName
//     * map    方法参数  methodParam
//     */
//    @PostMapping(value = "/getTempDaoData")
//    @ResponseBody
//    Map<?, ?> getTempDaoData(Map<String, Object> getTempDaoData);
//
//    /**
//     * 调用服务端Dao服务
//     * map   的方法名  methodName
//     * map    方法参数  methodParam
//     */
//    @PostMapping(value = "/templateDef/timingSynLog")
//    @ResponseBody
//    Map<?, ?> timingSynLog(PiAndTaskListDomain piAndTaskList);
//}
