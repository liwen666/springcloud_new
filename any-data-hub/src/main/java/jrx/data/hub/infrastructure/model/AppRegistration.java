package jrx.data.hub.infrastructure.model;

import lombok.*;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
* <p>
    * 
    * </p>
*
* @author schedule
* @since 2019-11-11
*/
@Data
@Slf4j
 @Builder
 @NoArgsConstructor
 @AllArgsConstructor
 @EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class AppRegistration implements Serializable {

private static final long serialVersionUID = 1L;

private Long id;

private Long objectVersion;

private Boolean defaultVersion;

private String metadataUri;

private String name;

private Integer type;

private String uri;

private String version;


}
