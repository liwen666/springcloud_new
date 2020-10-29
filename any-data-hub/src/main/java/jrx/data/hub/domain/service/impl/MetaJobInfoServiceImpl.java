package jrx.data.hub.domain.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jrx.data.hub.domain.service.IMetaJobInfoService;
import jrx.data.hub.infrastructure.dao.MetaJobInfoMapper;
import jrx.data.hub.infrastructure.model.MetaJobInfo;
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
public class MetaJobInfoServiceImpl extends ServiceImpl<MetaJobInfoMapper, MetaJobInfo> implements IMetaJobInfoService {

}
