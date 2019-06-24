package com.temp.springcloud.environment.config;

import org.h2.tools.Server;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

@Configuration
@ConfigurationProperties(prefix = "spring.h2cfg")
public class H2Service implements ApplicationListener<ContextClosedEvent> {
	Server server;
	Server serverWeb;
	private String name;

	public void setServer(String param) {
		try {
			System.out.println("正在启动h2...");
			server = Server.createTcpServer(new String[] { "-tcpAllowOthers", "-tcpPort",
					"8043" }).start();//其他电脑可以连接
			System.out.println("启动成功：" + server.getStatus());
			serverWeb = Server.createWebServer().start();
			System.out.println(serverWeb.getStatus());
		} catch (SQLException e) {
			System.out.println("启动h2出错：" + e.toString());

			e.printStackTrace();
		}
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
