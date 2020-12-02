package jrx.data.hub.domain.common;

import jrx.data.hub.infrastructure.entity.BaseModel;

import java.time.LocalDateTime;

/**
 * <p>
 * 描述
 * </p>
 *
 * @author LW
 * @since 2020/11/16  10:53
 */
public class ModelUpdateAssistant {

    /**
     * 通用的属性更新
     * @param baseModel
     */
    public static void setCreate(BaseModel baseModel) {
        LocalDateTime localDateTime = LocalDateTime.now();
        baseModel.setCreatePerson("admin");
        baseModel.setCreateTime(localDateTime);
        // 待获取系统用户
        baseModel.setUpdatePerson("admin");
        baseModel.setUpdateTime(localDateTime);
    }

    /**
     * 通用的属性更新
     * @param baseModel
     */
    public static void setUpdate(BaseModel baseModel) {
        LocalDateTime localDateTime = LocalDateTime.now();
        // 待获取系统用户
        baseModel.setUpdatePerson("admin");
        baseModel.setUpdateTime(localDateTime);
    }
}
