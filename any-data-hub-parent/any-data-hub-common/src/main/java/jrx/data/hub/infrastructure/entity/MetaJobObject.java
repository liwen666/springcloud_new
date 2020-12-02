package jrx.data.hub.infrastructure.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import jrx.data.hub.domain.enums.VersionState;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 描述 job 版本
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
@ApiModel(description = "job版本实体")
public class MetaJobObject extends BaseModel implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * job 版本ID
     */
    @TableId(type = IdType.ID_WORKER_STR)
    private String jobObjectId;

    /**
     * 租户号
     */
    private String contentCode;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * job 信息ID
     */
    private String resourceId;

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
     * 版本号
     */
    private String versionCode;

    /**
     * 版本状态
     */
    private VersionState versionState;

    /**
     * job 详细内容
     */
    private String sqlContent;

    /**
     * job 参数信息 JSON 数组
     */
    private String paramJson;


}
