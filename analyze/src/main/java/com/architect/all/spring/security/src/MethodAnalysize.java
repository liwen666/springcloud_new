package com.architect.all.spring.security.src;

import org.junit.Test;
import org.springframework.lang.Nullable;

import java.io.Serializable;
import java.util.AbstractList;
import java.util.Collections;
import java.util.List;
import java.util.RandomAccess;

public class MethodAnalysize {

    public static List<String> loadFactoryNames(Class<?> factoryClass, @Nullable ClassLoader classLoader) {
        String factoryClassName = factoryClass.getName();
//        return (List)loadSpringFactories(classLoader).getOrDefault(factoryClassName, Collections.emptyList());
        return null;
    }
    @Test
    public void md1(){
        List<Object> objects = Collections.emptyList();
//        获取到的list类型  如下
//        private static class EmptyList<E>
//                extends AbstractList<E>
//                implements RandomAccess, Serializable {
        System.out.println(objects);
    }
    @Test
    public void hashtx(){
       Object o = new Object();
            int hash = o != null ? o.hashCode() : 0;
            hash += hash << 15 ^ -12931;
            hash ^= hash >>> 10;
            hash += hash << 3;
            hash ^= hash >>> 6;
            hash += (hash << 2) + (hash << 14);
            hash ^= hash >>> 16;
        System.out.println(hash);
        hash= hash >>> 32 - 4 & 3 - 1;
    }
}
