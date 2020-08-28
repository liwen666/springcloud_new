package com.temp.jpa.jpa.dao;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.temp.jpa.ApplicationStart;
import com.temp.jpa.jpa.entity.BaseEntity;
import com.temp.jpa.jpa.entity.ObjectFieldEntity;
import com.temp.jpa.jpa.entity.TableEntityConversionRule;
import com.temp.jpa.jpa.enums.DataConversionModel;
import com.temp.jpa.service.DataConverRuleEngineUtils;
import javafx.beans.binding.ObjectExpression;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.persistence.Table;
import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TableApplicationStart.class)
@WebAppConfiguration
public class ObjectFieldEntityDaoTest {
    @Autowired
    private ObjectFieldEntityDao objectFieldEntityDao;
    @Autowired
    private TableEntityConversionRuleDao tableEntityConversionRuleDao;


    public static Map<DataConversionModel, Map<String, String>> idToCode = new ConcurrentHashMap<>();
    public static Map<DataConversionModel, Map<String, String>> codeToId = new ConcurrentHashMap<>();
    public static Map<String, List<TableEntityConversionRule>> ruleConversion = new ConcurrentHashMap<>();

    @Before
    public void before() {
        if (idToCode.get(DataConversionModel.FIELD) == null) {
            idToCode.put(DataConversionModel.FIELD, new ConcurrentHashMap<>());
        }
        if (idToCode.get(DataConversionModel.OBJECT) == null) {
            idToCode.put(DataConversionModel.OBJECT, new ConcurrentHashMap<>());
        }
        if (idToCode.get(DataConversionModel.ORG) == null) {
            idToCode.put(DataConversionModel.ORG, new ConcurrentHashMap<>());
        }
        if (codeToId.get(DataConversionModel.FIELD) == null) {
            codeToId.put(DataConversionModel.FIELD, new ConcurrentHashMap<>());
        }
        if (codeToId.get(DataConversionModel.OBJECT) == null) {
            codeToId.put(DataConversionModel.OBJECT, new ConcurrentHashMap<>());
        }
        if (codeToId.get(DataConversionModel.ORG) == null) {
            codeToId.put(DataConversionModel.ORG, new ConcurrentHashMap<>());
        }
        /**
         * 初始化字段转换数据
         */
        List<ObjectFieldEntity> all = objectFieldEntityDao.findAll();
        Map<String, String> fieldIdIdxToCode = all.stream().collect(Collectors.toMap(e -> e.getFieldType().getIndex()+e.getObjectFieldId(), e -> e.getResourceObjectId() + "_" + e.getFieldCode(), (k1, k2) -> k1));
        Map<String, String> fieldIdxCodeToId = all.stream().collect(Collectors.toMap(e -> e.getResourceObjectId() + "_" + e.getFieldCode(), e -> e.getFieldType().getIndex()+e.getObjectFieldId(), (k1, k2) -> k1));
        Map<String, String> fieldIdToCode = all.stream().collect(Collectors.toMap(e -> String.valueOf(e.getObjectFieldId()), e -> e.getResourceObjectId() + "_" + e.getFieldCode(), (k1, k2) -> k1));
        Map<String, String> fieldCodeToId = all.stream().collect(Collectors.toMap(e -> e.getResourceObjectId() + "_" + e.getFieldCode(), e -> String.valueOf(e.getObjectFieldId()), (k1, k2) -> k1));

        idToCode.put(DataConversionModel.FIELD_INDEX, fieldIdIdxToCode);
        codeToId.put(DataConversionModel.FIELD_INDEX, fieldIdxCodeToId);
        idToCode.put(DataConversionModel.FIELD, fieldIdToCode);
        codeToId.put(DataConversionModel.FIELD, fieldCodeToId);
        List<TableEntityConversionRule> allRule = tableEntityConversionRuleDao.findAllRule();
        ruleConversion = allRule.stream().collect(Collectors.groupingBy(TableEntityConversionRule::getTableCode));

        /**
         * 初始化ORG 转换
         */
        List<TableEntityConversionRule> org_rule = ruleConversion.get("org_rule");
        Map<String, String> orgIdToCode = org_rule.stream().filter(e->e.getEntityKey()!=null).filter(e->e.getEntityValue()!=null).collect(Collectors.toMap(TableEntityConversionRule::getEntityKey, TableEntityConversionRule::getEntityValue));
        Map<String, String> orgCodeToId = org_rule.stream().filter(e->e.getEntityKey()!=null).filter(e->e.getEntityValue()!=null).collect(Collectors.toMap(TableEntityConversionRule::getEntityValue, TableEntityConversionRule::getEntityKey));
        idToCode.put(DataConversionModel.ORG,orgIdToCode);
        codeToId.put(DataConversionModel.ORG,orgCodeToId);
    }


