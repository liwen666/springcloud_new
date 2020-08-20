package jrx.anyest.table.jpa.dao;

import jrx.anyest.table.TableApplicationStart;
import jrx.anyest.table.jpa.entity.TableCodeConfig;
import jrx.anyest.table.jpa.entity.TableHistoryData;
import jrx.anyest.table.jpa.enums.HistoryDataType;
import jrx.anyest.table.service.TableDataHandler;
import jrx.anyest.table.utils.TableSpringUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.Date;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TableApplicationStart.class)
public class TableHistoryRepositoryTest {
    @Autowired
    private TableHistoryDataRepository tableHistoryDataRepository;





    /**
     * est 不包含的code表
     */

    @Test
    public void insert() {

        TableHistoryData tableHistoryData = new TableHistoryData();
        tableHistoryData.setDataId("1111");
        tableHistoryData.setDataKey("32143294893813");
        tableHistoryData.setHistoryDataType(HistoryDataType.DELETE);
        tableHistoryData.setId(1);
        tableHistoryData.setPrimaryKeyName("id");
        tableHistoryData.setTableName("table_code_config");
        tableHistoryData.setCreateTime(new Date());
        tableHistoryData.setResourceId("111");
        tableHistoryDataRepository.save(tableHistoryData);
    }

}