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
public class BatchStepExecution implements Serializable {

private static final long serialVersionUID = 1L;

  @TableId("STEP_EXECUTION_ID")
private Long stepExecutionId;

 @TableField("VERSION")
private Long version;

 @TableField("STEP_NAME")
private String stepName;

 @TableField("JOB_EXECUTION_ID")
private Long jobExecutionId;

 @TableField("START_TIME")
private LocalDateTime startTime;

 @TableField("END_TIME")
private LocalDateTime endTime;

 @TableField("STATUS")
private String status;

 @TableField("COMMIT_COUNT")
private Long commitCount;

 @TableField("READ_COUNT")
private Long readCount;

 @TableField("FILTER_COUNT")
private Long filterCount;

 @TableField("WRITE_COUNT")
private Long writeCount;

 @TableField("READ_SKIP_COUNT")
private Long readSkipCount;

 @TableField("WRITE_SKIP_COUNT")
private Long writeSkipCount;

 @TableField("PROCESS_SKIP_COUNT")
private Long processSkipCount;

 @TableField("ROLLBACK_COUNT")
private Long rollbackCount;

 @TableField("EXIT_CODE")
private String exitCode;

 @TableField("EXIT_MESSAGE")
private String exitMessage;

 @TableField("LAST_UPDATED")
private LocalDateTime lastUpdated;


}
