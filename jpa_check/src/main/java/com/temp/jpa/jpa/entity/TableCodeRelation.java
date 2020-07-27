package com.temp.jpa.jpa.entity;


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
@Table(name = "table_code_relation")
public class TableCodeRelation extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    protected Integer  id;

    /**
     * 数据库主表名
     */
    @Column
    protected String primaryTableName;
    /**
     * 数据库主表名
     */
    @Column(length = 100)
    protected String primaryTableChinaName;
    /**
     * 数据库表名
     */
    @Column
    protected String primaryCodeKey;

    /**
     * 数据库从表名
     */
    @Column(length = 100)
    protected String slaveTableName;
    /**
     * 数据库从表名
     */
    @Column
    protected String slaveTableChinaName;




}
