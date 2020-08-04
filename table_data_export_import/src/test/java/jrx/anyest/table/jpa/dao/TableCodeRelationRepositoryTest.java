package jrx.anyest.table.jpa.dao;

import jrx.anyest.table.ApplicationStart;
import jrx.anyest.table.config.TablePropertiesConfig;
import jrx.anyest.table.jpa.entity.TableCodeRelation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ApplicationStart.class)
public class TableCodeRelationRepositoryTest {
    @Autowired
    private TableCodeRelationRepository tableCodeRelationRepository;

    @Autowired
    private TablePropertiesConfig tablePropertiesConfig;

    @Test
    public void cond() {
        String datasource = tablePropertiesConfig.getDatasource();
        System.out.println(datasource);

    }

    @Test
    public void name() {

        /**
         * 规则
         */
        TableCodeRelation tableCodeRelation = new TableCodeRelation();
        tableCodeRelation.setPrimaryCodeKey("category_id");
        tableCodeRelation.setPrimaryTableChinaName("规则信息表");
        tableCodeRelation.setPrimaryTableName("res_rule_info");
        tableCodeRelation.setSlaveTableChinaName("分类表");
        tableCodeRelation.setSlaveTableName("meta_category");
        tableCodeRelation.setSlaveCodeKey("category_id");
        tableCodeRelation.setFilterHandleBean("defaultTableDataHandler");
        TableCodeRelation byPrimaryCodeKeyAndPrimaryTableNameAAndSlaveTableName = tableCodeRelationRepository.findByPrimaryCodeKeyAndPrimaryTableNameAndSlaveTableNameAndSlaveCodeKey(tableCodeRelation.getPrimaryCodeKey(), tableCodeRelation.getPrimaryTableName(), tableCodeRelation.getSlaveTableName(), tableCodeRelation.getSlaveCodeKey());
        if (null == byPrimaryCodeKeyAndPrimaryTableNameAAndSlaveTableName) {
            tableCodeRelationRepository.save(tableCodeRelation);
        }

        tableCodeRelation.setId(null);
        tableCodeRelation.setPrimaryCodeKey("resource_id");
        tableCodeRelation.setPrimaryTableChinaName("规则信息表");
        tableCodeRelation.setPrimaryTableName("res_rule_info");
        tableCodeRelation.setSlaveTableChinaName("规则版本表");
        tableCodeRelation.setSlaveTableName("res_rule");
        tableCodeRelation.setSlaveCodeKey("resource_id");
        tableCodeRelation.setFilterHandleBean("defaultTableDataHandler");
        byPrimaryCodeKeyAndPrimaryTableNameAAndSlaveTableName = tableCodeRelationRepository.findByPrimaryCodeKeyAndPrimaryTableNameAndSlaveTableNameAndSlaveCodeKey(tableCodeRelation.getPrimaryCodeKey(), tableCodeRelation.getPrimaryTableName(), tableCodeRelation.getSlaveTableName(), tableCodeRelation.getSlaveCodeKey());
        if (null == byPrimaryCodeKeyAndPrimaryTableNameAAndSlaveTableName) {
            tableCodeRelationRepository.save(tableCodeRelation);
        }


    }


}