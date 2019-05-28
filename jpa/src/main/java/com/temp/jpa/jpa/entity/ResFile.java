package com.temp.jpa.jpa.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Portlet需要的资源文件
 * 
 * @author JACK
 *
 */
@Entity
@Table(name = "hq_portal_resfile")
public class ResFile implements Serializable {

	private static final long serialVersionUID = 1L;

	public final static String CSS = "css";
	public final static String JS = "js";

	@Id
	private String id;

	private String path;

	/**
	 * 表示当前为js还是css文件
	 */
	private String type;

	public static String getCSS() {
		return CSS;
	}

	/**
	 * 表示是否为第三方资源
	 */

	private boolean third;

	private int orderNO;
	@ManyToOne(cascade = { CascadeType.REFRESH }, optional = true)
	@JoinColumn(name = "notic_id")
	@JsonIgnore
	private Notic notic;



	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isThird() {
		return third;
	}

	public void setThird(boolean third) {
		this.third = third;
	}

	public int getOrderNO() {
		return orderNO;
	}

	public void setOrderNO(int orderNO) {
		this.orderNO = orderNO;
	}

}
