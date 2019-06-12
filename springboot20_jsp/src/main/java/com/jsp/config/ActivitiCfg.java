//package com.jsp.config;
//
//import com.alibaba.druid.pool.DruidDataSource;
//import org.activiti.engine.ProcessEngine;
//import org.activiti.engine.ProcessEngineConfiguration;
//import org.activiti.engine.impl.history.HistoryLevel;
//import org.activiti.spring.SpringProcessEngineConfiguration;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.transaction.PlatformTransactionManager;
//
//import javax.sql.DataSource;
//import java.sql.SQLException;
//
///**
// * @author xinre
// * @date 2019/6/11 11:06
// */
//@Configuration
//public class ActivitiCfg {
//
//    @Bean
//    @Primary
//    @ConfigurationProperties(prefix = "spring.datasource")
//    public DataSource getDataSource() {
//        // type必须先指定，否则会报错
//        return DataSourceBuilder.create().type(DruidDataSource.class).build();
//    }
//
//    @Bean(name = "processEngineConfiguration")
//    public ProcessEngineConfiguration getProcessEngineConfiguration(DataSource dataSource, PlatformTransactionManager platformTransactionManager) throws SQLException {
//        System.out.println("platformTransactionManager = " + platformTransactionManager);
//        SpringProcessEngineConfiguration springProcessEngineConfiguration = new SpringProcessEngineConfiguration();
//        springProcessEngineConfiguration.setDataSource(dataSource);
//        springProcessEngineConfiguration.setTransactionManager(platformTransactionManager);
//        springProcessEngineConfiguration.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_FALSE);
//        springProcessEngineConfiguration.setJobExecutorActivate(false);
//        springProcessEngineConfiguration.setJpaHandleTransaction(true);
//        springProcessEngineConfiguration.setHistory(HistoryLevel.AUDIT.getKey());
//        return springProcessEngineConfiguration;
//    }
//    @Bean(name = "processEngine")
//    public ProcessEngine getProcessEngine(ProcessEngineConfiguration processEngineConfiguration) {
//        return processEngineConfiguration.buildProcessEngine();
//    }
//}
