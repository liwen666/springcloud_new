package jrx.batch.dataflow.domain.config.batch;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import jrx.batch.dataflow.domain.exception.DataSourceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.DefaultApplicationArguments;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 使用Hikari数据库连接池配置
 * @author yuanxingyu
 * @date 2018/3/28
 */
@ConfigurationProperties(prefix = "schedule.center.jdbc.datasource")
public class ScheduleCenterHikariDataSourceBuilder implements IDataSourceBuilder {

    private static final Logger logger = LoggerFactory.getLogger(ScheduleCenterHikariDataSourceBuilder.class);

    private Map<String, String> props = new HashMap<>();

    private Map<String,DataSource> cache = new ConcurrentHashMap<>();

    private static final String JDBC_URL = "jdbcUrl";

    @Autowired
    private DefaultApplicationArguments applicationArguments;

    /**
     * 基于properties的配置信息，创建hikari的datasource
     * 在配置文件中，默认配置建jdbc.hikari.datasource.*
     * 如果传入指定参数则覆盖配置中的默认配置
     *
     * @param properties
     * @return
     */
    public DataSource build(Properties properties) {
        Properties dbProp = new Properties();
        dbProp.putAll(props);
        if (properties != null) {
            dbProp.putAll(properties);
        }
        String url = props.get(JDBC_URL);
        if(cache.containsKey(url)){
            return cache.get(url);
        }else{
            try {
                HikariDataSource dataSource = new HikariDataSource(new HikariConfig(dbProp));
                cache.put(url,dataSource);
                return dataSource;
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        }

        return null;
    }

    @Override
    public DataSource build(Map<String, String> properties) {
        Properties prop = new Properties();
        prop.putAll(properties);
        return build(prop);
    }

    @Override
    public DataSource build() {
        if(applicationArguments!=null){
//            return build(dbAddress,userName,password,dbName,params);
        }
        return build(new Properties());
    }

    /**
     * url不带database信息，格式为jdbc:mysql://ip:port/{db}
     *
     * @param dbName
     * @return
     */
    @Override
    public DataSource build(String dbName) {
        String url = props.get(JDBC_URL);
        if (url == null) {
            throw new DataSourceException("database jdbcUrl is empty.");
        }
        url = url.replace("{db}", dbName);
        Properties prop = new Properties();
        prop.put(JDBC_URL, url);
        return build(prop);
    }

    @Override
    public DataSource build(String dbAddress, String userName, String password, String params, String dbName) {
        Map<String,String> props = new HashMap<>();
        if(dbName!=null){
            String jdbcUrl = dbAddress + "/" + dbName;
            if(params!=null){
                jdbcUrl += jdbcUrl +"?"+ params;
            }
            props.put(DB_JDBCURL,jdbcUrl);
        }

        if(userName!=null){
            props.put(DB_USERNAME,userName);
        }
        if(password!=null){
            props.put(DB_PASS,password);
        }
        String driver = getDriver(dbAddress);
        if(driver!=null){
            props.put(DB_DRIVER,driver);
        }
        return build(props);
    }

    @Override
    public Map<String, String> getProps() {
        return props;
    }

    @Override
    public void setProps(Map<String, String> props) {
        this.props = props;
    }

}
