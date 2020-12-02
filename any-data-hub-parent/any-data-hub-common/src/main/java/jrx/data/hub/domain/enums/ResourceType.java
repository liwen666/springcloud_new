package jrx.data.hub.domain.enums;

/**
 * 表类型
 */
public enum ResourceType {

    COLLECTION("Collection", "采集表"),

    TARGET("Target", "目标表"),

    JOB_INFO("JOB_INFO", "job表"),

    DATASOURCE_INFO("DATASOURCE_INFO", "数据源信息表"),

    FUNCTION_INFO("FUNCTION_INFO", "函数表");

    private String code;

    private String description;

    ResourceType(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
