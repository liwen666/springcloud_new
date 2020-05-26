package com.temp.jpa.jpa.entity;


import com.temp.jpa.jpa.enums.ComputePeriod;
import com.temp.jpa.jpa.enums.FieldState;
import com.temp.jpa.jpa.enums.ObjectType;
import com.temp.jpa.jpa.jpautil.LargeTextConverter;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * 对象字段(包含业务字段、统计字段、公式字段、条件字段、正则字段、函数字段)
 * 所有衍生信息存放在一个字段中，通过model进行转换存储
 * @author peidong.meng on 2018/9/23
 */
@Entity
@Getter
@Setter
@Table(name = "meta_object_field", indexes ={@Index(name = "idx_resource_id", columnList = "resourceObjectId")})
public class ObjectFieldEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    protected Integer objectFieldId;

    /**
     * 对象资源主键id
     */
    @Column
    protected Integer resourceObjectId;
    /**
     * 对象资源版本主键id(创建与此版本，但未必仅隶属与此版本)
     */
    @Column
    protected Integer resourceObjectVersionId;

    /**
     * 数据集版本id
     * 约束字段和活动统计字段的resourceObjectVersionId为活动版本id，resourceObjectId为活动id
     * 因此引用的数据集需要额外字段来记录
     */
    @Column
    protected Integer dataObjectId;

    /**
     * 字段所属分类id
     * 实体模型统计模型字段按标签分类，分页
     */
    @Column
    protected Integer resourceObjectCategoryId;

    /**
     * 字段名称
     */
    @Column(length = 100)
    protected String fieldName;

    /**
     * 字段标识
     */
    @Column(length = 500)
    protected String fieldCode;

    /**
     * 列标识
     */
    @Column(length = 500)
    protected String columnCode;

    /**
     * 字段类别
     * @see FieldType
     */
    @Column(length = 20)
    @Enumerated(EnumType.STRING)
    protected FieldType fieldType;
    /**
     * 字段值类型
     * @see ValueType
     */
    @Column(length = 20)
    @Enumerated(EnumType.STRING)
    protected ValueType valueType;

    /**
     * 更新方式
     * @see ValueType
     */
    @Column(length = 100)
    @Enumerated(EnumType.STRING)
    protected UpdateMode updateMode = UpdateMode.OVERWRITE_ALL;


    /**
     * 最大长度
     */
    @Column
    protected Integer fieldLength;


    @Column
    protected Integer scaleLength;

    /**
     * 字段描述
     */
    @Column(length = 500)
    protected String description;

    /**
     * 字段默认值
     */
    @Column(length =200)
    protected String defaultValue;

    /**
     * 字段格式
     */
    @Column(length = 200)
    protected String fieldFormat;

    @Column
    protected Boolean isDimension = false;

    @Column
    protected Boolean isTarget = false;

    @Column
    protected Boolean isKey = false;

    /**
     * 字段状态,默认活跃状态
     * @see FieldState
     */
    @Column(length = 30)
    @Enumerated(EnumType.STRING)
    private FieldState fieldState = FieldState.ACTIVE;

    /**
     * 引用该字段版本号集合，逗号分开
     */
    @Column(length = 100)
    private String objectVersions;

    /**
     * 衍生内容（根据fieldType存储不同类型的衍生内容，格式参考model中对于类型字段定义）
     */
    @Column
    @Convert(converter = LargeTextConverter.class)
    private String deriveContent;

    /**
     * 列表类型字段中值类型
     * @see ValueType
     */
    @Column(length = 20)
    @Enumerated(EnumType.STRING)
    private ValueType listValueType = ValueType.STRING;

    /**
     * 隶属对象类型
     */
    @Column(length = 20)
    @Enumerated(EnumType.STRING)
    private ObjectType objectType;


    /**引用字段的主键id，用逗号分隔，如：1,2,3,4,5 */
    @Column(length = 500)
    protected String referFieldIds;

    /**来源字段主键id,将其他基础对象中的字段加入新的对象时，新建的字段和选择原字段的关联主键 */
    @Column
    private Integer sourceFieldId;

    /**
     * 计算周期
     * @see ComputePeriod
     */
    @Column(length = 30)
    @Enumerated(EnumType.STRING)
    private ComputePeriod computePeriod = ComputePeriod.NONE;


    /***
     * sql 片段 字段
     */
    @Column(length = 1000)
    private String sqlFragment;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ObjectFieldEntity{");
        sb.append("objectFieldId=").append(objectFieldId);
        sb.append(", resourceObjectId=").append(resourceObjectId);
        sb.append(", resourceObjectVersionId=").append(resourceObjectVersionId);
        sb.append(", fieldName='").append(fieldName).append('\'');
        sb.append(", fieldCode='").append(fieldCode).append('\'');
        sb.append(", fieldType=").append(fieldType);
        sb.append(", valueType=").append(valueType);
        sb.append(", fieldLength=").append(fieldLength);
        sb.append(", description='").append(description).append('\'');
        sb.append(", defaultValue='").append(defaultValue).append('\'');
        sb.append(", fieldFormat='").append(fieldFormat).append('\'');
        sb.append(", deriveContent='").append(deriveContent).append('\'');
        sb.append(", listValueType=").append(listValueType);
        sb.append(", objectType=").append(objectType);
        sb.append(", referFieldIds='").append(referFieldIds).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public String getBid() {
        if (fieldType != null && objectFieldId != null) {
            return fieldType.getIndex() + objectFieldId;
        }
        return null;
    }
}
