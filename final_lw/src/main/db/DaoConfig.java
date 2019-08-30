//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package vip.dcpay.cache.domain.config.db;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import vip.dcpay.util.frame.spring.PropertiesConfigurer;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Order(1)
@Configuration
@Import({MyBatisConfig.class})
public class DaoConfig {
    private static final Logger log = LoggerFactory.getLogger(DaoConfig.class);

    public DaoConfig() {
    }

    @Bean
    public DruidDataSource platformDataSource() {
        PropertiesConfigurer prop = this.dbPropertiesConfigurer();
        DruidDataSource d = new DruidDataSource();
        initDataSource(prop.getProperty("db.platform.url"), prop.getProperty("db.platform.username"), prop.getProperty("db.platform.password"), d, prop);
        return d;
    }

    @Bean
    public DataSource merchantDataSource() {
        DruidDataSource d = new DruidDataSource();
        PropertiesConfigurer prop = this.dbPropertiesConfigurer();
        initDataSource(prop.getProperty("db.merchant.url"), prop.getProperty("db.merchant.username"), prop.getProperty("db.merchant.password"), d, prop);
        return d;
    }

    @Bean
    public DataSource orderDataSource() {
        DruidDataSource d = new DruidDataSource();
        PropertiesConfigurer prop = this.dbPropertiesConfigurer();
        initDataSource(prop.getProperty("db.order.url"), prop.getProperty("db.order.username"), prop.getProperty("db.order.password"), d, prop);
        return d;
    }

    @Bean
    @Primary
    public DataSource baseDataSource() {
        DruidDataSource d = new DruidDataSource();
        PropertiesConfigurer prop = this.dbPropertiesConfigurer();
        initDataSource(prop.getProperty("db.base.url"), prop.getProperty("db.base.username"), prop.getProperty("db.base.password"), d, prop);
        return d;
    }

    @Bean
    public DataSource commonDataSource() {
        DruidDataSource d = new DruidDataSource();
        PropertiesConfigurer prop = this.dbPropertiesConfigurer();
        initDataSource(prop.getProperty("db.common.url"), prop.getProperty("db.common.username"), prop.getProperty("db.common.password"), d, prop);
        return d;
    }

    @Bean
    public DataSource scheduleDataSource() {
        DruidDataSource d = new DruidDataSource();
        PropertiesConfigurer prop = this.dbPropertiesConfigurer();
        initDataSource(prop.getProperty("db.schedule.url"), prop.getProperty("db.schedule.username"), prop.getProperty("db.schedule.password"), d, prop);
        return d;
    }

    @Bean
    public DataSource slaveBaseDataSource() {
        DruidDataSource d = new DruidDataSource();
        PropertiesConfigurer prop = this.dbPropertiesConfigurer();
        initDataSource(prop.getProperty("db.slave.base.url"), prop.getProperty("db.slave.base.username"), prop.getProperty("db.base.password"), d, prop);
        return d;
    }

    @Bean(
        name = {"myRoutingDataSource"}
    )
    public DataSource myRoutingDataSource(@Qualifier("platformDataSource") DataSource platformDataSource, @Qualifier("merchantDataSource") DataSource merchantDataSource, @Qualifier("orderDataSource") DataSource orderDataSource, @Qualifier("baseDataSource") DataSource baseDataSource, @Qualifier("commonDataSource") DataSource commonDataSource, @Qualifier("scheduleDataSource") DataSource scheduleDataSource, @Qualifier("slaveBaseDataSource") DataSource slaveBaseDataSource) {
        PropertiesConfigurer prop = this.dbPropertiesConfigurer();
        String default_database = prop.getProperty("db.default.database");
        if (default_database != null && DBTypeEnum.getEnum(default_database) != null) {
            Map<Object, Object> targetDataSources = new HashMap();
            targetDataSources.put(DBTypeEnum.PLATFORM, platformDataSource);
            targetDataSources.put(DBTypeEnum.MERCHANT, merchantDataSource);
            targetDataSources.put(DBTypeEnum.ORDER, orderDataSource);
            targetDataSources.put(DBTypeEnum.BASE, baseDataSource);
            targetDataSources.put(DBTypeEnum.COMMON, commonDataSource);
            targetDataSources.put(DBTypeEnum.SCHEDULE, scheduleDataSource);
            targetDataSources.put(DBTypeEnum.SLAVE_BASE, slaveBaseDataSource);
            MyRoutingDataSource myRoutingDataSource = new MyRoutingDataSource();
            myRoutingDataSource.setDefaultTargetDataSource(targetDataSources.get(DBTypeEnum.getEnum(default_database)));
            myRoutingDataSource.setTargetDataSources(targetDataSources);
            return myRoutingDataSource;
        } else {
            log.error("配置文件jdbc.properties中,必选项【db.default.database】不可是空 ");
            throw new RuntimeException("请在jdbc.properties配置文件中，通过db.default.database来指定默认数据库");
        }
    }

