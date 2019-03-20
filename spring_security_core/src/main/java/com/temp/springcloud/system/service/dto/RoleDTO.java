package com.temp.springcloud.system.service.dto;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

/**
 * @author lw
 * @date 2019.3.20
 */
@Data
public class RoleDTO implements Serializable {

    private Long id;

    private String name;

    private String remark;

    private Set<PermissionDTO> permissions;

    private Timestamp createTime;

}
