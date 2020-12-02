package jrx.data.hub.domain.enums;

/**
 * 版本状态
 * @author yxy
 * @since 2018/9/26
 */
public enum VersionState {

    INACTIVE("未启用"),
    ONLINE("启用"),
    OFFLINE("停用");

    private String desc;

    VersionState(String desc){
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}

