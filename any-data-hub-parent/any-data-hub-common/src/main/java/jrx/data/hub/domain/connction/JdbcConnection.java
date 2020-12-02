package jrx.data.hub.domain.connction;

import com.alibaba.fastjson.JSONObject;
import com.zaxxer.hikari.HikariDataSource;
import jrx.data.hub.domain.enums.DbType;
import jrx.data.hub.domain.exception.DataSourceException;
import jrx.data.hub.infrastructure.entity.MetaDataSourceInfo;
import jrx.data.hub.infrastructure.entity.MetaObjectField;
import jrx.data.hub.infrastructure.remote.IDataSourceConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.util.StringUtils;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.List;

/**
 * 数据jdbc的操作连接
 */
public class JdbcConnection implements IDataSourceConnection<HikariDataSource> {

    private static final Logger logger = LoggerFactory.getLogger(JdbcConnection.class);

    protected MetaDataSourceInfo metaDataSourceInfo;
    protected JdbcTemplate jdbcTemplate;
    protected HikariDataSource dataSource;
    protected String url;
    protected String password;
    protected String userName;
    protected String driverClass;

    public JdbcConnection(String url, String userName, String password, String driverClass) {
        this.url = url;
        this.password = password;
        this.userName = userName;
        this.driverClass = driverClass;
    }

    public JdbcConnection(MetaDataSourceInfo metaDataSourceInfo) {
        initDataSource();
        this.metaDataSourceInfo = metaDataSourceInfo;
        this.userName = JSONObject.parseObject(metaDataSourceInfo.getDbConfigJson()).getString("user");
        this.password = JSONObject.parseObject(metaDataSourceInfo.getDbConfigJson()).getString("password");
        this.url = JSONObject.parseObject(metaDataSourceInfo.getDbConfigJson()).getString("url");
        if (metaDataSourceInfo.getDbType() == DbType.MYSQL) {
            this.driverClass = "com.mysql.jdbc.Driver";
        } else if (metaDataSourceInfo.getDbType() == DbType.GREENPLUM) {

            if (url.startsWith("jdbc:pivotal:greenplum")) {
                this.driverClass = "com.pivotal.jdbc.GreenplumDriver";
            } else if (url.startsWith("jdbc:postgresql")) {
                this.driverClass = "org.postgresql.Driver";
            }
        } else if (metaDataSourceInfo.getDbType() == DbType.HIVE) {
            this.driverClass = "org.apache.hive.jdbc.HiveDriver";
        }
    }

    public JdbcConnection(HikariDataSource dataSource) {
        this.dataSource = dataSource;
        this.userName = dataSource.getUsername();
        this.password = dataSource.getPassword();
        this.url = dataSource.getJdbcUrl();
        this.driverClass = dataSource.getDriverClassName();
        jdbcTemplate = new JdbcTemplate(this.dataSource);
    }

    public JdbcConnection() {

    }

    @Override
    public void connect(boolean force) throws DataSourceException {
        if (jdbcTemplate == null || force) {
            initDataSource();
            this.dataSource.setDriverClassName(this.driverClass);
            this.dataSource.setJdbcUrl(url);
            this.dataSource.setUsername(userName);
            this.dataSource.setPassword(password);
            jdbcTemplate = new JdbcTemplate(this.dataSource);
        }
    }

    public void setMetaDataSourceInfo(MetaDataSourceInfo metaDataSourceInfo) {
        this.metaDataSourceInfo = metaDataSourceInfo;
    }

    @Override
    public HikariDataSource getConnect() {
        return dataSource;
    }

    @Override
    public void close() {
        this.jdbcTemplate = null;
        //不要close
        this.dataSource.close();
    }

    @Override
    public DbType getDbType() {
        return DbType.JDBC;
    }


    @Override
    public boolean existTable(String tableName) {
        if (StringUtils.isEmpty(tableName)) {
            logger.error("tablename is null");
            return true;
        }
        ResultSet tabs = null;
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            DatabaseMetaData dbMetaData = connection.getMetaData();
            String[] types = {"TABLE"};
            tabs = dbMetaData.getTables(null, null, tableName.toLowerCase(), types);
            if (tabs.next()) {
                return true;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (tabs != null) {
                    tabs.close();
                }
            } catch (Exception e) {
            }

        }
        return false;
    }

    @Override
    public Boolean connectionTest() throws DataSourceException{
        connect(false);
        DataSourceUtils.getConnection(dataSource);
        return true;
    }

    /**
     * 获取数据库名称
     *
     * @return
     */
    public String getDataBase() {
        String url;
        if (metaDataSourceInfo != null) {
            url = JSONObject.parseObject(metaDataSourceInfo.getDbConfigJson()).getString("url");
        } else {
            url = dataSource.getJdbcUrl();
        }
        //jdbc:mysql://localhost:3306/iso_db?useUnicode=true&characterEncoding=UTF-8
        String database = url.substring(url.lastIndexOf("/") + 1);
        int index = database.indexOf("?");
        if (index > 0) {
            database = database.substring(0, index);
        }
        return database;
    }

    /**
     * 初始化数据源
     */
    protected void initDataSource() {
        this.dataSource = new HikariDataSource();
        this.dataSource.setConnectionTestQuery("SELECT 1");
        this.dataSource.setConnectionTimeout(60000);
        this.dataSource.setMinimumIdle(5);
        this.dataSource.setMaximumPoolSize(20);
        //连接超过最大时长，会被连接池剔除，但不会新增连接
        this.dataSource.setMaxLifetime(1200000);
        this.dataSource.setValidationTimeout(5000);
        this.dataSource.setIdleTimeout(60000);
        this.dataSource.setLeakDetectionThreshold(600000);
    }
}
