package com.anji.springbootrabbitmq.basic.msg;

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
public class AssetChangeMsgDto implements Serializable {

    /**
     * 账户Id
     */
    private Long  accountId;

    /**
     * 账户类型
     */
    private Integer accountType;

    /**
     * 货币类型
     */
    private String currency;

    /**
     * 资金Id
     */
    private Long assetId;


    /**
     *热钱
     */
    private BigDecimal hotMoney;

    /**
     * 冷钱
     */
    private BigDecimal coldMoney;
}
