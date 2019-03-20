package com.temp.springcloud.system.service.impl;

import com.temp.springcloud.common.exception.BadRequestException;
import com.temp.springcloud.common.exception.EntityExistException;
import com.temp.springcloud.common.exception.EntityNotFoundException;
import com.temp.springcloud.common.util.ValidationUtil;
import com.temp.springcloud.core.util.JwtTokenUtil;
import com.temp.springcloud.system.domin.User;
import com.temp.springcloud.system.repository.UserRepository;
import com.temp.springcloud.system.service.UserService;
import com.temp.springcloud.system.service.dto.UserDTO;
import com.temp.springcloud.system.service.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author lw
 * @date 2019.3.20
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public UserDTO findById(long id) {
        Optional<User> user = userRepository.findById(id);
        ValidationUtil.isNull(user,"User","id",id);
        return userMapper.toDto(user.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserDTO create(User resources) {

        if(userRepository.findByUsername(resources.getUsername())!=null){
            throw new EntityExistException(User.class,"username",resources.getUsername());
        }

        if(userRepository.findByEmail(resources.getEmail())!=null){
            throw new EntityExistException(User.class,"email",resources.getEmail());
        }

        if(resources.getRoles() == null || resources.getRoles().size() == 0){
            throw new BadRequestException("角色不能为空");
        }

        // 默认密码 123456
        resources.setPassword("14e1b600b1fd579f47433b88e8d85291");
        resources.setAvatar("https://i.loli.net/2018/12/06/5c08894d8de21.jpg");
        return userMapper.toDto(userRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(User resources) {

        Optional<User> userOptional = userRepository.findById(resources.getId());
        ValidationUtil.isNull(userOptional,"User","id",resources.getId());

        User user = userOptional.get();

        /**
         * 根据实际需求修改
         */
        if(user.getId().equals(1L)){
            throw new BadRequestException("该账号不能被修改");
        }

        User user1 = userRepository.findByUsername(user.getUsername());
        User user2 = userRepository.findByEmail(user.getEmail());

        if(resources.getRoles() == null || resources.getRoles().size() == 0){
            throw new BadRequestException("角色不能为空");
        }
        //防止从名
        if(user1 !=null&&!user.getId().equals(user1.getId())){
            throw new EntityExistException(User.class,"username",resources.getUsername());
        }
        //防止邮箱重复
        if(user2!=null&&!user.getId().equals(user2.getId())){
            throw new EntityExistException(User.class,"email",resources.getEmail());
        }

        user.setUsername(resources.getUsername());
        user.setEmail(resources.getEmail());
        user.setEnabled(resources.getEnabled());
        user.setRoles(resources.getRoles());

        userRepository.save(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {

        /**
         * 根据实际需求修改
         */
        if(id.equals(1L)){
            throw new BadRequestException("该账号不能被删除");
        }
        userRepository.deleteById(id);
    }
    //通过邮箱或者名称查询用户
    @Override
    public User findByName(String userName) {
        User user = null;
        if(ValidationUtil.isEmail(userName)){
            user = userRepository.findByEmail(userName);
        } else {
            user = userRepository.findByUsername(userName);
        }

        if (user == null) {
            throw new EntityNotFoundException(User.class, "name", userName);
        } else {
            return user;
        }
    }
}
