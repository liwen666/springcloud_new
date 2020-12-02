package jrx.data.hub.infrastructure.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 描述
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
public class MetaWorkInfo extends BaseModel implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * work ID
     */
    @TableId(type = IdType.ID_WORKER_STR)
    private String resourceId;

    /**
     * 租户号
     */
    private String contentCode;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 创建人
     */
    private String createPerson;

    /**
     * 修改人
     */
    private String updatePerson;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

    /**
     * work状态
     */
    private String workState;

    /**
     * work 对应的zpl的ID
     */
    private String zplNotebookId;

    /**
     * work 对应的zpl的path
     */
    private String notePath;


}
