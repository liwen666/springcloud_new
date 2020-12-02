package jrx.data.hub.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import jrx.anyest.table.constant.TableConstants;
import jrx.anyest.table.exception.TableDataImportException;
import jrx.anyest.table.jpa.dto.*;
import jrx.anyest.table.service.TableDataCodeCacheManager;
import jrx.anyest.table.service.TableDataExpOrImpService;
import jrx.anyest.table.service.TablePropertiesThreadLocalHolder;
import jrx.anyest.table.utils.DownUploadUtils;
import jrx.anyest.table.utils.TableIdGenerator;
import jrx.data.hub.domain.common.TenantIdProvider;
import jrx.data.hub.domain.constant.TableNameConstant;
import jrx.data.hub.domain.enums.DataImportExportStatus;
import jrx.data.hub.domain.enums.ResourceType;
import jrx.data.hub.domin.service.TableDownUploadService;
import jrx.data.hub.domin.vo.DataCheckResultEast;
import jrx.data.hub.domin.vo.DownLoadModel;
import jrx.data.hub.domin.vo.ImportDataResultEast;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * <p>
 * 描述
 * </p>
 *
 * @author lw
 * @since 2020/8/11 19:06
 */
@RestController
@RequestMapping("/downUpload")
@Api(value = "导入导出操作接口")
public class TableDownUploadController {
    public static Logger logger = LoggerFactory.getLogger(TableDownUploadController.class);

    @Autowired
    private TableDownUploadService tableDownUploadService;

    private static String fileName = "";

    /**
     * 保存资源
     *
     * @return
     */
    @ApiOperation(value = "code检查")
    @GetMapping("/export/checkCode")
    public List<TableDataImportOrExpResult<CodeCheck>> checkCode() {
//         * 如果项目id为null 认为是项目外导出  否则认为是项目内导出
        String tenantId = TenantIdProvider.getTenantId();
        Map map = new HashMap();
        map.put("contentCode", tenantId);
        return tableDownUploadService.checkCode(map);
    }

    /**
     * 刷新导入导出配置缓存
     *
     * @return
     */
    @ApiOperation(value = "刷新导入导出配置缓存")
    @GetMapping("/import/refresh")
    public void refresh() {
        tableDownUploadService.refresh();
    }


    /**
     * 数据整理
     *
     * @param downLoadModels
     * @return
     */
    @ApiOperation(value = "数据整理")
    @PostMapping("/export/data")
    public DataCheckResultEast exportData(@ApiParam(value = "导出的所有数据", required = true) @RequestBody List<DownLoadModel> downLoadModels) {
        DataCheckResultEast dataCheckResultEast = new DataCheckResultEast();
        String next = TableIdGenerator.getNext() + "a";
        String sign;
        try {
            TablePropertiesThreadLocalHolder.addProperties(TableConstants.TABLE_CODE_UUID, next);
            tableDownUploadService.initCodeCache();

//             * 表名称和对应的id
            Map<String, Set<Object>> outDataId = Maps.newConcurrentMap();
            downLoadModels.stream().filter(e -> e.getResourceId() != null).forEach(e -> {
                ResourceType resourceType = e.getResourceType();
                switch (resourceType) {
                    case JOB_INFO:
                        if (outDataId.get(TableNameConstant.META_JOB_INFO) == null) {
                            outDataId.put(TableNameConstant.META_JOB_INFO, Sets.newConcurrentHashSet());
                            outDataId.get(TableNameConstant.META_JOB_INFO).add(e.getResourceId());
                        }
                        break;
                    default:
                        logger.error("接口暂不支持类型导出 ResourceType:{}", e.getResourceType());
                }
            });
            Map<String, Map<String, Map<String, Object>>> dataMap = Maps.newConcurrentMap();
            tableDownUploadService.exportData(outDataId, dataMap);
//            添加策略字段code到缓存里面
//            Map<String, Map<String, Object>> stringMapMap = dataMap.get(EngineAdminConstants.META_OBJECT_FIELD);
//            tableDownUploadService.addCode(next, stringMapMap, EngineAdminConstants.META_OBJECT_FIELD);
//            添加版本对象到缓存

            /**
             * 将数据进行code转换下载
             *
             */
            tableDownUploadService.conversionIdToCode(dataMap);
            tableDownUploadService.prepareData(next, dataMap);
            //数据明细加工
            tableDownUploadService.setExportResourceDetail(dataMap, dataCheckResultEast);
            TreeMap<String, String> comparableObjectTreeMap = Maps.newTreeMap();
            tableDownUploadService.initCodeCache();
            sign = tableDownUploadService.getSign(dataMap, next, comparableObjectTreeMap);
            dataCheckResultEast.setSign(sign);
            dataCheckResultEast.setDataKey(next);
            dataCheckResultEast.setDataImportExportStatus(DataImportExportStatus.SUCCESS);
        } catch (Exception e) {
            logger.error("ERROR", e);
            dataCheckResultEast.setDataImportExportStatus(DataImportExportStatus.FAIL);
            dataCheckResultEast.setErrorMsg(e.getMessage());
            return dataCheckResultEast;
        } finally {
            TableDataCodeCacheManager.idToCode.remove(TablePropertiesThreadLocalHolder.getProperties(TableConstants.TABLE_CODE_UUID));
            TableDataCodeCacheManager.codeToId.remove(TablePropertiesThreadLocalHolder.getProperties(TableConstants.TABLE_CODE_UUID));
            TablePropertiesThreadLocalHolder.remove(TableConstants.TABLE_CODE_UUID);
        }
        return dataCheckResultEast;
    }

