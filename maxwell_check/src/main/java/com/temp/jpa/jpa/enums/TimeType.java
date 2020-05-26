package com.temp.jpa.jpa.enums;

/**
 * @author peidong.meng
 * @date 2019/4/2
 */
public enum  TimeType {
    SECOND("s"),
    MIN("m"),
    HOUR("h"),
    DAY("d"),
    MOUTH("M"),
    YEAR("y");

    private String code;

    TimeType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
