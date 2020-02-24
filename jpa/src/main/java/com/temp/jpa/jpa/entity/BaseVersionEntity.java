package com.temp.jpa.jpa.entity;

import com.temp.jpa.jpa.jpautil.ResourceType;
import com.temp.jpa.jpa.jpautil.TimeConverter;
import com.temp.jpa.jpa.jpautil.VersionState;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * 基础版本信息实体类
 * @author yxy
 */
@MappedSuperclass
@Setter
@Getter
@ToString
public class BaseVersionEntity {


    /**版本状态 */
    @Column(length = 20)
    @Enumerated(EnumType.STRING)
    protected VersionState versionState;

    @Column
    protected Integer version;

    /** 版本标识唯一 */
    @Column
    protected String versionCode;

    /**关联资源主键*/
    @Column
    protected Integer resourceId;

    /**关联资源类型*/
    @Column(length = 20)
    @Enumerated(EnumType.STRING)
    protected ResourceType resourceType;

    /**
     * 机构标识
     */
    @Column(length = 50)
    protected String contentCode;

    /**
     * 创建时间
     */
    @Column
    @Convert(converter = TimeConverter.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    protected Date createTime;

    /**
     * 更新时间
     */
    @Column
    @Convert(converter = TimeConverter.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    protected Date updateTime;

    /**
     * 更新人
     */
    @Column(length = 50)
    protected String updatePerson;


}
