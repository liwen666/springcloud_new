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
public class BatchNodeCfg {

    @Test
    public void batchnode() throws NacosException, IOException {
        String pro = "{\"secretKey\":\"\",\"contextPath\":\"\",\"accessKey\":\"\",\"namespace\":\"09ef3f23-52e0-4d51-b9aa-3364a47619f1\",\"encode\":\"\",\"serverAddr\":\"172.16.101.29:8848,172.16.101.30:8848,172.16.101.31:8848\",\"clusterName\":\"\",\"endpoint\":\"\"}";
        Properties properties = JSON.parseObject(pro, Properties.class);
        ConfigService configService = NacosFactory.createConfigService(properties);
        List<String> profiles = new ArrayList<>();
        profiles.add("dev");
        profiles.add("dev9006");
        profiles.add("dev_test");
        profiles.add("dev_test2");
        profiles.add("dev_zh1");
        profiles.add("dev_zh2");
        profiles.add("dev_zh3");
        profiles.add("dev_im");
        profiles.add("dev_demo");
        profiles.add("dev_demo2");
        profiles.add("yc_node");  //压测环境  172.16.101.11

        String appname = "data_flow_server_node";
        String localfile = configService.getConfig(appname + "-local.yaml", "DEFAULT_GROUP", 1000);
        Map<?, ?> map = YamlUtil.collatingCfg(localfile);
        Map map1 = new HashMap();
        map1.putAll(map);
        YamlUtil.dumpYaml(appname + "-local.yaml", map);
        for (String file : profiles) {
            map = YamlUtil.collatingCfg(localfile);

            System.out.println(YamlUtil.converMapToProperties(map));
            Map dev = getNodeFile(file);
            for (Object o : dev.keySet()) {
                YamlUtil.setProperty(map, o, dev.get(o));
            }
            YamlUtil.dumpYaml(appname + "-" + file + ".yaml", map);
        }

    }



