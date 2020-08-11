package jrx.anyest.table.jpa.dao;

import com.google.common.collect.Maps;
import jrx.anyest.table.ApplicationStart;
import jrx.anyest.table.constant.TableConstants;
import jrx.anyest.table.jpa.entity.TableCodeConfig;
import jrx.anyest.table.jpa.entity.TableParamConfig;
import jrx.anyest.table.service.TableDataCodeCacheManager;
import jrx.anyest.table.service.TableDataHandler;
import jrx.anyest.table.utils.TableSpringUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ApplicationStart.class)
public class TableParamConfigRepositoryTest {
    @Autowired
    private TableParamConfigRepository tableParamConfigRepository;



    @Test
    public void insert() {
        TableParamConfig tableParamConfig = new TableParamConfig();
        tableParamConfig.setKeyColumn("next_val");
        tableParamConfig.setTableCodeName(TableConstants.KEY_SEQUENCE);
        tableParamConfig.setTableDescribe("hibernate_sequence");
        tableParamConfig.setResourceIdColumn(null);
        tableParamConfig.setVersionColumn(null);
        TableParamConfig tableParamConfigRepositoryByTableCodeName = tableParamConfigRepository.findByTableCodeName(tableParamConfig.getTableCodeName());
        if (null != tableParamConfigRepositoryByTableCodeName) {
            tableParamConfig.setId(tableParamConfigRepositoryByTableCodeName.getId());
        }
        tableParamConfigRepository.save(tableParamConfig);

        tableParamConfig.setId(null);
        tableParamConfig.setTableCodeName("res_rule");
        tableParamConfig.setKeyColumn(null);
        tableParamConfig.setTableDescribe("");
        tableParamConfig.setResourceIdColumn("resource_id");
        tableParamConfig.setVersionColumn("version");
         tableParamConfigRepositoryByTableCodeName = tableParamConfigRepository.findByTableCodeName(tableParamConfig.getTableCodeName());
        if (null != tableParamConfigRepositoryByTableCodeName) {
            tableParamConfig.setId(tableParamConfigRepositoryByTableCodeName.getId());
        }
        tableParamConfigRepository.save(tableParamConfig);

        tableParamConfig.setId(null);
        tableParamConfig.setTableCodeName("res_rule_set");
        tableParamConfig.setKeyColumn(null);
        tableParamConfig.setTableDescribe("");
        tableParamConfig.setResourceIdColumn("resource_id");
        tableParamConfig.setVersionColumn("version");
        tableParamConfigRepositoryByTableCodeName = tableParamConfigRepository.findByTableCodeName(tableParamConfig.getTableCodeName());
        if (null != tableParamConfigRepositoryByTableCodeName) {
            tableParamConfig.setId(tableParamConfigRepositoryByTableCodeName.getId());
        }
        tableParamConfigRepository.save(tableParamConfig);

        tableParamConfig.setId(null);
        tableParamConfig.setTableCodeName("res_rule_tree");
        tableParamConfig.setKeyColumn(null);
        tableParamConfig.setTableDescribe("");
        tableParamConfig.setResourceIdColumn("resource_id");
        tableParamConfig.setVersionColumn("version");
        tableParamConfigRepositoryByTableCodeName = tableParamConfigRepository.findByTableCodeName(tableParamConfig.getTableCodeName());
        if (null != tableParamConfigRepositoryByTableCodeName) {
            tableParamConfig.setId(tableParamConfigRepositoryByTableCodeName.getId());
        }
        tableParamConfigRepository.save(tableParamConfig);


        tableParamConfig.setId(null);
        tableParamConfig.setTableCodeName("res_score_card");
        tableParamConfig.setKeyColumn(null);
        tableParamConfig.setTableDescribe("");
        tableParamConfig.setResourceIdColumn("resource_id");
        tableParamConfig.setVersionColumn("version");
        tableParamConfigRepositoryByTableCodeName = tableParamConfigRepository.findByTableCodeName(tableParamConfig.getTableCodeName());
        if (null != tableParamConfigRepositoryByTableCodeName) {
            tableParamConfig.setId(tableParamConfigRepositoryByTableCodeName.getId());
        }
        tableParamConfigRepository.save(tableParamConfig);

        tableParamConfig.setId(null);
        tableParamConfig.setTableCodeName("res_script");
        tableParamConfig.setKeyColumn(null);
        tableParamConfig.setTableDescribe("");
        tableParamConfig.setResourceIdColumn("resource_id");
        tableParamConfig.setVersionColumn("version");
        tableParamConfigRepositoryByTableCodeName = tableParamConfigRepository.findByTableCodeName(tableParamConfig.getTableCodeName());
        if (null != tableParamConfigRepositoryByTableCodeName) {
            tableParamConfig.setId(tableParamConfigRepositoryByTableCodeName.getId());
        }
        tableParamConfigRepository.save(tableParamConfig);

        tableParamConfig.setId(null);
        tableParamConfig.setTableCodeName("res_strategy");
        tableParamConfig.setKeyColumn(null);
        tableParamConfig.setTableDescribe("");
        tableParamConfig.setResourceIdColumn("resource_id");
        tableParamConfig.setVersionColumn("version");
        tableParamConfigRepositoryByTableCodeName = tableParamConfigRepository.findByTableCodeName(tableParamConfig.getTableCodeName());
        if (null != tableParamConfigRepositoryByTableCodeName) {
            tableParamConfig.setId(tableParamConfigRepositoryByTableCodeName.getId());
        }
        tableParamConfigRepository.save(tableParamConfig);

        tableParamConfig.setId(null);
        tableParamConfig.setTableCodeName("res_matrix");
        tableParamConfig.setKeyColumn(null);
        tableParamConfig.setTableDescribe("");
        tableParamConfig.setResourceIdColumn("resource_id");
        tableParamConfig.setVersionColumn("version");
        tableParamConfigRepositoryByTableCodeName = tableParamConfigRepository.findByTableCodeName(tableParamConfig.getTableCodeName());
        if (null != tableParamConfigRepositoryByTableCodeName) {
            tableParamConfig.setId(tableParamConfigRepositoryByTableCodeName.getId());
        }


        tableParamConfigRepository.save(tableParamConfig);
        tableParamConfig.setId(null);
        tableParamConfig.setTableCodeName("meta_model_object");
        tableParamConfig.setKeyColumn(null);
        tableParamConfig.setTableDescribe("");
        tableParamConfig.setResourceIdColumn("resource_id");
        tableParamConfig.setVersionColumn("version");
        tableParamConfigRepositoryByTableCodeName = tableParamConfigRepository.findByTableCodeName(tableParamConfig.getTableCodeName());
        if (null != tableParamConfigRepositoryByTableCodeName) {
            tableParamConfig.setId(tableParamConfigRepositoryByTableCodeName.getId());
        }
        tableParamConfigRepository.save(tableParamConfig);

        tableParamConfig.setId(null);
        tableParamConfig.setTableCodeName("meta_topic_object");
        tableParamConfig.setKeyColumn(null);
        tableParamConfig.setTableDescribe("");
        tableParamConfig.setResourceIdColumn("resource_id");
        tableParamConfig.setVersionColumn("version");
        tableParamConfigRepositoryByTableCodeName = tableParamConfigRepository.findByTableCodeName(tableParamConfig.getTableCodeName());
        if (null != tableParamConfigRepositoryByTableCodeName) {
            tableParamConfig.setId(tableParamConfigRepositoryByTableCodeName.getId());
        }
        tableParamConfigRepository.save(tableParamConfig);

        tableParamConfig.setId(null);
        tableParamConfig.setTableCodeName("meta_data_object");
        tableParamConfig.setKeyColumn(null);
        tableParamConfig.setTableDescribe("");
        tableParamConfig.setResourceIdColumn("resource_id");
        tableParamConfig.setVersionColumn("version");
        tableParamConfigRepositoryByTableCodeName = tableParamConfigRepository.findByTableCodeName(tableParamConfig.getTableCodeName());
        if (null != tableParamConfigRepositoryByTableCodeName) {
            tableParamConfig.setId(tableParamConfigRepositoryByTableCodeName.getId());
        }
        tableParamConfigRepository.save(tableParamConfig);



        TableDataHandler defaultTableDataHandler = TableSpringUtil.getBean("defaultTableDataHandler", TableDataHandler.class);
    }

}