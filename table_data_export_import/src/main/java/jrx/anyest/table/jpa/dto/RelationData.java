package jrx.anyest.table.jpa.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 *     关联表数据
 * </p>
 *
 * @author lw
 * @since 2019/5/26 23:40
 */
@Setter
@Getter
@Builder
public class RelationData {
    /**
     * 关联表的表名
     */
    private String slaveTableName;
    /**
     * 关联数据ID
     */
    private Set<Object> keys;
    /**
     * 关联表id
     */
    private String key;

    /**
     * 关联键code
     */
    private String keyCode;
    /**
     * 处理器名称
     */
    private String handlerBean;
}
