package jrx.batch.dataflow.application;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import jrx.batch.dataflow.domain.config.batch.JrxBatchProperties;
import jrx.batch.dataflow.domain.enums.CodeEnums;
import jrx.batch.dataflow.domain.enums.JrxBatchEnums;
import jrx.batch.dataflow.domain.service.AppRegisterService;
import jrx.batch.dataflow.domain.service.IAppRegistrationService;
import jrx.batch.dataflow.infrastructure.model.AppRegistration;
import jrx.batch.dataflow.util.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.dataflow.core.ApplicationType;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  描述
 * </p>
 *
 * @author lw
 * @since  2020/3/6 21:04
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
    public JsonResult appListAll() {
        List<AppRegistration> list = appRegistrationService.list();
        String nodeName = BatachNodeContextUtils.getNodeName();
        if (null == nodeName) {
            return JsonResult.error("节点配置节点名称为空，请检查配置");
        }
        List<AppRegistration> collect = list.stream().filter(e -> null != e.getName()).map(e -> {
            e.setName(nodeName + ":" + e.getName());
            return e;
        }).collect(Collectors.toList());
        return JsonResult.success(collect);
    }

    @PostMapping(value = "/registerapp")
    public JsonResult fileUpload(@RequestParam(value = "file") MultipartFile file, Model model, HttpServletRequest request) throws URISyntaxException {
        if (file.isEmpty()) {
            return JsonResult.error(CodeEnums.FILE_NULL.code(), CodeEnums.FILE_NULL.getCnDesc());
        }
        String fileName = file.getOriginalFilename();  // 文件名
        log.info("====上传app   fileName: {}", fileName);
        if (!JrxRegxUtil.isAccept(fileName)) {
            log.error("====文件名不合法 filename: {}", fileName);
            return JsonResult.error(CodeEnums.FILE_NOT_ACCEPT.code(), CodeEnums.FILE_NOT_ACCEPT.getCnDesc());
        }

        String filePath = null; // 上传后的路径
        String type = request.getParameter("type");
        String appName = fileName.substring(0, fileName.lastIndexOf("."));
        filePath = JrxBatchProperties.properties.get(JrxBatchEnums.JAR_HOME_DEFAULT.name());
        if (StringUtils.isEmpty(type)) {
            type = ApplicationType.task.name();
        }
        if ("app".equals(type)) {
            filePath = JrxBatchProperties.properties.get(JrxBatchEnums.JOB_SERVER_HOME_DEFAULT.name());
        }
        Assert.state(!StringUtils.isEmpty(filePath), "文件上传存储路径有误 任务类型 type:" + type);
        if (!filePath.endsWith("/")) {
            filePath = filePath + "/";
        }
        /**
         * TODO 目前没有app版本控制，目前appName只能有一个
         */
        Integer appCode = TaskExecutionUtils.getAppCode(type);
        AppRegistration app = appRegistrationService.getOne(Wrappers.<AppRegistration>lambdaQuery().eq(AppRegistration::getName, appName).eq(AppRegistration::getType, appCode));
        if (null != app) {
            return JsonResult.error(CodeEnums.EXIST_DATA.code(), CodeEnums.EXIST_DATA.getCnDesc());
        }

        File dest = new File(filePath + fileName);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        /**
         * 注册app
         */
        return appRegisterService.registerApplication(fileName, filePath, type);
    }


}