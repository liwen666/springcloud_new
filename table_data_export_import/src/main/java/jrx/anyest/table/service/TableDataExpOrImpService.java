package jrx.anyest.table.service;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import jrx.anyest.table.config.TablePropertiesConfig;
import jrx.anyest.table.jpa.dao.TableCodeConfigRepository;
import jrx.anyest.table.jpa.dao.TableCodeRelationRepository;
import jrx.anyest.table.jpa.dao.TableConversionKeyRepository;
import jrx.anyest.table.jpa.dao.TableMarkInitRepository;
import jrx.anyest.table.jpa.dto.CodeCheck;
import jrx.anyest.table.jpa.dto.RelationData;
import jrx.anyest.table.jpa.dto.TableDataImportOrExpResult;
import jrx.anyest.table.jpa.entity.TableCodeConfig;
import jrx.anyest.table.jpa.entity.TableCodeRelation;
import jrx.anyest.table.utils.DataConverRuleEngineUtils;
import jrx.anyest.table.utils.TableSpringUtil;
import jrx.anyest.table.utils.TableSqlBulider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.concurrent.ConcurrentMap;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <p>
 * 描述
 * </p>
 * 数据导入导出
 *
 * @author lw
 * @since 2019/5/26 23:40
 */

@Service
public class TableDataExpOrImpService {

    @Autowired
    private TableCodeConfigRepository tableCodeConfigRepository;

    @Autowired
    private TableCodeRelationRepository tableCodeRelationRepository;
    @Autowired
    private TableConversionKeyRepository tableConversionKeyRepository;
    @Autowired
    private TableMarkInitRepository tableMarkInitRepository;

    public static Logger logger = LoggerFactory.getLogger(TableDataExpOrImpService.class);

    /**
     * 表数据导出前的code检查
     *
     * @return
     */
    public List<TableDataImportOrExpResult<CodeCheck>> checkCode(Map<String, Object> whereParam) {
        List<TableDataImportOrExpResult<CodeCheck>> errorData = Lists.newArrayList();
        /**
         * 查询所有表的code规则配置
         */
        List<TableCodeConfig> all = tableCodeConfigRepository.findAll();
        all.stream().filter(e -> e.isUsed()).collect(Collectors.toList());
        for (TableCodeConfig tableCodeConfig : all) {
            String ck = getCheckSql(tableCodeConfig, whereParam);
            List<Map<String, Object>> maps = JdbcTemplateService.jdbcTemplate.queryForList(ck);
            maps.forEach(e -> {
                if ((Long) e.get("num") != 1) {
                    TableDataImportOrExpResult<CodeCheck> codeCheck = new TableDataImportOrExpResult<>();
                    CodeCheck codeCheck1 = new CodeCheck();
                    codeCheck1.setNum(((Long) e.get("num")).intValue());
                    /**
                     * 查询出有问题的数据
                     */
                    ConcurrentMap<String, Object> errorParams = Maps.newConcurrentMap();
                    errorParams.putAll(whereParam);
                    e.remove("num");
                    errorParams.putAll(e);
                    String errorSql = getErrorDataSql(tableCodeConfig, errorParams);
                    List<Map<String, Object>> errorDatas = JdbcTemplateService.jdbcTemplate.queryForList(errorSql);
//                    errorData.put(tableCodeConfig.getTableCodeName(), errorDatas);
                    codeCheck.setTableChinaName(tableCodeConfig.getTableCodeChinaName());
                    codeCheck.setTableName(tableCodeConfig.getTableCodeName());
                    codeCheck1.setCode(tableCodeConfig.getColumns());
                    codeCheck1.setData(errorDatas);
                    codeCheck.setData(codeCheck1);
                    errorData.add(codeCheck);
                }
            });
        }
        return errorData;
    }

