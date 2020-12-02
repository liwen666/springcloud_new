package jrx.data.hub.domin.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import jrx.anyest.table.config.TableEnvironmentConfig;
import jrx.anyest.table.config.TablePropertiesConfig;
import jrx.anyest.table.constant.TableConstants;
import jrx.anyest.table.exception.TableDataExportException;
import jrx.anyest.table.exception.TableDataImportException;
import jrx.anyest.table.jpa.dto.CodeCheck;
import jrx.anyest.table.jpa.dto.DataCheckResult;
import jrx.anyest.table.jpa.dto.ImportDataResult;
import jrx.anyest.table.jpa.dto.TableDataImportOrExpResult;
import jrx.anyest.table.jpa.entity.TableCodeConfig;
import jrx.anyest.table.service.TableDataCodeCacheManager;
import jrx.anyest.table.service.TableDataExpOrImpService;
import jrx.anyest.table.utils.DownUploadUtils;
import jrx.anyest.table.utils.MD5FileUtil;
import jrx.data.hub.domain.common.DataHubSecurityHolder;
import jrx.data.hub.domain.common.TenantIdProvider;
import jrx.data.hub.domain.constant.DataHubConstant;
import jrx.data.hub.domain.constant.TableNameConstant;
import jrx.data.hub.domain.enums.DataDetail;
import jrx.data.hub.domin.vo.BaseDetailInfo;
import jrx.data.hub.domin.vo.DataCheckResultEast;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * <p>
 * 描述
 * </p>
 *
 * @author lw
 * @since 2020/8/12 9:27
 */
@Service
@Slf4j
public class TableDownUploadService {
    public static Map<String, Object> lock = Maps.newConcurrentMap();
    public static Map<String, String> userNames = Maps.newConcurrentMap();

    @Autowired(required = false)
    private TableDataExpOrImpService tableDataExpOrImpService;
    @Autowired(required = false)
    private TablePropertiesConfig tablePropertiesConfig;
    @Autowired
    private TableEnvironmentConfig tableEnvironmentConfig;

    public List<TableDataImportOrExpResult<CodeCheck>> checkCode(Map map) {
        return tableDataExpOrImpService.checkCode(map);
    }

    public void exportData(Map<String, Set<Object>> outDataId, Map<String, Map<String, Map<String, Object>>> dataMap) {
        outDataId.forEach((k, v) -> tableDataExpOrImpService.listAllRelationData(k, v, null, dataMap, null));

    }

    public void initCodeCache() {
        Map<String, Object> mapParam = new HashMap<>();
        if (null == TenantIdProvider.getTenantId()) {
            throw new TableDataImportException("content_code can't be null");
        }
        mapParam.put("contentCode", TenantIdProvider.getTenantId());
        tableDataExpOrImpService.initCodeCache(mapParam);
    }

    public void conversionIdToCode(Map<String, Map<String, Map<String, Object>>> dataMap) {
        tableDataExpOrImpService.conversionIdToCode(dataMap);
    }

    public void downLoadData(HttpServletResponse response, String next, String fileName) {
        initCodeCache();
        Map<String, Map<String, Map<String, Object>>> data = TableDataExpOrImpService.tableDataExportCache.get(next);
        if (null == data) {
            throw new TableDataExportException("数据已过期，或已被下载!");
        }
        downLoadData(response, data, next, fileName);
        TableDataExpOrImpService.tableDataExportCache.remove(next);
    }

    public void downLoadData(HttpServletResponse response, Map<String, Map<String, Map<String, Object>>> dataMap, String next, String fileName) {
        Map<String, String> data = Maps.newTreeMap();
        String md5String = getSign(dataMap, next, data);
        data.put("sign", md5String);
        DownUploadUtils.downloadZip(response, data, fileName);
    }

    public String getSign(Map<String, Map<String, Map<String, Object>>> dataMap, String next, Map<String, String> data) {
        dataMap.forEach((k, v) -> {
            Set<String> set = v.keySet();
            for (String key : set) {
                data.put(k + TableConstants.SPLIT + key + TableConstants.CODE_SEPATATION + TableDataCodeCacheManager.idToCode.get(next).get(k + TableConstants.CODE_SEPATATION + key), JSON.toJSONString(v.get(key)));
            }
        });
        return MD5FileUtil.getMD5String(tablePropertiesConfig.getFileSign() + data.toString());
    }

