package jrx.anyest.table.service;

import jrx.anyest.table.jpa.enums.HandlerParam;

import java.util.Map;

/**
 * <p>
 * 描述
 * </p>
 *
 * @author tx
 * @since 2019/5/26 23:40
 */
public interface TableDataHandler {
    /**
     * 数据处理
     */

    void conversionData(Map data, HandlerParam param);
}
