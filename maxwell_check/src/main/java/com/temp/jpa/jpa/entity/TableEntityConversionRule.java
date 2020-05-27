package com.temp.jpa.jpa.entity;


import com.temp.jpa.jpa.enums.*;
import com.temp.jpa.jpa.jpautil.LargeTextConverter;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * <p>
 *  描述
 * </p>
 *
 * @author lw
 * @since  2020/5/26 14:26
 */
@Entity
@Getter
@Setter
@Table(name = "table_entity_conversion_rule", indexes ={@Index(name = "idx_resource_id", columnList = "tableCode")})
public class TableEntityConversionRule extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    protected Integer  id;

    /**
     * 数据库表中文名称
     */
    @Column
    protected String tableName;
    /**
     * 数据库表名
     */
    @Column
    protected String tableCode;

    /**
     * 数据转换模型
     */
    @Column
    @Enumerated(EnumType.STRING)
    protected DataConversionModel dataConversionModel;

    /**
     * 转换数据对应的规则key
     */
    @Column
    protected String entityKey;

    /**
     * 替换默认值
     */
    @Column
    protected String entityValue;



}
