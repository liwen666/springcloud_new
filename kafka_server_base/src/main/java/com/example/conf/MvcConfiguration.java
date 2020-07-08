package com.example.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * <p>
 *  描述
 * </p>
 *
 * @author lw
 * @since  2019/11/12 16:16
 */
@Configuration
public class MvcConfiguration extends WebMvcConfigurerAdapter {


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        if (!registry.hasMappingForPattern("/static/**")) {
            registry.addResourceHandler("/static/**").
                    addResourceLocations("classpath:/static/");
        }
        if (!registry.hasMappingForPattern("/public/dashboard/**")) {
            registry.addResourceHandler("/public/dashboard/**").
                    addResourceLocations("classpath:/public/dashboard/");
        }
        super.addResourceHandlers(registry);
    }


    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        // 注册Spring data jpa pageable的参数分解器
        argumentResolvers.add(new PageableHandlerMethodArgumentResolver());
    }



}