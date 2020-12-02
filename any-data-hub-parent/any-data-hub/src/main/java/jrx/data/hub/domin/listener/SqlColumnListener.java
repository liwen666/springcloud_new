package jrx.data.hub.domin.listener;

import jrx.anyest.table.listener.ISqlColumnBuilderListener;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 描述
 * </p>
 *
 * @author lw
 * @since 2019/5/26 23:40
 */
@Service
public class SqlColumnListener implements ISqlColumnBuilderListener {
    @Override
    public Object listener(String columnName, Object value) {
        return value;
    }
}