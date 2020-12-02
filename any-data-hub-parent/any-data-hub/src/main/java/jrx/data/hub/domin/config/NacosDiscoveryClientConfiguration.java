package jrx.data.hub.domin.config;

import com.alibaba.cloud.nacos.ConditionalOnNacosDiscoveryEnabled;
import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
import com.alibaba.cloud.nacos.discovery.NacosWatch;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.client.CommonsClientAutoConfiguration;
import org.springframework.cloud.client.discovery.simple.SimpleDiscoveryClientAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.WebApplicationContext;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * nacos客户端注册至服务端时，更改服务详情中的元数据
 */
@Slf4j
@Configuration
@ConditionalOnNacosDiscoveryEnabled
@AutoConfigureBefore({SimpleDiscoveryClientAutoConfiguration.class, CommonsClientAutoConfiguration.class})
public class NacosDiscoveryClientConfiguration {
    @Value("${spring.application.name}")
    private String applicationName;

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(value = {"spring.cloud.nacos.discovery.watch.enabled"}, matchIfMissing = true)
    public NacosWatch nacosWatch(NacosDiscoveryProperties nacosDiscoveryProperties, WebApplicationContext webApplicationContext) {
        //更改服务详情中的元数据，增加服务注册时间
        nacosDiscoveryProperties.getMetadata().put("startup.time", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
//        nacosDiscoveryProperties.getMetadata().put("current-version", "V_1");
        return new NacosWatch(nacosDiscoveryProperties);
    }

}