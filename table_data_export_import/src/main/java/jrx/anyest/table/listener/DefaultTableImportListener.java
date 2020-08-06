package jrx.anyest.table.listener;

import com.alibaba.fastjson.JSON;
import jrx.anyest.table.constant.TableConstants;
import jrx.anyest.table.exception.TableDataImportException;
import jrx.anyest.table.jpa.dto.DataCheckResult;
import jrx.anyest.table.service.TableDataCodeCacheManager;
import jrx.anyest.table.service.TablePropertiesThreadLocalHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
        String codeColumns = TableDataCodeCacheManager.tableCodeConfigs.get(tableName) == null ? null : TableDataCodeCacheManager.tableCodeConfigs.get(tableName).getColumns();

        String tableCodeUuid = TablePropertiesThreadLocalHolder.getProperties("table_code_uuid");
        Map<String, String> stringStringMap = TableDataCodeCacheManager.codeToId.get(tableCodeUuid);
        boolean insert = dataCheckResult.getInsertDataMap().get(tableName).contains(data);
        boolean version = dataCheckResult.getVersionDataMap().get(tableName).contains(data);
        boolean update = dataCheckResult.getUpdateDataMap().get(tableName).contains(data);
        if (insert) {
        } else if (version) {
        } else if(update) {
            /**
             * 拿到之前的数据ID做更新操作
             */
//            String
            if(!StringUtils.isEmpty(codeColumns)){
                String[] split = codeColumns.split(TableConstants.CODE_SEPATATION);
            }
            /**
             * 删除以前的数据
             */
        }
        throw new TableDataImportException("数据导入异常，DataCheckResult 不存在该数据类型 tableName:"+tableName +" data:"+ JSON.toJSONString(data));
    }

    @Override
    public void after(String tableName, Map<String, Object> data) {

    }
}
