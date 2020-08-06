package jrx.anyest.table.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import jrx.anyest.table.jpa.dao.TableCodeConfigRepository;
import jrx.anyest.table.jpa.entity.TableCodeConfig;
import jrx.anyest.table.jpa.enums.TableDataTypeEnum;
import jrx.anyest.table.listener.ITableImportListener;
import jrx.anyest.table.service.JdbcTemplateService;
import jrx.anyest.table.service.TableDataCodeCacheManager;
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
import org.springframework.jdbc.core.RowCountCallbackHandler;

import javax.sql.DataSource;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

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
        url = standardEnvironment.getProperty("spring.datasource.url");
        username = standardEnvironment.getProperty("spring.datasource.username");
        password = standardEnvironment.getProperty("spring.datasource.password");
        driverClassName = standardEnvironment.getProperty("spring.datasource.driver-class-name");

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

        initCacheData();

    }

    /**
     * 系统缓存初始化方法，有特殊情况可以手动刷新
     */
    public void initCacheData() {
        String datasource = TableSpringUtil.getBean(TablePropertiesConfig.class).getDatasource();
        List<Map<String, Object>> maps = JdbcTemplateService.jdbcTemplate.queryForList("select  table_name,column_name from  INFORMATION_SCHEMA.KEY_COLUMN_USAGE  t where t.table_schema='" + datasource + "'");
        Map<String, String> collect = maps.stream().collect(Collectors.toMap(e -> (String) e.get("table_name"), e -> (String) e.get("column_name")));
        TableDataCodeCacheManager.tableKey = collect;
        logger.info("----------初始化数据库主键缓存已完成 database:{}-------------", datasource);
        initColumnType(datasource, TableDataTypeEnum.MYSQL);
        logger.info("-----------初始化表字段类型已完成---------------");
        Collection<ITableImportListener> values = TableSpringUtil.getBeansByInteface(ITableImportListener.class).values();
        TableDataExpOrImpService tableDataExpOrImpService = TableSpringUtil.getBean(TableDataExpOrImpService.class);
        tableDataExpOrImpService.setTableImportListeners(values);
        logger.info("-----------装配数据处理监听器已完成: size:{}------------", values.size());
        TableCodeConfigRepository tableCodeConfigRepository = TableSpringUtil.getBean(TableCodeConfigRepository.class);
        TableDataCodeCacheManager.tableCodeConfigs = tableCodeConfigRepository.findAll().stream().filter(TableCodeConfig::isUsed).collect(Collectors.toMap(TableCodeConfig::getTableCodeName, Function.identity()));
        logger.info("-----------初始化code系统配置已完成: size:{}------------", TableDataCodeCacheManager.tableCodeConfigs.size());
    }

    private void initColumnType(String datasource, TableDataTypeEnum tableDataTypeEnum) {
        List<String> tables = JdbcTemplateService.jdbcTemplate.queryForList("select table_name from information_schema.tables where table_schema='" + datasource + "'", String.class);
        /**
         * 获取表字段类型
         */
        tables.forEach(table -> {
            List<Map<String, Object>> maps;
            if (tableDataTypeEnum == TableDataTypeEnum.MYSQL) {
                maps = JdbcTemplateService.jdbcTemplate.queryForList("desc " + table);
            } else {
                throw new RuntimeException("暂不支持其他数据库！");
            }
            if (TableDataCodeCacheManager.tableColumns.get(table) == null) {
                TableDataCodeCacheManager.tableColumns.put(table, Maps.newConcurrentMap());
            }
            maps.forEach(column -> {
                TableDataCodeCacheManager.tableColumns.get(table).put((String) column.get("Field"), (String) column.get("Type"));
            });
        });
    }
}