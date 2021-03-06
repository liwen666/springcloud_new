package vip.dcpay.cache;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import vip.dcpay.alert.sdk.MyAlertManager;
import vip.dcpay.log.sdk.MyLogManager;
import vip.dcpay.redis.RedisSentinelConfig;
import vip.dcpay.util.frame.spring.AnnotationBeanNameGeneratorRewrite;
import vip.dcpay.util.frame.spring.FrameUtilConfig;

@SpringBootApplication
@ComponentScan(nameGenerator = AnnotationBeanNameGeneratorRewrite.class)
@Import({
        FrameUtilConfig.class,
        RedisSentinelConfig.class,
        MyLogManager.class,
        MyAlertManager.class
})
@EnableAspectJAutoProxy
@EnableDubbo
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
