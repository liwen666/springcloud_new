package jrx.data.hub.infrastructure.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jrx.data.hub.domain.complier.ScriptLanguage;
import jrx.data.hub.domain.enums.VersionState;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * <p>
 * 描述 函数版本
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
@ApiModel(description = "函数版本实体")
public class MetaFunction extends BaseModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("函数版本ID")
    @TableId(type = IdType.ID_WORKER_STR)
    private String functionId;
    @ApiModelProperty("租户号")
    private String contentCode;
    @ApiModelProperty("函数信息ID")
    private String resourceId;
    @ApiModelProperty("版本号")
    private Integer versionCode;
    @ApiModelProperty("版本状态")
    private VersionState versionState = VersionState.INACTIVE;
    @ApiModelProperty("函数参数")
    private String paramFields;
    @ApiModelProperty("函数参数类型")
    private String paramFieldTypes;
    @ApiModelProperty("函数返回值类型")
    private String returnValueType = "java.lang.Object";
    @ApiModelProperty("函数脚本内容")
    private String scripts;
    @ApiModelProperty("函数语言")
    private ScriptLanguage language;
}
