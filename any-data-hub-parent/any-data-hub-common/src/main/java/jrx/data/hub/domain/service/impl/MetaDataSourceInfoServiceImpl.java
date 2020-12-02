package jrx.data.hub.domain.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jrx.data.hub.domain.common.ModelUpdateAssistant;
import jrx.data.hub.domain.exception.DataSourceException;
import jrx.data.hub.domain.service.IMetaDataObjectInfoService;
import jrx.data.hub.domain.service.IMetaDataSourceInfoService;
import jrx.data.hub.domain.vo.MetaDataObjectVo;
import jrx.data.hub.domain.vo.MetaDataSourceInfoVo;
import jrx.data.hub.infrastructure.dao.MetaDataSourceInfoMapper;
import jrx.data.hub.infrastructure.dto.JobExecuteResult;
import jrx.data.hub.infrastructure.entity.MetaDataSourceInfo;
import jrx.data.hub.infrastructure.zeppelin.IJobOperator;
import jrx.data.hub.infrastructure.zeppelin.ZeppelinJobOperator;
import jrx.data.hub.util.CommonUtil;
import jrx.data.hub.util.DataResponse;
import jrx.data.hub.util.DataTransferUtils;
import jrx.data.hub.util.SpringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 数据源信息 服务实现类
 * </p>
 *
 * @author zhangch
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class MetaDataSourceInfoServiceImpl extends ServiceImpl<MetaDataSourceInfoMapper, MetaDataSourceInfo> implements IMetaDataSourceInfoService {

    @Autowired
    private MetaDataSourceInfoMapper metaDataSourceInfoMapper;

    @Autowired
    private IMetaDataObjectInfoService dataObjectInfoService;

    @Autowired
    ZeppelinServiceImpl zeppelinServiceImpl;

    @Override
    public MetaDataSourceInfo create(MetaDataSourceInfo metaDataSourceInfo) {
        QueryWrapper<MetaDataSourceInfo> queryWrapper = new QueryWrapper<MetaDataSourceInfo>();
        queryWrapper.eq("source_code", metaDataSourceInfo.getSourceCode()).or().eq("source_name", metaDataSourceInfo.getSourceName());
        MetaDataSourceInfo mInfo = metaDataSourceInfoMapper.selectOne(queryWrapper);
        if (mInfo != null) {
            throw new DataSourceException("数据源code或名称已存在");
        }
        ModelUpdateAssistant.setCreate(metaDataSourceInfo);
        metaDataSourceInfo.setSourceCode(CommonUtil.getStringRandom(20));
        metaDataSourceInfo.setDataSourceId(null);
        int count = metaDataSourceInfoMapper.insert(metaDataSourceInfo);
        if (count <= 0) {
            throw new DataSourceException("数据源新建失败");
        }
        try {
            DataResponse dataResponse = zeppelinServiceImpl.listInterpreter();
            JSONArray jsonArray = JSONObject.parseArray(JSON.toJSONString(dataResponse.getData()));
            zeppelinServiceImpl.newInterpreter(metaDataSourceInfo,jsonArray.getJSONObject(0));
        } catch (Exception e) {
            log.error("create Interpreter error",e);
            throw new DataSourceException("数据源新建失败,"+e.getMessage());
        }
        return metaDataSourceInfo;
    }

    @Override
    public MetaDataSourceInfo update(MetaDataSourceInfo metaDataSourceInfo) {
        QueryWrapper<MetaDataSourceInfo> queryWrapper = new QueryWrapper<MetaDataSourceInfo>();
//        queryWrapper.eq("source_code", metaDataSourceInfo.getSourceCode());
        queryWrapper.eq("source_code", metaDataSourceInfo.getSourceCode()).or().eq("data_source_id", metaDataSourceInfo.getDataSourceId());
        MetaDataSourceInfo mInfo = metaDataSourceInfoMapper.selectOne(queryWrapper);
        if (mInfo == null) {
            throw new DataSourceException("数据源不存在");
        }
        DataTransferUtils.modelToVo(metaDataSourceInfo, mInfo);
        ModelUpdateAssistant.setUpdate(mInfo);
        int count = metaDataSourceInfoMapper.updateById(mInfo);
        if (count <= 0) {
            throw new DataSourceException("数据源修改失败");
        }
        return metaDataSourceInfo;
    }

    @Override
    public List<MetaDataSourceInfoVo> listAll() {
        QueryWrapper<MetaDataSourceInfo> queryWrapper = new QueryWrapper<MetaDataSourceInfo>();

        List<MetaDataSourceInfo> metaDataSourceInfos = metaDataSourceInfoMapper.selectList(queryWrapper);
        List<MetaDataSourceInfoVo> voList = DataTransferUtils.modelListToVoList(metaDataSourceInfos, MetaDataSourceInfoVo.class);

        for (MetaDataSourceInfoVo dataSourceInfoVo : voList) {
            //获取上线数据集列表
            List<MetaDataObjectVo> onlineDataObjectVoList = dataObjectInfoService.getOnlineDataObjectListByDataSourceId(dataSourceInfoVo.getDataSourceId());
            dataSourceInfoVo.setOnlineMetaDataObjectVoList(onlineDataObjectVoList);
        }

        return voList;
    }
}