package jrx.anyest.table.jpa.dao;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import jrx.anyest.table.ApplicationStart;
import jrx.anyest.table.jpa.entity.TableCodeConfig;
import jrx.anyest.table.jpa.sql.JdbcTemplateUtils;
import jrx.anyest.table.jpa.sql.TableDateCodeCacheManager;
import jrx.anyest.table.service.TableDataHandler;
import jrx.anyest.table.utils.SpringUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ApplicationStart.class)
public class TableCodeConfigRepositoryTest {
    @Autowired
    private TableCodeConfigRepository tableCodeConfigRepository;




    @Test
    public void checkData() {
        Map<String ,String> errorData=Maps.newConcurrentMap();
        List<TableCodeConfig> all = tableCodeConfigRepository.findAll();
        for(TableCodeConfig tableCodeConfig:all){
            String ruleinfo=getCheckSql(tableCodeConfig);
            List<Map<String, Object>> maps = JdbcTemplateUtils.jdbcTemplate.queryForList(ruleinfo);
            maps.forEach(e->{
                if((Long) e.get("num")!=1){
                    errorData.put(tableCodeConfig.getTableCodeName(), JSON.toJSONString(maps));
                }
            });
        }

        System.out.println(JSON.toJSONString(errorData)
        );



    }

    private String getErrorDataSql(TableCodeConfig tableCodeConfig) {
        String[] split = tableCodeConfig.getColoums().split(",");
        StringBuffer stringBuffer = new StringBuffer();
        Arrays.stream(split).distinct().forEach(e->{stringBuffer.append(e+",");});
        String coloums = stringBuffer.toString().substring(0,stringBuffer.length()-1);
        StringBuffer checkSql = new StringBuffer("SELECT count(1)num ,"+coloums+" FROM `res_rule_info` group by  "+coloums);
        checkSql.append(" ORDER BY num;");
        return checkSql.toString();
    }

    private String getCheckSql(TableCodeConfig tableCodeConfig) {
        String[] split = tableCodeConfig.getColoums().split(",");
        StringBuffer stringBuffer = new StringBuffer();
        Arrays.stream(split).distinct().forEach(e->{stringBuffer.append(e+",");});
       String coloums = stringBuffer.toString().substring(0,stringBuffer.length()-1);
        StringBuffer checkSql = new StringBuffer("SELECT count(1)num ,"+coloums+" FROM `res_rule_info` group by  "+coloums);
                checkSql.append(" ORDER BY num;");
        return checkSql.toString();
    }



    @Test
    public void nameArray() {
        String name = "name,project_id";
        String[] split = name.split(",");
        StringBuffer stringBuffer = new StringBuffer();
        Arrays.stream(split).distinct().forEach(e->{stringBuffer.append(e+",");});
        System.out.println(stringBuffer.toString().substring(0,stringBuffer.length()-1));
    }

    @Test
    public void initOrgTableCode() {
        String orgId ="15029";
        Map<String, String> idToCode = TableDateCodeCacheManager.idToCode.get(orgId);
        Map<String, String> codeToId = TableDateCodeCacheManager.codeToId.get(orgId);
        if(CollectionUtils.isEmpty(idToCode)){
            TableDateCodeCacheManager.idToCode.put(orgId, Maps.newConcurrentMap());
        }
        if(CollectionUtils.isEmpty(codeToId)){
            TableDateCodeCacheManager.codeToId.put(orgId, Maps.newConcurrentMap());
        }
    }

    @Test
    public void name() {
        TableCodeConfig tableCodeConfig = new TableCodeConfig();
        tableCodeConfig.setCreateTime(new Date());
        tableCodeConfig.setTableCodeName("res_rule_info");
        tableCodeConfig.setTableCodeChinaName("规则信息表");
        tableCodeConfig.setColoums("name,project_id");
        tableCodeConfig.setHandleBeanName("defaultTableDataHandler");
        TableCodeConfig tableCodeConfigByTableCodeName = tableCodeConfigRepository.findTableCodeConfigByTableCodeName(tableCodeConfig.getTableCodeName());
        if (null == tableCodeConfigByTableCodeName) {
            tableCodeConfigRepository.save(tableCodeConfig);
        }


      TableDataHandler defaultTableDataHandler = SpringUtil.getBean("defaultTableDataHandler", TableDataHandler.class);
    }

}