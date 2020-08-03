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
@Table(name = "table_code_config")
public class TableCodeConfig extends TableBaseEntity
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
     * 用于计算code的列名称
     * 如果是多个字段用 | 分割
     */
    @Column(length = 200)
    protected String columns;

    /**
     * 处理器类名称
     */
    @Column
    protected String handleBeanName;

    /**
     * 排除的数据列名称
     */
    @Column
    protected String ignoreColumnName;
    /**
     *  排除的数据列值
     */
    @Column
    protected String ignoreColumnValue;


//    /**
//     * 数据转换模型
//     */
//    @Column
//    @Enumerated(EnumType.STRING)
//    protected DataConversionModel dataConversionModel;





}
