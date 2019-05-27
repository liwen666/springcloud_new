package com.lw.common.security.impl;

import com.lw.common.exception.impl.EntityNotFoundException;
import com.lw.common.security.GrantedAuthority;
import com.lw.common.security.UserDetails;
import com.lw.common.security.UserDetailsService;
import com.lw.common.security.dao.PermisssionDao;
import com.lw.common.security.dao.UserDao;
import com.lw.common.security.domain.JwtUser;
import com.lw.common.security.domain.Permission;
import com.lw.common.security.domain.Role;
import com.lw.common.security.domain.User;
import com.lw.common.utils.el.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
* Description:    java类作用描述<br>
* author:     lw
* date:     2019/5/26 16:53
* Version:        1.0
*/
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PermisssionDao permisssionDao;

    @Override
    public UserDetails loadUserByUsername(String username){

        User user = null;
        if(ValidationUtil.isEmail(username)){
            user = userDao.findByEmail(username);
        } else {
            user = userDao.findByUsername(username);
        }

        if (user == null) {
            throw new EntityNotFoundException(User.class, "name", username);
        } else {
            return create(user);
        }
    }

    public UserDetails create(User user) {
        return new JwtUser(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getAvatar(),
                user.getEmail(),
                mapToGrantedAuthorities(user.getRoles(),permisssionDao),
                user.getEnabled(),
                user.getLastPasswordResetTime()
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(Set<Role> roles, PermisssionDao permisssionDao) {

        Set<Permission> permissions = new HashSet<>();
        for (Role role : roles) {
            Set<Role> roleSet = new HashSet<>();
            roleSet.add(role);
            permissions.addAll(permisssionDao.findByRoles(roleSet));
        }

        return permissions.stream()
                .map(permission -> new SimpleGrantedAuthority("ROLE_"+permission.getName()))
                .collect(Collectors.toList());
    }
}
