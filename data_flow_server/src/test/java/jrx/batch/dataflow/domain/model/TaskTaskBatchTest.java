package jrx.batch.dataflow.domain.model;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import jrx.batch.dataflow.SpringbootDataflowServerApplication;
import jrx.batch.dataflow.infrastructure.dao.TaskTaskBatchMapper;
import jrx.batch.dataflow.infrastructure.model.TaskTaskBatch;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringbootDataflowServerApplication.class)
public class TaskTaskBatchTest {
    @Autowired(required = false)
    private TaskTaskBatchMapper taskTaskBatchMapper;

    @Test
    public void name() {
        TaskTaskBatch taskTaskBatch = taskTaskBatchMapper.selectOne(Wrappers.<TaskTaskBatch>lambdaQuery().eq(TaskTaskBatch::getJobExecutionId, 1));
        List<TaskTaskBatch> taskTaskBatches = taskTaskBatchMapper.selectList(Wrappers.<TaskTaskBatch>lambdaQuery().apply("TASK_EXECUTION_ID =2"));
        System.out.println(JSON.toJSONString(taskTaskBatch));
    }


}