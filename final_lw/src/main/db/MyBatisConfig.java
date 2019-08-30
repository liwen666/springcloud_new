//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package vip.dcpay.cache.domain.config.db;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.MybatisXMLLanguageDriver;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.core.config.GlobalConfig.DbConfig;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import vip.dcpay.util.frame.spring.PropertiesConfigurer;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@EnableTransactionManagement
@DependsOn({"myRoutingDataSource", "dbPropertiesConfigurer"})
@Order(2)
@Configuration
public class MyBatisConfig {
    @Resource(
        name = "myRoutingDataSource"
    )
    private DataSource myRoutingDataSource;
    @Resource(
        name = "dbPropertiesConfigurer"
    )
    private PropertiesConfigurer dbPropertiesConfigurer;

    public MyBatisConfig() {
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        MybatisSqlSessionFactoryBean sqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(this.myRoutingDataSource);
        List<Interceptor> interceptorList = new ArrayList();
        if (this.dbPropertiesConfigurer.getBoolean("db.PaginationInterceptor", true)) {
            interceptorList.add(new PaginationInterceptor());
        }

        if (this.dbPropertiesConfigurer.getBoolean("db.OptimisticLockerInterceptor", false)) {
            interceptorList.add(new OptimisticLockerInterceptor());
        }

        if (this.dbPropertiesConfigurer.getBoolean("db.PerformanceInterceptor", false)) {
            interceptorList.add(new PerformanceInterceptor());
        }

        sqlSessionFactoryBean.setPlugins((Interceptor[])interceptorList.toArray(new Interceptor[interceptorList.size()]));
        MybatisConfiguration configuration = new MybatisConfiguration();
        configuration.setDefaultScriptingLanguage(MybatisXMLLanguageDriver.class);
        configuration.setJdbcTypeForNull(JdbcType.NULL);
        configuration.setMapUnderscoreToCamelCase(this.dbPropertiesConfigurer.getBoolean("db.under_score_to_camel_case", false));
        GlobalConfig globalConfig = new GlobalConfig();
        DbConfig dbConfig = new DbConfig();
        dbConfig.setIdType(IdType.AUTO);
        dbConfig.setCapitalMode(false);
        globalConfig.setDbConfig(dbConfig);
        sqlSessionFactoryBean.setConfiguration(configuration);
        sqlSessionFactoryBean.setGlobalConfig(globalConfig);
        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    public PlatformTransactionManager platformTransactionManager() {
        return new DataSourceTransactionManager(this.myRoutingDataSource);
    }

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}
