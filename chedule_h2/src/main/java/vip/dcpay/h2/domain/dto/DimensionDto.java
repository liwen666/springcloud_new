package vip.dcpay.h2.domain.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: DimensionDto
 * @date Aug 15, 2019 4:17:14 PM
 * @author ToniR
 * @Description: 每日订单实时金额
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DimensionDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long merchantId;

	/**
	 * 每日成交订单数量
	 */
	private Long dayOrderCount;

	/**
	 * 商家每日订单实时金额
	 */
	private BigDecimal dayAmountSum;

}
