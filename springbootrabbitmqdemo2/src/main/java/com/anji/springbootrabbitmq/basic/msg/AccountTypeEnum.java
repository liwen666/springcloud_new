package com.anji.springbootrabbitmq.basic.msg;

public enum AccountTypeEnum {

    OUTER_PLATFORM(1, "外部平台")
    , MERCHANT(2, "商家")
    ,FINANCIAL_STAFF(3,"财务")//财务属于公司
    ,ADMINISTRATOR(4,"管理员")//企业管理员，可操作强制完成、关闭动作
    ,SYSTEM(5,"系统")//程序自动发起的线程，可操作超时关闭、匹配超时失败动作
    ,PLAYER(6,"平台玩家")
    ,PLATFORM_PROXY(7,"平台代理")
    ;

    private int code;
    private String desc;

    private AccountTypeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int code() {
        return code;
    }

    public String desc() {
        return desc;
    }

    public String getDesc(int code) {
        AccountTypeEnum c = getEnum(code);
        if (null == c) {
            return code + "";
        } else {
            return c.desc;
        }
    }

    public static AccountTypeEnum getEnum(Integer code) {
        if (null == code) {
            return null;
        }

        for (AccountTypeEnum c : AccountTypeEnum.values()) {
            if (c.code() == code.intValue()) {
                return c;
            }
        }
        return null;
    }

    public static boolean isValid(Integer code) {
        if (null == code) {
            return false;
        }

        return null != getEnum(code);
    }

}
