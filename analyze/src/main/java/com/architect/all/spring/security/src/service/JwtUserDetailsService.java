package com.architect.all.spring.security.src.service;

import com.architect.all.exception.EntityNotFoundException;
import com.architect.all.spring.security.src.domin.JwtUser;
import com.architect.all.spring.security.src.domin.Permission;
import com.architect.all.spring.security.src.domin.Role;
import com.architect.all.spring.security.src.domin.User;
import com.architect.all.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author lw
 * @date 2019.3.20
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserServiceImple userServiceImple;
    @Autowired
    private PermissionServcice permissionServcice;

    @Override
    public UserDetails loadUserByUsername(String username){

        User user = null;
        if(ValidationUtil.isEmail(username)){
            user = userServiceImple.findByEmail(username);
        } else {
            user = userServiceImple.findByUsername(username);
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
                mapToGrantedAuthorities(user.getRoles(),permissionServcice),
                user.getEnabled(),
                user.getLastPasswordResetTime()
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(Set<Role> roles, PermissionServcice permissionServcice) {

        Set<Permission> permissions = new HashSet<>();
        for (Role role : roles) {
            Set<Role> roleSet = new HashSet<>();
            roleSet.add(role);
            permissions.addAll(permissionServcice.findByRoles(roleSet));
        }

        return permissions.stream()
                .map(permission -> new SimpleGrantedAuthority("ROLE_"+permission.getName()))
                .collect(Collectors.toList());
    }
}
