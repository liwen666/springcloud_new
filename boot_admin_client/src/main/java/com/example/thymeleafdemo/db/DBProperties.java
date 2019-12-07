//package com.example.thymeleafdemo.db;
//
//import com.zaxxer.hikari.HikariDataSource;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.stereotype.Component;
//
//@Component
//@ConfigurationProperties(prefix = "hikari")
//public class DBProperties {
//    private HikariDataSource master;
//    private HikariDataSource slave;
//
//    public HikariDataSource getMaster() {
//        return master;
//    }
//
//    public void setMaster(HikariDataSource master) {
//        this.master = master;
//    }
//
//    public HikariDataSource getSlave() {
//        return slave;
//    }
//
//    public void setSlave(HikariDataSource slave) {
//        this.slave = slave;
//    }
//}