    /**
     * 数据下载
     *
     * @return
     */
    @ApiOperation(value = "数据下载")
    @GetMapping("/export/data/download/{dataKey}/{fileName}")
    public void exportData(HttpServletResponse response, @PathVariable String dataKey, @Nullable @PathVariable String fileName) {
        TablePropertiesThreadLocalHolder.addProperties(TableConstants.TABLE_CODE_UUID, dataKey);
        logger.info("开始下载数据 dataKey:{}", dataKey);
        try {
            if (null == fileName) {
                fileName = "数据";
            }

            tableDownUploadService.downLoadData(response, dataKey, fileName + ".zip");
        } catch (Exception e) {
            logger.error("数据下载失败！", e);
            throw new TableDataImportException("数据下载失败！" + e.getMessage());
        } finally {
            TableDataCodeCacheManager.idToCode.remove(TablePropertiesThreadLocalHolder.getProperties(TableConstants.TABLE_CODE_UUID));
            TableDataCodeCacheManager.codeToId.remove(TablePropertiesThreadLocalHolder.getProperties(TableConstants.TABLE_CODE_UUID));
            TablePropertiesThreadLocalHolder.remove(TableConstants.TABLE_CODE_UUID);
        }
    }


    /**
     * 导入检查
     *
     * @return
     */
    @ApiOperation(value = "导入检查")
    @PostMapping("/import/check}")
    public DataCheckResultEast importDataCheck(@RequestParam("file") MultipartFile file) {
        DataCheckResultEast dataCheckResultEast = new DataCheckResultEast();
//         * 判断文件格式是否合法
        String name = file.getOriginalFilename();
        fileName = name;

        if (!name.endsWith(".zip")) {
            logger.error("上传文件名称不合法，请以.zip结尾");
            dataCheckResultEast.setDataImportExportStatus(DataImportExportStatus.FAIL);
            dataCheckResultEast.setErrorMsg("上传文件名称不合法，请以.zip结尾");
            return dataCheckResultEast;
        }
        DataCheckResult dataCheckResult;
        try {
            String next = TableIdGenerator.getNext() + "a";
            TablePropertiesThreadLocalHolder.addProperties(TableConstants.TABLE_CODE_UUID, next);
            tableDownUploadService.initCodeCache();
            Map<String, String> stringStringMap = DownUploadUtils.importData(file.getInputStream());
            String sign = stringStringMap.get("sign");
            stringStringMap.remove("sign");
            String md5String = tableDownUploadService.getSign(stringStringMap.toString());

            if (!sign.equals(md5String)) {
                throw new TableDataImportException("数据包异常，MD5检验数据失败！");
            }
            dataCheckResult = tableDownUploadService.checkData(stringStringMap);
            tableDownUploadService.setImportResourceDetail(dataCheckResult.getImportDataMap(), dataCheckResultEast);

            dataCheckResultEast.setDataImportExportStatus(DataImportExportStatus.SUCCESS);
            dataCheckResultEast.setDataKey(next);
//            dataCheckResultEast.setDataCheckResult(dataCheckResult);
            dataCheckResultEast.setSign(sign);
        } catch (Exception e) {
            logger.error("数据存在异常！", e);
            dataCheckResultEast.setDataImportExportStatus(DataImportExportStatus.FAIL);
            dataCheckResultEast.setErrorMsg("数据存在异常！" + e.getMessage());
        } finally {
            TableDataCodeCacheManager.idToCode.remove(TablePropertiesThreadLocalHolder.getProperties(TableConstants.TABLE_CODE_UUID));
            TableDataCodeCacheManager.codeToId.remove(TablePropertiesThreadLocalHolder.getProperties(TableConstants.TABLE_CODE_UUID));
            TablePropertiesThreadLocalHolder.remove(TableConstants.TABLE_CODE_UUID);
        }
        return dataCheckResultEast;
    }

