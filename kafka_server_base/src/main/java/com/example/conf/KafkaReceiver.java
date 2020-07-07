package com.example.conf;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class KafkaReceiver {

    private static Logger logger = LoggerFactory.getLogger(KafkaReceiver.class);

    /****
     *
     * 添加测试计数器
     *
     */
    private AtomicLong count = new AtomicLong(0);

    private long firstTime;
    private long tmpTIme;

    //    @KafkaListener(topics = {"${kafka.dml.topic}"})
//    @KafkaListener(topics = {"#{'${kafka.dml.topic}'.split(',')}"})

    /**
     * 线程数必须小于所有分区的总和
     * @param record
     */
    @KafkaListener(id="dml_topic",topics = {"#{'${kafka.dml.topic}'.split(',')}"},concurrency = "2")
    public void dml(ConsumerRecord<?, ?> record) {
        Optional<?> kafkaMessage = Optional.ofNullable(record.value());
        if (kafkaMessage.isPresent()) {
            Object message = kafkaMessage.get();
            logger.info("---------kafka.dml.topic:{}--------- message:{} ", record.topic(), message);
        }
        long l = count.incrementAndGet();
        if (l == 1) {
            firstTime = System.currentTimeMillis();
            tmpTIme = firstTime;

        }
        if (l % 100000 == 0) {
            long now = System.currentTimeMillis();
            logger.info("发送到kafka数据总量:{},本次10万耗时:{} s,总耗时：{} s", l, (now - tmpTIme) / 1000, (now - firstTime) / 1000);
            this.tmpTIme = now;
        }

    }

    //    @KafkaListener(topics = {"${kafka.ddl.topic}"})
//    @KafkaListener(topics = {"#{'${kafka.ddl.topic}'.split(',')}"})
    @KafkaListener(id ="ddl_topic",topics={"#{'${kafka.ddl.topic}'.split(',')}"},concurrency = "2")
    public void ddl(ConsumerRecord<?, ?> record) {
        Optional<?> kafkaMessage = Optional.ofNullable(record.value());
        if (kafkaMessage.isPresent()) {
            Object message = kafkaMessage.get();
            logger.info("---------kafka.ddl.topic:{}--------- message:{} ",record.topic(), message);
        }


    }

    @KafkaListener(topics = {"hello"})
    public void check(ConsumerRecord<?, ?> record) {
        Optional<?> kafkaMessage = Optional.ofNullable(record.value());
        if (kafkaMessage.isPresent()) {
            Object message = kafkaMessage.get();
            logger.info("---------hello--------- message =" + message);
        }

    }

}
