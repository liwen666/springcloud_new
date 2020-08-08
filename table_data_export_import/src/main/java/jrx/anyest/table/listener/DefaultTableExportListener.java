package jrx.anyest.table.listener;

import com.alibaba.fastjson.JSON;
import jrx.anyest.table.constant.TableConstants;
import jrx.anyest.table.exception.TableDataImportException;
import jrx.anyest.table.jpa.dao.TableConversionKeyRepository;
import jrx.anyest.table.jpa.dto.DataCheckResult;
import jrx.anyest.table.jpa.entity.TableConversionKey;
import jrx.anyest.table.service.JdbcTemplateService;
import jrx.anyest.table.service.TableDataCodeCacheManager;
import jrx.anyest.table.service.TablePropertiesThreadLocalHolder;
import jrx.anyest.table.utils.DataConverRuleEngineUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * <p>
 * 描述
 * </p>
 *
 * @author lw
 * @since 2019/5/26 23:40
 */
@Slf4j
@Service
public class DefaultTableExportListener implements ITableExportListener {
    @Autowired
    private TableConversionKeyRepository tableConversionKeyRepository;

    @Override
    public int before(String tableName, Map<String, Map<String, Object>> data, Map<String, String> idToCode, JdbcTemplate jdbcTemplate, Map<String, List<TableConversionKey>> tableConversionKeys) {
        //如果数据没有转换规则直接返回数据大小
        if (null == tableConversionKeys.get(tableName)) {
            log.warn(" ----before id-code tableName:meta_category,datasize:1 没有转换规则！");
            return data.size();
        }
        //code转换成功数
        AtomicInteger successNum = new AtomicInteger(0);
        /**
         * 查询转换key
         */
        log.info("----before id-code tableName:{},datasize:{}", tableName, data.size());
        data.forEach((k, v) -> {
            tableConversionKeys.get(tableName).forEach(x -> {
                try {
                    String codeTableName = x.getConversionKey().split(TableConstants.CODE_SEPATATION)[0];
                    String conversionKey = x.getConversionKey().split(TableConstants.CODE_SEPATATION)[1];
//                判断 这个key对应的数据是否是个版本型的数据
                    if (x.isJsonObject()) {
                        Object tableProperty = DataConverRuleEngineUtils.getTableProperty(v, conversionKey);
                        if (null == tableProperty) {
                            return;
                        }
                        String codeTableKey = TableDataCodeCacheManager.tableKey.get(codeTableName);
                        Map<String, Object> stringObjectMap = jdbcTemplate.queryForMap("select * from " + codeTableName + "  where " + codeTableKey + "=?", tableProperty);
                        // 需要对关联数据做code转换处理  TODO 不支持级联的版本数据继续级联版本数据，
//                        Map<String, Object> dataJsonObj = cycleConversion(codeTableName, v, conversionKey, jdbcTemplate);
                        List<TableConversionKey> tableConversionKeys1 = tableConversionKeys.get(codeTableName);
                        if (tableConversionKeys1 != null) {
                            tableConversionKeys1.forEach(w -> {
                                String codeTable = w.getConversionKey().split(TableConstants.CODE_SEPATATION)[0];
                                String conversion = w.getConversionKey().split(TableConstants.CODE_SEPATATION)[1];
                                DataConverRuleEngineUtils.setPropertyTable(stringObjectMap, conversion, null, idToCode, codeTable + TableConstants.CODE_SEPATATION);
                            });
                        }
                        DataConverRuleEngineUtils.setPropertyTable(v, conversionKey, JSON.toJSONString(stringObjectMap), null, codeTableName + TableConstants.CODE_SEPATATION);
                        return;
                    }
                    DataConverRuleEngineUtils.setPropertyTable(v, conversionKey, null, idToCode, codeTableName + TableConstants.CODE_SEPATATION);
                } catch (Exception e) {
                    log.error("-----id-code error tableName:{} dataId:{} dataKey:{} errorMsg:{}", tableName, k, x.getConversionKey(), e.getClass().getName() + e.getMessage());
                    successNum.decrementAndGet();
                }
            });
            successNum.incrementAndGet();
        });
        return successNum.get();
    }

    private Map<String, Object> cycleConversion(String codeTableName, Map<String, Object> v, String conversionKey, JdbcTemplate jdbcTemplate) {
        return null;
    }


    @Override
    public void after(String tableName, Map<String, Object> data) {

    }
}
