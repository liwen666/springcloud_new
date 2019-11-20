package jrx.batch.dataflow.domain.config.system;

import com.alibaba.fastjson.JSON;
import jrx.batch.dataflow.demo.DemoConfig;
import jrx.batch.dataflow.domain.config.annotation.BeanOveride;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.cloud.dataflow.core.TaskDeployment;
import org.springframework.cloud.dataflow.server.config.features.TaskConfiguration;
import org.springframework.cloud.dataflow.server.repository.TaskDeploymentRepository;
import org.springframework.cloud.dataflow.server.service.TaskExecutionInfoService;
import org.springframework.cloud.dataflow.server.service.TaskExecutionService;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 描述
 * </p>
 *
 * @author tx
 * @since 2019/5/26 23:40
 */

@Configuration
@Slf4j
public class BeanOverideByFactory implements ApplicationRunner {
    @Autowired
    private ConfigurableApplicationContext context;

    @Autowired
    TaskDeploymentRepository taskDeploymentRepository;
    @PostConstruct
    public void init() {
        // 使用自定义扫描类，针对@Model进行扫描
        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
        String[] beanNamesForType1 = beanFactory.getBeanNamesForType(TaskExecutionService.class);
        log.info("taskExecutionService-->"+JSON.toJSONString(beanNamesForType1));
        BeanDefinitionRegistry beanFactory1 = (BeanDefinitionRegistry) beanFactory;
        BeanDefinition beanDefinition1 =beanFactory1.getBeanDefinition("JRXTaskExecutionService");
        beanFactory1.removeBeanDefinition(beanNamesForType1[0]);
        beanFactory1.removeBeanDefinition(beanNamesForType1[1]);
        beanFactory1.registerBeanDefinition("taskService",beanDefinition1);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
            TaskDeployment simple_job = taskDeploymentRepository.findTopByTaskDefinitionNameOrderByCreatedOnAsc("sys");

    }
}
