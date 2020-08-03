package jrx.anyest.table.service;

import jrx.anyest.table.ApplicationStart;
import jrx.anyest.table.jpa.dto.CodeCheck;
import jrx.anyest.table.jpa.dto.TableDataImportOrExpResult;
import jrx.anyest.table.utils.DownUploadUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ApplicationStart.class)
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
//        初始化code缓存信息
        List<TableDataImportOrExpResult<CodeCheck>> tableDataImportOrExpResult = tableDataExpOrImpService.checkCode();

    }

    @Test
    public void initCodeCache() throws FileNotFoundException {
//        初始化code缓存信息
        List<TableDataImportOrExpResult> tableDataImportOrExpResult = tableDataExpOrImpService.initCodeCache();

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
        String tableName = "res_rule_info";
        Integer dataId = 896;
        Map<String, Map<String, String>> dataMap = new ConcurrentHashMap<>();
//        初始化code缓存信息
         tableDataExpOrImpService.listAllRelationData(tableName, dataId,dataMap);

    }


    @Test
    public void exportData() throws FileNotFoundException {
        File file = new File("D:\\workspace\\springcloud_new\\jpa_check\\src\\test\\java\\com\\temp\\jpa\\service\\" + "测试数据.zip");
        FileOutputStream fileOutputStream = new FileOutputStream(file);

//        DownUploadUtils.expData(fileOutputStream,);
    }
}