package jrx.batch.dataflow.domain.service;

import jrx.batch.dataflow.domain.aop.annotation.BatchNodeLog;
import jrx.batch.dataflow.util.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.dataflow.core.AppRegistration;
import org.springframework.cloud.dataflow.core.ApplicationType;
import org.springframework.cloud.dataflow.registry.repository.AppRegistrationRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * <p>
 * 描述
 * </p>
 *
 * @author tx
 * @since 2019/5/26 23:40
 */
@Slf4j
@Service
public class AppRegisterService {
    @Autowired
    AppRegistrationRepository appRegistrationRepository;

    @BatchNodeLog(description="注册app")
    public JsonResult registerApplication(String fileName, String filePath,String type) throws URISyntaxException {
        Assert.state(null!=fileName,"文件名不能为空！");
        Assert.state(null!=filePath,"app路径不能为空！");
        AppRegistration appRegistration = new AppRegistration();
        appRegistration.setDefaultVersion(true);
        appRegistration.setType(ApplicationType.task);
//        查询版本 // TODO 对APP添加版本控制
        appRegistration.setVersion("1");
        appRegistration.setUri(new URI("file://"+filePath+fileName));
        String appName = fileName.substring(0, fileName.lastIndexOf("."));
        appRegistration.setName(appName);
        if("app".equals(type)){
            log.info("上传http job服务jar");
            appRegistration.setType(ApplicationType.app);
            appRegistration.setUri(new URI("http://"+filePath+fileName));
        }
        log.info("----注册App "+appRegistration.toString());
        AppRegistration save = appRegistrationRepository.save(appRegistration);
        return JsonResult.success(save);
    }
}
