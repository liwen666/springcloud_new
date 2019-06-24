package com.temp.springcloud.environment.service;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @author xinre
 * @date 2019/5/25 13:59
 */
@Entity
@Table(name = "hq_portal_notic_type")
public class AnnounceType implements Serializable {

	private static final long serialVersionUID = 1L;

    @Id
    private String id;
    
    @Column(length = 64)
    private String pid;
    
    @Column(length = 500)
    private String name;
    
    @Column(name = "userid",length = 64)
    private String userId;
    
    @Column(name = "state",length = 64)
    private String state;
    
    @Column(name = "type",length = 64)
    private String type;
    
    @Column(name = "cratetime")
    private Date crateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getCrateTime() {
        return crateTime;
    }

    public void setCrateTime(Date crateTime) {
        this.crateTime = crateTime;
    }
}