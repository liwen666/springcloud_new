package jrx.batch.dataflow.application;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import jrx.batch.dataflow.domain.service.IBatchStepExecutionService;
import jrx.batch.dataflow.domain.service.impl.BatchServiceAgent;
import jrx.batch.dataflow.infrastructure.model.BatchStepExecution;
import jrx.batch.dataflow.util.JsonResult;
import org.springframework.batch.core.BatchStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 * 前端控制器
 * 自定义
 * aaaaaaaaaaaaaaaaaaaaa
 * </p>
 *
 * @author schedule
 * @since 2019-11-19
 */
@RestController
@RequestMapping("/infrastructure/batch-step-execution")
public class BatchStepExecutionController {
    @Autowired
    private IBatchStepExecutionService batchStepExecutionService;

    @Autowired
    private BatchServiceAgent batchServiceAgent;

    @GetMapping(value = "/step/{executionId}")
    public JsonResult getBatchStepExecution(@PathVariable("executionId") long executionId) {
        BatchStepExecution one = null;
        try {
            one = batchStepExecutionService.getOne(Wrappers.<BatchStepExecution>lambdaQuery().eq(BatchStepExecution::getStepExecutionId, executionId));
        } catch (Exception e) {
            e.printStackTrace();
            JsonResult.error(e.getMessage());
        }
        return JsonResult.success(one);
    }

    @PostMapping(value = "/step/modify")
    public JsonResult modifyStepAndJobStatus(@RequestPart("executionId") long executionId, @RequestParam("status") String status) {
        boolean flag = false;
        try {
            flag = batchServiceAgent.updateJobAndStep(executionId, status);
        } catch (Exception e) {
            e.printStackTrace();
            JsonResult.error(e.getMessage());
        }
        return JsonResult.success(flag);
    }
}
