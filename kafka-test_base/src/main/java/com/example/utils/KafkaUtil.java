package com.example.utils;

import com.example.conf.Message;
import org.apache.kafka.clients.admin.KafkaAdminClient;
import org.apache.kafka.clients.admin.ListTopicsResult;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.KafkaFuture;

import java.util.Date;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ExecutionException;

/**
 * Desc: 往kafka中写数据,可以使用这个main函数进行测试
 * Created by zhisheng on 2019-02-17
 * Blog: http://www.54tianzhisheng.cn/2019/01/09/Flink-MySQL-sink/
 */
public class KafkaUtil {
//    public static final String broker_list = "192.168.137.111:9092";
    public static final String broker_list = "11.11.1.79:9092";
//    public static final String broker_list = "10.0.22.80:9092";
//    public static final String topic = "hello";  //kafka topic 需要和 flink 程序用同一个 topic
    public static final String topic = "generated.events";  //kafka topic 需要和 flink 程序用同一个 topic

    public static void writeToKafka() throws InterruptedException {
        Properties props = new Properties();
        props.put("bootstrap.servers", broker_list);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        KafkaProducer producer = new KafkaProducer<String, String>(props);

        for (int i = 1; i <= 20; i++) {
            Message message1 = new Message();
            message1.setStatus("status");
            message1.setDirection("direres");
//            message1.setEvent_ts(new Date().getMinutes());
            message1.setEvent_ts(49);
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

 class ListKafkaTopic{
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Properties pro = new Properties();
        pro.put("bootstrap.servers", "11.11.1.79:9092");
        //KafkaUtils.getTopicNames(zkAddress)
        ListTopicsResult result = KafkaAdminClient.create(pro).listTopics();
        KafkaFuture<Set<String>> set = result.names();
        System.out.println(set.get());
    }
}
