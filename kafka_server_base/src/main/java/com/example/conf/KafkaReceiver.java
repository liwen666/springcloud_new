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


    @KafkaListener(topics = {"${kafka.dml.topic}"})
    public void dml(ConsumerRecord<?, ?> record) {
        Optional<?> kafkaMessage = Optional.ofNullable(record.value());
        if (kafkaMessage.isPresent()) {
            Object message = kafkaMessage.get();
            logger.info("---------kafka.dml.topic--------- message =" + message);
        }

    }

    @KafkaListener(topics = {"${kafka.ddl.topic}"})
    public void ddl(ConsumerRecord<?, ?> record) {
        Optional<?> kafkaMessage = Optional.ofNullable(record.value());
        if (kafkaMessage.isPresent()) {
            Object message = kafkaMessage.get();
            logger.info("---------kafka.ddl.topic--------- message =" + message);
        }

    }

    @KafkaListener(topics = {"test_ddl_topic_101_56"})
    public void test_ddl_topic_101_56(ConsumerRecord<?, ?> record) {
        Optional<?> kafkaMessage = Optional.ofNullable(record.value());
        if (kafkaMessage.isPresent()) {
            Object message = kafkaMessage.get();
            logger.info("---------test_ddl_topic_101_56--------- message =" + message);
        }

    }

    @KafkaListener(topics = {"test_dml_101_56"})
    public void test_dml_101_56(ConsumerRecord<?, ?> record) {
        Optional<?> kafkaMessage = Optional.ofNullable(record.value());
        if (kafkaMessage.isPresent()) {
            Object message = kafkaMessage.get();
            logger.info("---------test_dml_101_56--------- message =" + message);
        }

    }


}
