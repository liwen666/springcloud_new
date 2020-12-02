package jrx.data.hub.domain.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jrx.data.hub.domain.service.IMetaObjectFieldService;
import jrx.data.hub.infrastructure.dao.MetaObjectFieldMapper;
import jrx.data.hub.infrastructure.entity.MetaObjectField;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 字段信息 服务实现类
 * </p>
 *
 * @author lw
 * @since 2020-11-05
 */
@Service
public class MetaObjectFieldServiceImpl extends ServiceImpl<MetaObjectFieldMapper, MetaObjectField> implements IMetaObjectFieldService {

}
