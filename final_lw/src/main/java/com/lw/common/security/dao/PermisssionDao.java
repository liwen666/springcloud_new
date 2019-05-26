package com.lw.common.security.dao;

import com.lw.common.security.domain.Permission;
import com.lw.common.security.domain.Role;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Set;

/**
 * Description:    java接口的作用
 * author:     lw
 * date:     2019/5/26$ 16:35$
 * Version:        1.0
 */
@Repository
public interface PermisssionDao {
    Collection<? extends Permission> findByRoles(Set<Role> roleSet);
}
