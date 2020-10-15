package com.example.conf;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.java_websocket.client.WebSocketClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.net.URISyntaxException;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class KafkaReceiver {
    public static WebSocketClient websocket;

    public static Map<String,String> colar = new ConcurrentHashMap<>();


    private static Logger logger = LoggerFactory.getLogger(KafkaReceiver.class);

    /****
     *
     * 添加测试计数器
     *
     */
    private AtomicLong count = new AtomicLong(0);

    private long firstTime;
    private long tmpTIme;

    public KafkaReceiver() throws URISyntaxException {
    }

    @KafkaListener(topics = {"hello"})
    public void check(ConsumerRecord<?, ?> record)   {
        Optional<?> kafkaMessage = Optional.ofNullable(record.value());
        if (kafkaMessage.isPresent()) {
            Object message = kafkaMessage.get();
            logger.info("---------hello--------- message =" + message);
        }

    }

}
