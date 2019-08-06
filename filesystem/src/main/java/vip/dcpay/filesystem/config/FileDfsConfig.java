package vip.dcpay.filesystem.config;

import com.alibaba.dubbo.config.ConsumerConfig;
import com.alibaba.fastjson.JSON;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import vip.dcpay.filesystem.common.MyException;
import vip.dcpay.filesystem.domain.fastdfs.*;
import vip.dcpay.log.sdk.MyLogManager;
import vip.dcpay.redis.RedisServiceManager;
import vip.dcpay.redis.service.RedisService;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * author lw
 * date 2019/8/6  12:29
 * discribe
 */
@Configuration
public class FileDfsConfig {
    public static TrackerServer trackerServer;
    public static Properties properties = new Properties();
    public static Map<String, StorageServer[]> storageServerMap = new HashMap<>();

    @PostConstruct
    void init() throws IOException, MyException {
        String conf_filename = "fastdfs-client.properties";
        ClientGlobal.initByProperties(conf_filename);
        TrackerClient tracker = new TrackerClient();
        trackerServer = tracker.getConnection();
        try {
            ProtoCommon.activeTest(trackerServer.getSocket());
        } catch (IOException e) {
            MyLogManager.develop("=====trackerServer连接异常" + e.getMessage());
        }
        System.out.println("=========================================================================================");
        MyLogManager.develop("初始化dfs trackerService " + JSON.toJSONString(trackerServer));
        String[] group_names = ((String) properties.get("fastdfs.groups")).split(",");
        for (String group : group_names) {
            storageServerMap.put(group, tracker.getStoreStorages(trackerServer, group));
            MyLogManager.develop("初始化storageServer:  【分组： " + group + "服务器地址：" + JSON.toJSONString(tracker.getStoreStorages(trackerServer, group)));
        }
        System.out.println("=========================================================================================");
    }

    public StorageServer[] getArrayStorage(String group) {
        return storageServerMap.get(group);
    }

    @Bean
    public ConsumerConfig consumerConfig() {
        ConsumerConfig consumerConfig = new ConsumerConfig();
        consumerConfig.setCheck(false);
        consumerConfig.setTimeout(20000);
        return consumerConfig;
    }

    @Bean("redisService")
    @DependsOn("vip.dcpay.util.frame.spring.SpringContextUtil")
    public RedisService redisService() {
        return RedisServiceManager.gainRedisService(0);
    }
}
