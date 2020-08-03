package jrx.anyest.table.service;

import com.google.common.collect.Maps;
import jrx.anyest.table.jpa.dao.TableCodeConfigRepository;
import jrx.anyest.table.jpa.dao.TableCodeRelationRepository;
import jrx.anyest.table.jpa.dao.TableConversionKeyRepository;
import jrx.anyest.table.jpa.dao.TableMarkInitRepository;
import jrx.anyest.table.jpa.dto.CodeCheck;
import jrx.anyest.table.jpa.dto.TableDataImportOrExpResult;
import jrx.anyest.table.jpa.entity.TableCodeConfig;
import jrx.anyest.table.jpa.entity.TableCodeRelation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <p>
 * 描述
 * </p>
 * 数据导入导出
 *
 * @author lw
 * @since 2019/5/26 23:40
 */

@Service
public class TableDataExpOrImpService {

    @Autowired
    private TableCodeConfigRepository tableCodeConfigRepository;

    @Autowired
    private TableCodeRelationRepository tableCodeRelationRepository;
    @Autowired
    private TableConversionKeyRepository tableConversionKeyRepository;
    @Autowired
    private TableMarkInitRepository tableMarkInitRepository;


    public static Logger logger = LoggerFactory.getLogger(TableDataExpOrImpService.class);

    /**
     * 表数据导出前的code检查
     *
     * @return
     */
    public List<TableDataImportOrExpResult<CodeCheck>> checkCode() {
        Map<String, Map<String, Object>> errorData = Maps.newConcurrentMap();
        /**
         * 查询会被引用的到的数据
         */
        List<TableCodeConfig> all = tableCodeConfigRepository.findAll();
        all.stream().filter(e -> e.isUsed()).collect(Collectors.toList());
        for (TableCodeConfig tableCodeConfig : all) {
            String ruleinfo = getCheckSql(tableCodeConfig);
            List<Map<String, Object>> maps = JdbcTemplateService.jdbcTemplate.queryForList(ruleinfo);
            maps.forEach(e -> {
                if ((Long) e.get("num") != 1) {
                    errorData.put(tableCodeConfig.getTableCodeName(), e);
                }
            });
        }
        /**
         * 查询出有问题的数据
         */
        for (Map.Entry entry : errorData.entrySet()) {


        }


        return null;
    }

    public List<TableDataImportOrExpResult> initCodeCache() {
        return null;
    }


    private String getErrorDataSql(TableCodeConfig tableCodeConfig, Map<String, Object> param) {
        String[] split = tableCodeConfig.getColumns().split(",");
        StringBuffer stringBuffer = new StringBuffer();
        Arrays.stream(split).distinct().forEach(e -> {
            stringBuffer.append(e + ",");
        });
        String coloums = stringBuffer.toString().substring(0, stringBuffer.length() - 1);
        StringBuffer checkSql = new StringBuffer("SELECT *  FROM " + tableCodeConfig.getTableCodeName() + " group by  " + coloums);
        checkSql.append(" ORDER BY num;");
        return checkSql.toString();
    }

    private String getCheckSql(TableCodeConfig tableCodeConfig) {
        String[] split = tableCodeConfig.getColumns().split(",");
        StringBuffer stringBuffer = new StringBuffer();
        Arrays.stream(split).distinct().forEach(e -> {
            stringBuffer.append(e + ",");
        });
        String coloums = stringBuffer.toString().substring(0, stringBuffer.length() - 1);
        StringBuffer checkSql = new StringBuffer("SELECT count(1) num ," + coloums + " FROM `res_rule_info` group by  " + coloums);
        checkSql.append(" ORDER BY num;");
        return checkSql.toString();
    }


    /**
     * 为数据添加唯一性标识
     */
    public void initTableMark() {
//        List<TableMarkInit> collect = tableMarkInitRepository.findAll().stream().filter(e -> e.isUsed()).collect(Collectors.toList());
//        for (TableMarkInit tableMarkInit : collect) {
//            String markInitSql = getMarkSql(tableMarkInit.getTableName());
//            JdbcTemplateUtils.jdbcTemplate.execute(markInitSql);
//            String primaryKey =JdbcTemplateUtils.jdbcTemplate.queryForObject("SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.KEY_COLUMN_USAGE WHERE TABLE_NAME='"+tableMarkInit.getTableName()+"' limit 1",String.class);
//            List<Long> longs = JdbcTemplateUtils.jdbcTemplate.queryForList("select " + primaryKey + " from " + tableMarkInit.getTableName() + " where mark_id is null", Long.class);
//
//
//        }
    }

    private String getMarkSql(String tableName) {
        return "ALTER table " + tableName + " add mark_id varchar(32);";
    }

    public void listAllRelationData(String tableName, Integer dataId, Map<String, Map<String, String>> dataMap) {
        /**
         * 查询此表关联数据表
         */
//        tableCodeRelationRepository.

    }

    public void initRelationCache() {
        TableDataCodeCacheManager.relations = tableCodeRelationRepository.findAll().stream().filter(e -> e.isUsed()).collect(Collectors.groupingBy(TableCodeRelation::getPrimaryTableName));
    }
}
