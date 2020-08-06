package jrx.anyest.table.jpa.dao;

import jrx.anyest.table.ApplicationStart;
import jrx.anyest.table.jpa.entity.TableConversionKey;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ApplicationStart.class)
public class TableConversionKeyRepositoryTest
{
    @Autowired
    private TableConversionKeyRepository tableConversionKeyRepository;

    @Test
    public void insert() {
        TableConversionKey tableConversionKey = new TableConversionKey();
        tableConversionKey.setCreateTime(new Date());
        tableConversionKey.setTableCodeName("res_rule_info");
        tableConversionKey.setTableCodeChinaName("规则信息表");
        tableConversionKey.setConversionKey("category_id");
        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
        TableConversionKey tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
        }
        tableConversionKeyRepository.save(tableConversionKey);

        tableConversionKey.setId(null);
        tableConversionKey.setTableCodeName("res_rule");
        tableConversionKey.setTableCodeChinaName("规则信息表");
        tableConversionKey.setConversionKey("resource_id");
        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
        tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
        }
        tableConversionKeyRepository.save(tableConversionKey);

    }




}