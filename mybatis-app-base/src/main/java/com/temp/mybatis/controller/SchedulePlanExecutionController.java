package com.temp.mybatis.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.temp.mybatis.dao.FlinkJobConfigMapper;
import com.temp.mybatis.model.FlinkJobConfig;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


/**
 * @author yxy
 */
@Api(description = "测试")
@RestController
@RequestMapping("/demo")
public class SchedulePlanExecutionController {


    @Autowired
    private FlinkJobConfigMapper flinkJobConfigMapper;

    @ApiOperation(value = "根据计划id列出该计划的执行记录", notes = "根据调度计划主键获取列表")
    @GetMapping("/{page}")
    public PageInfo list(
            @ApiParam(value = "页数，起始为1") @PathVariable(required = false, value = "page") Integer page,
            @ApiParam(required = true, value = "当前页item数量") @RequestParam(required = false, defaultValue = "2") Integer size,
            @ApiParam(required = true, value = "执行开始时间", format = "yyyy-MM-dd") @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @ApiParam(required = true, value = "执行结束时间", format = "yyyy-MM-dd") @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate
    ) {

        Page mybatisPage = PageHelper.startPage(page, size);
        List<FlinkJobConfig> schedulePlanExecutionList = flinkJobConfigMapper.selectAll();

        Page<FlinkJobConfig> pager = new Page<>(page, size);
        if (CollectionUtils.isEmpty(schedulePlanExecutionList)) {
            pager.close();
            return new PageInfo(pager);
        }
        pager.getResult().addAll(schedulePlanExecutionList);
        pager.setTotal(mybatisPage.getTotal());
        pager.close();
        return new PageInfo(pager);
    }

}
