package jrx.data.hub.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * <p>
 * 描述,对象之间的关联关系
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
@ApiModel(description = "对象之间的关联关系实体")
public class MetaRelationInfoVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 源表ID
     */
    @ApiModelProperty(value = "源表ID")
    private String sourceId;

    /**
     * 源表类型
     */
    @ApiModelProperty(value = "源表类型")
    private String sourceType;

    /**
     * 目标表ID
     */
    @ApiModelProperty(value = "目标表ID")
    private String targetId;

    /**
     * 目标表类型
     */
    @ApiModelProperty(value = "目标表类型")
    private String targetType;

    /**
     * 源表到目标表逻辑关系
     */
    @ApiModelProperty(value = "源表到目标表逻辑关系")
    private String comment;


}
