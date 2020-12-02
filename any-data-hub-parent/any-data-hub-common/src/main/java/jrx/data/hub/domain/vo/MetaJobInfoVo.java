package jrx.data.hub.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jrx.data.hub.domain.model.MetaDataObject;
import jrx.data.hub.domain.enums.JobState;
import jrx.data.hub.domain.enums.JobType;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

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
public class MetaJobInfoVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * jobID
     */
    @ApiModelProperty(value = "jobID")
    private String resourceId;

    /**
     * job名称
     */
    @ApiModelProperty(value = "job名称")
    private String name;

    /**
     * job标识
     */
    @ApiModelProperty(value = "job标识")
    private String code;

    /**
     * 租户号
     */
    @ApiModelProperty(value = "租户号")
    private String contentCode;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    /**
     * 描述信息
     */
    @ApiModelProperty(value = "描述信息")
    private String description;

    /**
     * job类型
     */
    @ApiModelProperty(value = "job类型")
    private JobType jobType;

    /**
     * job状态
     */
    @ApiModelProperty(value = "job状态")
    private JobState jobState;

    /**
     * 修改人
     */
    @ApiModelProperty(value = "修改人")
    private String updatePerson;

    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;

    /**
     * 加工数据源表ID
     */
    @ApiModelProperty(value = "加工数据源表ID")
    private String sourceIds;

    /**
     * 加工数据目标表ID
     */
    @ApiModelProperty(value = "加工数据目标表ID")
    private Integer targetId;

    /**
     * zpl分类ID
     */
    @ApiModelProperty(value = "zpl分类ID")
    private String zplCatagreId;

    /**
     * zpl noteBookId
     */
    @ApiModelProperty(value = "zplNoteBookId")
    private String zplNotebookId;

    /**
     * 最新版本ID
     */
    @ApiModelProperty(value = "最新表版本ID")
    private String latestVersionId;

}
