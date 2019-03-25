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
public class Permission implements Serializable {

private static final long serialVersionUID = 1L;

  @TableId(value = "id", type = IdType.AUTO)
private Long id;

private String alias;

 @TableField("createTime")
private LocalDateTime createTime;

private String name;

private Integer pid;


}
