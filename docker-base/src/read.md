http://localhost:8888/login 请求主页
http://localhost:8089/topic.html


http://172.16.104.12:22080/topic.html
kafka  每个分组记录消息小消费便宜量    只有偏移量有变化，后面的客户就不会再消费了。

#kafka创建topic  和分区  
/home/jrxany/kafka/kafka_2.13-2.4.0/bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 5 --topic test_ddl_local



/home/jrxany/batch_node/kafka/kafka_2.12-2.1.1/bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 5 --topic local_ddl
/home/jrxany/batch_node/kafka/kafka_2.12-2.1.1/bin/kafka-topics.sh --describe --zookeeper localhost:2181 --topic test_kafka_partion
/home/jrxany/batch_node/kafka/kafka_2.12-2.1.1/bin/kafka-topics.sh --alter --zookeeper localhost:2181 --topic test_kafka_partion --partitions 6