    /**
     * 对字段分组
     */
    @Test
    public void name() {
        List<ObjectFieldEntity> all = objectFieldEntityDao.findAll();
        System.out.println(all.size());
        System.out.println(all.get(0));
        Map<Integer, List<ObjectFieldEntity>> collect = all.stream().collect(Collectors.groupingBy(ObjectFieldEntity::getResourceObjectId));

    }

    /**
     * t
     */

    @Test
    public void converField() {
//        List<ObjectFieldEntity> all = objectFieldEntityDao.findAll();
//        System.out.println(all.size());
//        System.out.println(JSON.toJSONString(all.get(0)));

//        ObjectFieldEntity byObjectFieldId = objectFieldEntityDao.findByObjectFieldId(2269);
        ObjectFieldEntity byObjectFieldId = objectFieldEntityDao.findByObjectFieldId(2090);
        System.out.println(JSON.toJSONString(byObjectFieldId));
        JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(byObjectFieldId));
        System.out.println(DataConverRuleEngineUtils.getTableProperty(jsonObject, "objectFieldId"));
        System.out.println(DataConverRuleEngineUtils.getTableProperty(jsonObject, "deriveContent.statisticFieldBid"));
        Map<String, String> stringStringMap = idToCode.get(DataConversionModel.FIELD);

        DataConverRuleEngineUtils.setPropertyTable(jsonObject, "deriveContent.statisticFieldBid", null, stringStringMap,true);

        System.out.println(DataConverRuleEngineUtils.getTableProperty(jsonObject, "deriveContent.statisticFieldBid"));

