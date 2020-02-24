package com.temp.jpa.jpa.dao;

import com.temp.jpa.ApplicationStart;
import com.temp.jpa.jpa.entity.DataObjectEntity;
import com.temp.jpa.jpa.entity.TaskDeployment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ApplicationStart.class)
@WebAppConfiguration
public class TestJpa {
    @Autowired
    private JpaTest jpaTest;

    @Test
    public void insert() {
        DataObjectEntity dataObjectEntity = new DataObjectEntity();
        dataObjectEntity.setFieldIds("333");
        jpaTest .save(dataObjectEntity);

    }
}