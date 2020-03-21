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
    @KafkaListener(topics = {"test"})
    public void test(ConsumerRecord<?, ?> record) {
        Optional<?> kafkaMessage = Optional.ofNullable(record.value());
        if (kafkaMessage.isPresent()) {
            Object message = kafkaMessage.get();
//            logger.info("------testcsm----------- record =" + record);
            logger.info("---------test--------- message =" + message);
        }

    }

    @KafkaListener(topics = {"test_dml"})
    public void test_dml(ConsumerRecord<?, ?> record) {
        Optional<?> kafkaMessage = Optional.ofNullable(record.value());
        if (kafkaMessage.isPresent()) {
            Object message = kafkaMessage.get();
//            logger.info("------testcsm----------- record =" + record);
            logger.info("---------test_dml--------- message =" + message);
        }

    }

    @KafkaListener(topics = {"ddl_topic"},id="fkdjkaf")
    /**
     * kafka的消费机制是，所有的消费者都会对队列里面的所有数据全部依次消费一遍。
     */
    public void maxwell(ConsumerRecord<?, ?> record) {
        Optional<?> kafkaMessage = Optional.ofNullable(record.value());
        if (kafkaMessage.isPresent()) {
            Object message = kafkaMessage.get();
//            logger.info("------ddl_topic----------- record =" + record);
            logger.info("---------ddl_topic--------- message =" + message);
        }

    }

    @KafkaListener(topics = {"test_kafka_partion"})
    /**
     * kafka的消费机制是，所有的消费者都会对队列里面的所有数据全部依次消费一遍。
     */
    public void test_partion(ConsumerRecord<?, ?> record) {
        Optional<?> kafkaMessage = Optional.ofNullable(record.value());
        if (kafkaMessage.isPresent()) {
            Object message = kafkaMessage.get();
//            logger.info("------ddl_topic----------- record =" + record);
            logger.info("---------test_kafka_partition--------- message =" + message);
        }

    }

    @KafkaListener(topics = {"test_kafka_maxwell"})
    /**
     * kafka的消费机制是，所有的消费者都会对队列里面的所有数据全部依次消费一遍。
     */
    public void test_kafka_maxwell(ConsumerRecord<?, ?> record) {
        Optional<?> kafkaMessage = Optional.ofNullable(record.value());
        if (kafkaMessage.isPresent()) {
            Object message = kafkaMessage.get();
//            logger.info("------ddl_topic----------- record =" + record);
            logger.info("---------test_kafka_maxwell--------- message =" + message);
        }

    }
    @KafkaListener(topics = {"test_ddl_topic"})
    /**
     * kafka的消费机制是，所有的消费者都会对队列里面的所有数据全部依次消费一遍。
     */
    public void test_ddl_topic(ConsumerRecord<?, ?> record) {
        Optional<?> kafkaMessage = Optional.ofNullable(record.value());
        if (kafkaMessage.isPresent()) {
            Object message = kafkaMessage.get();
//            logger.info("------ddl_topic----------- record =" + record);
            logger.info("---------test_ddl_topic--------- message =" + message);
        }

    }

}
