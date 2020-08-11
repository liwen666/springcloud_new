package jrx.anyest.table.jpa.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * <p>
 *  描述 参数配置表
 * </p>
 * 注意表的字段的关键字，否则无法创建表
 *
 * @author lw
 * @since  2020/5/26 14:26
 */
@Entity
@Getter
@Setter
@Table(name = "table_param_config")
public class TableParamConfig extends TableBaseEntity
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
     *  列名
     */
    @Column
    protected String keyColumn;
    /**
     *  版本列名
     */
    @Column
    protected String versionColumn;

    /**
     *  resource 列明
     */
    @Column
    protected String resourceIdColumn;

    /**
     *  描述信息
     */
    @Column
    protected String tableDescribe;








}
