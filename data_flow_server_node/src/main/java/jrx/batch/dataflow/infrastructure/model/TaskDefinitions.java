package jrx.batch.dataflow.infrastructure.model;

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
* @since 2019-11-12
*/
@Data
@Slf4j
 @Builder
 @NoArgsConstructor
 @AllArgsConstructor
 @EqualsAndHashCode(callSuper = false)

@Accessors(chain = true)
public class TaskDefinitions implements Serializable {

private static final long serialVersionUID = 1L;

private String definitionName;

private String definition;


}
