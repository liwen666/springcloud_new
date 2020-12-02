package jrx.data.hub.domain.service;

import com.baomidou.mybatisplus.extension.service.IService;
import jrx.data.hub.domain.enums.ResourceType;
import jrx.data.hub.domain.vo.MetaDataObjectVo;
import jrx.data.hub.infrastructure.entity.MetaDataObjectInfo;

import java.util.List;

/**
 * <p>
 * 表详情 服务类
 * </p>
 *
 * @author lw
 * @since 2020-11-05
 */
public interface IMetaDataObjectInfoService extends IService<MetaDataObjectInfo> {

    MetaDataObjectInfo createCollectTable(MetaDataObjectInfo metaDataObjectInfo);

    MetaDataObjectInfo createTargetTable(MetaDataObjectInfo metaDataObjectInfo);

    MetaDataObjectInfo update(MetaDataObjectInfo metaDataObjectInfo);

    List<MetaDataObjectVo> getOnlineDataObjectListByDataSourceId(String dataSourceId);
}
