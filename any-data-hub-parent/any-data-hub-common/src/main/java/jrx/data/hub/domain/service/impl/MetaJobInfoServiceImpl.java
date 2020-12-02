package jrx.data.hub.domain.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jrx.data.hub.domain.common.ModelUpdateAssistant;
import jrx.data.hub.domain.enums.VersionState;
import jrx.data.hub.domain.exception.DataException;
import jrx.data.hub.domain.service.IMetaJobInfoService;
import jrx.data.hub.domain.service.IMetaJobObjectService;
import jrx.data.hub.domain.vo.MetaJobInfoVo;
import jrx.data.hub.domain.vo.MetaJobObjectVo;
import jrx.data.hub.infrastructure.dao.MetaJobInfoMapper;
import jrx.data.hub.infrastructure.entity.MetaFunctionInfo;
import jrx.data.hub.infrastructure.entity.MetaJobInfo;
import jrx.data.hub.infrastructure.entity.MetaJobObject;
import jrx.data.hub.util.CommonUtil;
import jrx.data.hub.util.DataTransferUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


/**
 * <p>
 * job 信息 服务实现类
 * </p>
 *
 * @author lw
 * @since 2020-11-05
 */
@Service
public class MetaJobInfoServiceImpl extends ServiceImpl<MetaJobInfoMapper, MetaJobInfo> implements IMetaJobInfoService {

    @Autowired
    private MetaJobInfoMapper metaJobInfoMapper;

    @Autowired
    private IMetaJobObjectService jobObjectService;

    @Override
    public MetaJobInfo create(MetaJobInfo metaJobInfo) {
        QueryWrapper<MetaJobInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("code", metaJobInfo.getCode()).or().eq("name", metaJobInfo.getName());
        MetaJobInfo mInfo = metaJobInfoMapper.selectOne(queryWrapper);
        if (mInfo != null) {
            throw new DataException("表code或表名称已存在");
        }
        mInfo = new MetaJobInfo();
        ModelUpdateAssistant.setCreate(mInfo);
        metaJobInfo.setCode(CommonUtil.getStringRandom(20));
        metaJobInfo.setResourceId(null);
        metaJobInfo.setJobState(VersionState.INACTIVE);
        int count = metaJobInfoMapper.insert(metaJobInfo);
        if (count <= 0) {
            throw new DataException("新建表信息失败");
        }
        String resourceId = metaJobInfo.getResourceId();
        MetaJobObject metaJobObject = new MetaJobObject();
        ModelUpdateAssistant.setCreate(metaJobObject);
        metaJobObject.setResourceId(resourceId);
        metaJobObject.setVersionCode("0");
        metaJobObject.setVersionState(VersionState.INACTIVE);
        jobObjectService.create(metaJobObject);
        return metaJobInfo;
    }

    @Override
    public MetaJobInfo update(MetaJobInfo metaJobInfo) {
        QueryWrapper<MetaJobInfo> infoQueryWrapper = new QueryWrapper<>();
        infoQueryWrapper.eq("name", metaJobInfo.getName());
        List<MetaJobInfo> oldInfos = metaJobInfoMapper.selectList(infoQueryWrapper);
        if (oldInfos != null && oldInfos.size() > 1) {
            throw new DataException("name已经存在");
        }
        ModelUpdateAssistant.setUpdate(metaJobInfo);
        int count = metaJobInfoMapper.updateById(metaJobInfo);
        if (count <= 0) {
            throw new DataException("修改表版本失败");
        }
        return metaJobInfo;
    }

    @Override
    public MetaJobInfoVo view(String resourceId) {
        MetaJobInfo metaJobInfo = metaJobInfoMapper.selectById(resourceId);
        Optional<MetaJobObjectVo> latestVersion = jobObjectService.getLatestVersionByInfoId(resourceId);
        MetaJobInfoVo metaJobInfoVo = DataTransferUtils.modelToVo(metaJobInfo, new MetaJobInfoVo());
        if (latestVersion.isPresent()) {
            metaJobInfoVo.setLatestVersionId(latestVersion.get().getJobObjectId());
        }

        return metaJobInfoVo;
    }

    @Override
    public void updateVersionState(String resourceId, VersionState infoState) {
        MetaJobInfo mInfo = metaJobInfoMapper.selectById(resourceId);
        mInfo.setJobState(infoState);
        metaJobInfoMapper.updateById(mInfo);

        jobObjectService.updateVersionStateByInfoId(resourceId, infoState);
    }

    @Override
    public void removeJobInfo(String resourceId) {
        metaJobInfoMapper.deleteById(resourceId);
        jobObjectService.removeByInfoId(resourceId);
    }
}
