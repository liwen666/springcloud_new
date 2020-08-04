package jrx.anyest.table.jpa.dao;

import com.google.common.collect.Maps;
import jrx.anyest.table.ApplicationStart;
import jrx.anyest.table.jpa.entity.TableCodeConfig;
import jrx.anyest.table.service.TableDataCodeCacheManager;
import jrx.anyest.table.service.TableDataHandler;
import jrx.anyest.table.utils.TableSpringUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ApplicationStart.class)
public class TableCodeConfigRepositoryTest {
    @Autowired
    private TableCodeConfigRepository tableCodeConfigRepository;


    @Test
    public void nameArray() {
        String name = "name,project_id";
        String[] split = name.split(",");
        StringBuffer stringBuffer = new StringBuffer();
        Arrays.stream(split).distinct().forEach(e -> {
            stringBuffer.append(e + ",");
        });
        System.out.println(stringBuffer.toString().substring(0, stringBuffer.length() - 1));
    }

    @Test
    public void initOrgTableCode() {
        String orgId = "15029";
        Map<String, String> idToCode = TableDataCodeCacheManager.idToCode.get(orgId);
        Map<String, String> codeToId = TableDataCodeCacheManager.codeToId.get(orgId);
        if (CollectionUtils.isEmpty(idToCode)) {
            TableDataCodeCacheManager.idToCode.put(orgId, Maps.newConcurrentMap());
        }
        if (CollectionUtils.isEmpty(codeToId)) {
            TableDataCodeCacheManager.codeToId.put(orgId, Maps.newConcurrentMap());
        }
    }

    /**
     * est 不包含的code表
     */

    @Test
    public void insert() {
        /**
         * 项目内导出
         */
        TableCodeConfig tableCodeConfig = new TableCodeConfig();
        tableCodeConfig.setCreateTime(new Date());
        tableCodeConfig.setTableCodeName("res_rule_info");
        tableCodeConfig.setTableCodeChinaName("规则信息表");
        tableCodeConfig.setColumns("name");
        tableCodeConfig.setWhereSqlColumns("project_id");
        tableCodeConfig.setHandleBeanName("defaultTableDataHandler");
        TableCodeConfig tableCodeConfigByTableCodeName = tableCodeConfigRepository.findTableCodeConfigByTableCodeName(tableCodeConfig.getTableCodeName());
        if (null == tableCodeConfigByTableCodeName) {
            tableCodeConfigRepository.save(tableCodeConfig);
        }

//        tableCodeConfig.setId(null);
//        tableCodeConfig.setCreateTime(new Date());
//        tableCodeConfig.setTableCodeName("res_rule");
//        tableCodeConfig.setTableCodeChinaName("规则版本表");
//        tableCodeConfig.setColumns("resource_id,version");
//        tableCodeConfig.setWhereSqlColumns("project_id");
//        tableCodeConfig.setHandleBeanName("defaultTableDataHandler");
//         tableCodeConfigByTableCodeName = tableCodeConfigRepository.findTableCodeConfigByTableCodeName(tableCodeConfig.getTableCodeName());
//        if (null == tableCodeConfigByTableCodeName) {
//            tableCodeConfigRepository.save(tableCodeConfig);
//        }



        TableDataHandler defaultTableDataHandler = TableSpringUtil.getBean("defaultTableDataHandler", TableDataHandler.class);
    }

}