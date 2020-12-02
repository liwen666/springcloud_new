package jrx.data.hub.domin.vo;

import jrx.data.hub.domain.enums.ResourceType;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * <p>
 * 描述
 * </p>
 *
 * @author LW
 * @since 2020/9/21  17:11
 */
@Getter
@Setter
public class BaseDetailInfo {
    /**
     * 名称
     */
    private String name;
    /**
     * 类型名称
     */
    private ResourceType resourceType;
    /**
     * 所属分类名称
     */
    private String categoryName;

    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 最后修改时间
     */

    private Date updateTime;
}
