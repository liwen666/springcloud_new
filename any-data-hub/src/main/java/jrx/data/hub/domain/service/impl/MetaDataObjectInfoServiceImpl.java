package jrx.data.hub.domain.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jrx.data.hub.domain.service.IMetaDataObjectInfoService;
import jrx.data.hub.infrastructure.dao.MetaDataObjectInfoMapper;
import jrx.data.hub.infrastructure.model.MetaDataObjectInfo;
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
public class MetaDataObjectInfoServiceImpl extends ServiceImpl<MetaDataObjectInfoMapper, MetaDataObjectInfo> implements IMetaDataObjectInfoService {

}