    public void initCodeCache(Map<String, Object> whereParam) {
        /**
         * 查询所有表的code规则配置
         */
        String codeUuid = PropertiesThreadLocalHolder.getProperties("table_code_uuid");
        TableDataCodeCacheManager.idToCode.put(codeUuid,Maps.newConcurrentMap());
        TableDataCodeCacheManager.codeToId.put(codeUuid,Maps.newConcurrentMap());
        List<TableCodeConfig> all = tableCodeConfigRepository.findAll();
        all.stream().filter(e -> e.isUsed()).collect(Collectors.toList());
        for (TableCodeConfig tableCodeConfig : all) {
            String keyName =TableDataCodeCacheManager.tableKey.get(tableCodeConfig.getTableCodeName());
            String ck = getCodeDataSql(tableCodeConfig, whereParam);
            List<Map<String, Object>> maps = JdbcTemplateService.jdbcTemplate.queryForList(ck);
            String columns = tableCodeConfig.getColumns();
            maps.stream().forEach(e->{
                StringBuffer code = new StringBuffer();
                String id=tableCodeConfig.getTableCodeName()+":"+e.get(keyName);
                for(String col:columns.split(",")){
                    String cd;
                    if(null==e.get(col)){
                       continue;
                    }
                    if(!StringUtils.isEmpty(tableCodeConfig.getHandleBeanName())){
                        TableDataHandler bean = TableSpringUtil.getBean(tableCodeConfig.getHandleBeanName(), TableDataHandler.class);
                        cd=bean.codeInit(e.get(col),tableCodeConfig.getTableCodeName());
                    }else {
                        cd = e.get(col).toString();
                    }
                    code.append(cd+":");
                }
                TableDataCodeCacheManager.idToCode.get(codeUuid).put(id,code.toString().substring(0,code.length()-1));
                TableDataCodeCacheManager.codeToId.get(codeUuid).put(code.toString().substring(0,code.length()-1),id);
            });

        }
    }

    /**
     * 查询所有符合条件的数据
     * @param tableCodeConfig
     * @param whereParam
     * @return
     */
    private String getCodeDataSql(TableCodeConfig tableCodeConfig, Map<String, Object> whereParam) {
        String[] whereSqls = tableCodeConfig.getWhereSqlColumns().split(",");
        String wheresql = TableSqlBulider.getWhereSql(Arrays.asList(whereSqls), whereParam);
        StringBuffer dataSql = new StringBuffer("SELECT *  FROM " + tableCodeConfig.getTableCodeName() + " " + wheresql);
        return dataSql.toString();
    }


    /**
     * 查询所有错误数据
     * @param tableCodeConfig
     * @param param
     * @return
     */
    private String getErrorDataSql(TableCodeConfig tableCodeConfig, Map<String, Object> param) {
        ArrayList<String> objects = Lists.newArrayList();
        String[] split = tableCodeConfig.getColumns().split(",");
        String[] whereSqls = tableCodeConfig.getWhereSqlColumns().split(",");
        objects.addAll(Arrays.asList(whereSqls));
        objects.addAll(Arrays.asList(split));
        String wheresql = TableSqlBulider.getWhereSql(objects, param);
        StringBuffer checkSql = new StringBuffer("SELECT *  FROM " + tableCodeConfig.getTableCodeName() + " " + wheresql);
        return checkSql.toString();
    }

    /**
     * 检查表数据是否符合唯一性
     * @param tableCodeConfig
     * @param whereParam
     * @return
     */
    private String getCheckSql(TableCodeConfig tableCodeConfig, Map<String, Object> whereParam) {
        String[] split = tableCodeConfig.getColumns().split(",");
        String[] whereSqls = tableCodeConfig.getWhereSqlColumns().split(",");
        String sql = TableSqlBulider.getSql(Arrays.asList(split));
        String wheresql = TableSqlBulider.getWhereSql(Arrays.asList(whereSqls), whereParam);
        StringBuffer checkSql = new StringBuffer("SELECT count(1) num ," + sql + " FROM `res_rule_info`  " + wheresql + "  group by  " + sql);
        checkSql.append(" ORDER BY num;");
        return checkSql.toString();
    }


    /**
     * 为数据添加唯一性标识
     */
    public void initTableMark() {
//        List<TableMarkInit> collect = tableMarkInitRepository.findAll().stream().filter(e -> e.isUsed()).collect(Collectors.toList());
//        for (TableMarkInit tableMarkInit : collect) {
//            String markInitSql = getMarkSql(tableMarkInit.getTableName());
//            JdbcTemplateUtils.jdbcTemplate.execute(markInitSql);
//            String primaryKey =JdbcTemplateUtils.jdbcTemplate.queryForObject("SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.KEY_COLUMN_USAGE WHERE TABLE_NAME='"+tableMarkInit.getTableName()+"' limit 1",String.class);
//            List<Long> longs = JdbcTemplateUtils.jdbcTemplate.queryForList("select " + primaryKey + " from " + tableMarkInit.getTableName() + " where mark_id is null", Long.class);
//
//
//        }
    }

