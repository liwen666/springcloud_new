package jrx.data.hub.domain.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jrx.data.hub.domain.service.IMetaDataObjectService;
import jrx.data.hub.infrastructure.dao.MetaDataObjectMapper;
import jrx.data.hub.infrastructure.model.MetaDataObject;
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
public class MetaDataObjectServiceImpl extends ServiceImpl<MetaDataObjectMapper, MetaDataObject> implements IMetaDataObjectService {

}
