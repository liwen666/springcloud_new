package jrx.data.hub.domin.vo;

import io.swagger.annotations.ApiModelProperty;
import jrx.data.hub.domain.enums.ResourceType;
import lombok.Getter;
import lombok.Setter;

/**
 * 导出对象，事件模型对象，数据集共用
 */
@Getter
@Setter
public class DownLoadModel {

    @ApiModelProperty(value = "资源主键")
    private String resourceId;

    @ApiModelProperty(value = "资源类别")
    private ResourceType resourceType;

}
