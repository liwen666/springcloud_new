package com.architect.all.spring.security.src.service;

import com.architect.all.spring.security.src.domin.Permission;
import com.architect.all.spring.security.src.domin.Role;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * @author jie
 * @date 2018-12-03
 */
@Service
public class PermissionServcice {

    public Collection<? extends Permission> findByRoles(Set<Role> roleSet) {
        return null;
    }
}
