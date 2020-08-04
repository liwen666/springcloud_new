package jrx.anyest.table.service;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import jrx.anyest.table.ApplicationStart;
import jrx.anyest.table.jpa.dto.CodeCheck;
import jrx.anyest.table.jpa.dto.TableDataImportOrExpResult;
import jrx.anyest.table.utils.DownUploadUtils;
import jrx.anyest.table.utils.TableIdGenerator;
import jrx.anyest.table.utils.TableSpringUtil;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
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
import java.util.concurrent.ConcurrentMap;
import java.util.function.BiConsumer;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ApplicationStart.class)
@Slf4j
public class TableDataExpOrImpServiceTest {
    @Autowired
    private TableDataExpOrImpService tableDataExpOrImpService;


    @Before
    public void name() throws FileNotFoundException {
        initRelationCache();
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
        map.put("projectId", 335);
        List<TableDataImportOrExpResult<CodeCheck>> tableDataImportOrExpResult = tableDataExpOrImpService.checkCode(map);
        log.error(JSON.toJSONString(tableDataImportOrExpResult
        ));
    }

    @Test
    public void initCodeCache() {
        /**
         * 初始化code缓存信息
         */
        Map<String, Object> map = new HashMap();
        map.put("projectId", 335);
        String next = TableIdGenerator.getNext();
        try {
            PropertiesThreadLocalHolder.addProperties("table_code_uuid", next);
            tableDataExpOrImpService.initCodeCache(map);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        } finally {
            TableDataCodeCacheManager.idToCode.remove(PropertiesThreadLocalHolder.getProperties("table_code_uuid"));
            TableDataCodeCacheManager.codeToId.remove(PropertiesThreadLocalHolder.getProperties("table_code_uuid"));
            PropertiesThreadLocalHolder.remove("table_code_uuid");
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
     * @throws FileNotFoundException
     */
    @Test
    public void listAllRelationData() {
        Integer projectId = 118;
        Map<String, Map<String, Map<String, Object>>> dataMap = new ConcurrentHashMap<>();
        Map<String, Object> mapParam = new HashMap();
        mapParam.put("projectId", projectId);
        String next = TableIdGenerator.getNext();
        try {
            PropertiesThreadLocalHolder.addProperties("table_code_uuid", next);
            tableDataExpOrImpService.initCodeCache(mapParam);
            /**
             * 项目内导出依赖项目外的导出数据
             */
            String prepareSql = "SELECT * FROM `res_resource_set_item` where project_id = " + projectId;
            List<Map<String, Object>> maps = tableDataExpOrImpService.prepareData(prepareSql);
            Map<String, Set<Object>> outProject = Maps.newConcurrentMap();
            outProject.put("meta_topic_object_info", new HashSet<>());
            outProject.put("meta_model_object_info", new HashSet<>());
            outProject.put("meta_data_object_info", new HashSet<>());
            for (Map map : maps) {
                String resource_id = map.get("resource_id").toString();
                if (TableDataCodeCacheManager.idToCode.get(next).get("meta_topic_object_info:" + resource_id) != null) {
                    outProject.get("meta_topic_object_info").add(map.get("resource_id"));
                }
                if (TableDataCodeCacheManager.idToCode.get(next).get("meta_model_object_info:" + resource_id) != null) {
                    outProject.get("meta_model_object_info").add(map.get("resource_id"));
                }
                if (TableDataCodeCacheManager.idToCode.get(next).get("meta_data_object_info:" + resource_id) != null) {
                    outProject.get("meta_data_object_info").add(map.get("resource_id"));
                }
            }
            outProject.forEach((s, objects) -> {
                tableDataExpOrImpService.listAllRelationData(s, objects, null, dataMap, null);
            });
//        tableDataExpOrImpService.listAllRelationData(tableName, objects, null, dataMap, null);
            String tableName = "res_rule_info";
            Integer dataId = 896;
            Set<Object> objects = Sets.newHashSet();
            objects.add(dataId);
//        初始化code缓存信息
//         tableDataExpOrImpService.listAllRelationData(tableName, objects,null,dataMap,null);
            /**
             * 项目外导出需要先导出所有关联数据集
             */
            ConcurrentMap<String, Object> objectObjectConcurrentMap = Maps.newConcurrentMap();
            objectObjectConcurrentMap.put("version", 1);
            tableDataExpOrImpService.listAllRelationData(tableName, objects, objectObjectConcurrentMap, dataMap, null);
            /**
             * 将数据进行code转换
             *
             */
            /**
             * 下载数据
             */
            File file = new File("D:\\workspace\\springcloud_new\\table_data_export_import\\src\\test\\java\\jrx\\anyest\\table\\service\\" + "测试数据.zip");
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            Map<String, String> data = Maps.newTreeMap();
            dataMap.forEach((s, stringMapMap) -> {
                Set<String> set = stringMapMap.keySet();
                for (String key : set) {
                    data.put(s + "/" + key, JSON.toJSONString(stringMapMap.get(key)));
                }
            });
            String md5String = MD5FileUtil.getMD5String(data.toString());
            data.put("sign", md5String);
            DownUploadUtils.expData(fileOutputStream, data);


            FileInputStream fileInputStream = new FileInputStream(file);
            Map<String, String> stringStringMap = DownUploadUtils.importData(fileInputStream);

            stringStringMap.forEach((k,v)->{
                if(!data.get(k).equals(v)){
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
            TableDataCodeCacheManager.idToCode.remove(PropertiesThreadLocalHolder.getProperties("table_code_uuid"));
            TableDataCodeCacheManager.codeToId.remove(PropertiesThreadLocalHolder.getProperties("table_code_uuid"));
            PropertiesThreadLocalHolder.remove("table_code_uuid");
        }

    }


    @Test
    public void exportData() throws FileNotFoundException {
        File file = new File("D:\\workspace\\springcloud_new\\table_data_export_import\\src\\test\\java\\jrx\\anyest\\table\\service\\" + "测试数据.zip");
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        String tableName = "res_rule_info";
        Integer dataId = 896;
        Set<Object> objects = Sets.newHashSet();
        objects.add(dataId);
        ConcurrentHashMap<String, Map<String, Map<String, Object>>> dataMap = new ConcurrentHashMap<>();
//        初始化code缓存信息
//         tableDataExpOrImpService.listAllRelationData(tableName, objects,null,dataMap,null);
        ConcurrentMap<String, Object> objectObjectConcurrentMap = Maps.newConcurrentMap();
        objectObjectConcurrentMap.put("version", 1);
        tableDataExpOrImpService.listAllRelationData(tableName, objects, objectObjectConcurrentMap, dataMap, null);
        Map<String, String> data = Maps.newConcurrentMap();
        dataMap.forEach((s, stringMapMap) -> {
            Set<String> set = stringMapMap.keySet();
            for (String key : set) {
                data.put(s + "/" + key, JSON.toJSONString(stringMapMap.get(key)));
            }
        });
        String md5String = MD5FileUtil.getMD5String(data.toString());
        data.put("sign", md5String);
        DownUploadUtils.expData(fileOutputStream, data);
    }

    /**
     * 签名校验
     *
     * @throws FileNotFoundException
     */
    @Test
    public void importdata() throws FileNotFoundException {
        File file = new File("D:\\workspace\\springcloud_new\\table_data_export_import\\src\\test\\java\\jrx\\anyest\\table\\service\\测试数据.zip");
        FileInputStream fileInputStream = new FileInputStream(file);
        Map<String, String> stringStringMap = DownUploadUtils.importData(fileInputStream);
        String sign = stringStringMap.get("sign");
        System.out.println("******************************************************");
        System.out.println(sign);
        stringStringMap.remove("sign");
        String md5String = MD5FileUtil.getMD5String(stringStringMap.toString());
        System.out.println(md5String.equals(sign));
    }


}