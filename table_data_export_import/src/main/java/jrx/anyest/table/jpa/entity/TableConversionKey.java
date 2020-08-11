package jrx.anyest.table.jpa.entity;


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
public class TableConversionKey extends TableBaseEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Integer  id;

    /**
     *  表名称
     */
    @Column
    private String tableCodeName;
    /**
     *  表中文名称
     */
    @Column
    private String tableCodeChinaName;


    /**
     *  表数据需要做转换的key
     */
    @Column
    private String conversionKey;


    /**
     * 处理器类名称
     */
    @Column
    private String handleBeanName;

    /**
     * 数据转换key 是否是一个版本对象
     */

    @Column
    private boolean isJsonObject = false;


    /**
     * 是否是多级引用
     * 如果是多级引用用需要 其他表数据全部处理完才去处理这个
     */
    @Column
    private boolean isMultipleRelation = false;
















}
