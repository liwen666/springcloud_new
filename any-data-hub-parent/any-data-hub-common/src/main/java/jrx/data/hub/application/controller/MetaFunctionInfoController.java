package jrx.data.hub.application.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import jrx.data.hub.domain.aop.annotation.ApplicationLog;
import jrx.data.hub.domain.common.ErrorMessageAssistant;
import jrx.data.hub.domain.service.IMetaFunctionInfoService;
import jrx.data.hub.domain.service.IMetaFunctionService;
import jrx.data.hub.domain.vo.MetaFunctionInfoVo;
import jrx.data.hub.infrastructure.entity.MetaFunction;
import jrx.data.hub.infrastructure.entity.MetaFunctionInfo;
import jrx.data.hub.util.DataResponse;
import jrx.data.hub.util.DataTransferUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 描述 函数信息
 * </p>
 *
 * @author lw
 * @since 2020-11-05
 */
@RestController
@RequestMapping("/hub/meta-function-info")
@Api(description = "函数信息接口")
public class MetaFunctionInfoController {
    @Autowired
    private IMetaFunctionInfoService metaFunctionInfoService;

    @Autowired
    private IMetaFunctionService metaFunctionService;

    @GetMapping("/list")
    @ApiOperation(value = "函数信息列函数查询")
    @ApplicationLog(description = "函数信息列函数查询")
    public DataResponse list(@ApiParam(value = "当前页数") @RequestParam(defaultValue = "1") int pageCount,
                             @ApiParam(value = "每页的记录条数") @RequestParam(defaultValue = "10") int pageSize,
                             @ApiParam(value = "函数名称") @RequestParam(required = false) String name) {
        DataResponse dataResponse = DataResponse.of();
        QueryWrapper<MetaFunctionInfo> param = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(name)) {
            param.like("name", name);
        }
        Page<MetaFunctionInfo> page = new Page<>(pageCount, pageSize);
        page.setOrders(Lists.newArrayList(OrderItem.desc("update_time")));
        IPage<MetaFunctionInfo> metaCategoryIPage = metaFunctionInfoService.page(page, param);
        IPage<MetaFunctionInfoVo> pageVo = DataTransferUtils.pageModelToVo(metaCategoryIPage, MetaFunctionInfoVo.class);
        if (!CollectionUtils.isEmpty(pageVo.getRecords())) {
            pageVo.getRecords().stream().forEach(it -> {
                QueryWrapper<MetaFunction> listParam = new QueryWrapper<>();
                listParam.eq("resource_id", it.getResourceId());
                listParam.orderByDesc("version_code");
                listParam.last("limit 1");
                MetaFunction function = metaFunctionService.getOne(listParam);
                if (function != null) {
                    it.setLanguage(function.getLanguage());
                    it.setVersionState(function.getVersionState());
                    it.setLastFunctionId(function.getFunctionId());
                }
            });
        }
        dataResponse.setData(pageVo);
        return dataResponse;
    }


    @GetMapping("/findlist")
    @ApiOperation("查找函数信息")
    public DataResponse findList(@ApiParam(value = "函数名称") @RequestParam(required = false) String name) {
        DataResponse res = DataResponse.of();
        // 查看注册的函数
        LambdaQueryWrapper<MetaFunctionInfo> wrapper = Wrappers.<MetaFunctionInfo>lambdaQuery().isNotNull(MetaFunctionInfo::getZplFunctionId);
        if (StringUtils.isNotBlank(name)) {
            wrapper.like(MetaFunctionInfo::getName, name);
        } else {
            return res;
        }
        List<MetaFunctionInfo> functions = metaFunctionInfoService.list(wrapper);
        List<MetaFunctionInfoVo> functionInfos = DataTransferUtils.modelListToVoList(functions, MetaFunctionInfoVo.class);
        functionInfos.forEach(it -> {
            MetaFunction function = metaFunctionService.getOne(Wrappers.<MetaFunction>lambdaQuery().eq(MetaFunction::getResourceId, it.getResourceId()).orderByDesc(MetaFunction::getCreateTime).last("limit 1"));
            it.setLanguage(function.getLanguage());
            it.setVersionState(function.getVersionState());
            it.setLastFunctionId(function.getFunctionId());
        });
        res.setData(functionInfos);
        return res;
    }

    @PostMapping("/save")
    @ApiOperation(value = "新建函数信息")
    @ApplicationLog(description = "新建函数信息")
    public DataResponse save(@ApiParam(required = true, value = "函数信息model") @Valid @RequestBody MetaFunctionInfoVo metaFunctionInfoVo, BindingResult bindingResult) {
        DataResponse dataResponse = DataResponse.of();
        if (bindingResult.hasErrors()) {
            return ErrorMessageAssistant.getDataResponse(bindingResult);
        }
        MetaFunctionInfo metaFunctionInfo = metaFunctionInfoService.create(DataTransferUtils.modelToVo(metaFunctionInfoVo, new MetaFunctionInfo()));
        dataResponse.setData(DataTransferUtils.modelToVo(metaFunctionInfo, new MetaFunctionInfoVo()));
        return dataResponse;
    }

    @PutMapping("/update")
    @ApiOperation(value = "修改函数信息")
    @ApplicationLog(description = "修改函数信息")
    public DataResponse update(@ApiParam(required = true, value = "函数信息model") @RequestBody MetaFunctionInfoVo metaFunctionInfoVo) {
        DataResponse dataResponse = DataResponse.of();
        MetaFunctionInfo metaFunctionInfo = metaFunctionInfoService.update(DataTransferUtils.modelToVo(metaFunctionInfoVo, new MetaFunctionInfo()));
        dataResponse.setData(DataTransferUtils.modelToVo(metaFunctionInfo, new MetaFunctionInfoVo()));
        return dataResponse;
    }

    @GetMapping("/{resourceId}")
    @ApiOperation(value = "查看函数信息")
    public DataResponse view(@ApiParam(required = true, value = "函数信息id") @PathVariable String resourceId) {
        DataResponse dataResponse = DataResponse.of();
        QueryWrapper<MetaFunctionInfo> param = new QueryWrapper<>();
        param.eq("resource_id", resourceId);
        MetaFunctionInfo info = metaFunctionInfoService.getOne(param);
        MetaFunctionInfoVo metaFunctionInfoVo = DataTransferUtils.modelToVo(info, new MetaFunctionInfoVo());

        QueryWrapper<MetaFunction> functionParam = new QueryWrapper<>();
        functionParam.eq("resource_id", resourceId);
        functionParam.orderBy(true, false, "version_code");
        List<MetaFunction> functionList = metaFunctionService.list(functionParam);
        metaFunctionInfoVo.setMetaFunctionList(functionList);
        metaFunctionInfoVo.setLastFunctionId(functionList.get(0).getFunctionId());
        dataResponse.setData(metaFunctionInfoVo);
        return dataResponse;
    }

    @DeleteMapping("/delete")
    @ApiOperation(value = "删除函数信息")
    @ApplicationLog(description = "删除函数信息")
    public DataResponse delete(@ApiParam(required = true, value = "函数id") @RequestParam String resourceId) {
        DataResponse dataResponse = DataResponse.of();
        boolean flag = metaFunctionInfoService.deleteByResourceId(resourceId);
        if (!flag) {
            dataResponse = DataResponse.error();
        }
        return dataResponse;
    }

    //    @GetMapping("/list/all")
    @ApiOperation(value = "函数信息列函数查询，Job编辑页面使用")
    @ApplicationLog(description = "函数信息列函数查询，Job编辑页面使用")
    public DataResponse<MetaFunctionInfoVo> listAll() {
        DataResponse dataResponse = DataResponse.of();
        List<MetaFunctionInfoVo> functionInfoVoList = metaFunctionInfoService.listAll();
        dataResponse.setData(functionInfoVoList);
        return dataResponse;

    }

}
