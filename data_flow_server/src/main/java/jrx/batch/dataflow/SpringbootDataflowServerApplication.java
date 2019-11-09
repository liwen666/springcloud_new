package jrx.batch.dataflow;

//import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.dataflow.server.EnableDataFlowServer;
import org.springframework.cloud.dataflow.server.config.features.TaskConfiguration;
import org.springframework.web.bind.annotation.RestController;

//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
//import org.springframework.cloud.context.config.annotation.RefreshScope;
@SpringBootApplication
@RestController
//@RefreshScope//自动刷新配置文件数据
//@EnableDiscoveryClient
@EnableDataFlowServer
public class SpringbootDataflowServerApplication {

    public static void main(String[] args) {

        SpringApplication.run(SpringbootDataflowServerApplication.class, args);
    }


}