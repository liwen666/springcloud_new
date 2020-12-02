package jrx.data.hub.domin.listener;

import com.alibaba.fastjson.JSONObject;
import jrx.anyest.table.constant.TableConstants;
import jrx.anyest.table.jpa.dao.TableHistoryDataRepository;
import jrx.anyest.table.jpa.dto.DataCheckResult;
import jrx.anyest.table.listener.ITableImportListener;
import jrx.anyest.table.service.TablePropertiesThreadLocalHolder;
import jrx.data.hub.domain.common.TenantIdProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

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
@Slf4j
public class DataImportListener implements ITableImportListener {
    @Autowired
    private TableHistoryDataRepository tableHistoryDataRepository;

    @Override
    public void before(String tableName, Map<String, Object> data, DataCheckResult dataCheckResult, JdbcTemplate jdbcTemplate) {
        String uuid = TablePropertiesThreadLocalHolder.getProperties(TableConstants.TABLE_CODE_UUID);
        String tenantId = TenantIdProvider.getTenantId();
//         * 将所有数据进行当前租户ID修改
        data.put("content_code", tenantId);
    }

    @Override
    public void after(String tableName, Map<String, Object> data) {

    }

    @Override
    public int order() {
        return 1;
    }

    /**
     * 返回0 数据会被抛弃
     * 1 数据会被保存数据库
     *
     * @param tableName
     * @param x
     * @param dataCheckResult
     * @param jdbcTemplate
     * @return
     */
    @Override
    public int filter(String tableName, JSONObject x, DataCheckResult dataCheckResult, JdbcTemplate jdbcTemplate) {
//        if (EngineAdminConstants.META_DATA_SOURCE_INFO.endsWith(tableName)) {
//            if(dataCheckResult.getUpdateDataMap().get(tableName).contains(x)){
//            log.info("该数据源已存在不允许导入！ sourceCode:{}",x.get("source_code"));
//                return 0;
//            }else{
//                x.put("db_config_json","{}");
//            }
//        }
        return 1;
    }
}
