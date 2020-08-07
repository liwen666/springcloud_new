package jrx.anyest.table.listener;

import com.alibaba.fastjson.JSON;
import jrx.anyest.table.constant.TableConstants;
import jrx.anyest.table.exception.TableDataImportException;
import jrx.anyest.table.jpa.dto.DataCheckResult;
import jrx.anyest.table.service.JdbcTemplateService;
import jrx.anyest.table.service.TableDataCodeCacheManager;
import jrx.anyest.table.service.TablePropertiesThreadLocalHolder;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.sql.SQLException;
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
    @Override
    public void before(String tableName, Map<String, Object> data, DataCheckResult dataCheckResult) {
        String code=null;
        String codeColumns = TableDataCodeCacheManager.tableCodeConfigs.get(tableName) == null ? null : TableDataCodeCacheManager.tableCodeConfigs.get(tableName).getColumns();
        String tableCodeUuid = TablePropertiesThreadLocalHolder.getProperties("table_code_uuid");
        Map<String, String> stringStringMap = TableDataCodeCacheManager.codeToId.get(tableCodeUuid);
        boolean insert = dataCheckResult.getInsertDataMap().get(tableName).contains(data);
        boolean version = dataCheckResult.getVersionDataMap().get(tableName).contains(data);
        boolean update = dataCheckResult.getUpdateDataMap().get(tableName).contains(data);
        if (insert) {
        } else if (version) {
        } else if (update) {
            /**
             * 拿到之前的数据ID做更新操作
             */
            if (!StringUtils.isEmpty(codeColumns)) {
                String[] split = codeColumns.split(TableConstants.ID_SEPATATION);
                StringBuffer codeBuffer = new StringBuffer(tableName + TableConstants.CODE_SEPATATION);
                for (String str : split) {
                    Object o = data.get(str);
                    if("meta_category".equals(tableName)&&"parent_id".equals(str)&&(Integer) o!=0){
                      o=  TableDataCodeCacheManager.idToCode.get(tableCodeUuid).get(tableName+ TableConstants.CODE_SEPATATION+o);
                    }
                    codeBuffer.append( o.toString()+ TableConstants.CODE_SEPATATION);
                }
                code=codeBuffer.toString().substring(0,codeBuffer.length()-1);
            }
            /**
             * 删除以前的数据
             */
            String keyName = TableDataCodeCacheManager.tableKey.get(tableName);
            Integer oldDataId = Integer.parseInt(stringStringMap.get(code).split(TableConstants.CODE_SEPATATION)[1]);
            try {
                int transactionIsolation =  JdbcTemplateService.jdbcTemplate.getDataSource().getConnection().getTransactionIsolation();
                System.out.println(transactionIsolation);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            JdbcTemplateService.jdbcTemplate.update("delete from "+tableName+" where "+keyName+"="+oldDataId);
        } else {
            throw new TableDataImportException("数据导入异常，DataCheckResult 不存在该数据类型 tableName:" + tableName + " data:" + JSON.toJSONString(data));
        }
    }

    @Override
    public void after(String tableName, Map<String, Object> data) {

    }
}
