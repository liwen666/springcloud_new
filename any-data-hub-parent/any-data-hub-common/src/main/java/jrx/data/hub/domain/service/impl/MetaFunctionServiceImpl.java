package jrx.data.hub.domain.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jrx.data.hub.domain.common.ModelUpdateAssistant;
import jrx.data.hub.domain.complier.CodeExecutorBuilder;
import jrx.data.hub.domain.complier.ICodeExecutor;
import jrx.data.hub.domain.complier.ScriptLanguage;
import jrx.data.hub.domain.enums.DataHubModule;
import jrx.data.hub.domain.enums.VersionState;
import jrx.data.hub.domain.exception.DataException;
import jrx.data.hub.domain.service.IMetaFunctionInfoService;
import jrx.data.hub.domain.service.IMetaFunctionService;
import jrx.data.hub.domain.service.IZeppelinService;
import jrx.data.hub.domain.vo.MetaFunctionVo;
import jrx.data.hub.infrastructure.dao.MetaFunctionMapper;
import jrx.data.hub.infrastructure.entity.MetaFunction;
import jrx.data.hub.util.DataResponse;
import jrx.data.hub.util.DataTransferUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * <p>
 * 函数版本 服务实现类
 * </p>
 *
 * @author lw
 * @since 2020-11-05
 */
@Service
@Slf4j
@Transactional
public class MetaFunctionServiceImpl extends ServiceImpl<MetaFunctionMapper, MetaFunction> implements IMetaFunctionService {


    @Autowired
    private IZeppelinService zeppelinService;

    @Autowired
    public IMetaFunctionInfoService metaFunctionInfoService;

    @Override
    public MetaFunction update(MetaFunction function) {
        MetaFunction metaFunction = getById(function.getFunctionId());
        if (metaFunction != null && metaFunction.getVersionState() == VersionState.ONLINE) {
            throw new DataException("该函数的版本状态上线，无法修改");
        }
        ModelUpdateAssistant.setUpdate(function);
        boolean flag = updateById(function);
        if (!flag) {
            throw new DataException("函数信息更新失败");
        }
        return function;
    }

    @Override
    public Boolean updateState(String functionId, VersionState versionState) {
        MetaFunction metaFunction = getById(functionId);
        metaFunction.setVersionState(versionState);
        boolean flag = updateById(metaFunction);
        if (!flag) {
            throw new DataException("函数信息状态变更失败");
        }
        return flag;
    }

    @Override
    public Boolean create(String functionId) {
        MetaFunction metaFunction = getById(functionId);
        MetaFunction newFunction = new MetaFunction();
        BeanUtils.copyProperties(metaFunction, newFunction);
        newFunction.setFunctionId(null);
        newFunction.setVersionCode(metaFunction.getVersionCode() + 1);
        ModelUpdateAssistant.setCreate(newFunction);
        newFunction.setVersionState(VersionState.INACTIVE);
        return save(newFunction);
    }

    @Override
    public Object test(MetaFunctionVo metaFunctionVo) {
        try {
            Class[] paramsType = null;
            String[] paramsCode = null;
            if (StringUtils.isNoneBlank(metaFunctionVo.getParamFields())) {
                if (StringUtils.isBlank(metaFunctionVo.getParamVals())) {
                    throw new DataException("参数值不能为空");
                }
                paramsCode = metaFunctionVo.getParamFields().split(",");
                String[] paramTypeArr = metaFunctionVo.getParamFieldTypes().split(",");
                if (metaFunctionVo.getParamVals().split(",").length != paramsCode.length) {
                    throw new DataException("参数定义与参数值不对应");
                }
                paramsType = new Class[paramTypeArr.length];
                for (int i = 0; i < paramTypeArr.length; i++) {
                    paramsType[i] = Class.forName(paramTypeArr[i]);
                }
            }
            String scripts = metaFunctionVo.getScripts();
            if (StringUtils.isEmpty(scripts)) {
                throw new DataException("函数脚本不能为空");
            }
            if (ScriptLanguage.SCALA.equals(metaFunctionVo.getLanguage())) {
                //valid functionName
                zeppelinService.validFunction(metaFunctionVo.getFunctionName(), scripts);
                DataResponse<String> res = zeppelinService.testFunction(DataHubModule.FUNCTION.name(), metaFunctionVo.getScripts());
                return res.getData();
            }
            ICodeExecutor build = null;
            if (paramsCode != null && paramsType != null && StringUtils.isNoneBlank(metaFunctionVo.getParamVals())) {
                build = CodeExecutorBuilder.build(
                        metaFunctionVo.getLanguage(),
                        scripts,
                        paramsCode,
                        paramsType);
                return build.execute(metaFunctionVo.getParamVals().split(","));
            } else {
                build = CodeExecutorBuilder.build(
                        metaFunctionVo.getLanguage(),
                        scripts);
                return build.execute(null);
            }
        } catch (Exception e) {
            log.error("出现异常{}", e.getMessage(), e);
            throw new DataException(e.getMessage());
        }
    }

    @Override
    public Boolean publish(String resourceId) {
        Boolean publish = zeppelinService.publish(DataHubModule.FUNCTION.name(), resourceId);
        return publish;
    }

    @Override
    public Optional<MetaFunctionVo> getOnlineFunctionByInfoId(String functionInfoId) {
        QueryWrapper<MetaFunction> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("resource_id", functionInfoId);
        queryWrapper.eq("version_state", "ONLINE");
        MetaFunction onlineVersion = super.getOne(queryWrapper);
        if (onlineVersion != null) {
            MetaFunctionVo metaFunctionVo = DataTransferUtils.modelToVo(onlineVersion, new MetaFunctionVo());
            return Optional.of(metaFunctionVo);
        }
        return Optional.empty();
    }
}
