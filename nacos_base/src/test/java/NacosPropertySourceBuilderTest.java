import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.common.Constants;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.client.config.NacosConfigService;
import org.junit.Test;
import org.springframework.cloud.alibaba.nacos.client.NacosPropertySourceBuilder;

import java.util.Properties;

/**
 * <p>
 * 描述
 * </p>
 *
 * @author tx
 * @since 2019/5/26 23:40
 */
public class NacosPropertySourceBuilderTest {

    @Test
    public void name() throws NacosException {
        String pro = "{\"secretKey\":\"\",\"contextPath\":\"\",\"accessKey\":\"\",\"namespace\":\"78ed2bf8-ebda-410a-856d-48c14f81f1dd\",\"encode\":\"\",\"serverAddr\":\"172.16.101.29:8848,172.16.101.30:8848,172.16.101.31:8848\",\"clusterName\":\"\",\"endpoint\":\"\"}";
        Properties properties = JSON.parseObject(pro, Properties.class);
        ConfigService configService = NacosFactory.createConfigService(properties);
        String default_group = configService.getConfig("testnacos-dev.yaml", "DEFAULT_GROUP", 1000);
        System.out.println(default_group);
    }
    @Test
    public void schedule() throws NacosException {
        String pro = "{\"secretKey\":\"\",\"contextPath\":\"\",\"accessKey\":\"\",\"namespace\":\"0a00b50c-9d02-4e0c-b43f-07ca7d1c160d\",\"encode\":\"\",\"serverAddr\":\"172.16.101.29:8848,172.16.101.30:8848,172.16.101.31:8848\",\"clusterName\":\"\",\"endpoint\":\"\"}";
        Properties properties = JSON.parseObject(pro, Properties.class);
        ConfigService configService = NacosFactory.createConfigService(properties);
        String default_group = configService.getConfig("task-schedule-system-local.yaml", "DEFAULT_GROUP",1000);
        System.out.println(default_group);
    }

    @Test
    public void sender() throws NacosException, InterruptedException {
        String pro = "{\"secretKey\":\"\",\"contextPath\":\"\",\"accessKey\":\"\",\"namespace\":\"78ed2bf8-ebda-410a-856d-48c14f81f1dd\",\"encode\":\"\",\"serverAddr\":\"172.16.101.29:8848,172.16.101.30:8848,172.16.101.31:8848\",\"clusterName\":\"\",\"endpoint\":\"\"}";
        Properties properties = JSON.parseObject(pro, Properties.class);
        ConfigService configService = NacosFactory.createConfigService(properties);
        if (configService.publishConfig("testnacos-test.yaml", Constants.DEFAULT_GROUP, "dept: Aliware\ngroup: Alibaba")) {
            Thread.sleep(200);
            System.out.println("First runner success: " + configService
                    .getConfig("testnacos-test.yaml", Constants.DEFAULT_GROUP, 5000));
        }
        else {
            System.out.println("First runner error: publish config error");
        }
    }


    @Test
    public void senderYaml() throws NacosException, InterruptedException {
        String pro = "{\"secretKey\":\"\",\"contextPath\":\"\",\"accessKey\":\"\",\"namespace\":\"78ed2bf8-ebda-410a-856d-48c14f81f1dd\",\"encode\":\"\",\"serverAddr\":\"172.16.101.29:8848,172.16.101.30:8848,172.16.101.31:8848\",\"clusterName\":\"\",\"endpoint\":\"\"}";
        Properties properties = JSON.parseObject(pro, Properties.class);
        ConfigService configService = NacosFactory.createConfigService(properties);
        if (configService.publishConfig("testnacos-test.yaml", Constants.DEFAULT_GROUP, "server:\n" +
                "  port: 11000\n" +
                "test:\n" +
                "  demo:\n" +
                "    value: 200011")) {
            Thread.sleep(200);
            System.out.println("First runner success: " + configService
                    .getConfig("testnacos-test.yaml", Constants.DEFAULT_GROUP, 5000));
        }
        else {
            System.out.println("First runner error: publish config error");
        }
    }

    @Test
    public void getFile() throws NacosException {
        String pro = "{\"secretKey\":\"\",\"contextPath\":\"\",\"accessKey\":\"\",\"namespace\":\"78ed2bf8-ebda-410a-856d-48c14f81f1dd\",\"encode\":\"\",\"serverAddr\":\"172.16.101.29:8848,172.16.101.30:8848,172.16.101.31:8848\",\"clusterName\":\"\",\"endpoint\":\"\"}";
        Properties properties = JSON.parseObject(pro, Properties.class);
        ConfigService configService = NacosFactory.createConfigService(properties);
        String default_group = configService.getConfig("testnacos-dev.yaml", "DEFAULT_GROUP", 1000);
        System.out.println(default_group);
    }

}
