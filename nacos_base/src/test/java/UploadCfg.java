import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.common.Constants;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;
import com.nacos.YamlUtil;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * <p>
 * 描述
 * </p>
 *
 * @author tx
 * @since 2019/5/26 23:40
 */
public class UploadCfg {

    @Test
    public void getNodeYaml() throws NacosException, InterruptedException {
        String pro = "{\"secretKey\":\"\",\"contextPath\":\"\",\"accessKey\":\"\",\"namespace\":\"09ef3f23-52e0-4d51-b9aa-3364a47619f1\",\"encode\":\"\",\"serverAddr\":\"172.16.101.29:8848,172.16.101.30:8848,172.16.101.31:8848\",\"clusterName\":\"\",\"endpoint\":\"\"}";
        Properties properties = JSON.parseObject(pro, Properties.class);
        ConfigService configService = NacosFactory.createConfigService(properties);
        String config = configService.getConfig("data_flow_server_node-local.yaml", Constants.DEFAULT_GROUP, 100);
        System.out.println(config);
    }

    @Test
    public void sendNodeYaml() throws NacosException, InterruptedException, IOException {
        String pro = "{\"secretKey\":\"\",\"contextPath\":\"\",\"accessKey\":\"\",\"namespace\":\"09ef3f23-52e0-4d51-b9aa-3364a47619f1\",\"encode\":\"\",\"serverAddr\":\"172.16.101.29:8848,172.16.101.30:8848,172.16.101.31:8848\",\"clusterName\":\"\",\"endpoint\":\"\"}";
        Properties properties = JSON.parseObject(pro, Properties.class);
        String fileName="data_flow_server_node-dev_test.yaml";
        ConfigService configService = NacosFactory.createConfigService(properties);

        InputStream inputStream = PageScanUtil.findInputStream("", fileName);
        byte[] cache = new byte[inputStream.available()];
        inputStream.read(cache);
        String data = new String(cache);
        System.out.println(data);
        configService.publishConfig(fileName, Constants.DEFAULT_GROUP, data);
    }


}
