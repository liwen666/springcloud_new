package com.temp.springcloud;

import io.jsonwebtoken.impl.TextCodec;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.util.Base64Utils;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;

/**
 * @author jie
 * @date 2018/11/15 9:20:19
 */
@SpringBootApplication
//开启定时任务
@EnableScheduling
@EnableTransactionManagement
@EnableWebSocketMessageBroker
public class AppRun {

    public static void main(String[] args) {
        SpringApplication.run(AppRun.class, args);
        System.out.println("service start success");

    }
    static class Base64{
        public static void main(String[] args) {
            String string = Base64Utils.encodeToString("mySecret".getBytes());
            System.out.println(string);
            String payload = TextCodec.BASE64URL.decodeToString(string);
            System.out.println(payload);
            String userNameAndPassword = Base64Utils.encodeToString("{\"userName\":\"admin\",\"password\":\"123456\"}".getBytes());
            System.out.println(userNameAndPassword);
//            eyJhbGciOiJIUzUxMiJ9
//            eyJzdWIiOiJhZG1pbiIsImV4cCI6MTU1MzY3NzI5OCwiaWF0IjoxNTUzNjczNjk4fQ
//            {"sub":"admin","exp":1553677298,"iat":1553673698}
        }
    }

}
