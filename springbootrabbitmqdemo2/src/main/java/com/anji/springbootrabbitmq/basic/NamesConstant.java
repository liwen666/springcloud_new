package com.anji.springbootrabbitmq.basic;

public class NamesConstant {

	/**
	 * 业务系统给调度系统>新订单通知交换机
	 */
	public static final String SCHEDULE_EXCHANGE = "orderExchange";
	/**
	 * 业务系统给调度系统>新订单通知
	 */
	public static final String SCHEDULE_QUEUE = "toNotifyScheduleCenter";
	/**
	 * 业务系统给调度系统>新订单通知routing_key
	 */
	public static final String SCHEDULE_ROUTING_KEY = "toNotifyScheduleCenterKey";

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * 业务系统给平台系统>订单状态变化 队列
	 */
	public static final String RECEIVE_ORDER_CHANGE_QUEUE = "toNotifyPlatformOrderChange";
	/**
	 * 业务系统给平台系统>订单状态变化  交换机
	 */
	public static final String RECEIVE_ORDER_CHANGE_EXCHANGE = "orderExchange";

	/**
	 * 业务系统给平台系统>订单状态变化  routing_key
	 */
	public static final String RECEIVE_ORDER_CHANGE_ROUTING_KEY = "toNotifyPlatformOrderChangeKey";
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * 业务系统给商家>订单状态变化  交换机
	 */
	public static final String toNotifyMerchantOrderExchange = "orderExchange";
	/**
	 * 业务系统给商家>订单状态变化 队列
	 */
	public static final String toNotifyMerchantOrderChange_QUEUE = "toNotifyMerchantOrderChange";
	/**
	 * 业务系统给商家>订单状态变化  routing_key
	 */
	public static final String toNotifyMerchantOrderChange_KEY = "toNotifyMerchantOrderChangeKey";
	
	
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * 通知业务系统商家信息有变更 交换机
	 */
	public static final String toBusinessMerchantExchange = "businessMerchantExchange";
	/**
	 * 通知业务系统商家信息有变更 队列
	 */
	public static final String toNotifyBusinessMerchantChange_QUEUE = "toNotifyBusinessMerchantChange";
	/**
	 * 通知业务系统商家信息有变更 路由key
	 */
	public static final String toNotifyBusinessMerchantChangeKey_KEY = "toNotifyBusinessMerchantChangeKey";
	
	
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * 订单最终记录交换机
	 */
	public static final String toOrderFinish = "orderFinishExchange";
	/**
	 * 订单最终记录队列
	 */
	public static final String toOrderFinish_QUEUE = "toOrderFinish";
	/**
	 * 订单最终记录队列路由键
	 */
	public static final String toOrderFinish_KEY = "toOrderFinishKey";


	/**
	 * 订单最终记录队列
	 */
	public static final String toOrderFinishCalculate_QUEUE= "toOrderFinishCalculate";
	
	
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * 订单就绪后通知订单管理中心交换机
	 */
	public static final String toOrderReadyExchange = "orderExchange";
	/**
	 * 订单就绪后通知订单管理中心队列
	 */
	public static final String toOrderReady_QUEUE = "toOrderReady";
	/**
	 * 订单就绪后通知订单管理路由键
	 */
	public static final String toOrderReady_KEY = "toOrderReadyKey";
	
	
	
}
