package jrx.batch.dataflow.infrastructure.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.time.LocalDateTime;

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

  @TableId("JOB_EXECUTION_ID")
private Long jobExecutionId;

 @TableField("VERSION")
private Long version;

 @TableField("JOB_INSTANCE_ID")
private Long jobInstanceId;

 @TableField("CREATE_TIME")
private LocalDateTime createTime;

 @TableField("START_TIME")
private LocalDateTime startTime;

 @TableField("END_TIME")
private LocalDateTime endTime;

 @TableField("STATUS")
private String status;

 @TableField("EXIT_CODE")
private String exitCode;

 @TableField("EXIT_MESSAGE")
private String exitMessage;

 @TableField("LAST_UPDATED")
private LocalDateTime lastUpdated;

 @TableField("JOB_CONFIGURATION_LOCATION")
private String jobConfigurationLocation;


}
