package jrx.anyest.table.jpa.sql;

import com.temp.jpa.jpa.entity.TableEntityConversionRule;
import com.temp.jpa.jpa.enums.DataConversionModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>
 * 描述
 * </p>
 *
 * @author lw
 * @since 2019/5/26 23:40
 */
public class TableDateCodeCacheManager {
    public static Logger logger = LoggerFactory.getLogger(TableDateCodeCacheManager.class);

    /**
     * 数据导出id转换成Code数据集合
     */
    public static Map<String, Map<String, String>> idToCode = new ConcurrentHashMap<>();
    /**
     * 数据导出code转换成id数据集合
     */
    public static Map<String,Map<String, String>> codeToId = new ConcurrentHashMap<>();




}
