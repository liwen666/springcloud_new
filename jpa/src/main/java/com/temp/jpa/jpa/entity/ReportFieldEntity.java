package com.temp.jpa.jpa.entity;

import com.temp.jpa.jpa.enums.ReportEnum;
import com.temp.jpa.jpa.jpautil.LargeTextConverter;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;

/**
 * 数据对象版本
 *
 * @author peidong.meng
 * @date 2018/9/27
 */
@Entity
@Getter
@Setter
@Table(name = "ins_report_field", indexes = {@Index(name = "idx_resource_id", columnList = "resourceId"), @Index(name = "idx_version_state", columnList = "versionState")})
public class ReportFieldEntity extends BaseVersionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer reportFieldId;

    /**
     * 是否被占用
     */
    @Column
    private Boolean used = false;

    /**
     * 字段名称
     */
    @Column
    private String fieldName;

    /**
     * 字段处理类型
     */
    @Column
    private ReportEnum sqlType;


    /**
     * 字段类型
     */
    @Column
    private ReportEnum modelType;


    /**
     * 字段配置信息
     */
    @Column
    @Convert(converter = LargeTextConverter.class)
    private String configs;

    public Boolean getUsed() {
        if (null == used) {
            return false;
        }

        return used;
    }
}
