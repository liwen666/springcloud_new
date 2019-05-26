package com.lw.common.config;

import com.baomidou.mybatisplus.extension.plugins.IllegalSQLInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

@Service
public class MybatisSessionConfig  {
    @Autowired
    @Qualifier("mysqlDataSource")
    private DataSource dataSource;

    /**
     *  TODO 操作不同的数据源可以用上
     * @return
     */
    @Bean
    public SqlSessionFactory getSqlSessionFactory() {
        try {
            SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
            sqlSessionFactoryBean.setDataSource(dataSource);
            String packageSearchPath = "classpath*:" + "com.lw.common".replaceAll("\\.", "/") + "/**/*Mapper.xml";

            Resource[] resources = new PathMatchingResourcePatternResolver().getResources(packageSearchPath);
            sqlSessionFactoryBean.setMapperLocations(resources);
            sqlSessionFactoryBean.setPlugins(findPlugins());
            return sqlSessionFactoryBean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private Interceptor[] findPlugins() {
        Interceptor interceptor = new IllegalSQLInterceptor(); //对sql进行校验
        Interceptor me = new MyBatisInterceptor();
        Interceptor sqlInter = new PerformanceInterceptor();
        ((PerformanceInterceptor)sqlInter).setWriteInLog(true);
        return new Interceptor[]{interceptor,me,sqlInter};
//        return new Interceptor[]{me};
    }


//    @Bean
//    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
//        SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
//        factory.setDataSource(dataSource);
//        factory.setVfs(SpringBootVFS.class);
//        if (StringUtils.hasText(this.properties.getConfigLocation())) {
//            factory.setConfigLocation(this.resourceLoader.getResource(this.properties.getConfigLocation()));
//        }
//        Configuration configuration = this.properties.getConfiguration();
//        if (configuration == null && !StringUtils.hasText(this.properties.getConfigLocation())) {
//            configuration = new Configuration();
//        }
//        if (configuration != null && !CollectionUtils.isEmpty(this.configurationCustomizers)) {
//            for (ConfigurationCustomizer customizer : this.configurationCustomizers) {
//                customizer.customize(configuration);
//            }
//        }
//        factory.setConfiguration(configuration);
//        if (this.properties.getConfigurationProperties() != null) {
//            factory.setConfigurationProperties(this.properties.getConfigurationProperties());
//        }
//        if (!ObjectUtils.isEmpty(this.interceptors)) {
//            factory.setPlugins(this.interceptors);
//        }
//        if (this.databaseIdProvider != null) {
//            factory.setDatabaseIdProvider(this.databaseIdProvider);
//        }
//        if (StringUtils.hasLength(this.properties.getTypeAliasesPackage())) {
//            factory.setTypeAliasesPackage(this.properties.getTypeAliasesPackage());
//        }
//        if (StringUtils.hasLength(this.properties.getTypeHandlersPackage())) {
//            factory.setTypeHandlersPackage(this.properties.getTypeHandlersPackage());
//        }
//        if (!ObjectUtils.isEmpty(this.properties.resolveMapperLocations())) {
//            factory.setMapperLocations(this.properties.resolveMapperLocations());
//        }
//
//        return factory.getObject();
//    }
//

    @Bean
    public SqlSessionTemplate getSqlSessionTemplate() {
        return new SqlSessionTemplate(getSqlSessionFactory());
    }
//    @Bean
//    public MybatisPlusProperties getSqlSessionFactory(){
//        try {
//            MybatisPlusProperties sqlSessionFactoryBean = new MybatisPlusProperties();
//            return sqlSessionFactoryBean;
//        }catch (Exception e){
//            e.printStackTrace();
//            return null;
//        }
//    }

}
