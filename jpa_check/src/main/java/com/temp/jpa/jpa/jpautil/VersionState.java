package com.temp.jpa.jpa.jpautil;

/**
 * 版本状态
 * @author
 * @date 2018/9/26
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
