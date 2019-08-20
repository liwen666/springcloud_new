package vip.dcpay.redis.util;

import org.apache.commons.lang3.StringUtils;

/**
 * @Auther: liq
 * @Date: 2019/7/17 12:17
 * @Description:
 */
public enum PatternEnum {

    SINGLE(0, "单机模式"),
    SENTINEL(1, "哨兵模式");

    private int code;
    private String desc;

    private PatternEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }


    public int code() {
        return code;
    }


    public String desc() {
        return desc;
    }


    public String getDesc(Integer code) {
        PatternEnum c = getEnum(code);
        if (null == c) {
            return code + "";
        } else {
            return c.desc;
        }
    }

    public static PatternEnum getEnum(Integer code) {
        if (null == code) {
            return null;
        }

        for (PatternEnum c : PatternEnum.values()) {
            if (c.code() == code.intValue()) {
                return c;
            }
        }
        return null;
    }

    public static PatternEnum getEnumByName(String name) {
        if (StringUtils.isBlank(name)) {
            return null;
        }

        return PatternEnum.valueOf(name.toUpperCase());
    }

}
