package com.example.utils;

import com.example.conf.Message;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Date;
import java.util.Properties;

/**
 * Desc: 往kafka中写数据,可以使用这个main函数进行测试
 * Created by zhisheng on 2019-02-17
 * Blog: http://www.54tianzhisheng.cn/2019/01/09/Flink-MySQL-sink/
 */
public class KafkaUtil {
    public static final String broker_list = "192.168.137.111:9092";
//    public static final String topic = "hello";  //kafka topic 需要和 flink 程序用同一个 topic
    public static final String topic = "2_dml_maxwell";  //kafka topic 需要和 flink 程序用同一个 topic

    public static void writeToKafka() throws InterruptedException {
        Properties props = new Properties();
        props.put("bootstrap.servers", broker_list);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        KafkaProducer producer = new KafkaProducer<String, String>(props);

        for (int i = 1; i <= 2; i++) {
            Message message1 = new Message();
            message1.setAccount_id(1000);
            message1.setAmount(1000);
            message1.setTransaction_time(new Date());
            ProducerRecord record = new ProducerRecord<String, String>(topic, null, null, GsonUtil.toJson(message1));
            producer.send(record);
            System.out.println("发送数据: " + GsonUtil.toJson(message1));
            Thread.sleep(1 * 1000); //发送一条数据 sleep 10s，相当于 1 分钟 6 条
        }
        producer.flush();
    }

    public static void main(String[] args) throws InterruptedException {
        writeToKafka();
    }
}
