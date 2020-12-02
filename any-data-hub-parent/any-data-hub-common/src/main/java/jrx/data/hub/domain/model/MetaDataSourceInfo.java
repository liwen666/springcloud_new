package jrx.data.hub.domain.model;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.time.LocalDateTime;

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
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "数据源信息实体")
public class MetaDataSourceInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 数据源ID
     */
    @TableId
    private Long dataSourceId;

    /**
     * 租户号
     */
    private String contentCode;

    /**
     * 创建人
     */
    private String createPerson;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新人
     */
    private String updatePerson;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 连接器的配置信息
     */
    private String dbConfigJson;

    /**
     * 数据源类型
     */
    private String dbType;

    /**
     * 描述信息
     */
    private String description;

    /**
     * 数据源标识
     */
    private String sourceCode;

    /**
     * 数据源名称
     */
    private String sourceName;

    /**
     * 定时器表达式
     */
    private String cronExpression;

}
