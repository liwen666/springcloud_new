package jrx.data.hub.infrastructure.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jrx.data.hub.domain.enums.ResourceType;
import jrx.data.hub.domain.enums.VersionState;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

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
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "表详情实体")
public class MetaDataObjectInfo extends BaseModel implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 表信息ID
     */
    @TableId(type = IdType.ID_WORKER_STR)
    @ApiModelProperty(value = "表信息ID")
    private String resourceId;

    /**
     * 创建人
     */
    private String createPerson;

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

    private String zplJobId;
}
