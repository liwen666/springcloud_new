package jrx.data.hub.application.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import jrx.data.hub.domain.aop.annotation.ApplicationLog;
import jrx.data.hub.domain.model.job.JobTestData;
import jrx.data.hub.domain.enums.VersionState;
import jrx.data.hub.domain.service.IMetaJobInfoService;
import jrx.data.hub.domain.service.IMetaJobObjectService;
import jrx.data.hub.domain.vo.MetaJobInfoVo;
import jrx.data.hub.domain.vo.MetaJobObjectVo;
import jrx.data.hub.infrastructure.entity.MetaJobObject;
import jrx.data.hub.infrastructure.dto.JobExecuteResult;
import jrx.data.hub.util.DataResponse;
import jrx.data.hub.util.DataTransferUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * <p>
 * 描述 job 版本
 * </p>
 *
 * @author lw
 * @since 2020-11-05
 */
@Slf4j
@RestController
@RequestMapping("/hub/meta-job-object")
public class MetaJobObjectController {

    @Autowired
    private IMetaJobInfoService jobInfoService;

    @Autowired
    private IMetaJobObjectService jobObjectService;

    @GetMapping("/list")
    @ApiOperation(value = "表版本信息列表查询")
    @ApplicationLog(description = "表版本信息列表查询")
    public DataResponse<IPage<MetaJobObjectVo>> list(@ApiParam(value = "当前页数") @RequestParam(defaultValue = "1") int pageCount,
                             @ApiParam(value = "每页的记录条数") @RequestParam(defaultValue = "10") int pageSize,
                             @ApiParam(value = "表详情ID") @RequestParam(required = true) String resourceId) {

        DataResponse<IPage<MetaJobObjectVo>> dataResponse = DataResponse.of();
        QueryWrapper<MetaJobObject> param = new QueryWrapper<>();
        param.eq("resource_id", resourceId);
        Page<MetaJobObject> page = new Page<>(pageCount, pageSize);
        IPage<MetaJobObject> metaJobObjectPage = jobObjectService.page(page, param);
        dataResponse.setData(DataTransferUtils.pageModelToVo(metaJobObjectPage, MetaJobObjectVo.class));
        if (log.isDebugEnabled()) {
            log.debug("Job版本信息列表查询.");
        }
        return dataResponse;
    }


    @PostMapping("/save")
    @ApiOperation(value = "新建Job对象")
    @ApplicationLog(description = "新建Job对象")
    public DataResponse save(@ApiParam(required = true, value = "表详情model") @Valid @RequestBody MetaJobObjectVo metaJobObjectVo, BindingResult bindingResult) {
        DataResponse dataResponse = DataResponse.of();

        MetaJobObject metaJobObject = jobObjectService.create(DataTransferUtils.modelToVo(metaJobObjectVo, new MetaJobObject()));

        DataTransferUtils.modelToVo(metaJobObject, metaJobObjectVo);
        if (log.isDebugEnabled()) {
            log.debug("新建Job对象. {}", JSONObject.toJSONString(metaJobObjectVo));
        }
        return dataResponse;
    }

    @PutMapping("/update")
    @ApiOperation(value = "编辑Job版本")
    @ApplicationLog(description = "编辑Job版本")
    public DataResponse update(@ApiParam(required = true, value = "Job详情model") @RequestBody MetaJobObjectVo metaJobObjectVo) {
        DataResponse dataResponse = DataResponse.of();

        MetaJobObject metaJobObject = jobObjectService.update(DataTransferUtils.modelToVo(metaJobObjectVo, new MetaJobObject()));
        dataResponse.setData(metaJobObject);
        if (log.isDebugEnabled()) {
            log.debug("编辑Job版本. {}", JSONObject.toJSONString(metaJobObject));
        }
        return dataResponse;
    }

    @PutMapping("update/{resourceVersionId}")
    @ApiOperation(value = "另存Job新版本")
    @ApplicationLog(description = "另存Job新版本")
    public DataResponse<MetaJobObjectVo> saveNew(@ApiParam(required = true, value = "表版本ID") @PathVariable String resourceVersionId) {
        DataResponse dataResponse = DataResponse.of();
        MetaJobObjectVo metaJobObjectVo = jobObjectService.saveNew(resourceVersionId);
        dataResponse.setData(DataTransferUtils.modelToVo(metaJobObjectVo, new MetaJobObjectVo()));

        if (log.isDebugEnabled()) {
            log.debug("另存Job新版本.");
        }
        return dataResponse;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "查询Job对象")
    @ApplicationLog(description = "查询Job对象")
    public DataResponse view(@ApiParam(required = true, value = "Job对象id") @PathVariable String id) {
        DataResponse dataResponse = DataResponse.of();

        MetaJobObject metaJobObject = jobObjectService.view(id);
        dataResponse.setData(metaJobObject);
        if (log.isDebugEnabled()) {
            log.debug("查看Job对象. {}", JSONObject.toJSONString(metaJobObject));
        }
        return dataResponse;
    }

    @PostMapping("/execute/{resourceId}")
    @ApiOperation(value = "Job对象执行")
    @ApplicationLog(description = "Job对象执行")
    public DataResponse<JobExecuteResult> execute(
            @ApiParam(required = true, value = "resourceId") @PathVariable String resourceId,
            @ApiParam(required = true, value = "执行的SQL语句") String sqlContent
    ) {
        DataResponse dataResponse = DataResponse.of();

        JobExecuteResult jobExecuteResult = jobObjectService.execute(resourceId, sqlContent);
        dataResponse.setData(jobExecuteResult);
        if (log.isDebugEnabled()) {
            log.debug("查看Job对象. {}", JSONObject.toJSONString(jobExecuteResult));
        }
        return dataResponse;
    }

    @PostMapping("/data-test")
    @ApiOperation(value = "Job数据测试, 根据前端传过来原表和目标表信息生成临时数据.")
    @ApplicationLog(description = "Job数据测试")
    public DataResponse dataTest(@ApiParam(required = true, value = "执行的SQL语句") MetaJobInfoVo metaJobInfoVo) {
        DataResponse dataResponse = DataResponse.of();

        JobTestData info = DataTransferUtils.modelToVo(metaJobInfoVo, new JobTestData());

        jobObjectService.dataTest(info);
        if (log.isDebugEnabled()) {
            log.debug("Job数据测试.");
        }
        return dataResponse;
    }

    @PutMapping("/updateState/{jobVersionId}/{versionState}")
    @ApiOperation(value = "修改实体状态")
    @ApplicationLog(description = "修改实体状态")
    public DataResponse updateVersionState(
            @ApiParam(value = "对象id")@PathVariable String jobVersionId,
            @ApiParam(value = "修改的状态")@PathVariable VersionState versionState){
        DataResponse dataResponse = DataResponse.of();

        jobObjectService.updateVersionState(jobVersionId, versionState);
        if (log.isDebugEnabled()) {
            log.debug("修改Job版本【{}】状态【{}】成功. ", jobVersionId, versionState);
        }
        return dataResponse;
    }

}
