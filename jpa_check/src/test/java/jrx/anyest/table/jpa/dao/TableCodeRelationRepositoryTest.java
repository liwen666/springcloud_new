package jrx.anyest.table.jpa.dao;

import jrx.anyest.table.ApplicationStart;
import jrx.anyest.table.jpa.entity.TableCodeRelation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TableApplicationStart.class)
public class TableCodeRelationRepositoryTest {
    @Autowired
    private TableCodeRelationRepository tableCodeRelationRepository;


    @Test
    public void name() {
        TableCodeRelation tableCodeRelation = new TableCodeRelation();
        tableCodeRelation.setPrimaryCodeKey("category_id");
        tableCodeRelation.setPrimaryTableChinaName("规则信息表");
        tableCodeRelation.setPrimaryTableName("res_rule_info");
        tableCodeRelation.setSlaveTableChinaName("分类表");
        tableCodeRelation.setSlaveTableName("meta_category");
        TableCodeRelation byPrimaryCodeKeyAndPrimaryTableNameAAndSlaveTableName = tableCodeRelationRepository.findByPrimaryCodeKeyAndPrimaryTableNameAndSlaveTableName(tableCodeRelation.getPrimaryCodeKey(), tableCodeRelation.getPrimaryTableName(), tableCodeRelation.getSlaveTableName());
        if(null ==byPrimaryCodeKeyAndPrimaryTableNameAAndSlaveTableName){
            tableCodeRelationRepository.save(tableCodeRelation);
        }
        tableCodeRelation.setId(null);
        tableCodeRelation.setPrimaryCodeKey("category_id");
        tableCodeRelation.setPrimaryTableChinaName("规则信息表");
        tableCodeRelation.setPrimaryTableName("res_rule_info");
        tableCodeRelation.setSlaveTableChinaName("分类表");
        tableCodeRelation.setSlaveTableName("meta_category");
         byPrimaryCodeKeyAndPrimaryTableNameAAndSlaveTableName = tableCodeRelationRepository.findByPrimaryCodeKeyAndPrimaryTableNameAndSlaveTableName(tableCodeRelation.getPrimaryCodeKey(), tableCodeRelation.getPrimaryTableName(), tableCodeRelation.getSlaveTableName());
        if(null ==byPrimaryCodeKeyAndPrimaryTableNameAAndSlaveTableName){
            tableCodeRelationRepository.save(tableCodeRelation);
        }



    }


}