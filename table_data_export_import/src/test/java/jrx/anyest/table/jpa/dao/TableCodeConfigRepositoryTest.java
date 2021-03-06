package jrx.anyest.table.jpa.dao;

import jrx.anyest.table.TableApplicationStart;
import jrx.anyest.table.jpa.entity.TableCodeConfig;
import jrx.anyest.table.service.TableDataHandler;
import jrx.anyest.table.utils.TableSpringUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.Date;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TableApplicationStart.class)
public class TableCodeConfigRepositoryTest {
    @Autowired
    private TableCodeConfigRepository tableCodeConfigRepository;


    @Test
    public void nameArray() {
        String name = "name,project_id";
        String[] split = name.split(",");
        StringBuffer stringBuffer = new StringBuffer();
        Arrays.stream(split).distinct().forEach(e -> {
            stringBuffer.append(e + ",");
        });
        System.out.println(stringBuffer.toString().substring(0, stringBuffer.length() - 1));
    }



    /**
     * est 不包含的code表
     */

    @Test
    public void insert() {
        TableCodeConfig tableCodeConfig = new TableCodeConfig();
        /**
         * 项目外
         */
        tableCodeConfig.setCreateTime(new Date());
        tableCodeConfig.setTableCodeName("meta_category");
        tableCodeConfig.setTableCodeChinaName("分类表");
        tableCodeConfig.setColumns("category_type,name,parent_id");
        tableCodeConfig.setWhereSqlColumns("content_code");
        tableCodeConfig.setHandleBeanName("defaultTableDataHandler");
        TableCodeConfig tableCodeConfigByTableCodeName = tableCodeConfigRepository.findTableCodeConfigByTableCodeName(tableCodeConfig.getTableCodeName());
        if (null != tableCodeConfigByTableCodeName) {
            tableCodeConfig.setId(tableCodeConfigByTableCodeName.getId());
        }
        tableCodeConfigRepository.save(tableCodeConfig);



        tableCodeConfig.setId(null);
        tableCodeConfig.setCreateTime(new Date());
        tableCodeConfig.setTableCodeName("meta_data_source_info");
        tableCodeConfig.setTableCodeChinaName("数据源表");
        tableCodeConfig.setColumns("source_code");
        tableCodeConfig.setWhereSqlColumns(null);
        tableCodeConfig.setHandleBeanName("defaultTableDataHandler");
        tableCodeConfigByTableCodeName = tableCodeConfigRepository.findTableCodeConfigByTableCodeName(tableCodeConfig.getTableCodeName());
        if (null != tableCodeConfigByTableCodeName) {
            tableCodeConfig.setId(tableCodeConfigByTableCodeName.getId());
        }
        tableCodeConfigRepository.save(tableCodeConfig);

        tableCodeConfig.setId(null);
        tableCodeConfig.setCreateTime(new Date());
        tableCodeConfig.setTableCodeName("meta_data_object_info");
        tableCodeConfig.setTableCodeChinaName("数据集信息表");
        tableCodeConfig.setColumns("code,data_source_id");
        tableCodeConfig.setWhereSqlColumns(null);
        tableCodeConfig.setHandleBeanName("defaultTableDataHandler");
        tableCodeConfigByTableCodeName = tableCodeConfigRepository.findTableCodeConfigByTableCodeName(tableCodeConfig.getTableCodeName());
        if (null != tableCodeConfigByTableCodeName) {
            tableCodeConfig.setId(tableCodeConfigByTableCodeName.getId());
        }
        tableCodeConfigRepository.save(tableCodeConfig);


        tableCodeConfig.setId(null);
        tableCodeConfig.setCreateTime(new Date());
        tableCodeConfig.setTableCodeName("meta_data_object");
        tableCodeConfig.setTableCodeChinaName("数据集信息表");
        tableCodeConfig.setColumns("resource_id");
        tableCodeConfig.setWhereSqlColumns("resource_id");
        tableCodeConfig.setHandleBeanName("defaultTableDataHandler");
        tableCodeConfigByTableCodeName = tableCodeConfigRepository.findTableCodeConfigByTableCodeName(tableCodeConfig.getTableCodeName());
        if (null != tableCodeConfigByTableCodeName) {
            tableCodeConfig.setId(tableCodeConfigByTableCodeName.getId());
        }
        tableCodeConfigRepository.save(tableCodeConfig);

        tableCodeConfig.setId(null);
        tableCodeConfig.setCreateTime(new Date());
        tableCodeConfig.setTableCodeName("meta_model_object_info");
        tableCodeConfig.setTableCodeChinaName("事件和模型对象信息表");
        tableCodeConfig.setColumns("code");
        tableCodeConfig.setWhereSqlColumns(null);
        tableCodeConfig.setHandleBeanName("defaultTableDataHandler");
        tableCodeConfigByTableCodeName = tableCodeConfigRepository.findTableCodeConfigByTableCodeName(tableCodeConfig.getTableCodeName());
        if (null != tableCodeConfigByTableCodeName) {
            tableCodeConfig.setId(tableCodeConfigByTableCodeName.getId());
        }
        tableCodeConfigRepository.save(tableCodeConfig);

        tableCodeConfig.setId(null);
        tableCodeConfig.setCreateTime(new Date());
        tableCodeConfig.setTableCodeName("meta_model_object");
        tableCodeConfig.setTableCodeChinaName("事件和模型对象信息表");
        tableCodeConfig.setColumns("resource_id");
        tableCodeConfig.setWhereSqlColumns("resource_id");
        tableCodeConfig.setHandleBeanName("defaultTableDataHandler");
        tableCodeConfigByTableCodeName = tableCodeConfigRepository.findTableCodeConfigByTableCodeName(tableCodeConfig.getTableCodeName());
        if (null != tableCodeConfigByTableCodeName) {
            tableCodeConfig.setId(tableCodeConfigByTableCodeName.getId());
        }
        tableCodeConfigRepository.save(tableCodeConfig);

        tableCodeConfig.setId(null);
        tableCodeConfig.setCreateTime(new Date());
        tableCodeConfig.setTableCodeName("meta_topic_object_info");
        tableCodeConfig.setTableCodeChinaName("统计模型信息表");
        tableCodeConfig.setColumns("code");
        tableCodeConfig.setIgnoreColumnName("resource_type");
        tableCodeConfig.setIgnoreColumnValue("STRATEGY_FIELD");
        tableCodeConfig.setWhereSqlColumns(null);
        tableCodeConfig.setHandleBeanName("defaultTableDataHandler");
        tableCodeConfigByTableCodeName = tableCodeConfigRepository.findTableCodeConfigByTableCodeName(tableCodeConfig.getTableCodeName());
        if (null != tableCodeConfigByTableCodeName) {
            tableCodeConfig.setId(tableCodeConfigByTableCodeName.getId());
        }
        tableCodeConfigRepository.save(tableCodeConfig);

        tableCodeConfig.setId(null);
        tableCodeConfig.setCreateTime(new Date());
        tableCodeConfig.setTableCodeName("meta_topic_object");
        tableCodeConfig.setTableCodeChinaName("统计模型信息表");
        tableCodeConfig.setColumns("resource_id");
        tableCodeConfig.setIgnoreColumnName("resource_type");
        tableCodeConfig.setIgnoreColumnValue("STRATEGY_FIELD");
        tableCodeConfig.setWhereSqlColumns("resource_id");
        tableCodeConfig.setHandleBeanName("defaultTableDataHandler");
        tableCodeConfigByTableCodeName = tableCodeConfigRepository.findTableCodeConfigByTableCodeName(tableCodeConfig.getTableCodeName());
        if (null != tableCodeConfigByTableCodeName) {
            tableCodeConfig.setId(tableCodeConfigByTableCodeName.getId());
        }
        tableCodeConfigRepository.save(tableCodeConfig);


        tableCodeConfig.setId(null);
        tableCodeConfig.setCreateTime(new Date());
        tableCodeConfig.setTableCodeName("res_strategy_info");
        tableCodeConfig.setTableCodeChinaName("策略信息表");
        tableCodeConfig.setColumns("strategy_key");
        tableCodeConfig.setWhereSqlColumns("project_id");
        tableCodeConfig.setHandleBeanName("defaultTableDataHandler");
        tableCodeConfig.setIgnoreColumnName(null);
        tableCodeConfig.setIgnoreColumnValue(null);
        tableCodeConfigByTableCodeName = tableCodeConfigRepository.findTableCodeConfigByTableCodeName(tableCodeConfig.getTableCodeName());
        if (null != tableCodeConfigByTableCodeName) {
            tableCodeConfig.setId(tableCodeConfigByTableCodeName.getId());
        }
        tableCodeConfigRepository.save(tableCodeConfig);



        //排除策略字段通过其他方式存取
        tableCodeConfig.setId(null);
        tableCodeConfig.setCreateTime(new Date());
        tableCodeConfig.setTableCodeName("meta_object_field");
        tableCodeConfig.setTableCodeChinaName("字段表");
        tableCodeConfig.setColumns("field_code,resource_object_id");
//        tableCodeConfig.setIgnoreColumnName("resource_object_id");
//        tableCodeConfig.setIgnoreColumnValue("1");
        tableCodeConfig.setWhereSqlColumns(null);
        tableCodeConfig.setHandleBeanName("defaultTableDataHandler");
        tableCodeConfigByTableCodeName = tableCodeConfigRepository.findTableCodeConfigByTableCodeName(tableCodeConfig.getTableCodeName());
        if (null != tableCodeConfigByTableCodeName) {
            tableCodeConfig.setId(tableCodeConfigByTableCodeName.getId());
        }
        tableCodeConfigRepository.save(tableCodeConfig);



        /**
         * 项目内导出
         */
        tableCodeConfig.setId(null);
        tableCodeConfig.setCreateTime(new Date());
        tableCodeConfig.setTableCodeName("res_resource_set_item");
        tableCodeConfig.setTableCodeChinaName("资源管理表");
        tableCodeConfig.setColumns("resource_id");
        tableCodeConfig.setWhereSqlColumns("project_id");
        tableCodeConfig.setHandleBeanName("defaultTableDataHandler");
        tableCodeConfig.setIgnoreColumnName(null);
        tableCodeConfig.setIgnoreColumnValue(null);
        tableCodeConfigByTableCodeName = tableCodeConfigRepository.findTableCodeConfigByTableCodeName(tableCodeConfig.getTableCodeName());
        if (null != tableCodeConfigByTableCodeName) {
            tableCodeConfig.setId(tableCodeConfigByTableCodeName.getId());
        }
        tableCodeConfigRepository.save(tableCodeConfig);

        /**
         * 关联关系
         */
        tableCodeConfig.setId(null);
        tableCodeConfig.setCreateTime(new Date());
        tableCodeConfig.setTableCodeName("meta_topic_object_relation");
        tableCodeConfig.setTableCodeChinaName("资源管理表");
        tableCodeConfig.setColumns("primary_resource_id,relation_object_id,topic_object_info_id");
        tableCodeConfig.setWhereSqlColumns(null);
        tableCodeConfig.setHandleBeanName("defaultTableDataHandler");
        tableCodeConfig.setIgnoreColumnName(null);
        tableCodeConfig.setIgnoreColumnValue(null);
        tableCodeConfigByTableCodeName = tableCodeConfigRepository.findTableCodeConfigByTableCodeName(tableCodeConfig.getTableCodeName());
        if (null != tableCodeConfigByTableCodeName) {
            tableCodeConfig.setId(tableCodeConfigByTableCodeName.getId());
        }
        tableCodeConfigRepository.save(tableCodeConfig);




        tableCodeConfig.setId(null);
        tableCodeConfig.setCreateTime(new Date());
        tableCodeConfig.setTableCodeName("res_rule_info");
        tableCodeConfig.setTableCodeChinaName("规则信息表");
        tableCodeConfig.setColumns("name");
        tableCodeConfig.setWhereSqlColumns("project_id");
        tableCodeConfig.setHandleBeanName("defaultTableDataHandler");
        tableCodeConfig.setIgnoreColumnName(null);
        tableCodeConfig.setIgnoreColumnValue(null);
         tableCodeConfigByTableCodeName = tableCodeConfigRepository.findTableCodeConfigByTableCodeName(tableCodeConfig.getTableCodeName());
        if (null != tableCodeConfigByTableCodeName) {
            tableCodeConfig.setId(tableCodeConfigByTableCodeName.getId());
        }
        tableCodeConfigRepository.save(tableCodeConfig);

        tableCodeConfig.setId(null);
        tableCodeConfig.setCreateTime(new Date());
        tableCodeConfig.setTableCodeName("res_rule_set_info");
        tableCodeConfig.setTableCodeChinaName("规则集信息表");
        tableCodeConfig.setColumns("name");
        tableCodeConfig.setWhereSqlColumns("project_id");
        tableCodeConfig.setHandleBeanName("defaultTableDataHandler");
        tableCodeConfig.setIgnoreColumnName(null);
        tableCodeConfig.setIgnoreColumnValue(null);
        tableCodeConfigByTableCodeName = tableCodeConfigRepository.findTableCodeConfigByTableCodeName(tableCodeConfig.getTableCodeName());
        if (null != tableCodeConfigByTableCodeName) {
            tableCodeConfig.setId(tableCodeConfigByTableCodeName.getId());
        }
        tableCodeConfigRepository.save(tableCodeConfig);

        tableCodeConfig.setId(null);
        tableCodeConfig.setCreateTime(new Date());
        tableCodeConfig.setTableCodeName("res_rule_tree_info");
        tableCodeConfig.setTableCodeChinaName("规则树信息表");
        tableCodeConfig.setColumns("name");
        tableCodeConfig.setWhereSqlColumns("project_id");
        tableCodeConfig.setHandleBeanName("defaultTableDataHandler");
        tableCodeConfig.setIgnoreColumnName(null);
        tableCodeConfig.setIgnoreColumnValue(null);
        tableCodeConfigByTableCodeName = tableCodeConfigRepository.findTableCodeConfigByTableCodeName(tableCodeConfig.getTableCodeName());
        if (null != tableCodeConfigByTableCodeName) {
            tableCodeConfig.setId(tableCodeConfigByTableCodeName.getId());
        }
        tableCodeConfigRepository.save(tableCodeConfig);


        tableCodeConfig.setId(null);
        tableCodeConfig.setCreateTime(new Date());
        tableCodeConfig.setTableCodeName("res_score_card_info");
        tableCodeConfig.setTableCodeChinaName("评分卡信息表");
        tableCodeConfig.setColumns("name");
        tableCodeConfig.setWhereSqlColumns("project_id");
        tableCodeConfig.setHandleBeanName("defaultTableDataHandler");
        tableCodeConfig.setIgnoreColumnName(null);
        tableCodeConfig.setIgnoreColumnValue(null);
        tableCodeConfigByTableCodeName = tableCodeConfigRepository.findTableCodeConfigByTableCodeName(tableCodeConfig.getTableCodeName());
        if (null != tableCodeConfigByTableCodeName) {
            tableCodeConfig.setId(tableCodeConfigByTableCodeName.getId());
        }
        tableCodeConfigRepository.save(tableCodeConfig);


        tableCodeConfig.setId(null);
        tableCodeConfig.setCreateTime(new Date());
        tableCodeConfig.setTableCodeName("res_script_info");
        tableCodeConfig.setTableCodeChinaName("脚本信息表");
        tableCodeConfig.setColumns("name");
        tableCodeConfig.setWhereSqlColumns("project_id");
        tableCodeConfig.setHandleBeanName("defaultTableDataHandler");
        tableCodeConfig.setIgnoreColumnName(null);
        tableCodeConfig.setIgnoreColumnValue(null);
        tableCodeConfigByTableCodeName = tableCodeConfigRepository.findTableCodeConfigByTableCodeName(tableCodeConfig.getTableCodeName());
        if (null != tableCodeConfigByTableCodeName) {
            tableCodeConfig.setId(tableCodeConfigByTableCodeName.getId());
        }
        tableCodeConfigRepository.save(tableCodeConfig);

        tableCodeConfig.setId(null);
        tableCodeConfig.setCreateTime(new Date());
        tableCodeConfig.setTableCodeName("res_matrix_info");
        tableCodeConfig.setTableCodeChinaName("决策矩阵信息表");
        tableCodeConfig.setColumns("name");
        tableCodeConfig.setWhereSqlColumns("project_id");
        tableCodeConfig.setHandleBeanName("defaultTableDataHandler");
        tableCodeConfig.setIgnoreColumnName(null);
        tableCodeConfig.setIgnoreColumnValue(null);
        tableCodeConfigByTableCodeName = tableCodeConfigRepository.findTableCodeConfigByTableCodeName(tableCodeConfig.getTableCodeName());
        if (null != tableCodeConfigByTableCodeName) {
            tableCodeConfig.setId(tableCodeConfigByTableCodeName.getId());
        }
        tableCodeConfigRepository.save(tableCodeConfig);



        tableCodeConfig.setId(null);
        tableCodeConfig.setCreateTime(new Date());
        tableCodeConfig.setTableCodeName("meta_function_info");
        tableCodeConfig.setTableCodeChinaName("函数信息表");
        tableCodeConfig.setColumns("code");
        tableCodeConfig.setWhereSqlColumns(null);
        tableCodeConfig.setHandleBeanName("defaultTableDataHandler");
        tableCodeConfigByTableCodeName = tableCodeConfigRepository.findTableCodeConfigByTableCodeName(tableCodeConfig.getTableCodeName());
        if (null != tableCodeConfigByTableCodeName) {
            tableCodeConfig.setId(tableCodeConfigByTableCodeName.getId());
        }
        tableCodeConfigRepository.save(tableCodeConfig);


        tableCodeConfig.setId(null);
        tableCodeConfig.setCreateTime(new Date());
        tableCodeConfig.setTableCodeName("meta_function");
        tableCodeConfig.setTableCodeChinaName("函数信息表");
        tableCodeConfig.setColumns("resource_id");
        tableCodeConfig.setWhereSqlColumns(null);
        tableCodeConfig.setHandleBeanName("defaultTableDataHandler");
        tableCodeConfigByTableCodeName = tableCodeConfigRepository.findTableCodeConfigByTableCodeName(tableCodeConfig.getTableCodeName());
        if (null != tableCodeConfigByTableCodeName) {
            tableCodeConfig.setId(tableCodeConfigByTableCodeName.getId());
        }
        tableCodeConfigRepository.save(tableCodeConfig);


//        tableCodeConfig.setId(null);
//        tableCodeConfig.setCreateTime(new Date());
//        tableCodeConfig.setTableCodeName("res_strategy");
//        tableCodeConfig.setTableCodeChinaName("策略表");
//        tableCodeConfig.setColumns("resource_id,version");
//        tableCodeConfig.setWhereSqlColumns(null);
//        tableCodeConfig.setHandleBeanName("defaultTableDataHandler");
//        tableCodeConfig.setIgnoreColumnName(null);
//        tableCodeConfig.setIgnoreColumnValue(null);
//        tableCodeConfigByTableCodeName = tableCodeConfigRepository.findTableCodeConfigByTableCodeName(tableCodeConfig.getTableCodeName());
//        if (null != tableCodeConfigByTableCodeName) {
//            tableCodeConfig.setId(tableCodeConfigByTableCodeName.getId());
//        }
//        tableCodeConfigRepository.save(tableCodeConfig);











        TableDataHandler defaultTableDataHandler = TableSpringUtil.getBean("defaultTableDataHandler", TableDataHandler.class);
    }

}