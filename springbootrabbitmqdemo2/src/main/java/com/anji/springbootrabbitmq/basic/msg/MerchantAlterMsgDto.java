package com.anji.springbootrabbitmq.basic.msg;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 商家内容变更通知信息体
 * @author MK
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MerchantAlterMsgDto implements Serializable{
	private static final long serialVersionUID = 1L;
	
	/**
	 * 商家ID
	 */
	private Long merchantId;
	/**
	 * 变更项
	 */
	private MerchantAlterItemEnum alterItem;

}
