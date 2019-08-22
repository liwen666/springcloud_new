package com.anji.springbootrabbitmq.basic.msg;

public enum MerchantAlterItemEnum {

	POWER("权限"),
	PHONE("手机号"),
	REALNAME("实名信息"),
	ACTIVATE_STATUS("激活状态"),
	ACCOUNT_STATUS("账号状态"),
	PAYMENT_WAY("支付方式"),
	PAYMENT_WAY_CHOICE("支付方式选择"),
	GRAB_SWITCH("抢单开关"),
	BUY_ORDER_LIMIT("买单限制")
	;

	private MerchantAlterItemEnum(String desc){
		this.desc = desc;
	}
	
	private String desc;
	public String desc(){
		return desc;
	}

}
