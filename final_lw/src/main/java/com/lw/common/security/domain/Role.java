package com.lw.common.security.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

/**
 * Description:    java类作用描述<br>
 * author:     lw
 * date:     2019/5/26 17:26
 * Version:        1.0
 */
@Getter
@Setter
public class Role implements Serializable {

    private Long id;

    @NotBlank
    private String name;

    private String remark;

    @JsonIgnore
    private Set<User> users;

    private Set<Permission> permissions;

    @JsonIgnore
    private Set<Menu> menus;

    private Timestamp createTime;

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", remark='" + remark + '\'' +
                ", createDateTime=" + createTime +
                '}';
    }
}
