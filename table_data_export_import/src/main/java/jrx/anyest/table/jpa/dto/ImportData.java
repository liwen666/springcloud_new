package jrx.anyest.table.jpa.dto;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;


/**
 * <p>
 * 导入明细
 * </p>
 *
 * @author lw
 * @since 2020/8/5 16:16
 */
@Setter
@Getter
public class ImportData {
    /**
     * 表名称
     */
    private String tableName;
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

    private Map<String, String> errorData;

    /**
     * 错误对象列表
     */
    private List<JSONObject> errorObj;

}
