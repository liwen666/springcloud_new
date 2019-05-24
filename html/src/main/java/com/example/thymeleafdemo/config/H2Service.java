package com.example.thymeleafdemo.config;

import org.h2.tools.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.SQLException;

@Component
public class H2Service implements ApplicationRunner ,ApplicationListener<ContextClosedEvent> {
	Server server;
	Server serverWeb;
  public  void startH2(){
	  try {
		  System.out.println("正在启动h2...");
//              server = Server.createTcpServer(new String[] {  "-tcpPort",
		  server = Server.createTcpServer(new String[] { "-tcpAllowOthers", "-tcpPort",
				  "8043" }).start();//其他电脑可以连接
//            Server server = Server.createTcpServer(
//            		new String[] { "-tcp", "-tcpAllowOthers", "-tcpPort",
//            		"8043" }).start();
		  System.out.println("启动成功：" + server.getStatus());
		  serverWeb = Server.createWebServer().start();
		  System.out.println(serverWeb.getStatus());
		  /**
		   * 正在启动h2...
		   启动成功：Web Console server running at http://192.168.9.180:8082 (only local connections)
		   */
	  } catch (SQLException e) {
		  System.out.println("启动h2出错：" + e.toString());

		  e.printStackTrace();
	  }
  }
	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println("启动初始化");
//		try {
//			System.out.println("正在启动h2...");
////              server = Server.createTcpServer(new String[] {  "-tcpPort",
//			server = Server.createTcpServer(new String[] { "-tcpAllowOthers", "-tcpPort",
//					"8043" }).start();//其他电脑可以连接
////            Server server = Server.createTcpServer(
////            		new String[] { "-tcp", "-tcpAllowOthers", "-tcpPort",
////            		"8043" }).start();
//			System.out.println("启动成功：" + server.getStatus());
//			serverWeb = Server.createWebServer().start();
//			System.out.println(serverWeb.getStatus());
//			/**
//			 * 正在启动h2...
//			 启动成功：Web Console server running at http://192.168.9.180:8082 (only local connections)
//			 */
//		} catch (SQLException e) {
//			System.out.println("启动h2出错：" + e.toString());
//
//			e.printStackTrace();
//		}
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
		System.out.println("关闭");
	}
}
