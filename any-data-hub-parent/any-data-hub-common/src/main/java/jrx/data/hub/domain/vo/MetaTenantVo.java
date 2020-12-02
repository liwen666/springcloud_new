package jrx.data.hub.domain.vo;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 描述
 * </p>
 *
 * @author lw
 * @since 2020-11-16
 */

@Getter
@Setter
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class MetaTenantVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String tenantId;

    private String createPerson;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private String updatePerson;

    private String tenantName;

    private String tenantCode;

    private String tenantDescription;


}
