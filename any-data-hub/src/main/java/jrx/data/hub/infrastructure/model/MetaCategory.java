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
public class MetaCategory implements Serializable {

private static final long serialVersionUID = 1L;

private Integer categoryId;

private String contentCode;

private String createPerson;

private LocalDateTime createTime;

private String updatePerson;

private LocalDateTime updateTime;

private String categoryType;

private String name;

private Integer parentId;

private Boolean used;


}
