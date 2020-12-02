package jrx.data.hub.domain.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jrx.data.hub.domain.complier.ScriptLanguage;
import jrx.data.hub.domain.enums.FunctionType;
import jrx.data.hub.domain.enums.VersionState;
import jrx.data.hub.infrastructure.entity.MetaFunction;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 描述 函数信息
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
@ApiModel(description = "函数信息实体")
public class MetaFunctionInfoVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.ID_WORKER_STR)
    private String resourceId;
    @ApiModelProperty("函数名称")
    private String name;
    @ApiModelProperty("函数标识")
    private String action;
    @ApiModelProperty("租户号")
    private String contentCode;
    @ApiModelProperty("函数类型")
    private FunctionType functionType;
    @ApiModelProperty("函数语言")
    private ScriptLanguage language;
    @ApiModelProperty("函数示例")
    private String example;
    @ApiModelProperty("函数描述")
    private String description;
    @ApiModelProperty("函数使用")
    private boolean used = false;
    @ApiModelProperty("版本状态")
    private VersionState versionState = VersionState.INACTIVE;
    @ApiModelProperty(value = "创建人")
    private String createPerson;
    @ApiModelProperty(value = "创建时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    @ApiModelProperty(value = "更新人")
    private String updatePerson;
    @ApiModelProperty(value = "更新时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
    @ApiModelProperty("历史函数版本信息")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<MetaFunction> metaFunctionList;
    @ApiModelProperty("历史函数版本信息")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private MetaFunctionVo onlineVersion;
    @ApiModelProperty("是否是内置函数, true为内置函数，false不是内置函数")
    private boolean buildin = false;
    @ApiModelProperty("函数最新版本列表")
    private String lastFunctionId;
}
