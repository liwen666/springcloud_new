package com.lw.common.security.domain;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;
import java.util.Set;
/**
* Description:    java类作用描述<br>
* author:     lw
* date:     2019/5/26 17:32
* Version:        1.0
*/
@Getter
@Setter
public class Menu {

    private Long id;

    @NotBlank
    private String name;

    private Long soft;

    private String path;

    private String component;

    private String icon;

    /**
     * 上级菜单ID
     */
    private Long pid;

    /**
     * 是否为外链 true/false
     */
    private Boolean iFrame;

    private Set<Role> roles;

    private Timestamp createTime;
}
