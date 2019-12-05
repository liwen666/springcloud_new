import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.common.Constants;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;
import com.nacos.YamlUtil;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * <p>
 * 描述
 * </p>
 *
 * @author tx
 * @since 2019/5/26 23:40
 */
public class CfgBak {

    @Test
    public void batchschedule() throws NacosException, IOException {
        String pro = "{\"secretKey\":\"\",\"contextPath\":\"\",\"accessKey\":\"\",\"namespace\":\"0a00b50c-9d02-4e0c-b43f-07ca7d1c160d\",\"encode\":\"\",\"serverAddr\":\"172.16.101.29:8848,172.16.101.30:8848,172.16.101.31:8848\",\"clusterName\":\"\",\"endpoint\":\"\"}";
        Properties properties = JSON.parseObject(pro, Properties.class);
        ConfigService configService = NacosFactory.createConfigService(properties);
        List<String>cfg = new ArrayList<>();
        cfg.add("task-schedule-system-local.yaml");
        cfg.add("task-schedule-system-devtest.yaml");
        cfg.add("task-schedule-system-dev.yaml");
        for(String name:cfg){
            String default_group = configService.getConfig(name, "DEFAULT_GROUP", 1000);
            Map<?, ?> map = YamlUtil.collatingCfg(default_group);
            YamlUtil.dumpYaml(name,map);
        }

    }

}
