package jrx.data.hub.application.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import jrx.data.hub.domain.aop.annotation.ApplicationLog;
import jrx.data.hub.domain.common.ErrorMessageAssistant;
import jrx.data.hub.domain.enums.ResourceType;
import jrx.data.hub.domain.exception.DataSourceException;
import jrx.data.hub.domain.service.impl.MetaDataObjectInfoServiceImpl;
import jrx.data.hub.domain.service.impl.MetaDataObjectServiceImpl;
import jrx.data.hub.domain.vo.MetaDataObjectInfoVo;
import jrx.data.hub.domain.vo.MetaDataObjectVo;
import jrx.data.hub.infrastructure.entity.MetaDataObject;
import jrx.data.hub.infrastructure.entity.MetaDataObjectInfo;
import jrx.data.hub.util.DataResponse;
import jrx.data.hub.util.DataTransferUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 描述 表信息
 * </p>
 *
 * @author zhangch
 */

@RestController
@RequestMapping("/hub/meta-data-object-info")
@Api(description = "表信息接口")
@Slf4j
public class MetaDataObjectInfoController {

    @Autowired
    private MetaDataObjectServiceImpl metaDataObjectServiceImpl;

    @Autowired
    private MetaDataObjectInfoServiceImpl metaDataObjectInfoServiceImpl;

    @GetMapping("/list")
    @ApiOperation(value = "表信息列表查询")
    @ApplicationLog(description = "表信息列表查询")
    public DataResponse list(@ApiParam(value = "当前页数") @RequestParam(defaultValue = "1") int pageCount,
                             @ApiParam(value = "每页的记录条数") @RequestParam(defaultValue = "10") int pageSize,
                             @ApiParam(value = "表类型") @RequestParam(required = false) ResourceType resourceType,
                             @ApiParam(value = "表所属数据源ID", required = true) @RequestParam String dataSourceId,
                             @ApiParam(value = "表名称") @RequestParam(required = false) String name) {
        DataResponse dataResponse = DataResponse.of();
        QueryWrapper<MetaDataObjectInfo> param = new QueryWrapper<>();
        if (resourceType != null) {
            param.eq("resource_type", resourceType);
        }
        if (StringUtils.isNotEmpty(dataSourceId)) {
            param.eq("data_source_id", dataSourceId);
        }
        if (StringUtils.isNotEmpty(name)) {
            param.like("name", name);
        }
        Page<MetaDataObjectInfo> page = new Page<>(pageCount, pageSize);
        IPage<MetaDataObjectInfo> metaCategoryIPage = metaDataObjectInfoServiceImpl.page(page, param);
        IPage<MetaDataObjectInfoVo> pageVo = DataTransferUtils.pageModelToVo(metaCategoryIPage, MetaDataObjectInfoVo.class);
        for (MetaDataObjectInfoVo metaDataObjectInfoVo : pageVo.getRecords()) {
            setLastVersion(metaDataObjectInfoVo);
        }
        dataResponse.setData(pageVo);
        return dataResponse;
    }

    @PostMapping("/save")
    @ApiOperation(value = "新建目标表信息")
    @ApplicationLog(description = "新建目标表信息")
    public DataResponse save(@ApiParam(required = true, value = "表信息model") @Valid @RequestBody MetaDataObjectInfoVo metaDataObjectInfoVo, BindingResult bindingResult) {
        DataResponse dataResponse = DataResponse.of();
        if (bindingResult.hasErrors()) {
            return ErrorMessageAssistant.getDataResponse(bindingResult);
        }
        MetaDataObjectInfo metaDataObjectInfo = metaDataObjectInfoServiceImpl.createTargetTable(DataTransferUtils.modelToVo(metaDataObjectInfoVo, new MetaDataObjectInfo()));
        dataResponse.setData(DataTransferUtils.modelToVo(metaDataObjectInfo, new MetaDataObjectInfoVo()));
        return dataResponse;
    }

    @PutMapping("/update")
    @ApiOperation(value = "修改表信息", notes = "包括编辑目标表信息、编辑采集表sql")
    @ApplicationLog(description = "修改表信息")
    public DataResponse update(@ApiParam(required = true, value = "表信息model") @RequestBody MetaDataObjectInfoVo metaDataObjectInfoVo) {
        DataResponse dataResponse = DataResponse.of();
        MetaDataObjectInfo metaDataObjectInfo = metaDataObjectInfoServiceImpl.update(DataTransferUtils.modelToVo(metaDataObjectInfoVo, new MetaDataObjectInfo()));
        dataResponse.setData(DataTransferUtils.modelToVo(metaDataObjectInfo, new MetaDataObjectInfoVo()));
        return dataResponse;
    }

    @GetMapping("/{resourceId}")
    @ApiOperation(value = "查看表信息", notes = "表信息包含表版本信息")
    public DataResponse view(@ApiParam(required = true, value = "表信息id") @PathVariable String resourceId) {
        DataResponse dataResponse = DataResponse.of();
        MetaDataObjectInfo metaDataObjectInfo = metaDataObjectInfoServiceImpl.getById(resourceId);
        if (metaDataObjectInfo == null) {
            throw new DataSourceException("resourceId:" + resourceId + "不存在");
        }
        MetaDataObjectInfoVo metaDataObjectInfoVo = new MetaDataObjectInfoVo();
        DataTransferUtils.modelToVo(metaDataObjectInfo, metaDataObjectInfoVo);
        setLastVersion(metaDataObjectInfoVo);
        dataResponse.setData(metaDataObjectInfoVo);
        return dataResponse;
    }

    @DeleteMapping("/delete")
    @ApiOperation(value = "删除表信息")
    @ApplicationLog(description = "删除表信息")
    public DataResponse delete(@ApiParam(required = true, value = "ID") @RequestParam String resourceId) {
        DataResponse dataResponse = DataResponse.of();
        metaDataObjectInfoServiceImpl.delete(resourceId);
        return dataResponse;
    }

    /**
     * 设置表信息的版本信息
     *
     * @param metaDataObjectInfoVo
     */
    public void setLastVersion(MetaDataObjectInfoVo metaDataObjectInfoVo) {
        QueryWrapper<MetaDataObject> listParam = new QueryWrapper<>();
        listParam.eq("resource_id", metaDataObjectInfoVo.getResourceId());
//        listParam.eq("version_state", VersionState.ONLINE);
        listParam.orderByDesc("create_time");
        List<MetaDataObject> metaDataObjects = metaDataObjectServiceImpl.list(listParam);
        metaDataObjectInfoVo.setMetaDataObjectVos(DataTransferUtils.modelListToVoList(metaDataObjects, MetaDataObjectVo.class));
        if (metaDataObjects.size() > 0) {
            metaDataObjectInfoVo.setLastResourceVersionId(metaDataObjects.get(0).getResourceVersionId());
            metaDataObjectInfoVo.setLastVersionCode(metaDataObjects.get(0).getVersionCode());
        } else {
            throw new DataSourceException("获取最新表版本失败");
        }
    }
}