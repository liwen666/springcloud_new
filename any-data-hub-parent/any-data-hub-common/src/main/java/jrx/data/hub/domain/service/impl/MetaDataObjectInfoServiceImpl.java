package jrx.data.hub.domain.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jrx.data.hub.domain.common.ModelUpdateAssistant;
import jrx.data.hub.domain.enums.DbType;
import jrx.data.hub.domain.enums.ResourceType;
import jrx.data.hub.domain.enums.RespStatus;
import jrx.data.hub.domain.exception.DataSourceException;
import jrx.data.hub.domain.enums.VersionState;
import jrx.data.hub.domain.service.IMetaDataObjectInfoService;
import jrx.data.hub.domain.service.IMetaDataObjectService;
import jrx.data.hub.domain.vo.MetaDataObjectVo;
import jrx.data.hub.domain.vo.MetaDataSourceInfoVo;
import jrx.data.hub.infrastructure.dao.MetaDataObjectInfoMapper;
import jrx.data.hub.infrastructure.dao.MetaDataObjectMapper;
import jrx.data.hub.infrastructure.entity.MetaDataObject;
import jrx.data.hub.infrastructure.entity.MetaDataObjectInfo;
import jrx.data.hub.infrastructure.entity.MetaDataSourceInfo;
import jrx.data.hub.util.CommonUtil;
import jrx.data.hub.util.DataResponse;
import jrx.data.hub.util.DataTransferUtils;
import net.bytebuddy.asm.Advice;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * <p>
 * 表详情 服务实现类
 * </p>
 *
 * @author zhangch
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class MetaDataObjectInfoServiceImpl extends ServiceImpl<MetaDataObjectInfoMapper, MetaDataObjectInfo> implements IMetaDataObjectInfoService {

    @Autowired
    private MetaDataObjectInfoMapper metaDataObjectInfoMapper;
    @Autowired
    private MetaDataObjectMapper metaDataObjectMapper;
    @Autowired
    private IMetaDataObjectService metaDataObjectService;
    @Autowired
    ZeppelinServiceImpl zeppelinServiceImpl;
    @Autowired
    MetaDataSourceInfoServiceImpl metaDataSourceInfoServiceImpl;
    @Autowired
    CollectorServiceImpl collectorServiceImpl;

    @Override
    public MetaDataObjectInfo createCollectTable(MetaDataObjectInfo metaDataObjectInfo) {
        QueryWrapper<MetaDataObjectInfo> queryWrapper = new QueryWrapper<MetaDataObjectInfo>();
        queryWrapper.eq("code", metaDataObjectInfo.getCode()).or().eq("name", metaDataObjectInfo.getName());
        MetaDataObjectInfo mInfo = metaDataObjectInfoMapper.selectOne(queryWrapper);
        if (mInfo != null) {
            throw new DataSourceException("表code或表名称已存在");
        }
        ModelUpdateAssistant.setCreate(metaDataObjectInfo);
        metaDataObjectInfo.setCode(CommonUtil.getStringRandom(20));
        metaDataObjectInfo.setResourceType(ResourceType.COLLECTION);
        metaDataObjectInfo.setResourceState(VersionState.INACTIVE);
        metaDataObjectInfo.setResourceId(null);
        int count = metaDataObjectInfoMapper.insert(metaDataObjectInfo);
        if (count <= 0) {
            throw new DataSourceException("新建采集表信息失败");
        }
        String resourceId = metaDataObjectInfo.getResourceId();
        MetaDataSourceInfo metaDataSourceInfo = metaDataSourceInfoServiceImpl.getOne(Wrappers.<MetaDataSourceInfo>lambdaQuery().eq(MetaDataSourceInfo::getDataSourceId, metaDataObjectInfo.getDataSourceId()));
        String ddlSql = "";
        if(metaDataSourceInfo.getDbType()==DbType.MYSQL){
            ddlSql = collectorServiceImpl.getMysqlDdl(metaDataSourceInfo.getSourceName(),metaDataObjectInfo);
        }else if(metaDataSourceInfo.getDbType()==DbType.GREENPLUM){
            ddlSql = collectorServiceImpl.getGreenPlumDdl(metaDataSourceInfo.getSourceName(),metaDataObjectInfo);
        }
        if(StringUtils.isEmpty(ddlSql)){
            throw new DataSourceException("新建采集表信息失败,调用zeeplin表获取ddl失败");
        }
        MetaDataObject metaDataObject =  new MetaDataObject();
        ModelUpdateAssistant.setCreate(metaDataObject);
        metaDataObject.setResourceId(resourceId);
        metaDataObject.setDdlSql(ddlSql);
        metaDataObject.setVersionCode("1");
        metaDataObject.setVersionState(VersionState.INACTIVE);
        count = metaDataObjectMapper.insert(metaDataObject);
        if (count <= 0) {
            throw new DataSourceException("新建采集表版本失败");
        }
        return metaDataObjectInfo;
    }

    @Override
    public MetaDataObjectInfo createTargetTable(MetaDataObjectInfo metaDataObjectInfo) {
        QueryWrapper<MetaDataObjectInfo> queryWrapper = new QueryWrapper<MetaDataObjectInfo>();
        queryWrapper.eq("code", metaDataObjectInfo.getCode()).or().eq("name", metaDataObjectInfo.getName());
        MetaDataObjectInfo mInfo = metaDataObjectInfoMapper.selectOne(queryWrapper);
        if (mInfo != null) {
            throw new DataSourceException("表code或表名称已存在");
        }
        ModelUpdateAssistant.setCreate(metaDataObjectInfo);
        metaDataObjectInfo.setCode(CommonUtil.getStringRandom(20));
        metaDataObjectInfo.setResourceType(ResourceType.TARGET);
        metaDataObjectInfo.setResourceState(VersionState.INACTIVE);
        metaDataObjectInfo.setResourceId(null);
        int count = metaDataObjectInfoMapper.insert(metaDataObjectInfo);
        if (count <= 0) {
            throw new DataSourceException("新建目标表信息失败");
        }
        String resourceId = metaDataObjectInfo.getResourceId();
        MetaDataObject metaDataObject =  new MetaDataObject();
        ModelUpdateAssistant.setCreate(metaDataObject);
        metaDataObject.setResourceId(resourceId);
        metaDataObject.setVersionCode("1");
        metaDataObject.setVersionState(VersionState.INACTIVE);
        count = metaDataObjectMapper.insert(metaDataObject);
        if (count <= 0) {
            throw new DataSourceException("新建目标表版本失败");
        }
        MetaDataSourceInfo metaDataSourceInfo = metaDataSourceInfoServiceImpl.getOne(Wrappers.<MetaDataSourceInfo>lambdaQuery().eq(MetaDataSourceInfo::getDataSourceId, metaDataObjectInfo.getDataSourceId()));
        if(metaDataSourceInfo.getDbType()==DbType.KAFKA){
            //创建topic

        }
        return metaDataObjectInfo;
    }

    @Override
    public MetaDataObjectInfo update(MetaDataObjectInfo metaDataObjectInfo) {
        QueryWrapper<MetaDataObjectInfo> queryWrapper = new QueryWrapper<MetaDataObjectInfo>();
        queryWrapper.eq("code", metaDataObjectInfo.getCode());
        MetaDataObjectInfo mInfo = metaDataObjectInfoMapper.selectOne(queryWrapper);
        if (mInfo == null) {
            throw new DataSourceException("表信息不存在");
        }
        DataTransferUtils.modelToVo(metaDataObjectInfo, mInfo);
        ModelUpdateAssistant.setUpdate(mInfo);
        int count = metaDataObjectInfoMapper.updateById(mInfo);
        if (count <= 0) {
            throw new DataSourceException("修改表信息失败");
        }
        return metaDataObjectInfo;
    }

    public void delete(String resourceId) {
        int count = metaDataObjectInfoMapper.deleteById(resourceId);
        if (count <= 0) {
            throw new DataSourceException("删除表信息失败");
        }
        QueryWrapper<MetaDataObject> param = new QueryWrapper<>();
        param.eq("resource_id", resourceId);
        List<MetaDataObject> metaDataObjects = metaDataObjectMapper.selectList(param);
        List<String> idList = new ArrayList<>();
        metaDataObjects.forEach(info -> {
            idList.add(info.getResourceVersionId());
        });
        count = metaDataObjectMapper.deleteBatchIds(idList);
        if (count <= 0) {
            throw new DataSourceException("删除表信息失败");
        }
    }

    @Override
    public List<MetaDataObjectVo> getOnlineDataObjectListByDataSourceId(String dataSourceId) {
        QueryWrapper<MetaDataObjectInfo> queryWrapper = new QueryWrapper<>();

        List<MetaDataObjectVo> onlineDataObjectVoList = new ArrayList<>();

        List<MetaDataObjectInfo> metaDataObjectInfoList = metaDataObjectInfoMapper.selectList(queryWrapper);

        for (MetaDataObjectInfo info : metaDataObjectInfoList) {
            Optional<MetaDataObjectVo> onlineDataObject = metaDataObjectService.getOnlineDataObjectByInfoId(info.getResourceId());
            if (onlineDataObject.isPresent()) {
                onlineDataObjectVoList.add(onlineDataObject.get());
            }

        }
        return onlineDataObjectVoList;
    }
}