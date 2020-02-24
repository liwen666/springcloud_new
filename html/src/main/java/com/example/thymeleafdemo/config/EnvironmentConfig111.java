package com.example.thymeleafdemo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;
import org.springframework.context.annotation.AnnotationScopeMetadataResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ScopeMetadataResolver;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;
import org.springframework.web.context.support.StandardServletEnvironment;

import javax.sql.DataSource;
import java.io.IOException;
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
public class EnvironmentConfig111 implements EnvironmentAware , BeanDefinitionRegistryPostProcessor {


    private static final Logger logger = LoggerFactory.getLogger(EnvironmentConfig111.class);

    private ScopeMetadataResolver scopeMetadataResolver = new AnnotationScopeMetadataResolver();
    private BeanNameGenerator beanNameGenerator = new AnnotationBeanNameGenerator();

    private DataSource defaultDataSource;

    Map<String, DataSource> jdbcTemplateMap = new HashMap<>();

    private static final String DB_SQL = "select t1.address, t1.auth_user as authUser, t1.auth_pwd as authPwd, t1.db_type as dbType, t1.params as infoParams, t2.db_partition_id as id, t2.db_name as dbName, t2.params  from schedule_db_info t1, schedule_db_partition t2 where t1.db_id = t2.db_id";

    @Override
    public void setEnvironment(Environment env) {
//        /**
//         * 添加动态端口参数
//         */
//        int freePort = 0;
//        try {
//
//            freePort = SysFreePortUtils.getAndFreePort();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        logger.info("=====系统启动端口号是 server.port：" + freePort);
//        MutablePropertySources propertySources = ((StandardServletEnvironment) env).getPropertySources();
//        int finalFreePort = freePort;
//        PropertySource backupSource = new MapPropertySource("taskInitProperties", new HashMap() {{
//            put("server.port", finalFreePort);
//        }});
//        propertySources.addFirst(backupSource);
    }

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {

    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {

    }
}