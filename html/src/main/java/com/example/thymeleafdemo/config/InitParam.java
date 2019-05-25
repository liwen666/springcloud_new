package com.example.thymeleafdemo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.UUID;

@Component
public class InitParam implements ApplicationRunner {
@Autowired
private DataSource dataSource;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        UeduitorManager.dataSource=dataSource;
        System.out.println(dataSource);


        Connection conn = dataSource.getConnection();
        Statement stmt = conn.createStatement();
        //如果存在USER_INFO表就先删除USER_INFO表
        stmt.execute("DROP TABLE IF EXISTS USER_INFO");
        //创建USER_INFO表
        stmt.execute("CREATE TABLE USER_INFO(id VARCHAR(36) PRIMARY KEY,name VARCHAR(100),sex VARCHAR(4))");
        //新增
        stmt.executeUpdate("INSERT INTO USER_INFO VALUES('" + UUID.randomUUID()+ "','大日如来','男')");
        stmt.executeUpdate("INSERT INTO USER_INFO VALUES('" + UUID.randomUUID()+ "','青龙','男')");
        stmt.executeUpdate("INSERT INTO USER_INFO VALUES('" + UUID.randomUUID()+ "','白虎','男')");
        stmt.executeUpdate("INSERT INTO USER_INFO VALUES('" + UUID.randomUUID()+ "','朱雀','女')");
        stmt.executeUpdate("INSERT INTO USER_INFO VALUES('" + UUID.randomUUID()+ "','玄武','男')");
        stmt.executeUpdate("INSERT INTO USER_INFO VALUES('" + UUID.randomUUID()+ "','苍狼','男')");
        //删除
        stmt.executeUpdate("DELETE FROM USER_INFO WHERE name='大日如来'");
        //修改
        stmt.executeUpdate("UPDATE USER_INFO SET name='孤傲苍狼' WHERE name='苍狼'");
        //查询
        ResultSet rs = stmt.executeQuery("SELECT * FROM USER_INFO");
        //遍历结果集
        while (rs.next()) {
            System.out.println(rs.getString("id") + "," + rs.getString("name")+ "," + rs.getString("sex"));
        }
        stmt.execute("DROP TABLE IF EXISTS hq_protal_bytearray");
        //创建USER_INFO表
        stmt.execute("create table hq_protal_bytearray \n" +
                "(\n" +
                "   id                 VARCHAR2(64),\n" +
                "   name               VARCHAR2(64),\n"+
                "   bytes              BLOB,\n" +
//                "   bytes              longvarbinary,\n" +
                "   belongid           VARCHAR2(64),\n" +
                "   belongtype         VARCHAR2(64),\n" +
                "   time               TIMESTAMP(6)\n" +
                ");");
        System.out.println("初始化表 "+ "hq_protal_bytearray");
        //释放资源
        stmt.close();
        conn.close();


    }
}
