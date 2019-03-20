package com.temp.springcloud.system.service.mapper;

import com.temp.springcloud.common.mapper.EntityMapper;
import com.temp.springcloud.system.domin.User;
import com.temp.springcloud.system.service.dto.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author lw
 * @date 2019.3.20
 */
@Mapper(componentModel = "spring",uses = {RoleMapper.class},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper extends EntityMapper<UserDTO, User> {

}
