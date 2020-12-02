package jrx.data.hub.application.controller;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import jrx.data.hub.domain.aop.annotation.ApplicationLog;
import jrx.data.hub.domain.model.work.WorkJobPublishResult;
import jrx.data.hub.domain.service.IMetaWorkInfoService;
import jrx.data.hub.domain.vo.MetaJobObjectVo;
import jrx.data.hub.domain.vo.MetaWorkInfoVo;
import jrx.data.hub.util.DataResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Work信息前端交互接口
 *
 * @author lw
 * @since 2020-11-05
 */

@Slf4j
@RestController
@RequestMapping("/hub/meta-work-info")
public class MetaWorkInfoController {

    @Autowired
    private IMetaWorkInfoService metaWorkInfoService;

    @PostMapping("/save")
    @ApiOperation(value = "新建Work")
    @ApplicationLog(description = "新建Work")
    public DataResponse<MetaWorkInfoVo> save(@ApiParam(required = true, value = "work")
                                                 @RequestBody MetaWorkInfoVo metaWorkInfoVo) {
        DataResponse<MetaWorkInfoVo> dataResponse = DataResponse.of();

        metaWorkInfoVo = metaWorkInfoService.create(metaWorkInfoVo);

        if (log.isDebugEnabled()) {
            log.debug("新建Job对象. {}", JSONObject.toJSONString(metaWorkInfoVo));
        }
        return dataResponse;
    }

    @PutMapping("/update")
    @ApiOperation(value = "更新Work")
    @ApplicationLog(description = "更新Work")
    public DataResponse<MetaWorkInfoVo> update(@ApiParam(required = true, value = "metaWorkInfoVo")
                                                   @RequestBody MetaWorkInfoVo metaWorkInfoVo) {
        DataResponse<MetaWorkInfoVo> dataResponse = DataResponse.of();

        metaWorkInfoVo = metaWorkInfoService.update(metaWorkInfoVo);

        if (log.isDebugEnabled()) {
            log.debug("新建Job对象. {}", JSONObject.toJSONString(metaWorkInfoVo));
        }
        return dataResponse;
    }

    @PutMapping("/remove")
    @ApiOperation(value = "移除Work")
    @ApplicationLog(description = "移除Work")
    public DataResponse<MetaWorkInfoVo> remove(@ApiParam(required = true, value = "metaWorkInfoVo")
                                                   @RequestBody MetaWorkInfoVo metaWorkInfoVo) {
        DataResponse<MetaWorkInfoVo> dataResponse = DataResponse.of();

        metaWorkInfoService.removeWork(metaWorkInfoVo);

        if (log.isDebugEnabled()) {
            log.debug("移除Work信息. {}", JSONObject.toJSONString(metaWorkInfoVo));
        }
        return dataResponse;
    }

    @PostMapping("/add-job/{workId}")
    @ApiOperation(value = "向work中添加Job")
    @ApplicationLog(description = "向work中添加Job")
    public DataResponse addJob(@ApiParam(required = true, value = "JobList") @RequestBody List<MetaJobObjectVo> metaJobObjectVoList,
                               @ApiParam(required = true, value = "workId") @PathVariable Long workId
    ) {
        DataResponse<MetaWorkInfoVo> dataResponse = DataResponse.of();
        metaWorkInfoService.addJob(workId, metaJobObjectVoList);

        if (log.isDebugEnabled()) {
            log.debug("向work【{}】中添加Job. {}", workId, JSONObject.toJSONString(metaJobObjectVoList));
        }
        return dataResponse;
    }

    @PostMapping("/job-delete/{workId}")
    @ApiOperation(value = "向work中移除Job")
    @ApplicationLog(description = "向work中移除Job")
    public DataResponse removeJob(@ApiParam(required = true, value = "JobList") @RequestBody List<MetaJobObjectVo> metaJobObjectVoList,
                               @ApiParam(required = true, value = "workId") @PathVariable Long workId
    ) {
        DataResponse<MetaWorkInfoVo> dataResponse = DataResponse.of();
        metaWorkInfoService.removeJob(workId, metaJobObjectVoList);

        if (log.isDebugEnabled()) {
            log.debug("向work【{}】中移除Job. {}", workId, JSONObject.toJSONString(metaJobObjectVoList));
        }
        return dataResponse;
    }

    @PostMapping("/job-publish/{workId}")
    @ApiOperation(value = "发布Work中的Job")
    @ApplicationLog(description = "发布Work中的Job")
    public DataResponse<List<WorkJobPublishResult>> publish(@ApiParam(required = true, value = "JobList") @RequestBody List<MetaJobObjectVo> metaJobObjectVoList,
                                                      @ApiParam(required = true, value = "workId") @PathVariable Long workId
    ) {
        DataResponse dataResponse = DataResponse.of();
        List<WorkJobPublishResult>  publishResults = metaWorkInfoService.publish(workId, metaJobObjectVoList);

        dataResponse.setData(publishResults);
        if (log.isDebugEnabled()) {
            log.debug("向work【{}】中发布Job. {}", workId, JSONObject.toJSONString(metaJobObjectVoList));
        }
        return dataResponse;
    }

    @PostMapping("/job-sort/{workId}")
    @ApiOperation(value = "发布Work中的Job")
    @ApplicationLog(description = "发布Work中的Job")
    public DataResponse jobSort(@ApiParam(required = true, value = "JobList") @RequestBody List<MetaJobObjectVo> metaJobObjectVoList,
                                                            @ApiParam(required = true, value = "workId") @PathVariable Long workId
    ) {
        DataResponse dataResponse = DataResponse.of();
        metaWorkInfoService.jobSort(workId, metaJobObjectVoList);

        if (log.isDebugEnabled()) {
            log.debug("向work【{}】中发布Job. {}", workId, JSONObject.toJSONString(metaJobObjectVoList));
        }
        return dataResponse;
    }

}
