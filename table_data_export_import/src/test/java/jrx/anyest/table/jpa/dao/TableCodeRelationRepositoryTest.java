package jrx.anyest.table.jpa.dao;

import jrx.anyest.table.TableApplicationStart;
import jrx.anyest.table.config.TablePropertiesConfig;
import jrx.anyest.table.jpa.entity.TableCodeRelation;
import jrx.anyest.table.jpa.entity.TableConversionKey;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TableApplicationStart.class)
public class TableCodeRelationRepositoryTest {
    @Autowired
    private TableCodeRelationRepository tableCodeRelationRepository;

    @Autowired
    private TablePropertiesConfig tablePropertiesConfig;


    @Autowired
    private TableConversionKeyRepository tableConversionKeyRepository;

    @Test
    public void cond() {
        String datasource = tablePropertiesConfig.getDatasource();
        System.out.println(datasource);

    }

    @Test
    public void insert() {


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


        tableCodeRelation.setId(null);
        tableCodeRelation.setPrimaryCodeKey("category_id");
        tableCodeRelation.setPrimaryTableChinaName("规则集信息表");
        tableCodeRelation.setPrimaryTableName("res_rule_set_info");
        tableCodeRelation.setSlaveTableChinaName("分类表");
        tableCodeRelation.setSlaveTableName("meta_category");
        tableCodeRelation.setSlaveCodeKey("category_id");
        tableCodeRelation.setFilterHandleBean("defaultTableDataHandler");
        byPrimaryCodeKeyAndPrimaryTableNameAAndSlaveTableName = tableCodeRelationRepository.findByPrimaryCodeKeyAndPrimaryTableNameAndSlaveTableNameAndSlaveCodeKey(tableCodeRelation.getPrimaryCodeKey(), tableCodeRelation.getPrimaryTableName(), tableCodeRelation.getSlaveTableName(), tableCodeRelation.getSlaveCodeKey());
        if (null == byPrimaryCodeKeyAndPrimaryTableNameAAndSlaveTableName) {
            tableCodeRelationRepository.save(tableCodeRelation);
        }


        tableCodeRelation.setId(null);
        tableCodeRelation.setPrimaryCodeKey("resource_id");
        tableCodeRelation.setPrimaryTableChinaName("规则集信息表");
        tableCodeRelation.setPrimaryTableName("res_rule_set_info");
        tableCodeRelation.setSlaveTableChinaName("规则集版本表");
        tableCodeRelation.setSlaveTableName("res_rule_set");
        tableCodeRelation.setSlaveCodeKey("resource_id");
        tableCodeRelation.setFilterHandleBean("defaultTableDataHandler");
        byPrimaryCodeKeyAndPrimaryTableNameAAndSlaveTableName = tableCodeRelationRepository.findByPrimaryCodeKeyAndPrimaryTableNameAndSlaveTableNameAndSlaveCodeKey(tableCodeRelation.getPrimaryCodeKey(), tableCodeRelation.getPrimaryTableName(), tableCodeRelation.getSlaveTableName(), tableCodeRelation.getSlaveCodeKey());
        if (null == byPrimaryCodeKeyAndPrimaryTableNameAAndSlaveTableName) {
            tableCodeRelationRepository.save(tableCodeRelation);
        }


        tableCodeRelation.setId(null);
        tableCodeRelation.setPrimaryCodeKey("category_id");
        tableCodeRelation.setPrimaryTableChinaName("规则树信息表");
        tableCodeRelation.setPrimaryTableName("res_rule_tree_info");
        tableCodeRelation.setSlaveTableChinaName("分类表");
        tableCodeRelation.setSlaveTableName("meta_category");
        tableCodeRelation.setSlaveCodeKey("category_id");
        tableCodeRelation.setFilterHandleBean("defaultTableDataHandler");
        byPrimaryCodeKeyAndPrimaryTableNameAAndSlaveTableName = tableCodeRelationRepository.findByPrimaryCodeKeyAndPrimaryTableNameAndSlaveTableNameAndSlaveCodeKey(tableCodeRelation.getPrimaryCodeKey(), tableCodeRelation.getPrimaryTableName(), tableCodeRelation.getSlaveTableName(), tableCodeRelation.getSlaveCodeKey());
        if (null == byPrimaryCodeKeyAndPrimaryTableNameAAndSlaveTableName) {
            tableCodeRelationRepository.save(tableCodeRelation);
        }


        tableCodeRelation.setId(null);
        tableCodeRelation.setPrimaryCodeKey("resource_id");
        tableCodeRelation.setPrimaryTableChinaName("规则树信息表");
        tableCodeRelation.setPrimaryTableName("res_rule_tree_info");
        tableCodeRelation.setSlaveTableChinaName("规则树版本表");
        tableCodeRelation.setSlaveTableName("res_rule_tree");
        tableCodeRelation.setSlaveCodeKey("resource_id");
        tableCodeRelation.setFilterHandleBean("defaultTableDataHandler");
        byPrimaryCodeKeyAndPrimaryTableNameAAndSlaveTableName = tableCodeRelationRepository.findByPrimaryCodeKeyAndPrimaryTableNameAndSlaveTableNameAndSlaveCodeKey(tableCodeRelation.getPrimaryCodeKey(), tableCodeRelation.getPrimaryTableName(), tableCodeRelation.getSlaveTableName(), tableCodeRelation.getSlaveCodeKey());
        if (null == byPrimaryCodeKeyAndPrimaryTableNameAAndSlaveTableName) {
            tableCodeRelationRepository.save(tableCodeRelation);
        }


        tableCodeRelation.setId(null);
        tableCodeRelation.setPrimaryCodeKey("category_id");
        tableCodeRelation.setPrimaryTableChinaName("评分卡信息表");
        tableCodeRelation.setPrimaryTableName("res_score_card_info");
        tableCodeRelation.setSlaveTableChinaName("分类表");
        tableCodeRelation.setSlaveTableName("meta_category");
        tableCodeRelation.setSlaveCodeKey("category_id");
        tableCodeRelation.setFilterHandleBean("defaultTableDataHandler");
        byPrimaryCodeKeyAndPrimaryTableNameAAndSlaveTableName = tableCodeRelationRepository.findByPrimaryCodeKeyAndPrimaryTableNameAndSlaveTableNameAndSlaveCodeKey(tableCodeRelation.getPrimaryCodeKey(), tableCodeRelation.getPrimaryTableName(), tableCodeRelation.getSlaveTableName(), tableCodeRelation.getSlaveCodeKey());
        if (null == byPrimaryCodeKeyAndPrimaryTableNameAAndSlaveTableName) {
            tableCodeRelationRepository.save(tableCodeRelation);
        }


        tableCodeRelation.setId(null);
        tableCodeRelation.setPrimaryCodeKey("resource_id");
        tableCodeRelation.setPrimaryTableChinaName("评分卡信息表");
        tableCodeRelation.setPrimaryTableName("res_score_card_info");
        tableCodeRelation.setSlaveTableChinaName("评分卡版本表");
        tableCodeRelation.setSlaveTableName("res_score_card");
        tableCodeRelation.setSlaveCodeKey("resource_id");
        tableCodeRelation.setFilterHandleBean("defaultTableDataHandler");
        byPrimaryCodeKeyAndPrimaryTableNameAAndSlaveTableName = tableCodeRelationRepository.findByPrimaryCodeKeyAndPrimaryTableNameAndSlaveTableNameAndSlaveCodeKey(tableCodeRelation.getPrimaryCodeKey(), tableCodeRelation.getPrimaryTableName(), tableCodeRelation.getSlaveTableName(), tableCodeRelation.getSlaveCodeKey());
        if (null == byPrimaryCodeKeyAndPrimaryTableNameAAndSlaveTableName) {
            tableCodeRelationRepository.save(tableCodeRelation);
        }


        tableCodeRelation.setId(null);
        tableCodeRelation.setPrimaryCodeKey("category_id");
        tableCodeRelation.setPrimaryTableChinaName("脚本信息表");
        tableCodeRelation.setPrimaryTableName("res_script_info");
        tableCodeRelation.setSlaveTableChinaName("分类表");
        tableCodeRelation.setSlaveTableName("meta_category");
        tableCodeRelation.setSlaveCodeKey("category_id");
        tableCodeRelation.setFilterHandleBean("defaultTableDataHandler");
        byPrimaryCodeKeyAndPrimaryTableNameAAndSlaveTableName = tableCodeRelationRepository.findByPrimaryCodeKeyAndPrimaryTableNameAndSlaveTableNameAndSlaveCodeKey(tableCodeRelation.getPrimaryCodeKey(), tableCodeRelation.getPrimaryTableName(), tableCodeRelation.getSlaveTableName(), tableCodeRelation.getSlaveCodeKey());
        if (null == byPrimaryCodeKeyAndPrimaryTableNameAAndSlaveTableName) {
            tableCodeRelationRepository.save(tableCodeRelation);
        }


        tableCodeRelation.setId(null);
        tableCodeRelation.setPrimaryCodeKey("resource_id");
        tableCodeRelation.setPrimaryTableChinaName("脚本信息表");
        tableCodeRelation.setPrimaryTableName("res_script_info");
        tableCodeRelation.setSlaveTableChinaName("脚本版本表");
        tableCodeRelation.setSlaveTableName("res_script");
        tableCodeRelation.setSlaveCodeKey("resource_id");
        tableCodeRelation.setFilterHandleBean("defaultTableDataHandler");
        byPrimaryCodeKeyAndPrimaryTableNameAAndSlaveTableName = tableCodeRelationRepository.findByPrimaryCodeKeyAndPrimaryTableNameAndSlaveTableNameAndSlaveCodeKey(tableCodeRelation.getPrimaryCodeKey(), tableCodeRelation.getPrimaryTableName(), tableCodeRelation.getSlaveTableName(), tableCodeRelation.getSlaveCodeKey());
        if (null == byPrimaryCodeKeyAndPrimaryTableNameAAndSlaveTableName) {
            tableCodeRelationRepository.save(tableCodeRelation);
        }


        tableCodeRelation.setId(null);
        tableCodeRelation.setPrimaryCodeKey("category_id");
        tableCodeRelation.setPrimaryTableChinaName("策略信息表");
        tableCodeRelation.setPrimaryTableName("res_strategy_info");
        tableCodeRelation.setSlaveTableChinaName("分类表");
        tableCodeRelation.setSlaveTableName("meta_category");
        tableCodeRelation.setSlaveCodeKey("category_id");
        tableCodeRelation.setFilterHandleBean("defaultTableDataHandler");
        byPrimaryCodeKeyAndPrimaryTableNameAAndSlaveTableName = tableCodeRelationRepository.findByPrimaryCodeKeyAndPrimaryTableNameAndSlaveTableNameAndSlaveCodeKey(tableCodeRelation.getPrimaryCodeKey(), tableCodeRelation.getPrimaryTableName(), tableCodeRelation.getSlaveTableName(), tableCodeRelation.getSlaveCodeKey());
        if (null == byPrimaryCodeKeyAndPrimaryTableNameAAndSlaveTableName) {
            tableCodeRelationRepository.save(tableCodeRelation);
        }


        tableCodeRelation.setId(null);
        tableCodeRelation.setPrimaryCodeKey("resource_id");
        tableCodeRelation.setPrimaryTableChinaName("策略信息表");
        tableCodeRelation.setPrimaryTableName("res_strategy_info");
        tableCodeRelation.setSlaveTableChinaName("策略版本表");
        tableCodeRelation.setSlaveTableName("res_strategy");
        tableCodeRelation.setSlaveCodeKey("resource_id");
        tableCodeRelation.setFilterHandleBean("defaultTableDataHandler");
        byPrimaryCodeKeyAndPrimaryTableNameAAndSlaveTableName = tableCodeRelationRepository.findByPrimaryCodeKeyAndPrimaryTableNameAndSlaveTableNameAndSlaveCodeKey(tableCodeRelation.getPrimaryCodeKey(), tableCodeRelation.getPrimaryTableName(), tableCodeRelation.getSlaveTableName(), tableCodeRelation.getSlaveCodeKey());
        if (null == byPrimaryCodeKeyAndPrimaryTableNameAAndSlaveTableName) {
            tableCodeRelationRepository.save(tableCodeRelation);
        }


        /**
         * 策略指标
         */
        tableCodeRelation.setId(null);
        tableCodeRelation.setPrimaryCodeKey("resource_id");
        tableCodeRelation.setPrimaryTableChinaName("策略信息表");
        tableCodeRelation.setPrimaryTableName("res_strategy_info");
        tableCodeRelation.setSlaveTableChinaName("策略版本表");
        tableCodeRelation.setSlaveTableName("meta_topic_object_info");
        tableCodeRelation.setSlaveCodeKey("category_id");
        tableCodeRelation.setFilterHandleBean("defaultTableDataHandler");
        byPrimaryCodeKeyAndPrimaryTableNameAAndSlaveTableName = tableCodeRelationRepository.findByPrimaryCodeKeyAndPrimaryTableNameAndSlaveTableNameAndSlaveCodeKey(tableCodeRelation.getPrimaryCodeKey(), tableCodeRelation.getPrimaryTableName(), tableCodeRelation.getSlaveTableName(), tableCodeRelation.getSlaveCodeKey());
        if (null == byPrimaryCodeKeyAndPrimaryTableNameAAndSlaveTableName) {
            tableCodeRelationRepository.save(tableCodeRelation);
        }

        tableCodeRelation.setId(null);
        tableCodeRelation.setPrimaryCodeKey("strategy_id");
        tableCodeRelation.setPrimaryTableChinaName("策略信息表");
        tableCodeRelation.setPrimaryTableName("res_strategy");
        tableCodeRelation.setSlaveTableChinaName("字段表");
        tableCodeRelation.setSlaveTableName("meta_object_field");
        tableCodeRelation.setSlaveCodeKey("resource_object_version_id");
        tableCodeRelation.setFilterHandleBean("defaultTableDataHandler");
        byPrimaryCodeKeyAndPrimaryTableNameAAndSlaveTableName = tableCodeRelationRepository.findByPrimaryCodeKeyAndPrimaryTableNameAndSlaveTableNameAndSlaveCodeKey(tableCodeRelation.getPrimaryCodeKey(), tableCodeRelation.getPrimaryTableName(), tableCodeRelation.getSlaveTableName(), tableCodeRelation.getSlaveCodeKey());
        if (null == byPrimaryCodeKeyAndPrimaryTableNameAAndSlaveTableName) {
            tableCodeRelationRepository.save(tableCodeRelation);
        }


        tableCodeRelation.setId(null);
        tableCodeRelation.setPrimaryCodeKey("strategy_id");
        tableCodeRelation.setPrimaryTableChinaName("策略版本表");
        tableCodeRelation.setPrimaryTableName("res_strategy");
        tableCodeRelation.setSlaveTableChinaName("策略节点表");
        tableCodeRelation.setSlaveTableName("res_strategy_node");
        tableCodeRelation.setSlaveCodeKey("strategy_id");
        tableCodeRelation.setFilterHandleBean("defaultTableDataHandler");
        byPrimaryCodeKeyAndPrimaryTableNameAAndSlaveTableName = tableCodeRelationRepository.findByPrimaryCodeKeyAndPrimaryTableNameAndSlaveTableNameAndSlaveCodeKey(tableCodeRelation.getPrimaryCodeKey(), tableCodeRelation.getPrimaryTableName(), tableCodeRelation.getSlaveTableName(), tableCodeRelation.getSlaveCodeKey());
        if (null == byPrimaryCodeKeyAndPrimaryTableNameAAndSlaveTableName) {
            tableCodeRelationRepository.save(tableCodeRelation);
        }

        tableCodeRelation.setId(null);
        tableCodeRelation.setPrimaryCodeKey("strategy_id");
        tableCodeRelation.setPrimaryTableChinaName("策略版本表");
        tableCodeRelation.setPrimaryTableName("res_strategy");
        tableCodeRelation.setSlaveTableChinaName("节点连线表");
        tableCodeRelation.setSlaveTableName("res_strategy_node_link");
        tableCodeRelation.setSlaveCodeKey("strategy_id");
        tableCodeRelation.setFilterHandleBean("defaultTableDataHandler");
        byPrimaryCodeKeyAndPrimaryTableNameAAndSlaveTableName = tableCodeRelationRepository.findByPrimaryCodeKeyAndPrimaryTableNameAndSlaveTableNameAndSlaveCodeKey(tableCodeRelation.getPrimaryCodeKey(), tableCodeRelation.getPrimaryTableName(), tableCodeRelation.getSlaveTableName(), tableCodeRelation.getSlaveCodeKey());
        if (null == byPrimaryCodeKeyAndPrimaryTableNameAAndSlaveTableName) {
            tableCodeRelationRepository.save(tableCodeRelation);
        }


        tableCodeRelation.setId(null);
        tableCodeRelation.setPrimaryCodeKey("category_id");
        tableCodeRelation.setPrimaryTableChinaName("矩阵信息表");
        tableCodeRelation.setPrimaryTableName("res_matrix_info");
        tableCodeRelation.setSlaveTableChinaName("分类表");
        tableCodeRelation.setSlaveTableName("meta_category");
        tableCodeRelation.setSlaveCodeKey("category_id");
        tableCodeRelation.setFilterHandleBean("defaultTableDataHandler");
        byPrimaryCodeKeyAndPrimaryTableNameAAndSlaveTableName = tableCodeRelationRepository.findByPrimaryCodeKeyAndPrimaryTableNameAndSlaveTableNameAndSlaveCodeKey(tableCodeRelation.getPrimaryCodeKey(), tableCodeRelation.getPrimaryTableName(), tableCodeRelation.getSlaveTableName(), tableCodeRelation.getSlaveCodeKey());
        if (null == byPrimaryCodeKeyAndPrimaryTableNameAAndSlaveTableName) {
            tableCodeRelationRepository.save(tableCodeRelation);
        }


        tableCodeRelation.setId(null);
        tableCodeRelation.setPrimaryCodeKey("resource_id");
        tableCodeRelation.setPrimaryTableChinaName("规则集信息表");
        tableCodeRelation.setPrimaryTableName("res_matrix_info");
        tableCodeRelation.setSlaveTableChinaName("矩阵版本表");
        tableCodeRelation.setSlaveTableName("res_matrix");
        tableCodeRelation.setSlaveCodeKey("resource_id");
        tableCodeRelation.setFilterHandleBean("defaultTableDataHandler");
        byPrimaryCodeKeyAndPrimaryTableNameAAndSlaveTableName = tableCodeRelationRepository.findByPrimaryCodeKeyAndPrimaryTableNameAndSlaveTableNameAndSlaveCodeKey(tableCodeRelation.getPrimaryCodeKey(), tableCodeRelation.getPrimaryTableName(), tableCodeRelation.getSlaveTableName(), tableCodeRelation.getSlaveCodeKey());
        if (null == byPrimaryCodeKeyAndPrimaryTableNameAAndSlaveTableName) {
            tableCodeRelationRepository.save(tableCodeRelation);
        }


        tableCodeRelation.setId(null);
        tableCodeRelation.setPrimaryCodeKey("resource_object_category_id");
        tableCodeRelation.setPrimaryTableChinaName("字段表");
        tableCodeRelation.setPrimaryTableName("meta_object_field");
        tableCodeRelation.setSlaveTableChinaName("数据集版本表");
        tableCodeRelation.setSlaveTableName("meta_category");
        tableCodeRelation.setSlaveCodeKey("category_id");
        tableCodeRelation.setFilterHandleBean("defaultTableDataHandler");
        byPrimaryCodeKeyAndPrimaryTableNameAAndSlaveTableName = tableCodeRelationRepository.findByPrimaryCodeKeyAndPrimaryTableNameAndSlaveTableNameAndSlaveCodeKey(tableCodeRelation.getPrimaryCodeKey(), tableCodeRelation.getPrimaryTableName(), tableCodeRelation.getSlaveTableName(), tableCodeRelation.getSlaveCodeKey());
        if (null == byPrimaryCodeKeyAndPrimaryTableNameAAndSlaveTableName) {
            tableCodeRelationRepository.save(tableCodeRelation);
        }


        /**
         * 模型对象导出
         */

        tableCodeRelation.setId(null);
        tableCodeRelation.setPrimaryCodeKey("resource_id");
        tableCodeRelation.setPrimaryTableChinaName("数据集信息表");
        tableCodeRelation.setPrimaryTableName("meta_data_object_info");
        tableCodeRelation.setSlaveTableChinaName("数据集版本表");
        tableCodeRelation.setSlaveTableName("meta_data_object");
        tableCodeRelation.setSlaveCodeKey("resource_id");
        tableCodeRelation.setFilterHandleBean("defaultTableDataHandler");
        byPrimaryCodeKeyAndPrimaryTableNameAAndSlaveTableName = tableCodeRelationRepository.findByPrimaryCodeKeyAndPrimaryTableNameAndSlaveTableNameAndSlaveCodeKey(tableCodeRelation.getPrimaryCodeKey(), tableCodeRelation.getPrimaryTableName(), tableCodeRelation.getSlaveTableName(), tableCodeRelation.getSlaveCodeKey());
        if (null == byPrimaryCodeKeyAndPrimaryTableNameAAndSlaveTableName) {
            tableCodeRelationRepository.save(tableCodeRelation);
        }
        tableCodeRelation.setId(null);
        tableCodeRelation.setPrimaryCodeKey("resource_id");
        tableCodeRelation.setPrimaryTableChinaName("数据集信息表");
        tableCodeRelation.setPrimaryTableName("meta_data_object_info");
        tableCodeRelation.setSlaveTableChinaName("字段表");
        tableCodeRelation.setSlaveTableName("meta_object_field");
        tableCodeRelation.setSlaveCodeKey("resource_object_id");
        tableCodeRelation.setFilterHandleBean("defaultTableDataHandler");
        byPrimaryCodeKeyAndPrimaryTableNameAAndSlaveTableName = tableCodeRelationRepository.findByPrimaryCodeKeyAndPrimaryTableNameAndSlaveTableNameAndSlaveCodeKey(tableCodeRelation.getPrimaryCodeKey(), tableCodeRelation.getPrimaryTableName(), tableCodeRelation.getSlaveTableName(), tableCodeRelation.getSlaveCodeKey());
        if (null == byPrimaryCodeKeyAndPrimaryTableNameAAndSlaveTableName) {
            tableCodeRelationRepository.save(tableCodeRelation);
        }


        tableCodeRelation.setId(null);
        tableCodeRelation.setPrimaryCodeKey("category_id");
        tableCodeRelation.setPrimaryTableChinaName("数据集信息表");
        tableCodeRelation.setPrimaryTableName("meta_data_object_info");
        tableCodeRelation.setSlaveTableChinaName("分类表");
        tableCodeRelation.setSlaveTableName("meta_category");
        tableCodeRelation.setSlaveCodeKey("category_id");
        tableCodeRelation.setFilterHandleBean("defaultTableDataHandler");
        byPrimaryCodeKeyAndPrimaryTableNameAAndSlaveTableName = tableCodeRelationRepository.findByPrimaryCodeKeyAndPrimaryTableNameAndSlaveTableNameAndSlaveCodeKey(tableCodeRelation.getPrimaryCodeKey(), tableCodeRelation.getPrimaryTableName(), tableCodeRelation.getSlaveTableName(), tableCodeRelation.getSlaveCodeKey());
        if (null == byPrimaryCodeKeyAndPrimaryTableNameAAndSlaveTableName) {
            tableCodeRelationRepository.save(tableCodeRelation);
        }
        tableCodeRelation.setId(null);
        tableCodeRelation.setPrimaryCodeKey("data_source_id");
        tableCodeRelation.setPrimaryTableChinaName("数据集信息表");
        tableCodeRelation.setPrimaryTableName("meta_data_object_info");
        tableCodeRelation.setSlaveTableChinaName("数据源表");
        tableCodeRelation.setSlaveTableName("meta_data_source_info");
        tableCodeRelation.setSlaveCodeKey("data_source_id");
        tableCodeRelation.setFilterHandleBean("defaultTableDataHandler");
        byPrimaryCodeKeyAndPrimaryTableNameAAndSlaveTableName = tableCodeRelationRepository.findByPrimaryCodeKeyAndPrimaryTableNameAndSlaveTableNameAndSlaveCodeKey(tableCodeRelation.getPrimaryCodeKey(), tableCodeRelation.getPrimaryTableName(), tableCodeRelation.getSlaveTableName(), tableCodeRelation.getSlaveCodeKey());
        if (null == byPrimaryCodeKeyAndPrimaryTableNameAAndSlaveTableName) {
            tableCodeRelationRepository.save(tableCodeRelation);
        }


        tableCodeRelation.setId(null);
        tableCodeRelation.setPrimaryCodeKey("resource_id");
        tableCodeRelation.setPrimaryTableChinaName("事件对象信息表");
        tableCodeRelation.setPrimaryTableName("meta_model_object_info");
        tableCodeRelation.setSlaveTableChinaName("事件对象版本表");
        tableCodeRelation.setSlaveTableName("meta_model_object");
        tableCodeRelation.setSlaveCodeKey("resource_id");
        tableCodeRelation.setFilterHandleBean("defaultTableDataHandler");
        byPrimaryCodeKeyAndPrimaryTableNameAAndSlaveTableName = tableCodeRelationRepository.findByPrimaryCodeKeyAndPrimaryTableNameAndSlaveTableNameAndSlaveCodeKey(tableCodeRelation.getPrimaryCodeKey(), tableCodeRelation.getPrimaryTableName(), tableCodeRelation.getSlaveTableName(), tableCodeRelation.getSlaveCodeKey());
        if (null == byPrimaryCodeKeyAndPrimaryTableNameAAndSlaveTableName) {
            tableCodeRelationRepository.save(tableCodeRelation);
        }
        tableCodeRelation.setId(null);
        tableCodeRelation.setPrimaryCodeKey("resource_id");
        tableCodeRelation.setPrimaryTableChinaName("事件对象信息表");
        tableCodeRelation.setPrimaryTableName("meta_model_object_info");
        tableCodeRelation.setSlaveTableChinaName("字段表");
        tableCodeRelation.setSlaveTableName("meta_object_field");
        tableCodeRelation.setSlaveCodeKey("resource_object_id");
        tableCodeRelation.setFilterHandleBean("defaultTableDataHandler");
        byPrimaryCodeKeyAndPrimaryTableNameAAndSlaveTableName = tableCodeRelationRepository.findByPrimaryCodeKeyAndPrimaryTableNameAndSlaveTableNameAndSlaveCodeKey(tableCodeRelation.getPrimaryCodeKey(), tableCodeRelation.getPrimaryTableName(), tableCodeRelation.getSlaveTableName(), tableCodeRelation.getSlaveCodeKey());
        if (null == byPrimaryCodeKeyAndPrimaryTableNameAAndSlaveTableName) {
            tableCodeRelationRepository.save(tableCodeRelation);
        }

        tableCodeRelation.setId(null);
        tableCodeRelation.setPrimaryCodeKey("category_id");
        tableCodeRelation.setPrimaryTableChinaName("事件对象信息表");
        tableCodeRelation.setPrimaryTableName("meta_model_object_info");
        tableCodeRelation.setSlaveTableChinaName("分类表");
        tableCodeRelation.setSlaveTableName("meta_category");
        tableCodeRelation.setSlaveCodeKey("category_id");
        tableCodeRelation.setFilterHandleBean("defaultTableDataHandler");
        byPrimaryCodeKeyAndPrimaryTableNameAAndSlaveTableName = tableCodeRelationRepository.findByPrimaryCodeKeyAndPrimaryTableNameAndSlaveTableNameAndSlaveCodeKey(tableCodeRelation.getPrimaryCodeKey(), tableCodeRelation.getPrimaryTableName(), tableCodeRelation.getSlaveTableName(), tableCodeRelation.getSlaveCodeKey());
        if (null == byPrimaryCodeKeyAndPrimaryTableNameAAndSlaveTableName) {
            tableCodeRelationRepository.save(tableCodeRelation);
        }

        System.out.println("**********************meta_topic_object_info********************************");

        tableCodeRelation.setId(null);
        tableCodeRelation.setPrimaryCodeKey("resource_id");
        tableCodeRelation.setPrimaryTableChinaName("统计模型信息表");
        tableCodeRelation.setPrimaryTableName("meta_topic_object_info");
        tableCodeRelation.setSlaveTableChinaName("统计模型版本表");
        tableCodeRelation.setSlaveTableName("meta_topic_object");
        tableCodeRelation.setSlaveCodeKey("resource_id");
        tableCodeRelation.setFilterHandleBean("defaultTableDataHandler");
        byPrimaryCodeKeyAndPrimaryTableNameAAndSlaveTableName = tableCodeRelationRepository.findByPrimaryCodeKeyAndPrimaryTableNameAndSlaveTableNameAndSlaveCodeKey(tableCodeRelation.getPrimaryCodeKey(), tableCodeRelation.getPrimaryTableName(), tableCodeRelation.getSlaveTableName(), tableCodeRelation.getSlaveCodeKey());
        if (null == byPrimaryCodeKeyAndPrimaryTableNameAAndSlaveTableName) {
            tableCodeRelationRepository.save(tableCodeRelation);
        }
        tableCodeRelation.setId(null);
        tableCodeRelation.setPrimaryCodeKey("resource_id");
        tableCodeRelation.setPrimaryTableChinaName("统计模型信息表");
        tableCodeRelation.setPrimaryTableName("meta_topic_object_info");
        tableCodeRelation.setSlaveTableChinaName("字段表");
        tableCodeRelation.setSlaveTableName("meta_object_field");
        tableCodeRelation.setSlaveCodeKey("resource_object_id");
        tableCodeRelation.setFilterHandleBean("defaultTableDataHandler");
        byPrimaryCodeKeyAndPrimaryTableNameAAndSlaveTableName = tableCodeRelationRepository.findByPrimaryCodeKeyAndPrimaryTableNameAndSlaveTableNameAndSlaveCodeKey(tableCodeRelation.getPrimaryCodeKey(), tableCodeRelation.getPrimaryTableName(), tableCodeRelation.getSlaveTableName(), tableCodeRelation.getSlaveCodeKey());
        if (null == byPrimaryCodeKeyAndPrimaryTableNameAAndSlaveTableName) {
            tableCodeRelationRepository.save(tableCodeRelation);
        }

        tableCodeRelation.setId(null);
        tableCodeRelation.setPrimaryCodeKey("category_id");
        tableCodeRelation.setPrimaryTableChinaName("统计模型信息表");
        tableCodeRelation.setPrimaryTableName("meta_topic_object_info");
        tableCodeRelation.setSlaveTableChinaName("分类表");
        tableCodeRelation.setSlaveTableName("meta_category");
        tableCodeRelation.setSlaveCodeKey("category_id");
        tableCodeRelation.setFilterHandleBean("defaultTableDataHandler");
        byPrimaryCodeKeyAndPrimaryTableNameAAndSlaveTableName = tableCodeRelationRepository.findByPrimaryCodeKeyAndPrimaryTableNameAndSlaveTableNameAndSlaveCodeKey(tableCodeRelation.getPrimaryCodeKey(), tableCodeRelation.getPrimaryTableName(), tableCodeRelation.getSlaveTableName(), tableCodeRelation.getSlaveCodeKey());
        if (null == byPrimaryCodeKeyAndPrimaryTableNameAAndSlaveTableName) {
            tableCodeRelationRepository.save(tableCodeRelation);
        }
        tableCodeRelation.setId(null);
        tableCodeRelation.setPrimaryCodeKey("resource_id");
        tableCodeRelation.setPrimaryTableChinaName("统计模型信息表");
        tableCodeRelation.setPrimaryTableName("meta_topic_object_info");
        tableCodeRelation.setSlaveTableChinaName("统计模型关系表");
        tableCodeRelation.setSlaveTableName("meta_topic_object_relation");
        tableCodeRelation.setSlaveCodeKey("topic_object_info_id");
        tableCodeRelation.setFilterHandleBean("defaultTableDataHandler");
        byPrimaryCodeKeyAndPrimaryTableNameAAndSlaveTableName = tableCodeRelationRepository.findByPrimaryCodeKeyAndPrimaryTableNameAndSlaveTableNameAndSlaveCodeKey(tableCodeRelation.getPrimaryCodeKey(), tableCodeRelation.getPrimaryTableName(), tableCodeRelation.getSlaveTableName(), tableCodeRelation.getSlaveCodeKey());
        if (null == byPrimaryCodeKeyAndPrimaryTableNameAAndSlaveTableName) {
            tableCodeRelationRepository.save(tableCodeRelation);
        }
        tableCodeRelation.setId(null);
        tableCodeRelation.setPrimaryCodeKey("data_source_id");
        tableCodeRelation.setPrimaryTableChinaName("统计模型信息表");
        tableCodeRelation.setPrimaryTableName("meta_topic_object_info");
        tableCodeRelation.setSlaveTableChinaName("数据源信息表");
        tableCodeRelation.setSlaveTableName("meta_data_source_info");
        tableCodeRelation.setSlaveCodeKey("data_source_id");
        tableCodeRelation.setFilterHandleBean("defaultTableDataHandler");
        byPrimaryCodeKeyAndPrimaryTableNameAAndSlaveTableName = tableCodeRelationRepository.findByPrimaryCodeKeyAndPrimaryTableNameAndSlaveTableNameAndSlaveCodeKey(tableCodeRelation.getPrimaryCodeKey(), tableCodeRelation.getPrimaryTableName(), tableCodeRelation.getSlaveTableName(), tableCodeRelation.getSlaveCodeKey());
        if (null == byPrimaryCodeKeyAndPrimaryTableNameAAndSlaveTableName) {
            tableCodeRelationRepository.save(tableCodeRelation);
        }


        tableCodeRelation.setId(null);
        tableCodeRelation.setPrimaryCodeKey("primary_resource_id");
        tableCodeRelation.setPrimaryTableChinaName("统计模型信息表");
        tableCodeRelation.setPrimaryTableName("meta_topic_object_info");
        tableCodeRelation.setSlaveTableChinaName("数据集信息表");
//        tableCodeRelation.setSlaveTableName("meta_data_object_info|meta_model_object_info|meta_topic_object_info");
        tableCodeRelation.setSlaveTableName("meta_data_object_info");
        tableCodeRelation.setSlaveCodeKey("resource_id");
        tableCodeRelation.setFilterHandleBean("defaultTableDataHandler");
        byPrimaryCodeKeyAndPrimaryTableNameAAndSlaveTableName = tableCodeRelationRepository.findByPrimaryCodeKeyAndPrimaryTableNameAndSlaveTableNameAndSlaveCodeKey(tableCodeRelation.getPrimaryCodeKey(), tableCodeRelation.getPrimaryTableName(), tableCodeRelation.getSlaveTableName(), tableCodeRelation.getSlaveCodeKey());
        if (null == byPrimaryCodeKeyAndPrimaryTableNameAAndSlaveTableName) {
            tableCodeRelationRepository.save(tableCodeRelation);
        }

        tableCodeRelation.setId(null);
        tableCodeRelation.setPrimaryCodeKey("category_id");
        tableCodeRelation.setPrimaryTableChinaName("统计模型版本表");
        tableCodeRelation.setPrimaryTableName("meta_topic_object");
        tableCodeRelation.setSlaveTableChinaName("分类表");
        tableCodeRelation.setSlaveTableName("meta_category");
        tableCodeRelation.setSlaveCodeKey("category_id");
        tableCodeRelation.setFilterHandleBean("defaultTableDataHandler");
        byPrimaryCodeKeyAndPrimaryTableNameAAndSlaveTableName = tableCodeRelationRepository.findByPrimaryCodeKeyAndPrimaryTableNameAndSlaveTableNameAndSlaveCodeKey(tableCodeRelation.getPrimaryCodeKey(), tableCodeRelation.getPrimaryTableName(), tableCodeRelation.getSlaveTableName(), tableCodeRelation.getSlaveCodeKey());
        if (null == byPrimaryCodeKeyAndPrimaryTableNameAAndSlaveTableName) {
            tableCodeRelationRepository.save(tableCodeRelation);
        }


//        meta_topic_object_relation

        tableCodeRelation.setId(null);
        tableCodeRelation.setPrimaryCodeKey("primary_resource_id");
        tableCodeRelation.setPrimaryTableChinaName("统计模型关系表");
        tableCodeRelation.setPrimaryTableName("meta_topic_object_relation");
        tableCodeRelation.setSlaveTableChinaName("对象信息表");
//        tableCodeRelation.setSlaveTableName("meta_data_object_info|meta_model_object_info|meta_topic_object_info");
        tableCodeRelation.setSlaveTableName("meta_data_object_info");
        tableCodeRelation.setSlaveCodeKey("resource_id");
        tableCodeRelation.setFilterHandleBean("defaultTableDataHandler");
        byPrimaryCodeKeyAndPrimaryTableNameAAndSlaveTableName = tableCodeRelationRepository.findByPrimaryCodeKeyAndPrimaryTableNameAndSlaveTableNameAndSlaveCodeKey(tableCodeRelation.getPrimaryCodeKey(), tableCodeRelation.getPrimaryTableName(), tableCodeRelation.getSlaveTableName(), tableCodeRelation.getSlaveCodeKey());
        if (null == byPrimaryCodeKeyAndPrimaryTableNameAAndSlaveTableName) {
            tableCodeRelationRepository.save(tableCodeRelation);
        }

        tableCodeRelation.setId(null);
        tableCodeRelation.setPrimaryCodeKey("relation_object_id");
        tableCodeRelation.setPrimaryTableChinaName("统计模型关系表");
        tableCodeRelation.setPrimaryTableName("meta_topic_object_relation");
        tableCodeRelation.setSlaveTableChinaName("对象信息表");
//        tableCodeRelation.setSlaveTableName("meta_data_object_info|meta_model_object_info|meta_topic_object_info");
        tableCodeRelation.setSlaveTableName("meta_data_object_info");
        tableCodeRelation.setSlaveCodeKey("resource_id");
        tableCodeRelation.setFilterHandleBean("defaultTableDataHandler");
        byPrimaryCodeKeyAndPrimaryTableNameAAndSlaveTableName = tableCodeRelationRepository.findByPrimaryCodeKeyAndPrimaryTableNameAndSlaveTableNameAndSlaveCodeKey(tableCodeRelation.getPrimaryCodeKey(), tableCodeRelation.getPrimaryTableName(), tableCodeRelation.getSlaveTableName(), tableCodeRelation.getSlaveCodeKey());
        if (null == byPrimaryCodeKeyAndPrimaryTableNameAAndSlaveTableName) {
            tableCodeRelationRepository.save(tableCodeRelation);
        }

        tableCodeRelation.setId(null);
        tableCodeRelation.setPrimaryCodeKey("resource_id");
        tableCodeRelation.setPrimaryTableChinaName("函数信息表");
        tableCodeRelation.setPrimaryTableName("meta_function_info");
        tableCodeRelation.setSlaveTableChinaName("对象信息表");
//        tableCodeRelation.setSlaveTableName("meta_data_object_info|meta_model_object_info|meta_topic_object_info");
        tableCodeRelation.setSlaveTableName("meta_function");
        tableCodeRelation.setSlaveCodeKey("resource_id");
        tableCodeRelation.setFilterHandleBean("defaultTableDataHandler");
        byPrimaryCodeKeyAndPrimaryTableNameAAndSlaveTableName = tableCodeRelationRepository.findByPrimaryCodeKeyAndPrimaryTableNameAndSlaveTableNameAndSlaveCodeKey(tableCodeRelation.getPrimaryCodeKey(), tableCodeRelation.getPrimaryTableName(), tableCodeRelation.getSlaveTableName(), tableCodeRelation.getSlaveCodeKey());
        if (null == byPrimaryCodeKeyAndPrimaryTableNameAAndSlaveTableName) {
            tableCodeRelationRepository.save(tableCodeRelation);
        }


        tableCodeRelation.setId(null);
        tableCodeRelation.setPrimaryCodeKey("node_content.ruleItemList.resourceId");
        tableCodeRelation.setPrimaryTableChinaName("函数信息表");
        tableCodeRelation.setPrimaryTableName("res_strategy_node");
        tableCodeRelation.setSlaveTableChinaName("对象信息表");
//        tableCodeRelation.setSlaveTableName("meta_data_object_info|meta_model_object_info|meta_topic_object_info");
        tableCodeRelation.setSlaveTableName("res_rule_info");
        tableCodeRelation.setSlaveCodeKey("resource_id");
        tableCodeRelation.setFilterHandleBean("defaultTableDataHandler");
        byPrimaryCodeKeyAndPrimaryTableNameAAndSlaveTableName = tableCodeRelationRepository.findByPrimaryCodeKeyAndPrimaryTableNameAndSlaveTableNameAndSlaveCodeKey(tableCodeRelation.getPrimaryCodeKey(), tableCodeRelation.getPrimaryTableName(), tableCodeRelation.getSlaveTableName(), tableCodeRelation.getSlaveCodeKey());
        if (null == byPrimaryCodeKeyAndPrimaryTableNameAAndSlaveTableName) {
            tableCodeRelationRepository.save(tableCodeRelation);
        }


    }

