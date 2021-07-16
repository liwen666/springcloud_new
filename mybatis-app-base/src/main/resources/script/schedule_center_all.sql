/*
SQLyog  v12.2.6 (64 bit)
MySQL - 5.7.24-log : Database - any_schedule_center
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;



DROP TABLE IF EXISTS `account_info`;

CREATE TABLE `account_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `account_number` char(32) DEFAULT NULL COMMENT '账户号',
  `customer_number` char(20) DEFAULT NULL COMMENT '客户号',
  `open_date` date DEFAULT NULL COMMENT '开户日期',
  `overdraft_amount` decimal(18,2) DEFAULT NULL COMMENT '透支金额',
  `credit_line` decimal(18,2) DEFAULT NULL COMMENT '授信额度',
  `overdue_number` tinyint(1) DEFAULT NULL COMMENT '当前逾期期数',
  `overdue_amount` decimal(18,2) DEFAULT NULL COMMENT '当前逾期金额',
  `account_label` varchar(1) DEFAULT NULL COMMENT '户标',
  `is_execute` char(1) DEFAULT '0' COMMENT '是否已执行 0-否 1-是',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='账户表';

/*Table structure for table `back_list` */

DROP TABLE IF EXISTS `back_list`;

CREATE TABLE `back_list` (
  `idCard` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `openBank` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `city` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`idCard`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Table structure for table `batch_job_execution_mq` */

DROP TABLE IF EXISTS `batch_job_execution_mq`;

CREATE TABLE `batch_job_execution_mq` (
  `JOB_EXECUTION_ID` bigint(20) NOT NULL,
  `VERSION` bigint(20) DEFAULT NULL,
  `JOB_INSTANCE_ID` bigint(20) NOT NULL,
  `CREATE_TIME` datetime DEFAULT NULL,
  `START_TIME` datetime DEFAULT NULL,
  `END_TIME` datetime DEFAULT NULL,
  `STATUS` varchar(10) DEFAULT NULL,
  `EXIT_CODE` varchar(2500) DEFAULT NULL,
  `EXIT_MESSAGE` varchar(2500) DEFAULT NULL,
  `LAST_UPDATED` datetime DEFAULT NULL,
  `TASK_EXECUTION_ID` varchar(20) DEFAULT NULL,
  `DB_INFO` varchar(20) DEFAULT NULL,
  `TASK_PARENT_EXECUTION_ID` varchar(32) NOT NULL,
  `JOB_CONFIGURATION_LOCATION` varchar(2500) DEFAULT NULL,
  `server_name` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`TASK_PARENT_EXECUTION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `card_info` */

DROP TABLE IF EXISTS `card_info`;

CREATE TABLE `card_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `card_number` varchar(19) DEFAULT NULL COMMENT '卡号',
  `account_number` char(32) DEFAULT NULL COMMENT '账户号',
  `customer_number` char(20) DEFAULT NULL COMMENT '客户号',
  `open_date` date DEFAULT NULL COMMENT '发卡日期',
  `activation_date` date DEFAULT NULL COMMENT '激活日期',
  `card_label` varchar(1) DEFAULT NULL COMMENT '卡标',
  `card_label_date` date DEFAULT NULL COMMENT '卡标日期',
  `product_number` char(6) DEFAULT NULL COMMENT '卡产品编号',
  `organization_number` char(4) DEFAULT NULL COMMENT '发卡渠道机构号',
  `is_execute` char(1) DEFAULT '0' COMMENT '是否已执行 0-否 1-是',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='卡表';

/*Table structure for table `customer_info` */

DROP TABLE IF EXISTS `customer_info`;

CREATE TABLE `customer_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `customer_number` char(20) DEFAULT NULL COMMENT '客户号',
  `birth_date` date DEFAULT NULL COMMENT '出生日期',
  `sex` char(1) DEFAULT NULL COMMENT '性别：0 = 男 1 = 女 2 = 未知',
  `qualification` char(1) DEFAULT NULL COMMENT '学历：0 = 硕士及以上 1 = 本科 2 = 大专 3 = 高中及中专 4 = 初中及以下',
  `marital` char(1) DEFAULT NULL COMMENT '婚姻状况：0 = 未婚 1 = 已婚 2 = 未知',
  `is_execute` char(1) DEFAULT '0' COMMENT '是否已执行 0-否 1-是',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新���间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='客户表';

/*Table structure for table `deployment_ids` */

DROP TABLE IF EXISTS `deployment_ids`;

CREATE TABLE `deployment_ids` (
  `DEPLOYMENT_KEY` varchar(255) NOT NULL,
  `DEPLOYMENT_ID` varchar(255) NOT NULL,
  PRIMARY KEY (`DEPLOYMENT_KEY`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `hibernate_sequence` */

DROP TABLE IF EXISTS `hibernate_sequence`;

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `qrtz_blob_triggers` */

DROP TABLE IF EXISTS `qrtz_blob_triggers`;

CREATE TABLE `qrtz_blob_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `BLOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_blob_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `qrtz_calendars` */

DROP TABLE IF EXISTS `qrtz_calendars`;

CREATE TABLE `qrtz_calendars` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `CALENDAR_NAME` varchar(200) NOT NULL,
  `CALENDAR` blob NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`CALENDAR_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `qrtz_cron_triggers` */

DROP TABLE IF EXISTS `qrtz_cron_triggers`;

CREATE TABLE `qrtz_cron_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `CRON_EXPRESSION` varchar(200) NOT NULL,
  `TIME_ZONE_ID` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_cron_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `qrtz_fired_triggers` */

DROP TABLE IF EXISTS `qrtz_fired_triggers`;

CREATE TABLE `qrtz_fired_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `ENTRY_ID` varchar(95) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `FIRED_TIME` bigint(13) NOT NULL,
  `SCHED_TIME` bigint(13) NOT NULL,
  `PRIORITY` int(11) NOT NULL,
  `STATE` varchar(16) NOT NULL,
  `JOB_NAME` varchar(200) DEFAULT NULL,
  `JOB_GROUP` varchar(200) DEFAULT NULL,
  `IS_NONCONCURRENT` varchar(1) DEFAULT NULL,
  `REQUESTS_RECOVERY` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`ENTRY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `qrtz_job_details` */

DROP TABLE IF EXISTS `qrtz_job_details`;

CREATE TABLE `qrtz_job_details` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `JOB_NAME` varchar(200) NOT NULL,
  `JOB_GROUP` varchar(200) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `JOB_CLASS_NAME` varchar(250) NOT NULL,
  `IS_DURABLE` varchar(1) NOT NULL,
  `IS_NONCONCURRENT` varchar(1) NOT NULL,
  `IS_UPDATE_DATA` varchar(1) NOT NULL,
  `REQUESTS_RECOVERY` varchar(1) NOT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `qrtz_locks` */

DROP TABLE IF EXISTS `qrtz_locks`;

CREATE TABLE `qrtz_locks` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `LOCK_NAME` varchar(40) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`LOCK_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `qrtz_paused_trigger_grps` */

DROP TABLE IF EXISTS `qrtz_paused_trigger_grps`;

CREATE TABLE `qrtz_paused_trigger_grps` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `qrtz_scheduler_state` */

DROP TABLE IF EXISTS `qrtz_scheduler_state`;

CREATE TABLE `qrtz_scheduler_state` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `LAST_CHECKIN_TIME` bigint(13) NOT NULL,
  `CHECKIN_INTERVAL` bigint(13) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`INSTANCE_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `qrtz_simple_triggers` */

DROP TABLE IF EXISTS `qrtz_simple_triggers`;

CREATE TABLE `qrtz_simple_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `REPEAT_COUNT` bigint(7) NOT NULL,
  `REPEAT_INTERVAL` bigint(12) NOT NULL,
  `TIMES_TRIGGERED` bigint(10) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_simple_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `qrtz_simprop_triggers` */

DROP TABLE IF EXISTS `qrtz_simprop_triggers`;

CREATE TABLE `qrtz_simprop_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `STR_PROP_1` varchar(512) DEFAULT NULL,
  `STR_PROP_2` varchar(512) DEFAULT NULL,
  `STR_PROP_3` varchar(512) DEFAULT NULL,
  `INT_PROP_1` int(11) DEFAULT NULL,
  `INT_PROP_2` int(11) DEFAULT NULL,
  `LONG_PROP_1` bigint(20) DEFAULT NULL,
  `LONG_PROP_2` bigint(20) DEFAULT NULL,
  `DEC_PROP_1` decimal(13,4) DEFAULT NULL,
  `DEC_PROP_2` decimal(13,4) DEFAULT NULL,
  `BOOL_PROP_1` varchar(1) DEFAULT NULL,
  `BOOL_PROP_2` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_simprop_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `qrtz_triggers` */

DROP TABLE IF EXISTS `qrtz_triggers`;

CREATE TABLE `qrtz_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `JOB_NAME` varchar(200) NOT NULL,
  `JOB_GROUP` varchar(200) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `NEXT_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PREV_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PRIORITY` int(11) DEFAULT NULL,
  `TRIGGER_STATE` varchar(16) NOT NULL,
  `TRIGGER_TYPE` varchar(8) NOT NULL,
  `START_TIME` bigint(13) NOT NULL,
  `END_TIME` bigint(13) DEFAULT NULL,
  `CALENDAR_NAME` varchar(200) DEFAULT NULL,
  `MISFIRE_INSTR` smallint(2) DEFAULT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `SCHED_NAME` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  CONSTRAINT `qrtz_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) REFERENCES `qrtz_job_details` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `schedule_config_info` */

DROP TABLE IF EXISTS `schedule_config_info`;

CREATE TABLE `schedule_config_info` (
  `config_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '配置主键',
  `config_name` varchar(100) DEFAULT NULL COMMENT '���置名称',
  `config_code` varchar(20) DEFAULT NULL COMMENT '配置标识',
  `param_infos` varchar(2000) DEFAULT NULL COMMENT '配置参数',
  `belong_uid` bigint(20) DEFAULT NULL COMMENT '所属用户',
  `belong_group` bigint(20) DEFAULT NULL COMMENT '所属组织',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `up_person` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`config_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='公共参数配置';

/*Table structure for table `schedule_db_info` */

DROP TABLE IF EXISTS `schedule_db_info`;

CREATE TABLE `schedule_db_info` (
  `db_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '数据库配置主键',
  `db_name` varchar(100) DEFAULT NULL COMMENT '数据库名称',
  `db_type` varchar(20) DEFAULT NULL COMMENT '数据库类型',
  `address` varchar(200) DEFAULT NULL COMMENT '地址和端口号',
  `auth_user` varchar(100) DEFAULT NULL COMMENT '用户名',
  `auth_pwd` varchar(100) DEFAULT NULL COMMENT '密码',
  `params` varchar(100) DEFAULT NULL COMMENT '数据库连接参数',
  `belong_uid` bigint(20) DEFAULT NULL COMMENT '所属用户',
  `belong_group` bigint(20) DEFAULT NULL COMMENT '所属组织',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `up_person` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`db_id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8 COMMENT='数据库配置';

/*Table structure for table `schedule_db_partition` */

DROP TABLE IF EXISTS `schedule_db_partition`;

CREATE TABLE `schedule_db_partition` (
  `db_partition_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '数据库分区主键',
  `db_id` int(11) DEFAULT NULL COMMENT '数据库���置关联主键',
  `pname` varchar(100) DEFAULT NULL COMMENT '分区库中文名称',
  `db_name` varchar(100) DEFAULT NULL COMMENT '分区库名标识',
  `params` varchar(200) DEFAULT NULL COMMENT '分区参数以 &进行分割，k=v&b=d',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `up_person` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`db_partition_id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COMMENT='数据库分库分区信息';

/*Table structure for table `schedule_master_server` */

DROP TABLE IF EXISTS `schedule_master_server`;

CREATE TABLE `schedule_master_server` (
  `master_server_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `server_uuid` varchar(60) NOT NULL COMMENT '服务器唯一表示',
  `last_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '主服务器',
  PRIMARY KEY (`master_server_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='主服务器信息记录';

/*Table structure for table `schedule_node` */

DROP TABLE IF EXISTS `schedule_node`;

CREATE TABLE `schedule_node` (
  `node_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '节点主键id',
  `node_name` varchar(50) DEFAULT '' COMMENT '节点名称',
  `domainIp` varchar(500) NOT NULL DEFAULT '' COMMENT '节点域名ip',
  `node_state` varchar(100) DEFAULT NULL COMMENT '节点状态',
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `auth_user` varchar(50) DEFAULT NULL COMMENT '验证用户名',
  `auth_pwd` varchar(1000) DEFAULT NULL COMMENT '经过加密的验证密码',
  `belong_uid` bigint(20) DEFAULT NULL COMMENT '所属用户',
  `belong_group` bigint(20) DEFAULT NULL COMMENT '所属组织',
  `up_person` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`node_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='执行节点';

/*Table structure for table `schedule_partition_execution` */

DROP TABLE IF EXISTS `schedule_partition_execution`;

CREATE TABLE `schedule_partition_execution` (
  `partition_execution_id` bigint(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `schedule_execution_id` bigint(11) NOT NULL COMMENT '调度执行主键关联',
  `run_state` varchar(20) DEFAULT NULL COMMENT '执行状态，停止，待运行，运行中，执行完成',
  `task_key` varchar(20) DEFAULT NULL COMMENT '执行任务key',
  `partition_id` int(11) DEFAULT NULL COMMENT '执行的任务分区主键',
  `task_execution_id` bigint(20) DEFAULT NULL COMMENT '任务执行的主键关联',
  `history_tasks` varchar(2000) DEFAULT NULL COMMENT '重跑时之前存储的历史任务执行主键，逗号分隔',
  `terminal_time` datetime DEFAULT NULL COMMENT '终止时间',
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  `up_person` varchar(50) DEFAULT NULL,
  `belong_group` bigint(20) DEFAULT NULL,
  `belong_uid` bigint(20) DEFAULT NULL,
  `execution_msg` varchar(3000) DEFAULT NULL COMMENT '执行结果消息',
  `partition_execution_params` varchar(4000) DEFAULT NULL COMMENT '分区执行参数',
  `external_execution_id` varchar(100) DEFAULT NULL COMMENT '执行分区容器地址',
  `node_name` varchar(200) DEFAULT NULL,
  `job_id` bigint(32) DEFAULT NULL,
  PRIMARY KEY (`partition_execution_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12912 DEFAULT CHARSET=utf8 COMMENT='任务分区执行记录';

/*Table structure for table `schedule_plan` */

DROP TABLE IF EXISTS `schedule_plan`;

CREATE TABLE `schedule_plan` (
  `plan_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '计划主键',
  `plan_name` varchar(100) DEFAULT NULL COMMENT '计划名称',
  `task_chain` text COMMENT '任务key调用链，用逗号分隔，顺序执行，最多挂100个链',
  `run_time` varchar(20) DEFAULT NULL COMMENT '运行时间',
  `period` varchar(20) DEFAULT NULL COMMENT '执行周期，每日���每周，每月',
  `state` varchar(20) DEFAULT NULL COMMENT '正常，下线',
  `scopes` varchar(200) DEFAULT NULL COMMENT '周期间隔，逗号分隔',
  `belong_group` bigint(20) DEFAULT NULL COMMENT '所属组织',
  `belong_uid` bigint(20) DEFAULT NULL COMMENT '所属用户',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `up_person` varchar(50) DEFAULT NULL,
  `strict_error` tinyint(1) DEFAULT NULL COMMENT '严格错误模式',
  `run_type` varchar(20) DEFAULT NULL COMMENT '运行方式',
  `status` varchar(2) DEFAULT NULL COMMENT '启用状态',
  `relate_plan_id` varchar(2) DEFAULT NULL,
  `task_link` text COMMENT '任务分层结构',
  PRIMARY KEY (`plan_id`)
) ENGINE=InnoDB AUTO_INCREMENT=119 DEFAULT CHARSET=utf8 COMMENT='调度计划';

/*Table structure for table `schedule_plan_execution` */

DROP TABLE IF EXISTS `schedule_plan_execution`;

CREATE TABLE `schedule_plan_execution` (
  `schedule_execution_id` bigint(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `start_time` datetime DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '结束时间',
  `terminal_time` datetime DEFAULT NULL COMMENT '终止时间',
  `create_time` timestamp NULL DEFAULT NULL,
  `plan_id` int(11) DEFAULT NULL COMMENT '调度计划主键关联',
  `run_state` varchar(20) DEFAULT NULL COMMENT '执行状态，停止，待运行，运行中，执行完成',
  `update_time` timestamp NULL DEFAULT NULL,
  `up_person` varchar(50) DEFAULT NULL,
  `belong_group` bigint(20) DEFAULT NULL,
  `belong_uid` bigint(20) DEFAULT NULL,
  `completed_task_keys` varchar(2000) DEFAULT NULL,
  `running_task_key` varchar(200) DEFAULT NULL,
  `fail_task_keys` varchar(2000) DEFAULT NULL,
  `execution_msg` varchar(1000) DEFAULT NULL,
  `out_params` varchar(2000) DEFAULT NULL COMMENT '外部参数',
  `task_chain` text,
  `rerun_error_task_keys` text COMMENT '异常重跑taskkeys',
  `status` varchar(2) DEFAULT NULL COMMENT '计划状态',
  `before_close_state` varchar(10) DEFAULT NULL COMMENT '执行记录关闭前的状态(用于还原使用)',
  `task_link` text,
  PRIMARY KEY (`schedule_execution_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6594 DEFAULT CHARSET=utf8 COMMENT='调度计划执行记录';

/*Table structure for table `schedule_plan_execution_task` */

DROP TABLE IF EXISTS `schedule_plan_execution_task`;

CREATE TABLE `schedule_plan_execution_task` (
  `plan_exeuction_task_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `schedule_execution_id` bigint(32) DEFAULT NULL COMMENT '计划执行id',
  `task_key` char(20) DEFAULT NULL COMMENT '任务key',
  `run_state` varchar(20) DEFAULT NULL COMMENT '运行状态',
  PRIMARY KEY (`plan_exeuction_task_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13217 DEFAULT CHARSET=utf8 COMMENT='计划执行任务表';

/*Table structure for table `schedule_record` */

DROP TABLE IF EXISTS `schedule_record`;

CREATE TABLE `schedule_record` (
  `record_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '操作记录主键id',
  `record_title` varchar(50) DEFAULT '' COMMENT '执行事件',
  `record_content` varchar(100) NOT NULL DEFAULT '' COMMENT '关联内容',
  `record_params` text COMMENT '相关参数',
  `record_user_id` int(11) DEFAULT NULL COMMENT '执行人',
  `record_user_name` varchar(50) DEFAULT NULL COMMENT '执行人名称',
  `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '执行时间',
  PRIMARY KEY (`record_id`)
) ENGINE=InnoDB AUTO_INCREMENT=930 DEFAULT CHARSET=utf8 COMMENT='操作记录';

/*Table structure for table `schedule_task` */

DROP TABLE IF EXISTS `schedule_task`;

CREATE TABLE `schedule_task` (
  `task_name` varchar(50) NOT NULL DEFAULT '' COMMENT '任务名称',
  `task_key` varchar(20) NOT NULL DEFAULT '' COMMENT '任务唯一标识16位',
  `job_name` varchar(50) DEFAULT '' COMMENT '执行job的名称',
  `task_definition` varchar(100) NOT NULL COMMENT '关联task的definition',
  `task_params` varchar(2000) DEFAULT '' COMMENT '任务参数',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `up_person` varchar(50) DEFAULT '' COMMENT '更新人',
  `belong_uid` bigint(20) DEFAULT NULL COMMENT '所属用户',
  `belong_group` bigint(20) DEFAULT NULL COMMENT '所属组织',
  `status` char(1) DEFAULT NULL COMMENT '任务状态（1：有效， d：删除）',
  PRIMARY KEY (`task_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='任务调度编排';

/*Table structure for table `schedule_task_partition` */

DROP TABLE IF EXISTS `schedule_task_partition`;

CREATE TABLE `schedule_task_partition` (
  `partition_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `task_key` varchar(20) NOT NULL DEFAULT '' COMMENT '关联taskkey',
  `node_id` int(11) NOT NULL COMMENT '执行节点Id',
  `db_partition_id` int(11) DEFAULT NULL COMMENT '数据库分区表主键',
  `config_id` varchar(100) DEFAULT NULL COMMENT '参数配置主键',
  `parameters` varchar(1000) DEFAULT NULL COMMENT '执行job时的参数',
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `up_person` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`partition_id`),
  KEY `key_idx` (`task_key`)
) ENGINE=InnoDB AUTO_INCREMENT=933 DEFAULT CHARSET=utf8 COMMENT='任务分区';

/*Table structure for table `stream_definitions` */

DROP TABLE IF EXISTS `stream_definitions`;

CREATE TABLE `stream_definitions` (
  `DEFINITION_NAME` varchar(255) NOT NULL,
  `DEFINITION` text,
  PRIMARY KEY (`DEFINITION_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `stream_deployments` */

DROP TABLE IF EXISTS `stream_deployments`;

CREATE TABLE `stream_deployments` (
  `STREAM_NAME` varchar(255) NOT NULL,
  `DEPLOYMENT_PROPS` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`STREAM_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `task_definitions` */

DROP TABLE IF EXISTS `task_definitions`;

CREATE TABLE `task_definitions` (
  `DEFINITION_NAME` varchar(255) NOT NULL,
  `DEFINITION` text,
  PRIMARY KEY (`DEFINITION_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `task_execution` */

DROP TABLE IF EXISTS `task_execution`;

CREATE TABLE `task_execution` (
  `TASK_EXECUTION_ID` bigint(20) NOT qNULL,
  `START_TIME` datetime DEFAULT NULL,
  `END_TIME` datetime DEFAULT NULL,
  `TASK_NAME` varchar(100) DEFAULT NULL,
  `EXIT_CODE` int(11) DEFAULT NULL,
  `EXIT_MESSAGE` varchar(2500) DEFAULT NULL,
  `ERROR_MESSAGE` varchar(2500) DEFAULT NULL,
  `LAST_UPDATED` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `EXTERNAL_EXECUTION_ID` varchar(255) DEFAULT NULL,
  `PARENT_EXECUTION_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`TASK_EXECUTION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `task_execution_params` */

DROP TABLE IF EXISTS `task_execution_params`;

CREATE TABLE `task_execution_params` (
  `TASK_EXECUTION_ID` bigint(20) NOT NULL,
  `TASK_PARAM` varchar(2500) DEFAULT NULL,
  KEY `TASK_EXEC_PARAMS_FK` (`TASK_EXECUTION_ID`),
  CONSTRAINT `TASK_EXEC_PARAMS_FK` FOREIGN KEY (`TASK_EXECUTION_ID`) REFERENCES `task_execution` (`TASK_EXECUTION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `task_info` */

DROP TABLE IF EXISTS `task_info`;

CREATE TABLE `task_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `job_execution_id` int(11) NOT NULL,
  `task_name` varchar(64) NOT NULL,
  `start_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  `end_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='任务表';

/*Table structure for table `task_lock` */

DROP TABLE IF EXISTS `task_lock`;

CREATE TABLE `task_lock` (
  `LOCK_KEY` char(36) NOT NULL,
  `REGION` varchar(100) NOT NULL,
  `CLIENT_ID` char(36) DEFAULT NULL,
  `CREATED_DATE` datetime(6) NOT NULL,
  PRIMARY KEY (`LOCK_KEY`,`REGION`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `task_seq` */

DROP TABLE IF EXISTS `task_seq`;

CREATE TABLE `task_seq` (
  `ID` bigint(20) NOT NULL,
  `UNIQUE_KEY` char(1) NOT NULL,
  UNIQUE KEY `UNIQUE_KEY_UN` (`UNIQUE_KEY`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `task_task_batch` */

DROP TABLE IF EXISTS `task_task_batch`;

CREATE TABLE `task_task_batch` (
  `TASK_EXECUTION_ID` bigint(20) NOT NULL,
  `JOB_EXECUTION_ID` bigint(20) NOT NULL,
  KEY `TASK_EXEC_BATCH_FK` (`TASK_EXECUTION_ID`),
  CONSTRAINT `TASK_EXEC_BATCH_FK` FOREIGN KEY (`TASK_EXECUTION_ID`) REFERENCES `task_execution` (`TASK_EXECUTION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `transaction_info` */

DROP TABLE IF EXISTS `transaction_info`;

CREATE TABLE `transaction_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `transaction_date` varchar(8) DEFAULT NULL COMMENT '交易日期：CCYYMMDD',
  `transaction_time` varchar(6) DEFAULT NULL COMMENT '交易时间：HHMMSS',
  `card_number` varchar(19) DEFAULT NULL COMMENT '卡号',
  `account_number` char(32) DEFAULT NULL COMMENT '账户号',
  `transaction_type` char(1) DEFAULT NULL COMMENT '交易类型：0-取现 1-消费',
  `transaction_amount` decimal(18,2) DEFAULT NULL COMMENT '交易金额',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='交易表';

/*Table structure for table `uri_registry` */

DROP TABLE IF EXISTS `uri_registry`;

CREATE TABLE `uri_registry` (
  `NAME` varchar(255) NOT NULL,
  `URI` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


/**
调度中心表添加索引
 */
DROP INDEX idx_run_state ON schedule_partition_execution;
ALTER TABLE schedule_partition_execution ADD INDEX idx_run_state (run_state);
DROP INDEX idx_schedule_execution_id ON schedule_partition_execution;
ALTER TABLE schedule_partition_execution ADD INDEX idx_schedule_execution_id (schedule_execution_id);
DROP INDEX idx_task_key ON schedule_partition_execution;
ALTER TABLE schedule_partition_execution ADD INDEX idx_task_key(task_key);


DROP INDEX idx_run_state ON schedule_plan_execution_task;
ALTER TABLE schedule_plan_execution_task ADD INDEX idx_run_state (run_state);
DROP INDEX idx_schedule_execution_id ON schedule_plan_execution_task;
ALTER TABLE schedule_plan_execution_task ADD INDEX idx_schedule_execution_id (schedule_execution_id);


DROP INDEX idx_run_state ON schedule_plan_execution;
ALTER TABLE schedule_plan_execution ADD INDEX idx_run_state (run_state);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

/**
调度中心表添加索引
 */
DROP INDEX idx_run_state ON schedule_partition_execution;
ALTER TABLE schedule_partition_execution ADD INDEX idx_run_state (run_state);
DROP INDEX idx_schedule_execution_id ON schedule_partition_execution;
ALTER TABLE schedule_partition_execution ADD INDEX idx_schedule_execution_id (schedule_execution_id);
DROP INDEX idx_task_key ON schedule_partition_execution;
ALTER TABLE schedule_partition_execution ADD INDEX idx_task_key(task_key);


DROP INDEX idx_run_state ON schedule_plan_execution_task;
ALTER TABLE schedule_plan_execution_task ADD INDEX idx_run_state (run_state);
DROP INDEX idx_schedule_execution_id ON schedule_plan_execution_task;
ALTER TABLE schedule_plan_execution_task ADD INDEX idx_schedule_execution_id (schedule_execution_id);


DROP INDEX idx_run_state ON schedule_plan_execution;
ALTER TABLE schedule_plan_execution ADD INDEX idx_run_state (run_state);




