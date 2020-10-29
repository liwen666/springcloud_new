package jrx.data.hub.domain.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jrx.data.hub.domain.service.IMcUserService;
import jrx.data.hub.infrastructure.dao.McUserMapper;
import jrx.data.hub.infrastructure.model.McUser;
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
public class McUserServiceImpl extends ServiceImpl<McUserMapper, McUser> implements IMcUserService {

}
