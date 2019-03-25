package com.architect.all.geneator.tx.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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

private Long enabled;

private String password;

private String username;

 @TableField("lastPasswordResetTime")
private LocalDateTime lastPasswordResetTime;


}
