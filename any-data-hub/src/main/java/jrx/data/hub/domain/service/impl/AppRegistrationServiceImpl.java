package jrx.data.hub.domain.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jrx.data.hub.domain.service.IAppRegistrationService;
import jrx.data.hub.infrastructure.dao.AppRegistrationMapper;
import jrx.data.hub.infrastructure.model.AppRegistration;
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
