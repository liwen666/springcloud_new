package vip.dcpay.commission;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.*;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import vip.dcpay.alert.sdk.MyAlertManager;
import vip.dcpay.dao.DaoConfig;
import vip.dcpay.log.sdk.MyLogManager;
import vip.dcpay.redis.RedisSentinelConfig;
import vip.dcpay.redis.RedisServiceManager;
import vip.dcpay.redis.service.RedisService;
import vip.dcpay.util.frame.spring.AnnotationBeanNameGeneratorRewrite;
import vip.dcpay.util.frame.spring.FrameUtilConfig;



@SpringBootApplication
@ComponentScan(nameGenerator = AnnotationBeanNameGeneratorRewrite.class)
@Import({
	    FrameUtilConfig.class
	    ,DaoConfig.class
	    ,MyLogManager.class
        ,MyAlertManager.class,
        RedisSentinelConfig.class
})
@EnableAspectJAutoProxy
@EnableScheduling
@EnableDubbo
@EnableTransactionManagement
public class RunApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(
            SpringApplicationBuilder application) {

        return application.sources(RunApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(RunApplication.class, args);
    }

    @Bean("redisService")
    @DependsOn("vip.dcpay.util.frame.spring.SpringContextUtil")
    public RedisService redisService() {
        return RedisServiceManager.gainRedisService(0);
    }
    
}
