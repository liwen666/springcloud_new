package jrx.anyest.table.jpa.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 描述 code检查结果集
 * </p>
 *
 * @author lw
 * @since 2019/5/26 23:40
 */
@Getter
@Setter
public class CodeCheck {
    /**
     * code列名
     */
    private String code;
    /**
     * 错误数据内容
     */
    private Object data;
    /**
     * 错误数据数量
     */
    private Integer num;
    /**
     * code的查询sql
     */
    private String codeSql;
}
