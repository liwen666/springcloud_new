package com.temp.jpa.jpa.jpautil;

/**
 * @author yxy
 * @date 2018/10/4
 */
public enum ResourceType {

    MATRIX("矩阵"),
    SCRIPT("脚本"),
    RULE("规则"),
    RULESET("规则集"),
    RULETREE("规则树"),
    SCORECARD("评分卡"),
    FIELD("字段"),
    FEATURE("特征衍生变量"),
    GROUPING("分群"),
    STAT_OBJECT("统计模型"),
    TOPIC_OBJECT("实体模型"),
    RELATION_OBJECT("关系模型"),
    MODEL_OBJECT("业务模型"),
    EVENT_OBJECT("事件对象"),
    SYSTEM_OBJECT("系统对象"),
    DATA_SOURCE("数据源"),
    DATA_OBJECT("数据集/数据对象"),
    COLLECT_DATA_OBJECT("采集数据"),
    STRATEGY("策略"),
    DASHBOARD("看板"),
    TEMPLATE("模板"),
    REPORT("报表"),
    SETTING("设置信息"),
    GENERAL("通用信息"),
    GRAPH("关联图谱"),
    FUNCTION("函数");

    private String desc;

    ResourceType(String desc){
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public ObjectType mapperObjectType(){
        if(this == ResourceType.EVENT_OBJECT){
            return ObjectType.EVENT;
        }else if(this == ResourceType.MODEL_OBJECT){
            return ObjectType.MODEL;
        }else if(this == ResourceType.DATA_OBJECT){
            return ObjectType.DATA;
        }else if(this == ResourceType.SYSTEM_OBJECT) {
            return ObjectType.SYSTEM;
        }else if(this == ResourceType.TOPIC_OBJECT) {
            return ObjectType.TOPIC;
        }else if(this == ResourceType.STAT_OBJECT){
            return ObjectType.STAT;
        }else if(this == ResourceType.COLLECT_DATA_OBJECT){
            return ObjectType.COLLECT_DATA;
        }else {
            return ObjectType.OTHER;
        }
    }
}
