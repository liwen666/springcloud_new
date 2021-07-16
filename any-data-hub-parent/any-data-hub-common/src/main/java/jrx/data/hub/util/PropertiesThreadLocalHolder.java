package jrx.data.hub.util;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 描述
 * </p>
 *
 * @author lw
 * @since 2020/10/22 16:27
 */
public class PropertiesThreadLocalHolder {
    private  static final ThreadLocal<Map<String, String>> localProperties = new ThreadLocal<>();

    public static void addProperties(String key, String value) {
        if (null == localProperties.get()) {
            localProperties.set(new HashMap<>());
        }
        localProperties.get().put(key, value);
    }

    public static Map<String, String> get() {
        return localProperties.get();
    }

    public static String getProperties(String key) {
        return localProperties.get().get(key);
    }

    public static void remove(String key) {
        localProperties.get().remove(key);
    }
}