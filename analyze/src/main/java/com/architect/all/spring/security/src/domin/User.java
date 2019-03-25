package com.architect.all.spring.security.src.domin;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

/**
* <p>
    * 
    * </p>
*
* @author tx
* @since 2019-03-25
*/
@Data
@Slf4j
 @EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class User implements Serializable {

private static final long serialVersionUID = 1L;

  @TableId(value = "id", type = IdType.AUTO)
private Long id;

private String avatar;

 @TableField("createTime")
private LocalDateTime createTime;

private String email;

private boolean enabled;

private String password;

private String username;
private Set<Role>roles;

 @TableField("lastPasswordResetTime")
private Date lastPasswordResetTime;


    public Set<Role> getRoles() {
        return roles;
    }


    public boolean getEnabled() {
        return enabled;
    }
}
