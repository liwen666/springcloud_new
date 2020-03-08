import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;
import com.nacos.YamlUtil;
import org.junit.Test;

import java.io.IOException;
import java.util.*;

/**
 * <p>
 * 描述
 * </p>
 *
 * @author tx
 * @since 2019/5/26 23:40
 */
public class JobServer {


    @Test
    public void name() {

    }

    /**
     * 演示环境
     */
    @Test
    public void batchschedule演示() throws NacosException, IOException {
        String pro = "{\"secretKey\":\"\",\"contextPath\":\"\",\"accessKey\":\"\",\"namespace\":\"fe5439ca-757c-4e93-ad97-f6bfb20fcc9a\",\"encode\":\"\",\"serverAddr\":\"172.16.101.23:8848\",\"clusterName\":\"\",\"endpoint\":\"\"}";
        Properties properties = JSON.parseObject(pro, Properties.class);
        ConfigService configService = NacosFactory.createConfigService(properties);
        List<String> profiles = new ArrayList<>();
        String appname = "task-schedule-system";
        String localfile = configService.getConfig(appname + "-dev_demo.yaml", "DEFAULT_GROUP", 1000);
        Map<?, ?> map = YamlUtil.collatingCfg(localfile);
        YamlUtil.dumpYaml(appname + "-dev_demo.yaml2", map);
        System.out.println(YamlUtil.converMapToProperties(map));

    }
    /**
     *
     * @throws NacosException
     * @throws IOException
     */

    @Test
    public void batchschedule() throws NacosException, IOException {
        String pro = "{\"secretKey\":\"\",\"contextPath\":\"\",\"accessKey\":\"\",\"namespace\":\"0a00b50c-9d02-4e0c-b43f-07ca7d1c160d\",\"encode\":\"\",\"serverAddr\":\"172.16.101.29:8848,172.16.101.30:8848,172.16.101.31:8848\",\"clusterName\":\"\",\"endpoint\":\"\"}";
        Properties properties = JSON.parseObject(pro, Properties.class);
        ConfigService configService = NacosFactory.createConfigService(properties);
        List<String> profiles = new ArrayList<>();
        profiles.add("dev");
        profiles.add("devtest");
        profiles.add("dev_demo");
        String appname = "task-schedule-system";
        String localfile = configService.getConfig(appname + "-local.yaml", "DEFAULT_GROUP", 1000);
        Map<?, ?> map = YamlUtil.collatingCfg(localfile);
        YamlUtil.dumpYaml(appname + "-local.yaml", map);
        System.out.println(YamlUtil.converMapToProperties(map));

        for (String file : profiles) {
            Map dev = getScheduleFile(file);
            for (Object o : dev.keySet()) {
                YamlUtil.setProperty(map, o, dev.get(o));
            }
            YamlUtil.dumpYaml(appname + "-" + file + ".yaml", map);
        }

    }

    private Map getScheduleFile(String file) {
        Map m = new HashMap();
        if (file.equals("devtest")) {
            m.put("jrx.batch.node.address.master_node_test","http://172.16.102.23:9002");
            m.put("jrx.batch.node.init-servers","master_node_test");
            m.put("spring.datasource.url","jdbc:mysql://172.16.101.19:3306/batch_schedule_center_test?serverTimezone=Hongkong&useUnicode=true&useSSL=false&characterEncoding=utf8");
            m.put("spring.datasource.username","root");
            m.put("spring.datasource.password","123.com");
            m.put("server.port",9099);
            m.put("spring.cloud.deployer.local.workingDirectoriesRoot","/home/jrxany/batch_schedule/workspace");
            m.put("spring.cloud.deployer.local.java-opts","-Xms128M -Xmx128M");
            m.put("filter.setting.indexUrl","http://172.16.102.23:9099/${server.context-path}/p/index.html#/");
            m.put("filter.setting.localUrl","http://172.16.102.23:9099");
//            m.put("citic.sso.open",false);
        }
        if (file.equals("dev")) {
            m.put("jrx.batch.node.address.master_node","http://172.16.102.23:9001");
            m.put("jrx.batch.node.init-servers","dev_zh1,dev_zh2,dev_zh3");
            m.put("spring.datasource.url","jdbc:mysql://172.16.101.19:3306/batch_schedule_center?serverTimezone=Hongkong&useUnicode=true&useSSL=false&characterEncoding=utf8");
            m.put("spring.datasource.username","root");
            m.put("spring.datasource.password","123.com");
            m.put("server.port",9090);
            m.put("spring.cloud.deployer.local.workingDirectoriesRoot","/home/jrxany/batch_schedule/workspace");
            m.put("spring.cloud.deployer.local.java-opts","-Xms128M -Xmx128M");
            m.put("filter.setting.indexUrl","http://172.16.102.24:9090/${server.context-path}/p/index.html#/");
            m.put("filter.setting.localUrl","http://172.16.102.24:9090");
        }

        if (file.equals("dev_demo")) {
            m.put("jrx.batch.node.address.master_node","http://172.16.101.41:9001");
            m.put("jrx.batch.node.init-servers","");
            m.put("spring.datasource.url","jdbc:mysql://172.16.101.56:3306/any_schedule_center?serverTimezone=Hongkong&useUnicode=true&useSSL=false&characterEncoding=utf8");
            m.put("spring.datasource.username","root");
            m.put("spring.datasource.password","mysqlhan");
            m.put("server.port",9090);
            m.put("spring.cloud.deployer.local.workingDirectoriesRoot","/home/jrxany/batch_schedule/workspace");
            m.put("spring.cloud.deployer.local.java-opts","-Xms128M -Xmx128M");
            m.put("filter.setting.indexUrl","http://172.16.101.41:9090/${server.context-path}/p/index.html#/");
            m.put("filter.setting.localUrl","http://172.16.101.41:9090");
            m.put("filter.setting.authAddress","http://172.16.101.42:9600");

        }
        return m;
    }


    @Test
    public void batchnode() throws NacosException, IOException {
        String pro = "{\"secretKey\":\"\",\"contextPath\":\"\",\"accessKey\":\"\",\"namespace\":\"09ef3f23-52e0-4d51-b9aa-3364a47619f1\",\"encode\":\"\",\"serverAddr\":\"172.16.101.29:8848,172.16.101.30:8848,172.16.101.31:8848\",\"clusterName\":\"\",\"endpoint\":\"\"}";
        Properties properties = JSON.parseObject(pro, Properties.class);
        ConfigService configService = NacosFactory.createConfigService(properties);
        List<String> profiles = new ArrayList<>();
        profiles.add("dev");
        profiles.add("dev_test");
        profiles.add("dev_zh1");
        profiles.add("dev_zh2");
        profiles.add("dev_zh3");
        String appname = "data_flow_server_node";
        String localfile = configService.getConfig(appname + "-local.yaml", "DEFAULT_GROUP", 1000);
        Map<?, ?> map = YamlUtil.collatingCfg(localfile);
        YamlUtil.dumpYaml(appname + "-local.yaml", map);
        System.out.println(YamlUtil.converMapToProperties(map));

        for (String file : profiles) {
            Map dev = getScheduleFile(file);
            for (Object o : dev.keySet()) {
                YamlUtil.setProperty(map, o, dev.get(o));
            }
            YamlUtil.dumpYaml(appname + "-" + file + ".yaml", map);
        }

    }

}
