package jrx.data.hub.domain.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jrx.data.hub.domain.service.IMcUserService;
import jrx.data.hub.infrastructure.dao.McUserMapper;
import jrx.data.hub.infrastructure.entity.McUser;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author lw
 * @since 2020-11-05
 */
@Service
public class McUserServiceImpl extends ServiceImpl<McUserMapper, McUser> implements IMcUserService {

}
