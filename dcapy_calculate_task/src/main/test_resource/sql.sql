/**
使用增量表的方式对mq的消息进行缓存，使用定时器来执行  记录表数据的修改
 */



CREATE TABLE `merchant_order_finish_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `record_id` bigint(32) DEFAULT NULL COMMENT '订单id',
  `customer_id` bigint(32) DEFAULT NULL COMMENT '发起人id',
  `merchant_id` bigint(32) DEFAULT NULL COMMENT '接单商户id',
  `order_no` varchar(200) DEFAULT NULL COMMENT '订单号',
  `transaction_amount` decimal(20,10) DEFAULT NULL COMMENT '订单交易额',
  `income` decimal(20,10) DEFAULT NULL COMMENT '订单收益',
  `order_type` int(2) DEFAULT NULL COMMENT '订单类型',
  `pay_way` varchar(100) DEFAULT NULL COMMENT '支付方式',
  `order_status` int(10) DEFAULT NULL COMMENT '订单状态',
  `create_time` datetime DEFAULT NULL COMMENT '订单创建时间',
  `simple_time` date DEFAULT NULL COMMENT '订单创建时间只精确到天',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=81 DEFAULT CHARSET=utf8

CREATE TABLE `merchant_day_amount` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `merchant_id` bigint(32) NOT NULL COMMENT '商户id',
  `order_num` bigint(32) DEFAULT NULL COMMENT '每日成交订单数量',
  `create_time` date DEFAULT NULL COMMENT '创建时间',
  `modify_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `remark` bigint(32) DEFAULT '1' COMMENT '标记位',
  `day_amount_sum` decimal(20,10) NOT NULL DEFAULT '0.0000000000' COMMENT '商家每日订单实时金额',
  PRIMARY KEY (`id`),
  KEY `index_create_time` (`create_time`),
  KEY `index_merchant_id` (`merchant_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8

