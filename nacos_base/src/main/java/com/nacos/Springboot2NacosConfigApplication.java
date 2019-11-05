package com.nacos;

import com.alibaba.nacos.api.config.annotation.NacosValue;
//import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
//@NacosPropertySource(dataId = "springboot2-nacos-config", autoRefreshed = true)
@RestController
@EnableConfigurationProperties(value = DemoConfig.class)
@RefreshScope
public class Springboot2NacosConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(Springboot2NacosConfigApplication.class, args);
    }
    @Autowired
    private DemoConfig demoConfig;

    @Value("${test.demo.value:123}")
    private String testProperties;

    public void setTestProperties(String testProperties){
        this.testProperties = testProperties;
    }

    @GetMapping("/test")
    public String test(){
        System.out.println(demoConfig.getValue());
        return testProperties;
    }
}