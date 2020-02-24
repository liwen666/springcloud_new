package com.temp.jpa.jpa.jpautil;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 * 生产报表的图表的时候用到的宽表数据集
 *
 * @author yxy
 * @date 2018/12/3
 */
//@ApiModel("宽表数据集")
@Setter
@Getter
public class DataViewInfo {

    /**
     * dataObjectEntity.getDataObjectId()
     */
//    @ApiModelProperty("实体主键")
    protected Integer id;

    /**
     * dataObjectInfoEntity.getName()
     */
//    @ApiModelProperty("实体名称")
    protected String name;

    /**
     *
     */
//    @ApiModelProperty("实体标识，默认null")
    protected String code;

    /**
     * 实体描述
     */
//    @ApiModelProperty("实体描述")
    protected String description;

    /**
     *
     */
//    @ApiModelProperty("实体数据源主键")
    protected Integer sourceId;

    /**
     * 数据源名称
     * key有两个 - id, name
     */
//    @ApiModelProperty("实体款表数据源信息")
    protected Map<String, Object> source;

    /**
     *
     */
//    @ApiModelProperty("类型，默认1")
    protected Integer type;

    /**
     *
     */
//    @ApiModelProperty("实体对象中字段列表")
    protected String model;

    /**
     *
     */
//    @ApiModelProperty("配置")
    protected String config;

//    @ApiModelProperty("是否是主表(宽表)")
    protected Boolean primary;

//    @ApiModelProperty("所属主表(宽表)名称")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    protected String primaryTable;

//    @ApiModelProperty("所属主表(宽表)主键")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    protected Integer primaryTableId;

//    @ApiModelProperty("实体表标识，默认null")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    protected String tableCode;
}
