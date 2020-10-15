package com.example.conf;

import org.springframework.core.env.StandardEnvironment;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 注册动态数据源bean
 *
 * @author: looyii
 * @Date: 2019/7/25 16:12
 * @Description:
 */
public class KafkaGlobleProperties {
    /**
     * job服务连接的数据源配置
     */
   public static StandardEnvironment standardEnvironment;


}