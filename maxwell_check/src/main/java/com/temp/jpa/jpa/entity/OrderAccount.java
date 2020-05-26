package com.temp.jpa.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalTime;
import java.util.List;

/**
 * @author xinre
 * @date 2019/5/25 13:59
 */
@Accessors(chain = true)
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
//@Table(name = "order_test1")
@Table(name = "order_account")
public class OrderAccount {



    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "custom-id")
    @GenericGenerator(name = "custom-id", strategy = "com.temp.jpa.jpa.dao.IdGenerator")
    private Long id;
    @Column(length = 500)
    /**
     * 订单号
     */
    private String orderNo;
    /**
     * 用户账户余额
     */
    private BigDecimal account;
    /**
     * 借款金额
     */
    private BigDecimal borrowAccount;
    /**
     * 用户身份证号
     */
    private String idCard;
    private LocalTime createTime;
    private Timestamp updateTime;
    private String userName;


}
