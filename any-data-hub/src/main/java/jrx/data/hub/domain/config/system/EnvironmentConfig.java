package jrx.data.hub.domain.config.system;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
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
public class EnvironmentConfig implements EnvironmentAware, BeanDefinitionRegistryPostProcessor {
    private static final Logger logger = LoggerFactory.getLogger(EnvironmentConfig.class);

    @Override
    public void setEnvironment(Environment env) {
        MutablePropertySources propertySources;
       if (env instanceof StandardEnvironment) {
            propertySources = ((StandardEnvironment) env).getPropertySources();
            PropertySource backupSource = new MapPropertySource("taskInitProperties", new HashMap() {{
            }});
            propertySources.addFirst(backupSource);
        }


    }

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {

    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {

    }
}