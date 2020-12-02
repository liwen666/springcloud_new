package jrx.data.hub.domain.enums;

/**
 *  表类型
 */
public enum TableEnum {

    USER("用户表"),
    CATEGORY("分类表"),
    DATA_OBJECT("表版本表"),
    DATA_OBJECT_INFO("表信息表"),
    DATA_SOURCE_INFO("数据源表"),
    FUNCTION("函数版本表"),
    FUNCTION_INFO("函数表"),
    JOB("job版本表"),
    JOB_INFO("job表"),
    OBJECT_FIELD("字段表"),
    RELATION_INFO("关联表"),
    WORK_INFO("work表");
    private String desc;

    TableEnum(String desc){
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
