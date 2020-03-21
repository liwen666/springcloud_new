package com.temp.jpa.jpa.entity;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalTime;

/**
 * 累计交易账户表
 *
 * @author peidong.meng
 * @date 2018/9/27
 */
@Accessors(chain = true)
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
//@Table(name = "order_history_count ", indexes = {@Index(name = "idx_resource_id", columnList = "resourceId"), @Index(name = "idx_version_state", columnList = "versionState")})
@Table(name = "order_history_count")
public class OrderHistoryCount {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /**
     * 借款总金额
     */
    private BigDecimal borrowAmmount;
    /**
     * 还款总金额
     */
    private BigDecimal repayMentAmmount;
    private String idCard;
    private LocalTime createIime;
    private Timestamp updateTime;
    private String userName;
}
