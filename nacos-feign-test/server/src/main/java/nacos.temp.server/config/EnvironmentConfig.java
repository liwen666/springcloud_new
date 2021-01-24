package nacos.temp.server.config;

import com.alibaba.nacos.api.config.ConfigService;
import com.google.common.collect.Maps;
import jrx.data.hub.domain.nacos.NodeServerConfigProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.*;
import org.springframework.core.io.ClassPathResource;

import java.util.Map;
import java.util.Properties;

/**
 * <p>
 * 描述
 * </p>
 *
 * @author lw
 * @since 2020/10/22 16:24
 */
@Configuration
@Slf4j
public class EnvironmentConfig implements EnvironmentAware, BeanDefinitionRegistryPostProcessor {

    @Override
    public void setEnvironment(Environment env) {
        try {
            MutablePropertySources propertySources;
            if (env instanceof StandardEnvironment) {
                String serverAddr = env.getProperty("jrx.data.hub.server.server-addr");
                String namespace = env.getProperty("jrx.data.hub.server.namespace");
                String dataId = env.getProperty("jrx.data.hub.server.data-id");
                ConfigService configService = NodeServerConfigProperties.builder().serverAddr(serverAddr).namespace(namespace).build().getConfigService();
                String dataContext = configService.getConfig(dataId, "DEFAULT_GROUP", 1000);
                if (null == dataContext) {
                    log.warn("----nacos----不存在对应的配置文件---dataID: {}-", dataId);
                    YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
                    yaml.setResources(new ClassPathResource("temp/" + dataId));
                    Properties object = yaml.getObject();
                    Map<String, Object> param = Maps.newConcurrentMap();
                    object.forEach((k, v) -> {
                        param.put((String) k, v);
                    });
                    propertySources = ((StandardEnvironment) env).getPropertySources();
                    PropertySource backupSource = new MapPropertySource("init-properties", param);
                    propertySources.addFirst(backupSource);
                }
            }
        } catch (Exception e) {
            log.error("----init system properties error !---{}", e);
        }
    }

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {

    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {

    }
}