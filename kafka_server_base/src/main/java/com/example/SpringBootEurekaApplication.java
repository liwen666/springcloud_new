package com.example;

import com.alibaba.fastjson.JSON;
import com.example.conf.JrxMaxwellConfig;
import com.example.conf.KafkaReceiver;
import com.example.websocket.ChatServer;
import com.example.websocket.ExampleClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.kafka.listener.ContainerProperties;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

//@EnableEurekaServer
@SpringBootApplication
public class SpringBootEurekaApplication {

    public static void main(String[] args) throws InterruptedException {
        int port = 40000; // 843 flash policy port
        for (String str : args) {
            if (str.startsWith("--socket")) {
                port = Integer.parseInt(str.split("=")[1]);
            }
        }
        System.out.println(JSON.toJSONString(args));
        System.out.println("-------------------------socket_port："+port+"----------------------------------");
        SpringApplication.run(SpringBootEurekaApplication.class, args);
        int finalPort = port;
        new Thread(()->{
            try {
                ChatServer.main(args);
                if (KafkaReceiver.websocket==null) {
                    KafkaReceiver. websocket = new ExampleClient(new URI("ws://localhost:"+ finalPort));
                    KafkaReceiver.  websocket.connect();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }).start();

    }

}