//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package tem.inter.analysis.utils;

import jrx.data.hub.core.utils.JsonUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class TablePropertiesThreadLocalHolder {

    private static final ThreadLocal<Map<String, String>> localProperties = new ThreadLocal();
    private static final ThreadLocal<Map<String, AtomicInteger>> sequence = new ThreadLocal();


    public TablePropertiesThreadLocalHolder() {
    }

    public static void addProperties(String key, String value) {
        if (null == localProperties.get()) {
            localProperties.set(new HashMap());
        }

        ((Map) localProperties.get()).put(key, value);
    }


    public static Map<String, String> get() {
        return (Map) localProperties.get();
    }

    public static String getProperties(String key) {
        return (String) ((Map) localProperties.get()).get(key);
    }

    public static int getSeq(String key) {
        if (null == sequence.get()) {
            sequence.set(new ConcurrentHashMap<>());
        }
        if (((Map) sequence.get()).get(key) == null) ((Map) sequence.get()).put(key, new AtomicInteger(0));
        return ((AtomicInteger) ((Map) sequence.get()).get(key)).getAndIncrement();
    }

    public static void remove(String key) {
        ((Map) localProperties.get()).remove(key);
    }

    public static void removeSeq(String key) {
        ((Map) sequence.get()).remove(key);
    }
}
