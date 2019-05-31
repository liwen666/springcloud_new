package com.lw.common;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

//@RestController()
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestDemo {
    @Autowired
    RedisTemplate redisTemplate;

    @Test
    public void testString() {
        redisTemplate.opsForValue().set("a", "b");
        System.out.println(redisTemplate.opsForValue().get("a"));
        //10秒过期
        redisTemplate.opsForValue().set("name", "tom", 10, TimeUnit.SECONDS);
        System.out.println(redisTemplate.opsForValue().get("name"));
        //如果存在key返回false  设置失败  不会覆盖之前的值
        System.out.println(redisTemplate.opsForValue().setIfAbsent("multi1", "666"));
        System.out.println(redisTemplate.opsForValue().get("multi1"));
        System.out.println(redisTemplate.opsForValue().setIfAbsent("multi111", "multi111"));//true  multi111之前不存在
        //多个值的存入
        Map<String, String> maps = new HashMap<String, String>();
        maps.put("multi1", "666");
        maps.put("multi2", "multi2");
        maps.put("multi3", "multi3");
        redisTemplate.opsForValue().multiSet(maps);
        List<String> keys = new ArrayList<String>();
        keys.add("multi1");
        keys.add("multi2");
        keys.add("multi3");
        System.out.println(redisTemplate.opsForValue().multiGet(keys));

        Map<String,String> maps1 = new HashMap<String, String>();
        maps1.put("multi11","multi11");
        maps1.put("multi22","multi22");
        maps1.put("multi33","multi33");
        Map<String,String> maps3 = new HashMap<String, String>();
        maps3.put("multi1","multi1");
        maps3.put("multi2","multi2");
        maps3.put("multi3","multi3");
        System.out.println(redisTemplate.opsForValue().multiSetIfAbsent(maps1));
        System.out.println(redisTemplate.opsForValue().multiSetIfAbsent(maps3));

    }

    @Test
    public void listTest() {

//        这个是json的序列化
//        默认是16进制
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<Object>(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        redisTemplate.setKeySerializer(jackson2JsonRedisSerializer);
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.setHashKeySerializer(jackson2JsonRedisSerializer);
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.opsForList().leftPushAll("list","c++", "python","java", "c#");
        System.out.println(redisTemplate.opsForList().range("list",0,-1));
        redisTemplate.opsForList().trim("list",1,-1);//裁剪第一个元素
        System.out.println(redisTemplate.opsForList().range("list",0,-1));

        System.out.println(redisTemplate.opsForList().leftPush("list", "java"));
        System.out.println(redisTemplate.opsForList().leftPush("list","python"));
        System.out.println(redisTemplate.opsForList().leftPush("list","c++"));
        System.out.println("******************************************************");
        String[] stringarrays = new String[]{"1","2","3"};
        redisTemplate.opsForList().leftPushAll("listarray",stringarrays);
        System.out.println(redisTemplate.opsForList().range("listarray",0,-1));
        System.out.println("******************************************************");

    }

    ;
}
