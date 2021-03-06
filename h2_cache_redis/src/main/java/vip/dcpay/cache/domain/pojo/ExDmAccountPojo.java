package vip.dcpay.cache.domain.pojo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class ExDmAccountPojo implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    private Long accountId;
    private int accountType;
    private BigDecimal hotMoney;
    private BigDecimal coldMoney;
    private String coinCode;
    private Double hotDouble;
    private Double coldDouble;

}