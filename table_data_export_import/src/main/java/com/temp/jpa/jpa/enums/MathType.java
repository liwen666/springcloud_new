package com.temp.jpa.jpa.enums;

/**
 * @author peidong.meng
 * @date 2019/4/2
 */
public enum MathType {
    ADD("+"),
    CUT("-");


    private String code;

    MathType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
