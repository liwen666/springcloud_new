package jrx.batch.dataflow.application;

import jrx.batch.dataflow.domain.config.batch.JrxBatchProperties;
import jrx.batch.dataflow.domain.enums.CodeEnums;
import jrx.batch.dataflow.domain.service.AppRegisterService;
import jrx.batch.dataflow.domain.service.IAppRegistrationService;
import jrx.batch.dataflow.infrastructure.model.AppRegistration;
import jrx.batch.dataflow.util.BatachNodeContextUtils;
import jrx.batch.dataflow.util.JrxRegxUtil;
import jrx.batch.dataflow.util.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 文件上传
 */
@Slf4j
@RestController
@RequestMapping("/batch/node")
public class AppRegisterController {

    @Autowired
    private AppRegisterService appRegisterService;
    @Autowired
    private IAppRegistrationService appRegistrationService;

    @GetMapping(value = "/apps/list")
    public JsonResult appListAll()  {
        List<AppRegistration> list = appRegistrationService.list();
        String nodeName = BatachNodeContextUtils.getNodeName();
        if (null==nodeName){
            return JsonResult.error("节点配置节点名称为空，请检查配置");
        }
        List<AppRegistration> collect = list.stream().filter(e->null!=e.getName()).map(e -> {
            e.setName(nodeName + ":" + e.getName());
            return e;
        }).collect(Collectors.toList());
        return JsonResult.success(collect);
    }

    @PostMapping(value = "/registerapp")
    public JsonResult fileUpload(@RequestParam(value = "file") MultipartFile file, Model model, HttpServletRequest request) throws URISyntaxException {
        if (file.isEmpty()) {
            return JsonResult.error(CodeEnums.FILE_NULL.getCode(),CodeEnums.FILE_NULL.getCnDesc());
        }
        String fileName = file.getOriginalFilename();  // 文件名
        log.info("====上传app   fileName: {}",fileName);
        String suffixName = fileName.substring(fileName.lastIndexOf("."));  // 后缀名
        if(!JrxRegxUtil.isAccept(fileName)){
            log.error("====文件名不合法 filename: {}",fileName);
            return JsonResult.error(CodeEnums.FILE_NOT_ACCEPT.getCode(),CodeEnums.FILE_NOT_ACCEPT.getCnDesc());
        }
        String filePath = JrxBatchProperties.properties.get("jarhome"); // 上传后的路径
        if(!filePath.endsWith("/")){
            filePath= filePath+"/";
        }
        File dest = new File(filePath + fileName);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        /**
         * 注册app
         */
        return appRegisterService.registerApplication(fileName,filePath);
    }


}