package jrx.anyest.table.jpa.entity;


import jrx.anyest.table.jpa.enums.HistoryDataType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * <p>
 *  描述 导入备份的历史数据，可以做数据回滚操作
 * </p>
 *
 * @author lw
 * @since  2020/5/26 14:26
 */
@Entity
@Getter
@Setter
@Table(name = "table_history_data")
public class TableHistoryData extends TableBaseEntity
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Integer  id;

    /**
     * 表名称
     */
    @Column
    private String tableName;

    @Column(length = 100)
    private String primaryTableChinaName;
    /**
     * 数据批次ID  全局唯一
     */
    @Column
    private String dataKey;

    /**
     * 数据内容
     */
    @Column
    @Lob
    private String data;

    /**
     * 数据id
     */
    @Column

    private String dataId;
    /**
     * 数据主键名称
     */
    @Column

    private String primaryKeyName;


    /**
     * 对数据的操作类型
     */
    @Column
    @Enumerated(EnumType.STRING)
    private HistoryDataType historyDataType;



}
