package jrx.anyest.table.jpa.dto;

import java.util.Map;

/**
 * <p>
 * 描述 code检查结果集
 * </p>
 *
 * @author lw
 * @since 2019/5/26 23:40
 */
public class ImportDataResult {
    /**
     * 导入成功的数据数量
     */
    private Integer successNum;
    /**
     * 错误数据数量
     */
    private Integer errorNum;
    /**
     * 错误消息
     * key为原始数据的id
     * value 为错误提示消息
     */
    private Map<String,String> errorData;
}
