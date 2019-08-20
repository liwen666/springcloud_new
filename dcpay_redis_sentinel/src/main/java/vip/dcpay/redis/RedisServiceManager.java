package vip.dcpay.redis;

import vip.dcpay.redis.service.RedisService;
import vip.dcpay.redis.service.ServiceCollection;

/**
 * @Auther: liq
 * @Date: 2019/6/28 18:52
 * @Description:
 */
public class RedisServiceManager {

    private static ServiceCollection serviceCollection = ServiceCollection.getInstance();

    public static RedisService gainRedisService() {
        return serviceCollection.gainRedisService();
    }

    public static RedisService gainRedisService(Integer db) {
        return serviceCollection.gainRedisService(db);
    }

}
