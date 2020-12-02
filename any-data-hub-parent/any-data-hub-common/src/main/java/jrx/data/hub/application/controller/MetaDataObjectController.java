package jrx.data.hub.application.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import jrx.data.hub.domain.aop.annotation.ApplicationLog;
import jrx.data.hub.domain.common.ErrorMessageAssistant;
import jrx.data.hub.domain.enums.VersionState;
import jrx.data.hub.domain.service.impl.MetaDataObjectInfoServiceImpl;
import jrx.data.hub.domain.service.impl.MetaDataObjectServiceImpl;
import jrx.data.hub.domain.service.impl.MetaDataSourceInfoServiceImpl;
import jrx.data.hub.domain.service.impl.ZeppelinServiceImpl;
import jrx.data.hub.domain.vo.MetaDataObjectVo;
import jrx.data.hub.infrastructure.entity.MetaDataObject;
import jrx.data.hub.infrastructure.entity.MetaDataObjectInfo;
import jrx.data.hub.infrastructure.entity.MetaDataSourceInfo;
import jrx.data.hub.infrastructure.entity.MetaJobInfo;
import jrx.data.hub.util.DataResponse;
import jrx.data.hub.util.DataTransferUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

/**
 * <p>
 * 描述 表版本信息
 * </p>
 *
 * @author zhangch
 */

@RestController
@RequestMapping("/hub/meta-data-object")
@Api(description = "表版本信息接口")
@Slf4j
public class MetaDataObjectController {
    @Autowired
    private MetaDataObjectServiceImpl metaDataObjectServiceImpl;

    @Autowired
    ZeppelinServiceImpl zeppelinServiceImpl;

    @Autowired
    private MetaDataObjectInfoServiceImpl metaDataObjectInfoServiceImpl;

    @Autowired
    private MetaDataSourceInfoServiceImpl metaDataSourceInfoServiceImpl;

    @GetMapping("/list")
    @ApiOperation(value = "表版本信息列表查询")
    @ApplicationLog(description = "表版本信息列表查询")
    public DataResponse list(@ApiParam(value = "当前页数") @RequestParam(defaultValue = "1") int pageCount,
                             @ApiParam(value = "每页的记录条数") @RequestParam(defaultValue = "10") int pageSize,
                             @ApiParam(value = "表详情ID") @RequestParam(required = true) String resourceId) {
        DataResponse dataResponse = DataResponse.of();
        QueryWrapper<MetaDataObject> param = new QueryWrapper<>();
        param.eq("resource_id", resourceId);
        Page<MetaDataObject> page = new Page<>(pageCount, pageSize);
        IPage<MetaDataObject> metaDataObjectIPage = metaDataObjectServiceImpl.page(page, param);
        dataResponse.setData(DataTransferUtils.pageModelToVo(metaDataObjectIPage, MetaDataObjectVo.class));
        return dataResponse;
    }

    @PostMapping("/save")
    @ApiOperation(value = "新建表版本信息")
    @ApplicationLog(description = "新建表版本信息")
    public DataResponse save(@ApiParam(required = true, value = "表版本信息model") @Valid @RequestBody MetaDataObjectVo metaDataObjectVo, BindingResult bindingResult) {
        DataResponse dataResponse = DataResponse.of();
        if (bindingResult.hasErrors()) {
            return ErrorMessageAssistant.getDataResponse(bindingResult);
        }
        MetaDataObject metaDataObject = metaDataObjectServiceImpl.create(DataTransferUtils.modelToVo(metaDataObjectVo, new MetaDataObject()));
        dataResponse.setData(DataTransferUtils.modelToVo(metaDataObject, new MetaDataObjectVo()));
        return dataResponse;
    }

    @PutMapping("/update")
    @ApiOperation(value = "修改表版本信息")
    @ApplicationLog(description = "修改表版本信息")
    public DataResponse update(@ApiParam(required = true, value = "表版本信息model") @RequestBody MetaDataObjectVo metaDataObjectVo) {
        DataResponse dataResponse = DataResponse.of();
        MetaDataObject metaDataObject = metaDataObjectServiceImpl.update(DataTransferUtils.modelToVo(metaDataObjectVo, new MetaDataObject()));
        dataResponse.setData(DataTransferUtils.modelToVo(metaDataObject, new MetaDataObjectVo()));
        return dataResponse;
    }

    @GetMapping("/{resourceVersionId}")
    @ApiOperation(value = "查看表版本信息")
    public DataResponse view(@ApiParam(required = true, value = "表版本信息id") @PathVariable String resourceVersionId) {
        DataResponse dataResponse = DataResponse.of();
        MetaDataObject metaDataObject = metaDataObjectServiceImpl.getById(resourceVersionId);
        dataResponse.setData(DataTransferUtils.modelToVo(metaDataObject, new MetaDataObjectVo()));
        return dataResponse;
    }

    @PutMapping("/{resourceVersionId}/{versionState}")
    @ApiOperation(value = "修改表版本状态")
    @ApplicationLog(description = "修改表版本状态")
    public DataResponse versionState(@ApiParam(required = true, value = "表版本ID") @PathVariable String resourceVersionId, @ApiParam(required = true, value = "表版本状态") @PathVariable VersionState versionState) {
        DataResponse dataResponse = DataResponse.of();
        MetaDataObject metaDataObject = metaDataObjectServiceImpl.updateVersionState(resourceVersionId, versionState);
        dataResponse.setData(DataTransferUtils.modelToVo(metaDataObject, new MetaDataObjectVo()));
        return dataResponse;
    }

    @PutMapping("/{resourceVersionId}")
    @ApiOperation(value = "另存新版本")
    @ApplicationLog(description = "另存新版本")
    public DataResponse saveNew(@ApiParam(required = true, value = "表版本ID") @PathVariable String resourceVersionId) {
        DataResponse dataResponse = DataResponse.of();
        MetaDataObject metaDataObject = metaDataObjectServiceImpl.getById(resourceVersionId);
        metaDataObject = metaDataObjectServiceImpl.createNewVersion(metaDataObject);
        dataResponse.setData(DataTransferUtils.modelToVo(metaDataObject, new MetaDataObjectVo()));
        return dataResponse;
    }

    @PostMapping("/executeSql")
    @ApiOperation(value = "执行sql")
    @ApplicationLog(description = "执行sql")
    public DataResponse executeSql(@ApiParam(required = true, value = "执行sql") @RequestBody MetaDataObjectVo metaDataObjectVo) {
        MetaDataObjectInfo metaDataObjectInfo = metaDataObjectInfoServiceImpl.getOne(Wrappers.<MetaDataObjectInfo>lambdaQuery().eq(MetaDataObjectInfo::getResourceId, metaDataObjectVo.getResourceId()));
        MetaDataSourceInfo metaDataSourceInfo = metaDataSourceInfoServiceImpl.getOne(Wrappers.<MetaDataSourceInfo>lambdaQuery().eq(MetaDataSourceInfo::getDataSourceId, metaDataObjectInfo.getDataSourceId()));
        DataResponse dataResponse =  zeppelinServiceImpl.execTmpJob(metaDataSourceInfo.getSourceName(),"%"+metaDataSourceInfo.getSourceName()+"\n" +
                "\n" +
                metaDataObjectVo.getDdlSql());
        return dataResponse;
    }
}