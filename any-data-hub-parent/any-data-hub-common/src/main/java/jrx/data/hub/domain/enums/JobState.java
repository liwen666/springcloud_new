package jrx.data.hub.domain.enums;

/**
 * @author Songyc5
 * @date: 2020/11/8
 */
public enum JobState {

    INACTIVE("未启用"),
    ACTIVE("启用");

    private String des;

    JobState(String des) {
        this.des = des;
    }

    public String getDes() {
        return des;
    }
}
