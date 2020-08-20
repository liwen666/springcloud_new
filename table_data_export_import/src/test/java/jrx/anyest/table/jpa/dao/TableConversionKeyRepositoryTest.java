package jrx.anyest.table.jpa.dao;

import jrx.anyest.table.TableApplicationStart;
import jrx.anyest.table.jpa.entity.TableConversionKey;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TableApplicationStart.class)
public class TableConversionKeyRepositoryTest {
    @Autowired
    private TableConversionKeyRepository tableConversionKeyRepository;

    @Test
    public void insert() {
        TableConversionKey tableConversionKey = new TableConversionKey();
        tableConversionKey.setCreateTime(new Date());
        tableConversionKey.setTableCodeName("res_rule_info");
        tableConversionKey.setTableCodeChinaName("规则信息表");
        tableConversionKey.setConversionKey("meta_category@category_id");
        tableConversionKey.setJsonObject(false);
        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
        TableConversionKey tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
        }
        tableConversionKeyRepository.save(tableConversionKey);


        tableConversionKey.setId(null);
        tableConversionKey.setTableCodeName("res_resource_set_item");
        tableConversionKey.setTableCodeChinaName("资源管理表");
        tableConversionKey.setConversionKey("meta_category@category_id");
        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
        tableConversionKey.setJsonObject(false);

        tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
        }
        tableConversionKeyRepository.save(tableConversionKey);

        tableConversionKey.setId(null);
        tableConversionKey.setTableCodeName("res_resource_set_item");
        tableConversionKey.setTableCodeChinaName("资源管理表");
        tableConversionKey.setConversionKey("meta_data_object_info|meta_model_object_info|meta_topic_object_info@resource_id");
        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
        tableConversionKey.setJsonObject(false);

        tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
        }
        tableConversionKeyRepository.save(tableConversionKey);


        tableConversionKey.setId(null);
        tableConversionKey.setTableCodeName("res_rule");
        tableConversionKey.setTableCodeChinaName("规则版本表");
        tableConversionKey.setConversionKey("res_rule_info@resource_id");
        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
        tableConversionKey.setJsonObject(false);

        tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
        }
        tableConversionKeyRepository.save(tableConversionKey);

        tableConversionKey.setId(null);
        tableConversionKey.setTableCodeName("res_rule");
        tableConversionKey.setTableCodeChinaName("规则版本表");
        tableConversionKey.setConversionKey("meta_object_field@field_ids");
        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
        tableConversionKey.setJsonObject(false);

        tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
        }
        tableConversionKeyRepository.save(tableConversionKey);

        tableConversionKey.setId(null);
        tableConversionKey.setCreateTime(new Date());
        tableConversionKey.setTableCodeName("res_rule_set_info");
        tableConversionKey.setTableCodeChinaName("规则集信息表");
        tableConversionKey.setConversionKey("meta_category@category_id");
        tableConversionKey.setJsonObject(false);
        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
         tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
        }
        tableConversionKeyRepository.save(tableConversionKey);
        tableConversionKey.setId(null);
        tableConversionKey.setTableCodeName("res_rule_set");
        tableConversionKey.setTableCodeChinaName("规则集版本表");
        tableConversionKey.setConversionKey("res_rule_set_info@resource_id");
        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
        tableConversionKey.setJsonObject(false);
        tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
        }
        tableConversionKeyRepository.save(tableConversionKey);


        tableConversionKey.setId(null);
        tableConversionKey.setTableCodeName("res_rule_set");
        tableConversionKey.setTableCodeChinaName("规则版本表");
        tableConversionKey.setConversionKey("meta_object_field@field_ids");
        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
        tableConversionKey.setJsonObject(false);

        tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
        }
        tableConversionKeyRepository.save(tableConversionKey);




        tableConversionKey.setId(null);
        tableConversionKey.setCreateTime(new Date());
        tableConversionKey.setTableCodeName("res_rule_tree_info");
        tableConversionKey.setTableCodeChinaName("规则集信息表");
        tableConversionKey.setConversionKey("meta_category@category_id");
        tableConversionKey.setJsonObject(false);
        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
        tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
        }
        tableConversionKeyRepository.save(tableConversionKey);
        tableConversionKey.setId(null);
        tableConversionKey.setTableCodeName("res_rule_tree");
        tableConversionKey.setTableCodeChinaName("规则集版本表");
        tableConversionKey.setConversionKey("res_rule_set_info@resource_id");
        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
        tableConversionKey.setJsonObject(false);
        tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
        }
        tableConversionKeyRepository.save(tableConversionKey);

        tableConversionKey.setId(null);
        tableConversionKey.setTableCodeName("res_rule_tree");
        tableConversionKey.setTableCodeChinaName("规则版本表");
        tableConversionKey.setConversionKey("meta_object_field@field_ids");
        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
        tableConversionKey.setJsonObject(false);

        tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
        }
        tableConversionKeyRepository.save(tableConversionKey);





        tableConversionKey.setId(null);
        tableConversionKey.setCreateTime(new Date());
        tableConversionKey.setTableCodeName("res_score_card_info");
        tableConversionKey.setTableCodeChinaName("规则集信息表");
        tableConversionKey.setConversionKey("meta_category@category_id");
        tableConversionKey.setJsonObject(false);
        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
        tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
        }
        tableConversionKeyRepository.save(tableConversionKey);
        tableConversionKey.setId(null);
        tableConversionKey.setTableCodeName("res_score_card");
        tableConversionKey.setTableCodeChinaName("规则集版本表");
        tableConversionKey.setConversionKey("res_score_card_info@resource_id");
        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
        tableConversionKey.setJsonObject(false);
        tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
        }
        tableConversionKeyRepository.save(tableConversionKey);

        tableConversionKey.setId(null);
        tableConversionKey.setTableCodeName("res_score_card");
        tableConversionKey.setTableCodeChinaName("规则版本表");
        tableConversionKey.setConversionKey("meta_object_field@field_ids");
        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
        tableConversionKey.setJsonObject(false);

        tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
        }
        tableConversionKeyRepository.save(tableConversionKey);



        tableConversionKey.setId(null);
        tableConversionKey.setCreateTime(new Date());
        tableConversionKey.setTableCodeName("res_script_info");
        tableConversionKey.setTableCodeChinaName("规则集信息表");
        tableConversionKey.setConversionKey("meta_category@category_id");
        tableConversionKey.setJsonObject(false);
        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
        tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
        }
        tableConversionKeyRepository.save(tableConversionKey);
        tableConversionKey.setId(null);
        tableConversionKey.setTableCodeName("res_script");
        tableConversionKey.setTableCodeChinaName("规则集版本表");
        tableConversionKey.setConversionKey("res_script_info@resource_id");
        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
        tableConversionKey.setJsonObject(false);
        tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
        }
        tableConversionKeyRepository.save(tableConversionKey);

        tableConversionKey.setId(null);
        tableConversionKey.setTableCodeName("res_script");
        tableConversionKey.setTableCodeChinaName("规则版本表");
        tableConversionKey.setConversionKey("meta_object_field@field_ids");
        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
        tableConversionKey.setJsonObject(false);

        tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
        }
        tableConversionKeyRepository.save(tableConversionKey);




        tableConversionKey.setId(null);
        tableConversionKey.setCreateTime(new Date());
        tableConversionKey.setTableCodeName("res_strategy_info");
        tableConversionKey.setTableCodeChinaName("策略信息表");
        tableConversionKey.setConversionKey("meta_category@category_id");
        tableConversionKey.setJsonObject(false);
        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
        tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
        }
        tableConversionKeyRepository.save(tableConversionKey);
        tableConversionKey.setId(null);
        tableConversionKey.setCreateTime(new Date());
        tableConversionKey.setTableCodeName("res_strategy_info");
        tableConversionKey.setTableCodeChinaName("策略信息表");
        tableConversionKey.setConversionKey("meta_category@model_category_id");
        tableConversionKey.setJsonObject(false);
        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
        tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
        }
        tableConversionKeyRepository.save(tableConversionKey);


        tableConversionKey.setId(null);
        tableConversionKey.setCreateTime(new Date());
        tableConversionKey.setTableCodeName("res_strategy_info");
        tableConversionKey.setTableCodeChinaName("策略信息表");
        tableConversionKey.setConversionKey("meta_data_object_info|meta_model_object_info|meta_topic_object_info@model_resource_id");
        tableConversionKey.setJsonObject(false);
        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
        tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
        }
        tableConversionKeyRepository.save(tableConversionKey);

        tableConversionKey.setId(null);
        tableConversionKey.setCreateTime(new Date());
        tableConversionKey.setTableCodeName("res_strategy_info");
        tableConversionKey.setTableCodeChinaName("策略信息表");
        tableConversionKey.setConversionKey("meta_data_object_info|meta_model_object_info|meta_topic_object_info@model_resource_id");
        tableConversionKey.setJsonObject(false);
        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
        tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
        }
        tableConversionKeyRepository.save(tableConversionKey);




        tableConversionKey.setId(null);
        tableConversionKey.setTableCodeName("res_strategy");
        tableConversionKey.setTableCodeChinaName("规则集版本表");
        tableConversionKey.setConversionKey("res_strategy_info@resource_id");
        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
        tableConversionKey.setJsonObject(false);
        tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
        }
        tableConversionKeyRepository.save(tableConversionKey);

        tableConversionKey.setId(null);
        tableConversionKey.setTableCodeName("res_strategy");
        tableConversionKey.setTableCodeChinaName("规则版本表");
        tableConversionKey.setConversionKey("meta_object_field@field_ids");
        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
        tableConversionKey.setJsonObject(false);

        tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
        }
        tableConversionKeyRepository.save(tableConversionKey);




        tableConversionKey.setId(null);
        tableConversionKey.setCreateTime(new Date());
        tableConversionKey.setTableCodeName("res_matrix_info");
        tableConversionKey.setTableCodeChinaName("规则集信息表");
        tableConversionKey.setConversionKey("meta_category@category_id");
        tableConversionKey.setJsonObject(false);
        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
        tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
        }
        tableConversionKeyRepository.save(tableConversionKey);
        tableConversionKey.setId(null);
        tableConversionKey.setTableCodeName("res_matrix");
        tableConversionKey.setTableCodeChinaName("规则集版本表");
        tableConversionKey.setConversionKey("res_matrix_info@resource_id");
        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
        tableConversionKey.setJsonObject(false);
        tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
        }
        tableConversionKeyRepository.save(tableConversionKey);

        tableConversionKey.setId(null);
        tableConversionKey.setTableCodeName("res_matrix");
        tableConversionKey.setTableCodeChinaName("规则版本表");
        tableConversionKey.setConversionKey("meta_object_field@field_ids");
        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
        tableConversionKey.setJsonObject(false);

        tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
        }
        tableConversionKeyRepository.save(tableConversionKey);


        /**
         * 版本弱化这项去掉
         */
