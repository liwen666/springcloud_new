package vip.dcpay.redis;

import com.google.common.collect.Sets;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ClassPathResource;
import redis.clients.jedis.JedisPoolConfig;
import vip.dcpay.redis.bean.ConfigParam;
import vip.dcpay.redis.util.PatternEnum;
import vip.dcpay.util.frame.spring.PropertiesConfigurer;
import vip.dcpay.util.frame.spring.SpringContextUtil;

import java.util.Set;

/**
 * @Auther: liq
 * @Date: 2019/6/28 15:24
 * @Description:
 */
@Import({SpringContextUtil.class})
@Configuration
public class RedisSentinelConfig {

    @DependsOn("vip.dcpay.util.frame.spring.SpringContextUtil")
    @Bean(name = "redisPropertiesConfigurer")
    public PropertiesConfigurer redisPropertiesConfigurer() {

        System.out.println(">>>>>加载 redisPropertiesConfigurer");

        PropertiesConfigurer prop = new PropertiesConfigurer();
        prop.setIgnoreUnresolvablePlaceholders(true);
        prop.setIgnoreResourceNotFound(true);
        prop.setLocations(new ClassPathResource("redis.properties"));
        return prop;
    }

    @Bean(name = "configParam")
    public ConfigParam configParam(@Qualifier("redisPropertiesConfigurer") PropertiesConfigurer configurer) {

        System.out.println(">>>>>加载 configParam");

        String pattern = configurer.getProperty("spring.redis.pattern");
        PatternEnum patternType = PatternEnum.getEnumByName(pattern);
        if (null == patternType) {
            System.out.println(">>>>>加载 configParam 失败。redis运行模式配置错误,pattern:" + pattern);
            return null;
        }

        String dbs = configurer.getProperty("spring.redis.dbs", "0");

        String url = configurer.getProperty("spring.redis.url");
        String portStr = configurer.getProperty("spring.redis.port");
        Integer port = null;
        if (StringUtils.isNoneBlank(portStr)) {
            port = Integer.valueOf(portStr);
        }
        Integer timeout = configurer.getInteger("spring.redis.timeout", 3000);
        String password = configurer.getProperty("spring.redis.password");

        String masterName = configurer.getProperty("spring.redis.sentinel.master");
        String sentinelNodes = configurer.getProperty("spring.redis.sentinel.nodes");

        ConfigParam configParam = ConfigParam.builder()
                .pattern(patternType)
                .dbs(dbs)
                .url(url)
                .port(port)
                .timeout(timeout)
                .password(password)
                .masterName(masterName)
                .build();

        if (StringUtils.isNoneBlank(sentinelNodes)) {
            Set<String> sentinels = Sets.newHashSet(StringUtils.split(sentinelNodes, ","));
            configParam.setSentinelNodes(sentinels);
        }

        return configParam;
    }

    @Bean(name = "jedis.pool.config")
    public JedisPoolConfig jedisPoolConfig(@Qualifier("redisPropertiesConfigurer") PropertiesConfigurer configurer) {

        System.out.println(">>>>>加载 jedisPoolConfig");

        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(configurer.getInteger("spring.redis.pool.max-active", 16));
        config.setMaxIdle(configurer.getInteger("spring.redis.jedis.pool.max-idle", 16));
        config.setMinIdle(configurer.getInteger("spring.redis.jedis.pool.min-idle", 8));
        config.setMaxWaitMillis(configurer.getInteger("spring.redis.jedis.pool.max-wait", 3000));

        config.setTestOnBorrow(configurer.getBoolean("spring.redis.jedis.pool.test-on-borrow", true));
        config.setTestOnReturn(configurer.getBoolean("spring.redis.jedis.pool.test-on-return", true));
//        config.setBlockWhenExhausted(blockWhenExhausted);
        config.setTestWhileIdle(configurer.getBoolean("spring.redis.jedis.pool.test-while-idle", true));

        config.setTimeBetweenEvictionRunsMillis(configurer.getInteger("spring.redis.jedis.pool.time-between-eviction-runs-millis", 30000));
        config.setNumTestsPerEvictionRun(configurer.getInteger("spring.redis.jedis.pool.num-tests-per-eviction-run", 10));
        config.setMinEvictableIdleTimeMillis(configurer.getInteger("spring.redis.jedis.pool.min-evictable-idle-time-millis", 60000));

        return config;
    }

}
