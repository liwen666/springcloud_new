package vip.dcpay.commission.infrastructure.repository.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Accessors(chain = true)
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("merchant_day_amount")
public class MerchantDayAmount implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键ID
	 */
	@TableId(value = "id", type = IdType.AUTO)
	private Long id;

	/**
	 * 商户id
	 */
	@TableField("merchant_id")
	private Long merchantId;

	/**
	 * 成交订单数量
	 */
	@TableField("order_num")
	private Long orderNum;

	/**
	 * 每天实时订单成交额
	 */
	@TableField("day_amount_sum")
	private BigDecimal dayAmountSum;

	/**
	 * 数据标记位
	 */
	@TableField("remark")
	private long remark;

	/**
	 * 创建时间
	 */
	@TableField("create_time")
	private Date createTime;


	/**
	 * 修改时间
	 */
	@TableField("modify_time")
	private String modifyTime;

}
