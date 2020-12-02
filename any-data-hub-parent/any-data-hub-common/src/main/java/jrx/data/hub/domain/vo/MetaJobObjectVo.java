package jrx.data.hub.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jrx.data.hub.domain.enums.VersionState;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 描述 job 版本
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
@ApiModel(description = "job版本实体")
public class MetaJobObjectVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * job 版本ID
     */
    @ApiModelProperty(value = "job版本ID")
    private String jobObjectId;

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
     * job 信息ID
     */
    @ApiModelProperty(value = "job信息ID")
    private String resourceId;

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
     * 版本号
     */
    @ApiModelProperty(value = "版本号")
    private String versionCode;

    /**
     * 版本状态
     */
    @ApiModelProperty(value = "版本状态")
    private VersionState versionState;

    /**
     * job 详细内容
     */
    @ApiModelProperty(value = "job详细内容")
    private String sqlContent;

    /**
     * job 参数信息 JSON 数组
     */
    @ApiModelProperty(value = "job参数信息JSON数组")
    private String paramJson;


}
