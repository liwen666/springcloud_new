package jrx.data.hub.domain.service;

import com.baomidou.mybatisplus.extension.service.IService;
import jrx.data.hub.domain.vo.MetaFunctionInfoVo;
import jrx.data.hub.infrastructure.entity.MetaFunctionInfo;

import java.util.List;

/**
 * <p>
 * 函数信息 服务类
 * </p>
 *
 * @author lw
 * @since 2020-11-05
 */
public interface IMetaFunctionInfoService extends IService<MetaFunctionInfo> {

    MetaFunctionInfo create(MetaFunctionInfo info);

    MetaFunctionInfo update(MetaFunctionInfo info);

    List<MetaFunctionInfoVo> listAll();

    boolean deleteByResourceId(String resourceId);
}
