package jrx.data.hub.infrastructure.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * <p>
 * 描述
 * </p>
 *
 * @author lw
 * @since 2020-11-16
 */

@Getter
@Setter
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "分类信息实体")
public class MetaTenant extends BaseModel implements Serializable {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "租户id")
    @TableId(type = IdType.ID_WORKER_STR)
    private String tenantId;
    @ApiModelProperty(value = "租户名称")
    private String tenantName;
    @ApiModelProperty(value = "租户标识")
    private String tenantCode;
    @ApiModelProperty(value = "租户描述")
    private String tenantDescription;


}
