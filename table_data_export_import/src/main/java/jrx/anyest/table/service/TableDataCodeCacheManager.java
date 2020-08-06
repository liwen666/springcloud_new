package jrx.anyest.table.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import jrx.anyest.table.jpa.entity.TableCodeConfig;
import jrx.anyest.table.jpa.entity.TableCodeRelation;
import jrx.anyest.table.jpa.entity.TableImportSort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
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
public class TableDataCodeCacheManager {
    public static Logger logger = LoggerFactory.getLogger(TableDataCodeCacheManager.class);

    /**
     * 数据导出id转换成Code数据集合
     */
    public static Map<String, Map<String, String>> idToCode = new ConcurrentHashMap<>();
    /**
     * 数据导出code转换成id数据集合
     */
    public static Map<String, Map<String, String>> codeToId = new ConcurrentHashMap<>();
    /**
     * 表code配置信息
     */
    public static  Map<String, TableCodeConfig> tableCodeConfigs = new ConcurrentHashMap<>();

    /**
     * 数据导出code转换成id数据集合
     */
    public static Map<String, List<TableCodeRelation>> relations = new ConcurrentHashMap<>();
    /**
     * 表数据导入顺序
     */
    public static List<TableImportSort> tableImportSorts = Lists.newArrayList();


    /**
     * 表主键
     */
    public static Map<String, String> tableKey;

    /**
     * 表字段类型
     */
    public static Map<String, Map<String, String>> tableColumns = Maps.newConcurrentMap();


}