//        tableConversionKey.setId(null);
//        tableConversionKey.setTableCodeName("meta_topic_object_relation");
//        tableConversionKey.setTableCodeChinaName("统计模型关联关系表");
//        tableConversionKey.setConversionKey("meta_data_object|meta_model_object|meta_topic_object@primary_data_object_id");
//        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
//        tableConversionKey.setJsonObject(false);
//        tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
//        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
//            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
//        }
//        tableConversionKeyRepository.save(tableConversionKey);

        tableConversionKey.setId(null);
        tableConversionKey.setTableCodeName("meta_topic_object_relation");
        tableConversionKey.setTableCodeChinaName("统计模型关联关系表");
//        tableConversionKey.setConversionKey("meta_data_object|meta_model_object|meta_topic_object@primary_resource_id");
        tableConversionKey.setConversionKey("meta_data_object_info@primary_resource_id");
        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
        tableConversionKey.setJsonObject(false);
        tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
        }
        tableConversionKeyRepository.save(tableConversionKey);

        tableConversionKey.setId(null);
        tableConversionKey.setTableCodeName("meta_topic_object_relation");
        tableConversionKey.setTableCodeChinaName("统计模型关联表");
        tableConversionKey.setConversionKey("meta_object_field@primary_field_ids");
        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
        tableConversionKey.setJsonObject(false);
        tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
        }
        tableConversionKeyRepository.save(tableConversionKey);

        tableConversionKey.setId(null);
        tableConversionKey.setTableCodeName("meta_topic_object_relation");
        tableConversionKey.setTableCodeChinaName("统计模型关联表");
        tableConversionKey.setConversionKey("meta_object_field@relation_field_ids");
        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
        tableConversionKey.setJsonObject(false);
        tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
        }
        tableConversionKeyRepository.save(tableConversionKey);


        tableConversionKey.setId(null);
        tableConversionKey.setTableCodeName("meta_topic_object_relation");
        tableConversionKey.setTableCodeChinaName("统计模型关联表");
        tableConversionKey.setConversionKey("meta_data_object_info@relation_object_id");
        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
        tableConversionKey.setJsonObject(false);
        tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
        }
        tableConversionKeyRepository.save(tableConversionKey);

        tableConversionKey.setId(null);
        tableConversionKey.setTableCodeName("meta_topic_object_relation");
        tableConversionKey.setTableCodeChinaName("统计模型关联表");
        tableConversionKey.setConversionKey("meta_topic_object_info@topic_object_info_id");
        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
        tableConversionKey.setJsonObject(false);
        tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
        }
        tableConversionKeyRepository.save(tableConversionKey);


        tableConversionKey.setId(null);
        tableConversionKey.setTableCodeName("meta_topic_object_info");
        tableConversionKey.setTableCodeChinaName("统计模型信息表");
        tableConversionKey.setConversionKey("meta_category@category_id");
        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
        tableConversionKey.setJsonObject(false);
        tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
        }
        tableConversionKeyRepository.save(tableConversionKey);

        tableConversionKey.setId(null);
        tableConversionKey.setTableCodeName("meta_topic_object_info");
        tableConversionKey.setTableCodeChinaName("统计模型信息表");
        tableConversionKey.setConversionKey("meta_data_source_info@data_source_id");
        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
        tableConversionKey.setJsonObject(false);
        tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
        }
        tableConversionKeyRepository.save(tableConversionKey);


        /**
         * 版本弱化这项去掉
         */
