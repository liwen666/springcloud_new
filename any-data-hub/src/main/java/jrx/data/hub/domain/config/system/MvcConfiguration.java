package jrx.data.hub.domain.config.system;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.HttpStatus;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
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
    public void addViewControllers(ViewControllerRegistry registry) {


        /**
         * 指定某个请求的状态码，而不返回任何的内容
         * 如下面将/badRequest请求返回状态码为400，而没有返回其它内容
         */
        registry.addStatusController("/badRequest", HttpStatus.BAD_REQUEST);
        super.addViewControllers(registry);
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        // 注册Spring data jpa pageable的参数分解器
        argumentResolvers.add(new PageableHandlerMethodArgumentResolver());
    }



}