package jrx.data.hub.domain.model;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 描述 表版本信息
 * </p>
 *
 * @author lw
 * @since 2020-11-05
 */

@Getter
@Setter
@Slf4j
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "表版本信息实体")
public class MetaDataObject implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 表版本ID
     */
    @TableId
    private Long resourceVersionId;

    /**
     * 租户号
     */
    private String contentCode;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 引用ID数组
     */
    private String fieldIds;

    /**
     * 表信息infoID
     */
    private Integer resourceId;

    /**
     * 更新人
     */
    private String updatePerson;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 版本号
     */
    private String versionCode;

    /**
     * 版本状态
     */
    private String versionState;

    /**
     * ddl 语句参数 是一个json数组
     */
    private String paramsJson;

    private Boolean used;

    /**
     * ddl 语句内容
     */
    private String ddlSql;


}
