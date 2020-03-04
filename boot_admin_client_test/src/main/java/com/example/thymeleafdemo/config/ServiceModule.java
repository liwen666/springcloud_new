package com.example.thymeleafdemo.config;


/**
 * @program: anyest-rule-engine-feign
 * @description: 微服务模块名称
 * @author: yuanchangyou
 * @create: 2019-11-04 17:55
 **/
public class ServiceModule {

    /**
     * 管理平台
     */
    public static final String ANYEST_CENTER_ADMIN = "ANYEST-CENTER-ADMIN";
    /**
     * 决策平台
     */
    public static final String ANYEST_ENGINE_SERVER = "ANYEST-ENGINE-SERVER";
    /**
     * 资信平台
     */
    public static final String ANYEST_DATA_SERVICE = "BOOT-ADMIN-CLIENT";

    /**
     * 统计模型计算节点
     */
    public static final String ANYEST_STAT_SERVICE = "ANYEST-STAT-SERVICE";

    /**
     * 策略存储节点
     */
    public static final String ANYEST_SAVE_SERVICE = "ANYEST-SAVE-SERVICE";

    /**
     * 数据采集
     */
    public static final String ANYEST_COLLECT_SERVER = "ANYEST-COLLECT-SERVER";


    /**
     * 批量调度
     */
    public static final String ANYEST_SCHEDULE_SERVER = "ANYEST-SCHEDULE-SERVER";


}
