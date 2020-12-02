package jrx.data.hub.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jrx.data.hub.domain.enums.ResourceType;
import jrx.data.hub.domain.enums.VersionState;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 描述 表详情
 * </p>
 *
 * @author lw
 * @since 2020-11-05
 */

@Getter
@Setter
@Slf4j
@NoArgsConstructor
@ApiModel(description = "表详情实体")
public class MetaDataObjectInfoVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 表信息ID
     */
    @ApiModelProperty(value = "表信息ID")
    private String resourceId;

    /**
     * 表的唯一标识
     */
    @ApiModelProperty(value = "表的唯一标识")
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
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
     * 描述信息
     */
    @ApiModelProperty(value = "描述信息")
    private String description;

    /**
     * 表名称
     */
    @ApiModelProperty(value = "表名称")
    @NotEmpty(message = "表名称不能为空")
    private String name;

    /**
     * 表状态
     */
    @ApiModelProperty(value = "表状态")
    private VersionState resourceState;

    /**
     * 表类型
     */
    @ApiModelProperty(value = "表类型")
    private ResourceType resourceType;

    /**
     * 更新人
     */
    @ApiModelProperty(value = "更新人")
    private String updatePerson;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    /**
     * 表所属数据源ID
     */
    @ApiModelProperty(value = "表所属数据源ID")
    private String dataSourceId;

    /**
     * 例如kafka表等的配置信息
     */
    @ApiModelProperty(value = "kafka表等的配置信息")
    private String configProps;

    @ApiModelProperty(value = "最新表版本ID")
    private String lastResourceVersionId;

    @ApiModelProperty(value = "最新表版本号")
    private String lastVersionCode;

    @ApiModelProperty(value = "数据集")
    private List<MetaDataObjectVo> metaDataObjectVos;
}
