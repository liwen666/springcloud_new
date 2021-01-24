package nacos.temp.client;

import com.netflix.loadbalancer.IRule;
import nacos.temp.client.config.MyCustomRule;
import nacos.temp.client.config.NodeServerConfigProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

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
public class NacosClient {
    public static void main(String[] args) {

        SpringApplication.run(NacosClient.class);
    }
    @Bean
    @LoadBalanced//支持负载均衡
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }

    @Bean
    public IRule myRule(){
        return new MyCustomRule();//自定义的Rule
        //return new RandomRule();
    }
}
