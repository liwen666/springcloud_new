//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package jrx.batch.dataflow.domain.config.system;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class DefaultEnvironmentPostProcessor implements EnvironmentPostProcessor, Ordered {
    private static Log logger = LogFactory.getLog(DefaultEnvironmentPostProcessor.class);
    private final Resource serverResource = new ClassPathResource("/dataflow-server.yml");
    private final Resource serverDefaultsResource = new ClassPathResource("META-INF/dataflow-server-defaults.yml");

    public DefaultEnvironmentPostProcessor() {
    }

    private static void contributeDefaults(Map<String, Object> defaults, Resource resource) {
        if (resource.exists()) {
            YamlPropertiesFactoryBean yamlPropertiesFactoryBean = new YamlPropertiesFactoryBean();
            yamlPropertiesFactoryBean.setResources(new Resource[]{resource});
            yamlPropertiesFactoryBean.afterPropertiesSet();
            Properties p = yamlPropertiesFactoryBean.getObject();
            Iterator var4 = p.keySet().iterator();

            while(var4.hasNext()) {
                Object k = var4.next();
                String key = k.toString();
                defaults.put(key, p.get(key));
            }
        }

    }

    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        Map<String, Object> internalDefaults = new HashMap();
        Map<String, Object> defaults = new HashMap();
        MutablePropertySources existingPropertySources = environment.getPropertySources();
        contributeDefaults(internalDefaults, this.serverDefaultsResource);
        contributeDefaults(defaults, this.serverResource);
        String defaultPropertiesKey = "defaultProperties";
        if (existingPropertySources.contains(defaultPropertiesKey) && existingPropertySources.get(defaultPropertiesKey) != null) {
            PropertySource<?> propertySource = existingPropertySources.get(defaultPropertiesKey);
            Map<String, Object> mapOfProperties = (Map)Map.class.cast(propertySource.getSource());
            Iterator var9 = internalDefaults.keySet().iterator();

            String k;
            Set setOfPropertyKeys;
            while(var9.hasNext()) {
                k = (String)var9.next();
                setOfPropertyKeys = mapOfProperties.keySet();
                if (!setOfPropertyKeys.contains(k)) {
                    mapOfProperties.put(k, internalDefaults.get(k));
                    logger.debug(k + '=' + internalDefaults.get(k));
                }
            }

            var9 = defaults.keySet().iterator();

            while(var9.hasNext()) {
                k = (String)var9.next();
                setOfPropertyKeys = mapOfProperties.keySet();
                if (!setOfPropertyKeys.contains(k)) {
                    mapOfProperties.put(k, defaults.get(k));
                    logger.debug(k + '=' + defaults.get(k));
                }
            }
        } else {
            existingPropertySources.addLast(new MapPropertySource(defaultPropertiesKey, internalDefaults));
            existingPropertySources.addLast(new MapPropertySource(defaultPropertiesKey, defaults));
        }

    }

    public int getOrder() {
        return 0;
    }
}
