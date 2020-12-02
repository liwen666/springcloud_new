package jrx.data.hub.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jrx.data.hub.domain.enums.DbType;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 描述 数据源信息
 * </p>
 *
 * @author lw
 * @since 2020-11-05
 */

@Getter
@Setter
@Slf4j
@ApiModel(description = "数据源信息实体")
public class MetaDataSourceInfoVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 数据源ID
     */
    @ApiModelProperty(value = "数据源ID")
    private String dataSourceId;

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
     * 连接器的配置信息
     */
    @ApiModelProperty(value = "连接器的配置信息", example = "\"{\"url\":\"url\",\"extend\":{\"param1\":\"value1\"}}\"")
    @NotEmpty(message = "连接器的配置信息不能为空")
    private String dbConfigJson;

    /**
     * 数据源类型
     */
    @ApiModelProperty(value = "数据源类型")
    private DbType dbType;

    /**
     * 描述信息
     */
    @ApiModelProperty(value = "描述信息")
    private String description;

    /**
     * 数据源标识
     */
    @ApiModelProperty(value = "数据源标识")
    private String sourceCode;

    /**
     * 数据源名称
     */
    @ApiModelProperty(value = "数据源名称")
    @NotEmpty(message = "数据源名称不能为空")
    private String sourceName;

    /**
     * 数据源名称
     */
    @ApiModelProperty(value = "数据库名称")
    @NotEmpty(message = "数据库名称不能为空")
    private String dbName;

    /**
     * 定时器表达式
     */
    @ApiModelProperty(value = "定时器表达式")
    @NotEmpty(message = "定时器表达式不能为空")
    private String cronExpression;

    /**
     * 定时器表达式
     */
    @ApiModelProperty(value = "上线数据集版本")
    private List<MetaDataObjectVo> onlineMetaDataObjectVoList;


}
