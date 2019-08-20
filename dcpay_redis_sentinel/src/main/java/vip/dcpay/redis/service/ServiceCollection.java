package vip.dcpay.redis.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;
import redis.clients.util.Pool;
import vip.dcpay.redis.bean.ConfigParam;
import vip.dcpay.redis.service.impl.RedisServiceImpl;
import vip.dcpay.util.frame.spring.SpringContextUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: liq
 * @Date: 2019/7/17 13:19
 * @Description:
 */
public class ServiceCollection {

    private static volatile ServiceCollection collection;

    private Map<String, RedisService> redisServiceMap = new HashMap<>();
    private String REDIS_PREFIX = "db";

    private ServiceCollection() {
    }

    public static ServiceCollection getInstance() {
        if (null == collection) {
            synchronized (ServiceCollection.class) {
                if (null == collection) {
                    collection = new ServiceCollection();
                }
            }
        }
        return collection;
    }

    public RedisService gainRedisService() {
        return gainRedisService(0);
    }

    public RedisService gainRedisService(Integer db) {

        RedisService redisService = redisServiceMap.get(REDIS_PREFIX + db);
        if (null == redisService) {
            synchronized (ServiceCollection.class) {
                if (null == redisService) {
                    redisService = new RedisServiceImpl(gainJedisPool(db));
                    redisServiceMap.put(REDIS_PREFIX + db, redisService);
                }
            }
        }

        return redisService;
    }

    private Pool<Jedis> gainJedisPool(Integer db) {
        ConfigParam configParam = SpringContextUtil.getBean("configParam", ConfigParam.class);
        Assert.isTrue(null != configParam, "redis配置注入失败");

        JedisPoolConfig config = SpringContextUtil.getBean(JedisPoolConfig.class);
        Assert.isTrue(null != config, "连接池配置注入失败");

        switch (configParam.getPattern()) {
            case SENTINEL:
                Assert.isTrue(StringUtils.isNoneBlank(configParam.getMasterName()), "spring.redis.sentinel.master 不能为空");
                Assert.isTrue(!configParam.getSentinelNodes().isEmpty(), "spring.redis.sentinel.nodes 不能为空");
                Assert.isTrue(null != db && db >= 0, "db 不能为空 并且不能小于0");
                return new JedisSentinelPool(configParam.getMasterName(), configParam.getSentinelNodes(), config, configParam.getTimeout(), configParam.getPassword(), db);
            case SINGLE:
                Assert.isTrue(StringUtils.isNoneBlank(configParam.getUrl()), "spring.redis.url 不能为空");
                Assert.isTrue(null != configParam.getPort() , "spring.redis.port 不能为空 ");
                Assert.isTrue(null != db && db >= 0, "db 不能为空 并且不能小于0");
                return new JedisPool(config, configParam.getUrl(), configParam.getPort(), configParam.getTimeout(), configParam.getPassword(), db);
            default:
                Assert.isTrue(false, "redis模式配置错误");
        }

        return null;
    }

}
