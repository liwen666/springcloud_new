package com.example.thymeleafdemo.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.*;

import java.util.HashMap;

/**
 * 注册动态数据源bean
 *
 * @author: looyii
 * @Date: 2019/7/25 16:12
 * @Description:
 */
@Configuration
public class EnvironmentConfig implements EnvironmentAware, BeanDefinitionRegistryPostProcessor, ApplicationRunner {
    private static final Logger logger = LoggerFactory.getLogger(EnvironmentConfig.class);

    @Override
    public void setEnvironment(Environment env) {
        StandardEnvironment standardEnvironment = (StandardEnvironment) env;
            String topic = standardEnvironment.getProperty("suning.rocketmq.topic");
        System.out.println("topic---->"+topic);
        MutablePropertySources propertySources = standardEnvironment.getPropertySources();
        PropertySource backupSource = new MapPropertySource("overideCfg", new HashMap<String, Object>(){{put("suning.rocketmq.topic","ANY_BATCH_JOB_RESULT_STATUE_TOPIC");}});
        propertySources.addFirst(backupSource);
    }

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {

    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {

    }

    @Override
    public void run(ApplicationArguments args) throws Exception {


    }
}