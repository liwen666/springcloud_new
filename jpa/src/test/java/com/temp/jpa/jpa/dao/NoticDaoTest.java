package com.temp.jpa.jpa.dao;

import com.temp.jpa.ApplicationStart;
import com.temp.jpa.jpa.entity.Notic;
import com.temp.jpa.jpa.entity.TaskDeployment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ApplicationStart.class)
@WebAppConfiguration
public class NoticDaoTest {
    @Autowired
    private TaskDep noticDao;

    @Test
    public void insert() {
        noticDao .save(TaskDeployment.builder()
                .taskDeploymentId("000000922ddss99")
                .taskDefinitionName("dataflow")
                .platformName("kjkj").build());

    }
}