package vip.dcpay.h2.domain.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vip.dcpay.h2.domain.dto.AssetInfo;
import vip.dcpay.h2.domain.dto.DimensionDto;

import java.io.Serializable;
import java.util.List;

/**
 * 订单领取人的信息
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MerchantInfoPojo implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 商家ID
	 */
	private Long id;
	/**
	 * 商家UID
	 */
	private String uid;
	/**
	 * 商家类型 1-普通商家 2-批发商
	 * 
	 * @see vip.dcpay.enums.merchant.MerchantTypeEnum
	 */
	private Integer type;

	/**
	 * 商家的实名
	 */
	private String realname;

	/**
	 * 商家的激活状态
	 * 
	 * @see vip.dcpay.enums.merchant.MerchantActivateStatusEnum
	 */
	private Integer activateStatus;

	/**
	 * 商家资产,可能null
	 */
	private List<AssetInfo> assets;

	/**
	 * 商家设置的收款方式,可能null
	 */
	private List<String> recvPayways;

	/**
	 * 每日实时金额
	 */
	private DimensionDto dimension;

}
