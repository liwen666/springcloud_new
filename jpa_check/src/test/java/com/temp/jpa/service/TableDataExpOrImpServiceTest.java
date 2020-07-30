package com.temp.jpa.service;

import com.temp.jpa.ApplicationStart;
import com.temp.jpa.jpa.dto.TableDataImportOrExpResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ApplicationStart.class)
public class TableDataExpOrImpServiceTest
{
    @Autowired
    private TableDataExpOrImpService tableDataExpOrImpService;

    @Test
    public void checkCode() throws FileNotFoundException {
//        初始化code缓存信息
        List<TableDataImportOrExpResult> tableDataImportOrExpResult=tableDataExpOrImpService.initCodeCache();

    }

    @Test
    public void exportData() throws FileNotFoundException {
        File file = new File("D:\\workspace\\springcloud_new\\jpa_check\\src\\test\\java\\com\\temp\\jpa\\service\\" + "测试数据.zip" );
        FileOutputStream fileOutputStream = new FileOutputStream(file);
//        TableDataExpOrImpService.expData(fileOutputStream,);
    }
}