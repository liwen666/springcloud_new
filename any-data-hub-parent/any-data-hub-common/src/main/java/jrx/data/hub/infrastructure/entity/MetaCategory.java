package jrx.data.hub.infrastructure.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 描述 分类信息
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
@ApiModel(description = "分类信息实体")
public class MetaCategory extends BaseModel implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 分类ID
     */
    @TableId(type = IdType.ID_WORKER_STR)
    private String categoryId;

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
     * 分类类型
     */
    private String categoryType;

    /**
     * 分类名称
     */
    private String name;

    /**
     * 分类父ID
     */
    private Integer parentId;

    /**
     * 是否启动
     */
    private Boolean used;

}
