package jrx.batch.dataflow.domain.config.batch;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.core.configuration.BatchConfigurationException;
import org.springframework.batch.core.configuration.ListableJobLocator;
import org.springframework.batch.core.configuration.annotation.BatchConfigurer;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.support.MapJobRegistry;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.explore.support.JobExplorerFactoryBean;
import org.springframework.batch.core.explore.support.MapJobExplorerFactoryBean;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.core.repository.support.MapJobRepositoryFactoryBean;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.dataflow.core.TaskPlatform;
import org.springframework.cloud.dataflow.server.config.features.LocalPlatformProperties;
import org.springframework.cloud.dataflow.server.config.features.LocalTaskPlatformFactory;
import org.springframework.cloud.scheduler.spi.core.Scheduler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.lang.Nullable;
import org.springframework.transaction.PlatformTransactionManager;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

/**
 * @author yxy
 * @date 2018/10/23
 */
@Configuration
public class DefineBatchConfigurer implements BatchConfigurer {

    private static final Log logger = LogFactory.getLog(DefineBatchConfigurer.class);
    @Autowired
    private DataSource dataSource;
    @Autowired
    private PlatformTransactionManager transactionManager;
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private JobLauncher jobLauncher;
    @Autowired
    private JobExplorer jobExplorer;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.transactionManager = new DataSourceTransactionManager(dataSource);
    }

    protected DefineBatchConfigurer() {
    }

    public DefineBatchConfigurer(DataSource dataSource) {
        setDataSource(dataSource);
    }

    @Override
    public JobRepository getJobRepository() {
        return jobRepository;
    }

    @Override
    public PlatformTransactionManager getTransactionManager() {
        return transactionManager;
    }

    @Override
    public JobLauncher getJobLauncher() {
        return jobLauncher;
    }

    @Override
    public JobExplorer getJobExplorer() {
        return jobExplorer;
    }

    @PostConstruct
    public void initialize() {
    }

    @Bean
    protected JobLauncher createJobLauncher() throws Exception {
        SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
        jobLauncher.setJobRepository(jobRepository);
        jobLauncher.afterPropertiesSet();
        return jobLauncher;
    }


    @Bean
    protected JobExplorer jobExplorer() throws Exception {
        JobExplorerFactoryBean jobExplorerFactoryBean = new JobExplorerFactoryBean();
        jobExplorerFactoryBean.setDataSource(this.dataSource);
        jobExplorerFactoryBean.afterPropertiesSet();
        return jobExplorerFactoryBean.getObject();

    }
    @Bean
    protected ListableJobLocator listableJobLocator() throws Exception {
        ListableJobLocator listableJobLocator = new MapJobRegistry();
        return listableJobLocator;

    }
    @Bean
    protected JobBuilderFactory jobBuilderFactory() throws Exception {
        return new JobBuilderFactory(jobRepository);

    }
    @Bean
    protected StepBuilderFactory stepBuilderFactory() throws Exception {
        return new StepBuilderFactory(jobRepository,transactionManager);

    }

    @Profile({"dev"})
    @Bean
    public TaskPlatform localTaskPlatform(LocalPlatformProperties localPlatformProperties, @Nullable Scheduler localScheduler) {
        TaskPlatform taskPlatform = (new LocalTaskPlatformFactory(localPlatformProperties, localScheduler)).createTaskPlatform();
        taskPlatform.setPrimary(true);
        return taskPlatform;
    }
}
