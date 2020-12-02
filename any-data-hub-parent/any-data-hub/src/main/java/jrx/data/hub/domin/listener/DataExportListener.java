package jrx.data.hub.domin.listener;

import jrx.anyest.table.jpa.entity.TableConversionKey;
import jrx.anyest.table.listener.ITableExportListener;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

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
public class DataExportListener implements ITableExportListener {
    @Override
    public int before(String tableName, Map<String, Map<String, Object>> data, Map<String, String> idToCode, JdbcTemplate jdbcTemplate, Map<String, List<TableConversionKey>> tableConversionKeys) {
        return data.size();
    }

    @Override
    public void after(String tableName, Map<String, Map<String, Object>> data, JdbcTemplate jdbcTemplate) {

    }

    @Override
    public int order() {
        return 1;
    }
}
