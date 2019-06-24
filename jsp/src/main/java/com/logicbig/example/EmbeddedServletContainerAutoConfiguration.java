package com.logicbig.example;

import org.apache.catalina.startup.Tomcat;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.condition.SearchStrategy;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

import javax.servlet.Servlet;
import java.io.File;

@AutoConfigureOrder(Ordered.HIGHEST_PRECEDENCE)
@Configuration
@ConditionalOnWebApplication
public class EmbeddedServletContainerAutoConfiguration {
    @Configuration
    @ConditionalOnClass({ Servlet.class, Tomcat.class })
    @ConditionalOnMissingBean(value = EmbeddedServletContainerFactory.class, search = SearchStrategy.CURRENT)
    public static class EmbeddedTomcat {
        @Bean
        public TomcatEmbeddedServletContainerFactory tomcatEmbeddedServletContainerFactory() {
            TomcatEmbeddedServletContainerFactory tomcatEmbeddedServletContainerFactory = new TomcatEmbeddedServletContainerFactory();
            //tomcatEmbeddedServletContainerFactory.setBaseDirectory(new File("D:\\workspaces_2019\\springboot-jsp\\target\\classes"));
            //tomcatEmbeddedServletContainerFactory.setDocumentRoot(new File("D:\\workspaces_2019\\springboot-jsp\\target\\classes"));
            return tomcatEmbeddedServletContainerFactory;
        }
    }
}
