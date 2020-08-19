package jrx.anyest.table.jpa.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * 导入数据表排序
 */
@Entity
@Getter
@Setter
@Table(name = "table_import_sort")
public class TableImportSort extends TableBaseEntity
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
     *  优先级
     *  数字越小越优先
     */
    @Column
    protected Integer orderId;

    /**
     *  如果标记
     *  主要正对字段筛选入库
     */
    @Column
    protected Integer flag;




}
