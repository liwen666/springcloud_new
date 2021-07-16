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




/**
执行节点表添加索引
 */

DROP INDEX IDX_PARENT_EXECUTION_ID ON `task_execution`;
ALTER TABLE task_execution ADD INDEX IDX_PARENT_EXECUTION_ID (PARENT_EXECUTION_ID);