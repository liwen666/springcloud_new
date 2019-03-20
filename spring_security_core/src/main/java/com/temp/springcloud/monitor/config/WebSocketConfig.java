package com.temp.springcloud.monitor.config;


import com.temp.springcloud.monitor.domin.LogMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import javax.annotation.PostConstruct;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 配置WebSocket消息代理端点，即stomp服务端
 * @author jie
 * @date 2018-12-24
 */
@Slf4j
@Configuration
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/websocket")
                .setAllowedOrigins("*")
                .withSockJS();
    }

    /**
     * 推送日志到/topic/pullLogger
     * PostConstruct 注释用于在依赖关系注入完成之后需要执行的方法上，以执行任何初始化
     * 方法上不能有参数
     */
    @PostConstruct
    public void pushLogger(){
        ExecutorService executorService= Executors.newFixedThreadPool(2);
        Runnable runnable=new Runnable() {

            @Override
            public void run() {
                while (true) {
                    try {
                        LogMessage log = LoggerQueue.getInstance().poll();
                        if(log!=null){
                            if(log.getClassName().equals("jdbc.resultsettable")){
                                log.setBody("<br><pre>"+log.getBody()+"</pre>");
                            }
                            if(messagingTemplate!=null){
                                messagingTemplate.convertAndSend("/topic/logMsg",log);
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        executorService.submit(runnable);
        executorService.submit(runnable);
    }
}