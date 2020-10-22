package com.example.conf;

import lombok.*;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.sql.Timestamp;
import java.util.Date;

/**
 * <p>
 * 描述
 * </p>
 *
 * @author lw
 * @since 2019/5/26 23:40
 */

@Data
@Slf4j
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Message {
    private String id;
    private long account_id;
    private long amount;
    private Date transaction_time;

//    account_id  BIGINT,\n" +
//            "    amount      BIGINT,\n" +
//            "    transaction_time TIMESTAMP(3),\n" +


}
