//package com.example.demo;
//
//import com.alibaba.fastjson.JSON;
//import com.hq.bpmn.common.bean.ProcessResult;
//import com.hq.bpmn.common.util.DataConversion;
//import com.hq.bpmn.rest.service.ITemplateRestService;
//import com.hq.bpmn.templatedef.bean.BpmnTemplateDef;
//import com.hq.bpmn.templatedef.dao.BpmnTemplateDefDao;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.lang.reflect.Method;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/wf/restapi/v1/")
//public class RestTemplateDefController {
//    @Autowired
//    private ITemplateRestService templateRestService;
//    @Autowired
//    private BpmnTemplateDefDao bpmnTemplateDefDao;
//    private Method[] bpmnTemplateDaoMethods;
//
//    /**
//     * 查询业务系统对应的所有模板
//     */
//    @PostMapping(value = "/templateDef/{id}")
//    @ResponseBody
//    public ProcessResult<BpmnTemplateDef> findTempDefById(@PathVariable("id") String id) {
//        ProcessResult<BpmnTemplateDef> result = new ProcessResult<>();
//        BpmnTemplateDef selectTemplateDef = templateRestService.findTempDefById(id);
//        result.setResult(selectTemplateDef);
//        return result;
//    }
//    /**
//     * 定时发送日志信息
//     */
//    @PostMapping(value = "/templateDef/timingSynLog")
//    @ResponseBody
//    public ProcessResult<BpmnTemplateDef> timingSynLog(@RequestBody Map<String, Object> map) {
//        System.out.println(JSON.toJSONString(map));
//        ProcessResult<BpmnTemplateDef> result = new ProcessResult<>();
////        result.setResult(selectTemplateDef);
//        return result;
//    }
//
//    /**
//     * 根据部署id查询模板
//     *
//     * @param deploymentId
//     * @return
//     */
//    @PostMapping(value = "/templateDef/deployment/{id}")
//    @ResponseBody
//    public ProcessResult<BpmnTemplateDef> getTemplateDefByDeploymentId(@PathVariable("id") String deploymentId) {
//        ProcessResult<BpmnTemplateDef> result = new ProcessResult<>();
//        BpmnTemplateDef bpmnTemplateDef = bpmnTemplateDefDao.selectTemplateDefBydeploymentId("2005");
//        result.setResult(bpmnTemplateDef);
//        return result;
//    }
//
//    /**
//     * 服务端推送
//     * 部署流程定义模板
//     */
//    @PostMapping(value = "/templateDef/bpmnType/{code}")
//    @ResponseBody
//    public ProcessResult<List<BpmnTemplateDef>> selectTemplateDefByAppIdAndCategory(@PathVariable(value = "code") String bpmnType) {
//        ProcessResult<List<BpmnTemplateDef>> processResult = new ProcessResult<>();
//        Map<String, Object> sqlParam = new HashMap<String, Object>();
//        sqlParam.put("category", bpmnType);
//        List<BpmnTemplateDef> bpmnTemplateDefs = bpmnTemplateDefDao.selectTemplateDefByCategoryAndState(sqlParam);
//        processResult.setResult(bpmnTemplateDefs);
//        return processResult;
//
//    }
//
//
//    /**
//     * 服务端模板dao接口服务
//     */
//
//    /**
//     * 服务端推送
//     * 部署流程定义模板
//     */
//    @PostMapping(value = "/getTempDaoData")
//    @ResponseBody
//    public ProcessResult<Object> getTempDaoData(@RequestBody Map<String, Object> map) {
//        ProcessResult<Object> processResult = new ProcessResult<>();
//        if (bpmnTemplateDaoMethods == null) {
//            bpmnTemplateDaoMethods = bpmnTemplateDefDao.getClass().getDeclaredMethods();
//        }
//        Object result = null;
//        for (Method method : bpmnTemplateDaoMethods) {
//            try {
//                if (method.getName().equalsIgnoreCase((String) map.get("methodName"))) {
//                    Class<?>[] parameterTypes = method.getParameterTypes();
//                    ///此处不做扩展，只有一个参数
//                    if (parameterTypes.length > 0) {
//                        Class param = parameterTypes[0];
//                        Object methodParam = map.get("methodParam");
//
//                        if (param.getTypeName().equalsIgnoreCase("java.lang.String")) {
//                            result = method.invoke(bpmnTemplateDefDao, (String) methodParam);
//                        } else {
//                            Object o = param.newInstance();
//                            Object object = DataConversion.mapToObject((Map) methodParam, o);
//                            result = method.invoke(bpmnTemplateDefDao, object);
//                        }
//                    } else {
//                        result = method.invoke(bpmnTemplateDefDao);
//                        List<BpmnTemplateDef> bpmnTemplateDefs = bpmnTemplateDefDao.selectTemplateDef();
//                    }
//                    break;
//
//                }
//            } catch (Exception e) {
//                System.err.println("bpmnTemplateDefDao  的方法" + method.getName() + "调用失败");
//            }
//
//        }
//        processResult.setResult(result);
//        return processResult;
//
//    }
//}
