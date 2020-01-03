package jrx.batch.dataflow.domain.mq;

import com.alibaba.fastjson.JSON;
import jrx.batch.dataflow.SpringbootDataflowServerApplication;
import jrx.batch.dataflow.domain.enums.ExecuteState;
import jrx.batch.dataflow.domain.pojo.BatchJobExecutionPojo;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SingleColumnRowMapper;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringbootDataflowServerApplication.class)
public class JobResultStatusMsgListenerTest {
    @Autowired
    @Qualifier("scheduleCenterJdbcTemplete")
    private JdbcTemplate scheduleCenterJdbcTemplete;

    @Test
    public void name() {
        List<Map<String, Object>> query = scheduleCenterJdbcTemplete.query("SELECT * FROM schedule_partition_execution LIMIT 100 ", new ColumnMapRowMapper());
        System.out.println(query.size());
    }

    @Test
    public void updatePartition() {
//        String jobstatus = "{\"dbInfo\":\"local\",\"endTime\":1577784084055,\"exitMessage\":\"\",\"jobExecutionId\":270,\"status\":\"FAIL\",\"taskExecutionId\":\"415\",\"taskParentExecutionId\":\"4134354221331673088\"}";
        String jobstatus = "{\"createTime\":1577949417135,\"dbInfo\":\"local\",\"endTime\":1577949423004,\"exitMessage\":\"\",\"jobExecutionId\":271,\"jobInstanceId\":270,\"status\":\"COMPLETED\",\"taskExecutionId\":\"416\",\"taskParentExecutionId\":\"1577949416141\"}";
        BatchJobExecutionPojo batchJobExecutionPojo = JSON.parseObject(jobstatus, BatchJobExecutionPojo.class);
//        String task_execution_id = batchJobExecutionPojo.getTaskParentExecutionId();
//        String task_execution_id = "4134354221331673088";
//        List<Integer> query = scheduleCenterJdbcTemplete.query("select count(1) from schedule_partition_execution where task_execution_id =?", new Object[]{task_execution_id}, new SingleColumnRowMapper<>(Integer.class));
        addJobRecord(batchJobExecutionPojo);
        List<Integer> query = scheduleCenterJdbcTemplete.query("select count(1) from schedule_partition_execution where task_execution_id =? ", new Object[]{batchJobExecutionPojo.getTaskParentExecutionId()}, new SingleColumnRowMapper<>(Integer.class));
        int partitionCount = query.get(0);
        if(partitionCount!=1){
            log.error("-----分区状态数据异常：分区任务  task_execution_id:{},分区数 partitionCount ：{}",batchJobExecutionPojo.getTaskParentExecutionId(),partitionCount);
            return;
        }
        if(StringUtils.isEmpty(batchJobExecutionPojo.getStatus())){
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

//        List<Map<String, Object>> query = scheduleCenterJdbcTemplete.query("SELECT * FROM schedule_partition_execution LIMIT 100 ", new ColumnMapRowMapper());
//        System.out.println(query.size());
    }

    private void addJobRecord(BatchJobExecutionPojo batchJobExecutionPojo) {
        scheduleCenterJdbcTemplete.update("INSERT INTO `batch_job_execution` (job_execution_id,job_instance_id,task_parent_execution_id,create_time,status,exit_message,task_execution_id,end_time,db_info) VALUES(?,?,?,?,?,?,?,?,?)", new Object[]{batchJobExecutionPojo.getJobExecutionId(),batchJobExecutionPojo.getJobInstanceId()
        ,batchJobExecutionPojo.getTaskParentExecutionId(),batchJobExecutionPojo.getCreateTime(),batchJobExecutionPojo.getStatus(),batchJobExecutionPojo.getExitMessage(),batchJobExecutionPojo.getTaskExecutionId(),batchJobExecutionPojo.getEndTime(),batchJobExecutionPojo.getDbInfo()});
    }
}