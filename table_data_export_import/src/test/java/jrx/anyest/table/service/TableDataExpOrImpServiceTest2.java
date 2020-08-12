package jrx.anyest.table.service;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import jrx.anyest.table.TableApplicationStart;
import jrx.anyest.table.constant.TableConstants;
import jrx.anyest.table.exception.TableDataImportException;
import jrx.anyest.table.jpa.dto.*;
import jrx.anyest.table.utils.DownUploadUtils;
import jrx.anyest.table.utils.MD5FileUtil;
import jrx.anyest.table.utils.TableIdGenerator;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TableApplicationStart.class)
@Slf4j
public class TableDataExpOrImpServiceTest2 {
    @Autowired
    private TableDataExpOrImpService tableDataExpOrImpService;


    @Before
    public void name() throws FileNotFoundException {
        initRelationCache();
        initTableSort();
    }

    private void initTableSort() {
        //        初始化code缓存信息
        tableDataExpOrImpService.initTableSort();
    }

    /**
     * 为无法判断唯一性的数据设置唯一键
     *
     * @throws FileNotFoundException
     */

    @Test
    public void initMark() {
//       初始化唯一标记
        tableDataExpOrImpService.initTableMark();

    }


    @Test
    public void checkCode() throws FileNotFoundException {
        /**
         * 数据code检查
         */
        Map map = new HashMap();
        map.put("projectId", 13784);
        map.put("contentCode", 2);
        List<TableDataImportOrExpResult<CodeCheck>> tableDataImportOrExpResult = tableDataExpOrImpService.checkCode(map);
//        log.error(JSON.toJSONString(tableDataImportOrExpResult
        System.out.println(JSON.toJSONString(tableDataImportOrExpResult));
    }

    @Test
    public void initCodeCache() {
        /**
         * 初始化code缓存信息
         */
        Map<String, Object> map = new HashMap();
        map.put("projectId", 335);
        map.put("contentCode", 15029);
        String next = TableIdGenerator.getNext();
        try {
            TablePropertiesThreadLocalHolder.addProperties("table_code_uuid", next);
            tableDataExpOrImpService.initCodeCache(map);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        } finally {
            TableDataCodeCacheManager.idToCode.remove(TablePropertiesThreadLocalHolder.getProperties("table_code_uuid"));
            TableDataCodeCacheManager.codeToId.remove(TablePropertiesThreadLocalHolder.getProperties("table_code_uuid"));
            TablePropertiesThreadLocalHolder.remove("table_code_uuid");
        }


    }


    @Test
    public void initRelationCache() throws FileNotFoundException {
//        初始化code缓存信息
        tableDataExpOrImpService.initRelationCache();

    }


    /**
     * 递归查询所有关联数据
     *
     * 暂时都只支持最新版本导出，如果需要指定版本，有时间再做
     *
     * @throws FileNotFoundException
     */
    @Test
    public void exportAllRelationData() {
        boolean enableOutProject = true;
        Integer projectId = 335;
           //tableName   key        data
        Map<String, Map<String, Map<String, Object>>> dataMap = new ConcurrentHashMap<>();
        Map<String, Object> mapParam = new HashMap();
        mapParam.put("projectId", projectId);
        mapParam.put("contentCode", 15029);
        String next = TableIdGenerator.getNext();
        try {
            TablePropertiesThreadLocalHolder.addProperties("table_code_uuid", next);
            tableDataExpOrImpService.initCodeCache(mapParam);
            /**
             * 项目内导出依赖项目外的导出数据
             */
            if (enableOutProject) {
                listProjectResource(projectId, dataMap, next);
            }
            /**
             * 将数据进行code转换
             *
             */
            tableDataExpOrImpService.conversionIdToCode(dataMap);

            /**
             * 下载数据
             */
            File file = new File("D:\\workspace\\springcloud_new\\table_data_export_import\\src\\test\\java\\jrx\\anyest\\table\\service\\" + "资源管理.zip");
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            Map<String, String> data = Maps.newTreeMap();
            dataMap.forEach((k, v) -> {
                Set<String> set = v.keySet();
                for (String key : set) {
                    if (null == TableDataCodeCacheManager.idToCode.get(next).get(k + TableConstants.CODE_SEPATATION + key)) {
                        data.put(k + TableConstants.SPLIT + key, JSON.toJSONString(v.get(key)));
                    } else {
                        data.put(k + TableConstants.SPLIT + key + TableConstants.CODE_SEPATATION + TableDataCodeCacheManager.idToCode.get(next).get(k + TableConstants.CODE_SEPATATION + key), JSON.toJSONString(v.get(key)));

                    }
                }
            });
            String md5String = MD5FileUtil.getMD5String(data.toString());
            data.put("sign", md5String);
            DownUploadUtils.expData(fileOutputStream, data);


            FileInputStream fileInputStream = new FileInputStream(file);
            Map<String, String> stringStringMap = DownUploadUtils.importData(fileInputStream);

            stringStringMap.forEach((k, v) -> {
                if (!data.get(k).equals(v)) {
                    System.out.println(v);
                }
            });
            stringStringMap.remove("sign");
            String md5String1 = MD5FileUtil.getMD5String(stringStringMap.toString());
            System.out.println(md5String);
            System.out.println(md5String1);


        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        } finally {
            TableDataCodeCacheManager.idToCode.remove(TablePropertiesThreadLocalHolder.getProperties("table_code_uuid"));
            TableDataCodeCacheManager.codeToId.remove(TablePropertiesThreadLocalHolder.getProperties("table_code_uuid"));
            TableDataCodeCacheManager.tableCodeConfigs.remove(TablePropertiesThreadLocalHolder.getProperties("table_code_uuid"));
            TablePropertiesThreadLocalHolder.remove("table_code_uuid");
        }

    }

