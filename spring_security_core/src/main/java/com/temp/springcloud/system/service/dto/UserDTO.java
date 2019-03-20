package com.temp.springcloud.system.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;

/**
 * @author lw
 * @date 2019.3.20
 */
@Data
public class UserDTO implements Serializable {

    private Long id;

    private String username;

    private String avatar;

    private String email;

    private Boolean enabled;
    /**
     * 序列化忽略掉这个属性
     */
    @JsonIgnore
    private String password;

    private Timestamp createTime;

    private Date lastPasswordResetTime;

//    private Set<RoleDTO> roles;
}
