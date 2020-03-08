package com.example.conf;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MaxwellKafkaReceiver {
	
	private static Logger logger = LoggerFactory.getLogger(MaxwellKafkaReceiver.class);
	
	@KafkaListener(topics = {"maxwell"})
    public void listen(ConsumerRecord<?, ?> record) {
        Optional<?> kafkaMessage = Optional.ofNullable(record.value());
        if (kafkaMessage.isPresent()) {
            Object message = kafkaMessage.get();
            logger.info("----------maxwell-------- message =" + message);
        }

    }

	
}
