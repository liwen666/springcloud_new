package com.temp.jpa.jpa.enums;

/**
 * @author peidong.meng
 * @date 2019/6/12
 */
public enum FieldState {
    INACTIVE("非活跃"),
    ACTIVE("活跃");


    private String des;

    FieldState(String des) {
        this.des = des;
    }

    public String getDes() {
        return des;
    }
}
