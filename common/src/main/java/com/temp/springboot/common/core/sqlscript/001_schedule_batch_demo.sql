/*
SQLyog  v12.2.6 (64 bit)
MySQL - 5.6.42 : Database - batch_demo_test
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
) ENGINE=InnoDB AUTO_INCREMENT=94 DEFAULT CHARSET=utf8 COMMENT='账户表';

/*Data for the table `account_info` */

/*Table structure for table `app_registration` */

DROP TABLE IF EXISTS `app_registration`;

CREATE TABLE `app_registration` (
  `id` bigint(20) NOT NULL,
  `object_Version` bigint(20) DEFAULT NULL,
  `default_Version` bit(1) DEFAULT NULL,
  `metadata_Uri` longtext,
  `name` varchar(255) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `uri` longtext,
  `version` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `app_registration` */

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

/*Data for the table `back_list` */

/*Table structure for table `batch_job_execution` */

DROP TABLE IF EXISTS `batch_job_execution`;

CREATE TABLE `batch_job_execution` (
  `JOB_EXECUTION_ID` bigint(20) NOT NULL,
  `VERSION` bigint(20) DEFAULT NULL,
  `JOB_INSTANCE_ID` bigint(20) NOT NULL,
  `CREATE_TIME` datetime NOT NULL,
  `START_TIME` datetime DEFAULT NULL,
  `END_TIME` datetime DEFAULT NULL,
  `STATUS` varchar(10) DEFAULT NULL,
  `EXIT_CODE` varchar(2500) DEFAULT NULL,
  `EXIT_MESSAGE` varchar(2500) DEFAULT NULL,
  `LAST_UPDATED` datetime DEFAULT NULL,
  `JOB_CONFIGURATION_LOCATION` varchar(2500) DEFAULT NULL,
  PRIMARY KEY (`JOB_EXECUTION_ID`),
  KEY `JOB_INST_EXEC_FK` (`JOB_INSTANCE_ID`),
  CONSTRAINT `JOB_INST_EXEC_FK` FOREIGN KEY (`JOB_INSTANCE_ID`) REFERENCES `batch_job_instance` (`JOB_INSTANCE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `batch_job_execution` */

/*Table structure for table `batch_job_execution_context` */

DROP TABLE IF EXISTS `batch_job_execution_context`;

CREATE TABLE `batch_job_execution_context` (
  `JOB_EXECUTION_ID` bigint(20) NOT NULL,
  `SHORT_CONTEXT` varchar(2500) NOT NULL,
  `SERIALIZED_CONTEXT` text,
  PRIMARY KEY (`JOB_EXECUTION_ID`),
  CONSTRAINT `JOB_EXEC_CTX_FK` FOREIGN KEY (`JOB_EXECUTION_ID`) REFERENCES `batch_job_execution` (`JOB_EXECUTION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `batch_job_execution_context` */

/*Table structure for table `batch_job_execution_params` */

DROP TABLE IF EXISTS `batch_job_execution_params`;

CREATE TABLE `batch_job_execution_params` (
  `JOB_EXECUTION_ID` bigint(20) NOT NULL,
  `TYPE_CD` varchar(6) NOT NULL,
  `KEY_NAME` varchar(100) NOT NULL,
  `STRING_VAL` varchar(250) DEFAULT NULL,
  `DATE_VAL` datetime DEFAULT NULL,
  `LONG_VAL` bigint(20) DEFAULT NULL,
  `DOUBLE_VAL` double DEFAULT NULL,
  `IDENTIFYING` char(1) NOT NULL,
  KEY `JOB_EXEC_PARAMS_FK` (`JOB_EXECUTION_ID`),
  CONSTRAINT `JOB_EXEC_PARAMS_FK` FOREIGN KEY (`JOB_EXECUTION_ID`) REFERENCES `batch_job_execution` (`JOB_EXECUTION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `batch_job_execution_params` */

/*Table structure for table `batch_job_execution_seq` */

DROP TABLE IF EXISTS `batch_job_execution_seq`;

CREATE TABLE `batch_job_execution_seq` (
  `ID` bigint(20) NOT NULL,
  `UNIQUE_KEY` char(1) NOT NULL,
  UNIQUE KEY `UNIQUE_KEY_UN` (`UNIQUE_KEY`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `batch_job_execution_seq` */

insert  into `batch_job_execution_seq`(`ID`,`UNIQUE_KEY`) values
(0,'0');

/*Table structure for table `batch_job_instance` */

DROP TABLE IF EXISTS `batch_job_instance`;

CREATE TABLE `batch_job_instance` (
  `JOB_INSTANCE_ID` bigint(20) NOT NULL,
  `VERSION` bigint(20) DEFAULT NULL,
  `JOB_NAME` varchar(100) NOT NULL,
  `JOB_KEY` varchar(32) NOT NULL,
  PRIMARY KEY (`JOB_INSTANCE_ID`),
  UNIQUE KEY `JOB_INST_UN` (`JOB_NAME`,`JOB_KEY`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `batch_job_instance` */

/*Table structure for table `batch_job_seq` */

DROP TABLE IF EXISTS `batch_job_seq`;

CREATE TABLE `batch_job_seq` (
  `ID` bigint(20) NOT NULL,
  `UNIQUE_KEY` char(1) NOT NULL,
  UNIQUE KEY `UNIQUE_KEY_UN` (`UNIQUE_KEY`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `batch_job_seq` */

insert  into `batch_job_seq`(`ID`,`UNIQUE_KEY`) values
(0,'0');

/*Table structure for table `batch_step_execution` */

DROP TABLE IF EXISTS `batch_step_execution`;

CREATE TABLE `batch_step_execution` (
  `STEP_EXECUTION_ID` bigint(20) NOT NULL,
  `VERSION` bigint(20) NOT NULL,
  `STEP_NAME` varchar(100) NOT NULL,
  `JOB_EXECUTION_ID` bigint(20) NOT NULL,
  `START_TIME` datetime NOT NULL,
  `END_TIME` datetime DEFAULT NULL,
  `STATUS` varchar(10) DEFAULT NULL,
  `COMMIT_COUNT` bigint(20) DEFAULT NULL,
  `READ_COUNT` bigint(20) DEFAULT NULL,
  `FILTER_COUNT` bigint(20) DEFAULT NULL,
  `WRITE_COUNT` bigint(20) DEFAULT NULL,
  `READ_SKIP_COUNT` bigint(20) DEFAULT NULL,
  `WRITE_SKIP_COUNT` bigint(20) DEFAULT NULL,
  `PROCESS_SKIP_COUNT` bigint(20) DEFAULT NULL,
  `ROLLBACK_COUNT` bigint(20) DEFAULT NULL,
  `EXIT_CODE` varchar(2500) DEFAULT NULL,
  `EXIT_MESSAGE` varchar(2500) DEFAULT NULL,
  `LAST_UPDATED` datetime DEFAULT NULL,
  PRIMARY KEY (`STEP_EXECUTION_ID`),
  KEY `JOB_EXEC_STEP_FK` (`JOB_EXECUTION_ID`),
  CONSTRAINT `JOB_EXEC_STEP_FK` FOREIGN KEY (`JOB_EXECUTION_ID`) REFERENCES `batch_job_execution` (`JOB_EXECUTION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `batch_step_execution` */

/*Table structure for table `batch_step_execution_context` */

DROP TABLE IF EXISTS `batch_step_execution_context`;

CREATE TABLE `batch_step_execution_context` (
  `STEP_EXECUTION_ID` bigint(20) NOT NULL,
  `SHORT_CONTEXT` varchar(2500) NOT NULL,
  `SERIALIZED_CONTEXT` text,
  PRIMARY KEY (`STEP_EXECUTION_ID`),
  CONSTRAINT `STEP_EXEC_CTX_FK` FOREIGN KEY (`STEP_EXECUTION_ID`) REFERENCES `batch_step_execution` (`STEP_EXECUTION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `batch_step_execution_context` */

/*Table structure for table `batch_step_execution_seq` */

DROP TABLE IF EXISTS `batch_step_execution_seq`;

CREATE TABLE `batch_step_execution_seq` (
  `ID` bigint(20) NOT NULL,
  `UNIQUE_KEY` char(1) NOT NULL,
  UNIQUE KEY `UNIQUE_KEY_UN` (`UNIQUE_KEY`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `batch_step_execution_seq` */

insert  into `batch_step_execution_seq`(`ID`,`UNIQUE_KEY`) values
(0,'0');

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
) ENGINE=InnoDB AUTO_INCREMENT=187 DEFAULT CHARSET=utf8 COMMENT='卡表';

/*Data for the table `card_info` */

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
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=94 DEFAULT CHARSET=utf8 COMMENT='客户表';

/*Data for the table `customer_info` */

/*Table structure for table `deployment_ids` */

DROP TABLE IF EXISTS `deployment_ids`;

CREATE TABLE `deployment_ids` (
  `DEPLOYMENT_KEY` varchar(255) NOT NULL,
  `DEPLOYMENT_ID` varchar(255) NOT NULL,
  PRIMARY KEY (`DEPLOYMENT_KEY`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `deployment_ids` */

/*Table structure for table `hibernate_sequence` */

DROP TABLE IF EXISTS `hibernate_sequence`;

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `hibernate_sequence` */

/*Table structure for table `schedule_config_info` */

DROP TABLE IF EXISTS `schedule_config_info`;

CREATE TABLE `schedule_config_info` (
  `config_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '配置主键',
  `config_name` varchar(100) DEFAULT NULL COMMENT '配置名称',
  `config_code` varchar(20) DEFAULT NULL COMMENT '配置标识',
  `param_infos` varchar(2000) DEFAULT NULL COMMENT '配置参数',
  `belong_uid` bigint(20) DEFAULT NULL COMMENT '所属用户',
  `belong_group` bigint(20) DEFAULT NULL COMMENT '所属组织',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `up_person` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`config_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COMMENT='公共参数配置';

/*Data for the table `schedule_config_info` */

insert  into `schedule_config_info`(`config_id`,`config_name`,`config_code`,`param_infos`,`belong_uid`,`belong_group`,`create_time`,`update_time`,`up_person`) values
(13,'文件路径','file_path','{\"txt\":\"/home/txt\"}',1,1,'2019-10-24 15:58:49','2019-10-24 15:58:49','UP_SYSTEM');

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
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8 COMMENT='数据库配置';

/*Data for the table `schedule_db_info` */

insert  into `schedule_db_info`(`db_id`,`db_name`,`db_type`,`address`,`auth_user`,`auth_pwd`,`params`,`belong_uid`,`belong_group`,`create_time`,`update_time`,`up_person`) values
(22,'batch_demo_test','mysql','192.168.42.136:3306','root','MYvtB8kUkew=','encoding=utf-8&ssL=false',1,1,'2019-10-24 15:46:07','2019-10-24 15:47:34','UP_SYSTEM');

/*Table structure for table `schedule_db_partition` */

DROP TABLE IF EXISTS `schedule_db_partition`;

CREATE TABLE `schedule_db_partition` (
  `db_partition_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '数据库分区主键',
  `db_id` int(11) DEFAULT NULL COMMENT '数据库配置关联主键',
  `pname` varchar(100) DEFAULT NULL COMMENT '分区库中文名称',
  `db_name` varchar(100) DEFAULT NULL COMMENT '分区库名标识',
  `params` varchar(200) DEFAULT NULL COMMENT '分区参数以 &进行分割，k=v&b=d',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `up_person` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`db_partition_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COMMENT='数据库分库分区信息';

/*Data for the table `schedule_db_partition` */

insert  into `schedule_db_partition`(`db_partition_id`,`db_id`,`pname`,`db_name`,`params`,`create_time`,`update_time`,`up_person`) values
(13,22,'分库1','batch_demo_test','useSSL=false&useUnicode=true&characterEncoding=UTF-8','2019-10-24 15:52:20','2019-10-24 15:52:20','UP_SYSTEM');

/*Table structure for table `schedule_master_server` */

DROP TABLE IF EXISTS `schedule_master_server`;

CREATE TABLE `schedule_master_server` (
  `master_server_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `server_uuid` varchar(60) NOT NULL COMMENT '服务器唯一表示',
  `last_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '主服务器',
  PRIMARY KEY (`master_server_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='主服务器信息记录';

/*Data for the table `schedule_master_server` */

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
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8 COMMENT='执行节点';

/*Data for the table `schedule_node` */

insert  into `schedule_node`(`node_id`,`node_name`,`domainIp`,`node_state`,`create_time`,`update_time`,`auth_user`,`auth_pwd`,`belong_uid`,`belong_group`,`up_person`) values
(27,'localtest','localhost:8080','ONLINE','2019-10-24 15:53:58','2019-10-24 15:53:58','','',1,1,'UP_SYSTEM');

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
  PRIMARY KEY (`partition_execution_id`)
) ENGINE=InnoDB AUTO_INCREMENT=113 DEFAULT CHARSET=utf8 COMMENT='任务分区执行记录';

/*Data for the table `schedule_partition_execution` */

insert  into `schedule_partition_execution`(`partition_execution_id`,`schedule_execution_id`,`run_state`,`task_key`,`partition_id`,`task_execution_id`,`history_tasks`,`terminal_time`,`create_time`,`update_time`,`up_person`,`belong_group`,`belong_uid`,`execution_msg`,`partition_execution_params`,`external_execution_id`) values
(112,100,'FAIL','157190002286523651',64,NULL,NULL,NULL,'2019-10-24 16:15:33','2019-10-24 16:15:33','UP_SYSTEM',1,1,'Could not find task definition named local_task','--task.partitionExecutionId=112,--task.taskKid=157190002286523651,--name=test,--task.partitionId=64,--task.db.address=192.168.42.136:3306?encoding=utf-8&ssL=false,--task.db.username=root,--task.db.pwd=******,--task.db.name=batch_demo_test,--task.db.parameters=useSSL=false&useUnicode=true&characterEncoding=UTF-8,--txt=/home/txt',NULL);

/*Table structure for table `schedule_plan` */

DROP TABLE IF EXISTS `schedule_plan`;

CREATE TABLE `schedule_plan` (
  `plan_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '计划主键',
  `plan_name` varchar(100) DEFAULT NULL COMMENT '计划名称',
  `task_chain` text COMMENT '任务key调用链，用逗号分隔，顺序执行，最多挂100个链',
  `run_time` varchar(20) DEFAULT NULL COMMENT '运行时间',
  `period` varchar(20) DEFAULT NULL COMMENT '执行周期，每日，每周，每月',
  `state` varchar(20) DEFAULT NULL COMMENT '正常，下线',
  `scopes` varchar(200) DEFAULT NULL COMMENT '周期间隔，逗号分隔',
  `belong_group` bigint(20) DEFAULT NULL COMMENT '所属组织',
  `belong_uid` bigint(20) DEFAULT NULL COMMENT '所属用户',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `up_person` varchar(50) DEFAULT NULL,
  `strict_error` tinyint(1) DEFAULT NULL COMMENT '严格错误模式',
  `run_type` varchar(20) DEFAULT NULL COMMENT '运行方式',
  PRIMARY KEY (`plan_id`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8 COMMENT='调度计划';

/*Data for the table `schedule_plan` */

insert  into `schedule_plan`(`plan_id`,`plan_name`,`task_chain`,`run_time`,`period`,`state`,`scopes`,`belong_group`,`belong_uid`,`create_time`,`update_time`,`up_person`,`strict_error`,`run_type`) values
(51,'执行本地jar','{\"nodeDatas\":[{\"key\":\"1\",\"type\":\"start\",\"text\":\"任务开始\"},{\"key\":\"157190002286523651\",\"type\":\"node\",\"text\":\"执行本地jar\"},{\"key\":\"-1\",\"type\":\"end\",\"text\":\"任务结束\"}],\"linkDatas\":[{\"from\":\"1\",\"to\":\"157190002286523651\"},{\"from\":\"157190002286523651\",\"to\":\"-1\"}]}',NULL,NULL,'ONLINE',NULL,1,1,'2019-10-24 16:07:06','2019-10-24 16:10:39','UP_SYSTEM',1,'MANUAL');

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
  PRIMARY KEY (`schedule_execution_id`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8 COMMENT='调度计划执行记录';

/*Data for the table `schedule_plan_execution` */

insert  into `schedule_plan_execution`(`schedule_execution_id`,`start_time`,`end_time`,`terminal_time`,`create_time`,`plan_id`,`run_state`,`update_time`,`up_person`,`belong_group`,`belong_uid`,`completed_task_keys`,`running_task_key`,`fail_task_keys`,`execution_msg`,`out_params`,`task_chain`) values
(100,'2019-10-24 16:15:33',NULL,NULL,'2019-10-24 16:15:33',51,'COMPLETE','2019-10-24 16:15:33','UP_SYSTEM',1,1,NULL,'157190002286523651',NULL,'调度任务分区有异常：planExecutionId: 100 , err: ',NULL,'{\"nodeDatas\":[{\"key\":\"1\",\"type\":\"start\",\"text\":\"任务开始\"},{\"key\":\"157190002286523651\",\"type\":\"node\",\"text\":\"执行本地jar\"},{\"key\":\"-1\",\"type\":\"end\",\"text\":\"任务结束\"}],\"linkDatas\":[{\"from\":\"1\",\"to\":\"157190002286523651\"},{\"from\":\"157190002286523651\",\"to\":\"-1\"}]}');

/*Table structure for table `schedule_record` */

DROP TABLE IF EXISTS `schedule_record`;

CREATE TABLE `schedule_record` (
  `record_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '操作记录主键id',
  `record_title` varchar(50) DEFAULT '' COMMENT '执行事件',
  `record_content` varchar(100) NOT NULL DEFAULT '' COMMENT '关联内容',
  `record_params` varchar(2000) DEFAULT NULL COMMENT '相关参数',
  `record_user_id` int(11) DEFAULT NULL COMMENT '执行人',
  `record_user_name` varchar(50) DEFAULT NULL COMMENT '执行人名称',
  `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '执行时间',
  PRIMARY KEY (`record_id`)
) ENGINE=InnoDB AUTO_INCREMENT=84 DEFAULT CHARSET=utf8 COMMENT='操作记录';

/*Data for the table `schedule_record` */

insert  into `schedule_record`(`record_id`,`record_title`,`record_content`,`record_params`,`record_user_id`,`record_user_name`,`create_time`) values
(74,'任务管理','创建任务','{\"taskKey\":\"157190002286523651\",\"taskName\":\"执行本地jar\",\"olbTaskName\":null,\"jobName\":\"\",\"taskDefinition\":\"local_task\",\"taskState\":null,\"taskPartitionSize\":null,\"paramsMap\":{},\"entriesParamsMap\":[]}',NULL,'system_user','2019-10-24 14:53:43'),
(75,'资源配置','创建数据库','{\"dbId\":22,\"dbName\":\"batch_demo_test\",\"dbType\":\"mysql\",\"address\":\"jdbc:mysql://192.168.42.136:3306\",\"authUser\":\"root\",\"authPwd\":\"sauitFCYcPc=\",\"params\":\"encoding=utf-8&ssL=false\",\"dbSize\":0}',NULL,'system_user','2019-10-24 15:46:07'),
(76,'资源配置','修改数据库','{\"dbId\":22,\"dbName\":\"batch_demo_test\",\"dbType\":\"mysql\",\"address\":\"192.168.42.136:3306\",\"authUser\":\"root\",\"authPwd\":\"******\",\"params\":\"encoding=utf-8&ssL=false\",\"dbSize\":null}',NULL,'system_user','2019-10-24 15:47:34'),
(77,'资源配置','创建数据库分区','{\"dbPartitionId\":13,\"dbId\":22,\"pname\":\"分库1\",\"dbName\":\"batch_demo_test\",\"params\":\"useSSL=false&useUnicode=true&characterEncoding=UTF-8\"}',NULL,'system_user','2019-10-24 15:52:20'),
(78,'资源配置','创建执行节点','{\"nodeId\":27,\"nodeName\":\"localtest\",\"domainip\":\"localhost:8080\",\"nodeState\":\"ONLINE\",\"authUser\":\"\",\"authPwd\":\"*****\"}',NULL,'system_user','2019-10-24 15:53:58'),
(79,'资源配置','创建数据库配置信息','{\"configId\":13,\"configName\":\"文件路径\",\"configCode\":\"file_path\",\"paramsMap\":{\"txt\":\"/home/txt\"},\"entriesParamsMap\":[[\"txt\",\"/home/txt\"]]}',NULL,'system_user','2019-10-24 15:58:49'),
(80,'任务管理','任务：创建分区','{\"partitionId\":64,\"taskKey\":\"157190002286523651\",\"nodeId\":27,\"dbPartitionId\":13,\"configIds\":[13],\"configIdName\":null,\"paramsMap\":{\"name\":\"test\"},\"nodeName\":null,\"dbPartitionName\":null,\"dbName\":null,\"entriesParamsMap\":[[\"name\",\"test\"]],\"dbId\":\"22\"}',NULL,'system_user','2019-10-24 16:02:35'),
(81,'调度计划','创建计划','{\"planId\":51,\"planName\":\"执行本地jar\",\"taskChain\":\"\",\"runTime\":null,\"nextStartTime\":null,\"totalJobNumber\":null,\"planPeriod\":null,\"planState\":\"OFFLINE\",\"state\":\"\",\"runType\":\"MANUAL\",\"planScopes\":null,\"strictError\":true,\"planKey\":null,\"totalTaskNumber\":null,\"taskChainNode\":null}',NULL,'system_user','2019-10-24 16:07:06'),
(82,'调度计划','手动执行计划：执行调度计划','{\"planId\":51}',NULL,'system_user','2019-10-24 16:15:33'),
(83,'强制修改状态','调度计划执行任务：状态CLOSING修改为COMPLETE','{\"scheduleExecutionId\":100,\"state\":\"COMPLETE\"}',NULL,'system_user','2019-10-24 16:39:50');

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

/*Data for the table `schedule_task` */

insert  into `schedule_task`(`task_name`,`task_key`,`job_name`,`task_definition`,`task_params`,`create_time`,`update_time`,`up_person`,`belong_uid`,`belong_group`,`status`) values
('执行本地jar','157190002286523651','','local_task',NULL,'2019-10-24 14:53:43','2019-10-24 14:53:43','UP_SYSTEM',1,1,'1');

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
) ENGINE=InnoDB AUTO_INCREMENT=65 DEFAULT CHARSET=utf8 COMMENT='任务分区';

/*Data for the table `schedule_task_partition` */

insert  into `schedule_task_partition`(`partition_id`,`task_key`,`node_id`,`db_partition_id`,`config_id`,`parameters`,`create_time`,`update_time`,`up_person`) values
(64,'157190002286523651',27,13,'[13]','{\"name\":\"test\"}','2019-10-24 16:02:35','2019-10-24 16:02:35','UP_SYSTEM');

/*Table structure for table `stream_definitions` */

DROP TABLE IF EXISTS `stream_definitions`;

CREATE TABLE `stream_definitions` (
  `DEFINITION_NAME` varchar(255) NOT NULL,
  `DEFINITION` text,
  PRIMARY KEY (`DEFINITION_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `stream_definitions` */

/*Table structure for table `stream_deployments` */

DROP TABLE IF EXISTS `stream_deployments`;

CREATE TABLE `stream_deployments` (
  `STREAM_NAME` varchar(255) NOT NULL,
  `DEPLOYMENT_PROPS` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`STREAM_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `stream_deployments` */

/*Table structure for table `task_definitions` */

DROP TABLE IF EXISTS `task_definitions`;

CREATE TABLE `task_definitions` (
  `DEFINITION_NAME` varchar(255) NOT NULL,
  `DEFINITION` text,
  PRIMARY KEY (`DEFINITION_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `task_definitions` */

insert  into `task_definitions`(`DEFINITION_NAME`,`DEFINITION`) values
('local_task','customerSimpleJob');

/*Table structure for table `task_execution` */

DROP TABLE IF EXISTS `task_execution`;

CREATE TABLE `task_execution` (
  `TASK_EXECUTION_ID` bigint(20) NOT NULL,
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

/*Data for the table `task_execution` */

/*Table structure for table `task_execution_params` */

DROP TABLE IF EXISTS `task_execution_params`;

CREATE TABLE `task_execution_params` (
  `TASK_EXECUTION_ID` bigint(20) NOT NULL,
  `TASK_PARAM` varchar(2500) DEFAULT NULL,
  KEY `TASK_EXEC_PARAMS_FK` (`TASK_EXECUTION_ID`),
  CONSTRAINT `TASK_EXEC_PARAMS_FK` FOREIGN KEY (`TASK_EXECUTION_ID`) REFERENCES `task_execution` (`TASK_EXECUTION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `task_execution_params` */

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='任务表';

/*Data for the table `task_info` */

/*Table structure for table `task_seq` */

DROP TABLE IF EXISTS `task_seq`;

CREATE TABLE `task_seq` (
  `ID` bigint(20) NOT NULL,
  `UNIQUE_KEY` char(1) NOT NULL,
  UNIQUE KEY `UNIQUE_KEY_UN` (`UNIQUE_KEY`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `task_seq` */

insert  into `task_seq`(`ID`,`UNIQUE_KEY`) values
(0,'0');

/*Table structure for table `task_task_batch` */

DROP TABLE IF EXISTS `task_task_batch`;

CREATE TABLE `task_task_batch` (
  `TASK_EXECUTION_ID` bigint(20) NOT NULL,
  `JOB_EXECUTION_ID` bigint(20) NOT NULL,
  KEY `TASK_EXEC_BATCH_FK` (`TASK_EXECUTION_ID`),
  CONSTRAINT `TASK_EXEC_BATCH_FK` FOREIGN KEY (`TASK_EXECUTION_ID`) REFERENCES `task_execution` (`TASK_EXECUTION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `task_task_batch` */

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
) ENGINE=InnoDB AUTO_INCREMENT=559 DEFAULT CHARSET=utf8 COMMENT='交易表';

/*Data for the table `transaction_info` */

/*Table structure for table `uri_registry` */

DROP TABLE IF EXISTS `uri_registry`;

CREATE TABLE `uri_registry` (
  `NAME` varchar(255) NOT NULL,
  `URI` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `uri_registry` */

insert  into `uri_registry`(`NAME`,`URI`) values
('task.simpleJob','maven://jrx.tutorial:b01-simple-job:jar:1.0.0'),
('task.customerSimpleJob','file:///C:/Users/liwen/Desktop/jrx/worktask/b02-custom-tasklet-1.0.0.jar');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
