package nacos.temp.client;

import nacos.temp.client.config.NodeServerConfigProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * <p>
 * 描述
 * </p>
 *
 * @author LW
 * @since 2021/1/22  18:10
 */
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "nacos.test")
@SpringBootApplication
@EnableConfigurationProperties(NodeServerConfigProperties.class)
public class NacosClient {
    public static void main(String[] args) {

        SpringApplication.run(NacosClient.class);
    }
}
