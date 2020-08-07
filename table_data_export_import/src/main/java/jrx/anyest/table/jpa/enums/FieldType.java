package jrx.anyest.table.jpa.enums;


/**
 * 字段类别 枚举
 * 前缀标识index后续扩展为大于1个字符
 */
public enum FieldType {
    SYS_FIELD(0, "SYS", "系统字段", "s", "$"),
    BIZ_FIELD(1, "BIZ", "业务字段", "b", ""),
    STAT_FIELD(2, "STAT", "统计字段", "a", "#"),
    THIRD_FIELD(3, "THIRD", "第三方字段", "t", ""),
    CUSTOM_FIELD(4, "CUSTOM", "自有库字段", "c", ""),
    CONDITION_FIELD(5, "CONDITION", "衍生条件字段", "o", "#" ),
    FORMULA_FIELD(6, "FORMULA", "衍生计算公式字段", "f", "#"),
    REGULAR_FIELD(7, "REGULAR", "衍生正则字段", "r", "#"),
    SCRIPT_FIELD(8, "SCRIPT", "衍生脚本字段", "p", "#"),
    FUNCTION_FIELD(9, "FUNCTION", "衍生函数字段", "n", "#"),
    INTERFACE_FIELD(10, "INTERFACE", "接口字段", "i", ""),
    DIMENSION_FIELD(11, "DIMENSION", "维度字段", "d", ""),
    RESTRICT_FIELD(12, "RESTRICT", "约束字段", "e", ""),
    TAG_FIELD(13, "TAG", "衍生标签字段", "k", ""),
    STRATEGY_STAT_FIELD(14, "STRATEGY_STAT", "活动统计字段", "g", "#"),
    /**
     * 此字段标识来对象的标识映射，key值为对象的主键，value为对象类型
     */
    OBJECT_DATA(15, "OBJECT_DATA", "数据对象映射字段", "j", ""),
    OBJECT_MODEL(16, "OBJECT_MODEL", "数据对象映射字段", "m", ""),
    TRANSFORM_FIELD(17, "TRANSFORM_FIELD", "衍生转换字段", "x", ""),
    AGGREGATION_FIELD(18, "AGGREGATION_FIELD", "聚合计算字段", "h", ""),
    TEMP_FIELD(19, "TEMP_FIELD", "临时字段", "z", ""),
    DICTIONARY_FIELD(20, "DICTIONARY_FIELD", "字典字段", "k", "");
//    MATRIX_FIELD(11,"MATRIX","矩阵字段","m");

    private int id;
    private String code;
    private String description;
    private String index;
    private String sign;

    FieldType(int id, String code, String description, String index, String sign) {
        this.id = id;
        this.code = code;
        this.description = description;
        this.index = index;
        this.sign = sign;
    }


    public String getCode() {
        return code;
    }


    public int getId() {
        return id;
    }


    public String getDescription() {
        return description;
    }


    public String getIndex() {
        return index;
    }


    public String getSign() {
        return sign;
    }


}
