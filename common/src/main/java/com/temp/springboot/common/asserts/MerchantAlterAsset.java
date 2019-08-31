package com.temp.springboot.common.asserts;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.temp.springboot.common.model.MerchantInfoCache;

import java.lang.reflect.Field;

/**
 * 商户缓存更新断言
 * author lw
 * date 2019/8/28 11:30
 */
public class MerchantAlterAsset {


    public static boolean allFieldIsNull(Object o) {
        try {
            for (Field field : o.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                Object object = field.get(o);
                if (object instanceof CharSequence) {
                    if (!org.springframework.util.ObjectUtils.isEmpty(object)) {
                        return false;
                    }
                } else {
                    if (null != object) {
                        return false;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("判断字段为空异常");
        }
        return true;

    }

    public static boolean isNull(MerchantInfoCache merchantInfoCache) {
        String s = JSON.toJSONString(merchantInfoCache);
        if ("{}".equals(s)) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        int count = 10000000;
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            isNull(MerchantInfoCache.builder().build());
        }
        long next = System.currentTimeMillis();
        System.out.println("判断为空耗时 " + (next - startTime));
        for (int i = 0; i < count; i++) {
            allFieldIsNull(MerchantInfoCache.builder().build());
        }
        long next1 = System.currentTimeMillis();
        System.out.println("判断为空耗时 " + (next1 - next));
    }


    public static String toJsonNotNull(Object obj) {
        if (null != obj) {
            return JSONObject.toJSONString(obj, filter);
        }
        throw new NullPointerException("需要json化的参数为空!");
    }

    private static PropertyFilter filter = (obj, s, v) -> {
        if (null == v) {
            return false;
        }
        return true;
    };

}

class JsonUtil {
    public static void main(String[] args) {
        String s = MerchantAlterAsset.toJsonNotNull(MerchantInfoCache.builder().build());
        System.out.println(s);
    }
}
