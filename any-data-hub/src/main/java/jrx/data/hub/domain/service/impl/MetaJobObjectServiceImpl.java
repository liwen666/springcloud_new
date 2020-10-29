package jrx.data.hub.domain.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jrx.data.hub.domain.service.IMetaJobObjectService;
import jrx.data.hub.infrastructure.dao.MetaJobObjectMapper;
import jrx.data.hub.infrastructure.model.MetaJobObject;
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
public class MetaJobObjectServiceImpl extends ServiceImpl<MetaJobObjectMapper, MetaJobObject> implements IMetaJobObjectService {

}
