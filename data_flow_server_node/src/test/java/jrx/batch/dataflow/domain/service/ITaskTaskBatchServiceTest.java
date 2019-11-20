package jrx.batch.dataflow.domain.service;

import jrx.batch.dataflow.SpringbootDataflowServerApplication;
import jrx.batch.dataflow.infrastructure.model.TaskTaskBatch;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringbootDataflowServerApplication.class)
public class ITaskTaskBatchServiceTest {
    @Autowired
    private ITaskTaskBatchService taskTaskBatchService;

    @Test
    public void name() {
        List<TaskTaskBatch> list = taskTaskBatchService.list();
        System.out.println(list.size());
    }

    @Test
    public void listJobById() {
        List<Map> list = taskTaskBatchService.listJobById("4118405925211795456");
        System.out.println(list.size());
    }

    @Test
    public void listJobByIds() {
        List<String> parentIds = new ArrayList<>();
        parentIds.add("4118405925211795456");
        List<Map> list = taskTaskBatchService.listJobByIds(parentIds);
        System.out.println(list.size());
    }
}