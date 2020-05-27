package com.temp.jpa.jpa.enums;

import com.alibaba.fastjson.JSONArray;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.temp.jpa.jpa.enums.DataTimeFormat.*;

/**
 * |String|字符串|abc||
 * |Integer|整型|22||
 * |Long|长整型|98172122132||
 * |Double|小数浮点|32.212||
 * |Currency|金额|32.212|保留小数位2位或3位|
 * |Boolean|布尔型|true,false||
 * |Date|日期|日期格式的格式化信息可按照字段格式进行格式化|格式化日期|
 * |Timestamp|时间戳|1970年1月1日的相对时间毫秒数|相对时间|
 * |Map|k,v结构|{"k","v"}|java中的map,和一个json中的key,value数据对|
 * |Dict|字典类型|字典中的值保存在字段定义的词典数据项中||
 * |Enum|枚举类型|枚举类型是有限固定值||
 * |List|列表类型|["abc","bcd"],||
 * 值类型 枚举
 */
public enum ValueType {

    STRING(0, "字符串", String.class, "VARCHAR"),
    INTEGER(1, "整数", Integer.class, "INT"),
    Boolean(2, "布尔", Boolean.class, "INT"),
    LONG(3, "长整数", Long.class, "BIGINT"),
    DATE(4, "日期", Date.class, "DATE"),
    DOUBLE(5, "浮点数", Double.class, "DOUBLE"),
    Currency(6, "金额", BigDecimal.class, "DECIMAL"),
    DICTIONARY(7, "字典", List.class, "VARCHAR"),
    TIMESTAMP(8, "时间戳", Timestamp.class, "TIMESTAMP"),
    Maps(9, "键值对", Map.class, "TEXT"),
    Lists(10, "列表", List.class, "TEXT"),
    ListItem(11, "对象列表", List.class, "TEXT"),
    YEAR_MONTH(12, "年月(整型)", Integer.class, "INT"),
    YEAR_MONTH_DATE(13, "日期(整型)", Integer.class, "INT"),
    MONTH(14, "月份(整型)", Integer.class, "INT"),
    MONTH_DATE(15, "月份日期(整型)", Integer.class, "INT"),
    DAY_OF_MONTH(16, "月份中的日期(整型)", Integer.class, "INT"),
    OBJECT(17, "对象", Object.class, "VARCHAR");


    private Class clazz;

    private Integer id;

    private String description;

    private String dbFieldType;

    private static final Logger logger = LoggerFactory.getLogger(ValueType.class);

    ValueType(Integer id, String description, Class clazz, String dbFieldType) {
        this.id = id;
        this.description = description;
        this.clazz = clazz;
        this.dbFieldType = dbFieldType;
    }

    public Class getValueClazz() {
        return clazz;
    }

    public Integer getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getDbFieldType() {
        return dbFieldType;
    }

    public static ValueType getByDbFieldType(String dbFieldType) {
//        for (ValueType typeEnum : values()) {
//            if (dbFieldType.equals(typeEnum.dbFieldType)) {
//                return typeEnum;
//            }
//        }
//        return null;
        return Arrays.stream(values()).filter(it -> it.getDbFieldType().equals(dbFieldType)).findFirst().orElse(null);
    }

    public static ValueType getByValueType(String valueType) {
        return Arrays.stream(values()).filter(it -> it.name().equals(valueType)).findFirst().orElse(null);
    }

    public Object normalizeValue(Object value) {
        return this.normalizeValue(value, null);
    }

    public static Object normalizeValue(ValueType valueType, Object value) {
        return valueType.normalizeValue(value);
    }

