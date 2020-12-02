package jrx.data.hub.domain.service;

import com.baomidou.mybatisplus.extension.service.IService;
import jrx.data.hub.domain.enums.VersionState;
import jrx.data.hub.domain.vo.MetaJobInfoVo;
import jrx.data.hub.infrastructure.entity.MetaJobInfo;

/**
 * <p>
 * job 信息 服务类
 * </p>
 *
 * @author lw
 * @since 2020-11-05
 */
public interface IMetaJobInfoService extends IService<MetaJobInfo> {

    /**
     * 新建Job信息
     *
     * @param metaJobInfo
     * @return
     */
    MetaJobInfo create(MetaJobInfo metaJobInfo);

    /**
     * 更新Job信息
     *
     * @param metaJobInfo
     * @return
     */
    MetaJobInfo update(MetaJobInfo metaJobInfo);

    /**
     * 查看Job信息
     *
     * @param resourceId
     * @return
     */
    MetaJobInfoVo view(String resourceId);

    /**
     * 更新Job信息状态
     *
     * @param resourceId
     * @param infoState
     */
    void updateVersionState(String resourceId, VersionState infoState);

    /**
     * 删除Job信息和全部版本
     * @param resourceId
     */
    void removeJobInfo(String resourceId);
}
