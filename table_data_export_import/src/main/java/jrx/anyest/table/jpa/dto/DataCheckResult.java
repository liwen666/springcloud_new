package jrx.anyest.table.jpa.dto;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

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
public class DataCheckResult {
    /**
     * 导入数据缓存key
     */
    private String uuidKey;
    /**
     * 版本迭代数据
     */
    private Map<String, List<JSONObject>> versionDataMap;
    /**
     * 新增加数据
     */
    private Map<String, List<JSONObject>> insertDataMap;
    /**
     * 需要更新的数据
     */
    private Map<String, List<JSONObject>> updateDataMap;
    /**
     * 需要导入的数据集合
     */
    private Map<String, List<JSONObject>> importDataMap;

}
