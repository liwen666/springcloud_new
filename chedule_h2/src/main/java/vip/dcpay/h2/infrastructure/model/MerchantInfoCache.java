package vip.dcpay.h2.infrastructure.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * author lw
 * date 2019/8/22  17:49
 * discribe  商户信息缓存数据
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("merchant_info_cache")
public class MerchantInfoCache {
  @TableId(value = "id", type = IdType.INPUT)
  private Long id;
  @TableField("uid")
  private Long uid;
  //普通，批发商
  @TableField("type")
  private Integer type;
  @TableField("realname")
  private String realname;
  @TableField("activate_status")
  private Integer activateStatus;
  @TableField("recv_pay_ways")
  private String recvPayWays;
  @TableField("assets")
  private String assets;
  @TableField("day_mount_sum")
  private BigDecimal dayMountSum;
  @TableField("day_order_count")
  private Long dayOrderCount;

}
