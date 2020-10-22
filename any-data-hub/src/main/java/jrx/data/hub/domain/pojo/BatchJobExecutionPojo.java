package jrx.data.hub.domain.pojo;

import lombok.*;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 *  描述
 * </p>
 *
 * @author lw
 * @since  2020/10/22 16:27
 */
@Data
@Slf4j
 @Builder
 @NoArgsConstructor
 @AllArgsConstructor
 @EqualsAndHashCode(callSuper = false)

@Accessors(chain = true)
public class BatchJobExecutionPojo implements Serializable {

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
 /**
  * job对应的任务执行id
  */
 private  String taskExecutionId;
 /**
  * 批次服务数据库配置
  */
 private  String dbInfo;
 /**
  * 任务执行id对应的调度分区父id
  */
 private  String taskParentExecutionId;

 /**
  * 服务器注册名称
  */
 private  String serverName;

}
