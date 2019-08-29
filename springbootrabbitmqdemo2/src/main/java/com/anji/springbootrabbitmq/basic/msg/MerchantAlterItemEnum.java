package com.anji.springbootrabbitmq.basic.msg;

public enum MerchantAlterItemEnum {

	// 更新power属性
	POWER("power", "权限"),
	// 更新basic
	PHONE("basic", "手机号"),
	// 更新basic
	REALNAME("basic", "实名信息"),
	// 更新basic
	ACTIVATE_STATUS("basic", "激活状态"),
	// 更新risk
	ACCOUNT_STATUS("risk", "账号状态"),
	// 更新payments
	PAYMENT_WAY("payments", "支付方式"),
	// 更新paymentChoices
	PAYMENT_WAY_CHOICE("paymentChoices", "支付方式选择"),
	// 更新grabSwitch
	GRAB_SWITCH("grabSwitch", "抢单开关"),
	// 更新buyOrderLimit
	BUY_ORDER_LIMIT("buyOrderLimit", "买单限制"),
	// 更新dimension
	DIMENSION_DATA("dimension", "商家订单实时金额");

	private MerchantAlterItemEnum(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	private String code;

	private String desc;

	public String code() {
		return code;
	}

	public String desc() {
		return desc;
	}

}
