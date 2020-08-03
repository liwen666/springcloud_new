package jrx.anyest.table.config;

import com.alibaba.druid.pool.DruidDataSource;
import jrx.anyest.table.service.JdbcTemplateService;
import jrx.anyest.table.service.TableDataExpOrImpService;
import jrx.anyest.table.utils.TableSpringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * 注册动态数据源bean
 *
 * @author: looyii
 * @Date: 2019/7/25 16:12
 * @Description:
 */
@Configuration
public class TableEnvironmentConfig implements EnvironmentAware, BeanDefinitionRegistryPostProcessor, ApplicationRunner {
    private static final Logger logger = LoggerFactory.getLogger(TableEnvironmentConfig.class);
    private String url;
    private String username;
    private String password;
    private String driverClassName;

    @Override
    public void setEnvironment(Environment env) {
        StandardEnvironment standardEnvironment = (StandardEnvironment) env;
       url= standardEnvironment.getProperty("spring.datasource.url");
       username= standardEnvironment.getProperty("spring.datasource.username");
       password= standardEnvironment.getProperty("spring.datasource.password");
       driverClassName= standardEnvironment.getProperty("spring.datasource.driver-class-name");

    }

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {

    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {

    }

    @Override
    public void run(ApplicationArguments args) {
        DataSource bean = TableSpringUtil.getBean(DataSource.class);
        if (bean != null) {
            JdbcTemplateService.jdbcTemplate = new JdbcTemplate(bean);
        } else {
            DruidDataSource dataSource = new DruidDataSource();
            //设置连接参数
            dataSource.setDriverClassName(driverClassName);
            dataSource.setUrl(url);
            dataSource.setUsername(username);
            dataSource.setPassword(password);
            //配置初始化大小、最小、最大
            dataSource.setInitialSize(5);
            dataSource.setMinIdle(1);
            dataSource.setMaxActive(20);
            //连接泄漏监测
            dataSource.setRemoveAbandoned(true);
            dataSource.setRemoveAbandonedTimeout(30);
            //配置获取连接等待超时的时间
            dataSource.setMaxWait(20000);
            //配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
            dataSource.setTimeBetweenEvictionRunsMillis(20000);
            //防止过期
            dataSource.setValidationQuery("SELECT 'x'");
            dataSource.setTestWhileIdle(true);
            dataSource.setTestOnBorrow(true);
            JdbcTemplateService.jdbcTemplate = new JdbcTemplate(dataSource);
        }
    }
}