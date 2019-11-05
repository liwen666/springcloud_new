/*
*********************************************************************
  针对develop_dataflow_plugins 批次调度升级脚本
*/
use batch_schedule_master;

ALTER  TABLE `schedule_plan_execution` ADD  rerun_error_task_keys text COMMENT "异常重跑taskkeys";
ALTER  TABLE `schedule_plan_execution` ADD  status VARCHAR(2) COMMENT "计划状态";
ALTER  TABLE `schedule_plan_execution` ADD  before_close_state VARCHAR(2) COMMENT "执行记录关闭前的状态(用于还原使用)";
ALTER  TABLE `schedule_partition_execution` ADD  job_id  bigint(20)  NULL default -1 COMMENT "jobid";
ALTER  TABLE `schedule_plan` ADD  status VARCHAR(2) COMMENT "启用状态";
ALTER  TABLE `schedule_plan` ADD  relate_plan_id VARCHAR(2) COMMENT "";

--
-- CREATE TABLE  IF NOT EXISTS  `schedule_plan_execution_task` (
--   `plan_exeuction_task_id` BIGINT(20) NOT NULL AUTO_INCREMENT,
--   `schedule_execution_id` BIGINT(32) DEFAULT NULL COMMENT '',
--   `task_key` CHAR(20) DEFAULT NULL COMMENT '任务key',
--   `run_state` VARCHAR(20) DEFAULT NULL COMMENT '运行状态',
--   PRIMARY KEY (`plan_exeuction_task_id`)
-- ) ENGINE=INNODB AUTO_INCREMENT=94 DEFAULT CHARSET=utf8 COMMENT='计划执行任务表';

DROP TABLE IF EXISTS `schedule_plan_execution_task`;
CREATE TABLE   `schedule_plan_execution_task` (
  `plan_exeuction_task_id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `schedule_execution_id` BIGINT(32) DEFAULT NULL COMMENT '计划执行id',
  `task_key` CHAR(20) DEFAULT NULL COMMENT '任务key',
  `run_state` VARCHAR(20) DEFAULT NULL COMMENT '运行状态',
  PRIMARY KEY (`plan_exeuction_task_id`)
) ENGINE=INNODB AUTO_INCREMENT=94 DEFAULT CHARSET=utf8 COMMENT='计划执行任务表';