//        tableConversionKey.setId(null);
//        tableConversionKey.setTableCodeName("meta_topic_object_info");
//        tableConversionKey.setTableCodeChinaName("统计模型信息表");
//        tableConversionKey.setConversionKey("meta_data_object|meta_model_object|meta_topic_object@primary_data_object_id");
//        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
//        tableConversionKey.setJsonObject(false);
//        tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
//        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
//            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
//        }
//        tableConversionKeyRepository.save(tableConversionKey);


        tableConversionKey.setId(null);
        tableConversionKey.setTableCodeName("meta_topic_object_info");
        tableConversionKey.setTableCodeChinaName("统计模型信息表");
        tableConversionKey.setConversionKey("meta_object_field@primary_dimension_id");
        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
        tableConversionKey.setJsonObject(false);
        tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
        }
        tableConversionKeyRepository.save(tableConversionKey);


        tableConversionKey.setId(null);
        tableConversionKey.setTableCodeName("meta_topic_object_info");
        tableConversionKey.setTableCodeChinaName("统计模型信息表");
        tableConversionKey.setConversionKey("meta_object_field@primary_field_id");
        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
        tableConversionKey.setJsonObject(false);
        tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
        }
        tableConversionKeyRepository.save(tableConversionKey);

        tableConversionKey.setId(null);
        tableConversionKey.setTableCodeName("meta_topic_object_info");
        tableConversionKey.setTableCodeChinaName("统计模型信息表");
        tableConversionKey.setConversionKey("meta_object_field@primary_key_field_ids");
        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
        tableConversionKey.setJsonObject(false);
        tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
        }
        tableConversionKeyRepository.save(tableConversionKey);

        tableConversionKey.setId(null);
        tableConversionKey.setTableCodeName("meta_topic_object_info");
        tableConversionKey.setTableCodeChinaName("统计模型信息表");
        tableConversionKey.setConversionKey("meta_object_field@second_dimension_ids");
        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
        tableConversionKey.setJsonObject(false);
        tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
        }
        tableConversionKeyRepository.save(tableConversionKey);

        tableConversionKey.setId(null);
        tableConversionKey.setTableCodeName("meta_topic_object_info");
        tableConversionKey.setTableCodeChinaName("统计模型信息表");
        tableConversionKey.setConversionKey("meta_object_field@stat_time_field_id");
        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
        tableConversionKey.setJsonObject(false);
        tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
        }
        tableConversionKeyRepository.save(tableConversionKey);

        tableConversionKey.setId(null);
        tableConversionKey.setTableCodeName("meta_topic_object_info");
        tableConversionKey.setTableCodeChinaName("统计模型信息表");
