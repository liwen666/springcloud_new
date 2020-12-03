import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.common.Constants;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingFactory;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.nacos.IpUtils;
import org.junit.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;
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
    public void testNaming() throws NacosException {
//        String pro = "{\"secretKey\":\"\",\"contextPath\":\"\",\"accessKey\":\"\",\"namespace\":\"254ba5e6-2a13-4f0e-bf1e-8eb3ea21f4ba\",\"encode\":\"\",\"serverAddr\":\"172.16.101.23:8848,172.16.101.37:8848,172.16.101.38:8848\",\"clusterName\":\"\",\"endpoint\":\"\"}";
        String pro = "{\"secretKey\":\"\",\"contextPath\":\"\",\"accessKey\":\"\",\"namespace\":\"c5874023-84e9-4960-92aa-8d497916ed59\",\"encode\":\"\",\"serverAddr\":\"172.16.101.23:8848,172.16.101.37:8848,172.16.101.38:8848\",\"clusterName\":\"\",\"endpoint\":\"\"}";
        Properties properties = JSON.parseObject(pro, Properties.class);
        NamingService namingService = NamingFactory.createNamingService(properties);
        for(int i=0;i<10;i++){
            List<Instance> testnacos = namingService.getAllInstances("ANYEST-DATA-SERVICE");
//            List<Instance> testnacos = namingService.getAllInstances("ANYEST-CENTER-ADMIN");
            String serverStatus = namingService.getServerStatus();
//        Instance testnacos1 = namingService.selectOneHealthyInstance("ANYEST-DATA-SERVICE");
            if(testnacos.size()==0){
                System.out.println("------------------");
            }
//            System.out.println(JSON.toJSONString(testnacos));
        }
        String ip = IpUtils.getIp();
        namingService.registerInstance("test_nacos", ip, Integer.parseInt("1000"));
        for(int i=0;i<10;i++){
//            List<Instance> testnacos = namingService.getAllInstances("ANYEST-DATA-SERVICE");
            List<Instance> testnacos = namingService.getAllInstances("test_nacos");
            String serverStatus = namingService.getServerStatus();
//        Instance testnacos1 = namingService.selectOneHealthyInstance("ANYEST-DATA-SERVICE");
            if(testnacos.size()==0){
                System.out.println("jkjkj-------------");
            }
//            System.out.println(JSON.toJSONString(testnacos));
        }

//        System.out.println(JSON.toJSONString(testnacos1));
    }


    @Test
    public void namingServiceRegister() throws NacosException {
        String pro = "{\"secretKey\":\"\",\"contextPath\":\"\",\"accessKey\":\"\",\"namespace\":\"a03c92e1-2623-4f4a-b07e-3e32b7af9281\",\"encode\":\"\",\"serverAddr\":\"172.16.101.29:8848,172.16.101.30:8848,172.16.101.31:8848\",\"clusterName\":\"\",\"endpoint\":\"\"}";
        Properties properties = JSON.parseObject(pro, Properties.class);
        NamingService namingService = NamingFactory.createNamingService(properties);
//        namingService.registerInstance("test_server","192.168.42.4",9988);
        namingService.registerInstance("test_server", "192.168.42.4", 9988);

//        NamingService namingService = NamingFactory.createNamingService("172.16.101.29:8848,172.16.101.30:8848,172.16.101.31:8848");
//        namingService.registerInstance("test_server","192.168.42.4",9988);
    }

    @Test
    public void name() {
        try {
            InetAddress address = InetAddress.getLocalHost();//获取的是本地的IP地址 //PC-20140317PXKX/192.168.0.121
            String hostAddress = address.getHostAddress();//192.168.0.121
            System.out.println(hostAddress);
            InetAddress address1 = InetAddress.getByName("www.wodexiangce.cn");//获取的是该网站的ip地址，比如我们所有的请求都通过nginx的，所以这里获取到的其实是nginx服务器的IP地
            String hostAddress1 = address1.getHostAddress();//124.237.121.122
            InetAddress[] addresses = InetAddress.getAllByName("www.baidu.com");//根据主机名返回其可能的所有InetAddress对象
            for (InetAddress addr : addresses) {
                System.out.println(addr);//www.baidu.com/14.215.177.38
                //www.baidu.com/14.215.177.37
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void nacosFactory() throws NacosException, InterruptedException {
        ConfigService configService = NacosFactory.createConfigService("172.16.101.29:8848,172.16.101.30:8848,172.16.101.31:8848");
        if (configService.publishConfig("testnacos-test.yaml", Constants.DEFAULT_GROUP, "dept: Aliware\ngroup: Alibaba")) {
            Thread.sleep(200);
            System.out.println("First runner success: " + configService
                    .getConfig("testnacos-test.yaml", Constants.DEFAULT_GROUP, 5000));
        } else {
            System.out.println("First runner error: publish config error");
        }

    }

    @Test
    public void nacosFactoryName() throws NacosException, InterruptedException {
        NamingService namingService = NacosFactory.createNamingService("172.16.101.29:8848,172.16.101.30:8848,172.16.101.31:8848");
    }


    @Test
    public void localest() throws NacosException {
        String pro = "{\"secretKey\":\"\",\"contextPath\":\"\",\"accessKey\":\"\",\"namespace\":\"any_est\",\"encode\":\"\",\"serverAddr\":\"192.168.42.220:8848\",\"clusterName\":\"\",\"endpoint\":\"\"}";
        Properties properties = JSON.parseObject(pro, Properties.class);
        NamingService namingService = NamingFactory.createNamingService(properties);
        List<Instance> testnacos = namingService.getAllInstances("ANYEST-CENTER-ADMIN");
        String serverStatus = namingService.getServerStatus();
        Instance testnacos1 = namingService.selectOneHealthyInstance("ANYEST-CENTER-ADMIN");
        System.out.println(JSON.toJSONString(testnacos));
        System.out.println(JSON.toJSONString(testnacos1));
    }


    @Test
    public void metaData() throws NacosException {
        String pro = "{\"secretKey\":\"\",\"contextPath\":\"\",\"accessKey\":\"\",\"namespace\":\"temp\",\"encode\":\"\",\"serverAddr\":\"192.168.137.111:8848\",\"clusterName\":\"\",\"endpoint\":\"\"}";
        Properties properties = JSON.parseObject(pro, Properties.class);
        NamingService namingService = NamingFactory.createNamingService(properties);
        List<Instance> testnacos = namingService.getAllInstances("temp");
        String serverStatus = namingService.getServerStatus();
        Instance testnacos1 = namingService.selectOneHealthyInstance("temp");
        System.out.println(JSON.toJSONString(testnacos));
        System.out.println(JSON.toJSONString(testnacos1));
    }
}
