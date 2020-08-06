package jrx.anyest.table.service;

import com.google.common.base.CaseFormat;
import com.google.common.collect.Lists;
import jrx.anyest.table.jpa.sql.PackageScanUtil;
import jrx.anyest.table.utils.TableSqlBulider;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;


/**
 * author lw
 * date 2019/8/30  19:54
 * discribe
 */
public class JdbcTemplateService {
    public static JdbcTemplate jdbcTemplate;
    private static final Logger logger = LoggerFactory.getLogger(JdbcTemplateService.class);

    static {

    }

    public static void initTableBySql(String sqlName) {
        try {
            /**
             * 初始化表结构
             */
            Resource resource = PackageScanUtil.findResource("sql", sqlName, false);
            String initTable = loadSqlSource(resource);
            executeSource(initTable);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

    }

    public static void save(Object object,String tableName)  {
        Field[] fields = object.getClass().getDeclaredFields();
        List<String> cols = Arrays.asList(fields).stream().filter(e -> !e.getName().equals("log")).map(e -> e.getName()).collect(Collectors.toList());
        Object[] values = Arrays.asList(fields).stream().filter(e -> !e.getName().equals("log")).map(e -> {
            try {
                e.setAccessible(true);
                return e.get(object);
            } catch (IllegalAccessException e1) {
                logger.error(e1.getMessage());
                return null;
            }
        }).collect(Collectors.toList()).toArray(new Object[0]);
        String insertSql = getInsertSql(cols, tableName);
        jdbcTemplate.update(insertSql, values);

    }

    public synchronized static void saveByMap(String tableName, Map<String,Object> data)  {
        Map<String, String> stringIntegerMap = TableDataCodeCacheManager.tableColumns.get(tableName);
        ArrayList<String> columns = Lists.newArrayList();
        ArrayList<Object> values = Lists.newArrayList();
        data.forEach((k,v)->{
            columns.add(k);
            /**
             * 根据表结构构建数据对象
             */
            String s = stringIntegerMap.get(k);
           Object value = TableSqlBulider.getColumnVaule(s,v);
            values.add(value);
            });
        String insertSql = getInsertSql(columns, tableName);
        jdbcTemplate.update(insertSql, values.toArray());

    }


    public  static String getInsertSql(Collection<String> columnNames, String tableName) {
        StringBuffer insert = new StringBuffer(" INSERT INTO " + tableName + "(");
        StringBuffer value = new StringBuffer(" VALUES (");

        for (String column : columnNames) {
            String to = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, column);
            insert.append(to + " , ");
            value.append("?,");
        }
        value.delete(value.length() - 1, value.length());
        insert.delete(insert.length() - 2, insert.length());
        insert.append(")");
        insert.append(value + ")");
        return insert.toString();
    }

    private static void executeSource(String sqlSource)  {
        String[] split = sqlSource.split(";");
        for (String sql : split) {
            try {
                if (!StringUtils.isEmpty(sql)) {
                    jdbcTemplate.execute(sql);
                }

            } catch (Exception e) {
                logger.error(e.getMessage()+"---------执行的错误sql是:{}-----------", sql);
            }
        }
    }

    private static String loadSqlSource(Resource sqlResource) throws Exception {
        InputStream sqlFileIn = null;

        try {
            sqlFileIn = sqlResource.getInputStream();
            StringBuffer sqlSb = new StringBuffer();
            byte[] buff = new byte[1024];
            int byteRead = 0;
            while ((byteRead = sqlFileIn.read(buff)) != -1) {
                sqlSb.append(new String(buff, 0, byteRead, "utf8"));
            }
            String sqlArr = sqlSb.toString();
            String source = sqlArr.replaceAll("--.*", "").trim();
            return source;
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        } finally {
            sqlFileIn.close();
        }
    }


}
