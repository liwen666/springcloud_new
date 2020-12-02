package jrx.data.hub.domain.connction;

import com.alibaba.fastjson.JSONObject;
import com.lambdaworks.redis.RedisClient;
import com.lambdaworks.redis.RedisURI;
import com.lambdaworks.redis.api.StatefulRedisConnection;
import jrx.data.hub.domain.enums.DbType;
import jrx.data.hub.infrastructure.entity.MetaDataSourceInfo;
import jrx.data.hub.infrastructure.remote.IDataSourceConnection;
import org.apache.commons.lang3.StringUtils;

/**
 * redis客户端连接
 * @author zhangch
 */
public class RedisConnection implements IDataSourceConnection<StatefulRedisConnection> {

    private MetaDataSourceInfo metaDataSourceInfo;
    private RedisClient redisClient;
    private StatefulRedisConnection redisConnection;

    public RedisConnection(MetaDataSourceInfo metaDataSourceInfo){
        this.metaDataSourceInfo = metaDataSourceInfo;
    }

    @Override
    public void connect(boolean force) {
        if(redisClient == null || force){
            //TODO 创建redis客户端连接
        }
    }

    @Override
    public StatefulRedisConnection getConnect() {
        return redisConnection;
    }

    @Override
    public void close() {
        if(redisConnection!=null){
            redisConnection.close();
            redisConnection = null;
        }
        if(redisClient!=null){
            redisClient.shutdown();
        }
    }

    @Override
    public DbType getDbType() {
        return DbType.REDIS;
    }

    @Override
    public boolean existTable(String tableName) {
        return false;
    }

    @Override
    public Boolean connectionTest() {
        JSONObject dbConfigJson = JSONObject.parseObject(metaDataSourceInfo.getDbConfigJson());
        String host = dbConfigJson.getString("url").split(":")[0];
        String port = dbConfigJson.getString("url").split(":")[1];
        String password = dbConfigJson.getString("password");
        RedisURI redisURI = new RedisURI();
        redisURI.setHost(host);
        redisURI.setPort(Integer.parseInt(port));
        if(StringUtils.isNotEmpty(password)){
            redisURI.setPassword(password);
        }
        RedisClient client = RedisClient.create(redisURI);
        redisConnection = client.connect();
        return redisConnection.isOpen();
    }


}
