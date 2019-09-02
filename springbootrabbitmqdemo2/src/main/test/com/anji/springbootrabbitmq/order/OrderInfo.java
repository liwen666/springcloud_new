package com.anji.springbootrabbitmq.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * author lw
 * date 2019/8/30  20:32
 * discribe
 */
@Accessors(chain = true)
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderInfo {
    //    "platformId=2&jUserId=1&jUserIp=192.168.1.11&jOrderId=" + FileIdGenerator.getNext()
//            + "&orderType=1&amount=2220&currency=CNY&notifyUrl=http://notifyUrl/&jExtra=测试&payWay=AliPay"
    private int platformId;
    private int jUserId;
    private String jUserIp;
    private int orderType;
    private BigDecimal amount;
    private String currency;
    private String payWay;
    private String ipCity;//地区名称
    private String ipCode;//地区编号
}
