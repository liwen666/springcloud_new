package nacos.temp.server;

import nacos.temp.server.config.NodeServerConfigProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * <p>
 * 描述
 * </p>
 *
 * @author LW
 * @since 2021/1/22  18:10
 */
@EnableDiscoveryClient
@SpringBootApplication
@EnableConfigurationProperties(NodeServerConfigProperties.class)
public class NacosServer {
    public static void main(String[] args) {
        SpringApplication.run(NacosServer.class);
    }
}
