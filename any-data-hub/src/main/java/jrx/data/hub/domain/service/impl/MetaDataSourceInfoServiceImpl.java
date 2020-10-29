package jrx.data.hub.domain.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jrx.data.hub.domain.service.IMetaDataSourceInfoService;
import jrx.data.hub.infrastructure.dao.MetaDataSourceInfoMapper;
import jrx.data.hub.infrastructure.model.MetaDataSourceInfo;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lw
 * @since 2020-10-29
 */
@Service
public class MetaDataSourceInfoServiceImpl extends ServiceImpl<MetaDataSourceInfoMapper, MetaDataSourceInfo> implements IMetaDataSourceInfoService {

}
