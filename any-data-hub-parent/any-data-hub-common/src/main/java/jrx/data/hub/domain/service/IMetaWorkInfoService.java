package jrx.data.hub.domain.service;

import com.baomidou.mybatisplus.extension.service.IService;
import jrx.data.hub.domain.model.work.WorkJobPublishResult;
import jrx.data.hub.domain.vo.MetaJobObjectVo;
import jrx.data.hub.domain.vo.MetaWorkInfoVo;
import jrx.data.hub.infrastructure.entity.MetaWorkInfo;

import java.util.List;

/**
 * <p>
 *  Work操作服务
 *</p>
 * @author lw
 * @since 2020-11-05
 */
public interface IMetaWorkInfoService extends IService<MetaWorkInfo> {

    /**
     * <p>
     * 创建Work信息
     * </p>
     *
     * @param metaWorkInfoVo
     * @return
     */
    MetaWorkInfoVo create(MetaWorkInfoVo metaWorkInfoVo);

    /**
     * <p>
     * 更新Work信息
     * </p>
     * @param metaWorkInfoVo
     * @return
     */
    MetaWorkInfoVo update(MetaWorkInfoVo metaWorkInfoVo);

    /**
     * <p>
     * 移除Work
     * </p>
     * @param metaWorkInfoVo
     */
    void removeWork(MetaWorkInfoVo metaWorkInfoVo);

    /**
     * <p>
     * 添加任务
     * </p>
     * @param workId
     * @param metaJobObjectList
     */
    void addJob(Long workId, List<MetaJobObjectVo> metaJobObjectList);

    /**
     * <p>
     * 移除任务
     * </p>
     * @param workId
     * @param metaJobObjectList
     */
    void removeJob(Long workId, List<MetaJobObjectVo> metaJobObjectList);

    /**
     * <p>
     * 任务发布
     * </p>
     * @param workId
     * @param metaJobObjectList
     * @return
     */
    List<WorkJobPublishResult> publish(Long workId, List<MetaJobObjectVo> metaJobObjectList);

    /**
     * </p>
     * 任务排序
     * </p>
     * @param workId
     * @param metaJobObjectList
     */
    void jobSort(Long workId, List<MetaJobObjectVo> metaJobObjectList);

}
