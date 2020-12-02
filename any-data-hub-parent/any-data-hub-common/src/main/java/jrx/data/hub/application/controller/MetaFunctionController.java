package jrx.data.hub.application.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import jrx.data.hub.domain.aop.annotation.ApplicationLog;
import jrx.data.hub.domain.enums.VersionState;
import jrx.data.hub.domain.service.IMetaFunctionService;
import jrx.data.hub.domain.vo.MetaFunctionVo;
import jrx.data.hub.infrastructure.entity.MetaFunction;
import jrx.data.hub.util.DataResponse;
import jrx.data.hub.util.DataTransferUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * <p>
 * 描述 函数版本
 * </p>
 *
 * @author lw
 * @since 2020-11-05
 */

@Api(description = "函数详细信息")
@RestController
@RequestMapping("/hub/meta-function")
public class MetaFunctionController {

    @Autowired
    private IMetaFunctionService metaFunctionService;

    @GetMapping("/{functionId}/view")
    @ApiOperation(value = "函数版本信息查询")
    @ApplicationLog(description = "函数版本信息查询")
    public DataResponse view(@ApiParam(value = "函数版本Id") @PathVariable String functionId) {
        DataResponse dataResponse = DataResponse.of();
        QueryWrapper<MetaFunction> param = new QueryWrapper<>();
        param.eq("function_id", functionId);
        MetaFunction metaFunction = metaFunctionService.getOne(param);
        MetaFunctionVo functionVo = DataTransferUtils.modelToVo(metaFunction, new MetaFunctionVo());
        dataResponse.setData(functionVo);
        return dataResponse;
    }

    @GetMapping("/{resourceId}/function/list")
    @ApiOperation(value = "函数版本列表查询")
    @ApplicationLog(description = "函数版本列表查询")
    public DataResponse list(@ApiParam(value = "函数资源id") @PathVariable String resourceId,
                             @ApiParam(value = "当前页数") @RequestParam(defaultValue = "1") int pageCount,
                             @ApiParam(value = "每页的记录条数") @RequestParam(defaultValue = "10") int pageSize) {
        DataResponse dataResponse = DataResponse.of();
        QueryWrapper<MetaFunction> param = new QueryWrapper<>();
        param.eq("resource_id", resourceId);
        Page<MetaFunction> page = new Page<>(pageCount, pageSize);
        page.setOrders(Lists.newArrayList(OrderItem.desc("update_time")));
        IPage<MetaFunction> metaFunctionIPage = metaFunctionService.page(page, param);
        IPage<MetaFunctionVo> functionVos = DataTransferUtils.pageModelToVo(metaFunctionIPage, MetaFunctionVo.class);
        dataResponse.setData(functionVos);
        return dataResponse;
    }

    @PutMapping("/update")
    @ApiOperation(value = "函数详情编辑")
    @ApplicationLog(description = "函数详情编辑")
    public DataResponse update(@ApiParam(required = true, value = "函数版本信息model") @RequestBody MetaFunctionVo metaFunctionVo) {
        DataResponse dataResponse = DataResponse.of();
        MetaFunction metaFunction = metaFunctionService.update(DataTransferUtils.modelToVo(metaFunctionVo, new MetaFunction()));
        dataResponse.setData(DataTransferUtils.modelToVo(metaFunction, new MetaFunctionVo()));
        return dataResponse;
    }

    @PutMapping("/updateState/{resourceId}/{functionId}/{versionState}")
    @ApiOperation(value = "函数版本状态变更")
    @ApplicationLog(description = "函数版本状态变更")
    public DataResponse updateState(@ApiParam(value = "函数资源id") @PathVariable String resourceId,
                                    @ApiParam(value = "函数版本id") @PathVariable String functionId,
                                    @ApiParam(value = "函数状态") @PathVariable VersionState versionState) {
        DataResponse dataResponse = DataResponse.of();
        if(VersionState.ONLINE.equals(versionState)){
            metaFunctionService.publish(resourceId);
        }
        Boolean flag = metaFunctionService.updateState(functionId, versionState);
        dataResponse.setData(flag);
        return dataResponse;
    }

    @PutMapping("/{functionId}/save")
    @ApiOperation(value = "函数版本另存为")
    @ApplicationLog(description = "函数版本另存为")
    public DataResponse save(@ApiParam(value = "函数版本id") @PathVariable String functionId) {
        DataResponse dataResponse = DataResponse.of();
        Boolean flag = metaFunctionService.create(functionId);
        dataResponse.setData(flag);
        return dataResponse;
    }

    @PutMapping("/test")
    @ApiOperation(value = "函数脚本运行")
    @ApplicationLog(description = "函数脚本运行")
    public DataResponse test(@ApiParam(value = "函数版本信息") @RequestBody @Valid MetaFunctionVo metaFunctionVo, BindingResult results) {
        DataResponse dataResponse = DataResponse.of();
        if (results.hasErrors()) {
            dataResponse = DataResponse.error();
            dataResponse.setReason(results.getFieldError().getDefaultMessage());
            return dataResponse;
        }

        Object result = metaFunctionService.test(metaFunctionVo);
        dataResponse.setData(result);
        return dataResponse;
    }

    @PutMapping("/{resourceId}/publish")
    @ApiOperation(value = "函数脚本发布")
    @ApplicationLog(description = "函数脚本发布")
    public DataResponse publish(@ApiParam(value = "函数id") @PathVariable String resourceId) {
        DataResponse dataResponse = DataResponse.of();
        Boolean result = metaFunctionService.publish(resourceId);
        dataResponse.setData(result);
        return dataResponse;
    }
}
