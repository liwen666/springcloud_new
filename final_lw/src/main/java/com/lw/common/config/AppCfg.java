package com.hq.bpmn.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * 为静态变量赋值
 * business:
     app:
     mh: http://10.10.15.143:8003
     bb: http://10.10.15.143:8003
     hqbpmn: http://127.0.0.1:9091
     server-enable: true
 *
 *
 * app封装类
 * @date 2019/6/12 14:37
 */

@Configuration
@ConfigurationProperties(prefix = "business")
public class AppCfg {

    public static boolean serverEnable =false;
    /**
     * 从配置文件中读取的app开头的数据
     * 注意：名称必须与配置文件中保持一致
     */
    public static Map<String, String> app = new HashMap<>();

    public void setApp(Map<String, String> app) {
        AppCfg.app = app;
    }

    public void setServerEnable(boolean serverEnable) {
        AppCfg.serverEnable = serverEnable;
    }
}
