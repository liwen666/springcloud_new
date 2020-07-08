package com.example;

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

    public static void main(String[] args) {

        SpringApplication.run(SpringBootEurekaApplication.class, args);
        try {
            ChatServer.main(args);
            if (KafkaReceiver.websocket==null) {
                KafkaReceiver. websocket = new ExampleClient(new URI("ws://localhost:8887"));
                KafkaReceiver.  websocket.connect();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

}