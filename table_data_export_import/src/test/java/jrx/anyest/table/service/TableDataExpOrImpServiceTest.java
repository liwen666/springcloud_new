package jrx.anyest.table.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import jrx.anyest.table.ApplicationStart;
import jrx.anyest.table.constant.TableConstants;
import jrx.anyest.table.jpa.dto.CodeCheck;
import jrx.anyest.table.jpa.dto.DataCheckResult;
import jrx.anyest.table.jpa.dto.ImportDataResult;
import jrx.anyest.table.jpa.dto.TableDataImportOrExpResult;
import jrx.anyest.table.utils.DownUploadUtils;
import jrx.anyest.table.utils.TableIdGenerator;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.RowCountCallbackHandler;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ApplicationStart.class)
@Slf4j
public class TableDataExpOrImpServiceTest {
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
        map.put("projectId", 335);
        map.put("contentCode", 15029);
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
        map.put("projectId", 950);
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
     * @throws FileNotFoundException
     */
    @Test
    public void exportAllRelationData() {
        boolean enableOutProject = false;
        Integer projectId = 335;
        Map<String, Map<String, Map<String, Object>>> dataMap = new ConcurrentHashMap<>();
        Map<String, Object> mapParam = new HashMap();
        mapParam.put("projectId", projectId);
        String next = TableIdGenerator.getNext();
        try {
            TablePropertiesThreadLocalHolder.addProperties("table_code_uuid", next);
            tableDataExpOrImpService.initCodeCache(mapParam);
            /**
             * 项目内导出依赖项目外的导出数据
             */
            if (enableOutProject) {
                String prepareSql = "SELECT * FROM `res_resource_set_item` where project_id = " + projectId;
                List<Map<String, Object>> maps = tableDataExpOrImpService.prepareData(prepareSql);
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
        dataMap.forEach((k, v) -> {
            Set<String> set = v.keySet();
            for (String key : set) {
                if (null == TableDataCodeCacheManager.idToCode.get(key)) {
                    data.put(k + TableConstants.SPLIT + key, JSON.toJSONString(v.get(key)));
                } else {
                    data.put(k + TableConstants.SPLIT + key + ":" + TableDataCodeCacheManager.idToCode.get(key), JSON.toJSONString(v.get(key)));

                }
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
    public void importdata() {
        String tableCodeUuid = TablePropertiesThreadLocalHolder.getProperties("table_code_uuid");
        TablePropertiesThreadLocalHolder.addProperties("table_code_uuid",tableCodeUuid);
        Integer projectId = 335;
        Map<String, Object> mapParam = new HashMap();
        mapParam.put("projectId", projectId);
        tableDataExpOrImpService.initCodeCache(mapParam);
        ImportDataResult importDataResult = tableDataExpOrImpService.importData(tableCodeUuid);

    }

    /**
     * 导入前的数据检查
     */
    @Test
    public void importCheck() {
        try {
            String next = TableIdGenerator.getNext();
            TablePropertiesThreadLocalHolder.addProperties("table_code_uuid", next);
            Integer projectId = 335;
            Map<String, Object> mapParam = new HashMap();
            mapParam.put("projectId", projectId);
            tableDataExpOrImpService.initCodeCache(mapParam);
            File file = new File("D:\\workspace\\springcloud_new\\table_data_export_import\\src\\test\\java\\jrx\\anyest\\table\\service\\测试数据.zip");
            FileInputStream fileInputStream = new FileInputStream(file);
            Map<String, String> stringStringMap = DownUploadUtils.importData(fileInputStream);
            String sign = stringStringMap.get("sign");
            System.out.println("******************************************************");
            System.out.println(sign);
            stringStringMap.remove("sign");
            String md5String = MD5FileUtil.getMD5String(stringStringMap.toString());
            System.out.println(md5String.equals(sign));
            DataCheckResult dataCheckResult = tableDataExpOrImpService.checkData(stringStringMap);

            System.out.println(dataCheckResult.getUuidKey());

            importdata();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            TableDataCodeCacheManager.idToCode.remove(TablePropertiesThreadLocalHolder.getProperties("table_code_uuid"));
            TableDataCodeCacheManager.codeToId.remove(TablePropertiesThreadLocalHolder.getProperties("table_code_uuid"));
            TablePropertiesThreadLocalHolder.remove("table_code_uuid");
        }

    }

    @Test
    public void importInnerProject() {
        try {
            String next = TableIdGenerator.getNext();
            TablePropertiesThreadLocalHolder.addProperties("table_code_uuid", next);
            Integer projectId = 335;
            Map<String, Object> mapParam = new HashMap();
            mapParam.put("projectId", projectId);
            tableDataExpOrImpService.initCodeCache(mapParam);
            File file = new File("D:\\workspace\\springcloud_new\\table_data_export_import\\src\\test\\java\\jrx\\anyest\\table\\service\\测试数据.zip");
            FileInputStream fileInputStream = new FileInputStream(file);
            Map<String, String> stringStringMap = DownUploadUtils.importData(fileInputStream);
            String sign = stringStringMap.get("sign");
            System.out.println("******************************************************");
            System.out.println(sign);
            stringStringMap.remove("sign");
            String md5String = MD5FileUtil.getMD5String(stringStringMap.toString());
            System.out.println(md5String.equals(sign));
            DataCheckResult dataCheckResult = tableDataExpOrImpService.checkData(stringStringMap);

            System.out.println(dataCheckResult.getUuidKey());

            importdata();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            TableDataCodeCacheManager.idToCode.remove(TablePropertiesThreadLocalHolder.getProperties("table_code_uuid"));
            TableDataCodeCacheManager.codeToId.remove(TablePropertiesThreadLocalHolder.getProperties("table_code_uuid"));
            TablePropertiesThreadLocalHolder.remove("table_code_uuid");
        }
    }

    @Test
    public void sqlTest() {
        String sql =" INSERT INTO meta_category( category_id  ) VALUES (?)";
//        String sql =" INSERT INTO meta_category(update_time , category_id , create_time , project_id , parent_id , name , category_type , update_person , used , content_code ) VALUES (?,?,?,?,?,?,?,?,?,?)";
//        String value="[1595214790000,337,1595214790000,335,0,\"默认\",\"RULE\",\"lsw\",true,\"15029\"]";
        String value="[1000]";
        List list = JSON.parseObject(value, List.class);
        JdbcTemplateService.jdbcTemplate.update(sql,list.toArray());

    }


    @Test
    public void sqltese2() {
        /**
         * 查询表数据两以及字段类型
         */
        RowCountCallbackHandler rowCountCallbackHandler = new RowCountCallbackHandler();
        JdbcTemplateService.jdbcTemplate.query("select * from ins_market_channel ", rowCountCallbackHandler);
        List<Map<String, Object>> desc_ins_market_channel_ = JdbcTemplateService.jdbcTemplate.queryForList("desc ins_market_channel ");

    }

    @Test
    public void tableId() {
        String next = TableIdGenerator.getNext();
        System.out.println(next);
    }


}