package jrx.batch.dataflow.infrastructure.model;

import com.baomidou.mybatisplus.annotation.TableField;
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
 * @since 2019-11-04
 */
@Data
@Slf4j
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TaskTaskBatch implements Serializable {


 private static final long serialVersionUID = 1L;

 @TableField("TASK_EXECUTION_ID")
 private Long taskExecutionId;

 @TableField("JOB_EXECUTION_ID")
 private Long jobExecutionId;


}
