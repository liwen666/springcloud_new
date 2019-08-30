package com.anji.springbootrabbitmq.order;

/**
 * 订单来源
 */
public enum OrderTypeEnum implements IBaseEnum {

    PLAYER_DEPOSIT(1, "玩家充值"),
    PLATFORM_WITHDRAW(2, "平台提现"),
    MERCHANT_DEPOSIT(3, "商家充值"),
    MERCHANT_WITHDRAW(4, "商家提现"),
    AGENT_WITHDRAW(5, "代理提现");

    private int code;
    private String desc;

    private OrderTypeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Override
    public int code() {
        return code;
    }

    @Override
    public String desc() {
        return desc;
    }

    @Override
    public String getDesc(Integer code) {
        if (null == code) {
            return null;
        }

        OrderTypeEnum c = getEnum(code);
        if (null == c) {
            return code + "";
        } else {
            return c.desc;
        }
    }

    public static OrderTypeEnum getEnum(Integer code) {
        if (null == code) {
            return null;
        }

        for (OrderTypeEnum c : OrderTypeEnum.values()) {
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
    
    public static boolean isDeposit(OrderTypeEnum orderType){
    	return orderType == PLAYER_DEPOSIT || orderType == MERCHANT_DEPOSIT;
    }
    
    public static boolean fromMerchant(OrderTypeEnum orderType){
    	return orderType == MERCHANT_DEPOSIT || orderType == OrderTypeEnum.MERCHANT_WITHDRAW;
    }

}
