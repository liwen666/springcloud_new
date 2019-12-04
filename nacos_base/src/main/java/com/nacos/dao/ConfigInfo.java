package com.nacos.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
* <p>
    * config_info
    * </p>
*
* @author schedule
* @since 2019-12-04
*/
@Data
@Slf4j
 @Builder
 @NoArgsConstructor
 @AllArgsConstructor
 @EqualsAndHashCode(callSuper = false)

@Accessors(chain = true)
public class ConfigInfo implements Serializable {

private static final long serialVersionUID = 1L;

  /**
  * id
  */
  @TableId(value = "id", type = IdType.AUTO)
private Long id;

  /**
  * data_id
  */
private String dataId;

private String groupId;

  /**
  * content
  */
private String content;

  /**
  * md5
  */
private String md5;

  /**
  * 创建时间
  */
private LocalDateTime gmtCreate;

  /**
  * 修改时间
  */
private LocalDateTime gmtModified;

  /**
  * source user
  */
private String srcUser;

  /**
  * source ip
  */
private String srcIp;

private String appName;

  /**
  * 租户字段
  */
private String tenantId;

private String cDesc;

private String cUse;

private String effect;

private String type;

private String cSchema;


}
