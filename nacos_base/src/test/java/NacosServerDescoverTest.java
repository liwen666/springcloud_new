import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.common.Constants;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingFactory;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import org.junit.Test;

import java.util.List;
import java.util.Properties;

/**
 * <p>
 * 描述
 * </p>
 *
 * @author tx
 * @since 2019/5/26 23:40
 */
public class NacosServerDescoverTest {


    @Test
    public void getFile() throws NacosException {
        String pro = "{\"secretKey\":\"\",\"contextPath\":\"\",\"accessKey\":\"\",\"namespace\":\"78ed2bf8-ebda-410a-856d-48c14f81f1dd\",\"encode\":\"\",\"serverAddr\":\"172.16.101.29:8848,172.16.101.30:8848,172.16.101.31:8848\",\"clusterName\":\"\",\"endpoint\":\"\"}";
        Properties properties = JSON.parseObject(pro, Properties.class);
        ConfigService configService = NacosFactory.createConfigService(properties);
        String default_group = configService.getConfig("testnacos-dev.yaml", "DEFAULT_GROUP", 1000);
        System.out.println(default_group);
    }


    @Test
    public void namingService() throws NacosException {
        String pro = "{\"secretKey\":\"\",\"contextPath\":\"\",\"accessKey\":\"\",\"namespace\":\"78ed2bf8-ebda-410a-856d-48c14f81f1dd\",\"encode\":\"\",\"serverAddr\":\"172.16.101.29:8848,172.16.101.30:8848,172.16.101.31:8848\",\"clusterName\":\"\",\"endpoint\":\"\"}";
        Properties properties = JSON.parseObject(pro, Properties.class);
        NamingService namingService = NamingFactory.createNamingService(properties);
        List<Instance> testnacos = namingService.getAllInstances("testnacos");
        String serverStatus = namingService.getServerStatus();
        Instance testnacos1 = namingService.selectOneHealthyInstance("testnacos");
        System.out.println(JSON.toJSONString(testnacos));
        System.out.println(JSON.toJSONString(testnacos1));
    }


    @Test
    public void namingServiceRegister() throws NacosException {
        String pro = "{\"secretKey\":\"\",\"contextPath\":\"\",\"accessKey\":\"\",\"namespace\":\"78ed2bf8-ebda-410a-856d-48c14f81f1dd\",\"encode\":\"\",\"serverAddr\":\"172.16.101.29:8848,172.16.101.30:8848,172.16.101.31:8848\",\"clusterName\":\"\",\"endpoint\":\"\"}";
        Properties properties = JSON.parseObject(pro, Properties.class);
        NamingService namingService = NamingFactory.createNamingService(properties);
        namingService.registerInstance("test_server","192.168.42.4",9988);
    }


    @Test
    public void nacosFactory() throws NacosException, InterruptedException {
        ConfigService configService = NacosFactory.createConfigService("172.16.101.29:8848,172.16.101.30:8848,172.16.101.31:8848");
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
    public void nacosFactoryName() throws NacosException, InterruptedException {
        NamingService namingService = NacosFactory.createNamingService("172.16.101.29:8848,172.16.101.30:8848,172.16.101.31:8848");
    }
}
