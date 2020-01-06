##TaskLifecycleListener  task生命周期类


#
public enum BatchStatus {
    COMPLETED,
    STARTING,
    STARTED,
    STOPPING,
    STOPPED,
    FAILED,
    ABANDONED,
    UNKNOWN;
    
    
 #创建消息表




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
) ENGINE=InnoDB DEFAULT CHARSET=utf8
   