        System.out.println(JSON.parseObject(JSON.toJSONString(jsonObject), ObjectFieldEntity.class));

    }


    @Test
    public void entrityTOCode() {

//        List<ObjectFieldEntity> all = objectFieldEntityDao.findAll();
//        System.out.println(all.size());
//        System.out.println(JSON.toJSONString(all.get(0)));

//        ObjectFieldEntity byObjectFieldId = objectFieldEntityDao.findByObjectFieldId(2269);
//        ObjectFieldEntity byObjectFieldId = objectFieldEntityDao.findByObjectFieldId(2090);
//        ObjectFieldEntity byObjectFieldId = objectFieldEntityDao.findByObjectFieldId(2495);
//        ObjectFieldEntity byObjectFieldId = objectFieldEntityDao.findByObjectFieldId(2617);
        ObjectFieldEntity byObjectFieldId = objectFieldEntityDao.findByObjectFieldId(1930);
        System.out.println(JSON.toJSONString(byObjectFieldId));
        JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(byObjectFieldId));


        List<TableEntityConversionRule> tableEntityConversionRules = ruleConversion.get(getTableName(byObjectFieldId)).stream().filter(e->e.getEntityKey()!=null).collect(Collectors.toList());
        for(TableEntityConversionRule rule:tableEntityConversionRules){
            Map<String, String> stringStringMap = idToCode.get(rule.getDataConversionModel());
            DataConverRuleEngineUtils.setPropertyTable(jsonObject, rule.getEntityKey(), null, stringStringMap,false);
        }
        BaseEntity objectFieldEntity = JSON.parseObject(JSON.toJSONString(jsonObject), ObjectFieldEntity.class);
        System.out.println(JSON.toJSONString(objectFieldEntity));


    }

    @Test
    public void entryToId() {
//        String entry="{\"bid\":\"a2090\",\"columnCode\":\"stat_province\",\"computePeriod\":\"NONE\",\"contentCode\":\"将融信机构\",\"createTime\":1584454844000,\"deriveContent\":\"{\\\"calculationType\\\":\\\"COUNT\\\",\\\"timeSpliceNum\\\":0,\\\"isFunction\\\":false,\\\"statisticFieldBid\\\":\\\"1379_id\\\",\\\"updateMode\\\":\\\"ACCUMULATE\\\",\\\"tableVersionId\\\":1379,\\\"dimensionFieldBids\\\":\\\"\\\"}\",\"fieldCode\":\"stat_province\",\"fieldName\":\"计算2\",\"fieldState\":\"ACTIVE\",\"fieldType\":\"STAT_FIELD\",\"isDimension\":false,\"isKey\":false,\"isTarget\":false,\"listValueType\":\"STRING\",\"objectFieldId\":2090,\"objectType\":\"TOPIC\",\"referFieldIds\":\"1568\",\"resourceObjectCategoryId\":1877,\"resourceObjectId\":1848,\"resourceObjectVersionId\":1878,\"updateMode\":\"ACCUMULATE\",\"updatePerson\":\"liwen\",\"updateTime\":1584454844000,\"valueType\":\"INTEGER\"}\n";
//        String entry="{\"bid\":\"a2090\",\"columnCode\":\"stat_province\",\"computePeriod\":\"NONE\",\"contentCode\":\"将融信机构\",\"createTime\":1584454844000,\"deriveContent\":\"{\\\"calculationType\\\":\\\"COUNT\\\",\\\"timeSpliceNum\\\":0,\\\"isFunction\\\":false,\\\"statisticFieldBid\\\":\\\"1379_id\\\",\\\"updateMode\\\":\\\"ACCUMULATE\\\",\\\"tableVersionId\\\":1379,\\\"dimensionFieldBids\\\":\\\"\\\"}\",\"fieldCode\":\"stat_province\",\"fieldName\":\"计算2\",\"fieldState\":\"ACTIVE\",\"fieldType\":\"STAT_FIELD\",\"isDimension\":false,\"isKey\":false,\"isTarget\":false,\"listValueType\":\"STRING\",\"objectFieldId\":2090,\"objectType\":\"TOPIC\",\"referFieldIds\":\"1379_id\",\"resourceObjectCategoryId\":1877,\"resourceObjectId\":1848,\"resourceObjectVersionId\":1878,\"updateMode\":\"ACCUMULATE\",\"updatePerson\":\"liwen\",\"updateTime\":1584454844000,\"valueType\":\"INTEGER\"}";
        String entry="{\"bid\":\"a1930\",\"columnCode\":\"stat\",\"computePeriod\":\"NONE\",\"contentCode\":\"将融信机构\",\"deriveContent\":\"{\\\"calculationType\\\":\\\"COUNT\\\",\\\"timeSpliceNum\\\":0,\\\"isFunction\\\":false,\\\"statisticFieldBid\\\":\\\"1379_id\\\",\\\"updateMode\\\":\\\"OVERWRITE_ALL\\\",\\\"tableVersionId\\\":1379,\\\"dimensionFieldBids\\\":\\\"\\\"}\",\"description\":\"\",\"fieldCode\":\"stat\",\"fieldName\":\"统计数量\",\"fieldState\":\"ACTIVE\",\"fieldType\":\"STAT_FIELD\",\"isDimension\":false,\"isKey\":false,\"isTarget\":false,\"listValueType\":\"STRING\",\"objectFieldId\":1930,\"objectType\":\"TOPIC\",\"referFieldIds\":\"1379_id\",\"resourceObjectCategoryId\":1877,\"resourceObjectId\":1848,\"resourceObjectVersionId\":1878,\"updateMode\":\"OVERWRITE_ALL\",\"updatePerson\":\"liwen\",\"updateTime\":1584454844000,\"valueType\":\"INTEGER\"}\n";
        JSONObject jsonObject = JSONObject.parseObject(entry);
        List<TableEntityConversionRule> tableEntityConversionRules = ruleConversion.get(getTableName(new ObjectFieldEntity())).stream().filter(e->e.getEntityKey()!=null).collect(Collectors.toList());
        for(TableEntityConversionRule rule:tableEntityConversionRules){
            Map<String, String> stringStringMap = codeToId.get(rule.getDataConversionModel());
            DataConverRuleEngineUtils.setPropertyTable(jsonObject, rule.getEntityKey(), null, stringStringMap,false);
        }
        BaseEntity objectFieldEntity = JSON.parseObject(JSON.toJSONString(jsonObject), ObjectFieldEntity.class);
        System.out.println(JSON.toJSONString(objectFieldEntity));
    }

    /**
     * 实体转换
     *
     * @param byObjectFieldId
     * @param tableName
     * @return
     */


    private String idToCode(ObjectFieldEntity byObjectFieldId, String tableName) {
        List<TableEntityConversionRule> tableEntityConversionRules = ruleConversion.get(tableName);
        for (TableEntityConversionRule tableEntityConversionRule : tableEntityConversionRules) {

        }
        return JSON.toJSONString(byObjectFieldId);
    }

    private String getTableName(BaseEntity baseEntity) {
        Class<? extends BaseEntity> aClass = baseEntity.getClass();
        Annotation[] annotations = aClass.getAnnotations();
        for (Annotation annotation : annotations) {
            if (annotation.annotationType().getName().equals("javax.persistence.Table")) {
                String name = ((Table) annotation).name();
                return name;
            }
        }
        return null;
    }
}