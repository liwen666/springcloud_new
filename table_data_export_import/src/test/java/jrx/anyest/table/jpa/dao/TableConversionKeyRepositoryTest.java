package jrx.anyest.table.jpa.dao;

import jrx.anyest.table.TableApplicationStart;
import jrx.anyest.table.jpa.entity.TableConversionKey;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;


/**
 *
 * --   INSERT INTO `table_conversion_key` VALUES (3615, '2020-09-02 11:39:39', b'1', 'meta_object_field@derive_content.valueConditions.expressionUnits.code', 'defaultTableDataHandler', b'0', b'0', '字段表', 'meta_object_field');
 */
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
        tableConversionKey.setTableCodeName("res_rule_set");
        tableConversionKey.setTableCodeChinaName("规则版本表");
        tableConversionKey.setConversionKey("res_rule_info@rule_items.resourceId");
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
        tableConversionKey.setTableCodeName("res_rule_tree");
        tableConversionKey.setTableCodeChinaName("规则版本表");
        tableConversionKey.setConversionKey("meta_object_field@nodes.field.bid");
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
        tableConversionKey.setConversionKey("meta_object_field@nodes.field.fieldId");
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
        tableConversionKey.setConversionKey("meta_object_field@nodes.expressionUnits.code");
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
        tableConversionKey.setConversionKey("meta_data_object_info|meta_model_object_info|meta_topic_object_info@nodes.field.resourceObjectId");
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
        tableConversionKey.setTableCodeName("res_score_card");
        tableConversionKey.setTableCodeChinaName("规则版本表");
        tableConversionKey.setConversionKey("meta_object_field@items.conditionField.bid");
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
        tableConversionKey.setConversionKey("meta_object_field@items.conditionField.fieldId");
        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
        tableConversionKey.setJsonObject(false);

        tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
        }
        tableConversionKeyRepository.save(tableConversionKey);

//        tableConversionKey.setId(null);
//        tableConversionKey.setTableCodeName("res_score_card");
//        tableConversionKey.setTableCodeChinaName("规则版本表");
//        tableConversionKey.setConversionKey("meta_object_field@items.conditionField.deriveContent");
//        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
//        tableConversionKey.setJsonObject(false);
//
//        tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
//        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
//            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
//        }
//        tableConversionKeyRepository.save(tableConversionKey);


        tableConversionKey.setId(null);
        tableConversionKey.setTableCodeName("res_score_card");
        tableConversionKey.setTableCodeChinaName("规则版本表");
        tableConversionKey.setConversionKey("meta_object_field@items.conditionField.referFields.bid");
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
        tableConversionKey.setConversionKey("meta_object_field@items.conditionField.referFields.fieldId");
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
        tableConversionKey.setConversionKey("meta_data_object_info|meta_model_object_info|meta_topic_object_info@items.conditionField.resourceObjectId");
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
        tableConversionKey.setTableCodeName("res_script");
        tableConversionKey.setTableCodeChinaName("规则版本表");
        tableConversionKey.setConversionKey("meta_object_field@in_param_fields.mapField.bid");
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
        tableConversionKey.setConversionKey("meta_object_field@in_param_fields.mapField.fieldId");
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
        tableConversionKey.setConversionKey("meta_data_object_info|meta_model_object_info|meta_topic_object_info@in_param_fields.mapField.resourceObjectId");
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


        tableConversionKey.setId(null);
        tableConversionKey.setTableCodeName("res_matrix");
        tableConversionKey.setTableCodeChinaName("规则版本表");
        tableConversionKey.setConversionKey("meta_object_field@output_field_ids");
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
        tableConversionKey.setConversionKey("meta_object_field@row_field_ids");
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
        tableConversionKey.setConversionKey("meta_data_object_info|meta_model_object_info|meta_topic_object_info|res_strategy_info@resource_object_id");
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