    private Map getNodeFile(String file) {
        Map m = new HashMap();
        if (file.equals("dev")) {

        }
        if (file.equals("dev_test")) {
            m.put("server.port",9002);
            m.put("spring.cloud.dataflow.task.platform.local.accounts.local.workingDirectoriesRoot","/home/jrxany/batch_schedule/workspace");
            m.put("spring.cloud.dataflow.task.platform.local.accounts.test.workingDirectoriesRoot","/home/jrxany/batch_schedule/workspace");
            m.put("spring.cloud.dataflow.task.platform.local.accounts.default.workingDirectoriesRoot","/home/jrxany/batch_schedule/workspace");
            m.put("spring.cloud.dataflow.task.platform.local.accounts.bigplatform.workingDirectoriesRoot","/home/jrxany/batch_schedule/platform/bigspace");
            m.put("spring.cloud.deployer.local.workingDirectoriesRoot","/home/jrxany/batch_schedule/workspace");
            m.put("spring.datasource.password","123.com");
            m.put("spring.datasource.url","jdbc:mysql://172.16.101.19:3306/data_flow_node_20?serverTimezone=Hongkong&useUnicode=true&useSSL=false&characterEncoding=utf8");
            m.put("spring.boot.admin.client.instance.service-base-url","http://172.16.102.23:9002");
            m.put("jrx.batch.properties.JOB_SERVER_DB","dev_test");
            m.put("jrx.batch.properties.JOB_SERVER_HOME_DEFAULT","/home/jrxany/batch_schedule/dev_jobserverhome");
            m.put("jrx.batch.properties.JAR_HOME_DEFAULT","/home/jrxany/batch_schedule/dev_jarhome");
            m.put("schedule.center.jdbc.datasource.props.jdbcUrl","jdbc:mysql://172.16.101.19:3306/batch_schedule_center_test?serverTimezone=Hongkong&useUnicode=true&useSSL=false&characterEncoding=utf8");
            m.put("schedule.center.jdbc.datasource.props.password","123.com");
            m.put("rocketmq.name-server","10.0.8.16:9876");
        }
        if (file.equals("dev_test2")) {
            m.put("server.port",9001);
            m.put("spring.cloud.dataflow.task.platform.local.accounts.local.workingDirectoriesRoot","/home/anyTxnCF/batch_schedule/workspace");
            m.put("spring.cloud.dataflow.task.platform.local.accounts.test.workingDirectoriesRoot","/home/anyTxnCF/batch_schedule/workspace");
            m.put("spring.cloud.dataflow.task.platform.local.accounts.default.workingDirectoriesRoot","/home/anyTxnCF/batch_schedule/workspace");
            m.put("spring.cloud.dataflow.task.platform.local.accounts.bigplatform.workingDirectoriesRoot","/home/anyTxnCF/batch_schedule/platform/bigspace");
            m.put("spring.cloud.deployer.local.workingDirectoriesRoot","/home/anyTxnCF/batch_schedule/workspace");
            m.put("spring.datasource.password","123.com");
            m.put("spring.datasource.url","jdbc:mysql://172.16.101.19:3306/data_flow_node_20_dev?serverTimezone=Hongkong&useUnicode=true&useSSL=false&characterEncoding=utf8");
            m.put("spring.boot.admin.client.instance.service-base-url","http://10.0.9.21:9001");
            m.put("jrx.batch.properties.JOB_SERVER_DB","dev_test");
            m.put("jrx.batch.properties.JOB_SERVER_HOME_DEFAULT","/home/anyTxnCF/batch_schedule/dev_jobserverhome");
            m.put("jrx.batch.properties.JAR_HOME_DEFAULT","/home/anyTxnCF/batch_schedule/dev_jarhome");
            m.put("schedule.center.jdbc.datasource.props.jdbcUrl","jdbc:mysql://172.16.101.19:3306/batch_schedule_center_test?serverTimezone=Hongkong&useUnicode=true&useSSL=false&characterEncoding=utf8");
            m.put("schedule.center.jdbc.datasource.props.password","123.com");
            m.put("rocketmq.name-server","10.0.8.16:9876");
        }
        if (file.equals("dev")) {
            m.put("server.port",9001);
            m.put("spring.cloud.dataflow.task.platform.local.accounts.local.workingDirectoriesRoot","/home/jrxany/batch_schedule/workspace");
            m.put("spring.cloud.dataflow.task.platform.local.accounts.test.workingDirectoriesRoot","/home/jrxany/batch_schedule/workspace");
            m.put("spring.cloud.dataflow.task.platform.local.accounts.default.workingDirectoriesRoot","/home/jrxany/batch_schedule/workspace");
            m.put("spring.cloud.dataflow.task.platform.local.accounts.bigplatform.workingDirectoriesRoot","/home/jrxany/batch_schedule/platform/bigspace");
            m.put("spring.cloud.deployer.local.workingDirectoriesRoot","/home/jrxany/batch_schedule/workspace");
            m.put("spring.datasource.password","123.com");
            m.put("spring.datasource.url","jdbc:mysql://172.16.101.19:3306/data_flow_node?serverTimezone=Hongkong&useUnicode=true&useSSL=false&characterEncoding=utf8");
            m.put("spring.boot.admin.client.instance.service-base-url","http://172.16.102.23:9001");
            m.put("jrx.batch.properties.JOB_SERVER_DB","master_node");
            m.put("jrx.batch.properties.JOB_SERVER_HOME_DEFAULT","/home/jrxany/batch_schedule/jobserverhome");
            m.put("jrx.batch.properties.JAR_HOME_DEFAULT","/home/jrxany/batch_schedule/jarhome");
            m.put("schedule.center.jdbc.datasource.props.jdbcUrl","jdbc:mysql://172.16.101.19:3306/batch_schedule_center?serverTimezone=Hongkong&useUnicode=true&useSSL=false&characterEncoding=utf8");
            m.put("schedule.center.jdbc.datasource.props.password","123.com");
            m.put("rocketmq.name-server","172.16.101.30:9876;172.16.101.31:9876");

        }
        if (file.equals("dev9006")) {
            m.put("server.port",9006);
            m.put("spring.cloud.dataflow.task.platform.local.accounts.local.workingDirectoriesRoot","/home/jrxany/batch_schedule/workspace");
            m.put("spring.cloud.dataflow.task.platform.local.accounts.test.workingDirectoriesRoot","/home/jrxany/batch_schedule/workspace");
            m.put("spring.cloud.dataflow.task.platform.local.accounts.default.workingDirectoriesRoot","/home/jrxany/batch_schedule/workspace");
            m.put("spring.cloud.dataflow.task.platform.local.accounts.bigplatform.workingDirectoriesRoot","/home/jrxany/batch_schedule/platform/bigspace");
            m.put("spring.cloud.deployer.local.workingDirectoriesRoot","/home/jrxany/batch_schedule/workspace");
            m.put("spring.datasource.password","123.com");
            m.put("spring.datasource.url","jdbc:mysql://172.16.101.19:3306/data_flow_node?serverTimezone=Hongkong&useUnicode=true&useSSL=false&characterEncoding=utf8");
            m.put("spring.boot.admin.client.instance.service-base-url","http://172.16.102.23:9006");
            m.put("jrx.batch.properties.JOB_SERVER_DB","master_node");
            m.put("jrx.batch.properties.JOB_SERVER_HOME_DEFAULT","/home/jrxany/batch_schedule/jobserverhome");
            m.put("jrx.batch.properties.JAR_HOME_DEFAULT","/home/jrxany/batch_schedule/jarhome");
            m.put("schedule.center.jdbc.datasource.props.jdbcUrl","jdbc:mysql://172.16.101.19:3306/batch_schedule_center?serverTimezone=Hongkong&useUnicode=true&useSSL=false&characterEncoding=utf8");
            m.put("schedule.center.jdbc.datasource.props.password","123.com");
            m.put("rocketmq.name-server","172.16.101.30:9876;172.16.101.31:9876");

        }
        if (file.equals("dev_zh1")) {
            m.put("server.port",9001);
            m.put("spring.cloud.dataflow.task.platform.local.accounts.local.workingDirectoriesRoot","/home/jrxany/batch_schedule/workspace");
            m.put("spring.cloud.dataflow.task.platform.local.accounts.test.workingDirectoriesRoot","/home/jrxany/batch_schedule/workspace");
            m.put("spring.cloud.dataflow.task.platform.local.accounts.default.workingDirectoriesRoot","/home/jrxany/batch_schedule/workspace");
            m.put("spring.cloud.dataflow.task.platform.local.accounts.bigplatform.workingDirectoriesRoot","/home/jrxany/batch_schedule/platform/bigspace");
            m.put("spring.cloud.deployer.local.workingDirectoriesRoot","/home/jrxany/batch_schedule/workspace");
            m.put("spring.datasource.password","123.com");
            m.put("spring.datasource.url","jdbc:mysql://172.16.101.19:3306/data_flow_node_zh1?serverTimezone=Hongkong&useUnicode=true&useSSL=false&characterEncoding=utf8");
            m.put("spring.boot.admin.client.instance.service-base-url","http://172.16.104.12:9001");
            m.put("jrx.batch.properties.JOB_SERVER_DB","dev_zh1");
            m.put("jrx.batch.properties.JOB_SERVER_HOME_DEFAULT","/home/jrxany/batch_schedule/jobserverhome");
            m.put("jrx.batch.properties.JAR_HOME_DEFAULT","/home/jrxany/batch_schedule/jarhome");
            m.put("schedule.center.jdbc.datasource.props.jdbcUrl","jdbc:mysql://172.16.101.19:3306/batch_schedule_center?serverTimezone=Hongkong&useUnicode=true&useSSL=false&characterEncoding=utf8");
            m.put("schedule.center.jdbc.datasource.props.password","123.com");
            m.put("rocketmq.name-server","172.16.101.30:9876;172.16.101.31:9876");

        }
        if (file.equals("dev_zh2")) {
            m.put("server.port",9001);
            m.put("spring.cloud.dataflow.task.platform.local.accounts.local.workingDirectoriesRoot","/home/jrxany/batch_schedule/workspace");
            m.put("spring.cloud.dataflow.task.platform.local.accounts.test.workingDirectoriesRoot","/home/jrxany/batch_schedule/workspace");
            m.put("spring.cloud.dataflow.task.platform.local.accounts.default.workingDirectoriesRoot","/home/jrxany/batch_schedule/workspace");
            m.put("spring.cloud.dataflow.task.platform.local.accounts.bigplatform.workingDirectoriesRoot","/home/jrxany/batch_schedule/platform/bigspace");
            m.put("spring.cloud.deployer.local.workingDirectoriesRoot","/home/jrxany/batch_schedule/workspace");
            m.put("spring.datasource.password","123.com");
            m.put("spring.datasource.url","jdbc:mysql://172.16.101.19:3306/data_flow_node_zh2?serverTimezone=Hongkong&useUnicode=true&useSSL=false&characterEncoding=utf8");
            m.put("spring.boot.admin.client.instance.service-base-url","http://172.16.102.20:9001");
            m.put("jrx.batch.properties.JOB_SERVER_DB","dev_zh2");
            m.put("jrx.batch.properties.JOB_SERVER_HOME_DEFAULT","/home/jrxany/batch_schedule/jobserverhome");
            m.put("jrx.batch.properties.JAR_HOME_DEFAULT","/home/jrxany/batch_schedule/jarhome");
            m.put("schedule.center.jdbc.datasource.props.jdbcUrl","jdbc:mysql://172.16.101.19:3306/batch_schedule_center?serverTimezone=Hongkong&useUnicode=true&useSSL=false&characterEncoding=utf8");
            m.put("schedule.center.jdbc.datasource.props.password","123.com");
            m.put("rocketmq.name-server","172.16.101.30:9876;172.16.101.31:9876");

        }
        if (file.equals("dev_zh3")) {
            m.put("server.port",9001);
            m.put("spring.cloud.dataflow.task.platform.local.accounts.local.workingDirectoriesRoot","/home/jrxany/batch_schedule/workspace");
            m.put("spring.cloud.dataflow.task.platform.local.accounts.test.workingDirectoriesRoot","/home/jrxany/batch_schedule/workspace");
            m.put("spring.cloud.dataflow.task.platform.local.accounts.default.workingDirectoriesRoot","/home/jrxany/batch_schedule/workspace");
            m.put("spring.cloud.dataflow.task.platform.local.accounts.bigplatform.workingDirectoriesRoot","/home/jrxany/batch_schedule/platform/bigspace");
            m.put("spring.cloud.deployer.local.workingDirectoriesRoot","/home/jrxany/batch_schedule/workspace");
            m.put("spring.datasource.password","123.com");
            m.put("spring.datasource.url","jdbc:mysql://172.16.101.19:3306/data_flow_node_zh3?serverTimezone=Hongkong&useUnicode=true&useSSL=false&characterEncoding=utf8");
            m.put("spring.boot.admin.client.instance.service-base-url","http://172.16.102.20:9001");
            m.put("jrx.batch.properties.JOB_SERVER_DB","dev_zh3");
            m.put("jrx.batch.properties.JOB_SERVER_HOME_DEFAULT","/home/jrxany/batch_schedule/jobserverhome");
            m.put("jrx.batch.properties.JAR_HOME_DEFAULT","/home/jrxany/batch_schedule/jarhome");
            m.put("schedule.center.jdbc.datasource.props.jdbcUrl","jdbc:mysql://172.16.101.19:3306/batch_schedule_center?serverTimezone=Hongkong&useUnicode=true&useSSL=false&characterEncoding=utf8");
            m.put("schedule.center.jdbc.datasource.props.password","123.com");
            m.put("rocketmq.name-server","172.16.101.30:9876;172.16.101.31:9876");

        }
        if (file.equals("dev_im")) {
            m.put("server.port",9002);
            m.put("spring.cloud.dataflow.task.platform.local.accounts.local.workingDirectoriesRoot","/home/jrxany/batch_schedule/workspace");
            m.put("spring.cloud.dataflow.task.platform.local.accounts.test.workingDirectoriesRoot","/home/jrxany/batch_schedule/workspace");
            m.put("spring.cloud.dataflow.task.platform.local.accounts.default.workingDirectoriesRoot","/home/jrxany/batch_schedule/workspace");
            m.put("spring.cloud.dataflow.task.platform.local.accounts.bigplatform.workingDirectoriesRoot","/home/jrxany/batch_schedule/platform/bigspace");
            m.put("spring.cloud.deployer.local.workingDirectoriesRoot","/home/jrxany/batch_schedule/workspace");
            m.put("spring.datasource.password","jrx0203");
            m.put("spring.datasource.url","jdbc:mysql://172.16.101.65:3306/data_flow_node?serverTimezone=Hongkong&useUnicode=true&useSSL=false&characterEncoding=utf8");
            m.put("spring.boot.admin.client.instance.service-base-url","http://172.16.102.23:9002");
            m.put("jrx.batch.properties.JOB_SERVER_DB","batch_node_im");
            m.put("jrx.batch.properties.JOB_SERVER_HOME_DEFAULT","/home/jrxany/batch_schedule/jobserverhome");
            m.put("jrx.batch.properties.JAR_HOME_DEFAULT","/home/jrxany/batch_schedule/jarhome");
            m.put("schedule.center.jdbc.datasource.props.jdbcUrl","jdbc:mysql://172.16.101.65:3306/batch_schedule_center?serverTimezone=Hongkong&useUnicode=true&useSSL=false&characterEncoding=utf8");
            m.put("schedule.center.jdbc.datasource.props.password","jrx0203");
            m.put("rocketmq.name-server","10.0.8.16:9876");

        }

        if (file.equals("dev_demo")) {
            m.put("server.port",9001);
            m.put("spring.cloud.dataflow.task.platform.local.accounts.local.workingDirectoriesRoot","/home/jrxany/batch_schedule/workspace");
            m.put("spring.cloud.dataflow.task.platform.local.accounts.test.workingDirectoriesRoot","/home/jrxany/batch_schedule/workspace");
            m.put("spring.cloud.dataflow.task.platform.local.accounts.default.workingDirectoriesRoot","/home/jrxany/batch_schedule/workspace");
            m.put("spring.cloud.dataflow.task.platform.local.accounts.bigplatform.workingDirectoriesRoot","/home/jrxany/batch_schedule/platform/bigspace");
            m.put("spring.cloud.deployer.local.workingDirectoriesRoot","/home/jrxany/batch_schedule/workspace");
            m.put("spring.datasource.password","mysqlhan");
            m.put("spring.datasource.url","jdbc:mysql://172.16.101.56:3306/data_flow_node1?serverTimezone=Hongkong&useUnicode=true&useSSL=false&characterEncoding=utf8");
            m.put("spring.boot.admin.client.instance.service-base-url","http://172.16.101.41:9001");
            m.put("jrx.batch.job.server.server-addr","172.16.101.23:8848,172.16.101.37:8848,172.16.101.38:8848");
            m.put("jrx.batch.job.server.namespace","4b5fe50d-eac1-4c43-afcd-49d6da44ab0b");
            m.put("jrx.batch.properties.JOB_SERVER_DB","batch_node1");
            m.put("jrx.batch.properties.JOB_SERVER_HOME_DEFAULT","/home/jrxany/batch_schedule/jobserverhome");
            m.put("jrx.batch.properties.JAR_HOME_DEFAULT","/home/jrxany/batch_schedule/jarhome");
            m.put("schedule.center.jdbc.datasource.props.jdbcUrl","jdbc:mysql://172.16.101.56:3306/any_schedule_center?serverTimezone=Hongkong&useUnicode=true&useSSL=false&characterEncoding=utf8");
            m.put("schedule.center.jdbc.datasource.props.password","mysqlhan");
            m.put("rocketmq.name-server","10.0.8.16:9876");

        }
        if (file.equals("dev_demo2")) {
            m.put("server.port",9008);
            m.put("spring.cloud.dataflow.task.platform.local.accounts.local.workingDirectoriesRoot","/home/jrxany/batch_schedule/workspace");
            m.put("spring.cloud.dataflow.task.platform.local.accounts.test.workingDirectoriesRoot","/home/jrxany/batch_schedule/workspace");
            m.put("spring.cloud.dataflow.task.platform.local.accounts.default.workingDirectoriesRoot","/home/jrxany/batch_schedule/workspace");
            m.put("spring.cloud.dataflow.task.platform.local.accounts.bigplatform.workingDirectoriesRoot","/home/jrxany/batch_schedule/platform/bigspace");
            m.put("spring.cloud.deployer.local.workingDirectoriesRoot","/home/jrxany/batch_schedule/workspace");
            m.put("spring.datasource.password","mysqlhan");
            m.put("spring.datasource.url","jdbc:mysql://172.16.101.56:3306/data_flow_node2?serverTimezone=Hongkong&useUnicode=true&useSSL=false&characterEncoding=utf8");
            m.put("spring.boot.admin.client.instance.service-base-url","http://172.16.102.23:9008");
            m.put("jrx.batch.job.server.server-addr","172.16.101.23:8848,172.16.101.37:8848,172.16.101.38:8848");
            m.put("jrx.batch.job.server.namespace","4b5fe50d-eac1-4c43-afcd-49d6da44ab0b");
            m.put("jrx.batch.properties.JOB_SERVER_DB","batch_node2");
            m.put("jrx.batch.properties.JOB_SERVER_HOME_DEFAULT","/home/jrxany/batch_schedule/jobserverhome_yanshi");
            m.put("jrx.batch.properties.JAR_HOME_DEFAULT","/home/jrxany/batch_schedule/jarhome_yanshi");
            m.put("schedule.center.jdbc.datasource.props.jdbcUrl","jdbc:mysql://172.16.101.56:3306/any_schedule_center?serverTimezone=Hongkong&useUnicode=true&useSSL=false&characterEncoding=utf8");
            m.put("schedule.center.jdbc.datasource.props.password","mysqlhan");
            m.put("rocketmq.name-server","10.0.8.16:9876");

        }


        if (file.equals("yc_node")) {
            m.put("server.port",9001);
            m.put("spring.cloud.dataflow.task.platform.local.accounts.local.workingDirectoriesRoot","/home/jrxany/batch_schedule/workspace");
            m.put("spring.cloud.dataflow.task.platform.local.accounts.test.workingDirectoriesRoot","/home/jrxany/batch_schedule/workspace");
            m.put("spring.cloud.dataflow.task.platform.local.accounts.default.workingDirectoriesRoot","/home/jrxany/batch_schedule/workspace");
            m.put("spring.cloud.dataflow.task.platform.local.accounts.bigplatform.workingDirectoriesRoot","/home/jrxany/batch_schedule/platform/bigspace");
            m.put("spring.cloud.deployer.local.workingDirectoriesRoot","/home/jrxany/batch_schedule/workspace");
            m.put("spring.datasource.password","any1234");
            m.put("spring.datasource.username","any");
            m.put("spring.datasource.url","jdbc:mysql://172.16.101.62:3306/any_batch_node1?serverTimezone=Hongkong&useUnicode=true&useSSL=false&characterEncoding=utf8");
            m.put("spring.boot.admin.client.instance.service-base-url","http://172.16.101.11:9001");
            m.put("jrx.batch.job.server.server-addr","172.16.101.23:8848,172.16.101.37:8848,172.16.101.38:8848");
            m.put("jrx.batch.job.server.namespace","4b5fe50d-eac1-4c43-afcd-49d6da44ab0b");
            m.put("jrx.batch.properties.JOB_SERVER_DB","batch_node_yc");
            m.put("jrx.batch.properties.JOB_SERVER_HOME_DEFAULT","/home/jrxany/batch_schedule/jobserverhome");
            m.put("jrx.batch.properties.JAR_HOME_DEFAULT","/home/jrxany/batch_schedule/jarhome");
            m.put("schedule.center.jdbc.datasource.props.jdbcUrl","jdbc:mysql://172.16.101.62:3306/any_schedule_center?serverTimezone=Hongkong&useUnicode=true&useSSL=false&characterEncoding=utf8");
            m.put("schedule.center.jdbc.datasource.props.password","any1234");
            m.put("schedule.center.jdbc.datasource.props.username","any");
            m.put("rocketmq.name-server","");
            m.put("server.tomcat.basedir","/home/jrxany/batch_schedule/tomcat_dir");

        }

        return m;
    }



}