//        tableConversionKey.setConversionKey("meta_data_object_info|meta_model_object_info|meta_topic_object_info@primary_resource_id");
        tableConversionKey.setConversionKey("meta_data_object_info@primary_resource_id");
        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
        tableConversionKey.setJsonObject(false);
        tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
        }
        tableConversionKeyRepository.save(tableConversionKey);

        tableConversionKey.setId(null);
        tableConversionKey.setTableCodeName("meta_topic_object");
        tableConversionKey.setTableCodeChinaName("统计模型表");
        tableConversionKey.setConversionKey("meta_topic_object_info@resource_id");
        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
        tableConversionKey.setJsonObject(false);
        tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
        }
        tableConversionKeyRepository.save(tableConversionKey);


        tableConversionKey.setId(null);
        tableConversionKey.setTableCodeName("meta_topic_object");
        tableConversionKey.setTableCodeChinaName("统计模型表");
        tableConversionKey.setConversionKey("meta_object_field@derive_ids");
        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
        tableConversionKey.setJsonObject(false);
        tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
        }
        tableConversionKeyRepository.save(tableConversionKey);

        /**
         * 版本弱化，不支持字段引用版本
         */


        tableConversionKey.setId(null);
        tableConversionKey.setTableCodeName("meta_object_field");
        tableConversionKey.setTableCodeChinaName("字段表");
        tableConversionKey.setConversionKey("meta_object_field@derive_content.statisticFieldBid");
        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
        tableConversionKey.setJsonObject(false);
        tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
        }
        tableConversionKeyRepository.save(tableConversionKey);

        tableConversionKey.setId(null);
        tableConversionKey.setTableCodeName("meta_object_field");
        tableConversionKey.setTableCodeChinaName("字段表");
        tableConversionKey.setConversionKey("meta_object_field@refer_fieldIds");
        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
        tableConversionKey.setJsonObject(false);
        tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
        }
        tableConversionKeyRepository.save(tableConversionKey);

        tableConversionKey.setId(null);
        tableConversionKey.setTableCodeName("meta_object_field");
        tableConversionKey.setTableCodeChinaName("字段表");
        tableConversionKey.setConversionKey("meta_data_object_info|meta_model_object_info|meta_topic_object_info@resource_object_id");
        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
        tableConversionKey.setJsonObject(false);
        tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
        }
        tableConversionKeyRepository.save(tableConversionKey);


        tableConversionKey.setId(null);
        tableConversionKey.setTableCodeName("meta_object_field");
        tableConversionKey.setTableCodeChinaName("字段表");
        tableConversionKey.setConversionKey("meta_object_field@derive_content.originField.bid");
        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
        tableConversionKey.setJsonObject(false);
        tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
        }
        tableConversionKeyRepository.save(tableConversionKey);

        tableConversionKey.setId(null);
        tableConversionKey.setTableCodeName("meta_object_field");
        tableConversionKey.setTableCodeChinaName("字段表");
        tableConversionKey.setConversionKey("meta_object_field@derive_content.originField.fieldId");
        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
        tableConversionKey.setJsonObject(false);
        tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
        }
        tableConversionKeyRepository.save(tableConversionKey);

        tableConversionKey.setId(null);
        tableConversionKey.setTableCodeName("meta_object_field");
        tableConversionKey.setTableCodeChinaName("字段表");
        tableConversionKey.setConversionKey("meta_object_field@derive_content.valueConditions.conditionGroup.children.fieldBid");
        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
        tableConversionKey.setJsonObject(false);
        tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
        }
        tableConversionKeyRepository.save(tableConversionKey);


        tableConversionKey.setId(null);
        tableConversionKey.setTableCodeName("meta_object_field");
        tableConversionKey.setTableCodeChinaName("字段表");
        tableConversionKey.setConversionKey("meta_object_field@derive_content.valueConditions.expressionUnits.code");
        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
        tableConversionKey.setJsonObject(false);
        tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
        }
        tableConversionKeyRepository.save(tableConversionKey);


        tableConversionKey.setId(null);
        tableConversionKey.setTableCodeName("meta_object_field");
        tableConversionKey.setTableCodeChinaName("字段表");
        tableConversionKey.setConversionKey("meta_object_field@derive_content.valueConditions.referFieldBids");
        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
        tableConversionKey.setJsonObject(false);
        tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
        }
        tableConversionKeyRepository.save(tableConversionKey);


        tableConversionKey.setId(null);
        tableConversionKey.setTableCodeName("meta_object_field");
        tableConversionKey.setTableCodeChinaName("字段表");
        tableConversionKey.setConversionKey("meta_object_field@derive_content.expressionUnits.code");
        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
        tableConversionKey.setJsonObject(false);
        tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
        }
        tableConversionKeyRepository.save(tableConversionKey);


        tableConversionKey.setId(null);
        tableConversionKey.setTableCodeName("meta_object_field");
        tableConversionKey.setTableCodeChinaName("字段表");
        tableConversionKey.setConversionKey("meta_object_field@derive_content.referFieldBids");
        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
        tableConversionKey.setJsonObject(false);
        tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
        }
        tableConversionKeyRepository.save(tableConversionKey);


        /**
         * 不做版本级联
         */
