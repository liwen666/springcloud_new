package com.temp.springcloud.system.service.mapper;

import com.temp.springcloud.common.mapper.EntityMapper;
import com.temp.springcloud.system.domin.Permission;
import com.temp.springcloud.system.service.dto.PermissionDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author jie
 * @date 2018-11-23
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PermissionMapper extends EntityMapper<PermissionDTO, Permission> {

}
