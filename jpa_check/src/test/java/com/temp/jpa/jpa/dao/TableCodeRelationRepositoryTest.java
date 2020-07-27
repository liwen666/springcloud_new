package com.temp.jpa.jpa.dao;

import com.temp.jpa.ApplicationStart;
import com.temp.jpa.jpa.entity.TableCodeRelation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ApplicationStart.class)
public class TableCodeRelationRepositoryTest {
    @Autowired
    private TableCodeRelationRepository tableCodeRelationRepository;


    @Test
    public void name() {
        TableCodeRelation tableCodeRelation = new TableCodeRelation();
        tableCodeRelation.setPrimaryCodeKey("projectId");
        tableCodeRelation.setPrimaryTableChinaName("规则信息表");
        tableCodeRelation.setPrimaryTableName("res_rule_info");
        tableCodeRelation.setSlaveTableChinaName("项目表");
        tableCodeRelation.setPrimaryTableName("res_resource_project");
        tableCodeRelationRepository.save(tableCodeRelation);
    }


}