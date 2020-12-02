package jrx.data.hub.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 描述 字段信息
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
@ApiModel(description = "字段信息实体")
public class MetaObjectFieldVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 字段ID
     */
    @ApiModelProperty(value = "字段ID")
    private String objectFieldId;

    /**
     * 租户号
     */
    @ApiModelProperty(value = "租户号")
    private String contentCode;

    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人")
    private String createPerson;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

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
     * 默认值
     */
    @ApiModelProperty(value = "默认值")
    private String defaultValue;

    /**
     * 描述信息
     */
    @ApiModelProperty(value = "描述信息")
    @NotEmpty(message = "描述信息不能为空")
    private String description;

    /**
     * 字段标识
     */
    @ApiModelProperty(value = "字段ID")
    private String fieldCode;

    @ApiModelProperty(value = "fieldFormat")
    private String fieldFormat;

    /**
     * 字段长度
     */
    @ApiModelProperty(value = "字段长度")
    private Integer fieldLength;

    /**
     * 字段名称
     */
    @ApiModelProperty(value = "字段名称")
    @NotEmpty(message = "字段名称不能为空")
    private String fieldName;

    /**
     * 字段类型
     */
    @ApiModelProperty(value = "字段类型")
    @NotEmpty(message = "字段类型不能为空")
    private String fieldType;

    /**
     * 是否是主键
     */
    @ApiModelProperty(value = "是否是主键")
    private Boolean isKey;

    /**
     * 字段类型
     */
    @ApiModelProperty(value = "字段类型")
    private String objectType;

    @ApiModelProperty(value = "scaleLength")
    private Integer scaleLength;


}
