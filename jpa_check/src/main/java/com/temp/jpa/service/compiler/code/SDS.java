package com.temp.jpa.service.compiler.code;


import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 关联表数据在脚本中使用时使用的静态方法类
 * 在脚本中使用
 * @author
 * @version 2.0
 * @date 2019/11/24
 */
public class SDS {


    private static ThreadLocal<EventStatisticRecorder>  recorderThreadLocal = new ThreadLocal<>();


    /**
     * 当前线程缓存数据，其中，key值为:查询的字段主键组合值
     * value值为返回的数据，map中的结果为：key值为缓存的fieldCode，value为对应的值
     */
    private static ThreadLocal<Map<String, Map<String, Object>>> threadDataCache = ThreadLocal.withInitial(() -> new HashMap<>(10));

    /**
     * 通过静态方法注册数据管理类和数据缓存类
     */
    public static void registry(EventStatisticRecorder eventStatisticRecorder) {
        recorderThreadLocal.set(eventStatisticRecorder);
    }

    /**
     * 查询关联表数据,基于时间片
     * 当前统计模型逻辑暂时用不到使用此方法, by 20191124
     *
     * @param objectInfoCode
     * @param keyValue
     * @param statTime
     * @param columnPositions
     * @return
     */
    public static Map<String, Object> r(String objectInfoCode, String keyValue, Object statTime, Set<Integer> columnPositions) {
        if (recorderThreadLocal.get() == null) {
            return Collections.emptyMap();
        }

        String key = keyValue;

        if (statTime != null) {
            key += "_" + statTime;
        }

        Map<String, Object> data = threadDataCache.get().get(key);

        if (data != null) {
            return data;
        }

        data  = recorderThreadLocal.get().findRelationData(objectInfoCode,keyValue,statTime,columnPositions,false);
        threadDataCache.get().put(key, data);

        return data;
    }

    /**
     * 查询cache缓存引擎中数据
     *
     * @param objectInfoCode
     * @param key
     * @return
     */
    public static Map<String, Object> c(String objectInfoCode, String key) {
        if(recorderThreadLocal.get() == null){
            return Collections.emptyMap();
        }
        Map<String, Object> data = threadDataCache.get().get(key);

        if (data != null) {
            return data;
        }
        data = recorderThreadLocal.get().findCacheDataUseCode(objectInfoCode,key);
        threadDataCache.get().put(key, data);
        return data;
    }
}
