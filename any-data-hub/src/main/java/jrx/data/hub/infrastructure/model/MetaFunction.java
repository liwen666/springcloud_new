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
public class MetaFunction implements Serializable {

private static final long serialVersionUID = 1L;

private Integer functionId;

private String contentCode;

private LocalDateTime createTime;

private Integer resourceId;

private String updatePerson;

private LocalDateTime updateTime;

private String versionCode;

private String versionState;

private String functionContent;


}
