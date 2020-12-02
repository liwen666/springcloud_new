package jrx.data.hub.application.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import jrx.data.hub.domain.aop.annotation.ApplicationLog;
import jrx.data.hub.domain.enums.VersionState;
import jrx.data.hub.domain.service.IMetaJobInfoService;
import jrx.data.hub.domain.service.IMetaJobObjectService;
import jrx.data.hub.domain.vo.MetaJobInfoVo;
import jrx.data.hub.domain.vo.MetaJobObjectVo;
import jrx.data.hub.infrastructure.entity.MetaJobInfo;
import jrx.data.hub.util.DataResponse;
import jrx.data.hub.util.DataTransferUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

/**
 * <p>
 * 描述 job 信息
 * </p>
 *
 * @author lw
 * @since 2020-11-05
 */
@Slf4j
@RestController
@RequestMapping("/hub/meta-job-info")
public class MetaJobInfoController {

    @Autowired
    private IMetaJobInfoService jobInfoService;

    @Autowired
    private IMetaJobObjectService jobObjectService;

    @GetMapping("/list")
    @ApiOperation(value = "Job详情列表查询")
    @ApplicationLog(description = "表详情列表查询")
    public DataResponse<IPage<MetaJobInfoVo>> list(@ApiParam(value = "当前页数") @RequestParam(defaultValue = "1") int pageCount,
                                                   @ApiParam(value = "每页的记录条数") @RequestParam(defaultValue = "10") int pageSize,
                                                   @ApiParam(value = "Job名称") @RequestParam(required = false) String name) {
        DataResponse<IPage<MetaJobInfoVo>> dataResponse = DataResponse.of();
        QueryWrapper<MetaJobInfo> param = new QueryWrapper<>();

        Page<MetaJobInfo> page = new Page<>(pageCount, pageSize);
        if (StringUtils.isNotEmpty(name)) {
            param.like("name", name);
        }
        IPage<MetaJobInfo> jobInfoIPage = jobInfoService.page(page, param);
        IPage<MetaJobInfoVo> voPage = DataTransferUtils.pageModelToVo(jobInfoIPage, MetaJobInfoVo.class);
        for (MetaJobInfoVo record : voPage.getRecords()) {
            Optional<MetaJobObjectVo> latestVersion = jobObjectService.getLatestVersionByInfoId(record.getResourceId());
            if (latestVersion.isPresent()) {
                record.setLatestVersionId(latestVersion.get().getJobObjectId());
            }
        }

        dataResponse.setData(voPage);

        if (log.isDebugEnabled()) {
            log.debug("Job详情列表查询. Size: {}", JSONObject.toJSONString(jobInfoIPage.getRecords()));
        }
        return dataResponse;
    }

    @PostMapping("/save")
    @ApiOperation(value = "新建Job详情")
    @ApplicationLog(description = "新建表详情")
    public DataResponse save(@ApiParam(required = true, value = "表详情model") @Valid @RequestBody MetaJobInfoVo metaJobInfoVo) {
        DataResponse dataResponse = DataResponse.of();

        MetaJobInfo metaJobInfo = jobInfoService.create(DataTransferUtils.modelToVo(metaJobInfoVo, new MetaJobInfo()));
        metaJobInfoVo = DataTransferUtils.modelToVo(metaJobInfo, new MetaJobInfoVo());
        dataResponse.setData(metaJobInfoVo);
        if (log.isDebugEnabled()) {
            log.debug("新建Job详情. {}", JSONObject.toJSONString(metaJobInfoVo));
        }
        return dataResponse;
    }

    @PostMapping("/update")
    @ApiOperation(value = "修改Job详情")
    @ApplicationLog(description = "修改Job详情")
    public DataResponse update(@ApiParam(required = true, value = "Job详情model") @RequestBody MetaJobInfoVo metaJobInfoVo) {
        DataResponse dataResponse = DataResponse.of();

        MetaJobInfo info = DataTransferUtils.modelToVo(metaJobInfoVo, new MetaJobInfo());
        info = jobInfoService.update(info);
        dataResponse.setData(info);
        if (log.isDebugEnabled()) {
            log.debug("Job详情列表查询. {}", JSONObject.toJSONString(info));
        }
        return dataResponse;
    }

    @GetMapping("/{resourceId}")
    @ApiOperation(value = "查看Job详情")
    public DataResponse<MetaJobInfoVo> view(@ApiParam(required = true, value = "表详情id") @PathVariable String resourceId) {
        DataResponse dataResponse = DataResponse.of();

        MetaJobInfoVo metaJobInfoVo = jobInfoService.view(resourceId);
        if (log.isDebugEnabled()) {
            log.debug("查看Job详情. {}", JSONObject.toJSONString(metaJobInfoVo));
        }
        dataResponse.setData(metaJobInfoVo);
        return dataResponse;
    }

    @ApiOperation(value = "修改Job详情状态")
    @PutMapping("/updateState/{resourceId}/{versionState}")
    public DataResponse updateVersionState(
            @ApiParam(value = "资源id") @PathVariable String resourceId,
            @ApiParam(value = "修改的状态") @PathVariable VersionState infoState) {
        DataResponse dataResponse = DataResponse.of();

        jobInfoService.updateVersionState(resourceId, infoState);
        if (log.isDebugEnabled()) {
            log.debug("修改Job信息【{}】状态【{}】成功. ", resourceId, infoState);
        }
        return dataResponse;
    }

    @ApiOperation(value = "修改Job详情状态")
    @DeleteMapping("/{resourceId}")
    public DataResponse remove(@ApiParam(value = "资源id") @PathVariable String resourceId) {
        DataResponse dataResponse = DataResponse.of();

        jobInfoService.removeJobInfo(resourceId);
        if (log.isDebugEnabled()) {
            log.debug("修改Job信息【{}】删除成功. ", resourceId);
        }
        return dataResponse;
    }
}
