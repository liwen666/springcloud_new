package jrx.anyest.table.utils;

import com.google.common.base.CaseFormat;
import jrx.anyest.table.exception.TableDataConversionException;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 描述
 * </p>
 *
 * @author lw
 * @since 2019/5/26 23:40
 */
public class TableSqlBulider {
    public static String getSql(Collection collection){
        StringBuffer stringBuffer = new StringBuffer();
        collection.stream().distinct().forEach(e -> {
            stringBuffer.append(e + ",");
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

    public static String getWhereSql(Collection<String> whereSqls, Map<String, Object> whereParam) {
        StringBuffer stringBuffer = new StringBuffer(" where 1=1 ");
        for(String column:whereSqls){
            String s = toLowerCamel(column);
            Object o = whereParam.get(s);
            if(null==o){
                throw new TableDataConversionException("code初始化异常，缺少必要的列查询条件，column ->"+column);
            }
            if(!(o instanceof Integer)){
                stringBuffer.append(" and "+column+"='"+o+"' ");
            }else{
                stringBuffer.append(" and "+column+"="+o);
            }
        }
        return stringBuffer.toString();
    }
}
