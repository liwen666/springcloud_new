package jrx.data.hub.infrastructure.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 描述 用户信息表
 * </p>
 *
 * @author lw
 * @since 2020-11-05
 */

@Getter
@Setter
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class McUser extends BaseModel implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(type = IdType.ID_WORKER_STR)
    private String id;

    private String createBy;

    private LocalDateTime createTime;

    private String modifiedBy;

    private LocalDateTime modifiedTime;

    private String domanType;

    private String email;

    private Boolean enabled;

    private LocalDateTime forbiddenDate;

    private LocalDateTime lastPasswordRestDate;

    private Integer lastPasswordResetType;

    private String mobile;

    private String nickName;

    private String orgName;

    private String password;

    private String userName;


}
