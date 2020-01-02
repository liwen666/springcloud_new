package jrx.batch.dataflow.domain.mq;

import com.alibaba.fastjson.JSON;
import jrx.batch.dataflow.SpringbootDataflowServerApplication;
import jrx.batch.dataflow.domain.enums.ExecuteState;
import jrx.batch.dataflow.domain.pojo.BatchJobExecutionPojo;
import lombok.extern.java.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SingleColumnRowMapper;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
@Log
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
        String jobstatus="{\"dbInfo\":\"local\",\"endTime\":1577784084055,\"exitMessage\":\"\",\"jobExecutionId\":270,\"status\":\"COMPLETED\",\"taskExecutionId\":\"415\",\"taskParentExecutionId\":\"4134354221331673088\"}";
        BatchJobExecutionPojo batchJobExecutionPojo = JSON.parseObject(jobstatus, BatchJobExecutionPojo.class);
//        String task_execution_id = batchJobExecutionPojo.getTaskParentExecutionId();
//        String task_execution_id = "4134354221331673088";
//        List<Integer> query = scheduleCenterJdbcTemplete.query("select count(1) from schedule_partition_execution where task_execution_id =?", new Object[]{task_execution_id}, new SingleColumnRowMapper<>(Integer.class));
        List<Integer> query = scheduleCenterJdbcTemplete.query("select count(1) from schedule_partition_execution where task_execution_id =? and run_state=?", new Object[]{batchJobExecutionPojo.getTaskParentExecutionId(),"COMPLETE"}, new SingleColumnRowMapper<>(Integer.class));
        scheduleCenterJdbcTemplete.update("update schedule_partition_execution set  run_state= ?,update_time=?",new Object[]{batchJobExecutionPojo.getStatus(), LocalDateTime.now()});

        if(batchJobExecutionPojo.getStatus().equals("FAILED")||batchJobExecutionPojo.getStatus().equals("UNKNOWN")){
//            partitionExecution.setRunState(ExecuteState.FAIL.name());
        }else if(batchJobExecutionPojo.getStatus().equals("STARTING")||batchJobExecutionPojo.getStatus().equals("STARTED")||batchJobExecutionPojo.getStatus().equals("STOPPING")){
//            partitionExecution.setRunState(ExecuteState.RUNNING.name());
        }else {
//            partitionExecution.setRunState(ExecuteState.COMPLETE.name());

        }

//        if(!ExecuteState.RUNNING.name().equals(partitionExecution.getRunState())){
//            partitionExecution.setUpdateTime(new Date());
//            schedulePartitionExecutionMapper.updateByPrimaryKey(partitionExecution);
//            logger.info("===更新分区状态：partitionExecution{}",JSON.toJSONString(partitionExecution));
//
//        }
//        List<Map<String, Object>> query = scheduleCenterJdbcTemplete.query("SELECT * FROM schedule_partition_execution LIMIT 100 ", new ColumnMapRowMapper());
//        System.out.println(query.size());
    }
}