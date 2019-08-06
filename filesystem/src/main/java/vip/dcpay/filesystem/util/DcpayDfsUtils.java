package vip.dcpay.filesystem.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import vip.dcpay.filesystem.config.FileDfsConfig;
import vip.dcpay.filesystem.domain.fastdfs.StorageServer;

/**
 * author lw
 * date 2019/8/6  11:02
 * discribe 文件上传工具类
 */
@Component
public class DcpayDfsUtils implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    public static StorageServer[] getArrayStorage(String group) {
        FileDfsConfig bean = applicationContext.getBean(FileDfsConfig.class);
        return bean.getArrayStorage(group);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        DcpayDfsUtils.applicationContext = applicationContext;
    }
}