//        tableConversionKey.setId(null);
//        tableConversionKey.setTableCodeName("meta_object_field");
//        tableConversionKey.setTableCodeChinaName("字段表");
//        tableConversionKey.setConversionKey("meta_object_field@derive_content.valueConditions.expressionUnits.code");
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
         * deriveContent.tableVersionId
         */

        tableConversionKey.setId(null);
        tableConversionKey.setTableCodeName("meta_object_field");
        tableConversionKey.setTableCodeChinaName("字段表");
        tableConversionKey.setConversionKey("meta_data_object_info|meta_model_object_info|meta_topic_object_info@derive_content.tableId");
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


        tableConversionKey.setId(null);
        tableConversionKey.setTableCodeName("res_rule");
        tableConversionKey.setTableCodeChinaName("规则版本");
        tableConversionKey.setConversionKey("meta_object_field@hit_output.expField");
        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
        tableConversionKey.setJsonObject(false);
        tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
        }
        tableConversionKeyRepository.save(tableConversionKey);
        tableConversionKey.setId(null);
        tableConversionKey.setTableCodeName("res_rule");
        tableConversionKey.setTableCodeChinaName("规则版本");
        tableConversionKey.setConversionKey("meta_object_field@hit_output.outField.bid");
        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
        tableConversionKey.setJsonObject(false);
        tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
        }
        tableConversionKeyRepository.save(tableConversionKey);


        tableConversionKey.setId(null);
        tableConversionKey.setTableCodeName("res_rule");
        tableConversionKey.setTableCodeChinaName("规则版本");
        tableConversionKey.setConversionKey("meta_data_object_info|meta_model_object_info|meta_topic_object_info|res_strategy_info@hit_output.outField.resourceObjectId");
        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
        tableConversionKey.setJsonObject(false);
        tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
        }
        tableConversionKeyRepository.save(tableConversionKey);


        tableConversionKey.setId(null);
        tableConversionKey.setTableCodeName("res_rule");
        tableConversionKey.setTableCodeChinaName("规则版本");
        tableConversionKey.setConversionKey("meta_object_field@conditions.children.fieldBid");
        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
        tableConversionKey.setJsonObject(false);
        tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
        }
        tableConversionKeyRepository.save(tableConversionKey);

        tableConversionKey.setId(null);
        tableConversionKey.setTableCodeName("res_rule");
        tableConversionKey.setTableCodeChinaName("规则版本");
        tableConversionKey.setConversionKey("meta_object_field@conditions.children.fieldBid");
        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
        tableConversionKey.setJsonObject(false);
        tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
        }
        tableConversionKeyRepository.save(tableConversionKey);


        tableConversionKey.setId(null);
        tableConversionKey.setTableCodeName("meta_function");
        tableConversionKey.setTableCodeChinaName("函数表");
        tableConversionKey.setConversionKey("meta_function_info@resource_id");
        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
        tableConversionKey.setJsonObject(false);
        tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
        }
        tableConversionKeyRepository.save(tableConversionKey);




        tableConversionKey.setId(null);
        tableConversionKey.setTableCodeName("res_strategy_node");
        tableConversionKey.setTableCodeChinaName("节点表");
        tableConversionKey.setConversionKey("meta_category@node_content.ruleItemList.category.categoryId");
        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
        tableConversionKey.setJsonObject(false);
        tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
        }
        tableConversionKeyRepository.save(tableConversionKey);


        tableConversionKey.setId(null);
        tableConversionKey.setTableCodeName("res_strategy_node");
        tableConversionKey.setTableCodeChinaName("节点表");
        tableConversionKey.setConversionKey("meta_data_object_info|meta_model_object_info|meta_topic_object_info|res_strategy_info@node_content.eventStatObjectInfoId");
        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
        tableConversionKey.setJsonObject(false);
        tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
        }
        tableConversionKeyRepository.save(tableConversionKey);

        tableConversionKey.setId(null);
        tableConversionKey.setTableCodeName("res_strategy_node");
        tableConversionKey.setTableCodeChinaName("节点表");
        tableConversionKey.setConversionKey("res_rule_info@node_content.ruleItemList.resourceId");
        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
        tableConversionKey.setJsonObject(false);
        tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
        }
        tableConversionKeyRepository.save(tableConversionKey);

        tableConversionKey.setId(null);
        tableConversionKey.setTableCodeName("res_strategy_node");
        tableConversionKey.setTableCodeChinaName("节点表");
        tableConversionKey.setConversionKey("meta_object_field@node_content.statTimeFieldMapperBid");
        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
        tableConversionKey.setJsonObject(false);
        tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
        }
        tableConversionKeyRepository.save(tableConversionKey);

        tableConversionKey.setId(null);
        tableConversionKey.setTableCodeName("res_strategy_node");
        tableConversionKey.setTableCodeChinaName("节点表");
        tableConversionKey.setConversionKey("meta_object_field@node_content.statParams.paramBids");
        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
        tableConversionKey.setJsonObject(false);
        tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
        }
        tableConversionKeyRepository.save(tableConversionKey);

        tableConversionKey.setId(null);
        tableConversionKey.setTableCodeName("res_strategy_node");
        tableConversionKey.setTableCodeChinaName("节点表");
        tableConversionKey.setConversionKey("meta_object_field@node_content.statParams.outBid");
        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
        tableConversionKey.setJsonObject(false);
        tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
        }
        tableConversionKeyRepository.save(tableConversionKey);


        tableConversionKey.setId(null);
        tableConversionKey.setTableCodeName("res_strategy_node");
        tableConversionKey.setTableCodeChinaName("节点表");
        tableConversionKey.setConversionKey("meta_object_field@node_content.primaryFieldMapperBid");
        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
        tableConversionKey.setJsonObject(false);
        tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
        }
        tableConversionKeyRepository.save(tableConversionKey);


        tableConversionKey.setId(null);
        tableConversionKey.setTableCodeName("res_strategy_node");
        tableConversionKey.setTableCodeChinaName("节点表");
        tableConversionKey.setConversionKey("meta_data_object_info|meta_model_object_info|meta_topic_object_info|res_strategy_info@node_content.resourceObjectId");
        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
        tableConversionKey.setJsonObject(false);
        tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
        }
        tableConversionKeyRepository.save(tableConversionKey);


        tableConversionKey.setId(null);
        tableConversionKey.setTableCodeName("res_strategy_node");
        tableConversionKey.setTableCodeChinaName("节点表");
        tableConversionKey.setConversionKey("meta_object_field@node_content.items.conditionField.bid");
        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
        tableConversionKey.setJsonObject(false);
        tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
        }
        tableConversionKeyRepository.save(tableConversionKey);


        tableConversionKey.setId(null);
        tableConversionKey.setTableCodeName("res_strategy_node");
        tableConversionKey.setTableCodeChinaName("节点表");
        tableConversionKey.setConversionKey("meta_object_field@node_content.items.conditionField.fieldId");
        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
        tableConversionKey.setJsonObject(false);
        tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
        }
        tableConversionKeyRepository.save(tableConversionKey);



        tableConversionKey.setId(null);
        tableConversionKey.setTableCodeName("res_strategy_node");
        tableConversionKey.setTableCodeChinaName("节点表");
        tableConversionKey.setConversionKey("meta_object_field@node_content.items.conditionField.fieldId");
        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
        tableConversionKey.setJsonObject(false);
        tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
        }
        tableConversionKeyRepository.save(tableConversionKey);

        tableConversionKey.setId(null);
        tableConversionKey.setTableCodeName("res_strategy_node");
        tableConversionKey.setTableCodeChinaName("节点表");
        tableConversionKey.setConversionKey("meta_data_object_info|meta_model_object_info|meta_topic_object_info|res_strategy_info@node_content.items.conditionField.resourceObjectId");
        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
        tableConversionKey.setJsonObject(false);
        tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
        }
        tableConversionKeyRepository.save(tableConversionKey);


        tableConversionKey.setId(null);
        tableConversionKey.setTableCodeName("res_strategy_node");
        tableConversionKey.setTableCodeChinaName("节点表");
        tableConversionKey.setConversionKey("meta_object_field@node_content.scoreField.bid");
        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
        tableConversionKey.setJsonObject(false);
        tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
        }
        tableConversionKeyRepository.save(tableConversionKey);

        tableConversionKey.setId(null);
        tableConversionKey.setTableCodeName("res_strategy_node");
        tableConversionKey.setTableCodeChinaName("节点表");
        tableConversionKey.setConversionKey("meta_object_field@node_content.scoreField.fieldId");
        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
        tableConversionKey.setJsonObject(false);
        tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
        }
        tableConversionKeyRepository.save(tableConversionKey);


        tableConversionKey.setId(null);
        tableConversionKey.setTableCodeName("res_strategy_node");
        tableConversionKey.setTableCodeChinaName("节点表");
        tableConversionKey.setConversionKey("meta_data_object_info|meta_model_object_info|meta_topic_object_info|res_strategy_info@node_content.scoreField.resourceObjectId");
        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
        tableConversionKey.setJsonObject(false);
        tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
        }
        tableConversionKeyRepository.save(tableConversionKey);


        tableConversionKey.setId(null);
        tableConversionKey.setTableCodeName("res_strategy_node");
        tableConversionKey.setTableCodeChinaName("节点表");
        tableConversionKey.setConversionKey("meta_data_object_info|meta_model_object_info|meta_topic_object_info|res_strategy_info@node_content.missOutputList.outField.resourceObjectId");
        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
        tableConversionKey.setJsonObject(false);
        tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
        }
        tableConversionKeyRepository.save(tableConversionKey);

        tableConversionKey.setId(null);
        tableConversionKey.setTableCodeName("res_strategy_node");
        tableConversionKey.setTableCodeChinaName("节点表");
        tableConversionKey.setConversionKey("meta_data_object_info|meta_model_object_info|meta_topic_object_info|res_strategy_info@node_content.hitOutputList.outField.resourceObjectId");
        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
        tableConversionKey.setJsonObject(false);
        tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
        }
        tableConversionKeyRepository.save(tableConversionKey);


        tableConversionKey.setId(null);
        tableConversionKey.setTableCodeName("res_strategy_node");
        tableConversionKey.setTableCodeChinaName("节点表");
        tableConversionKey.setConversionKey("meta_object_field@node_content.missOutputList.expField");
        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
        tableConversionKey.setJsonObject(false);
        tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
        }
        tableConversionKeyRepository.save(tableConversionKey);


        tableConversionKey.setId(null);
        tableConversionKey.setTableCodeName("res_strategy_node");
        tableConversionKey.setTableCodeChinaName("节点表");
        tableConversionKey.setConversionKey("meta_object_field@node_content.expFields");
        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
        tableConversionKey.setJsonObject(false);
        tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
        }
        tableConversionKeyRepository.save(tableConversionKey);


        tableConversionKey.setId(null);
        tableConversionKey.setTableCodeName("res_strategy_node");
        tableConversionKey.setTableCodeChinaName("节点表");
        tableConversionKey.setConversionKey("meta_object_field@node_content.condition.children.fieldBid");
        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
        tableConversionKey.setJsonObject(false);
        tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
        }
        tableConversionKeyRepository.save(tableConversionKey);

        tableConversionKey.setId(null);
        tableConversionKey.setTableCodeName("res_strategy_node");
        tableConversionKey.setTableCodeChinaName("节点表");
        tableConversionKey.setConversionKey("meta_object_field@node_content.hitOutputList.outField.bid");
        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
        tableConversionKey.setJsonObject(false);
        tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
        }
        tableConversionKeyRepository.save(tableConversionKey);

        tableConversionKey.setId(null);
        tableConversionKey.setTableCodeName("res_strategy_node");
        tableConversionKey.setTableCodeChinaName("节点表");
        tableConversionKey.setConversionKey("meta_object_field@node_content.hitOutputList.outField.fieldId");
        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
        tableConversionKey.setJsonObject(false);
        tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
        }
        tableConversionKeyRepository.save(tableConversionKey);

        tableConversionKey.setId(null);
        tableConversionKey.setTableCodeName("res_strategy_node");
        tableConversionKey.setTableCodeChinaName("节点表");
        tableConversionKey.setConversionKey("meta_object_field@node_content.hitOutputList.expField");
        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
        tableConversionKey.setJsonObject(false);
        tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
        }
        tableConversionKeyRepository.save(tableConversionKey);

        tableConversionKey.setId(null);
        tableConversionKey.setTableCodeName("res_strategy_node");
        tableConversionKey.setTableCodeChinaName("节点表");
        tableConversionKey.setConversionKey("meta_object_field@node_content.missOutputList.outField.bid");
        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
        tableConversionKey.setJsonObject(false);
        tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
        }
        tableConversionKeyRepository.save(tableConversionKey);

        tableConversionKey.setId(null);
        tableConversionKey.setTableCodeName("res_strategy_node");
        tableConversionKey.setTableCodeChinaName("节点表");
        tableConversionKey.setConversionKey("meta_object_field@node_content.missOutputList.outField.fieldId");
        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
        tableConversionKey.setJsonObject(false);
        tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
        }
        tableConversionKeyRepository.save(tableConversionKey);


        tableConversionKey.setId(null);
        tableConversionKey.setTableCodeName("res_strategy_node");
        tableConversionKey.setTableCodeChinaName("节点表");
        tableConversionKey.setConversionKey("meta_object_field@node_content.queryParams.valueField.fieldId");
        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
        tableConversionKey.setJsonObject(false);
        tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
        }
        tableConversionKeyRepository.save(tableConversionKey);
        tableConversionKey.setId(null);
        tableConversionKey.setTableCodeName("res_strategy_node");
        tableConversionKey.setTableCodeChinaName("节点表");
        tableConversionKey.setConversionKey("meta_object_field@node_content.queryParams.valueField.bid");
        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
        tableConversionKey.setJsonObject(false);
        tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
        }
        tableConversionKeyRepository.save(tableConversionKey);




        tableConversionKey.setId(null);
        tableConversionKey.setTableCodeName("res_strategy_node");
        tableConversionKey.setTableCodeChinaName("节点表");
        tableConversionKey.setConversionKey("meta_data_object_info|meta_model_object_info|meta_topic_object_info|res_strategy_info@node_content.queryParams.valueField.resourceObjectId");
        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
        tableConversionKey.setJsonObject(false);
        tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
        }
        tableConversionKeyRepository.save(tableConversionKey);


        tableConversionKey.setId(null);
        tableConversionKey.setTableCodeName("res_strategy_node");
        tableConversionKey.setTableCodeChinaName("节点表");
        tableConversionKey.setConversionKey("meta_data_object_info|meta_model_object_info|meta_topic_object_info|res_strategy_info@node_content.dataObjectInfoId");
        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
        tableConversionKey.setJsonObject(false);
        tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
        }
        tableConversionKeyRepository.save(tableConversionKey);

        tableConversionKey.setId(null);
        tableConversionKey.setTableCodeName("res_strategy_node");
        tableConversionKey.setTableCodeChinaName("节点表");
        tableConversionKey.setConversionKey("meta_object_field@node_content.bid");
        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
        tableConversionKey.setJsonObject(false);
        tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
        }
        tableConversionKeyRepository.save(tableConversionKey);

        tableConversionKey.setId(null);
        tableConversionKey.setTableCodeName("res_strategy_node");
        tableConversionKey.setTableCodeChinaName("节点表");
        tableConversionKey.setConversionKey("meta_object_field@node_content.fieldId");
        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
        tableConversionKey.setJsonObject(false);
        tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
        }
        tableConversionKeyRepository.save(tableConversionKey);

        tableConversionKey.setId(null);
        tableConversionKey.setTableCodeName("res_strategy_node");
        tableConversionKey.setTableCodeChinaName("节点表");
        tableConversionKey.setConversionKey("meta_object_field@node_content.referFieldBids");
        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
        tableConversionKey.setJsonObject(false);
        tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
        }
        tableConversionKeyRepository.save(tableConversionKey);

        tableConversionKey.setId(null);
        tableConversionKey.setTableCodeName("res_strategy_node");
        tableConversionKey.setTableCodeChinaName("节点表");
        tableConversionKey.setConversionKey("meta_object_field@node_content.valueConditions.conditionGroup.children.fieldBid");
        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
        tableConversionKey.setJsonObject(false);
        tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
        }
        tableConversionKeyRepository.save(tableConversionKey);

        tableConversionKey.setId(null);
        tableConversionKey.setTableCodeName("res_strategy_node");
        tableConversionKey.setTableCodeChinaName("节点表");
        tableConversionKey.setConversionKey("meta_object_field@node_content.valueConditions.referFieldBids");
        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
        tableConversionKey.setJsonObject(false);
        tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
        }
        tableConversionKeyRepository.save(tableConversionKey);

        tableConversionKey.setId(null);
        tableConversionKey.setTableCodeName("res_strategy_node");
        tableConversionKey.setTableCodeChinaName("节点表");
        tableConversionKey.setConversionKey("meta_object_field@node_content.params.value");
        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
        tableConversionKey.setJsonObject(false);
        tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
        }
        tableConversionKeyRepository.save(tableConversionKey);

        tableConversionKey.setId(null);
        tableConversionKey.setTableCodeName("res_strategy_node");
        tableConversionKey.setTableCodeChinaName("节点表");
        tableConversionKey.setConversionKey("meta_object_field@node_content.expressionUnits.code");
        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
        tableConversionKey.setJsonObject(false);
        tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
        }
        tableConversionKeyRepository.save(tableConversionKey);

