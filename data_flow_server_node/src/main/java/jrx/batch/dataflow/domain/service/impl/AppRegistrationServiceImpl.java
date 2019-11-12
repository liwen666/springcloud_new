package jrx.batch.dataflow.domain.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jrx.batch.dataflow.domain.service.IAppRegistrationService;
import jrx.batch.dataflow.infrastructure.dao.AppRegistrationMapper;
import jrx.batch.dataflow.infrastructure.model.AppRegistration;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author schedule
 * @since 2019-11-11
 */
@Service
public class AppRegistrationServiceImpl extends ServiceImpl<AppRegistrationMapper, AppRegistration> implements IAppRegistrationService {

}
