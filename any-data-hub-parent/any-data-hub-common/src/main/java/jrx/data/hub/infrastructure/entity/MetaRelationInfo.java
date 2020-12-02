package jrx.data.hub.infrastructure.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * <p>
 * 描述,对象之间的关联关系
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
public class MetaRelationInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 源表ID
     */
    @TableId(type = IdType.ID_WORKER_STR)
    private String sourceId;

    /**
     * 源表类型
     */
    private String sourceType;

    /**
     * 目标表ID
     */
    private Long targetId;

    /**
     * 目标表类型
     */
    private String targetType;

    /**
     * 源表到目标表逻辑关系
     */
    private String comment;

}
