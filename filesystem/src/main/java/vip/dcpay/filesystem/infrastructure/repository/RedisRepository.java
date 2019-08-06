package vip.dcpay.filesystem.infrastructure.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import vip.dcpay.filesystem.domain.repository.IRedisRepository;
import vip.dcpay.redis.service.RedisService;

/**
 * author lw
 * date 2019/8/5 19:26
 * 
 */
@Repository
public class RedisRepository implements IRedisRepository {

    @Autowired
    private RedisService redisService;

    @Override
    public boolean isManualCalculateOpen() {

        return true;
    }

}
