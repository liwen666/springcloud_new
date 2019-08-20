package vip.dcpay.redis.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import vip.dcpay.redis.util.PatternEnum;

import java.io.Serializable;
import java.util.Set;

/**
 * @Auther: liq
 * @Date: 2019/7/17 13:29
 * @Description:
 */
@Accessors(chain = true)
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConfigParam implements Serializable {

    /**
     * 连接模式
     */
    private PatternEnum pattern;

    private String dbs;

    private String url;
    private Integer port;
    private String password;
    private Integer timeout;

    private String masterName;
    private Set<String> sentinelNodes;

}
