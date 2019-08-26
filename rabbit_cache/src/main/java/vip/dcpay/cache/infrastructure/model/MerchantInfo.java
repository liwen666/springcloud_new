package vip.dcpay.cache.infrastructure.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * author lw
 * date 2019/8/22  17:49
 * discribe
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MerchantInfo {

  /**
   * 主键ID
   */
//  @TableId(value = "id", type = IdType.UUID)
  private Long uid;
  //普通，批发商
  private Integer type;
  private String realname;
  private Integer activate_status;
  private String recv_pay_ways;
  private String assets;
  private BigDecimal day_mount_sum;

}
