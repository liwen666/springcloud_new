package jrx.data.hub.domain.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jrx.data.hub.domain.service.IMetaFunctionService;
import jrx.data.hub.infrastructure.dao.MetaFunctionMapper;
import jrx.data.hub.infrastructure.model.MetaFunction;
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
public class MetaFunctionServiceImpl extends ServiceImpl<MetaFunctionMapper, MetaFunction> implements IMetaFunctionService {

}
