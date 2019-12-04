//package com.nacos.config;
//
//import jrx.batch.scheduling.constants.CommonConstants;
//import jrx.batch.scheduling.exception.CryptException;
//import jrx.batch.scheduling.utils.CryptoUtils;
//import org.apache.commons.lang3.StringUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.BeansException;
//import org.springframework.beans.MutablePropertyValues;
//import org.springframework.beans.factory.annotation.AnnotatedGenericBeanDefinition;
//import org.springframework.beans.factory.config.BeanDefinitionHolder;
//import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
//import org.springframework.beans.factory.support.*;
//import org.springframework.context.EnvironmentAware;
//import org.springframework.context.annotation.*;
//import org.springframework.core.env.Environment;
//import org.springframework.core.type.AnnotationMetadata;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.util.CollectionUtils;
//
//import javax.sql.DataSource;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
///**
// * 注册动态数据源bean
// *
// * @author: looyii
// * @Date: 2019/7/25 16:12
// * @Description:
// */
////@Configuration
//public class DBConfig implements BeanDefinitionRegistryPostProcessor, ImportBeanDefinitionRegistrar, EnvironmentAware {
//
//
//    private static final Logger logger = LoggerFactory.getLogger(DBConfig.class);
//
//    private ScopeMetadataResolver scopeMetadataResolver = new AnnotationScopeMetadataResolver();
//    private BeanNameGenerator beanNameGenerator = new AnnotationBeanNameGenerator();
//
//    private DataSource defaultDataSource;
//
//    Map<String, DataSource> jdbcTemplateMap = new HashMap<>();
//
//    private static final String DB_SQL = "select t1.address, t1.auth_user as authUser, t1.auth_pwd as authPwd, t1.db_type as dbType, t1.params as infoParams, t2.db_partition_id as id, t2.db_name as dbName, t2.params  from schedule_db_info t1, schedule_db_partition t2 where t1.db_id = t2.db_id";
//
//    @Override
//    public void setEnvironment(Environment env) {
//        // 调度平台数据源， 并且设为默认数据源
//        defaultDataSource = DataSourceBuild.initDefaultDataSource(env, "spring.datasource");
//        JdbcTemplate jdbcTemplate = new JdbcTemplate(defaultDataSource);
//        List<Map<String, Object>> mapList = jdbcTemplate.queryForList(DB_SQL);
//        if (!CollectionUtils.isEmpty(mapList)) {
//            for (Map<String, Object> map : mapList) {
//                StringBuffer url = new StringBuffer();
//                Map<String, Object> dsMap = new HashMap<>();
//                url.append(map.get("address").toString());
//                if (map.get("dbName") != null) {
//                    url.append("/").append(map.get("dbName").toString());
//                    String params = map.get("infoParams") == null ? "" : map.get("infoParams").toString();
//                    if (map.get("params") != null) {
//                        params = (StringUtils.isEmpty(params) ? "" : params + "&") + map.get("params").toString();
//                    }
//                    if (!StringUtils.isEmpty(params)) {
//                        url.append("?").append(params);
//                    }
//                }
//                dsMap.put(DataSourceBuild.DB_JDBCURL, url);
//
//                if (map.get("authUser") != null) {
//                    dsMap.put(DataSourceBuild.DB_USERNAME, map.get("authUser").toString());
//                }
//
//
//                if (map.get("authPwd") != null) {
//                    // 加密的密码参数
//                    try {
//                        dsMap.put(DataSourceBuild.DB_PASS, CryptoUtils.decrypt(map.get("authPwd").toString()));
//                    } catch (CryptException e) {
//                        logger.error("数据库解密错误", e.getLocalizedMessage());
//                    }
//                }
//
//                String driver = DataSourceBuild.getDriver(map.get("dbType") == null ? "mysql" : map.get("dbType").toString());
//                if (driver != null) {
//                    dsMap.put(DataSourceBuild.DB_DRIVER, driver);
//                }
//
//                DataSource batchDataSource = DataSourceBuild.buildDataSource(dsMap);
//
//                // 添加配置中心的数据源到 targetDataSources 中
//                String dataSoruceBeanName = String.format(CommonConstants.JDBCTEMPLATE_DB_PREFIX, map.get("id").toString());
//                jdbcTemplateMap.put(dataSoruceBeanName, batchDataSource);
//            }
//        }
//
//        // 添加 batch 的默认数据源到 targetDataSources（默认batch库）
//        DataSource defaultBatchDataSource = DataSourceBuild.initDefaultDataSource(env, "batch.datasource");
//        jdbcTemplateMap.put(CommonConstants.JDBCTEMPLATE_BATCH, defaultBatchDataSource);
//
//
//        jdbcTemplateMap.put(CommonConstants.JDBCTEMPLATE_SCHEDULE, defaultDataSource);
//
//    }
//
//
//    @Override
//    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
//        System.out.println("-------");
//        logger.info("Invoke Metho postProcessBeanDefinitionRegistry");
//
//        if (jdbcTemplateMap.size() > 0) {
//            for (String jdbcTemplateName : jdbcTemplateMap.keySet()) {
//                GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
//                beanDefinition.setBeanClass(JdbcTemplate.class);
//                beanDefinition.setSynthetic(true);
//                MutablePropertyValues mpv = beanDefinition.getPropertyValues();
//                mpv.addPropertyValue("dataSource", jdbcTemplateMap.get(jdbcTemplateName));
//                registry.registerBeanDefinition(jdbcTemplateName, beanDefinition);
//            }
//        }
//
//        //        GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
//        //        beanDefinition.setBeanClass(DynamicDataSource.class);
//        //        beanDefinition.setSynthetic(true);
//        //        MutablePropertyValues mpv = beanDefinition.getPropertyValues();
//        //        mpv.addPropertyValue("defaultTargetDataSource", defaultDataSource);
//        //        mpv.addPropertyValue("targetDataSources", targetDataSources);
//        //        //注册 - BeanDefinitionRegistry
//        //        registry.registerBeanDefinition(CommonConstants.DATA_SOURCE, beanDefinition);
//    }
//
//    @Override
//    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
//
////        if (jdbcTemplateMap.size() > 0) {
////            for (String jdbcTemplateName : jdbcTemplateMap.keySet()) {
////                // 注册 jdbcTemplate Bean
////                this.registerBean(registry, jdbcTemplateName, JdbcTemplate.class);
////            }
////        }
//
//    }
//
//    private void registerBean(BeanDefinitionRegistry registry, String name, Class<?> beanClass) {
//        AnnotatedGenericBeanDefinition abd = new AnnotatedGenericBeanDefinition(beanClass);
//        ScopeMetadata scopeMetadata = this.scopeMetadataResolver.resolveScopeMetadata(abd);
//        abd.setScope(scopeMetadata.getScopeName());
//        String beanName = (name != null ? name : this.beanNameGenerator.generateBeanName(abd, registry));
//        AnnotationConfigUtils.processCommonDefinitionAnnotations(abd);
//        BeanDefinitionHolder definitionHolder = new BeanDefinitionHolder(abd, beanName);
//        BeanDefinitionReaderUtils.registerBeanDefinition(definitionHolder, registry);
//    }
//
//
//    @Override
//    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
//        System.out.println("---------");
//        //        if (jdbcTemplateMap.size() > 0) {
//        //            for (String jdbcTemplateName : jdbcTemplateMap.keySet()) {
//        //
//        //                BeanDefinition bd = beanFactory.getBeanDefinition(jdbcTemplateName);
//        //                MutablePropertyValues mpv = bd.getPropertyValues();
//        //                mpv.addPropertyValue("dataSource", jdbcTemplateMap.get(jdbcTemplateName));
//        //            }
//        //        }
//
//        // 默认DataSource
//        //        if (jdbcTemplateMap.size() > 0) {
//        //            String jdbcTemplateName = CommonConstants.JDBCTEMPLATE_SCHEDULE;
//        //            BeanDefinition bd = beanFactory.getBeanDefinition(jdbcTemplateName);
//        //            MutablePropertyValues mpv = bd.getPropertyValues();
//        //            mpv.addPropertyValue("dataSource", jdbcTemplateMap.get(jdbcTemplateName));
//        //        }
//    }
//}