package jrx.data.hub.domin.handler;

import jrx.anyest.table.constant.TableConstants;
import jrx.anyest.table.service.TableDataCodeCacheManager;
import jrx.anyest.table.service.TablePropertiesThreadLocalHolder;
import jrx.anyest.table.service.handler.DefaultTableDataHandler;
import jrx.data.hub.domain.constant.TableNameConstant;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 描述
 * </p>
 *
 * @author LW
 * @since 2020/12/2  18:14
 */
@Component
public class AnyDataHubHandler extends DefaultTableDataHandler {
    @Override
    public String codeProcess(String tableName, String column, Object value) {
        String tableCodeUuid = TablePropertiesThreadLocalHolder.getProperties(TableConstants.TABLE_CODE_UUID);
        //分类表的二次code转换
        if (TableNameConstant.META_JOB_OBJECT.equals(tableName) && "resource_id".equals(column)) {
            return TableDataCodeCacheManager.idToCode.get(tableCodeUuid).get(TableNameConstant.META_JOB_INFO + TableConstants.CODE_SEPATATION + value);
        }
        return value.toString();
    }
}
