package jrx.anyest.table.jpa.util;

import com.alibaba.druid.pool.DruidDataSource;
import com.google.common.base.CaseFormat;
import jrx.anyest.table.jpa.entity.TableCodeRelation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * author lw
 * date 2019/8/30  19:54
 * discribe
 */
public class JdbcTemplateUtils {
    public static JdbcTemplate jdbcTemplate;
    private static final Logger LOGGER = LoggerFactory.getLogger(JdbcTemplateUtils.class);

    static {
        DruidDataSource dataSource = new DruidDataSource();
        String host;
        String port;
        String user;
        String password;
        String jdbc_options;
        String schema_database;
        host = "jdbc:mysql://192.168.60.136:3306/anyest3_financial_cloud_15029?serverTimezone=Hongkong&useUnicode=true&useSSL=false&characterEncoding=utf8";
        //设置连接参数
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://192.168.60.136:3306/anyest3_financial_cloud_15029?serverTimezone=Hongkong&useUnicode=true&useSSL=false&characterEncoding=utf8");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        //配置初始化大小、最小、最大
        dataSource.setInitialSize(5);
        dataSource.setMinIdle(1);
        dataSource.setMaxActive(20);
        //连接泄漏监测
        dataSource.setRemoveAbandoned(true);
        dataSource.setRemoveAbandonedTimeout(30);
        //配置获取连接等待超时的时间
        dataSource.setMaxWait(20000);
        //配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
        dataSource.setTimeBetweenEvictionRunsMillis(20000);
        //防止过期
        dataSource.setValidationQuery("SELECT 'x'");
        dataSource.setTestWhileIdle(true);
        dataSource.setTestOnBorrow(true);
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public static void intiBootstapBySql(String sqlName) {
        try {
            /**
             * 初始化表结构
             */
            Resource resource = PackageScanUtil.findResource("sql", sqlName, false);
            String initTable = loadSqlSource(resource);
            executeSource(initTable);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void initBootstrapt(boolean onyddl) throws IOException, SQLException, InterruptedException {
        TableCodeRelation tableCodeRelation = new TableCodeRelation();
        tableCodeRelation.setPrimaryCodeKey("projectId");
        tableCodeRelation.setPrimaryTableChinaName("规则信息表");
        tableCodeRelation.setPrimaryTableName("res_rule_info");
        tableCodeRelation.setSlaveTableChinaName("项目表");
        tableCodeRelation.setPrimaryTableName("res_resource_project");
        Field[] fields = tableCodeRelation.getClass().getDeclaredFields();
        String[] cols = Arrays.asList(fields).stream().filter(e -> !e.getName().equals("log")).map(e -> e.getName()).collect(Collectors.toList()).toArray(new String[0]);
        Object[] vals = Arrays.asList(fields).stream().filter(e -> !e.getName().equals("log")).map(e -> {
            try {
                e.setAccessible(true);
                if (e.getName().equals("databaseName")) {
                    return e.get(tableCodeRelation).toString().replace("`", "");
                }
                return e.get(tableCodeRelation);
            } catch (IllegalAccessException e1) {
                e1.printStackTrace();
                return null;
            }
        }).collect(Collectors.toList()).toArray(new Object[0]);
        String insertSql = getInsertSql(cols, "bootstrap");
        jdbcTemplate.update(insertSql, vals);

    }


    public synchronized static String getInsertSql(String[] columnNames, String tableName) {
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

    public static void initTableAndKafkaColumn() {
        try {
            /**
             * 初始化表结构
             */
            Resource resource = PackageScanUtil.findResource("sql", "maxwell_schema_all.sql", false);
            String initTable = loadSqlSource(resource);
            executeSource(initTable);
            /**
             * 初始分区字段
             */
            List<Map<String, Object>> maps = jdbcTemplate.queryForList("select * from maxwell_partition_rule ");
            if (CollectionUtils.isEmpty(maps)) {
                Resource coloumSource = PackageScanUtil.findResource("sql", "maxwell_partition_rule.sql", false);
                String initColoum = loadSqlSource(coloumSource);
                executeSource(initColoum);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void executeSource(String sqlSource) throws Exception {
        String[] split = sqlSource.split(";");
        for (String sql : split) {
            try {
                if (!StringUtils.isEmpty(sql)) {
                    jdbcTemplate.execute(sql);
                }

            } catch (Exception e) {
                e.printStackTrace();
                LOGGER.error("---------执行的错误sql是:{}-----------", sql);
                throw e;
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


    public List<Map<String, Object>> listColoums() {
        return jdbcTemplate.queryForList("select * from maxwell_partition_rule");
    }

}
