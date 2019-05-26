package com.lw.common.security.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

/**
* Description:    java类作用描述<br>
* author:     lw
* date:     2019/5/26 17:33
* Version:        1.0
*/
@Getter
@Setter
public class Permission implements Serializable{

	private Long id;

	@NotBlank
	private String name;

	/**
	 * 上级类目
	 */
	@NotNull
	private Long pid;

	@NotBlank
	private String alias;

	@JsonIgnore
	private Set<Role> roles;

	private Timestamp createTime;

	@Override
	public String toString() {
		return "Permission{" +
				"id=" + id +
				", name='" + name + '\'' +
				", pid=" + pid +
				", alias='" + alias + '\'' +
				", createTime=" + createTime +
				'}';
	}
}
