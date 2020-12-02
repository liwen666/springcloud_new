package jrx.data.hub.domain.service;

import com.baomidou.mybatisplus.extension.service.IService;
import jrx.data.hub.domain.vo.MetaDataSourceInfoVo;
import jrx.data.hub.infrastructure.entity.MetaDataSourceInfo;

import java.util.List;

/**
 * <p>
 * 数据源信息 服务类
 * </p>
 *
 * @author lw
 * @since 2020-11-05
 */
public interface IMetaDataSourceInfoService extends IService<MetaDataSourceInfo> {

    MetaDataSourceInfo create(MetaDataSourceInfo metaDataSourceInfo);

    MetaDataSourceInfo update(MetaDataSourceInfo metaDataSourceInfo);

    List<MetaDataSourceInfoVo> listAll();

}
