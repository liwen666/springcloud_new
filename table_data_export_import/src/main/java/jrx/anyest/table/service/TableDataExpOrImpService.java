package jrx.anyest.table.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import jrx.anyest.table.constant.TableConstants;
import jrx.anyest.table.exception.TableDataConversionException;
import jrx.anyest.table.exception.TableDataImportException;
import jrx.anyest.table.jpa.dao.*;
import jrx.anyest.table.jpa.dto.*;
import jrx.anyest.table.jpa.entity.TableCodeConfig;
import jrx.anyest.table.jpa.entity.TableCodeRelation;
import jrx.anyest.table.jpa.entity.TableImportSort;
import jrx.anyest.table.listener.ITableImportListener;
import jrx.anyest.table.utils.DataConverRuleEngineUtils;
import jrx.anyest.table.utils.TableSpringUtil;
import jrx.anyest.table.utils.TableSqlBulider;
import jrx.anyest.table.utils.TableTimeMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

import static jrx.anyest.table.service.JdbcTemplateService.jdbcTemplate;

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

    private Collection<ITableImportListener> tableImportListeners;
    public static TableTimeMap<String, DataCheckResult> tableDataCache = new TableTimeMap<>();


    public void setTableImportListeners(Collection<ITableImportListener> tableImportListeners) {
        this.tableImportListeners = tableImportListeners;
    }

    @Autowired
    private TableCodeConfigRepository tableCodeConfigRepository;

    @Autowired
    private TableCodeRelationRepository tableCodeRelationRepository;
    @Autowired
    private TableImportSortRepository tableImportSortRepository;
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
            List<Map<String, Object>> maps = jdbcTemplate.queryForList(ck);

            maps.forEach(e -> {
                if ((Long) e.get("num") != 1) {
                    logger.error("code初始化有异常数据：sql:{}", ck);
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
                    List<Map<String, Object>> errorDatas = jdbcTemplate.queryForList(errorSql);
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
        logger.info("开始初始化code缓存信息");
        /**
         * 查询所有表的code规则配置
         */
        String codeUuid = TablePropertiesThreadLocalHolder.getProperties("table_code_uuid");
        TableDataCodeCacheManager.idToCode.put(codeUuid, Maps.newConcurrentMap());
        TableDataCodeCacheManager.codeToId.put(codeUuid, Maps.newConcurrentMap());
        List<TableCodeConfig> all = tableCodeConfigRepository.findAll().stream().filter(e -> e.isUsed()).collect(Collectors.toList());
        /**
         * 如果是项目外就不需要projectId  把Code规则去掉
         */
        if (null == whereParam.get("projectId")) {
            logger.info("-------------执行项目外导出code初始化---------");
            all = all.stream().filter(e -> !"project_id".equals(e.getWhereSqlColumns())).collect(Collectors.toList());
        }
        for (TableCodeConfig tableCodeConfig : all) {
            String keyName = TableDataCodeCacheManager.tableKey.get(tableCodeConfig.getTableCodeName());
            String ck = getCodeDataSql(tableCodeConfig, whereParam);
            List<Map<String, Object>> maps = jdbcTemplate.queryForList(ck);
            String columns = tableCodeConfig.getColumns();
            maps.stream().forEach(e -> {
                if (!StringUtils.isEmpty(tableCodeConfig.getHandleBeanName())) {
                    TableDataHandler bean = TableSpringUtil.getBean(tableCodeConfig.getHandleBeanName(), TableDataHandler.class);
                    boolean b = bean.codeInit(tableCodeConfig.getTableCodeName(), e, whereParam);
                    if (!b) {
                        return;
                    }
                }
                StringBuffer code = new StringBuffer(tableCodeConfig.getTableCodeName() + TableConstants.CODE_SEPATATION);
                String id = tableCodeConfig.getTableCodeName() + TableConstants.CODE_SEPATATION + e.get(keyName);
                for (String col : columns.split(TableConstants.ID_SEPATATION)) {
                    String cd;
                    if (null == e.get(col)) {
                        continue;
                    }
                    if (!StringUtils.isEmpty(tableCodeConfig.getHandleBeanName())) {
                        TableDataHandler bean = TableSpringUtil.getBean(tableCodeConfig.getHandleBeanName(), TableDataHandler.class);
                        cd = bean.codeProcess(tableCodeConfig.getTableCodeName(), col, e.get(col));
                    } else {
                        cd = e.get(col).toString();
                    }
                    code.append(cd + TableConstants.CODE_SEPATATION);
                }
                TableDataCodeCacheManager.idToCode.get(codeUuid).put(id, code.toString().substring(0, code.length() - 1));
                TableDataCodeCacheManager.codeToId.get(codeUuid).put(code.toString().substring(0, code.length() - 1), id);
            });

            if (TableDataCodeCacheManager.idToCode.get(codeUuid).size() != TableDataCodeCacheManager.codeToId.get(codeUuid).size()) {
                throw new TableDataConversionException("初始化code缓存异常，idToCode和codeToId数量不一样！");

            }
        }
    }

    /**
     * 查询所有符合条件的数据
     *
     * @param tableCodeConfig
     * @param whereParam
     * @return
     */
    public String getCodeDataSql(TableCodeConfig tableCodeConfig, Map<String, Object> whereParam) {
        String where = TableSqlBulider.getWhereSql(tableCodeConfig, Arrays.asList(getWhere(tableCodeConfig)), whereParam);
        StringBuffer dataSql = new StringBuffer("SELECT *  FROM " + tableCodeConfig.getTableCodeName() + " " + where);
        /**
         * 针对分类表需要做分类排序，先硬编码有空再弄
         */
        if (tableCodeConfig.getTableCodeName().equals("meta_category")) {
            return dataSql.toString() + " ORDER BY parent_id asc";
        }
        return dataSql.toString();
    }

    private String[] getWhere(TableCodeConfig tableCodeConfig) {
        String[] whereSql;
        if (!StringUtils.isEmpty(tableCodeConfig.getWhereSqlColumns())) {
            whereSql = tableCodeConfig.getWhereSqlColumns().split(",");
        } else {
            whereSql = new String[0];
        }
        return whereSql;
    }


    /**
     * 查询所有错误数据
     *
     * @param tableCodeConfig
     * @param param
     * @return
     */
    private String getErrorDataSql(TableCodeConfig tableCodeConfig, Map<String, Object> param) {
        ArrayList<String> objects = Lists.newArrayList();
        String[] split = tableCodeConfig.getColumns().split(",");
        String[] whereSqls = getWhere(tableCodeConfig);
        objects.addAll(Arrays.asList(whereSqls));
        objects.addAll(Arrays.asList(split));
        String wheresql = TableSqlBulider.getWhereSql(tableCodeConfig, objects, param);
        StringBuffer checkSql = new StringBuffer("SELECT *  FROM " + tableCodeConfig.getTableCodeName() + " " + wheresql);
        return checkSql.toString();
    }

    /**
     * 检查表数据是否符合唯一性
     *
     * @param tableCodeConfig
     * @param whereParam
     * @return
     */
    private String getCheckSql(TableCodeConfig tableCodeConfig, Map<String, Object> whereParam) {
        String[] split = tableCodeConfig.getColumns().split(",");
        String[] whereSqls = getWhere(tableCodeConfig);
        String sql = TableSqlBulider.getSql(Arrays.asList(split));
        String where = TableSqlBulider.getWhereSql(tableCodeConfig, Arrays.asList(whereSqls), whereParam);
        StringBuffer checkSql = new StringBuffer("SELECT count(1) num ," + sql + " FROM " + tableCodeConfig.getTableCodeName() + "  " + where + "  group by  " + sql);
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
        List<Map<String, Object>> maps = jdbcTemplate.queryForList("select * from   " + tableName + " where " + keyName + " in (" + sql + ")");
        if (!StringUtils.isEmpty(handlerBeanName)) {
            TableDataHandler bean = TableSpringUtil.getBean(handlerBeanName, TableDataHandler.class);
            maps = bean.filterData(tableName, maps, exetraParam);
        }
        List<RelationData> relationDatas = new ArrayList<>();

        for (Map map : maps) {
            if (!CollectionUtils.isEmpty(tableCodeRelations)) {
                for (TableCodeRelation tableCodeRelation : tableCodeRelations) {
                    if (map.get(tableCodeRelation.getPrimaryCodeKey()) != null) {
                        String tableProperty = DataConverRuleEngineUtils.getTableProperty(map, tableCodeRelation.getPrimaryCodeKey()).toString();
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

    public List<Map<String, Object>> prepareData(String prepareSql) {
        return jdbcTemplate.queryForList(prepareSql);
    }

    /**
     * 导入数据
     */
    @Transactional
    public void importData(String dataKey, ImportDataResult importDataResult) {
        DataCheckResult dataCheckResult = tableDataCache.get(dataKey);
        logger.info("--------准备导入数据----------");
        Map<String, List<JSONObject>> importDataMap = dataCheckResult.getImportDataMap();
        if (CollectionUtils.isEmpty(importDataMap)) {
            throw new TableDataImportException("数据不存在，请重新导入！");
        }


        /**
         * 开始数据导入
         */
        List<TableImportSort> collect = tableImportSortRepository.findAll().stream().sorted(Comparator.comparing(TableImportSort::getOrderId)).collect(Collectors.toList());
        List<String> tableNames = collect.stream().map(TableImportSort::getTableCodeName).collect(Collectors.toList());
        Set<String> set = importDataMap.keySet();
        boolean b = tableNames.containsAll(set);
        if (!b) {
            StringBuffer missTables = new StringBuffer();
            for (String str : set) {
                if (!tableNames.contains(str)) {
                    missTables.append(str + " ");
                }
            }
            throw new TableDataImportException("存在表数据不在TableImportSort表排序列表中! 缺失的表有： " + missTables.toString());
        }

        collect.forEach(e -> {
            List<JSONObject> tableDatas = importDataMap.get(e.getTableCodeName());
            if (!CollectionUtils.isEmpty(tableDatas)) {
                ImportData importData = new ImportData();
                Map<String, String> errorMsgs = Maps.newConcurrentMap();
                List<JSONObject> successObj = Lists.newArrayList();
                List<JSONObject> errorObj = Lists.newArrayList();
                circleSaveDatas(e.getTableCodeName(), tableDatas, successObj, errorObj, errorMsgs, dataCheckResult);
                importData.setTableName(e.getTableCodeName());
                importData.setSuccessNum(successObj.size());
                importData.setErrorNum(errorObj.size());
                importData.setErrorData(errorMsgs);
                if (importData.getErrorNum() != 0) {
                    importDataResult.setResult(false);
                }
                importDataResult.getImportData().add(importData);
            }
        });

        /**
         * 数据导入后置处理
         */
        if (!CollectionUtils.isEmpty(tableImportListeners)) {
            tableImportListeners.forEach(e -> {
                importDataMap.forEach((k, v) -> {
                    v.forEach(x -> {
                        e.after(k, x);
                    });
                });
            });
        }
        if (!importDataResult.isResult()) {
            tableDataCache.remove(dataKey);
            throw new TableDataImportException("导入失败");
        }
        tableDataCache.remove(dataKey);
    }


    /**
     * 此方法正对一张表数据互相引用
     *
     * @param tableName
     * @param tableDatas
     * @param successObj
     * @param errorObj
     * @param errorMsgs
     * @param dataCheckResult
     */
    private void circleSaveDatas(String
                                         tableName, List<JSONObject> tableDatas, List<JSONObject> successObj, List<JSONObject> errorObj, Map<String, String> errorMsgs, DataCheckResult
                                         dataCheckResult) {
        if (tableDatas.size() == successObj.size() + errorObj.size()) {
            logger.info("-------表数据保存结束--------tableName:{},successNum:{},errorNum:{},errorMsg:{}", tableName, successObj.size(), errorObj.size(), JSON.toJSONString(errorMsgs));
            return;
        }
        tableDatas.forEach(x -> {
            String key = TableDataCodeCacheManager.tableKey.get(tableName);
            boolean flag = checkDataConversionKeys(tableName, x);
            if (flag) {
                /**
                 * 数据导入前置处理
                 */
                try {
                    tableImportListeners.forEach(e1 -> {
                        e1.before(tableName, x, dataCheckResult);
                    });
                    JdbcTemplateService.saveByMap(tableName, x);
                    /**
                     * 更新code转换缓存信息
                     */
                    successObj.add(x);
                } catch (Exception e) {
                    logger.error("保存数据异常，tableName:{},key,{} errorMsg,{}", tableName, x.get(key), e.getMessage());
                    errorObj.add(x);
                    errorMsgs.put(x.get(key).toString(), e.getMessage());
                }
            }
            circleSaveDatas(tableName, tableDatas, successObj, errorObj, errorMsgs, dataCheckResult);
        });
    }

    private boolean checkDataConversionKeys(String tableName, JSONObject x) {
        /**
         * 检查code缓存是否存在所有要转化的key
         */
//        TableDataCodeCacheManager.
        return true;
    }

    public DataCheckResult checkData(Map<String, String> stringStringMap) {
        DataCheckResult dataCheckResult = new DataCheckResult();
        String tableCodeUuid = TablePropertiesThreadLocalHolder.getProperties("table_code_uuid");
        dataCheckResult.setUuidKey(tableCodeUuid);

        /**
         * 对数据按表分组
         */
        //新增加数据
        Map<String, List<JSONObject>> insertDataMap = Maps.newConcurrentMap();
        //更新数据
        Map<String, List<JSONObject>> updateDataMap = Maps.newConcurrentMap();
        //即将覆盖更新的数据
        Map<String, List<JSONObject>> importDataMap = Maps.newConcurrentMap();
        Map<String, List<JSONObject>> versionDataMap = Maps.newConcurrentMap();
        stringStringMap.forEach((k, v) -> {
            String tableName = k.split(TableConstants.SPLIT)[0];
            if (null == importDataMap.get(tableName)) {
                importDataMap.put(tableName, Lists.newArrayList());
                insertDataMap.put(tableName, Lists.newArrayList());
                updateDataMap.put(tableName, Lists.newArrayList());
                versionDataMap.put(tableName, Lists.newArrayList());
            }
            /**
             * 判断时新增加还是更新,还是版本迭代
             */
            if (k.contains(TableConstants.CODE_SEPATATION)) {
                String codeId = k.split(TableConstants.SPLIT)[1];
                String code = codeId.substring(codeId.indexOf(TableConstants.CODE_SEPATATION) + 1);
                if (TableDataCodeCacheManager.codeToId.get(tableCodeUuid).containsKey(code)) {
                    updateDataMap.get(tableName).add(JSON.parseObject(v));
                } else {
                    insertDataMap.get(tableName).add(JSON.parseObject(v));
                }
            } else {
                versionDataMap.get(tableName).add(JSON.parseObject(v));
            }
            importDataMap.get(tableName).add(JSON.parseObject(v));
        });
        tableDataCache.put(tableCodeUuid, dataCheckResult);
        dataCheckResult.setImportDataMap(importDataMap);
        dataCheckResult.setUpdateDataMap(updateDataMap);
        dataCheckResult.setInsertDataMap(insertDataMap);
        dataCheckResult.setVersionDataMap(versionDataMap);
        return dataCheckResult;
    }

    public void initTableSort() {

        TableDataCodeCacheManager.tableImportSorts = tableImportSortRepository.findAll().stream().sorted(Comparator.comparing(TableImportSort::getOrderId)).collect(Collectors.toList());

    }
}
