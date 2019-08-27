package vip.dcpay.h2.application;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import vip.dcpay.h2.infrastructure.model.MerchantInfo;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author dalaoyang
 * @project springboot_learn
 * @package com.dalaoyang.controller
 * @email yangyang@dalaoyang.cn
 * @date 2018/7/25
 */
@RestController
public class TestController {

    @Autowired
    @Qualifier("jdbcTemplate")
    private JdbcTemplate jdbcTemplate;
    @Autowired
    @Qualifier("mysqlJdbcTemplate")
    private JdbcTemplate mysqlTemplate;
    //http://localhost:8888/createTable
    @GetMapping("test/test")
    public String createTable() throws IllegalAccessException {
            int weixin = jdbcTemplate.update(getInsertSql(MerchantInfo.builder().recv_pay_ways("weixin").uid(123l).build()));
            List<MerchantInfo> query = jdbcTemplate.query("select * from merchant_info", new BeanPropertyRowMapper(MerchantInfo.class));
            System.out.println(JSON.toJSONString(query.get(0)));
            return JSON.toJSONString(query);
    }
    @GetMapping("test/mysql")
    public String mysql() throws IllegalAccessException {
        List<Map<String, Object>> maps = mysqlTemplate.queryForList("select * from merchant_group");
        System.out.println(JSON.toJSONString(maps.get(0)));
        return JSON.toJSONString(maps);
    }


    private String getInsertSql(MerchantInfo build) throws IllegalAccessException {
//        insert into MERCHANT_INFO (id,uid,type,realname) values (1,11,1,'我')
        String sql = "insert into MERCHANT_INFO (";
        Field[] declaredFields = build.getClass().getDeclaredFields();
        for(Field f:declaredFields){
            sql+=f.getName()+",";
        }
        sql =sql.substring(0,sql.length()-1)+") values( ";
        for(Field f:declaredFields){
            f.setAccessible(true);
            if(f.getType().getName().equals("java.lang.String")){
                sql+= "'"+f.get(build)+"',";
                continue;
            }
            sql+=f.get(build)+",";
        }
        sql =sql.substring(0,sql.length()-1)+")";
        return sql;
    }

}