//        tableConversionKey.setId(null);
//        tableConversionKey.setTableCodeName("res_strategy_node");
//        tableConversionKey.setTableCodeChinaName("节点表");
//        tableConversionKey.setConversionKey("meta_object_field@node_content.items.conditionField.deriveContent");
//        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
//        tableConversionKey.setJsonObject(false);
//        tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
//        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
//            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
//        }
//        tableConversionKeyRepository.save(tableConversionKey);
//

        tableConversionKey.setId(null);
        tableConversionKey.setTableCodeName("res_strategy_node");
        tableConversionKey.setTableCodeChinaName("节点表");
        tableConversionKey.setConversionKey("meta_object_field@node_content.hitOutputList.outField.expressionUnits.code");
        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
        tableConversionKey.setJsonObject(false);
        tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
        }
        tableConversionKeyRepository.save(tableConversionKey);


        tableConversionKey.setId(null);
        tableConversionKey.setTableCodeName("res_strategy_node");
        tableConversionKey.setTableCodeChinaName("节点表");
        tableConversionKey.setConversionKey("meta_object_field@node_content.hitOutputList.outField.referFieldBids");
        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
        tableConversionKey.setJsonObject(false);
        tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
        }
        tableConversionKeyRepository.save(tableConversionKey);


        tableConversionKey.setId(null);
        tableConversionKey.setTableCodeName("res_strategy_node");
        tableConversionKey.setTableCodeChinaName("节点表");
        tableConversionKey.setConversionKey("meta_object_field@node_content.hitOutputList.outField.referFields.bid");
        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
        tableConversionKey.setJsonObject(false);
        tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
        }
        tableConversionKeyRepository.save(tableConversionKey);


        tableConversionKey.setId(null);
        tableConversionKey.setTableCodeName("res_strategy_node");
        tableConversionKey.setTableCodeChinaName("节点表");
        tableConversionKey.setConversionKey("meta_object_field@node_content.hitOutputList.outField.referFields.fieldId");
        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
        tableConversionKey.setJsonObject(false);
        tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
        }
        tableConversionKeyRepository.save(tableConversionKey);

        tableConversionKey.setId(null);
        tableConversionKey.setTableCodeName("res_strategy_node");
        tableConversionKey.setTableCodeChinaName("节点表");
        tableConversionKey.setConversionKey("meta_object_field@node_content.hitOutputList.outField.valueConditions.conditionGroup.children.fieldBid");
        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
        tableConversionKey.setJsonObject(false);
        tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
        }
        tableConversionKeyRepository.save(tableConversionKey);


        tableConversionKey.setId(null);
        tableConversionKey.setTableCodeName("res_strategy_node");
        tableConversionKey.setTableCodeChinaName("节点表");
        tableConversionKey.setConversionKey("meta_object_field@node_content.hitOutputList.outField.valueConditions.expressionUnits.code");
        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
        tableConversionKey.setJsonObject(false);
        tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
        }
        tableConversionKeyRepository.save(tableConversionKey);


        tableConversionKey.setId(null);
        tableConversionKey.setTableCodeName("res_strategy_node");
        tableConversionKey.setTableCodeChinaName("节点表");
        tableConversionKey.setConversionKey("meta_object_field@node_content.queryParams.paramField.bid");
        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
        tableConversionKey.setJsonObject(false);
        tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
        }
        tableConversionKeyRepository.save(tableConversionKey);


        tableConversionKey.setId(null);
        tableConversionKey.setTableCodeName("res_strategy_node");
        tableConversionKey.setTableCodeChinaName("节点表");
        tableConversionKey.setConversionKey("meta_object_field@node_content.queryParams.paramField.fieldId");
        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
        tableConversionKey.setJsonObject(false);
        tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
        }
        tableConversionKeyRepository.save(tableConversionKey);


        tableConversionKey.setId(null);
        tableConversionKey.setTableCodeName("res_strategy_node");
        tableConversionKey.setTableCodeChinaName("节点表");
        tableConversionKey.setConversionKey("meta_data_object_info|meta_model_object_info|meta_topic_object_info@node_content.queryParams.paramField.resourceObjectId");
        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
        tableConversionKey.setJsonObject(false);
        tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
        }
        tableConversionKeyRepository.save(tableConversionKey);


        tableConversionKey.setId(null);
        tableConversionKey.setTableCodeName("res_strategy_node");
        tableConversionKey.setTableCodeChinaName("节点表");
        tableConversionKey.setConversionKey("meta_data_object_info|meta_model_object_info|meta_topic_object_info@node_content.hitOutputList.outField.referFields.resourceObjectId");
        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
        tableConversionKey.setJsonObject(false);
        tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
        }
        tableConversionKeyRepository.save(tableConversionKey);



        tableConversionKey.setId(null);
        tableConversionKey.setTableCodeName("res_strategy_node");
        tableConversionKey.setTableCodeChinaName("节点表");
        tableConversionKey.setConversionKey("meta_data_object_info|meta_model_object_info|meta_topic_object_info@node_content.eventModel.fields.referFields.resourceObjectId");
        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
        tableConversionKey.setJsonObject(false);
        tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
        }
        tableConversionKeyRepository.save(tableConversionKey);


        tableConversionKey.setId(null);
        tableConversionKey.setTableCodeName("res_strategy_node");
        tableConversionKey.setTableCodeChinaName("节点表");
        tableConversionKey.setConversionKey("meta_data_object_info|meta_model_object_info|meta_topic_object_info@node_content.eventModel.fields.resourceObjectId");
        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
        tableConversionKey.setJsonObject(false);
        tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
        }
        tableConversionKeyRepository.save(tableConversionKey);




        tableConversionKey.setId(null);
        tableConversionKey.setTableCodeName("res_strategy_node");
        tableConversionKey.setTableCodeChinaName("节点表");
        tableConversionKey.setConversionKey("meta_data_object_info|meta_model_object_info|meta_topic_object_info@node_content.eventModel.resourceId");
        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
        tableConversionKey.setJsonObject(false);
        tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
        }
        tableConversionKeyRepository.save(tableConversionKey);




        tableConversionKey.setId(null);
        tableConversionKey.setTableCodeName("res_strategy_node");
        tableConversionKey.setTableCodeChinaName("节点表");
        tableConversionKey.setConversionKey("meta_object_field@node_content.eventModel.fields.bid");
        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
        tableConversionKey.setJsonObject(false);
        tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
        }
        tableConversionKeyRepository.save(tableConversionKey);


        tableConversionKey.setId(null);
        tableConversionKey.setTableCodeName("res_strategy_node");
        tableConversionKey.setTableCodeChinaName("节点表");
        tableConversionKey.setConversionKey("meta_object_field@node_content.eventModel.fields.fieldId");
        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
        tableConversionKey.setJsonObject(false);
        tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
        }
        tableConversionKeyRepository.save(tableConversionKey);


        tableConversionKey.setId(null);
        tableConversionKey.setTableCodeName("res_strategy_node");
        tableConversionKey.setTableCodeChinaName("节点表");
        tableConversionKey.setConversionKey("meta_object_field@node_content.eventModel.fields.referFieldBids");
        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
        tableConversionKey.setJsonObject(false);
        tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
        }
        tableConversionKeyRepository.save(tableConversionKey);


        tableConversionKey.setId(null);
        tableConversionKey.setTableCodeName("res_strategy_node");
        tableConversionKey.setTableCodeChinaName("节点表");
        tableConversionKey.setConversionKey("meta_object_field@node_content.eventModel.fields.referFields.bid");
        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
        tableConversionKey.setJsonObject(false);
        tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
        }
        tableConversionKeyRepository.save(tableConversionKey);


        tableConversionKey.setId(null);
        tableConversionKey.setTableCodeName("res_strategy_node");
        tableConversionKey.setTableCodeChinaName("节点表");
        tableConversionKey.setConversionKey("meta_object_field@node_content.eventModel.fields.referFields.fieldId");
        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
        tableConversionKey.setJsonObject(false);
        tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
        }
        tableConversionKeyRepository.save(tableConversionKey);





        tableConversionKey.setId(null);
        tableConversionKey.setTableCodeName("res_strategy_node");
        tableConversionKey.setTableCodeChinaName("节点表");
        tableConversionKey.setConversionKey("meta_object_field@node_content.eventModel.fields.expressionUnits.code");
        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
        tableConversionKey.setJsonObject(false);
        tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
        }
        tableConversionKeyRepository.save(tableConversionKey);





        tableConversionKey.setId(null);
        tableConversionKey.setTableCodeName("res_strategy_node");
        tableConversionKey.setTableCodeChinaName("节点表");
        tableConversionKey.setConversionKey("meta_object_field@node_content.failLoad.expressionUnits.code");
        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
        tableConversionKey.setJsonObject(false);
        tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
        }
        tableConversionKeyRepository.save(tableConversionKey);




        tableConversionKey.setId(null);
        tableConversionKey.setTableCodeName("res_strategy_node");
        tableConversionKey.setTableCodeChinaName("节点表");
        tableConversionKey.setConversionKey("meta_object_field@node_content.failLoad.outField.bid");
        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
        tableConversionKey.setJsonObject(false);
        tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
        }
        tableConversionKeyRepository.save(tableConversionKey);

        tableConversionKey.setId(null);
        tableConversionKey.setTableCodeName("res_strategy_node");
        tableConversionKey.setTableCodeChinaName("节点表");
        tableConversionKey.setConversionKey("meta_object_field@node_content.successLoad.expressionUnits.code");
        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
        tableConversionKey.setJsonObject(false);
        tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
        }
        tableConversionKeyRepository.save(tableConversionKey);



        tableConversionKey.setId(null);
        tableConversionKey.setTableCodeName("res_strategy_node");
        tableConversionKey.setTableCodeChinaName("节点表");
        tableConversionKey.setConversionKey("meta_object_field@node_content.successLoad.outField.bid");
        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
        tableConversionKey.setJsonObject(false);
        tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
        }
        tableConversionKeyRepository.save(tableConversionKey);

        tableConversionKey.setId(null);
        tableConversionKey.setTableCodeName("res_strategy_node");
        tableConversionKey.setTableCodeChinaName("节点表");
        tableConversionKey.setConversionKey("meta_data_object_info|meta_model_object_info|meta_topic_object_info|res_strategy_info@node_content.successLoad.outField.resourceObjectId");
        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
        tableConversionKey.setJsonObject(false);
        tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
        }
        tableConversionKeyRepository.save(tableConversionKey);


        tableConversionKey.setId(null);
        tableConversionKey.setTableCodeName("res_strategy_node");
        tableConversionKey.setTableCodeChinaName("节点表");
        tableConversionKey.setConversionKey("meta_data_object_info|meta_model_object_info|meta_topic_object_info|res_strategy_info@node_content.failLoad.outField.resourceObjectId");
        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
        tableConversionKey.setJsonObject(false);
        tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
        }
        tableConversionKeyRepository.save(tableConversionKey);



        tableConversionKey.setId(null);
        tableConversionKey.setTableCodeName("res_strategy_node");
        tableConversionKey.setTableCodeChinaName("节点表");
        tableConversionKey.setConversionKey("meta_object_field@node_content.failLoad.expField");
        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
        tableConversionKey.setJsonObject(false);
        tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
        }
        tableConversionKeyRepository.save(tableConversionKey);

        tableConversionKey.setId(null);
        tableConversionKey.setTableCodeName("res_strategy_node");
        tableConversionKey.setTableCodeChinaName("节点表");
        tableConversionKey.setConversionKey("meta_object_field@node_content.successLoad.expField");
        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
        tableConversionKey.setJsonObject(false);
        tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
        }
        tableConversionKeyRepository.save(tableConversionKey);


        tableConversionKey.setId(null);
        tableConversionKey.setTableCodeName("res_strategy_node");
        tableConversionKey.setTableCodeChinaName("节点表");
        tableConversionKey.setConversionKey("meta_object_field@node_content.successLoad.outField.fieldId");
        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
        tableConversionKey.setJsonObject(false);
        tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
        }
        tableConversionKeyRepository.save(tableConversionKey);

        tableConversionKey.setId(null);
        tableConversionKey.setTableCodeName("res_strategy_node");
        tableConversionKey.setTableCodeChinaName("节点表");
        tableConversionKey.setConversionKey("meta_object_field@node_content.failLoad.outField.fieldId");
        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
        tableConversionKey.setJsonObject(false);
        tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
        }
        tableConversionKeyRepository.save(tableConversionKey);tableConversionKey.setId(null);
        tableConversionKey.setTableCodeName("res_strategy_node");
        tableConversionKey.setTableCodeChinaName("节点表");
        tableConversionKey.setConversionKey("meta_object_field@node_content.failLoad.outField.fieldId");
        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
        tableConversionKey.setJsonObject(false);
        tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
        }
        tableConversionKeyRepository.save(tableConversionKey);








        /**
         * 数据结构存在问题
         */
//        tableConversionKey.setId(null);
//        tableConversionKey.setTableCodeName("res_strategy_node");
//        tableConversionKey.setTableCodeChinaName("节点表");
//        tableConversionKey.setConversionKey("meta_function_info@node_content.function.resourceId");
//        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
//        tableConversionKey.setJsonObject(false);
//        tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
//        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
//            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
//        }
//        tableConversionKeyRepository.save(tableConversionKey);



/**
 * 版本弱化  一下配置暂时不用
 */
//        tableConversionKey.setId(null);
//        tableConversionKey.setTableCodeName("meta_object_field");
//        tableConversionKey.setTableCodeChinaName("字段表");
//        tableConversionKey.setConversionKey("meta_data_object|meta_model_object|meta_topic_object@derive_content.tableVersionId");
//        tableConversionKey.setHandleBeanName("defaultTableDataHandler");
//        tableConversionKey.setJsonObject(false);
//        tableConversionKey.setUsed(false);
//        tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());
//        if (null != tableConversionKeyByTableCodeNameAndConversionKey) {
//            tableConversionKey.setId(tableConversionKeyByTableCodeNameAndConversionKey.getId());
//        }
//        tableConversionKeyRepository.save(tableConversionKey);

    }


}