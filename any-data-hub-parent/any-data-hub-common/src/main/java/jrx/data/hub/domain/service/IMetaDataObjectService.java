package jrx.data.hub.domain.service;

import com.baomidou.mybatisplus.extension.service.IService;
import jrx.data.hub.domain.vo.MetaDataObjectVo;
import jrx.data.hub.infrastructure.entity.MetaDataObject;

import java.util.Optional;

/**
 * <p>
 * 表版本信息 服务类
 * </p>
 *
 * @author lw
 * @since 2020-11-05
 */
public interface IMetaDataObjectService extends IService<MetaDataObject> {

    MetaDataObject create(MetaDataObject metaDataObject);

    MetaDataObject update(MetaDataObject metaDataObject);

    Optional<MetaDataObjectVo> getOnlineDataObjectByInfoId(String resourceId);
}
