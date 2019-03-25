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
public class Menu implements Serializable {

private static final long serialVersionUID = 1L;

  @TableId(value = "id", type = IdType.AUTO)
private Long id;

 @TableField("createTime")
private LocalDateTime createTime;

 @TableField("iFrame")
private Boolean iFrame;

private String name;

private String component;

private Long pid;

private Long soft;

private String icon;

private String path;


}
