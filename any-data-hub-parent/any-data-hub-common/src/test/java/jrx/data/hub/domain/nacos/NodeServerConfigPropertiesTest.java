package jrx.data.hub.domain.nacos;

import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 */
@ActiveProfiles("local")
@RunWith(SpringRunner.class)
@SpringBootTest
public class NodeServerConfigPropertiesTest {
    @Autowired
    NodeServerConfigProperties nodeServerConfigProperties;

    @Test
    public void name() throws NacosException, InterruptedException {

        String dataId = "any-data-hub-devtest.yaml";
        String group = "DEFAULT_GROUP";
        nodeServerConfigProperties.setNamespace("testname");
        ConfigService configService = nodeServerConfigProperties.getConfigService();
        boolean isPublishOk = configService.publishConfig(dataId, group, "content");
        System.out.println(isPublishOk);
        Thread.sleep(1000);
        String content = configService.getConfig(dataId, group, 5000);
        System.out.println(content);

    }
}