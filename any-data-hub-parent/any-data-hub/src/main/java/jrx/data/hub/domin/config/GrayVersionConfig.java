package jrx.data.hub.domin.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 服务数据配置
 */
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "spring.cloud.nacos.discovery.metadata")
public class GrayVersionConfig {
    private String currentVersion;

    public void setCurrentVersion(String currentVersion) {
        this.currentVersion = currentVersion;
    }
}
