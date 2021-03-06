package jrx.anyest.table.listener;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import jrx.anyest.table.constant.TableConstants;
import jrx.anyest.table.exception.TableDataImportException;
import jrx.anyest.table.jpa.dao.TableHistoryDataRepository;
import jrx.anyest.table.jpa.dto.DataCheckResult;
import jrx.anyest.table.jpa.entity.TableCodeConfig;
import jrx.anyest.table.jpa.entity.TableConversionKey;
import jrx.anyest.table.jpa.entity.TableHistoryData;
import jrx.anyest.table.jpa.entity.TableParamConfig;
import jrx.anyest.table.jpa.enums.HistoryDataType;
import jrx.anyest.table.service.*;
import jrx.anyest.table.utils.DataConverRuleEngineUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 描述
 * </p>
 *
 * @author lw
 * @since 2020/8/11 17:42
 */
@Service
public class DefaultTableImportListener implements ITableImportListener {
    @Autowired
    private TableKeyService tableKeyService;
    @Autowired
    private TableHistoryDataRepository tableHistoryDataRepository;
    private int order = 0;

    @Override
    public void before(String tableName, Map<String, Object> data, DataCheckResult dataCheckResult, JdbcTemplate jdbcTemplate) {
        String code = null;
        String codeColumns = TableDataCodeCacheManager.tableCodeConfigs.get(tableName) == null ? null : TableDataCodeCacheManager.tableCodeConfigs.get(tableName).getColumns();
        String tableCodeUuid = TablePropertiesThreadLocalHolder.getProperties(TableConstants.TABLE_CODE_UUID);
        Map<String, String> codeToId = TableDataCodeCacheManager.codeToId.get(tableCodeUuid);
        boolean insert = dataCheckResult.getInsertDataMap().get(tableName).contains(data);
        boolean version = dataCheckResult.getVersionDataMap().get(tableName).contains(data);
        boolean update = dataCheckResult.getUpdateDataMap().get(tableName).contains(data);
//        历史数据转存
        TableHistoryData tableHistoryData = new TableHistoryData();
        if (insert) {
            getHistoryData(tableName, data, jdbcTemplate, tableCodeUuid, codeToId, tableHistoryData);
        } else if (version) {
            /**
             * 获取到infoCode 根据infoCode找到version
             */
            //这里est系统的infoCode都是在版本对象的resource_id里面
            TableParamConfig tableParamConfig = TableDataCodeCacheManager.tableParamConfigs.get(tableName);
            Long resource = Long.parseLong(TableDataCodeCacheManager.codeToId.get(tableCodeUuid).get(data.get(tableParamConfig.getResourceIdColumn())));
            Long oldVersion;
            try {
                oldVersion = jdbcTemplate.queryForObject("select " + tableParamConfig.getVersionColumn() + " from " + tableName + " where  " + tableParamConfig.getResourceIdColumn() + "=" + resource + " order by " + tableParamConfig.getVersionColumn() + " desc limit 1", Long.class);
            } catch (DataAccessException e) {
                oldVersion = 0l;
            }
            data.put("version", ++oldVersion);
            getHistoryData(tableName, data, jdbcTemplate, tableCodeUuid, codeToId, tableHistoryData);
        } else if (update) {
            /**
             * 拿到之前的数据ID做更新操作
             */
            if (!StringUtils.isEmpty(codeColumns)) {
                String[] split = codeColumns.split(TableConstants.ID_SEPATATION);
                StringBuffer codeBuffer = new StringBuffer(tableName + TableConstants.CODE_SEPATATION);
                for (String str : split) {
                    String o = null;
                    try {
                        o = data.get(str).toString();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    codeBuffer.append(o + TableConstants.CODE_SEPATATION);
                }
                code = codeBuffer.toString().substring(0, codeBuffer.length() - 1);
            }
            /**
             * 删除以前的数据
             */
            String keyName = TableDataCodeCacheManager.tableKey.get(tableName);
            Long oldDataId = null;
            try {
                oldDataId = Long.parseLong(codeToId.get(code));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            /**
             * 查询历史数据
             */
            Map<String, Object> oldData = JdbcTemplateService.jdbcTemplate.queryForMap("select * from " + tableName + " where  " + keyName + " =?", oldDataId);
            tableHistoryData.setCreateTime(new Date());
            tableHistoryData.setTableName(tableName);
            tableHistoryData.setPrimaryKeyName(keyName);
            tableHistoryData.setHistoryDataType(HistoryDataType.UPDATE);
            tableHistoryData.setDataKey(tableCodeUuid);
            tableHistoryData.setDataId(oldDataId.toString());
            tableHistoryData.setData(JSON.toJSONString(oldData));
            JdbcTemplateService.jdbcTemplate.update("delete from " + tableName + " where " + keyName + "=" + oldDataId);
            /**
             * 做数据转换处理
             */
            data.put(keyName, oldDataId);
            conversionKey(tableName, data, codeToId);

        } else {
            throw new TableDataImportException("数据导入异常，DataCheckResult 不存在该数据类型 tableName:" + tableName + " data:" + JSON.toJSONString(data));
        }
        tableHistoryData.setResourceId(data.get("resource_id")==null?null:data.get("resource_id").toString());
        tableHistoryDataRepository.save(tableHistoryData);
    }

    private void getHistoryData(String tableName, Map<String, Object> data, JdbcTemplate jdbcTemplate, String tableCodeUuid, Map<String, String> codeToId, TableHistoryData tableHistoryData) {
        Long newKey = tableKeyService.getNewKey(jdbcTemplate);
        String key = TableDataCodeCacheManager.tableKey.get(tableName);
        data.put(key, newKey);
        conversionKey(tableName, data, codeToId);
        tableHistoryData.setCreateTime(new Date());
        tableHistoryData.setTableName(tableName);
        tableHistoryData.setPrimaryKeyName(key);
        tableHistoryData.setHistoryDataType(HistoryDataType.DELETE);
        tableHistoryData.setDataKey(tableCodeUuid);
        tableHistoryData.setDataId(newKey.toString());
        tableHistoryData.setData(null);
    }


    private void conversionKey(String tableName, Map<String, Object> data, Map<String, String> codeToId) {

        List<TableConversionKey> tableConversionKeys = TableDataCodeCacheManager.tableConversionKeys.get(tableName);
        if (CollectionUtils.isEmpty(tableConversionKeys)) {
            return;
        }
        tableConversionKeys.forEach(x -> {
            String conversionKey = x.getConversionKey().split(TableConstants.CODE_SEPATATION)[1];
            /**
             *  如果是版本类型的数据在监听器后置处理在做ID更新
             */
            if (x.isJsonObject()) {
                return;
            }
//            对于策略版本数据做特殊处理
            DataConverRuleEngineUtils.setPropertyTable(data, conversionKey, null, codeToId, null);
        });
    }

    @Override
    public void after(String tableName, Map<String, Object> data) {
        String uuid = TablePropertiesThreadLocalHolder.getProperties(TableConstants.TABLE_CODE_UUID);
        TableCodeConfig tableCodeConfig = TableDataCodeCacheManager.tableCodeConfigs.get(tableName);
        if (null == tableCodeConfig) return;
        String key = TableDataCodeCacheManager.tableKey.get(tableName);
        TableDataExpOrImpService.addCache(uuid, tableCodeConfig, key, tableCodeConfig.getColumns(), data);
    }

    @Override
    public int order() {
        return order;
    }

    @Override
    public int filter(String tableName, JSONObject x, DataCheckResult dataCheckResult, JdbcTemplate jdbcTemplate) {
        return 1;
    }
}
