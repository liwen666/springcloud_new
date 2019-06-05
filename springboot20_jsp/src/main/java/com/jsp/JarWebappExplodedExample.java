package com.jsp;

import com.alibaba.fastjson.JSON;
import com.jsp.config.Configuration;
import org.apache.catalina.Host;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.embedded.EmbeddedWebServerFactoryCustomizerAutoConfiguration;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.embedded.tomcat.TomcatWebServer;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
@EnableFeignClients
@ServletComponentScan
@SpringBootApplication
public class JarWebappExplodedExample extends TomcatServletWebServerFactory {

    public static void main(String[] args) {
        SpringApplication.run(JarWebappExplodedExample.class, args);
    }


    @Override
    protected TomcatWebServer getTomcatWebServer(Tomcat tomcat) {
        StringBuilder ports = new StringBuilder();
        Connector[] var3 = tomcat.getService().findConnectors();
        int var4 = var3.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            Connector connector = var3[var5];
            connector.setPort(10000);
        }
        return super.getTomcatWebServer(tomcat);
    }
    @Controller
    public static class MyController {
        @Autowired
        private Configuration configuration;
        @RequestMapping("/")
        public String handler (Model model) {
            model.addAttribute("date",
                    LocalDateTime.now());
            System.out.println(JSON.toJSONString(configuration));
            return "myPage";
        }
    }


}