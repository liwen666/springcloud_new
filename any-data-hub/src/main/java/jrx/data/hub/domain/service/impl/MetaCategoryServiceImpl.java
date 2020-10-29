package jrx.data.hub.domain.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jrx.data.hub.domain.service.IMetaCategoryService;
import jrx.data.hub.infrastructure.dao.MetaCategoryMapper;
import jrx.data.hub.infrastructure.model.MetaCategory;
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
public class MetaCategoryServiceImpl extends ServiceImpl<MetaCategoryMapper, MetaCategory> implements IMetaCategoryService {

}
