package com.example.thymeleafdemo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * 配置静态资源路径
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        if(!registry.hasMappingForPattern("/static/**")){
            registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        }
        super.addResourceHandlers(registry);
    }

    @Override
    protected void addViewControllers(ViewControllerRegistry registry) {
        /**
         * 配置路径跳转；将某个路径的请求映射到另外一个路径
         * 如将所有http://localhost/b/**的请求全部跳转到http://localhost/test上去
         */
//        registry.addRedirectViewController("/b/**", "/test");

        /**
         * 将路径映射到某个名称为指定值的视图上
         * 访问/c会返回a.html的视图
         * 一般与ViewResolver结合使用
         */
//        registry.addViewController("/c").setViewName("a");

        /**
         * 指定某个请求的状态码，而不返回任何的内容
         * 如下面将/badRequest请求返回状态码为400，而没有返回其它内容
         */
        registry.addStatusController("/badRequest", HttpStatus.BAD_REQUEST);
        super.addViewControllers(registry);
    }

}