    private String getMarkSql(String tableName) {
        return "ALTER table " + tableName + " add mark_id varchar(32);";
    }

    public void listAllRelationData(String tableName, Set<Object> dataIds, Map<String, Object> exetraParam, Map<String, Map<String, Map<String, Object>>> dataMap, String handlerBeanName) {
        String key = TableDataCodeCacheManager.tableKey.get(tableName);
        listAllRelationData(tableName, key, dataIds, exetraParam, dataMap, handlerBeanName);
    }


    public void listAllRelationData(String tableName, String keyName, Set<Object> dataIds, Map<String, Object> exetraParam, Map<String, Map<String, Map<String, Object>>> dataMap, String handlerBeanName) {
        /**
         * 查询此表关联数据表
         */
        String key = TableDataCodeCacheManager.tableKey.get(tableName);
        List<TableCodeRelation> tableCodeRelations = TableDataCodeCacheManager.relations.get(tableName);
        String sql = TableSqlBulider.getSql(dataIds);
        List<Map<String, Object>> maps = JdbcTemplateService.jdbcTemplate.queryForList("select * from   " + tableName + " where " + keyName + " in (" + sql + ")");
        if (!StringUtils.isEmpty(handlerBeanName)) {
            TableDataHandler bean = TableSpringUtil.getBean(handlerBeanName, TableDataHandler.class);
            maps = bean.filterData(tableName, maps, exetraParam);
        }
        List<RelationData> relationDatas = new ArrayList<>();

        for (Map map : maps) {
            if (!CollectionUtils.isEmpty(tableCodeRelations)) {
                for (TableCodeRelation tableCodeRelation : tableCodeRelations) {
                    if (map.get(tableCodeRelation.getPrimaryCodeKey()) != null) {
                        String  tableProperty = DataConverRuleEngineUtils.getTableProperty(map, tableCodeRelation.getPrimaryCodeKey()).toString();
//                        relationDatas.add(RelationData.builder().key(map.get(tableCodeRelation.getPrimaryCodeKey()).toString())
                        /**
                         * 有些关联数据在json字符串中
                         */
                        relationDatas.add(RelationData.builder().key(tableProperty)
                                .slaveTableName(tableCodeRelation.getSlaveTableName()).handlerBean(tableCodeRelation.getFilterHandleBean()).keyCode(tableCodeRelation.getSlaveCodeKey()).build());
                    }
                }
            }
            if (dataMap.get(tableName) == null) {
                ConcurrentMap<String, Map<String, Object>> objectObjectConcurrentMap = Maps.newConcurrentMap();
                objectObjectConcurrentMap.put(map.get(key).toString(), map);
                dataMap.put(tableName, objectObjectConcurrentMap);
            } else {
                dataMap.get(tableName).put(map.get(key).toString(), map);
            }
        }
        /**
         * 查询关联数据
         */
        List<RelationData> relationDataNew = new ArrayList<>();
        if (!CollectionUtils.isEmpty(relationDatas)) {
            Map<String, List<RelationData>> collect = relationDatas.stream().collect(Collectors.groupingBy(RelationData::getSlaveTableName));
            collect.forEach((s, relationData) -> {
                RelationData build = RelationData.builder().slaveTableName(s).handlerBean(relationData.get(0).getHandlerBean())
                        .keyCode(relationData.get(0).getKeyCode()).build();
                Set<Object> keys = new HashSet<>();
                relationData.stream().forEach(e -> {
                    String key1 = e.getKey();
                    String[] split = key1.split(",");
                    keys.addAll(Arrays.asList(split));
                });
                build.setKeys(keys);
                relationDataNew.add(build);
            });
        }
        for (RelationData relationData : relationDataNew) {
            listAllRelationData(relationData.getSlaveTableName(), relationData.getKeyCode(), relationData.getKeys(), exetraParam, dataMap, relationData.getHandlerBean());
        }

    }

    public void initRelationCache() {
        TableDataCodeCacheManager.relations = tableCodeRelationRepository.findAll().stream().filter(e -> e.isUsed()).collect(Collectors.groupingBy(TableCodeRelation::getPrimaryTableName));
    }
}
