//import com.alibaba.fastjson.JSON;
//import com.alibaba.nacos.api.NacosFactory;
//import com.alibaba.nacos.api.config.ConfigService;
//import com.alibaba.nacos.api.exception.NacosException;
//import com.alibaba.nacos.client.config.NacosConfigService;
//import org.junit.Test;
//import org.springframework.cloud.alibaba.nacos.client.NacosPropertySourceBuilder;
//
//import java.util.Properties;
//
///**
// * <p>
// * 描述
// * </p>
// *
// * @author tx
// * @since 2019/5/26 23:40
// */
//public class NacosPropertySourceBuilderTest {
//
//    @Test
//    public void name() throws NacosException {
//        String pro = "{\"secretKey\":\"\",\"contextPath\":\"\",\"accessKey\":\"\",\"namespace\":\"78ed2bf8-ebda-410a-856d-48c14f81f1dd\",\"encode\":\"\",\"serverAddr\":\"172.16.101.29:8848,172.16.101.30:8848,172.16.101.31:8848\",\"clusterName\":\"\",\"endpoint\":\"\"}";
//        Properties properties = JSON.parseObject(pro, Properties.class);
//        ConfigService configService = NacosFactory.createConfigService(properties);
//        String default_group = configService.getConfig("testnacos-dev.yaml", "DEFAULT_GROUP", 1000);
//        System.out.println(default_group);
//    }
//    @Test
//    public void schedule() throws NacosException {
//        String pro = "{\"secretKey\":\"\",\"contextPath\":\"\",\"accessKey\":\"\",\"namespace\":\"0a00b50c-9d02-4e0c-b43f-07ca7d1c160d\",\"encode\":\"\",\"serverAddr\":\"172.16.101.29:8848,172.16.101.30:8848,172.16.101.31:8848\",\"clusterName\":\"\",\"endpoint\":\"\"}";
//        Properties properties = JSON.parseObject(pro, Properties.class);
//        ConfigService configService = NacosFactory.createConfigService(properties);
//        String default_group = configService.getConfig("task-schedule-system-local.yaml", "DEFAULT_GROUP", 1000);
//        System.out.println(default_group);
//    }
//
//
//    @Test
//    public void batch_node() throws NacosException {
//        String pro = "{\"secretKey\":\"\",\"contextPath\":\"\",\"accessKey\":\"\",\"namespace\":\"09ef3f23-52e0-4d51-b9aa-3364a47619f1\",\"encode\":\"\",\"serverAddr\":\"172.16.101.29:8848,172.16.101.30:8848,172.16.101.31:8848\",\"clusterName\":\"\",\"endpoint\":\"\"}";
//        Properties properties = JSON.parseObject(pro, Properties.class);
//        ConfigService configService = NacosFactory.createConfigService(properties);
//        String default_group = configService.getConfig("data_flow_server_node-local.yaml", "DEFAULT_GROUP", 1000);
//        System.out.println(default_group);
//    }
//}
