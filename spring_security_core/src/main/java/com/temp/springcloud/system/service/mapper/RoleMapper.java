package com.temp.springcloud.system.service.mapper;

import com.temp.springcloud.common.mapper.EntityMapper;
import com.temp.springcloud.system.domin.Role;
import com.temp.springcloud.system.service.dto.RoleDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author jie
 * @date 2018-11-23
 */
@Mapper(componentModel = "spring", uses = {PermissionMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleMapper extends EntityMapper<RoleDTO, Role> {

}
