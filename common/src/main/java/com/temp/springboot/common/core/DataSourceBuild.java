package com.temp.springboot.common.core;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: looyii
 * @Date: 2019/7/25 16:17
 * @Description:
 */
public class DataSourceBuild {

    public static final String DB_JDBCURL = "url";
    public static final String DB_USERNAME = "username";
    public static final String DB_PASS = "password";
    public static final String DB_TYPE = "type";
    public static final String DB_DRIVER = "driverClassName";
    public static final String DRIVER_MYSQL = "com.mysql.jdbc.Driver";
    public static final String DRIVER_MARIADB = "org.mariadb.jdbc.Driver";
    public static final String DRIVER_ORACLE = "oracle.jdbc.driver.OracleDriver";
    public static final String DRIVER_HSQLDB = "org.hsqldb.jdbcDriver";
    public static final String DRIVER_H2 = "org.h2.Driver";

    private static ConversionService conversionService = new DefaultConversionService();

    /**
     * 构建数据源
     *
     * @param dataSourceMap
     * @return
     */
    public static DataSource buildDataSource(Map<String, Object> dataSourceMap) {
        try {
            Object type = dataSourceMap.get(DB_TYPE) == null ? "com.zaxxer.hikari.HikariDataSource" : dataSourceMap.get(DB_TYPE);
            Class<? extends DataSource> dataSourceType;
            dataSourceType = (Class<? extends DataSource>) Class.forName((String) type);
            String driverClassName = dataSourceMap.get(DB_DRIVER).toString();
            String url = dataSourceMap.get(DB_JDBCURL).toString();
            String username = dataSourceMap.get(DB_USERNAME).toString();
            String password = dataSourceMap.get(DB_PASS).toString();
            // 自定义DataSource配置
            DataSourceBuilder factory = DataSourceBuilder.create().driverClassName(driverClassName).url(url)
                    .username(username).password(password).type(dataSourceType);
            return factory.build();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getDriver(String jdbcAddress) {
        if (jdbcAddress.indexOf("mysql") != -1) {
            return DRIVER_MYSQL;
        } else if (jdbcAddress.indexOf("mariadb") != -1) {
            return DRIVER_MARIADB;
        } else if (jdbcAddress.indexOf("oracle") != -1) {
            return DRIVER_ORACLE;
        } else if (jdbcAddress.indexOf("h2") != -1) {
            return DRIVER_H2;
        } else if (jdbcAddress.indexOf("hsqldb") != -1) {
            return DRIVER_HSQLDB;
        }
        return null;
    }


    /**
     * 初始化默认数据源
     *
     * @param env
     * @return
     */
    public static DataSource initDefaultDataSource(Environment env, String dsPrefixs) {

        // 读取主数据源
        Map<String, Object> dsMap = new HashMap<>();
        dsMap.put(DB_TYPE, env.getProperty(dsPrefixs + ".type"));
        dsMap.put(DB_DRIVER, env.getProperty(dsPrefixs + ".driver-class-name"));
        dsMap.put(DB_JDBCURL, env.getProperty(dsPrefixs + ".url"));
        dsMap.put(DB_USERNAME, env.getProperty(dsPrefixs + ".username"));
        dsMap.put(DB_PASS, env.getProperty(dsPrefixs + ".password"));
        DataSource dataSource = buildDataSource(dsMap);
//        dataBinder(dataSource, env, dsPrefixs);
        return dataSource;
    }

//
//    /**
//     * 为DataSource绑定更多其他配置数据
//     *
//     * @param dataSource
//     * @param env
//     */
//    private static void dataBinder(DataSource dataSource, Environment env, String dsPrefixs) {
//
//        RelaxedDataBinder dataBinder = new RelaxedDataBinder(dataSource);
//        dataBinder.setConversionService(conversionService);
//        dataBinder.setIgnoreNestedProperties(false);//false
//        dataBinder.setIgnoreInvalidFields(false);//false
//        dataBinder.setIgnoreUnknownFields(true);//true
//
//        Map<String, Object> rpr = new RelaxedPropertyResolver(env, dsPrefixs).getSubProperties(".");
//        Map<String, Object> values = new HashMap<>(rpr);
//        // 排除已经设置的属性
//        // type 统一用 hikari 连接池
//        values.remove(DB_TYPE);
//        values.remove(DB_DRIVER);
//        values.remove(DB_JDBCURL);
//        values.remove(DB_USERNAME);
//        values.remove(DB_PASS);
//        PropertyValues dataSourcePropertyValues = new MutablePropertyValues(values);
//        dataBinder.bind(dataSourcePropertyValues);
//
//    }

}
