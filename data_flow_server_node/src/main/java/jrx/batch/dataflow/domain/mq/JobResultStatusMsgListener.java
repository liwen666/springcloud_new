package jrx.batch.dataflow.domain.mq;

import com.alibaba.fastjson.JSON;
import jrx.batch.dataflow.domain.enums.ExecuteState;
import jrx.batch.dataflow.domain.pojo.BatchJobExecutionPojo;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SingleColumnRowMapper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RocketMQMessageListener(
        topic = "${jrx.node.rocketmq.topic}",
        consumerGroup = "${jrx.node.rocketmq.conumer-group}",
        accessKey = "${jrx.node.rocketmq.access-key}",
        secretKey = "${jrx.node.rocketmq.secret-key}"

)
public class JobResultStatusMsgListener implements RocketMQListener<String> {
    @Autowired
    @Qualifier("scheduleCenterJdbcTemplete")
    private JdbcTemplate scheduleCenterJdbcTemplete;


    @Override
    public void onMessage(String jobResultMsg) {
        log.info("---------接收到job状态更新消息 -->jobResultMsg：{}", jobResultMsg);
        try {
            BatchJobExecutionPojo batchJobExecutionPojo = JSON.parseObject(jobResultMsg, BatchJobExecutionPojo.class);
            if (StringUtils.isEmpty(batchJobExecutionPojo.getTaskParentExecutionId())) {
                log.error("--------接收到的jobResultMsg消息异常，缺少taskParentExecutionId 参数值");
                return;
            }
            addJobRecord(batchJobExecutionPojo);
            List<Integer> query = scheduleCenterJdbcTemplete.query("select count(1) from schedule_partition_execution where task_execution_id =? ", new Object[]{batchJobExecutionPojo.getTaskParentExecutionId()}, new SingleColumnRowMapper<>(Integer.class));
            int partitionCount = query.get(0);
            if (partitionCount != 1) {
                log.error("-----分区状态数据异常：分区任务  task_execution_id:{},分区数 partitionCount ：{}", batchJobExecutionPojo.getTaskParentExecutionId(), partitionCount);
                return;
            }
            if (StringUtils.isEmpty(batchJobExecutionPojo.getStatus())) {
                log.error("-----分区状态数据异常：job状态不能为空。");
                return;
            }
            if (batchJobExecutionPojo.getStatus().equals("FAILED") || batchJobExecutionPojo.getStatus().equals("UNKNOWN")) {
                scheduleCenterJdbcTemplete.update("update schedule_partition_execution set  run_state= ?,update_time=? where task_execution_id=?", new Object[]{ExecuteState.FAIL.name(), LocalDateTime.now(), batchJobExecutionPojo.getTaskParentExecutionId()});
            } else if (batchJobExecutionPojo.getStatus().equals("STARTING") || batchJobExecutionPojo.getStatus().equals("STARTED") || batchJobExecutionPojo.getStatus().equals("STOPPING")) {
                scheduleCenterJdbcTemplete.update("update schedule_partition_execution set  run_state= ?,update_time=? where task_execution_id=?", new Object[]{ExecuteState.RUNNING.name(), LocalDateTime.now(), batchJobExecutionPojo.getTaskParentExecutionId()});
            } else {
                scheduleCenterJdbcTemplete.update("update schedule_partition_execution set  run_state= ?,update_time=? where task_execution_id=?", new Object[]{ExecuteState.COMPLETE.name(), LocalDateTime.now(), batchJobExecutionPojo.getTaskParentExecutionId()});
            }
            log.info("------------分区状态更新成功：task_execution_id：{},run_state:{}",batchJobExecutionPojo.getTaskParentExecutionId() ,batchJobExecutionPojo.getStatus());
        } catch (Exception e) {
            log.error("--------分区状态更新异常：-->jobResultMsg:{} ", jobResultMsg);
        }

    }

    private void addJobRecord(BatchJobExecutionPojo batchJobExecutionPojo) {
        scheduleCenterJdbcTemplete.update("INSERT INTO `batch_job_execution_mq` (job_execution_id,job_instance_id,task_parent_execution_id,create_time,status,exit_message,task_execution_id,end_time,db_info,server_name) VALUES(?,?,?,?,?,?,?,?,?,?)", new Object[]{batchJobExecutionPojo.getJobExecutionId(), batchJobExecutionPojo.getJobInstanceId()
                , batchJobExecutionPojo.getTaskParentExecutionId(), batchJobExecutionPojo.getCreateTime(), batchJobExecutionPojo.getStatus(), batchJobExecutionPojo.getExitMessage(), batchJobExecutionPojo.getTaskExecutionId(), batchJobExecutionPojo.getEndTime(), batchJobExecutionPojo.getDbInfo(),batchJobExecutionPojo.getServerName()});
    }
}
