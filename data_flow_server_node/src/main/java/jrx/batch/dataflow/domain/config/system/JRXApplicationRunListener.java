package jrx.batch.dataflow.domain.config.system;

import com.alibaba.fastjson.JSON;
import jrx.batch.dataflow.demo.DemoConfig;
import jrx.batch.dataflow.domain.config.annotation.BeanOveride;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  描述
 * </p>
 *
 * @author lw
 *
 * @since  2019/11/12 16:12
 */
@Slf4j
public class JRXApplicationRunListener implements SpringApplicationRunListener {
    private final SpringApplication application;
    private final String[] args;

    public JRXApplicationRunListener(SpringApplication sa, String[] args) {
        this.application = sa;
        this.args = args;
    }

    @Override
    public void starting() {
//        System.out.println("自定义starting");
    }

    @Override
    public void environmentPrepared(ConfigurableEnvironment environment) {
//        System.out.println("自定义environmentPrepared");
    }

    @Override
    public void contextPrepared(ConfigurableApplicationContext context) {
//        System.out.println("自定义contextPrepared");
    }

    @Override
    public void contextLoaded(ConfigurableApplicationContext context) {
//        System.out.println("自定义contextLoaded");
    }

    @Override
    public void started(ConfigurableApplicationContext context) {
            ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
        String[] service = beanFactory.getBeanNamesForAnnotation(Service.class);
        String[] restController = beanFactory.getBeanNamesForAnnotation(RestController.class);
        String[] controller = beanFactory.getBeanNamesForAnnotation(Controller.class);
        log.info("====初始化组件 \n ====service:{},\n ====restController:{} ,\n ====controller:{}  ",
                service,restController,controller);
    }

    @Override
    public void running(ConfigurableApplicationContext context) {
//        System.out.println("自定义srunning");
    }

    @Override
    public void failed(ConfigurableApplicationContext context, Throwable exception) {
//        System.out.println("自定义failed");
    }
}