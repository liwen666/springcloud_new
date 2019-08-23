package vip.dcpay.h2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import vip.dcpay.util.frame.spring.AnnotationBeanNameGeneratorRewrite;
import vip.dcpay.util.frame.spring.FrameUtilConfig;

@SpringBootApplication
@ComponentScan(nameGenerator = AnnotationBeanNameGeneratorRewrite.class)
@Import({
        FrameUtilConfig.class
})
@EnableAspectJAutoProxy
@EnableAsync
@EnableScheduling
public class RunApplication extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(
            SpringApplicationBuilder application) {
        return application.sources(RunApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(RunApplication.class, args);
    }


}
