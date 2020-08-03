package jrx.anyest.table.jpa.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * <p>
 *  描述
 *  为无法判断唯一性的数据指定唯一键
 * </p>
 *
 * @author lw
 * @since  2020/5/26 14:26
 */
@Entity
@Getter
@Setter
@Table(name = "table_mark_init")
public class TableMarkInit extends TableBaseEntity
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    protected Integer  id;

    /**
     *  表名称
     */
    @Column
    protected String tableName;
    /**
     *  表中文名称
     */
    @Column
    protected String tableChinaName;


}
