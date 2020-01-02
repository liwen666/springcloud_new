package com.example.thymeleafdemo.demo;

import lombok.*;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
* <p>
    * 
    * </p>
*
* @author schedule
* @since 2019-11-19
*/
@Data
@Slf4j
 @Builder
 @NoArgsConstructor
 @AllArgsConstructor
 @EqualsAndHashCode(callSuper = false)

@Accessors(chain = true)
public class BatchJobExecution implements Serializable {

private static final long serialVersionUID = 1L;

private Long jobExecutionId;

private Long version;

private Long jobInstanceId;

private Date createTime;

private Date startTime;

private Date endTime;

private String status;

private String exitCode;

private String exitMessage;

private LocalDateTime lastUpdated;

private String jobConfigurationLocation;


}
