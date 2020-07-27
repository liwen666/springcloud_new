package com.temp.jpa.jpa.enums;

/**
 * 数据转换模型
 */
public enum DataConversionModel {
    FIELD("字段数据转换"),
    FIELD_INDEX("字段带前缀数据转换"),
    OBJECT("对象数据转换"),
    ORG("机构编码转换");

    DataConversionModel(String description) {

        this.description = description;

    }

    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }}
