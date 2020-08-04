package jrx.anyest.table.jpa.enums;

/**
 * 数据转换模型
 */
public enum HandlerParam {
    FIELD("字段数据转换");

    HandlerParam(String description) {

        this.description = description;

    }

    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }}
