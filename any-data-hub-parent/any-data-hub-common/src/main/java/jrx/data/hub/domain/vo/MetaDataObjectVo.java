package jrx.data.hub.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jrx.data.hub.domain.enums.VersionState;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 描述 表版本信息
 * </p>
 *
 * @author lw
 * @since 2020-11-05
 */

@Getter
@Setter
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "表版本信息实体")
public class MetaDataObjectVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 表版本ID
     */
    @ApiModelProperty(value = "表版本ID")
    private String resourceVersionId;

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
     * 引用ID数组
     */
    @ApiModelProperty(value = "引用ID数组")
    private String fieldIds;

    /**
     * 表信息infoID
     */
    @ApiModelProperty(value = "表信息infoID")
    private Integer resourceId;

    /**
     * 更新人
     */
    @ApiModelProperty(value = "更新人")
    private String updatePerson;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
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
     * ddl 语句参数 是一个json数组
     */
    @ApiModelProperty(value = "ddl语句参数")
    private String paramsJson;

    @ApiModelProperty(value = "是否启用")
    private Boolean used;

    /**
     * ddl 语句内容
     */
    @ApiModelProperty(value = "是否启用")
    private String ddlSql;

    /**
     * 数据集名称
     */
    @ApiModelProperty(value = "数据集名称")
    private String name;

    /**
     * 字段列表
     */
    @ApiModelProperty(value = "字段列表")
    private List<MetaObjectFieldVo> metaObjectFieldList;


}
