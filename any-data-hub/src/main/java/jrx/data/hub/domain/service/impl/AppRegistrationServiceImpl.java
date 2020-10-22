package jrx.data.hub.domain.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jrx.data.hub.domain.service.IAppRegistrationService;
import jrx.data.hub.infrastructure.dao.AppRegistrationMapper;
import jrx.data.hub.infrastructure.model.AppRegistration;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  描述
 * </p>
 *
 * @author lw
 * @since  2020/10/22 16:27
 */
@Service
public class AppRegistrationServiceImpl extends ServiceImpl<AppRegistrationMapper, AppRegistration> implements IAppRegistrationService {

}
