package jrx.data.hub.domain.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jrx.data.hub.domain.model.work.WorkJobPublishResult;
import jrx.data.hub.domain.service.IMetaWorkInfoService;
import jrx.data.hub.domain.vo.MetaJobObjectVo;
import jrx.data.hub.domain.vo.MetaWorkInfoVo;
import jrx.data.hub.infrastructure.dao.MetaWorkInfoMapper;
import jrx.data.hub.infrastructure.entity.MetaWorkInfo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lw
 * @since 2020-11-05
 */
@Service
public class MetaWorkInfoServiceImpl extends ServiceImpl<MetaWorkInfoMapper, MetaWorkInfo> implements IMetaWorkInfoService {

    @Override
    public MetaWorkInfoVo create(MetaWorkInfoVo metaWorkInfoVo) {
        return null;
    }

    @Override
    public MetaWorkInfoVo update(MetaWorkInfoVo metaWorkInfoVo) {
        return null;
    }

    @Override
    public void removeWork(MetaWorkInfoVo metaWorkInfoVo) {

    }

    @Override
    public void addJob(Long workId, List<MetaJobObjectVo> metaJobObjectList) {

    }

    @Override
    public void removeJob(Long workId, List<MetaJobObjectVo> metaJobObjectList) {

    }

    @Override
    public List<WorkJobPublishResult> publish(Long workId, List<MetaJobObjectVo> metaJobObjectList) {
        return null;
    }

    @Override
    public void jobSort(Long workId, List<MetaJobObjectVo> metaJobObjectList) {

    }
}
