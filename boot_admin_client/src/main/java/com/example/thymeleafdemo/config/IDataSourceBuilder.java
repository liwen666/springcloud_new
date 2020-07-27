//package com.example.thymeleafdemo.db;
//
//import javax.sql.DataSource;
//import java.util.Map;
//
///**
// * @author
// * @date 2018/7/12
// */
//public interface IDataSourceBuilder {
//
//
//    String DB_JDBCURL = "jdbcUrl";
//    String DB_USERNAME = "username";
//    String DB_PASS = "password";
//    String DB_DRIVER = "driverClassName";
//    String DRIVER_MYSQL = "com.mysql.jdbc.Driver";
//    String DRIVER_MARIADB = "org.mariadb.jdbc.Driver";
//    String DRIVER_ORACLE = "oracle.jdbc.driver.OracleDriver";
//    String DRIVER_HSQLDB = "org.hsqldb.jdbcDriver";
//    String DRIVER_H2 = "org.h2.Driver";
//
//    default String getDriver(String jdbcAddress){
//        if(jdbcAddress.indexOf("mysql")!=-1){
//            return DRIVER_MYSQL;
//        }else if(jdbcAddress.indexOf("mariadb")!=-1){
//            return DRIVER_MARIADB;
//        }else if(jdbcAddress.indexOf("oracle") != -1){
//            return DRIVER_ORACLE;
//        }else if(jdbcAddress.indexOf("h2") != -1){
//            return DRIVER_H2;
//        }else if(jdbcAddress.indexOf("hsqldb") !=-1){
//            return DRIVER_HSQLDB;
//        }
//        return null;
//    }
//
//
//    DataSource build(Map<String, String> properties);
//
//    DataSource build();
//
//    DataSource build(String dbName);
//
//    DataSource build(String dbAddress, String userName, String password, String params, String dbName);
//
//    Map<String, String> getProps();
//
//    void setProps(Map<String, String> props);
//
//
//}
