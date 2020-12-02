package jrx.data.hub.domain.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jrx.data.hub.domain.common.ModelUpdateAssistant;
import jrx.data.hub.domain.enums.VersionState;
import jrx.data.hub.domain.exception.DataException;
import jrx.data.hub.domain.exception.DataSourceException;
import jrx.data.hub.domain.service.IMetaFunctionInfoService;
import jrx.data.hub.domain.service.IMetaFunctionService;
import jrx.data.hub.domain.vo.MetaFunctionInfoVo;
import jrx.data.hub.domain.vo.MetaFunctionVo;
import jrx.data.hub.infrastructure.dao.MetaFunctionInfoMapper;
import jrx.data.hub.infrastructure.dao.MetaFunctionMapper;
import jrx.data.hub.infrastructure.entity.MetaFunction;
import jrx.data.hub.infrastructure.entity.MetaFunctionInfo;
import jrx.data.hub.util.DataTransferUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * <p>
 * 函数信息 服务实现类
 * </p>
 *
 * @author lw
 * @since 2020-11-05
 */
@Transactional(rollbackFor = DataException.class)
@Service
public class MetaFunctionInfoServiceImpl extends ServiceImpl<MetaFunctionInfoMapper, MetaFunctionInfo> implements IMetaFunctionInfoService {

    @Autowired
    private MetaFunctionInfoMapper infoMapper;

    @Autowired
    private MetaFunctionMapper functionMapper;

    @Autowired
    private IMetaFunctionService functionService;

    @Override
    public MetaFunctionInfo create(MetaFunctionInfo functionInfo) {
        QueryWrapper<MetaFunctionInfo> infoQueryWrapper = new QueryWrapper<>();
        infoQueryWrapper.eq("name", functionInfo.getName());
        MetaFunctionInfo info = infoMapper.selectOne(infoQueryWrapper);
        if (info != null) {
            throw new DataException("函数name已经存在");
        }
        ModelUpdateAssistant.setCreate(functionInfo);
        functionInfo.setResourceId(null);
        int count = infoMapper.insert(functionInfo);
        if (count <= 0) {
            throw new DataSourceException("新建函数失败");
        }
        MetaFunction function = new MetaFunction();
        ModelUpdateAssistant.setCreate(function);
        function.setResourceId(functionInfo.getResourceId());
        function.setContentCode(functionInfo.getContentCode());
        function.setVersionState(VersionState.INACTIVE);
        function.setVersionCode(1);
        count = functionMapper.insert(function);
        if (count <= 0) {
            throw new DataSourceException("新建函数版本失败");
        }
        return functionInfo;
    }

    @Override
    public MetaFunctionInfo update(MetaFunctionInfo info) {
        QueryWrapper<MetaFunctionInfo> infoQueryWrapper = new QueryWrapper<>();
        infoQueryWrapper.eq("name", info.getName());
        List<MetaFunctionInfo> oldInfos = infoMapper.selectList(infoQueryWrapper);
        if (oldInfos != null && oldInfos.size() > 1) {
            throw new DataException("函数name已经存在");
        }
        ModelUpdateAssistant.setUpdate(info);
        int count = infoMapper.updateById(info);
        if (count <= 0) {
            throw new DataSourceException("函数信息更新失败");
        }
        return info;
    }

    @Override
    public List<MetaFunctionInfoVo> listAll() {
        List<MetaFunctionInfo> infoList = super.list();
        List<MetaFunctionInfoVo> infoVoList = DataTransferUtils.modelListToVoList(infoList, MetaFunctionInfoVo.class);
        for (MetaFunctionInfoVo functionInfoVo : infoVoList) {
            Optional<MetaFunctionVo> onlineVersion = functionService.getOnlineFunctionByInfoId(functionInfoVo.getResourceId());
            if (onlineVersion.isPresent()) {
                functionInfoVo.setOnlineVersion(onlineVersion.get());
            }
        }

        return infoVoList;
    }

    @Override
    public boolean deleteByResourceId(String resourceId) {
        MetaFunctionInfo metaFunctionInfo = this.getById(resourceId);
        if (metaFunctionInfo != null && metaFunctionInfo.isUsed()) {
            throw new DataException("该函数已经被使用，无法删除，请处理");
        }
        MetaFunction function = functionService.getOne(Wrappers.<MetaFunction>lambdaQuery().eq(MetaFunction::getResourceId, resourceId).orderBy(true, false, MetaFunction::getCreateTime).last("limit 1"));
        if (function == null || function.getVersionState().equals(VersionState.ONLINE)) {
            throw new DataException("该函数不存在或者正在启动，无法删除，请下线后在操作");
        } else {
            functionService.removeByMap(Collections.singletonMap("resource_id", resourceId));
        }
        return this.removeById(resourceId);
    }

}
