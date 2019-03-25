package com.architect.all.geneator.tx.service.impl;

import com.architect.all.geneator.tx.domain.User;
import com.architect.all.geneator.tx.dao.UserMapper;
import com.architect.all.geneator.tx.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tx
 * @since 2019-03-25
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
