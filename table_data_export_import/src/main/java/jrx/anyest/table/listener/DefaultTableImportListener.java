package jrx.anyest.table.listener;

import com.alibaba.fastjson.JSON;
import jrx.anyest.table.constant.TableConstants;
import jrx.anyest.table.exception.TableDataImportException;
import jrx.anyest.table.jpa.dto.DataCheckResult;
import jrx.anyest.table.jpa.entity.TableCodeConfig;
import jrx.anyest.table.jpa.entity.TableConversionKey;
import jrx.anyest.table.jpa.entity.TableParamConfig;
import jrx.anyest.table.service.*;
import jrx.anyest.table.utils.DataConverRuleEngineUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 描述
 * </p>
 *
 * @author lw
 * @since 2019/5/26 23:40
 */
@Service
public class DefaultTableImportListener implements ITableImportListener {
    @Autowired
    private TableKeyService tableKeyService;
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
        if (insert) {

        } else if (version) {
            /**
             * 获取到infoCode 根据infoCode找到version
             */
            //这里est系统的infoCode都是在版本对象的resource_id里面
            TableParamConfig tableParamConfig = TableDataCodeCacheManager.tableParamConfigs.get(tableName);
            Integer resource = Integer.parseInt(TableDataCodeCacheManager.codeToId.get(tableCodeUuid).get(data.get(tableParamConfig.getResourceIdColumn())));
            Integer oldVersion = jdbcTemplate.queryForObject("select "+tableParamConfig.getVersionColumn()+" from " + tableName + " where  "+tableParamConfig.getResourceIdColumn()+"=" + resource + " order by "+tableParamConfig.getVersionColumn()+" desc limit 1", Integer.class);
            Integer newKey = tableKeyService.getNewKey(jdbcTemplate);
            String key = TableDataCodeCacheManager.tableKey.get(tableName);
            data.put("version",++oldVersion);
            data.put(key,newKey);
            conversionKey(tableName, data, codeToId);
        } else if (update) {
            /**
             * 拿到之前的数据ID做更新操作
             */
            if (!StringUtils.isEmpty(codeColumns)) {
                String[] split = codeColumns.split(TableConstants.ID_SEPATATION);
                StringBuffer codeBuffer = new StringBuffer(tableName + TableConstants.CODE_SEPATATION);
                for (String str : split) {
                    String o = data.get(str).toString();
                    codeBuffer.append(o + TableConstants.CODE_SEPATATION);
                }
                code = codeBuffer.toString().substring(0, codeBuffer.length() - 1);
            }
            /**
             * 删除以前的数据
             */
            String keyName = TableDataCodeCacheManager.tableKey.get(tableName);
            Integer oldDataId = Integer.parseInt(codeToId.get(code));
            JdbcTemplateService.jdbcTemplate.update("delete from " + tableName + " where " + keyName + "=" + oldDataId);
            /**
             * 做数据转换处理
             */
            data.put(keyName,oldDataId);
            conversionKey(tableName, data, codeToId);

        } else {
            throw new TableDataImportException("数据导入异常，DataCheckResult 不存在该数据类型 tableName:" + tableName + " data:" + JSON.toJSONString(data));
        }
    }


    private void conversionKey(String tableName, Map<String, Object> data, Map<String, String> codeToId) {
        List<TableConversionKey> tableConversionKeys = TableDataCodeCacheManager.tableConversionKeys.get(tableName);
        tableConversionKeys.forEach(x -> {
            String conversionKey = x.getConversionKey().split(TableConstants.CODE_SEPATATION)[1];
            /**
             *  如果是版本类型的数据在监听器后置处理在做ID更新
             */
            if (x.isJsonObject()) {
                return;
            }
            DataConverRuleEngineUtils.setPropertyTable(data,conversionKey,null,codeToId,null);
        });
    }

    @Override
    public void after(String tableName, Map<String, Object> data) {
        String uuid = TablePropertiesThreadLocalHolder.getProperties(TableConstants.TABLE_CODE_UUID);
        TableCodeConfig tableCodeConfig = TableDataCodeCacheManager.tableCodeConfigs.get(tableName);
        if(null==tableCodeConfig)return;
        String key = TableDataCodeCacheManager.tableKey.get(tableName);
        TableDataExpOrImpService.addCache(uuid,tableCodeConfig,key,tableCodeConfig.getColumns(),data);
    }

    @Override
    public int order() {
        return order;
    }
}
