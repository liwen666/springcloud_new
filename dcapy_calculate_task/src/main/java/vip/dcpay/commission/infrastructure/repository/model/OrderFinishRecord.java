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
@TableName("merchant_order_finish_record")
public class OrderFinishRecord implements Serializable {

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
     * 订单id
     */
    @TableField("record_id")
    private Long recordId;

    /**
     * 订单编号
     */
    @TableField("order_no")
    private String orderNo;

    /**
     * 订单交易金额
     */
    @TableField("transaction_amount")
    private BigDecimal transactionAmount;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * 精确到天的创建时间
     */
    @TableField("simple_time")
    private Date simpleTime;
    /**
     * 订单收益
     */
    @TableField("income")
    private BigDecimal income;
    /**
     * 发起人id
     */
    @TableField("customer_id")
    private long customerId;
    /**
     * 订单状态
     */
    @TableField("order_status")
    private int orderStatus;
    /**
     * 订单类型
     */
    @TableField("order_type")
    private int orderType;
    /**
     * 订单类型
     */
    @TableField("pay_way")
    private String payWay;



}
