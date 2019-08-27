package vip.dcpay.h2.domain.config;

import org.h2.tools.Server;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;
import vip.dcpay.h2.util.SpringContextUtil;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
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
                "        `uid` bigint(32) DEFAULT NULL,\n" +
                "        `type` int(10) DEFAULT NULL,\n" +
                "        `realname` varchar(255) DEFAULT NULL,\n" +
                "        `activate_status` int(10) DEFAULT NULL,\n" +
                "        `recv_pay_ways` varchar(2000) DEFAULT NULL,\n" +
                "        `assets` varchar(2000) DEFAULT NULL,\n" +
                "        `day_mount_sum` decimal(20,10) DEFAULT NULL\n" +
                ")");

        stmt.close();
        conn.close();
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
