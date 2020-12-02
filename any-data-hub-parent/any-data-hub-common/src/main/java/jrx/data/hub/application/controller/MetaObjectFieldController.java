package jrx.data.hub.application.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import jrx.data.hub.domain.aop.annotation.ApplicationLog;
import jrx.data.hub.domain.service.impl.MetaObjectFieldServiceImpl;
import jrx.data.hub.domain.service.impl.MetaRelationInfoServiceImpl;
import jrx.data.hub.domain.vo.MetaObjectFieldVo;
import jrx.data.hub.infrastructure.entity.MetaObjectField;
import jrx.data.hub.infrastructure.entity.MetaRelationInfo;
import jrx.data.hub.util.DataResponse;
import jrx.data.hub.util.DataTransferUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 描述 字段信息
 * </p>
 *
 * @author lw
 * @since 2020-11-05
 */

@RestController
@RequestMapping("/hub/meta-object-field")
@Api(description = "字段信息接口")
public class MetaObjectFieldController {

    @Autowired
    MetaObjectFieldServiceImpl metaObjectFieldServiceImpl;
    @Autowired
    MetaRelationInfoServiceImpl metaRelationInfoServiceImpl;

    @GetMapping("/list")
    @ApiOperation(value = "表字段列表查询")
    @ApplicationLog(description = "表字段列表查询")
    public DataResponse list(@ApiParam(value = "当前页数") @RequestParam(defaultValue = "1") int pageCount,
                             @ApiParam(value = "每页的记录条数") @RequestParam(defaultValue = "10") int pageSize,
                             @ApiParam(value = "表版本ID") @RequestParam(required = true) int resourceVersionId) {
        //查询表对应的字段id
        QueryWrapper<MetaRelationInfo> relationParam = new QueryWrapper<>();
        relationParam.eq("source_id", resourceVersionId);
        List<MetaRelationInfo> metaRelationList = metaRelationInfoServiceImpl.list(relationParam);
        List<Long> objectfieldIds = new ArrayList<>();
        metaRelationList.forEach(info -> {
            objectfieldIds.add(info.getTargetId());
        });
        //查询字段id对应的实体
        QueryWrapper<MetaObjectField> objectFieldParam = new QueryWrapper<>();
        objectFieldParam.in("object_field_id", objectfieldIds);
        DataResponse dataResponse = DataResponse.of();
        Page<MetaObjectField> page = new Page<>(pageCount, pageSize);
        IPage<MetaObjectField> metaObjectFieldIPage = metaObjectFieldServiceImpl.page(page, objectFieldParam);
        dataResponse.setData(DataTransferUtils.pageModelToVo(metaObjectFieldIPage, MetaObjectFieldVo.class));
        return dataResponse;
    }

}
