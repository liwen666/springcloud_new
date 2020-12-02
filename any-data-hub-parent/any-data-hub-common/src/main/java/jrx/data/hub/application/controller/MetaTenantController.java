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
import jrx.data.hub.domain.common.ErrorMessageAssistant;
import jrx.data.hub.domain.service.IMetaTenantService;
import jrx.data.hub.domain.vo.MetaTenantVo;
import jrx.data.hub.infrastructure.entity.MetaTenant;
import jrx.data.hub.util.DataResponse;
import jrx.data.hub.util.DataTransferUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * <p>
 * 描述
 * </p>
 *
 * @author lw
 * @since 2020-11-16
 */

@RestController
@RequestMapping("/hub/meta-tenant")
@Api(description = "租户")
public class MetaTenantController {

    @Autowired
    private IMetaTenantService metaTenantService;

    @GetMapping("/list")
    @ApiOperation(value = "租户列表查询")
    @ApplicationLog(description = "租户列表查询")
    public DataResponse list(@ApiParam(value = "当前页数") @RequestParam(defaultValue = "1") int pageCount,
                             @ApiParam(value = "每页的记录条数") @RequestParam(defaultValue = "10") int pageSize,
                             @ApiParam(value = "租户名称") @RequestParam(required = false) String tenantName,
                             @ApiParam(value = "租户标识") @RequestParam(required = false) String tenantCode) {
        DataResponse dataResponse = DataResponse.of();
        QueryWrapper<MetaTenant> param = new QueryWrapper<>();
        if (StringUtils.isNoneBlank(tenantName)) {
            param.like("tenant_name", tenantName);
        }
        if (StringUtils.isNoneBlank(tenantCode)) {
            param.like("tenant_code", tenantCode);
        }
        Page<MetaTenant> page = new Page<>(pageCount, pageSize);
        page.setOrders(Lists.newArrayList(OrderItem.desc("update_time")));
        IPage<MetaTenant> metaTenantIPage = metaTenantService.page(page, param);
        dataResponse.setData(DataTransferUtils.pageModelToVo(metaTenantIPage, MetaTenantVo.class));
        return dataResponse;
    }

    @PostMapping("/save")
    @ApiOperation(value = "新建租户信息")
    @ApplicationLog(description = "新建租户信息")
    public DataResponse save(@ApiParam(required = true, value = "租户信息model") @Valid @RequestBody MetaTenantVo metaTenantVo, BindingResult bindingResult) {
        DataResponse dataResponse = DataResponse.of();
        if (bindingResult.hasErrors()) {
            return ErrorMessageAssistant.getDataResponse(bindingResult);
        }
        metaTenantVo.setTenantId(null);
        boolean flag = metaTenantService.create(DataTransferUtils.modelToVo(metaTenantVo, new MetaTenant()));
        if (!flag) {
            dataResponse = DataResponse.error();
        }
        return dataResponse;
    }

    @PutMapping("/update")
    @ApiOperation(value = "修改租户信息")
    @ApplicationLog(description = "修改租户信息")
    public DataResponse update(@ApiParam(required = true, value = "租户信息model") @RequestBody MetaTenantVo metaTenantVo) {
        DataResponse dataResponse = DataResponse.of();
        boolean flag = metaTenantService.update(DataTransferUtils.modelToVo(metaTenantVo, new MetaTenant()));
        if (!flag) {
            dataResponse = DataResponse.error();
        }
        return dataResponse;
    }

    @GetMapping("/{tenantId}")
    @ApiOperation(value = "查看租户信息")
    public DataResponse view(@ApiParam(required = true, value = "租户id") @PathVariable Long tenantId) {
        DataResponse dataResponse = DataResponse.of();
        MetaTenant metaTenant = metaTenantService.getById(tenantId);
        dataResponse.setData(DataTransferUtils.modelToVo(metaTenant, new MetaTenantVo()));
        return dataResponse;
    }

    @DeleteMapping("/{tenantId}")
    @ApiOperation(value = "删除租户信息")
    public DataResponse delete(@ApiParam(required = true, value = "租户id") @PathVariable String tenantId) {
        DataResponse dataResponse = DataResponse.of();
        Boolean flag = metaTenantService.deleteBYId(tenantId);
        dataResponse.setData(flag);
        return dataResponse;
    }

}
