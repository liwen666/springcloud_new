package jrx.data.hub.domin.listener;

import com.alibaba.fastjson.JSONObject;
import jrx.anyest.table.jpa.entity.TableImportSort;
import jrx.anyest.table.listener.IImportDataListener;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 描述
 * </p>
 *
 * @author lw
 * @since 2019/5/26 23:40
 */
@Service
public class ImportDataListener implements IImportDataListener {

    /**
     * 对数据做分类筛选多次入库
     *
     * @param tableData
     * @param e
     * @return
     */
    @Override
    public List<JSONObject> filterData(List<JSONObject> tableData, TableImportSort e) {
        List<JSONObject> collect = tableData;

//        if (!CollectionUtils.isEmpty(tableData)&&e.getTableCodeName().equals(EngineAdminConstants.META_OBJECT_FIELD) && e.getFlag() == 1) {
//            //1 表时字段表
//            collect = tableData.stream().filter(d -> d.get("object_type").equals("TOPIC") || d.get("object_type").equals("STAT")||d.get("object_type").equals("STRATEGY_FIELD")||d.get("object_type").equals("NODE_FEATURE")).collect(Collectors.toList());
//        } else if (!CollectionUtils.isEmpty(tableData)&&e.getTableCodeName().equals(EngineAdminConstants.META_OBJECT_FIELD) && e.getFlag() == 0) {
//            collect = tableData.stream().filter(d -> !d.get("object_type").equals("TOPIC") && !d.get("object_type").equals("STAT")&&!d.get("object_type").equals("STRATEGY_FIELD")&&!d.get("object_type").equals("NODE_FEATURE")).collect(Collectors.toList());
//        }
        return collect;
    }
}
