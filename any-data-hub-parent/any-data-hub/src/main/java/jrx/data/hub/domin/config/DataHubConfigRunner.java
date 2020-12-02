package jrx.data.hub.domin.config;

import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
import jrx.data.hub.domin.service.DataServiceVersionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * 描述
 * </p>
 *
 * @author LW
 * @since 2020/11/25  17:28
 */
@Configuration
@Slf4j
public class DataHubConfigRunner implements ApplicationRunner {
    @Autowired
    private DataServiceVersionService dataServiceVersionService;
    @Autowired
    private NacosDiscoveryProperties nacosDiscoveryProperties;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        String version = dataServiceVersionService.getCurrentVersion();
        log.info("----当前服务的版本是{}----", version);
    }
}