    @Test
    public void name() {

        /*---------------------------------------------------------------------------------/
           对conversion_key做relation转换
        /---------------------------------------------------------------------------------*/
        List<TableConversionKey> conversionKeyList = tableConversionKeyRepository.findConverRelation();
//        tableConversionKeyByTableCodeNameAndConversionKey = tableConversionKeyRepository.findTableConversionKeyByTableCodeNameAndConversionKey(tableConversionKey.getTableCodeName(), tableConversionKey.getConversionKey());

        conversionKeyList.forEach(e->{
            String conversionKey = e.getConversionKey();
            String[] split = conversionKey.split("@");
            String primaryCode=split[1];
            String slaveTableNames=split[0];
            TableCodeRelation tableCodeRelation = new TableCodeRelation();
            tableCodeRelation.setPrimaryCodeKey(primaryCode);
            tableCodeRelation.setPrimaryTableChinaName("级联");
            tableCodeRelation.setPrimaryTableName("res_strategy_node");
            tableCodeRelation.setSlaveTableChinaName("级联");
            tableCodeRelation.setSlaveTableName(slaveTableNames);
            tableCodeRelation.setSlaveCodeKey("resource_id");
            tableCodeRelation.setFilterHandleBean("defaultTableDataHandler");
            TableCodeRelation byPrimaryCodeKeyAndPrimaryTableNameAAndSlaveTableName = tableCodeRelationRepository.findByPrimaryCodeKeyAndPrimaryTableNameAndSlaveTableNameAndSlaveCodeKey(tableCodeRelation.getPrimaryCodeKey(), tableCodeRelation.getPrimaryTableName(), tableCodeRelation.getSlaveTableName(), tableCodeRelation.getSlaveCodeKey());
            if (null == byPrimaryCodeKeyAndPrimaryTableNameAAndSlaveTableName) {
                tableCodeRelationRepository.save(tableCodeRelation);
            }
        });


    }
}