package jrx.data.hub.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import jrx.anyest.table.constant.TableConstants;
import jrx.anyest.table.exception.TableDataImportException;
import jrx.anyest.table.jpa.dto.DataCheckResult;
import jrx.anyest.table.jpa.dto.ImportData;
import jrx.anyest.table.jpa.dto.ImportDataResult;
import jrx.anyest.table.service.TableDataExpOrImpService;
import jrx.anyest.table.service.TablePropertiesThreadLocalHolder;
import jrx.anyest.table.utils.DownUploadUtils;
import jrx.anyest.table.utils.MD5FileUtil;
import jrx.anyest.table.utils.TableIdGenerator;
import jrx.data.hub.AnyDataHubApplication;
import jrx.data.hub.domain.common.TenantIdProvider;
import jrx.data.hub.domain.constant.DataHubConstant;
import jrx.data.hub.domain.enums.ResourceType;
import jrx.data.hub.domin.service.TableDownUploadService;
import jrx.data.hub.domin.vo.DataCheckResultEast;
import jrx.data.hub.domin.vo.DownLoadModel;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 导入导出验证
 */
@Slf4j
@ActiveProfiles("local_lw")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AnyDataHubApplication.class)
public class TableDownUploadControllerTest {
    @Autowired
    private TableDownUploadController tableDownUploadController;
    @Autowired
    private TableDownUploadService tableDownUploadService;
    @Autowired
    private TableDataExpOrImpService tableDataExpOrImpService;

    @Test
    public void export() throws FileNotFoundException {
        TenantIdProvider.setTenantId(DataHubConstant.TEST_TENANT);
        List<DownLoadModel> downList = Lists.newArrayList();
        DownLoadModel downLoadModel = new DownLoadModel();
        downLoadModel.setResourceId("123");
        downLoadModel.setResourceType(ResourceType.JOB_INFO);
        downList.add(downLoadModel);
        DataCheckResultEast dataCheckResultEast = tableDownUploadController.exportData(downList);
        System.out.println(JSON.toJSONString(dataCheckResultEast));
        File file = new File("D:\\workspace\\webspace\\any-data-hub\\any-data-hub\\src\\test\\java\\jrx\\data\\hub\\controller\\" + "测试数据.zip");
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        String next = dataCheckResultEast.getDataKey();
        Map<String, Map<String, Map<String, Object>>> data = TableDataExpOrImpService.tableDataExportCache.get(next);
        TablePropertiesThreadLocalHolder.addProperties(TableConstants.TABLE_CODE_UUID, next);
        TreeMap<String, String> comparableObjectTreeMap = Maps.newTreeMap();
        tableDownUploadService.initCodeCache();
        String sign = tableDownUploadService.getSign(data, next, comparableObjectTreeMap);
        comparableObjectTreeMap.put("sign", sign);
        DownUploadUtils.expData(fileOutputStream, comparableObjectTreeMap);
    }

    @Test
    public void importData() throws FileNotFoundException {
        TenantIdProvider.setTenantId(DataHubConstant.TEST_TENANT);
        String next = TableIdGenerator.getNext();
        TablePropertiesThreadLocalHolder.addProperties(TableConstants.TABLE_CODE_UUID, next);
        Map<String, Object> mapParam = new HashMap();
        mapParam.put("contentCode", DataHubConstant.TEST_TENANT);
        tableDataExpOrImpService.initCodeCache(mapParam);
        File file = new File("D:\\workspace\\webspace\\any-data-hub\\any-data-hub\\src\\test\\java\\jrx\\data\\hub\\controller\\测试数据.zip");
        FileInputStream fileInputStream = new FileInputStream(file);
        Map<String, String> stringStringMap = DownUploadUtils.importData(fileInputStream);
        String sign = stringStringMap.get("sign");
        stringStringMap.remove("sign");
        String md5String = MD5FileUtil.getMD5String("any_data_hub_sign" + stringStringMap.toString());
        if (!sign.equals(md5String)) {
            throw new TableDataImportException("数据包异常，MD5检验数据失败！");
        }
        DataCheckResult dataCheckResult = tableDataExpOrImpService.checkData(stringStringMap);
        String tableCodeUuid = TablePropertiesThreadLocalHolder.getProperties(TableConstants.TABLE_CODE_UUID);
        tableDataExpOrImpService.initCodeCache(mapParam);
        ImportDataResult importResult = new ImportDataResult();

        try {
            tableDataExpOrImpService.importData(tableCodeUuid, importResult);
            System.out.println("--------------导入成功------------------");
        } catch (Exception e) {
            List<ImportData> importData = importResult.getImportData();
            for (ImportData importData1 : importData) {
                if (importData1.getErrorNum() != 0) {
                    log.error("[---导入失败数据---]tableName:{},errorMsg:{}", importData1.getTableName(), JSON.toJSONString(importData1.getErrorData()));
                }
            }
        }
        System.out.println(JSON.toJSONString(importResult));
    }
}