//        tableConversionKey.setId(null);
//        tableConversionKey.setTableCodeName("meta_object_field");
//        tableConversionKey.setTableCodeChinaName("字段表");
//        tableConversionKey.setConversionKey("meta_data_object|meta_model_object|meta_topic_object@derive_content.tableVersionId");
//        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
//        tableConversionKey.setJsonObject(false);
//        tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
//        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
//            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
//        }
//        tableConversionKeyRepository.save(tableConversionKey);

        tableConversionKey.setId(null);
        tableConversionKey.setTableCodeName("meta_object_field");
        tableConversionKey.setTableCodeChinaName("字段表");
        tableConversionKey.setConversionKey("meta_category@resource_object_category_id");
        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
        tableConversionKey.setJsonObject(false);
        tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
        }
        tableConversionKeyRepository.save(tableConversionKey);


        tableConversionKey.setId(null);
        tableConversionKey.setTableCodeName("meta_model_object_info");
        tableConversionKey.setTableCodeChinaName("模型信息表");
        tableConversionKey.setConversionKey("meta_category@category_id");
        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
        tableConversionKey.setJsonObject(false);
        tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
        }
        tableConversionKeyRepository.save(tableConversionKey);

        tableConversionKey.setId(null);
        tableConversionKey.setTableCodeName("meta_model_object");
        tableConversionKey.setTableCodeChinaName("模型版本表");
        tableConversionKey.setConversionKey("meta_model_object_info@resource_id");
        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
        tableConversionKey.setJsonObject(false);
        tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
        }
        tableConversionKeyRepository.save(tableConversionKey);

        tableConversionKey.setId(null);
        tableConversionKey.setTableCodeName("meta_model_object");
        tableConversionKey.setTableCodeChinaName("模型版本表");
        tableConversionKey.setConversionKey("meta_object_field@field_ids");
        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
        tableConversionKey.setJsonObject(false);
        tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
        }
        tableConversionKeyRepository.save(tableConversionKey);


        tableConversionKey.setId(null);
        tableConversionKey.setTableCodeName("meta_function_info");
        tableConversionKey.setTableCodeChinaName("函数信息表");
        tableConversionKey.setConversionKey("meta_category@category_id");
        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
        tableConversionKey.setJsonObject(false);
        tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
        }
        tableConversionKeyRepository.save(tableConversionKey);

        tableConversionKey.setId(null);
        tableConversionKey.setTableCodeName("meta_function");
        tableConversionKey.setTableCodeChinaName("函数版本表");
        tableConversionKey.setConversionKey("meta_function_info@resource_id");
        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
        tableConversionKey.setJsonObject(false);
        tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
        }
        tableConversionKeyRepository.save(tableConversionKey);


        tableConversionKey.setId(null);
        tableConversionKey.setTableCodeName("meta_data_source_info");
        tableConversionKey.setTableCodeChinaName("数据源信息表");
        tableConversionKey.setConversionKey("meta_category@category_id");
        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
        tableConversionKey.setJsonObject(false);
        tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
        }
        tableConversionKeyRepository.save(tableConversionKey);

        tableConversionKey.setId(null);
        tableConversionKey.setTableCodeName("meta_data_object_info");
        tableConversionKey.setTableCodeChinaName("数据集信息表");
        tableConversionKey.setConversionKey("meta_category@category_id");
        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
        tableConversionKey.setJsonObject(false);
        tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
        }
        tableConversionKeyRepository.save(tableConversionKey);

        tableConversionKey.setId(null);
        tableConversionKey.setTableCodeName("meta_data_object_info");
        tableConversionKey.setTableCodeChinaName("数据集信息表");
        tableConversionKey.setConversionKey("meta_data_source_info@data_source_id");
        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
        tableConversionKey.setJsonObject(false);
        tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
        }
        tableConversionKeyRepository.save(tableConversionKey);


        tableConversionKey.setId(null);
        tableConversionKey.setTableCodeName("meta_data_object");
        tableConversionKey.setTableCodeChinaName("数据集版本表");
        tableConversionKey.setConversionKey("meta_data_object_info@resource_id");
        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
        tableConversionKey.setJsonObject(false);
        tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
        }
        tableConversionKeyRepository.save(tableConversionKey);


        tableConversionKey.setId(null);
        tableConversionKey.setTableCodeName("meta_data_object");
        tableConversionKey.setTableCodeChinaName("数据集版本表");
        tableConversionKey.setConversionKey("meta_object_field@field_ids");
        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
        tableConversionKey.setJsonObject(false);
        tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
        }
        tableConversionKeyRepository.save(tableConversionKey);


        tableConversionKey.setId(null);
        tableConversionKey.setTableCodeName("meta_category");
        tableConversionKey.setTableCodeChinaName("数据集版本表");
        tableConversionKey.setConversionKey("meta_category@parent_id");
        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
        tableConversionKey.setJsonObject(false);
        tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
        }
        tableConversionKeyRepository.save(tableConversionKey);


    }


}