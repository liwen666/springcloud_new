package jrx.batch.dataflow.infrastructure.dao;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import jrx.batch.dataflow.SpringbootDataflowServerApplication;
import jrx.batch.dataflow.infrastructure.model.TaskExecution;
import lombok.extern.java.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

@Log
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringbootDataflowServerApplication.class)
public class TaskTaskBatchMapperTest {
    @Autowired
    private TaskTaskBatchMapper taskTaskBatchMapper;

    @Autowired
    private TaskExecutionMapper taskExecutionMapper;

    @Test
    public void name() {
        List<Map> maps = taskTaskBatchMapper.listTaskExecutionById("4119170606029377536");
        System.out.println(maps);
    }

    @Test
    public void getExecute() {
        TaskExecution one = taskExecutionMapper.selectOne(Wrappers.<TaskExecution>lambdaQuery().eq(TaskExecution::getParentExecutionId, "4119170606029377536"));

        System.out.println(one);

    }
}