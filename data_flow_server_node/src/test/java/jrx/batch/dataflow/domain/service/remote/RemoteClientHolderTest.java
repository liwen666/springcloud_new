package jrx.batch.dataflow.domain.service.remote;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import jrx.batch.dataflow.SpringbootDataflowServerApplication;
import jrx.batch.dataflow.domain.config.batch.JrxBatchProperties;
import jrx.batch.dataflow.domain.enums.CodeEnums;
import jrx.batch.dataflow.domain.service.IHttpBatchService;
import jrx.batch.dataflow.domain.service.ITaskExecutionService;
import jrx.batch.dataflow.domain.service.ITaskTaskBatchService;
import jrx.batch.dataflow.infrastructure.model.TaskExecution;
import jrx.batch.dataflow.util.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.BatchStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("local")
@SpringBootTest(classes = SpringbootDataflowServerApplication.class)
public class RemoteClientHolderTest {
    @Autowired
    private RemoteClientHolder remoteClientHolder;
    @Autowired
    private IHttpBatchService httpBatchService;
    @Autowired
    private ITaskExecutionService taskExecutionService;
    @Autowired
    private ITaskTaskBatchService taskTaskBatchService;


    @Test
    public void testJob() {
        try {
            for (int i = 0; i < 100; i++) {
                testRemoteJob();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testRemoteJob() {
        String parentId = System.currentTimeMillis() + "";
        long taskExecutionId = httpBatchService.createTaskExecution("test_task_define", parentId);

        JobExecuteClient job_server_test = remoteClientHolder.getClient("http_task", JobExecuteClient.class);
        JsonResult result = null;
        try {
            result = job_server_test.executeRemoteJob(new ArrayList<String>() {{
                //            add("task.jobName=simpleJob");
                add("--task.jobName=customTaskletJob");
                add("--spring.cloud.task.executionid=" + taskExecutionId);
                //            add("--spring.cloud.task.executionid=121" );
                add("--jrx.batch.job.server.batch_datasource=" + JrxBatchProperties.properties.get("JOB_SERVER_DB"));
                //            add("--spring.cloud.task.executionid=40");
                //            add("job.runDate=" + System.currentTimeMillis());
                add("job.runDate=200101");
//                add("--server.port=11111");
                add("job.ptime=200");
            }});
        } catch (Exception e) {
            e.printStackTrace();
            checkTask(taskExecutionId, parentId);
        }


        //失败做一下查询确认，更新分区
        if (null == result || result.getCode() == CodeEnums.EROOR.code()) {
            log.error("===job执行失败，errorMsg:{},准备做失败数据检查。",result.getMessage());
            checkTask(taskExecutionId, parentId);
        }

        //成功，更新分区
        System.out.println(JSON.toJSONString(result));
        System.out.println("******************************************************");


/**
 *更新分区状态
 */


    }

    private void checkTask(long taskExecutionId, String parentId) {
        List<Map> maps = taskTaskBatchService.listJobById(parentId);
        if (CollectionUtils.isEmpty(maps)) {
            log.error("未查询到任务对应的job信息：parentId:{}", parentId);
        }
        Map map = maps.get(0);
        String status = (String) map.get("STATUS");
        if (BatchStatus.COMPLETED.name().equals(status)) {
            log.info("任务对应的job已经完成，taskExecutionId:{} parentId:{},jobId:{}", taskExecutionId, parentId, map.get("JOB_EXECUTION_ID"));
            TaskExecution task = TaskExecution.builder().
                    endTime(LocalDateTime.now())
                    .exitCode(0)
                    .errorMessage(null)
                    .build();
            taskExecutionService.update(task, Wrappers.<TaskExecution>lambdaUpdate().eq(TaskExecution::getTaskExecutionId, taskExecutionId));
        }
    }

    @Test
    public void testInterface() {

        JobExecuteClient job_server_test = remoteClientHolder.testClient("http://localhost:20000", JobExecuteClient.class);
        try {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    job_server_test.executeRemoteJob(new ArrayList<String>() {{
                        //            add("task.jobName=simpleJob");
                        add("task.jobName=simpleJob");
                        //            add("--spring.cloud.task.executionid=121" );
                        add("--jrx.batch.job.server.batch_datasource=" + JrxBatchProperties.properties.get("JOB_SERVER_DB"));
                        //            add("--spring.cloud.task.executionid=40");
                        //            add("job.runDate=" + System.currentTimeMillis());
                        add("job.runDate=200101");
                        add("job.ptime=200");
                    }});
                }
            }).start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}