    @Bean
    public PropertiesConfigurer dbPropertiesConfigurer() {
        PropertiesConfigurer prop = new PropertiesConfigurer();
        prop.setIgnoreUnresolvablePlaceholders(true);
        prop.setIgnoreResourceNotFound(true);
        prop.setLocations(new Resource[]{new ClassPathResource("jdbc.properties")});
        return prop;
    }

    public static void initDataSource(String url, String username, String password, DruidDataSource d, PropertiesConfigurer prop) {
        int maxActive = prop.getInteger("db.maxActive", 1000);
        int initialSize = prop.getInteger("db.initialSize", 1);
        int minIdle = prop.getInteger("db.minIdle", 1);
        long maxWaitMillis = prop.getLong("db.maxWaitMillis", 60000L);
        int timeBetweenEvictionRunsMillis = prop.getInteger("db.timeBetweenEvictionRunsMillis", 60000);
        int minEvictableIdleTimeMillis = prop.getInteger("db.minEvictableIdleTimeMillis", 300000);
        boolean testWhileIdle = prop.getBoolean("db.testWhileIdle", true);
        boolean testOnBorrow = prop.getBoolean("db.testOnBorrow", false);
        boolean testOnReturn = prop.getBoolean("db.testOnReturn", false);
        boolean poolPreparedStatements = prop.getBoolean("db.poolPreparedStatements", true);
        int maxOpenPreparedStatements = prop.getInteger("db.maxOpenPreparedStatements", 20);
        boolean asyncInit = prop.getBoolean("db.asyncInit", true);
        String filters = prop.getProperty("db.filters", "slf4j");
        String connectionProperties = prop.getProperty("db.connectionProperties");
        Properties p = null;
        if (StringUtils.isNoneBlank(new CharSequence[]{connectionProperties})) {
            String[] arr = connectionProperties.split(";");
            if (null != arr && arr.length > 0) {
                String[] var22 = arr;
                int var23 = arr.length;

                for(int var24 = 0; var24 < var23; ++var24) {
                    String s = var22[var24];
                    String[] pair = s.split("=");
                    if (null != pair && pair.length > 0) {
                        if (null == p) {
                            p = new Properties();
                        }

                        p.put(pair[0], pair[1]);
                    }
                }
            }
        }

        if (StringUtils.isAnyBlank(new CharSequence[]{url, username, password})) {
            log.error("配置文件jdbc.properties中,必选项【db.url | db.username | db.password】不可是空 ");
            throw new NullPointerException("配置项非法");
        } else {
            d.setUrl(url);
            d.setUsername(username);
            d.setPassword(password);
            d.setDriverClassName("com.mysql.jdbc.Driver");
            d.setInitialSize(initialSize);
            d.setMaxActive(maxActive);
            d.setMinIdle(minIdle);
            d.setMaxWait(maxWaitMillis);
            d.setPoolPreparedStatements(poolPreparedStatements);
            d.setMaxOpenPreparedStatements(maxOpenPreparedStatements);
            d.setValidationQuery("SELECT 1");
            d.setTestOnBorrow(testOnBorrow);
            d.setTestOnReturn(testOnReturn);
            d.setTestWhileIdle(testWhileIdle);
            d.setMinEvictableIdleTimeMillis((long)minEvictableIdleTimeMillis);
            d.setTimeBetweenEvictionRunsMillis((long)timeBetweenEvictionRunsMillis);
            d.setMinEvictableIdleTimeMillis((long)minEvictableIdleTimeMillis);
            d.setAsyncInit(asyncInit);
            if (null != p) {
                d.setConnectProperties(p);
            }

            try {
                d.addFilters(filters);
            } catch (SQLException var27) {
                log.error("配置文件jdbc.properties中,必选项【db.filters】设置异常", var27);
            }

        }
    }
}
