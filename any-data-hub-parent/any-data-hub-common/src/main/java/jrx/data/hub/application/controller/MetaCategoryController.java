package jrx.data.hub.application.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import jrx.data.hub.domain.aop.annotation.ApplicationLog;
import jrx.data.hub.domain.exception.DataSourceException;
import jrx.data.hub.domain.service.impl.MetaCategoryServiceImpl;
import jrx.data.hub.domain.service.impl.MetaRelationInfoServiceImpl;
import jrx.data.hub.domain.vo.MetaCategoryVo;
import jrx.data.hub.infrastructure.entity.MetaCategory;
import jrx.data.hub.infrastructure.entity.MetaRelationInfo;
import jrx.data.hub.util.DataResponse;
import jrx.data.hub.util.DataTransferUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 描述 分类信息
 * </p>
 *
 * @author lw
 * @since 2020-11-05
 */

@RestController
@RequestMapping("/hub/meta-category")
@Api(description = "分类信息接口")
@Slf4j
public class MetaCategoryController {

    @Autowired
    private MetaCategoryServiceImpl metaCategoryServiceImpl;

    @Autowired
    private MetaRelationInfoServiceImpl metaRelationInfoServiceImpl;

    @GetMapping("/list")
    @ApiOperation(value = "分类信息列表查询")
    @ApplicationLog(description = "分类信息列表查询")
    public DataResponse list(@ApiParam(value = "当前页数") @RequestParam(defaultValue = "1") int pageCount,
                             @ApiParam(value = "每页的记录条数") @RequestParam(defaultValue = "10") int pageSize) {
        Page<MetaCategory> page = new Page<>(pageCount, pageSize);
        IPage<MetaCategory> metaCategoryIPage = metaCategoryServiceImpl.page(page);
        DataResponse dataResponse = DataResponse.of();
        dataResponse.setData(DataTransferUtils.pageModelToVo(metaCategoryIPage, MetaCategoryVo.class));
        return dataResponse;
    }

    @PostMapping("/save")
    @ApiOperation(value = "新建分类")
    @ApplicationLog(description = "新建分类")
    public DataResponse save(@ApiParam(required = true, value = "分类model") @RequestBody MetaCategoryVo metaCategoryVo) {
        DataResponse dataResponse = DataResponse.of();
        validateDateName(metaCategoryVo.getName());
        metaCategoryVo.setCategoryId(null);
        boolean flag = metaCategoryServiceImpl.save(DataTransferUtils.modelToVo(metaCategoryVo, new MetaCategory()));
        if (!flag) {
            dataResponse = DataResponse.error();
        }
        return dataResponse;
    }

    @PutMapping("/update")
    @ApiOperation(value = "修改分类")
    @ApplicationLog(description = "修改分类")
    public DataResponse update(@ApiParam(required = true, value = "分类model") @RequestBody MetaCategoryVo metaCategoryVo) {
        DataResponse dataResponse = DataResponse.of();
        validateDateName(metaCategoryVo.getName());
        boolean flag = metaCategoryServiceImpl.updateById(DataTransferUtils.modelToVo(metaCategoryVo, new MetaCategory()));
        if (!flag) {
            dataResponse = DataResponse.error();
        }
        return dataResponse;

    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除分类")
    @ApplicationLog(description = "删除分类")
    public DataResponse delete(@ApiParam(required = true, value = "分类对象id") @PathVariable String id) {
        DataResponse dataResponse = DataResponse.of();
        QueryWrapper<MetaRelationInfo> queryWrapper = new QueryWrapper<MetaRelationInfo>();
        queryWrapper.eq("source_id", id);
        List<MetaRelationInfo> list = metaRelationInfoServiceImpl.list(queryWrapper);
        if (list != null && list.size() > 0) {
            throw new DataSourceException("该分类正在被使用，不能删除");
        }
        boolean flag = metaCategoryServiceImpl.removeById(id);
        if (!flag) {
            dataResponse = DataResponse.error();
        }
        return dataResponse;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "查看分类")
    public DataResponse view(@ApiParam(required = true, value = "分类id") @PathVariable Long id) {
        DataResponse dataResponse = DataResponse.of();
        MetaCategory metaCategory = metaCategoryServiceImpl.getById(id);
        dataResponse.setData(DataTransferUtils.modelToVo(metaCategory, new MetaCategoryVo()));
        return dataResponse;
    }

    public void validateDateName(String name) {
        MetaCategory info = metaCategoryServiceImpl.queryByName(name);
        if (info != null) {
            throw new DataSourceException("分类名称不能相同");
        }
    }
}
