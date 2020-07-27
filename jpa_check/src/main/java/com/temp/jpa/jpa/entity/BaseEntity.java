package com.temp.jpa.jpa.entity;

import com.temp.jpa.jpa.jpautil.TimeConverter;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * 通用基础属性
 * @author peidong.meng on 2018/9/23
 */
@MappedSuperclass
@Setter
@Getter
public class BaseEntity {

    /**
     * 机构标识
     */
    @Column(length = 50)
    protected String contentCode;

    /**
     * 创建人
     */
    @Column(length = 50)
    protected String createPerson;

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
