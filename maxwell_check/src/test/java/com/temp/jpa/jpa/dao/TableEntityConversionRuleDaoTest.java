package com.temp.jpa.jpa.dao;

import com.alibaba.fastjson.JSON;
import com.temp.jpa.ApplicationStart;
import com.temp.jpa.jpa.entity.TableEntityConversionRule;
import com.temp.jpa.jpa.enums.DataConversionModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TableApplicationStart.class)
@WebAppConfiguration
public class TableEntityConversionRuleDaoTest {
    @Autowired
    private TableEntityConversionRuleDao tableEntityConversionRuleDao;

    @Test
    public void name() {
        System.out.println(tableEntityConversionRuleDao.findById(1));
        System.out.println(JSON.toJSONString(tableEntityConversionRuleDao.findByTableCode("meta_object_field")));
    }



    @Test
    public void insertField() {
        TableEntityConversionRule tableEntityConversionRule = new TableEntityConversionRule();
        tableEntityConversionRule.setDataConversionModel(DataConversionModel.FIELD);
        tableEntityConversionRule.setTableCode("meta_object_field");
        tableEntityConversionRule.setTableName("字段表");
        tableEntityConversionRule.setEntityKey("contentCode");
        tableEntityConversionRule.setDataConversionModel(DataConversionModel.ORG);
        TableEntityConversionRule byEntityKeyAndTableCode = tableEntityConversionRuleDao.findByEntityKeyAndTableCode(tableEntityConversionRule.getEntityKey(), tableEntityConversionRule.getTableCode());
        if(null==byEntityKeyAndTableCode){
            tableEntityConversionRuleDao.save(tableEntityConversionRule);
        }

        tableEntityConversionRule.setEntityKey("deriveContent.statisticFieldBid");
        tableEntityConversionRule.setDataConversionModel(DataConversionModel.FIELD_INDEX);
        byEntityKeyAndTableCode = tableEntityConversionRuleDao.findByEntityKeyAndTableCode(tableEntityConversionRule.getEntityKey(), tableEntityConversionRule.getTableCode());
        if(null==byEntityKeyAndTableCode){
            tableEntityConversionRuleDao.save(tableEntityConversionRule);
        }

        tableEntityConversionRule.setEntityKey("referFieldIds");
        tableEntityConversionRule.setDataConversionModel(DataConversionModel.FIELD);
        byEntityKeyAndTableCode = tableEntityConversionRuleDao.findByEntityKeyAndTableCode(tableEntityConversionRule.getEntityKey(), tableEntityConversionRule.getTableCode());
        if(null==byEntityKeyAndTableCode){
            tableEntityConversionRuleDao.save(tableEntityConversionRule);
        }

        tableEntityConversionRule.setEntityKey("deriveContent.originField.bid");
        tableEntityConversionRule.setDataConversionModel(DataConversionModel.FIELD_INDEX);
        byEntityKeyAndTableCode = tableEntityConversionRuleDao.findByEntityKeyAndTableCode(tableEntityConversionRule.getEntityKey(), tableEntityConversionRule.getTableCode());
        if(null==byEntityKeyAndTableCode){
            tableEntityConversionRuleDao.save(tableEntityConversionRule);
        }

        tableEntityConversionRule.setEntityKey("deriveContent.originField.fieldId");
        tableEntityConversionRule.setDataConversionModel(DataConversionModel.FIELD);
        byEntityKeyAndTableCode = tableEntityConversionRuleDao.findByEntityKeyAndTableCode(tableEntityConversionRule.getEntityKey(), tableEntityConversionRule.getTableCode());
        if(null==byEntityKeyAndTableCode){
            tableEntityConversionRuleDao.save(tableEntityConversionRule);
        }



    }

    @Test
    public void insert() {
//        TableEntityConversionRule tableEntityConversionRule= new TableEntityConversionRule();
//        tableEntityConversionRule.setDataConversionModel(DataConversionModel.FIELD);
//        tableEntityConversionRule.setTableCode("meta_object_field");
//        tableEntityConversionRule.setTableName("字段表");
//        tableEntityConversionRule.setTableCode("meta_model_object_info");
//        tableEntityConversionRule.setTableName("对象表");

        TableEntityConversionRule tableEntityConversionRule = new TableEntityConversionRule();
        tableEntityConversionRule.setTableCode("org_rule");
        tableEntityConversionRule.setTableName("机构字典");
        tableEntityConversionRule.setEntityKey("15029");
        tableEntityConversionRule.setEntityValue("金融云");
//                tableEntityConversionRule.setEntityKey("149");
//                tableEntityConversionRule.setEntityValue("将融信机构");
        tableEntityConversionRule.setDataConversionModel(DataConversionModel.ORG);

        tableEntityConversionRuleDao.save(tableEntityConversionRule);
    }


    @Test
    public void list() {
        List<TableEntityConversionRule> allRule = tableEntityConversionRuleDao.findAllRule();
        Map<String, List<TableEntityConversionRule>> collect = allRule.stream().collect(Collectors.groupingBy(TableEntityConversionRule::getTableCode));


    }
}


