package com.example.utils;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.CreateTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.KafkaFuture;

import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

/** 批量创建kafka topic| 批量删除kafka topic
 * @author fangchangtan
 * @date 2019-11-28 16:12
 */
public class BatchCreateTopicUtils {

    public static AdminClient client;

    public static void main(String[] args) {
        Properties props = new Properties();
        props.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "11.11.1.79:9092");
        try (AdminClient client = AdminClient.create(props)) {
            //创建topic
//            createTopics(client);
            //删除topic
            deleteTopics(client);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**批量创建kafka的topic
     * create multiple topics
     * @param client
     */
    public static void createTopics(AdminClient client) throws ExecutionException, InterruptedException {
        //批量创建kafka的topic ,请指定分区数:numPartitions
//        NewTopic newTopic1 = new NewTopic("topic-good500", 10, (short)3);
        NewTopic newTopic1 = new NewTopic("topic-good500", 10, (short)1);
        NewTopic newTopic2 = new NewTopic("topic-good501", 11, (short)1);
        NewTopic newTopic3 = new NewTopic("topic-good502", 12, (short)1);
        final ArrayList<NewTopic> newTopicsList = new ArrayList<>();
        newTopicsList.add(newTopic1);
        newTopicsList.add(newTopic2);
        newTopicsList.add(newTopic3);
        CreateTopicsResult ret = client.createTopics(newTopicsList);
        ret.all().get();

        System.out.println("[info]: Create Topic success!");

    }


    /**
     * delete the given topics
     * @param client
     */
    public static void deleteTopics(AdminClient client) throws ExecutionException, InterruptedException {
        //批量创建kafka的topic
        final ArrayList<String> listTopic = new ArrayList<>();
        listTopic.add("topic-good500");
        listTopic.add("topic-good501");
        listTopic.add("topic-good502");

        KafkaFuture<Void> futures = client.deleteTopics(listTopic).all();
        futures.get();
        System.out.println("[info]: delete Topic success!");
    }




}