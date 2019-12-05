package jrx.batch.dataflow;//import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;

//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
//import org.springframework.cloud.context.config.annotation.RefreshScope;
@ServletComponentScan
@SpringBootApplication
//@RefreshScope//自动刷新配置文件数据
public class SpringbootDataflowServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootDataflowServerApplication.class, args);
    }
}