package com.hq.bpmn.common.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * app封装类
 *  business:
     app:
     mh: http://10.10.15.143:8003
     bb: http://10.10.15.143:8003
     hqbpmn: http://lacalhost:9091
 * @date 2019/6/12 14:37
 */

@Configuration
@ConditionalOnProperty(prefix = "business.server", name = "enable", matchIfMissing = false)
@ConfigurationProperties(prefix = "business")
@EnableConfigurationProperties(AppCfg.class)
public class AppCfg {

    /**
     * 从配置文件中读取的app开头的数据
     * 注意：名称必须与配置文件中保持一致
     */
    private Map<String, String> app = new HashMap<>();

    public Map<String, String> getApp() {
        return app;
    }

    public void setApp(Map<String, String> app) {
        this.app = app;
    }


}
