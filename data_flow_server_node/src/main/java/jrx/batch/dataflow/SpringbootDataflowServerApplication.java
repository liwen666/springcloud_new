package jrx.batch.dataflow;

//import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource

import jrx.batch.dataflow.domain.config.batch.EnableJrxProperties;
import jrx.batch.dataflow.domain.config.batch.ScheduleCenterHikariDataSourceBuilder;
import jrx.batch.dataflow.domain.nacos.NodeServerConfigProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.dataflow.server.EnableDataFlowServer;

//import org.springframexecuteTaskPartitioework.cloud.client.discovery.EnableDiscoveryClient;
//import org.springframework.cloud.context.config.annotation.RefreshScope;
@SpringBootApplication
@ServletComponentScan
//@RefreshScope//自动刷新配置文件数据
@EnableDiscoveryClient
@EnableDataFlowServer
@EnableJrxProperties
@EnableConfigurationProperties({NodeServerConfigProperties.class, ScheduleCenterHikariDataSourceBuilder.class})
public class SpringbootDataflowServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootDataflowServerApplication.class, args);
    }
}