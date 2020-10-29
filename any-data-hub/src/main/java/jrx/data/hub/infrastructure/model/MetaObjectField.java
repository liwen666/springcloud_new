package jrx.data.hub.infrastructure.model;

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
public class MetaObjectField implements Serializable {

private static final long serialVersionUID = 1L;

private Integer objectFieldId;

private String contentCode;

private String createPerson;

private LocalDateTime createTime;

private String updatePerson;

private LocalDateTime updateTime;

private String defaultValue;

private String description;

private String fieldCode;

private String fieldFormat;

private Integer fieldLength;

private String fieldName;

private String fieldType;

private Boolean isKey;

private String objectType;

private Integer scaleLength;


}
