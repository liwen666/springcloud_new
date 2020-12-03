package jrx.anyest.table.listener;

import com.alibaba.fastjson.JSON;
import jrx.anyest.table.constant.TableConstants;
import jrx.anyest.table.exception.TableDataConversionException;
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
    private int order = 0;


    @Override
    public int before(String tableName, Map<String, Map<String, Object>> data, Map<String, String> idToCode, JdbcTemplate jdbcTemplate, Map<String, List<TableConversionKey>> tableConversionKeys) {
        //如果数据没有转换规则直接返回数据大小
        if (null == tableConversionKeys.get(tableName)) {
            log.warn(" ----before id-code tableName:{} 没有转换规则！",tableName);
            return data.size();
        }
        //code转换成功数
        AtomicInteger successNum = new AtomicInteger(0);

        /**
         * 查询转换key
         */
        log.info("----before id-code tableName:{},dataSize:{}", tableName, data.size());
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
                                /**
                                 *  如果是字段表 他的resourceID 是3个表中的一个
                                 */
                                codeTable = getTableName(idToCode, v, codeTable, conversion);
                                DataConverRuleEngineUtils.setPropertyTable(stringObjectMap, conversion, null, idToCode, codeTable + TableConstants.CODE_SEPATATION);
                            });
                        }
                        DataConverRuleEngineUtils.setPropertyTable(v, conversionKey, JSON.toJSONString(stringObjectMap), null, codeTableName + TableConstants.CODE_SEPATATION);
                        return;
                    }
                        /**
                         *  如果是字段表 他的resourceID 是3个表中的一个
                         */
                    codeTableName = getTableName(idToCode, v, codeTableName, conversionKey);
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

    private String getTableName(Map<String, String> idToCode, Map<String, Object> v, String codeTable, String conversion) {
        if (codeTable.contains("|")) {
            String[] split = codeTable.split("\\|");
            Object tableProperty1 = DataConverRuleEngineUtils.getTableProperty(v, conversion);
            if (null == tableProperty1) {
                throw new TableDataConversionException("---id-code---转换异常,值不能为NULL" + codeTable + " conversionKey:" + conversion);
            }
            String relTableName = null;
            for (String s : split) {
                String code = idToCode.get(s + TableConstants.CODE_SEPATATION + tableProperty1);
                if (!StringUtils.isEmpty(code)) {
                    relTableName = s;
                    break;
                }
            }
            if (StringUtils.isEmpty(relTableName)) {
                throw new TableDataConversionException("---id-code---转换异常，未查询到id对应的表名" + codeTable + "  id:" + tableProperty1);
            }
            codeTable = relTableName;
        }
        return codeTable;
    }

    private Map<String, Object> cycleConversion(String codeTableName, Map<String, Object> v, String conversionKey, JdbcTemplate jdbcTemplate) {
        return null;
    }



    @Override
    public int order() {
        return order;
    }

    @Override
    public void after(String k, Map<String, Map<String, Object>> v, JdbcTemplate jdbcTemplate) {

    }
}
