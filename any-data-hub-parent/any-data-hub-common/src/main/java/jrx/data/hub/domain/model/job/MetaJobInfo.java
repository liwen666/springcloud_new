package jrx.data.hub.domain.model.job;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jrx.data.hub.domain.enums.JobState;
import jrx.data.hub.domain.enums.JobType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
@NoArgsConstructor
@ApiModel(description = "job信息实体")
public class MetaJobInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * jobID
     */
    @TableId
    @ApiModelProperty(value = "jobID")
    private Long resourceId;

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
    private String sourceTables;

    /**
     * 加工数据目标表ID
     */
    @ApiModelProperty(value = "加工数据目标表ID")
    private Integer targetId;

    /**
     * zpl段落ID
     */
    @ApiModelProperty(value = "zpl段落ID")
    private String zplParagraphId;

    /**
     * zpl noteBookId
     */
    @ApiModelProperty(value = "zplnoteBookId")
    private String zplNotebookId;


}
