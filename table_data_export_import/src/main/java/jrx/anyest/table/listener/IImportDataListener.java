package jrx.anyest.table.listener;

import com.alibaba.fastjson.JSONObject;
import jrx.anyest.table.jpa.entity.TableImportSort;

import java.util.List;

/**
 * <p>
 * 描述
 * </p>
 *
 * @author LW
 * @since 2020/8/18  13:36
 */
public interface IImportDataListener {
    List<JSONObject> filterData(List<JSONObject> tableDatas, TableImportSort e);
}