    /**
     * 正规化字段值
     *
     * @param value
     * @param format
     * @return
     */
    public Object normalizeValue(Object value, String format) {
        Object nValue = value;
        try {
            switch (this) {
                case DATE:
                    nValue = normalDate(value, format);
                    break;
                case LONG:
                    nValue = normalLong(value);
                    break;
                case DOUBLE:
                    nValue = normalDouble(value);
                    break;
                case Boolean:
                    nValue = normalBoolean(value);
                    break;
                case INTEGER:
                    nValue = normalInteger(value);
                    break;
                case STRING:
                    nValue = normalString(value, format);
                    break;
                case Currency:
                    nValue = normalCurrency(value, format);
                    break;
                case TIMESTAMP:
                    nValue = normalTimeStamp(value);
                    break;
                case YEAR_MONTH:
                    nValue = normalIntMonth(value);
                    break;
                case YEAR_MONTH_DATE:
                    nValue = normalIntDate(value);
                    break;
                case Lists:
                    nValue = normalLists(value);
                    break;
                case DICTIONARY:
                case Maps:
                case ListItem:
                default:
                    nValue = value;
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return nValue;
    }

    /**
     * 日期整型格式
     *
     * @param value
     * @return
     */
    private Object normalIntDate(Object value) {

        if (value != null && value instanceof String && !value.equals("")) {
            return Integer.parseInt((String) value);
        }
        return value;
    }

    /**
     * 列表转换 （暂时只转字符串）
     *
     * @param value
     * @return
     */
    private Object normalLists(Object value) {
        if (value instanceof String) {
            return JSONArray.parseArray(value.toString());
        }
        return value;
    }


    /**
     * 月份整型格式
     *
     * @param value
     * @return
     */
    private Object normalIntMonth(Object value) {
        return value;
    }

    //出去后必定是Date/null
    private Object normalDate(Object value, String format) {
        try {
            if (value == null || value instanceof Date) {
                return value;
            } else if (value instanceof Number) {
                return new Date(((Number) value).longValue());
            } else if (value instanceof String) {
                if ("".equals(value)) {
                    return null;
                }
                DateTimeFormatter dateTimeFormatter;
                String v = value.toString();
                if (StringUtils.isNotEmpty(format) &&
                        //通过Feign转发的Date会变成默认的个数，再按原先的格式计算会有问题。
                        v.length() == format.length()
                ) {
                    dateTimeFormatter = DateTimeFormatter.ofPattern(format);
                } else {
                    //日期

                    if (v.length() == DataTimeFormat5.getFormat().length()) {
                        dateTimeFormatter = DateTimeFormatter.ofPattern(DataTimeFormat5.getFormat());
                        //日期时间
                    } else if (v.length() == DataTimeFormat9.getFormat().length()) {
                        dateTimeFormatter = DateTimeFormatter.ofPattern(DataTimeFormat9.getFormat());
                    } else if (v.length() == DataTimeFormat10.getFormat().length()) {
                        dateTimeFormatter = DateTimeFormatter.ofPattern(DataTimeFormat10.getFormat());
                    } else if (DataTimeFormat31.match(v)) {
                        dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
                    } else if (DataTimeFormat13.match(v)) {
                        dateTimeFormatter = DateTimeFormatter.ofPattern(DataTimeFormat13.getFormat());
                    } else if (DataTimeFormat11.match(v)) {
                        dateTimeFormatter = DateTimeFormatter.ofPattern(DataTimeFormat11.getFormat());
                    } else if (DataTimeFormat29.match(v)) {
                        dateTimeFormatter = DateTimeFormatter.ofPattern(DataTimeFormat29.getFormat());
                    } else {
                        dateTimeFormatter = DateTimeFormatter.ofPattern(DataTimeFormat30.getFormat());
                    }
                }

                ZoneId zone = ZoneId.systemDefault();
                //先尝试转localDateTime
                try {
                    //TODO  timestamp 转换的时候会丢失 时分秒，下边的判断先注释掉 -yuanchangyou
//                    if (DataTimeFormat.DataTimeFormat5.getFormat().length() <= v.length()) {
//                        LocalDate localDate = LocalDate.parse(value.toString(), dateTimeFormatter);
//                        Instant instant = localDate.atStartOfDay().atZone(zone).toInstant();
//                        return Date.from(instant);
//                    } else {
                    LocalDateTime localDateTime = LocalDateTime.parse(v, dateTimeFormatter);
                    Instant instant = localDateTime.atZone(zone).toInstant();
                    return Date.from(instant);
//                    }
                } catch (Exception e) {
                    try {
                        //失败再尝试转localDate
                        LocalDate localDate = LocalDate.parse(v, dateTimeFormatter);
                        Instant instant = localDate.atStartOfDay().atZone(zone).toInstant();

                        return Date.from(instant);
                    } catch (Exception e2) {
                    }
                }
                // TODO 失败考虑时间戳类型   -huangyong
//                        注意时间戳类型: 1581752356000
                try {
                    if (value.toString().startsWith("1") && value.toString().length() == 13) {
                        return new Date(Long.parseLong(value.toString()));
                    }
                } catch (Exception e3) {
                }
                return null;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /***
     * update by yuanchangyou
     *  返回类型  Date -> java.sql.timestamp
     *  原因：在自定义脚本Timestamp类型的参数 传的是java.sql.timestamp ,两边不一致。
     *
     * @param value
     * @return
     */
    private Object normalTimeStamp(Object value) {
        Date normalDate;
        try {
            if (value == null) {
                return null;
            } else if (value instanceof Number) {
                normalDate = new Date(((Number) value).longValue());
            } else if (value instanceof Date) {
                normalDate = (Date) value;
            } else if (value instanceof String) {
                /***
                 * 按normalDate 方式处理
                 */
                normalDate = (Date) normalDate(value, null);
//                return new Date(Long.parseLong(value.toString()));
            } else {
                return value;
            }
            //TODO ES 不支持存储timestamp ，这边先转成long 类型，看看后续会有什么影响
            if (normalDate != null) {
//                return new Timestamp(normalDate.getTime());
                //es
                return normalDate.getTime();
            }
            // 注意时间戳类型: 1581752356000
//            if(normalDate == null){
//                return  new Date(Long.parseLong(value.toString()));
//            }
        } catch (Exception e) {
            return value;
        }
        return null;
    }


    /***
     * TODO value 为null 返回为默认值，在字段计算出异常时，返回的默认值可能是错误的，这样会导致字段配置默认值不被使用，或者字段的更新方式被 受影响
     * TODO 需要把null 不处理 直接返回
     * add by yuanchangyou
     * @param value
     * @return
     */
    private Object normalInteger(Object value) {
        try {
            if (value == null) {
                return value;
            } else if (value instanceof Integer) {
                return value;
            } else if (value instanceof Number) {
                return ((Number) value).intValue();
            } else if (value instanceof String) {
                return Integer.parseInt(value.toString());
            } else {
                return Integer.valueOf(value.toString());
            }
        } catch (Exception e) {
            return 0;
        }
    }

    /***
     * TODO value 为null 返回为默认值，在字段计算出异常时，返回的默认值可能是错误的，这样会导致字段配置默认值不被使用，或者字段的更新方式被 受影响
     * TODO 需要把null 不处理 直接返回
     * add by yuanchangyou
     * @param value
     * @return
     */
    private Object normalLong(Object value) {
        try {
            if (value == null) {
                return value;
            } else if (value instanceof Long) {
                return value;
            } else if (value instanceof Number) {
                return ((Number) value).longValue();
            } else {
                return Long.valueOf(value.toString());
            }
        } catch (Exception e) {
            return 0L;
        }
    }

    /***
     * TODO value 为null 返回为默认值，在字段计算出异常时，返回的默认值可能是错误的，这样会导致字段配置默认值不被使用，或者字段的更新方式被 受影响
     * TODO 需要把null 不处理 直接返回
     * add by yuanchangyou
     * @param value
     * @return
     */
    private Object normalDouble(Object value) {
        try {
            if (value == null || value.toString().trim().length() == 0) {
                return value;
            } else if (value instanceof Double) {
                return value;
            } else if (value instanceof Number) {
                return ((Number) value).doubleValue();
            } else if (value.toString().matches("[-]?[\\d.]+")) {
                return Double.parseDouble(value.toString());
            } else {
                return 0d;
            }
        } catch (Exception e) {
            return 0d;
        }
    }

    /***
     * TODO value 为null 返回为默认值，在字段计算出异常时，返回的默认值可能是错误的，这样会导致字段配置默认值不被使用，或者字段的更新方式被 受影响
     * TODO 需要把null 不处理 直接返回
     * add by yuanchangyou
     * @param value
     * @return
     */
    private Object normalCurrency(Object value, String format) {
//        return normalDouble(value);
        try {
            if (value == null || StringUtils.isEmpty(value.toString().trim())) {
                return value;
            } else if (format == null && value instanceof Number) {
                return BigDecimal.valueOf(((Number) value).doubleValue());
            } else if (format != null) {
                DecimalFormatSymbols dfs = new DecimalFormatSymbols();
                dfs.setDecimalSeparator('.');
                dfs.setGroupingSeparator(',');
                dfs.setMonetaryDecimalSeparator('.');
                //"###,###.##"
                DecimalFormat decimalFormat = new DecimalFormat(format, dfs);
                return BigDecimal.valueOf(decimalFormat.parse(value.toString()).doubleValue());
            } else {
                return BigDecimal.valueOf(DecimalFormat.getNumberInstance().parse(value.toString()).doubleValue());
            }
        } catch (Exception e) {
            return BigDecimal.ZERO;
        }
    }

    /***
     * TODO value 为null 返回为默认值，在字段计算出异常时，返回的默认值可能是错误的，这样会导致字段配置默认值不被使用，或者字段的更新方式被 受影响
     * TODO 需要把null 不处理 直接返回
     * add by yuanchangyou
     * @param value
     * @return
     */
    private Object normalBoolean(Object value) {
        try {
            if (value == null) {
                return value;
            } else if (value instanceof Boolean) {
                return value;
            } else if (value instanceof Number) {
                return ((Number) value).intValue() > 0;
            } else {
                return java.lang.Boolean.valueOf(value.toString());
            }
        } catch (Exception e) {
            return false;
        }
    }

    /***
     * TODO value 为null 返回为默认值，在字段计算出异常时，返回的默认值可能是错误的，这样会导致字段配置默认值不被使用，或者字段的更新方式被 受影响
     * TODO 需要把null 不处理 直接返回
     * add by yuanchangyou
     * @param value
     * @return
     */
    private Object normalString(Object value, String format) {
        try {
            if (value == null) {
                return value;
            }
            if (format == null || format.equals("")) {
                return value.toString();
            }
            return String.format(format, value);
        } catch (Exception e) {
            return "";
        }
    }


    public Object defaultValue(Object v) {
        Object obj = v;
        if (v == null) {
            switch (this) {
                case STRING:
                    obj = "";
                    break;
                case DOUBLE:
                    obj = 0.0D;
                    break;
                case INTEGER:
                    obj = 0;
                    break;
                case LONG:
                    obj = 0L;
                    break;
                case Boolean:
                    obj = false;
                    break;
            }
        }
        return obj;
    }
}
