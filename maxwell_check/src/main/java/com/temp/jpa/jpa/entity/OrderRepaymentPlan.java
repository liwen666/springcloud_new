//
//

package com.temp.jpa.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalTime;

/**
 * 订单还款计划
 */
@Accessors(chain = true)
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(
    name = "order_repayment_plan"
)
public class OrderRepaymentPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /**
     * 订单每期还款金额
     */
    private BigDecimal account;
    /**
     * 剩余还款期数
     */
    private Integer repayMentNum;
    /**
     * 订单编号
     */
    private String orderNo;
    private LocalTime createIime;
    private Timestamp updateTime;
}
