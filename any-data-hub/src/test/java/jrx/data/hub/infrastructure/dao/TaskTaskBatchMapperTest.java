//package jrx.data.hub.infrastructure.dao;
//
//import com.baomidou.mybatisplus.core.toolkit.Wrappers;
//import jrx.data.hub.SpringbootDataflowServerApplication;
//import jrx.data.hub.infrastructure.model.TaskExecution;
//import lombok.extern.java.Log;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.Map;
//
//@Log
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest(classes = SpringbootDataflowServerApplication.class)
//public class TaskTaskBatchMapperTest {
//    @Autowired
//    private TaskTaskBatchMapper taskTaskBatchMapper;
//
//    @Autowired
//    private TaskExecutionMapper taskExecutionMapper;
//
//    @Test
//    public void name() {
//        List<Map> maps = taskTaskBatchMapper.listTaskExecutionById("4119170606029377536");
//        System.out.println(maps);
//    }
//
//    @Test
//    public void getExecute() {
//        TaskExecution one = taskExecutionMapper.selectOne(Wrappers.<TaskExecution>lambdaQuery().eq(TaskExecution::getParentExecutionId, "4119170606029377536"));
////        List<Map<String, Object>> query = scheduleCenterJdbcTemplete.query("SELECT * FROM schedule_partition_execution LIMIT 100 ", new ColumnMapRowMapper());
//
//
//        System.out.println(one);
//
//    }
//
//    @Test
//    public void update() {
//        TaskExecution build = TaskExecution.builder().taskExecutionId(103l).endTime(LocalDateTime.now()).build();
//        int update = taskExecutionMapper.update(build, Wrappers.<TaskExecution>lambdaUpdate().eq(TaskExecution::getTaskExecutionId,build.getTaskExecutionId()));
//
//        System.out.println(update);
//
//    }
//}