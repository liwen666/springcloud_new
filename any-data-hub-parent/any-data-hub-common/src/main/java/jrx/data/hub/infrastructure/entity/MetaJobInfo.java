package jrx.data.hub.infrastructure.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import jrx.data.hub.domain.enums.JobType;
import jrx.data.hub.domain.enums.VersionState;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 描述 job 信息
 * </p>
 *
 * @author lw
 * @since 2020-11-05
 */

@Getter
@Setter
@Slf4j
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "job信息实体")
public class MetaJobInfo extends BaseModel implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * jobID
     */
    @TableId(type = IdType.ID_WORKER_STR)
    private String resourceId;

    /**
     * job名称
     */
    private String name;

    /**
     * job标识
     */
    private String code;

    /**
     * 租户号
     */
    private String contentCode;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 描述信息
     */
    private String description;

    /**
     * job类型
     */
    private JobType jobType;

    /**
     * job状态
     */
    private VersionState jobState;

    /**
     * 创建人
     */
    private String createPerson;

    /**
     * 修改人
     */
    private String updatePerson;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

    /**
     * 加工数据源表ID
     */
    private String sourceIds;

    /**
     * 加工数据目标表ID
     */
    private Integer targetId;

    /**
     * zpl分类ID
     */
    private String zplCategreId;

    /**
     * zpl noteBookId
     */
    private String zplNotebookId;
    /**
     * zpl jobId
     */
    private String zplJobId;


}
