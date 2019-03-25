package com.architect.all.geneator.tx.service.impl;

import com.architect.all.geneator.tx.domain.Permission;
import com.architect.all.geneator.tx.dao.PermissionMapper;
import com.architect.all.geneator.tx.service.IPermissionService;
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
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {

}
