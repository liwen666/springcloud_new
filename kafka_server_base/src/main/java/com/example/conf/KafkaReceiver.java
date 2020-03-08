package com.example.conf;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class KafkaReceiver {
	
	private static Logger logger = LoggerFactory.getLogger(KafkaReceiver.class);
	
//	@KafkaListener(topics = {"test"})
	@KafkaListener(topics = {"testcsm"})
    public void listen(ConsumerRecord<?, ?> record) {
        Optional<?> kafkaMessage = Optional.ofNullable(record.value());
        if (kafkaMessage.isPresent()) {
            Object message = kafkaMessage.get();
//            logger.info("------testcsm----------- record =" + record);
            logger.info("---------testcsm--------- message =" + message);
        }

    }

    @KafkaListener(topics = {"ddl_topic"})
    public void maxwell(ConsumerRecord<?, ?> record) {
        Optional<?> kafkaMessage = Optional.ofNullable(record.value());
        if (kafkaMessage.isPresent()) {
            Object message = kafkaMessage.get();
//            logger.info("------ddl_topic----------- record =" + record);
            logger.info("---------ddl_topic--------- message =" + message);
        }

    }
}