    /**
     * 执行导入
     *
     * @return
     */
    @ApiOperation(value = "执行导入")
    @GetMapping("/import/data/{dataId}/{importType}")
    public ImportDataResultEast importData(@PathVariable String dataId, @PathVariable String importType) {
        String tenantId = TenantIdProvider.getTenantId();
        logger.info("执行导入  dataKey:{}", dataId);
        ImportDataResultEast importDataResultEast = new ImportDataResultEast();
        ImportDataResult importResult = null;
//        导入不能并发执行
        boolean lock = tableDownUploadService.lock(tenantId);
        if (!lock) {
            importDataResultEast.setDataImportExportStatus(DataImportExportStatus.FAIL);
            importDataResultEast.setMsg("租户" + tenantId + "有其他导入操作正在进行中，请稍后再试！");
            return importDataResultEast;
        }

        try {
            TablePropertiesThreadLocalHolder.addProperties(TableConstants.TABLE_CODE_UUID, dataId);
            tableDownUploadService.initCodeCache();
            importResult = new ImportDataResult();
            //添加导入中断监控
            TableDataExpOrImpService.monitor.put(dataId, true);
            tableDownUploadService.importData(dataId, importResult);
            logger.info("--------------导入成功------------------");
            importDataResultEast.setImportDataResult(importResult);
        } catch (Exception e) {
            logger.error("导入失败", e);
            List<ImportData> importData = importResult.getImportData();
            importResult.setResult(false);
            for (ImportData importData1 : importData) {
                if (importData1.getErrorNum() != 0) {
                    logger.error("[---导入失败数据---]tableName:{},errorMsg:{}", importData1.getTableName(), JSON.toJSONString(importData1.getErrorData()));
                }
            }
            importDataResultEast.setImportDataResult(importResult);
            importDataResultEast.setMsg(e.getMessage());
        } finally {
            TableDataCodeCacheManager.idToCode.remove(dataId);
            TableDataCodeCacheManager.codeToId.remove(dataId);
            TableDataExpOrImpService.monitor.remove(dataId);
            TablePropertiesThreadLocalHolder.remove(TableConstants.TABLE_CODE_UUID);
            TableDataExpOrImpService.tableDataCache.remove(dataId);
            tableDownUploadService.unlock(tenantId);
        }
        if (importResult.isResult()) {
            importDataResultEast.setDataImportExportStatus(DataImportExportStatus.SUCCESS);
        } else {
            importDataResultEast.setDataImportExportStatus(DataImportExportStatus.FAIL);
        }
        return importDataResultEast;
    }

    /**
     * 执行导入
     *
     * @return
     */
    @ApiOperation(value = "执行回滚")
    @GetMapping("/import/rollback/{dataKey}")
    public void rollback(@PathVariable String dataKey) {
        tableDownUploadService.rollback(dataKey);

    }

    /**
     * 执行导入
     *
     * @return
     */
    @ApiOperation(value = "中断导入")
    @GetMapping("/import/break/{dataKey}")
    public void breakImport(@PathVariable String dataKey) {
        logger.info("取消 datakey:" + dataKey);
        if (TableDataExpOrImpService.monitor.get(dataKey) != null) {
            TableDataExpOrImpService.monitor.put(dataKey, false);
        }
    }
}