    public DataCheckResult checkData(Map<String, String> stringStringMap) {
        return tableDataExpOrImpService.checkData(stringStringMap);
    }

    public void importData(String dataId, ImportDataResult importResult) {
        tableDataExpOrImpService.importData(dataId, importResult);
    }

    public void prepareData(String next, Map<String, Map<String, Map<String, Object>>> dataMap) {
        TableDataExpOrImpService.tableDataExportCache.put(next, dataMap);
    }

    public void rollback(String dataKey) {
        tableDataExpOrImpService.rollback(dataKey);
    }

    public String getSign(String toString) {
        return MD5FileUtil.getMD5String(tablePropertiesConfig.getFileSign() + toString);
    }

    public static synchronized boolean lock(String tenantId) {
        Object o = lock.get(DataHubConstant.TABLE_DATA_LOCK + tenantId);
        if (null == o) {
            lock.put(DataHubConstant.TABLE_DATA_LOCK + tenantId, DataHubConstant.TABLE_DATA_LOCK);
            userNames.put(tenantId, DataHubSecurityHolder.user().getNickName());
            return true;
        }
        return false;
    }

    public static synchronized void unlock(String tenantId) {
        lock.remove(DataHubConstant.TABLE_DATA_LOCK + tenantId);
    }

    public void addCode(String next, Map<String, Map<String, Object>> stringMapMap, String tableName) {
        TableCodeConfig tableCodeConfig = TableDataCodeCacheManager.tableCodeConfigs.get(tableName);
        String key = TableDataCodeCacheManager.tableKey.get(tableName);

        if (null != stringMapMap) {
            stringMapMap.values().forEach(e -> TableDataExpOrImpService.addCache(next, tableCodeConfig, key, tableCodeConfig.getColumns(), e));
        }
    }

    public void refresh() {
        tableEnvironmentConfig.initCacheData();
    }

    /**
     * 导出数据明细加工
     *
     * @param dataMap
     * @param dataCheckResultEast
     */
    public void setExportResourceDetail(Map<String, Map<String, Map<String, Object>>> dataMap, DataCheckResultEast dataCheckResultEast) {
        Map<DataDetail, List<? extends BaseDetailInfo>> detailMap = dataCheckResultEast.getDetailMap();
        ArrayList<BaseDetailInfo> objects = Lists.newArrayList();
        Map<String, Map<String, Object>> data = dataMap.get(TableNameConstant.META_JOB_INFO);
        getData(objects, data);
        detailMap.put(DataDetail.JOB, objects);
        dataCheckResultEast.setDetailMap(detailMap);
    }

    private void getData(ArrayList<BaseDetailInfo> objects, Map<String, Map<String, Object>> model) {
        if (!CollectionUtils.isEmpty(model)) {
            model.forEach((k, v) -> {
                toDetail(objects, v);
            });
        }
    }

    /**
     * 导入数据明细加工
     *
     * @param importDataMap
     * @param dataCheckResultEast
     */
    public void setImportResourceDetail(Map<String, List<JSONObject>> importDataMap, DataCheckResultEast dataCheckResultEast) {
        Map<DataDetail, List<? extends BaseDetailInfo>> detailMap = dataCheckResultEast.getDetailMap();
        //机构数据明细加工
        ArrayList<BaseDetailInfo> objects = Lists.newArrayList();
        List<JSONObject> data = importDataMap.get(TableNameConstant.META_JOB_INFO);
        getImportData(objects, data);
        detailMap.put(DataDetail.JOB, objects);
        dataCheckResultEast.setDetailMap(detailMap);
    }

    private void getImportData(ArrayList<BaseDetailInfo> objects, List<JSONObject> data) {
        if (!CollectionUtils.isEmpty(data)) {
            data.forEach(v -> {
                toDetail(objects, v);
            });
        }
    }

    private void toDetail(ArrayList<BaseDetailInfo> objects, Map v) {
        BaseDetailInfo baseDetailInfo = new BaseDetailInfo();
        objects.add(baseDetailInfo);
    }
}
