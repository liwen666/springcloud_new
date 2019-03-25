package com.architect.all.spring.core;

import org.junit.Test;
import org.springframework.util.ConcurrentReferenceHashMap;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class ConcurrentReferenceHashMapTX {

    @Test
    public void test() throws InterruptedException {
        String key = new String("key");
        String value = new String("val");
//        Map<String, String> map = new HashMap<String, String>(8);
        Map<String, String> map = new ConcurrentReferenceHashMap<>(8, ConcurrentReferenceHashMap.ReferenceType.WEAK);
        map.put(key, value);
        System.out.println(map);
        key = null;
        System.gc();
        TimeUnit.SECONDS.sleep(5);
        System.out.println("------");
        System.out.println(map);
    }
}
