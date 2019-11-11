package jrx.batch.dataflow.domain.config.system;

import com.alibaba.fastjson.JSON;
import jrx.batch.dataflow.demo.DemoConfig;
import jrx.batch.dataflow.domain.config.annotation.BeanOveride;
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

import javax.annotation.PostConstruct;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @author hafiz.zhang
 * @description:
 * @date Created in 2018/6/7 20:07.
 */
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
        DemoConfig bean = null;
        try {
            ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
            String[] beanNamesForType1 = beanFactory.getBeanNamesForType(DemoConfig.class);
            System.out.println(JSON.toJSONString(beanNamesForType1));
        } catch (BeansException e) {

        }
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