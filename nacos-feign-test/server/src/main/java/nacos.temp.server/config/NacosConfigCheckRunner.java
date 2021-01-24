package nacos.temp.server.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.api.config.ConfigService;
import jrx.data.hub.core.utils.FileRecursionScan;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;

/**
 * <p>
 * 描述
 * </p>
 *
 * @author LW
 * @since 2020/10/29  17:46
 */
@Slf4j
@Component
public class NacosConfigCheckRunner implements ApplicationRunner {
    @Autowired
    private NodeServerConfigProperties nodeServerConfigProperties;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        ConfigService configService = nodeServerConfigProperties.getConfigService();
        String cfg = configService.getConfig(nodeServerConfigProperties.getDataId(), nodeServerConfigProperties.getGroup(), 5000);
        /*---------------------------------------------------------------------------------/
            如果nacos不存在配置文件，自动添加配置文件到nacos;
        /---------------------------------------------------------------------------------*/
        if (null == cfg) {
            File file = FileRecursionScan.getResourceCfg(nodeServerConfigProperties.getDataId());
            if (file == null) {
                log.warn("nacos和本地不存在配置:{}", nodeServerConfigProperties.getDataId());
                return;
            }
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] cache = new byte[1024 * 1024];
            int read = fileInputStream.read(cache);
            String contextCfg = new String(cache, 0, read);
            boolean isPublishOk = configService.publishConfig(nodeServerConfigProperties.getDataId(), nodeServerConfigProperties.getGroup(), contextCfg);
            log.info("config: {}  publish complete statue: {}", nodeServerConfigProperties.getDataId(), isPublishOk);
            Thread.sleep(2000);
            String content = configService.getConfig(nodeServerConfigProperties.getDataId(), "DEFAULT_GROUP", 5000);
            log.info("配置自动添加到nacos nacosCfg:{},context:{}", JSON.toJSONString(nodeServerConfigProperties), content);
        } else {

        }

    }
}
