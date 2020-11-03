package jrx.data.hub.infrastructure.model;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
* <p>
*  描述
* </p>
*
* @author lw
* @since 2020-10-29
*/

@Getter
@Setter
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class McUser implements Serializable {

private static final long serialVersionUID = 1L;
@TableId
private Long id;

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
