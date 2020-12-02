package jrx.data.hub.infrastructure.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jrx.data.hub.domain.complier.ScriptLanguage;
import jrx.data.hub.domain.enums.FunctionType;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

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
public class MetaFunctionInfo extends BaseModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("函数id")
    @TableId(type = IdType.ID_WORKER_STR)
    private String resourceId;
    @ApiModelProperty("函数名称")
    private String name;
    @ApiModelProperty("函数标识")
    private String action;
    @ApiModelProperty("函数类型")
    private FunctionType functionType;
    @ApiModelProperty("函数语言")
    private ScriptLanguage language;
    @ApiModelProperty("租户号")
    private String contentCode;
    @ApiModelProperty("示例")
    private String example;
    @ApiModelProperty("描述信息")
    private String description;
    @ApiModelProperty("函数使用与否")
    private boolean used = false;
    @ApiModelProperty("是否是内置函数, true为内置函数，false不是内置函数")
    private boolean buildin = false;
    @ApiModelProperty("对应zeppelin的notebook的段落id")
    private String ZplFunctionId;

}
