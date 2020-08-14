package jrx.anyest.table.jpa.enums;

/**
 * 历史数据操作类型
 */
public enum HistoryDataType {
    DELETE("删除数据"),
    /**
     * 如果是更新数据 先更具主键删除，然后插入
     */
    UPDATE("更新数据");

    HistoryDataType(String description) {

        this.description = description;

    }

    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }}
