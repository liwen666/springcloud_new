package jrx.data.hub.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 描述 分类信息
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
@ApiModel(description = "分类信息实体")
public class MetaCategoryVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 分类ID
     */
    @ApiModelProperty(value = "分类ID", example = "0")
    private String categoryId;

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
     * 分类类型
     */
    @ApiModelProperty(value = "分类类型")
    private String categoryType;

    /**
     * 分类名称
     */
    @ApiModelProperty(value = "分类名称")
    private String name;

    /**
     * 分类父ID
     */
    @ApiModelProperty(value = "分类父ID", example = "0")
    private Integer parentId;

    /**
     * 是否启动
     */
    @ApiModelProperty(value = "是否启动")
    private Boolean used;


}
