package com.temp.jpa.jpa.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

/**
 * @author xinre
 * @date 2019/5/25 13:59
 */
@Entity
@Table(name = "hq_portal_notic")
public class Notic {

    /*

    id                 VARCHAR2(64),
    title              VARCHAR2(255),
    createuserid       VARCHAR2(64),
    province           VARCHAR2(64),
    appid              VARCHAR2(64),
    fileserverid       VARCHAR2(64),
    type               VARCHAR2(64),
    subtype            VARCHAR2(64),

    createtime         TIMESTAMP(6),
    updatetime         TIMESTAMP(6),
    publishtime        TIMESTAMP(6),

    state              CHAR(2),
    publishlocation    CHAR(6),
    content            CLOB,

    orderid            NUMBER,
    views              NUMBER,
    downloads          NUMBER,

    remark             VARCHAR2(1000)

    */



    @Id
    private String id;
    @Column(length = 500)
    private String title;
    private String code;

    public List<ResFile> getResFiles() {
        return resFiles;
    }

    public void setResFiles(List<ResFile> resFiles) {
        this.resFiles = resFiles;
    }

    @OneToMany(mappedBy = "notic", cascade = { CascadeType.ALL })
    @OrderBy("orderNO asc")
    private List<ResFile> resFiles;
    public String getCreateUserIdName() {
        return createUserIdName;
    }

    public void setCreateUserIdName(String createUserIdName) {
        this.createUserIdName = createUserIdName;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    @Column(name = "createuseridname")
    private String createUserIdName;
    @Column(name = "createuserid")
    private String createUserId;
    /**
     *区划名称
     */
    @Column(name = "provincename")
    private String provinceName;
    private String province;
    @Column(name = "appid")
    private String appId;
    @Column(name = "fileserverid")
    private String fileServerId;
    private String type;
    @Column(name = "subtype")
    private String subType;

    @Column(name = "cratetime")
    private Date crateTime;
    @Column(name = "updatetime")
    private Date updateTime;
    @Column(name = "publishtime")
    private Date publishTime;

    @Column(name = "publishlocation")
    private String publishLocation;

    private String state;
    @Lob
    @Column(name = "content")
    private String content;

    @Column(name = "orderid")
    private int orderId;
    private int views;
    private int downloads;

    @Column(length = 1000)
    private String remark;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getFileServerId() {
        return fileServerId;
    }

    public void setFileServerId(String fileServerId) {
        this.fileServerId = fileServerId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }

    public Date getCrateTime() {
        return crateTime;
    }

    public void setCrateTime(Date crateTime) {
        this.crateTime = crateTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public String getPublishLocation() {
        return publishLocation;
    }

    public void setPublishLocation(String publishLocation) {
        this.publishLocation = publishLocation;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public int getDownloads() {
        return downloads;
    }

    public void setDownloads(int downloads) {
        this.downloads = downloads;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCode() {
        
        return code;
    }
}
