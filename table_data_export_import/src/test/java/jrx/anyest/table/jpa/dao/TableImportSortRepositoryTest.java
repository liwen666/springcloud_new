package jrx.anyest.table.jpa.dao;

import jrx.anyest.table.TableApplicationStart;
import jrx.anyest.table.jpa.entity.TableImportSort;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TableApplicationStart.class)
public class TableImportSortRepositoryTest {
    @Autowired
    private TableImportSortRepository tableImportSortRepository;


    @Test
    public void name() {
        int orderId = 1;
        TableImportSort tableImportSort = new TableImportSort();
        tableImportSort.setOrderId(orderId++);
        tableImportSort.setTableCodeName("meta_category");
        TableImportSort byTableCodeName = tableImportSortRepository.findByTableCodeName(tableImportSort.getTableCodeName());
        if (null != byTableCodeName) {
            tableImportSort.setId(byTableCodeName.getId());
        }
        tableImportSortRepository.save(tableImportSort);
        tableImportSort.setId(null);
        tableImportSort.setOrderId(orderId++);
        tableImportSort.setTableCodeName("meta_data_source_info");
        byTableCodeName = tableImportSortRepository.findByTableCodeName(tableImportSort.getTableCodeName());
        if (null != byTableCodeName) {
            tableImportSort.setId(byTableCodeName.getId());
        }
        tableImportSortRepository.save(tableImportSort);
        tableImportSort.setId(null);
        tableImportSort.setOrderId(orderId++);
        tableImportSort.setTableCodeName("meta_data_object_info");
        byTableCodeName = tableImportSortRepository.findByTableCodeName(tableImportSort.getTableCodeName());
        if (null != byTableCodeName) {
            tableImportSort.setId(byTableCodeName.getId());
        }
        tableImportSortRepository.save(tableImportSort);
        tableImportSort.setId(null);
        tableImportSort.setOrderId(orderId++);
        tableImportSort.setTableCodeName("meta_model_object_info");
        byTableCodeName = tableImportSortRepository.findByTableCodeName(tableImportSort.getTableCodeName());
        if (null != byTableCodeName) {
            tableImportSort.setId(byTableCodeName.getId());
        }
        tableImportSortRepository.save(tableImportSort);
        tableImportSort.setId(null);
        tableImportSort.setOrderId(orderId++);
        tableImportSort.setTableCodeName("meta_function_info");
        byTableCodeName = tableImportSortRepository.findByTableCodeName(tableImportSort.getTableCodeName());
        if (null != byTableCodeName) {
            tableImportSort.setId(byTableCodeName.getId());
        }
        tableImportSortRepository.save(tableImportSort);


        tableImportSort.setId(null);
        tableImportSort.setOrderId(orderId++);
        tableImportSort.setTableCodeName("res_strategy_info");
        byTableCodeName = tableImportSortRepository.findByTableCodeName(tableImportSort.getTableCodeName());
        if (null != byTableCodeName) {
            tableImportSort.setId(byTableCodeName.getId());
        }
        tableImportSortRepository.save(tableImportSort);

        tableImportSort.setId(null);
        tableImportSort.setOrderId(orderId++);
        tableImportSort.setTableCodeName("res_strategy");
        byTableCodeName = tableImportSortRepository.findByTableCodeName(tableImportSort.getTableCodeName());
        if (null != byTableCodeName) {
            tableImportSort.setId(byTableCodeName.getId());
        }
        tableImportSortRepository.save(tableImportSort);

        tableImportSort.setId(null);
        tableImportSort.setOrderId(orderId++);
        tableImportSort.setTableCodeName("meta_object_field");
        byTableCodeName = tableImportSortRepository.findByTableCodeName(tableImportSort.getTableCodeName());
        if (null != byTableCodeName) {
            tableImportSort.setId(byTableCodeName.getId());
        }
        tableImportSortRepository.save(tableImportSort);

        tableImportSort.setId(null);
        tableImportSort.setOrderId(orderId++);
        tableImportSort.setTableCodeName("meta_topic_object_info");
        byTableCodeName = tableImportSortRepository.findByTableCodeName(tableImportSort.getTableCodeName());
        if (null != byTableCodeName) {
            tableImportSort.setId(byTableCodeName.getId());
        }
        tableImportSortRepository.save(tableImportSort);

        tableImportSort.setId(null);
        tableImportSort.setOrderId(orderId++);
        tableImportSort.setTableCodeName("meta_object_field1");
        byTableCodeName = tableImportSortRepository.findByTableCodeName(tableImportSort.getTableCodeName());
        if (null != byTableCodeName) {
            tableImportSort.setId(byTableCodeName.getId());
        }
        tableImportSortRepository.save(tableImportSort);
        tableImportSort.setId(null);
        tableImportSort.setOrderId(orderId++);
        tableImportSort.setTableCodeName("meta_data_object");
        byTableCodeName = tableImportSortRepository.findByTableCodeName(tableImportSort.getTableCodeName());
        if (null != byTableCodeName) {
            tableImportSort.setId(byTableCodeName.getId());
        }
        tableImportSortRepository.save(tableImportSort);
        tableImportSort.setId(null);
        tableImportSort.setOrderId(orderId++);
        tableImportSort.setTableCodeName("meta_model_object");
        byTableCodeName = tableImportSortRepository.findByTableCodeName(tableImportSort.getTableCodeName());
        if (null != byTableCodeName) {
            tableImportSort.setId(byTableCodeName.getId());
        }
        tableImportSortRepository.save(tableImportSort);
        tableImportSort.setId(null);
        tableImportSort.setOrderId(orderId++);
        tableImportSort.setTableCodeName("meta_function");
        byTableCodeName = tableImportSortRepository.findByTableCodeName(tableImportSort.getTableCodeName());
        if (null != byTableCodeName) {
            tableImportSort.setId(byTableCodeName.getId());
        }
        tableImportSortRepository.save(tableImportSort);

        tableImportSort.setId(null);
        tableImportSort.setOrderId(orderId++);
        tableImportSort.setTableCodeName("meta_topic_object");
        byTableCodeName = tableImportSortRepository.findByTableCodeName(tableImportSort.getTableCodeName());
        if (null != byTableCodeName) {
            tableImportSort.setId(byTableCodeName.getId());
        }
        tableImportSortRepository.save(tableImportSort);

        tableImportSort.setId(null);
        tableImportSort.setOrderId(orderId++);
        tableImportSort.setTableCodeName("meta_topic_object_relation");
        byTableCodeName = tableImportSortRepository.findByTableCodeName(tableImportSort.getTableCodeName());
        if (null != byTableCodeName) {
            tableImportSort.setId(byTableCodeName.getId());
        }
        tableImportSortRepository.save(tableImportSort);


        tableImportSort.setId(null);
        tableImportSort.setOrderId(orderId++);
        tableImportSort.setTableCodeName("res_resource_set_item");
        byTableCodeName = tableImportSortRepository.findByTableCodeName(tableImportSort.getTableCodeName());
        if (null != byTableCodeName) {
            tableImportSort.setId(byTableCodeName.getId());
        }
        tableImportSortRepository.save(tableImportSort);

        tableImportSort.setId(null);
        tableImportSort.setOrderId(orderId++);
        tableImportSort.setTableCodeName("res_rule_info");
        byTableCodeName = tableImportSortRepository.findByTableCodeName(tableImportSort.getTableCodeName());
        if (null != byTableCodeName) {
            tableImportSort.setId(byTableCodeName.getId());
        }
        tableImportSortRepository.save(tableImportSort);

        tableImportSort.setId(null);
        tableImportSort.setOrderId(orderId++);
        tableImportSort.setTableCodeName("res_rule");
        byTableCodeName = tableImportSortRepository.findByTableCodeName(tableImportSort.getTableCodeName());
        if (null != byTableCodeName) {
            tableImportSort.setId(byTableCodeName.getId());
        }
        tableImportSortRepository.save(tableImportSort);

        tableImportSort.setId(null);
        tableImportSort.setOrderId(orderId++);
        tableImportSort.setTableCodeName("res_rule_set_info");
        byTableCodeName = tableImportSortRepository.findByTableCodeName(tableImportSort.getTableCodeName());
        if (null != byTableCodeName) {
            tableImportSort.setId(byTableCodeName.getId());
        }
        tableImportSortRepository.save(tableImportSort);

        tableImportSort.setId(null);
        tableImportSort.setOrderId(orderId++);
        tableImportSort.setTableCodeName("res_rule_set");
        byTableCodeName = tableImportSortRepository.findByTableCodeName(tableImportSort.getTableCodeName());
        if (null != byTableCodeName) {
            tableImportSort.setId(byTableCodeName.getId());
        }
        tableImportSortRepository.save(tableImportSort);

        tableImportSort.setId(null);
        tableImportSort.setOrderId(orderId++);
        tableImportSort.setTableCodeName("res_rule_tree_info");
        byTableCodeName = tableImportSortRepository.findByTableCodeName(tableImportSort.getTableCodeName());
        if (null != byTableCodeName) {
            tableImportSort.setId(byTableCodeName.getId());
        }
        tableImportSortRepository.save(tableImportSort);

        tableImportSort.setId(null);
        tableImportSort.setOrderId(orderId++);
        tableImportSort.setTableCodeName("res_rule_tree");
        byTableCodeName = tableImportSortRepository.findByTableCodeName(tableImportSort.getTableCodeName());
        if (null != byTableCodeName) {
            tableImportSort.setId(byTableCodeName.getId());
        }
        tableImportSortRepository.save(tableImportSort);

        tableImportSort.setId(null);
        tableImportSort.setOrderId(orderId++);
        tableImportSort.setTableCodeName("res_score_card_info");
        byTableCodeName = tableImportSortRepository.findByTableCodeName(tableImportSort.getTableCodeName());
        if (null != byTableCodeName) {
            tableImportSort.setId(byTableCodeName.getId());
        }
        tableImportSortRepository.save(tableImportSort);

        tableImportSort.setId(null);
        tableImportSort.setOrderId(orderId++);
        tableImportSort.setTableCodeName("res_score_card");
        byTableCodeName = tableImportSortRepository.findByTableCodeName(tableImportSort.getTableCodeName());
        if (null != byTableCodeName) {
            tableImportSort.setId(byTableCodeName.getId());
        }
        tableImportSortRepository.save(tableImportSort);

        tableImportSort.setId(null);
        tableImportSort.setOrderId(orderId++);
        tableImportSort.setTableCodeName("res_script_info");
        byTableCodeName = tableImportSortRepository.findByTableCodeName(tableImportSort.getTableCodeName());
        if (null != byTableCodeName) {
            tableImportSort.setId(byTableCodeName.getId());
        }
        tableImportSortRepository.save(tableImportSort);

        tableImportSort.setId(null);
        tableImportSort.setOrderId(orderId++);
        tableImportSort.setTableCodeName("res_script");
        byTableCodeName = tableImportSortRepository.findByTableCodeName(tableImportSort.getTableCodeName());
        if (null != byTableCodeName) {
            tableImportSort.setId(byTableCodeName.getId());
        }
        tableImportSortRepository.save(tableImportSort);





        tableImportSort.setId(null);
        tableImportSort.setOrderId(orderId++);
        tableImportSort.setTableCodeName("res_strategy_node");
        byTableCodeName = tableImportSortRepository.findByTableCodeName(tableImportSort.getTableCodeName());
        if (null != byTableCodeName) {
            tableImportSort.setId(byTableCodeName.getId());
        }
        tableImportSortRepository.save(tableImportSort);


        tableImportSort.setId(null);
        tableImportSort.setOrderId(orderId++);
        tableImportSort.setTableCodeName("res_strategy_node_link");
        byTableCodeName = tableImportSortRepository.findByTableCodeName(tableImportSort.getTableCodeName());
        if (null != byTableCodeName) {
            tableImportSort.setId(byTableCodeName.getId());
        }
        tableImportSortRepository.save(tableImportSort);

        tableImportSort.setId(null);
        tableImportSort.setOrderId(orderId++);
        tableImportSort.setTableCodeName("res_strategy_business_goal");
        byTableCodeName = tableImportSortRepository.findByTableCodeName(tableImportSort.getTableCodeName());
        if (null != byTableCodeName) {
            tableImportSort.setId(byTableCodeName.getId());
        }
        tableImportSortRepository.save(tableImportSort);

        tableImportSort.setId(null);
        tableImportSort.setOrderId(orderId++);
        tableImportSort.setTableCodeName("res_strategy_group");
        byTableCodeName = tableImportSortRepository.findByTableCodeName(tableImportSort.getTableCodeName());
        if (null != byTableCodeName) {
            tableImportSort.setId(byTableCodeName.getId());
        }
        tableImportSortRepository.save(tableImportSort);


        tableImportSort.setId(null);
        tableImportSort.setOrderId(orderId++);
        tableImportSort.setTableCodeName("res_matrix_info");
        byTableCodeName = tableImportSortRepository.findByTableCodeName(tableImportSort.getTableCodeName());
        if (null != byTableCodeName) {
            tableImportSort.setId(byTableCodeName.getId());
        }
        tableImportSortRepository.save(tableImportSort);

        tableImportSort.setId(null);
        tableImportSort.setOrderId(orderId++);
        tableImportSort.setTableCodeName("res_matrix");
        byTableCodeName = tableImportSortRepository.findByTableCodeName(tableImportSort.getTableCodeName());
        if (null != byTableCodeName) {
            tableImportSort.setId(byTableCodeName.getId());
        }
        tableImportSortRepository.save(tableImportSort);


    }

    @Test
    public void test1() {
        List<TableImportSort> collect = tableImportSortRepository.findAll().stream().sorted(Comparator.comparing(TableImportSort::getOrderId)).collect(Collectors.toList());

    }
}