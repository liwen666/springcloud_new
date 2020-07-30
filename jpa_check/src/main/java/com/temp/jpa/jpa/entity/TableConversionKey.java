package com.temp.jpa.jpa.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

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
@Table(name = "table_conversion_key")
public class TableConversionKey
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    protected Integer  id;

    /**
     *  表名称
     */
    @Column
    protected String tableCodeName;
    /**
     *  表中文名称
     */
    @Column
    protected String tableCodeChinaName;


    /**
     *  表数据需要做转换的key
     */
    @Column
    protected String conversionKey;


    /**
     * 处理器类名称
     */
    @Column
    protected String handleBeanName;
    /**
     * 创建时间
     */
    @Column
    protected Date createTime;






}
