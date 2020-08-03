package jrx.anyest.table.jpa.dao;

import jrx.anyest.table.ApplicationStart;
import jrx.anyest.table.jpa.entity.TableMarkInit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ApplicationStart.class)
public class TableMarkInitRepositoryTest {
    @Autowired
    private  TableMarkInitRepository tableMarkInitRepository;

    @Test
    public void inert() {
        TableMarkInit tableMarkInit = new TableMarkInit();
        tableMarkInit.setTableName("ins_board_template");
        tableMarkInit.setTableChinaName("看板表");
        tableMarkInit.setCreateTime(new Date());
        TableMarkInit byTableName = tableMarkInitRepository.findByTableName(tableMarkInit.getTableName());
        if(null== byTableName){
            tableMarkInitRepository.save(tableMarkInit);
        }

    }
}