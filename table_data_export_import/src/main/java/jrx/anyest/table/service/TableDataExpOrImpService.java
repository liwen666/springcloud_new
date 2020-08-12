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
import jrx.anyest.table.jpa.entity.TableConversionKey;
import jrx.anyest.table.jpa.entity.TableImportSort;
import jrx.anyest.table.jpa.enums.FieldType;
import jrx.anyest.table.listener.ITableExportListener;
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

import java.util.*;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;
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

    /**
     * 导入监听器
     */
    private Collection<ITableImportListener> tableImportListeners;
    /**
     * 导出监听器
     */
    private Collection<ITableExportListener> tableExportListeners;
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
            String condition = null;
            if (!StringUtils.isEmpty(tableCodeConfig.getHandleBeanName())) {
                TableDataHandler bean = TableSpringUtil.getBean(tableCodeConfig.getHandleBeanName(), TableDataHandler.class);
                /**
                 * code 表权限过滤
                 */
                boolean enableCode = bean.enableCode(tableCodeConfig, whereParam);
                if (!enableCode) {
                    continue;
                }
                /**
                 * code查询条件构造
                 */
                condition = bean.getCondition(tableCodeConfig, whereParam);
            }
            String ck = getCheckSql(tableCodeConfig, whereParam, condition);

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
                    codeCheck1.setCodeSql(ck);
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
        /**
         * 如果是分类表分类表初始化code
         */
        TableDataCodeCacheManager.idToCode.get(codeUuid).put("meta_category@0", "meta_category@系统");
        TableDataCodeCacheManager.codeToId.get(codeUuid).put("meta_category@系统", "0");
        List<TableCodeConfig> all = tableCodeConfigRepository.findAll().stream().filter(e -> e.isUsed()).collect(Collectors.toList());
        for (TableCodeConfig tableCodeConfig : all) {
            String condition = null;
            if (!StringUtils.isEmpty(tableCodeConfig.getHandleBeanName())) {
                TableDataHandler bean = getTableDataHandler(tableCodeConfig);
                /**
                 * code 表权限过滤
                 */
                boolean enableCode = bean.enableCode(tableCodeConfig, whereParam);
                if (!enableCode) {
                    continue;
                }
                /**
                 * code查询条件构造
                 */
                condition = bean.getCondition(tableCodeConfig, whereParam);
            }
            String keyName = TableDataCodeCacheManager.tableKey.get(tableCodeConfig.getTableCodeName());
            String ck = getCodeDataSql(tableCodeConfig, whereParam, condition);
            if (!StringUtils.isEmpty(tableCodeConfig.getHandleBeanName())) {
                TableDataHandler bean = TableSpringUtil.getBean(tableCodeConfig.getHandleBeanName(), TableDataHandler.class);
                ck = bean.processDataSql(tableCodeConfig, ck);
            }
            List<Map<String, Object>> maps = jdbcTemplate.queryForList(ck);
            logger.info("-----------------初始化code 缓存信息tableName:{} sql:{} codeSize:{}", tableCodeConfig.getTableCodeName(), ck, maps.size());
            String columns = tableCodeConfig.getColumns();
            maps.stream().forEach(e -> {
                if (!StringUtils.isEmpty(tableCodeConfig.getHandleBeanName())) {
                    TableDataHandler bean = TableSpringUtil.getBean(tableCodeConfig.getHandleBeanName(), TableDataHandler.class);
                    boolean b = bean.codeInit(tableCodeConfig.getTableCodeName(), e, whereParam);
                    if (!b) {
                        return;
                    }
                }
                addCache(codeUuid, tableCodeConfig, keyName, columns, e);
            });

            if (TableDataCodeCacheManager.idToCode.get(codeUuid).size() != TableDataCodeCacheManager.codeToId.get(codeUuid).size()) {
                throw new TableDataConversionException("初始化code缓存异常，idToCode和codeToId数量不一样！ tableName:" + tableCodeConfig.getTableCodeName() + " sql " + ck);
            }
        }

    }

    private TableDataHandler getTableDataHandler(TableCodeConfig tableCodeConfig) {
        return TableSpringUtil.getBean(tableCodeConfig.getHandleBeanName(), TableDataHandler.class);
    }

    public static void addCache(String codeUuid, TableCodeConfig tableCodeConfig, String keyName, String columns, Map<String, Object> e) {
        StringBuffer code = new StringBuffer(tableCodeConfig.getTableCodeName() + TableConstants.CODE_SEPATATION);
        String id = tableCodeConfig.getTableCodeName() + TableConstants.CODE_SEPATATION;
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
        String bid = null;
        String bcode = null;
        if (tableCodeConfig.getTableCodeName().equals("meta_object_field")) {
            bid = id + FieldType.valueOf((String) e.get("field_type")).getIndex() + e.get(keyName);
            bcode = code.substring(0, code.length() - 1) + FieldType.valueOf((String) e.get("field_type")).getIndex();
        }
        if (bid != null) {
            TableDataCodeCacheManager.idToCode.get(codeUuid).put(bid, bcode);
            TableDataCodeCacheManager.codeToId.get(codeUuid).put(bcode, FieldType.valueOf((String) e.get("field_type")).getIndex() + e.get(keyName).toString());
        }
        TableDataCodeCacheManager.idToCode.get(codeUuid).put(id + e.get(keyName), code.toString().substring(0, code.length() - 1));
        TableDataCodeCacheManager.codeToId.get(codeUuid).put(code.toString().substring(0, code.length() - 1), e.get(keyName).toString());
    }

    /**
     * 查询所有符合条件的数据
     *
     * @param tableCodeConfig
     * @param whereParam
     * @return
     */
    public String getCodeDataSql(TableCodeConfig tableCodeConfig, Map<String, Object> whereParam, String condition) {
        String where = TableSqlBulider.getWhereSql(tableCodeConfig, Arrays.asList(getWhere(tableCodeConfig)), whereParam);
        StringBuffer dataSql = new StringBuffer("SELECT *  FROM " + tableCodeConfig.getTableCodeName() + " " + where);
        if (!StringUtils.isEmpty(condition)) {
            dataSql.append("  " + condition);
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
    private String getCheckSql(TableCodeConfig tableCodeConfig, Map<String, Object> whereParam, String condition) {
        String[] split = tableCodeConfig.getColumns().split(",");
        String[] whereSqls = getWhere(tableCodeConfig);
        String sql = TableSqlBulider.getSql(Arrays.asList(split));
        String where = TableSqlBulider.getWhereSql(tableCodeConfig, Arrays.asList(whereSqls), whereParam);
        if (!StringUtils.isEmpty(condition)) {
            where = where + condition;
        }

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

    public void listAllRelationData(String tableName, Set<Object> dataIds, Map<String, Map<String,Object>> extraParam, Map<String, Map<String, Map<String, Object>>> dataMap, String handlerBeanName) {
        String key = TableDataCodeCacheManager.tableKey.get(tableName);
        listAllRelationData(tableName, key, dataIds, extraParam, dataMap, handlerBeanName);
    }


    public void listAllRelationData(String tableName, String keyName, Set<Object> dataIds, Map<String, Map<String,Object>> extraParam, Map<String, Map<String, Map<String, Object>>> dataMap, String handlerBeanName) {
        /**
         * 查询此表关联数据表
         */
        String key = TableDataCodeCacheManager.tableKey.get(tableName);
        List<TableCodeRelation> tableCodeRelations = TableDataCodeCacheManager.relations.get(tableName);
        String sql = TableSqlBulider.getSql(dataIds);
        List<Map<String, Object>> maps = jdbcTemplate.queryForList("select * from   " + tableName + " where " + keyName + " in (" + sql + ")");
        if (!StringUtils.isEmpty(handlerBeanName)) {
            TableDataHandler bean = TableSpringUtil.getBean(handlerBeanName, TableDataHandler.class);
            /**
             * 过滤掉旧版本数据
             */
            maps = bean.filterData(tableName, maps, extraParam);
        }

        List<RelationData> relationDatas = new ArrayList<>();
        for (Map map : maps) {
            if (!CollectionUtils.isEmpty(tableCodeRelations)) {
                for (TableCodeRelation tableCodeRelation : tableCodeRelations) {
                    if (map.get(tableCodeRelation.getPrimaryCodeKey()) != null) {
                        String tableProperty = DataConverRuleEngineUtils.getTableProperty(map, tableCodeRelation.getPrimaryCodeKey()).toString();
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
//            /**
//             * 判断一个id对应多个表的情况
//             */
//            if(relationData.getSlaveTableName().contains("\\|")){
//
//
//            }
            listAllRelationData(relationData.getSlaveTableName(), relationData.getKeyCode(), relationData.getKeys(), extraParam, dataMap, relationData.getHandlerBean());
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
        if (null==dataCheckResult) {
            throw new TableDataImportException("数据不存在，或者导入超时，请重新导入！");
        }
        Map<String, List<JSONObject>> importDataMap = dataCheckResult.getImportDataMap();
        if (CollectionUtils.isEmpty(importDataMap)) {
            throw new TableDataImportException("数据不存在，或者导入超时，请重新导入！");
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
            logger.info("[-------表数据保存结束--------]tableName:{},successNum:{},errorNum:{},errorMsg:{}", tableName, successObj.size(), errorObj.size(), JSON.toJSONString(errorMsgs));
            return;
        }
        /**
         * 排除掉成功或者失败的对象
         */
        tableDatas.stream().filter(e->!(successObj.contains(e)||errorObj.contains(e))).forEach(x -> {
            String key = TableDataCodeCacheManager.tableKey.get(tableName);
            /**
             * 递归查找满足条件的数据然后入库，直到所有数据全部入库或失败为止
             */
            try {
                boolean flag = checkDataConversionKeys(tableName, x);
                if (flag) {
                    /**
                     * 数据导入前置处理
                     */

                    tableImportListeners.stream().sorted(Comparator.comparing(ITableImportListener::order)).forEach(e1 -> e1.before(tableName, x, dataCheckResult,jdbcTemplate));
                    JdbcTemplateService.saveByMap(tableName, x);
                    /**
                     * 更新code转换缓存信息
                     */
                    successObj.add(x);

                    /**
                     * 数据导入后置处理
                     */

                    tableImportListeners.stream().sorted(Comparator.comparing(ITableImportListener::order)).forEach(e1 -> e1.after(tableName, x));
                }
            } catch (Exception e) {
                logger.error("保存数据异常，tableName:{},key,{} errorMsg,{}", tableName, x.get(key), e.getMessage());
                errorObj.add(x);
                errorMsgs.put(x.get(key).toString(), e.getMessage());
            }
            circleSaveDatas(tableName, tableDatas, successObj, errorObj, errorMsgs, dataCheckResult);
        });
    }

    private boolean checkDataConversionKeys(String tableName, JSONObject data) {
        String properties = TablePropertiesThreadLocalHolder.getProperties(TableConstants.TABLE_CODE_UUID);
        /**
         * 检查code缓存是否存在所有要转化的key
         */
        List<TableConversionKey> tableConversionKeys = TableDataCodeCacheManager.tableConversionKeys.get(tableName);

        for (TableConversionKey conversionKey : tableConversionKeys) {
            String conversion = conversionKey.getConversionKey();
            String code = (String) DataConverRuleEngineUtils.getTableProperty(data, conversion.substring(conversion.indexOf(TableConstants.CODE_SEPATATION)+1));
            if (!StringUtils.isEmpty(code)&&!code.contains(TableConstants.ID_SEPATATION)&&null == TableDataCodeCacheManager.codeToId.get(properties).get(code)) {
                return false;
            }
        }
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
             * 判断是新增加还是更新,还是版本迭代
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

    public void conversionIdToCode(Map<String, Map<String, Map<String, Object>>> dataMap) {
        String tableCodeUuid = TablePropertiesThreadLocalHolder.getProperties("table_code_uuid");
        Map<String, String> idToCode = TableDataCodeCacheManager.idToCode.get(tableCodeUuid);
        Map<String, List<TableConversionKey>> collect = tableConversionKeyRepository.findAll().stream().filter(TableConversionKey::isUsed).collect(Collectors.groupingBy(TableConversionKey::getTableCodeName));
        AtomicInteger success = new AtomicInteger(0);
        AtomicInteger conversion = new AtomicInteger(0);
        dataMap.forEach((k, v) -> tableExportListeners.stream().sorted(Comparator.comparing(ITableExportListener::order)).forEach(e -> {
            conversion.addAndGet(v.size());
            success.addAndGet(e.before(k, v, idToCode, jdbcTemplate, collect));
        }));
        if (success.get() != conversion.get()) {
            throw new TableDataConversionException("数据导出失败，有数据id-code转换失败");
        }
    }


    public void setTableExportListeners(Collection<ITableExportListener> tableExportListeners) {
        this.tableExportListeners = tableExportListeners;
    }
}
