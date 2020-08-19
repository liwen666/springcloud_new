package jrx.anyest.table.utils;

import com.google.common.base.CaseFormat;
import jrx.anyest.table.exception.TableDataConversionException;
import jrx.anyest.table.jpa.entity.TableCodeConfig;
import jrx.anyest.table.listener.ISqlColumnBuilderListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

/**
 * <p>
 * 描述
 * </p>
 *
 * @author lw
 * @since 2019/5/26 23:40
 */
public class TableSqlBuilder {

    public   static ISqlColumnBuilderListener iSqlColumnBuilderListener;

    public static Logger logger = LoggerFactory.getLogger(TableSqlBuilder.class);

    public static String getSql(Collection collection){
        if(CollectionUtils.isEmpty(collection)){
            return "''";
        }
        StringBuffer stringBuffer = new StringBuffer();
        collection.stream().distinct().forEach(e -> {
                stringBuffer.append(e + ",");

        });
        return stringBuffer.toString().substring(0, stringBuffer.length() - 1);
    }
    public static String getIgnore(Collection collection){
        StringBuffer stringBuffer = new StringBuffer();
        collection.stream().distinct().forEach(e -> {
            if(e instanceof  Integer){
                stringBuffer.append(e + ",");
            }else{
                stringBuffer.append("'"+e+"',");
            }

        });
        return stringBuffer.toString().substring(0, stringBuffer.length() - 1);
    }

    /**
     * 转下划线
     * @param string
     * @return
     */
    public static String toUnderScore(String string) {
        return CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, string);
    }

    /**
     * 转驼峰
     * @param string
     * @return
     */
    public static String toLowerCamel(String string) {
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, string));
    }

    public static String getWhereSql(TableCodeConfig tableCodeConfig,Collection<String> whereSql, Map<String, Object> whereParam) {
        String ignoreColumnName = tableCodeConfig.getIgnoreColumnName();
        StringBuffer stringBuffer = new StringBuffer(" where 1=1 ");
        for(String column:whereSql){
            Object o = whereParam.get(column)==null?whereParam.get(toLowerCamel(column)):whereParam.get(column);
            if(null==o){
                throw new TableDataConversionException("code初始化异常，缺少必要的列查询条件，,column ->"+column+" tablename->"+tableCodeConfig.getTableCodeName());
            }
            if(!(o instanceof Integer)){
                stringBuffer.append(" and "+column+"='"+o+"' ");
            }else{
                stringBuffer.append(" and "+column+"="+o);
            }
        }
        if(!StringUtils.isEmpty(ignoreColumnName)){
            stringBuffer.append(" and "+ignoreColumnName+" not in ("+ TableSqlBuilder.getIgnore(Arrays.asList(tableCodeConfig.getIgnoreColumnValue().split(",")))+")");
        }
        return stringBuffer.toString();
    }


    public static Object getColumnVale(String tableName, String field, String s, Object v) {
        try {
            if(s.startsWith("datetime")||s.startsWith("date")||s.startsWith("timestamp")){
                if(v instanceof Long) return new Date((Long)v);
                return v;
            }
            if(s.startsWith("longtext")){
                return v;
            }
            if(null!=iSqlColumnBuilderListener){
                return iSqlColumnBuilderListener.listener(s,v);
            }
        } catch (Exception e) {
            logger.error("表少字段 tableName:{},field:{}",tableName,field);
        }
        return v;
    }
}
