package com.jsp.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * @author xinre
 * @date 2019/6/11 13:28
 */
@Configuration
public class SpringMvcCfg {
    @Bean
    public ServletRegistrationBean servletRegistrationBean(DispatcherServlet dispatcherServlet) {
        ServletRegistrationBean bean = new ServletRegistrationBean(dispatcherServlet);
        bean.addUrlMappings("*.do");
        bean.addUrlMappings("/");
        return bean;
    }

}


