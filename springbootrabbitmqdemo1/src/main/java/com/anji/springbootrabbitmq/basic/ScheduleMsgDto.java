package com.anji.springbootrabbitmq.basic;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

@Accessors(chain = true)
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleMsgDto implements Serializable {

    private static final long serialVersionUID = 1L;

    //private Long id;//订单id
    private String invoice;//订单号
    private Integer orderType;//1、玩家充值;2、平台提现;3、商家充值;4、商家提现
    private Integer tradeType;//交易类型（1、充值；2：提现）
    private String payWay;//支付类型（Bankcard-银行卡 | AliPay-支付宝 | WechatPay-微信）
    private BigDecimal amount; //订单金额 ,提现的时候需要
    private String merchantIds;
    private Boolean show = true;//是否显示
    private Boolean isAutoPush = true; //是否是自动推单   默认是自动推单
}