    private void listProjectResource(Integer projectId, Map<String, Map<String, Map<String, Object>>> dataMap, String next) {
        String prepareSql = "SELECT * FROM `res_resource_set_item` where project_id = " + projectId;
        List<Map<String, Object>> maps = tableDataExpOrImpService.prepareData(prepareSql);
        /**
         * 将资源管理数据添加到导出数据中
         */
        Map<String, Map<String, Object>> item_id = maps.stream().collect(Collectors.toMap(e -> e.get("item_id").toString(), Function.identity()));
        dataMap.put("res_resource_set_item",item_id);
        Map<String, Set<Object>> outProject = Maps.newConcurrentMap();
        outProject.put("meta_topic_object_info", new HashSet<>());
        outProject.put("meta_model_object_info", new HashSet<>());
        outProject.put("meta_data_object_info", new HashSet<>());
        for (Map map : maps) {
            String resource_id = map.get("resource_id").toString();
            if (TableDataCodeCacheManager.idToCode.get(next).get("meta_topic_object_info" + TableConstants.CODE_SEPATATION + resource_id) != null) {
                outProject.get("meta_topic_object_info").add(map.get("resource_id"));
            }
            if (TableDataCodeCacheManager.idToCode.get(next).get("meta_model_object_info" + TableConstants.CODE_SEPATATION + resource_id) != null) {
                outProject.get("meta_model_object_info").add(map.get("resource_id"));
            }
            if (TableDataCodeCacheManager.idToCode.get(next).get("meta_data_object_info" + TableConstants.CODE_SEPATATION + resource_id) != null) {
                outProject.get("meta_data_object_info").add(map.get("resource_id"));
            }
        }
        outProject.forEach((s, objects) -> {
            tableDataExpOrImpService.listAllRelationData(s, objects, null, dataMap, null);
        });
    }


    /**
     * 签名校验
     *
     * @throws FileNotFoundException
     */

    @Test
    public void importData() {
        String tableCodeUuid = TablePropertiesThreadLocalHolder.getProperties(TableConstants.TABLE_CODE_UUID);
        TablePropertiesThreadLocalHolder.addProperties(TableConstants.TABLE_CODE_UUID,tableCodeUuid);
        Integer projectId = 335;
        Map<String, Object> mapParam = new HashMap();
        mapParam.put("projectId", projectId);
        mapParam.put("contentCode", 15029);
        tableDataExpOrImpService.initCodeCache(mapParam);
        ImportDataResult importResult = new ImportDataResult();

        try {
            tableDataExpOrImpService.importData(tableCodeUuid,importResult);
            System.out.println("--------------导入成功------------------");
        } catch (Exception e) {
            List<ImportData> importData = importResult.getImportData();
            for(ImportData importData1:importData){
                if(importData1.getErrorNum()!=0){
                    log.error("[---导入失败数据---]tableName:{},errorMsg:{}",importData1.getTableName(),JSON.toJSONString(importData1.getErrorData()));
                }
            }
        }

        System.out.println(importResult);

    }

    @Test
    public void importInnerProject() {
        try {
            String next = TableIdGenerator.getNext();
            TablePropertiesThreadLocalHolder.addProperties(TableConstants.TABLE_CODE_UUID, next);
            Integer projectId = 335;
            Map<String, Object> mapParam = new HashMap();
            mapParam.put("contentCode", 15029);
            mapParam.put("projectId", projectId);
            tableDataExpOrImpService.initCodeCache(mapParam);
            File file = new File("D:\\workspace\\springcloud_new\\table_data_export_import\\src\\test\\java\\jrx\\anyest\\table\\service\\测试数据.zip");
            FileInputStream fileInputStream = new FileInputStream(file);
            Map<String, String> stringStringMap = DownUploadUtils.importData(fileInputStream);
            String sign = stringStringMap.get("sign");
            stringStringMap.remove("sign");
            String md5String = MD5FileUtil.getMD5String(stringStringMap.toString());
            if(!sign.equals(md5String)){
                throw new TableDataImportException("数据包异常，MD5检验数据失败！");
            }
            DataCheckResult dataCheckResult = tableDataExpOrImpService.checkData(stringStringMap);
            importData();

        } catch (Exception e) {
            log.error("导入数据失败"+e.getMessage());
        } finally {
            TableDataCodeCacheManager.idToCode.remove(TablePropertiesThreadLocalHolder.getProperties(TableConstants.TABLE_CODE_UUID));
            TableDataCodeCacheManager.codeToId.remove(TablePropertiesThreadLocalHolder.getProperties(TableConstants.TABLE_CODE_UUID));
            TablePropertiesThreadLocalHolder.remove(TableConstants.TABLE_CODE_UUID);
        }
    }




}