package jrx.data.hub.domain.connction;

import com.alibaba.fastjson.JSONObject;
import jrx.data.hub.domain.enums.DbType;
import jrx.data.hub.infrastructure.entity.MetaDataSourceInfo;
import jrx.data.hub.infrastructure.remote.IDataSourceConnection;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

/**
 * kafka客户端连接
 * @author zhangch
 */
public class KafkaConnection implements IDataSourceConnection {

    private MetaDataSourceInfo metaDataSourceInfo;
    private Producer<String, String> producer;
    private Properties properties;

    public KafkaConnection(MetaDataSourceInfo metaDataSourceInfo){
        this.metaDataSourceInfo = metaDataSourceInfo;
    }

    @Override
    public void connect(boolean force) {}

    @Override
    public Producer getConnect() {
        return null;
    }

    @Override
    public void close() {
        if(producer!=null){
            producer.close();
            producer = null;
        }
    }

    @Override
    public DbType getDbType() {
        return DbType.KAFKA;
    }

    @Override
    public boolean existTable(String tableName) {
        return false;
    }

    @Override
    public Boolean connectionTest() {
        JSONObject dbConfigJson = JSONObject.parseObject(metaDataSourceInfo.getDbConfigJson());
        String url = dbConfigJson.getString("url");
        properties = new Properties();
        properties.put("bootstrap.servers", url);
        properties.put("acks", "all");
        properties.put("retries", 0);
        properties.put("batch.size", 16384);
        properties.put("linger.ms", 1);
        properties.put("buffer.memory", 33554432);
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        Producer<String, String> producer = null;
        try {
            producer = new KafkaProducer<String, String>(properties);
            producer.send(new ProducerRecord<String, String>("connectionTest","connectionTest"));
            return true;
        } catch (Exception e) {
            return false;

        }
    }

}
