package jrx.data.hub.domain.service;

import com.baomidou.mybatisplus.extension.service.IService;
import jrx.data.hub.domain.enums.VersionState;
import jrx.data.hub.domain.vo.MetaFunctionVo;
import jrx.data.hub.infrastructure.entity.MetaFunction;

import java.util.Optional;

/**
 * <p>
 * 函数版本 服务类
 * </p>
 *
 * @author lw
 * @since 2020-11-05
 */
public interface IMetaFunctionService extends IService<MetaFunction> {

    MetaFunction update(MetaFunction function);

    Boolean updateState(String functionId, VersionState versionState);

    Boolean create(String functionId);

    Optional<MetaFunctionVo> getOnlineFunctionByInfoId(String functionInfoId);

    Object test(MetaFunctionVo metaFunctionVo);

    Boolean publish(String resourceId);
}
