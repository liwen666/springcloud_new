package jrx.batch.dataflow.domain.config.system;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.HttpStatus;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

@Configuration
public class MvcConfiguration extends WebMvcConfigurationSupport {


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
         * 配置路径跳转；将某个路径的请求映射到另外一个路径
         * 如将所有http://localhost/b/**的请求全部跳转到http://localhost/test上去
         */
        registry.addRedirectViewController("/dashboard/runtime.26209474bfa8dc87a77c.js", "/public/dashboard/runtime.26209474bfa8dc87a77c.js");
        registry.addRedirectViewController("/dashboard/polyfills.a41f31ce6428ed14368b.js", "/public/dashboard/polyfills.a41f31ce6428ed14368b.js");
        registry.addRedirectViewController("/dashboard/main.f12466615384b92082d1.js", "/public/dashboard/main.f12466615384b92082d1.js");
        registry.addRedirectViewController("/dashboard/styles.1f73bce9a5c274f63362.css", "/public/dashboard/styles.1f73bce9a5c274f63362.css");
        registry.addRedirectViewController("/dashboard/polyfills.a41f31ce6428ed14368b.js", "/public/dashboard/polyfills.a41f31ce6428ed14368b.js");
        registry.addRedirectViewController("/dashboard/index.html", "/public/dashboard/index.html");

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

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        // 注册Spring data jpa pageable的参数分解器
        argumentResolvers.add(new PageableHandlerMethodArgumentResolver());
    }



}