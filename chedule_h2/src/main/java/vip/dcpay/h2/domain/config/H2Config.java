package vip.dcpay.h2.domain.config;

import org.h2.tools.Server;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;
import vip.dcpay.h2.infrastructure.model.MerchantInfo;
import vip.dcpay.h2.util.SpringContextUtil;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@Component
public class H2Config implements ApplicationRunner ,ApplicationListener<ContextClosedEvent> {
	Server server;
	Server serverWeb;

    @PostConstruct
    public void initH2(){
        System.out.println("============================================初始h2服务=============================================");
        try {
            server = Server.createTcpServer(new String[] { "-tcpAllowOthers", "-tcpPort",
                    "8043" }).start();//其他电脑可以连接
            System.out.println("启动成功：" + server.getStatus());
//            serverWeb = Server.createWebServer().start();
            serverWeb = Server.createWebServer("-webAllowOthers").start();
            System.out.println(serverWeb.getStatus());
        } catch (SQLException e) {
            System.out.println("启动h2出错：" + e.toString());
            e.printStackTrace();
        }

    }

    @Override
	public void run(ApplicationArguments args) throws Exception {
        System.out.println("=======================================初始化表结构==================================================");
        DataSource dataSource = SpringContextUtil.getBean("dataSource",DataSource.class);
        Connection conn = dataSource.getConnection();
        Statement stmt = conn.createStatement();
        long startTime = System.currentTimeMillis();
        long count = 10;
        //如果存在USER_INFO表就先删除USER_INFO表
        stmt.execute("DROP TABLE IF EXISTS merchant_info");
        //创建USER_INFO表
        stmt.execute("  CREATE TABLE `merchant_info` (\n" +
                "        `id` bigint(32) DEFAULT NULL,\n" +
                "        `uid` bigint(32) DEFAULT NULL,\n" +
                "        `type` int(10) DEFAULT NULL,\n" +
                "        `realname` varchar(255) DEFAULT NULL,\n" +
                "        `activate_status` int(10) DEFAULT NULL,\n" +
                "        `recv_pay_ways` varchar(2000) DEFAULT NULL,\n" +
                "        `assets` varchar(2000) DEFAULT NULL,\n" +
                "        `day_mount_sum` decimal(20,10) DEFAULT NULL,\n" +
                "        `day_order_count` bigint(32)  DEFAULT NULL\n" +
                ")");
        stmt.execute("DROP TABLE IF EXISTS merchant_info_cache");
        stmt.execute("  CREATE TABLE `merchant_info_cache` (\n" +
                "        `id` bigint(32) DEFAULT NULL,\n" +
                "        `uid` bigint(32) DEFAULT NULL,\n" +
                "        `type` int(10) DEFAULT NULL,\n" +
                "        `grab` int(1) DEFAULT NULL,\n" +
                "        `player_deposit` int(1) DEFAULT NULL,\n" +
                "        `platform_withdraw` int(1) DEFAULT NULL,\n" +
                "        `merchant_deposit` int(1) DEFAULT NULL,\n" +
                "        `merchant_withdraw` int(1) DEFAULT NULL,\n" +
                "        `realname` varchar(255) DEFAULT NULL,\n" +
                "        `activate_status` int(10) DEFAULT NULL,\n" +
//                    "        `payments` CLOB (6000) DEFAULT NULL,\n" +
//                    "        `payment_choices` CLOB (6000) DEFAULT NULL,\n" +
//                    "        `assets` CLOB(6000) DEFAULT NULL,\n" +
                "        `payments` varchar (255) DEFAULT NULL,\n" +
                "        `payment_choices` varchar (255) DEFAULT NULL,\n" +
                "        `assets` varchar(1000) DEFAULT NULL,\n" +
                "        `day_order_count` bigint(32) DEFAULT NULL,\n" +
                "        `day_mount_sum` decimal(20,10) DEFAULT NULL\n" +
                ")");

        initdata(stmt);

        stmt.close();
        conn.close();
	}

    private void initdata(Statement stmt) throws IllegalAccessException, SQLException {


        for (int i = 0; i < 10000; i++) {
            String sql = getInsertSql(MerchantInfo.builder().activate_status(1).assets("{}").day_mount_sum(new BigDecimal(1000))
                    .uid((long) i)
                    .realname("test").recv_pay_ways("weixin").type(1).build());
            stmt.executeUpdate(sql);

        }
    }
    public   String getInsertSql(MerchantInfo build) throws IllegalAccessException {
//        insert into MERCHANT_INFO (id,uid,type,realname) values (1,11,1,'我')
        String sql = "insert into MERCHANT_INFO (";
        Field[] declaredFields = build.getClass().getDeclaredFields();
        for (Field f : declaredFields) {
            sql += f.getName() + ",";
        }
        sql = sql.substring(0, sql.length() - 1) + ") values( ";
        for (Field f : declaredFields) {
            f.setAccessible(true);
            if (f.getType().getName().equals("java.lang.String")) {
                sql += "'" + f.get(build) + "',";
                continue;
            }
            sql += f.get(build) + ",";
        }
        sql = sql.substring(0, sql.length() - 1) + ")";
        return sql;
    }


    @Override
	public void onApplicationEvent(ContextClosedEvent contextClosedEvent) {
		if (server != null) {
            System.out.println("正在关闭h2...");
			server.stop();
            System.out.println("关闭成功.");
		}
		if (serverWeb != null) {
            System.out.println("正在关闭h2...");
			serverWeb.stop();
            System.out.println("关闭成功.");
		}
	}
}
