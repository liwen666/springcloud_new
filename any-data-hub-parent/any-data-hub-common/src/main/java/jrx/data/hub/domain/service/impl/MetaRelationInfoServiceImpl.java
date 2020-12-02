package jrx.data.hub.domain.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jrx.data.hub.domain.service.IMetaRelationInfoService;
import jrx.data.hub.infrastructure.dao.MetaRelationInfoMapper;
import jrx.data.hub.infrastructure.entity.MetaRelationInfo;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 对象之间的关联关系  服务实现类
 * </p>
 *
 * @author lw
 * @since 2020-11-05
 */
@Service
public class MetaRelationInfoServiceImpl extends ServiceImpl<MetaRelationInfoMapper, MetaRelationInfo> implements IMetaRelationInfoService {

}
