package jrx.data.hub.domain.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jrx.data.hub.domain.common.ModelUpdateAssistant;
import jrx.data.hub.domain.exception.DataSourceException;
import jrx.data.hub.domain.enums.VersionState;
import jrx.data.hub.domain.service.IMetaDataObjectInfoService;
import jrx.data.hub.domain.service.IMetaDataObjectService;
import jrx.data.hub.domain.service.IMetaObjectFieldService;
import jrx.data.hub.domain.vo.MetaDataObjectVo;
import jrx.data.hub.domain.vo.MetaObjectFieldVo;
import jrx.data.hub.infrastructure.dao.MetaDataObjectInfoMapper;
import jrx.data.hub.infrastructure.dao.MetaDataObjectMapper;
import jrx.data.hub.infrastructure.entity.MetaDataObject;
import jrx.data.hub.infrastructure.entity.MetaDataObjectInfo;
import jrx.data.hub.infrastructure.entity.MetaObjectField;
import jrx.data.hub.util.DataTransferUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * <p>
 * 表版本信息 服务实现类
 * </p>
 *
 * @author zhangch
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class MetaDataObjectServiceImpl extends ServiceImpl<MetaDataObjectMapper, MetaDataObject> implements IMetaDataObjectService {

    @Autowired
    private MetaDataObjectMapper metaDataObjectMapper;

    @Autowired
    private MetaDataObjectInfoMapper metaDataObjectInfoMapper;

    @Autowired
    private IMetaDataObjectInfoService metaDataObjectInfoService;

    @Autowired
    private IMetaObjectFieldService metaObjectFieldService;

    @Override
    public MetaDataObject create(MetaDataObject metaDataObject) {
        ModelUpdateAssistant.setCreate(metaDataObject);
        metaDataObject.setVersionState(VersionState.INACTIVE);
        metaDataObject.setResourceVersionId(null);
        Integer versionCode = Integer.parseInt(metaDataObject.getVersionCode()) + 1;
        metaDataObject.setVersionCode(versionCode + "");
        int count = metaDataObjectMapper.insert(metaDataObject);
        if (count <= 0) {
            throw new DataSourceException("新建表版本失败");
        }
        return metaDataObject;
    }

    public MetaDataObject createNewVersion(MetaDataObject metaDataObject) {
        ModelUpdateAssistant.setCreate(metaDataObject);
        metaDataObject.setVersionState(VersionState.INACTIVE);
        metaDataObject.setResourceVersionId(null);
        QueryWrapper<MetaDataObject> listParam = new QueryWrapper<>();
        listParam.eq("resource_id", metaDataObject.getResourceId());
        listParam.orderByDesc("create_time");
        List<MetaDataObject> metaDataObjects = metaDataObjectMapper.selectList(listParam);
        if (metaDataObjects == null && metaDataObjects.size() == 0) {
            throw new DataSourceException("另存表版本失败");
        }
        Integer versionCode = Integer.parseInt(metaDataObjects.get(0).getVersionCode()) + 1;
        metaDataObject.setVersionCode(versionCode + "");
        int count = metaDataObjectMapper.insert(metaDataObject);
        if (count <= 0) {
            throw new DataSourceException("另存表版本失败");
        }
        return metaDataObject;
    }

    @Override
    public MetaDataObject update(MetaDataObject metaDataObject) {
        ModelUpdateAssistant.setUpdate(metaDataObject);
        int count = metaDataObjectMapper.updateById(metaDataObject);
        if (count <= 0) {
            throw new DataSourceException("修改表版本失败");
        }
        return metaDataObject;
    }

    public MetaDataObject updateVersionState(String resourceVersionId, VersionState versionState) {
        MetaDataObject metaDataObject = metaDataObjectMapper.selectById(resourceVersionId);
        QueryWrapper<MetaDataObject> listParam = new QueryWrapper<>();
        listParam.eq("resource_id", metaDataObject.getResourceId());
        List<MetaDataObject> list = metaDataObjectMapper.selectList(listParam);
        for (MetaDataObject obj : list) {
            if (versionState == VersionState.ONLINE) {
                if (obj.getResourceVersionId().equals(resourceVersionId)) {
                    obj.setVersionState(VersionState.ONLINE);
                } else {
                    obj.setVersionState(VersionState.OFFLINE);
                }
            } else if (versionState == VersionState.OFFLINE) {
                obj.setVersionState(VersionState.OFFLINE);
            }
            metaDataObjectMapper.updateById(obj);
        }
        QueryWrapper<MetaDataObjectInfo> param = new QueryWrapper<>();
        param.eq("resource_id", metaDataObject.getResourceId());
        MetaDataObjectInfo metaDataObjectInfo = metaDataObjectInfoMapper.selectOne(param);
        metaDataObjectInfo.setResourceState(versionState);
        int count = metaDataObjectInfoMapper.updateById(metaDataObjectInfo);
        if (count <= 0) {
            throw new DataSourceException("修改表状态失败");
        }
        metaDataObject = metaDataObjectMapper.selectById(resourceVersionId);
        return metaDataObject;
    }

    @Override
    public Optional<MetaDataObjectVo> getOnlineDataObjectByInfoId(String resourceId) {
        QueryWrapper<MetaDataObject> dataObjectQueryWrapper = new QueryWrapper<>();
        dataObjectQueryWrapper.eq("resource_id", resourceId);
        dataObjectQueryWrapper.eq("version_state", "ONLINE");

        MetaDataObject onlineDataObject = metaDataObjectMapper.selectOne(dataObjectQueryWrapper);
        if (onlineDataObject == null) {
            return Optional.empty();
        }
        MetaDataObjectVo metaDataObjectVo = DataTransferUtils.modelToVo(onlineDataObject, new MetaDataObjectVo());
        MetaDataObjectInfo dataObjectInfo = metaDataObjectInfoService.getById(resourceId);
        //添加数据集名称，方便前端回显
        metaDataObjectVo.setName(dataObjectInfo.getName());

        QueryWrapper<MetaObjectField> fieldQueryWrapper = new QueryWrapper<>();
        dataObjectQueryWrapper.eq("resource_id", resourceId);

        // TODO: 2020/11/25
//        List<MetaObjectField> fieldList = metaObjectFieldService.list(fieldQueryWrapper);
        List<MetaObjectField> fieldList = mockFieldList();
        List<MetaObjectFieldVo> metaObjectFieldVoList = DataTransferUtils.modelListToVoList(fieldList, MetaObjectFieldVo.class);

        metaDataObjectVo.setMetaObjectFieldList(metaObjectFieldVoList);

        return Optional.of(metaDataObjectVo);
    }

    private List<MetaObjectField> mockFieldList() {
        List<MetaObjectField> result = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            MetaObjectField field = new MetaObjectField();
            field.setContentCode("123");
            field.setFieldName("mock field" + i);
            field.setFieldCode("mock field" + i);
            field.setObjectFieldId("" + i);
            field.setUpdateTime(LocalDateTime.now());
            field.setObjectFieldId("" + i);
            result.add(field);
        }
        return result;
    }
}