package jrx.data.hub.domain.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jrx.data.hub.domain.complier.ScriptLanguage;
import jrx.data.hub.domain.enums.VersionState;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

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
public class MetaFunctionVo implements Serializable {

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
    @NotNull(message = "函数语言不能为空")
    @ApiModelProperty("函数语言")
    private ScriptLanguage language;
    @ApiModelProperty("函数参数, 由逗号分隔")
    private String paramFields;
    @ApiModelProperty("函数参数值, 由逗号分隔")
    private String paramVals;
    @ApiModelProperty("函数参数类型, 由逗号分隔,例如java.lang.String, java.lang.String")
    private String paramFieldTypes;
    @ApiModelProperty("函数返回值类型")
    private String returnValueType;
    @NotEmpty(message = "函数脚本内容不能为空")
    @ApiModelProperty("函数脚本内容")
    private String scripts;
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

    @ApiModelProperty(value = "函数的名称")
    private String functionName;


}
