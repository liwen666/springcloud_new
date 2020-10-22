package jrx.data.hub;

//import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource

import jrx.data.hub.domain.nacos.NodeServerConfigProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
@EnableConfigurationProperties({NodeServerConfigProperties.class})
public class AnyDataHubApplication {

    public static void main(String[] args) {
        SpringApplication.run(AnyDataHubApplication.class, args);
    }
}