package com.architect.all.spring.security.src.dao;

import com.architect.all.spring.security.src.domin.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author tx
 * @since 2019-03-25
 */
//@Mapper
public interface UserMapper {
    User findUser(String userId);
}
