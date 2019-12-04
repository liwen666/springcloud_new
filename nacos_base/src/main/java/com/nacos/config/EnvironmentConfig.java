package com.nacos.config;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.web.context.support.StandardServletEnvironment;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 注册动态数据源bean
 *
 * @author: looyii
 * @Date: 2019/7/25 16:12
 * @Description:
 */
@Configuration
public class EnvironmentConfig implements BeanDefinitionRegistryPostProcessor, ImportBeanDefinitionRegistrar, EnvironmentAware {


    private static final Logger logger = LoggerFactory.getLogger(EnvironmentConfig.class);

    private ScopeMetadataResolver scopeMetadataResolver = new AnnotationScopeMetadataResolver();
    private BeanNameGenerator beanNameGenerator = new AnnotationBeanNameGenerator();

    private DataSource defaultDataSource;

    Map<String, DataSource> jdbcTemplateMap = new HashMap<>();

    private static final String DB_SQL = "select t1.address, t1.auth_user as authUser, t1.auth_pwd as authPwd, t1.db_type as dbType, t1.params as infoParams, t2.db_partition_id as id, t2.db_name as dbName, t2.params  from schedule_db_info t1, schedule_db_partition t2 where t1.db_id = t2.db_id";

    @Override
    public void setEnvironment(Environment env) {
        String[] activeProfiles = env.getActiveProfiles();
        System.out.println(JSON.toJSONString(activeProfiles));
        System.out.println(env.getProperty("server.port"));
        MutablePropertySources propertySources = ((StandardServletEnvironment) env).getPropertySources();
        PropertySource backupSource = new MapPropertySource("backupSource", new HashMap(){{put("server.port","30000");}});
        propertySources.addFirst(backupSource);
    }


    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
//        logger.info("Invoke Metho postProcessBeanDefinitionRegistry");
//
//                GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
//                beanDefinition.setBeanClass(MyBean.class);
//                beanDefinition.setSynthetic(true);
//                MutablePropertyValues mpv = beanDefinition.getPropertyValues();
//                mpv.addPropertyValue("name", "自定义benn");
//                registry.registerBeanDefinition("testBean", beanDefinition);

//                GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
//                beanDefinition.setBeanClass(DynamicDataSource.class);
//                beanDefinition.setSynthetic(true);
//                MutablePropertyValues mpv = beanDefinition.getPropertyValues();
//                mpv.addPropertyValue("defaultTargetDataSource", defaultDataSource);
//                mpv.addPropertyValue("targetDataSources", targetDataSources);
//                //注册 - BeanDefinitionRegistry
//                registry.registerBeanDefinition(CommonConstants.DATA_SOURCE, beanDefinition);
    }

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {

        logger.info("Invoke Metho postProcessBeanDefinitionRegistry");

        GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
        beanDefinition.setBeanClass(MyBean.class);
        beanDefinition.setSynthetic(true);
        MutablePropertyValues mpv = beanDefinition.getPropertyValues();
        mpv.addPropertyValue("name", "自定义benn");
        registry.registerBeanDefinition("testBean", beanDefinition);

//        if (jdbcTemplateMap.size() > 0) {
//            for (String jdbcTemplateName : jdbcTemplateMap.keySet()) {
//                // 注册 jdbcTemplate Bean
//                this.registerBean(registry, jdbcTemplateName, JdbcTemplate.class);
//            }
//        }

    }

//    private void registerBean(BeanDefinitionRegistry registry, String name, Class<?> beanClass) {
//        AnnotatedGenericBeanDefinition abd = new AnnotatedGenericBeanDefinition(beanClass);
//        ScopeMetadata scopeMetadata = this.scopeMetadataResolver.resolveScopeMetadata(abd);
//        abd.setScope(scopeMetadata.getScopeName());
//        String beanName = (name != null ? name : this.beanNameGenerator.generateBeanName(abd, registry));
//        AnnotationConfigUtils.processCommonDefinitionAnnotations(abd);
//        BeanDefinitionHolder definitionHolder = new BeanDefinitionHolder(abd, beanName);
//        BeanDefinitionReaderUtils.registerBeanDefinition(definitionHolder, registry);
//    }


    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("---postProcessBeanFactory------");
        //        if (jdbcTemplateMap.size() > 0) {
        //            for (String jdbcTemplateName : jdbcTemplateMap.keySet()) {
        //
        //                BeanDefinition bd = beanFactory.getBeanDefinition(jdbcTemplateName);
        //                MutablePropertyValues mpv = bd.getPropertyValues();
        //                mpv.addPropertyValue("dataSource", jdbcTemplateMap.get(jdbcTemplateName));
        //            }
        //        }

        // 默认DataSource
        //        if (jdbcTemplateMap.size() > 0) {
        //            String jdbcTemplateName = CommonConstants.JDBCTEMPLATE_SCHEDULE;
        //            BeanDefinition bd = beanFactory.getBeanDefinition(jdbcTemplateName);
        //            MutablePropertyValues mpv = bd.getPropertyValues();
        //            mpv.addPropertyValue("dataSource", jdbcTemplateMap.get(jdbcTemplateName));
        //        }
        MyBean bean = beanFactory.getBean(MyBean.class);
        System.out.println(JSON.toJSONString(bean));
    }
}