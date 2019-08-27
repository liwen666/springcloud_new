package vip.dcpay.h2.domain.dto;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 资金结构
 *
 * @Auther: liq
 * @Date: 2019/5/31 10:22
 * @Description:
 */
@EqualsAndHashCode(callSuper = false)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssetInfo implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 资产类型
     */
    private String currency;

    /**
     * 金额
     */
    private BigDecimal amount;

    /**
     * 精度
     */
    private Integer accuracy;


}
