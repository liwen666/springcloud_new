package jrx.data.hub.domain.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jrx.data.hub.domain.service.IMetaFunctionInfoService;
import jrx.data.hub.infrastructure.dao.MetaFunctionInfoMapper;
import jrx.data.hub.infrastructure.model.MetaFunctionInfo;
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
public class MetaFunctionInfoServiceImpl extends ServiceImpl<MetaFunctionInfoMapper, MetaFunctionInfo> implements IMetaFunctionInfoService {

}
