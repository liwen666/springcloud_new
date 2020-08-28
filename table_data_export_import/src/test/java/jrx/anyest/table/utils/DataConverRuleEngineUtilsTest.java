package jrx.anyest.table.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Expression;
import jrx.anyest.table.constant.TableConstants;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;

import static org.junit.Assert.*;

public class DataConverRuleEngineUtilsTest {
    @Test
    public void get() {
            String value ="{\"node_id\":16433,\"content_code\":\"3\",\"create_time\":1597825951000,\"update_person\":\"xhp33\",\"update_time\":1597825951000,\"execute_mode\":\"ALWAYS\",\"height\":50,\"icon\":\"calc\",\"name\":\"衍生变量\",\"node_content\":\"[{\\\"functionParams\\\":{},\\\"fieldName\\\":\\\"test_模型字段\\\",\\\"functions\\\":{},\\\"import\\\":false,\\\"expressionUnits\\\":[{\\\"type\\\":\\\"input\\\",\\\"value\\\":\\\"4\\\"},{\\\"term\\\":\\\"+\\\",\\\"type\\\":\\\"term\\\"},{\\\"type\\\":\\\"input\\\",\\\"value\\\":\\\"3\\\"}],\\\"fieldCode\\\":\\\"NODE_test_model\\\",\\\"referFunctionCodes\\\":\\\"\\\",\\\"isKey\\\":false,\\\"description\\\":\\\"\\\",\\\"relColumnCode\\\":\\\"NODE_test_model\\\",\\\"resourceObjectVersionId\\\":11156,\\\"priority\\\":0,\\\"recorded\\\":false,\\\"resourceObjectId\\\":11155,\\\"fieldState\\\":\\\"ACTIVE\\\",\\\"objectType\\\":\\\"NODE_FEATURE\\\",\\\"referFieldBids\\\":\\\"\\\",\\\"valueType\\\":\\\"INTEGER\\\",\\\"columnCode\\\":\\\"NODE_test_model\\\",\\\"position\\\":0,\\\"bid\\\":\\\"f12176\\\",\\\"fieldType\\\":\\\"FORMULA_FIELD\\\",\\\"fieldId\\\":12176}]\",\"node_key\":\"12190_20395\",\"node_type\":\"DERIVE_FIELD\",\"strategy_id\":16431,\"text\":\"衍生变量\",\"width\":150,\"x\":75,\"y\":125}";
            JSONObject jsonObject = JSON.parseObject(value);
            Object tableProperty = DataConverRuleEngineUtils.getTableProperty(jsonObject, "node_content.eventStatObjectInfoId");
            System.out.println(tableProperty);
    }

    @Test
    public void getArray() {
        String value="{\"node_id\":107623,\"content_code\":\"101\",\"create_time\":1598334976000,\"update_person\":\"lw101\",\"update_time\":1598334976000,\"execute_mode\":\"ALWAYS\",\"height\":50,\"icon\":\"scoreCard\",\"name\":\"稳定性评分卡\",\"node_content\":\"{\\\"scoreField\\\":{\\\"fieldName\\\":\\\"稳定性评分\\\",\\\"import\\\":false,\\\"fieldCode\\\":\\\"stabilityScore\\\",\\\"isKey\\\":false,\\\"objectInfoCode\\\":\\\"md_credit_data\\\",\\\"relColumnCode\\\":\\\"stabilityScore\\\",\\\"priority\\\":0,\\\"recorded\\\":false,\\\"resourceObjectId\\\":423,\\\"fieldState\\\":\\\"ACTIVE\\\",\\\"objectType\\\":\\\"MODEL\\\",\\\"valueType\\\":\\\"INTEGER\\\",\\\"columnCode\\\":\\\"stabilityScore\\\",\\\"position\\\":0,\\\"bid\\\":\\\"b837\\\",\\\"fieldType\\\":\\\"BIZ_FIELD\\\",\\\"fieldId\\\":837},\\\"items\\\":[{\\\"weight\\\":100.0,\\\"assertItemList\\\":[{\\\"chosen2\\\":false,\\\"valueList\\\":[\\\"已婚\\\"],\\\"betweenType\\\":\\\"LCRO\\\",\\\"itemScoreValue\\\":20.0,\\\"operator\\\":\\\"EQ\\\",\\\"chosen\\\":false},{\\\"chosen2\\\":false,\\\"valueList\\\":[\\\"未婚\\\"],\\\"betweenType\\\":\\\"LCRO\\\",\\\"itemScoreValue\\\":5.0,\\\"operator\\\":\\\"EQ\\\",\\\"chosen\\\":false},{\\\"chosen2\\\":false,\\\"valueList\\\":[\\\"离异\\\"],\\\"betweenType\\\":\\\"LCRO\\\",\\\"itemScoreValue\\\":10.0,\\\"operator\\\":\\\"EQ\\\",\\\"chosen\\\":false}],\\\"conditionField\\\":{\\\"fieldName\\\":\\\"人行婚姻-二代\\\",\\\"import\\\":false,\\\"fieldCode\\\":\\\"ccrcMrriage\\\",\\\"isKey\\\":false,\\\"objectInfoCode\\\":\\\"db_chengdeBankApplyCreditReport\\\",\\\"relColumnCode\\\":\\\"ccrcMrriage\\\",\\\"resourceObjectVersionId\\\":3477,\\\"priority\\\":0,\\\"recorded\\\":false,\\\"resourceObjectId\\\":1276,\\\"fieldState\\\":\\\"ACTIVE\\\",\\\"objectType\\\":\\\"DATA\\\",\\\"updateMode\\\":\\\"OVERWRITE_ALL\\\",\\\"valueType\\\":\\\"STRING\\\",\\\"columnCode\\\":\\\"ccrcMrriage\\\",\\\"position\\\":0,\\\"bid\\\":\\\"b1618\\\",\\\"fieldType\\\":\\\"BIZ_FIELD\\\",\\\"fieldId\\\":1618}},{\\\"weight\\\":100.0,\\\"assertItemList\\\":[{\\\"chosen2\\\":false,\\\"valueList\\\":[\\\"公务员\\\"],\\\"betweenType\\\":\\\"LCRO\\\",\\\"itemScoreValue\\\":20.0,\\\"operator\\\":\\\"EQ\\\",\\\"chosen\\\":false}],\\\"conditionField\\\":{\\\"fieldName\\\":\\\"人行职业-二代\\\",\\\"import\\\":false,\\\"fieldCode\\\":\\\"job\\\",\\\"isKey\\\":false,\\\"objectInfoCode\\\":\\\"db_chengdeBankApplyCreditReport\\\",\\\"relColumnCode\\\":\\\"job\\\",\\\"resourceObjectVersionId\\\":3476,\\\"priority\\\":0,\\\"recorded\\\":false,\\\"resourceObjectId\\\":1276,\\\"fieldState\\\":\\\"ACTIVE\\\",\\\"objectType\\\":\\\"DATA\\\",\\\"updateMode\\\":\\\"OVERWRITE_ALL\\\",\\\"valueType\\\":\\\"STRING\\\",\\\"columnCode\\\":\\\"job\\\",\\\"position\\\":0,\\\"bid\\\":\\\"b1620\\\",\\\"fieldType\\\":\\\"BIZ_FIELD\\\",\\\"fieldId\\\":1620}}],\\\"resourceType\\\":\\\"SCORECARD\\\"}\",\"node_key\":\"107622_32369\",\"node_type\":\"SCORECARD\",\"strategy_id\":107622,\"text\":\"稳定性评分卡\",\"width\":150,\"x\":75,\"y\":1225}";
        JSONObject jsonObject = JSON.parseObject(value);
        Object tableProperty = DataConverRuleEngineUtils.getTableProperty(jsonObject, "node_content.items.conditionField.bid");
        System.out.println(tableProperty);
    }

    @Test
    public void setArray() {
        String value="{\"node_id\":107623,\"content_code\":\"101\",\"create_time\":1598334976000,\"update_person\":\"lw101\",\"update_time\":1598334976000,\"execute_mode\":\"ALWAYS\",\"height\":50,\"icon\":\"scoreCard\",\"name\":\"稳定性评分卡\",\"node_content\":\"{\\\"scoreField\\\":{\\\"fieldName\\\":\\\"稳定性评分\\\",\\\"import\\\":false,\\\"fieldCode\\\":\\\"stabilityScore\\\",\\\"isKey\\\":false,\\\"objectInfoCode\\\":\\\"md_credit_data\\\",\\\"relColumnCode\\\":\\\"stabilityScore\\\",\\\"priority\\\":0,\\\"recorded\\\":false,\\\"resourceObjectId\\\":423,\\\"fieldState\\\":\\\"ACTIVE\\\",\\\"objectType\\\":\\\"MODEL\\\",\\\"valueType\\\":\\\"INTEGER\\\",\\\"columnCode\\\":\\\"stabilityScore\\\",\\\"position\\\":0,\\\"bid\\\":\\\"b837\\\",\\\"fieldType\\\":\\\"BIZ_FIELD\\\",\\\"fieldId\\\":837},\\\"items\\\":[{\\\"weight\\\":100.0,\\\"assertItemList\\\":[{\\\"chosen2\\\":false,\\\"valueList\\\":[\\\"已婚\\\"],\\\"betweenType\\\":\\\"LCRO\\\",\\\"itemScoreValue\\\":20.0,\\\"operator\\\":\\\"EQ\\\",\\\"chosen\\\":false},{\\\"chosen2\\\":false,\\\"valueList\\\":[\\\"未婚\\\"],\\\"betweenType\\\":\\\"LCRO\\\",\\\"itemScoreValue\\\":5.0,\\\"operator\\\":\\\"EQ\\\",\\\"chosen\\\":false},{\\\"chosen2\\\":false,\\\"valueList\\\":[\\\"离异\\\"],\\\"betweenType\\\":\\\"LCRO\\\",\\\"itemScoreValue\\\":10.0,\\\"operator\\\":\\\"EQ\\\",\\\"chosen\\\":false}],\\\"conditionField\\\":{\\\"fieldName\\\":\\\"人行婚姻-二代\\\",\\\"import\\\":false,\\\"fieldCode\\\":\\\"ccrcMrriage\\\",\\\"isKey\\\":false,\\\"objectInfoCode\\\":\\\"db_chengdeBankApplyCreditReport\\\",\\\"relColumnCode\\\":\\\"ccrcMrriage\\\",\\\"resourceObjectVersionId\\\":3477,\\\"priority\\\":0,\\\"recorded\\\":false,\\\"resourceObjectId\\\":1276,\\\"fieldState\\\":\\\"ACTIVE\\\",\\\"objectType\\\":\\\"DATA\\\",\\\"updateMode\\\":\\\"OVERWRITE_ALL\\\",\\\"valueType\\\":\\\"STRING\\\",\\\"columnCode\\\":\\\"ccrcMrriage\\\",\\\"position\\\":0,\\\"bid\\\":\\\"b1618\\\",\\\"fieldType\\\":\\\"BIZ_FIELD\\\",\\\"fieldId\\\":1618}},{\\\"weight\\\":100.0,\\\"assertItemList\\\":[{\\\"chosen2\\\":false,\\\"valueList\\\":[\\\"公务员\\\"],\\\"betweenType\\\":\\\"LCRO\\\",\\\"itemScoreValue\\\":20.0,\\\"operator\\\":\\\"EQ\\\",\\\"chosen\\\":false}],\\\"conditionField\\\":{\\\"fieldName\\\":\\\"人行职业-二代\\\",\\\"import\\\":false,\\\"fieldCode\\\":\\\"job\\\",\\\"isKey\\\":false,\\\"objectInfoCode\\\":\\\"db_chengdeBankApplyCreditReport\\\",\\\"relColumnCode\\\":\\\"job\\\",\\\"resourceObjectVersionId\\\":3476,\\\"priority\\\":0,\\\"recorded\\\":false,\\\"resourceObjectId\\\":1276,\\\"fieldState\\\":\\\"ACTIVE\\\",\\\"objectType\\\":\\\"DATA\\\",\\\"updateMode\\\":\\\"OVERWRITE_ALL\\\",\\\"valueType\\\":\\\"STRING\\\",\\\"columnCode\\\":\\\"job\\\",\\\"position\\\":0,\\\"bid\\\":\\\"b1620\\\",\\\"fieldType\\\":\\\"BIZ_FIELD\\\",\\\"fieldId\\\":1620}}],\\\"resourceType\\\":\\\"SCORECARD\\\"}\",\"node_key\":\"107622_32369\",\"node_type\":\"SCORECARD\",\"strategy_id\":107622,\"text\":\"稳定性评分卡\",\"width\":150,\"x\":75,\"y\":1225}";
        JSONObject jsonObject = JSON.parseObject(value);
        Object tableProperty = DataConverRuleEngineUtils.getTableProperty(jsonObject, "node_content.items.conditionField.bid");
        System.out.println(tableProperty);
        ConcurrentMap<String, String> idToCode = Maps.newConcurrentMap();
        idToCode.put("test@b1618","aaaaa");
        idToCode.put("test@b1620","bbbbb");
        DataConverRuleEngineUtils.setPropertyTable(jsonObject, "node_content.items.conditionField.bid", null,idToCode , "test" + TableConstants.CODE_SEPATATION);
        Object tableProperty2 = DataConverRuleEngineUtils.getTableProperty(jsonObject, "node_content.items.conditionField.bid");
        System.out.println(tableProperty2);

    }

    @Test
    public void experession() {
//       String  "expression": "(b842!='是')",

//        String expression="(b842!='是')";
        String expression="(meta_object_field￥is_custmer_white_list￥meta_model_object_info￥md_credit_datab!='是')";
//        AviatorUtil.compute()
//        String regx=""

        Expression compile = AviatorEvaluator.compile(expression);
        Map map = Maps.newConcurrentMap();
        map.put("meta_object_field@is_custmer_white_list@meta_model_object_info@md_credit_datab", "是");
        Object execute = compile.execute(map);
        List<String> variableFullNames = compile.getVariableFullNames();
        System.out.println(variableFullNames);
        List<String> variableNames = compile.getVariableNames();
        System.out.println(variableNames);
        for(String key:variableNames){
          expression=  expression.replaceAll(key,"aaa");
        }
        System.out.println(execute);

        System.out.println(expression);

        Expression compile1 = AviatorEvaluator.compile(expression);
        List<String> variableNames1 = compile1.getVariableNames();
        System.out.println(variableNames1);
        for(String key:variableNames1){
            expression=  expression.replaceAll(key,"b842");
        }
        System.out.println(expression);


    }

    @Test
    public void name() {
        String aaa="a43432";
        System.out.println(aaa.matches("^[a-z][0-9]*"));


        String content="[{\"fieldName\":\"额度有效起始日期\",\"import\":false,\"fieldCode\":\"NODE_amtEffectDate\",\"isKey\":false,\"description\":\"\",\"relColumnCode\":\"NODE_amtEffectDate\",\"resourceObjectVersionId\":858,\"params\":[{\"paramCode\":\"num\",\"isField\":false,\"valueType\":\"INTEGER\",\"paramName\":\"有效天数\",\"recorded\":false,\"value\":\"0\"}],\"priority\":0,\"recorded\":false,\"resourceObjectId\":\"meta_model_object_info@sys_default\",\"fieldState\":\"ACTIVE\",\"objectType\":\"NODE_FEATURE\",\"functionId\":\"numEffectDate\",\"function\":{\"contentCode\":\"101\",\"returnValueType\":\"STRING\",\"resourceId\":1072,\"versionState\":\"ONLINE\",\"inState\":\"accumulator\",\"language\":\"JAVA\",\"updateTime\":1591930827000,\"version\":1,\"functionId\":1073,\"createTime\":1591930816000,\"inStateClass\":\"java.lang.Object\",\"paramFields\":[{\"fieldName\":\"有效天数\",\"import\":false,\"fieldCode\":\"num\",\"valueType\":\"INTEGER\",\"isKey\":false,\"relColumnCode\":\"num\",\"columnCode\":\"num\",\"position\":0,\"priority\":0,\"recorded\":false,\"fieldType\":\"BIZ_FIELD\",\"fieldState\":\"ACTIVE\"}],\"functionType\":\"NORMAL_FUNCTION\",\"scripts\":\"Date d = new Date();\\nSimpleDateFormat format = new SimpleDateFormat(\\\"yyyy-MM-dd\\\");\\nString currDate = format.format(d);\\nCalendar ca = Calendar.getInstance();\\nca.add(Calendar.DATE, num);\\nd = ca.getTime();\\nreturn format.format(d);\",\"nValue\":\"nValue\",\"updatePerson\":\"tanshishu\",\"resourceType\":\"FUNCTION\"},\"valueType\":\"STRING\",\"columnCode\":\"NODE_amtEffectDate\",\"position\":0,\"bid\":\"n1077\",\"fieldType\":\"FUNCTION_FIELD\",\"fieldId\":1077},{\"fieldName\":\"额度失效时间\",\"import\":false,\"fieldCode\":\"NODE_amtendtDate\",\"isKey\":false,\"description\":\"\",\"relColumnCode\":\"NODE_amtendtDate\",\"resourceObjectVersionId\":858,\"params\":[{\"paramCode\":\"num\",\"isField\":false,\"valueType\":\"INTEGER\",\"paramName\":\"有效天数\",\"recorded\":false,\"value\":\"360\"}],\"priority\":0,\"recorded\":false,\"resourceObjectId\":\"meta_model_object_info@sys_default\",\"fieldState\":\"ACTIVE\",\"objectType\":\"NODE_FEATURE\",\"functionId\":\"numEffectDate\",\"function\":{\"$ref\":\"$[0].function\"},\"valueType\":\"STRING\",\"columnCode\":\"NODE_amtendtDate\",\"position\":0,\"bid\":\"n1078\",\"fieldType\":\"FUNCTION_FIELD\",\"fieldId\":1078}]";
        JSON.parseObject(content);
    }


    @Test
    public void vonver() {
        String val ="{\"node_id\":105772,\"content_code\":\"101\",\"update_person\":\"lwl101\",\"update_time\":1598427127000,\"execute_mode\":\"ALWAYS\",\"height\":50,\"icon\":\"rule\",\"name\":\"输出接口条件字段默认值4\",\"node_content\":\"{\\\"condition\\\":{\\\"nodeName\\\":\\\"满足其中一条\\\",\\\"hit\\\":false,\\\"listItemType\\\":\\\"STRING\\\",\\\"nodeNo\\\":\\\"1\\\",\\\"children\\\":[{\\\"nodeName\\\":\\\"证件号 不等于 0\\\",\\\"listItemType\\\":\\\"STRING\\\",\\\"fieldBid\\\":\\\"meta_object_field@icNumber@meta_model_object_info@ev_credit_eventb\\\",\\\"fieldBidValue\\\":\\\"\\\",\\\"fieldName\\\":\\\"证件号\\\",\\\"nodeNo\\\":\\\"1\\\",\\\"operateType\\\":\\\"NEQ\\\",\\\"nodeType\\\":\\\"item\\\",\\\"parentId\\\":530144,\\\"listValue\\\":\\\"\\\",\\\"hit\\\":false,\\\"valueType\\\":\\\"STRING\\\",\\\"betweenType\\\":\\\"LCRO\\\",\\\"id\\\":530145,\\\"betweenType2\\\":\\\"LCRO\\\",\\\"value\\\":\\\"0\\\",\\\"fieldNameValue\\\":\\\"\\\"}],\\\"betweenType\\\":\\\"LCRO\\\",\\\"id\\\":530144,\\\"betweenType2\\\":\\\"LCRO\\\",\\\"nodeType\\\":\\\"group\\\",\\\"parentId\\\":0,\\\"relation\\\":\\\"or\\\"},\\\"expFunctions\\\":\\\"\\\",\\\"expression\\\":\\\"(meta_object_field@icNumber@meta_model_object_info@ev_credit_eventb!='0')\\\",\\\"functions\\\":{},\\\"expFields\\\":\\\"meta_object_field@icNumber@meta_model_object_info@ev_credit_eventb\\\",\\\"description\\\":\\\"( (证件号 不等于 0) )\\\",\\\"hitOutputList\\\":[{\\\"expFunction\\\":\\\"\\\",\\\"outField\\\":{\\\"fieldName\\\":\\\"最低应还总额\\\",\\\"functions\\\":{},\\\"import\\\":false,\\\"referFields\\\":[{\\\"fieldName\\\":\\\"贷记卡近6个月平均使用额度\\\",\\\"import\\\":false,\\\"fieldCode\\\":\\\"crdCardAvgAmountUsed6Mth\\\",\\\"isKey\\\":false,\\\"relColumnCode\\\":\\\"crdCardAvgAmountUsed6Mth\\\",\\\"resourceObjectVersionId\\\":105060,\\\"priority\\\":0,\\\"recorded\\\":false,\\\"resourceObjectId\\\":1281,\\\"fieldState\\\":\\\"ACTIVE\\\",\\\"objectType\\\":\\\"DATA\\\",\\\"updateMode\\\":\\\"OVERWRITE_ALL\\\",\\\"valueType\\\":\\\"INTEGER\\\",\\\"columnCode\\\":\\\"crdCardAvgAmountUsed6Mth\\\",\\\"position\\\":0,\\\"bid\\\":\\\"b1437\\\",\\\"fieldType\\\":\\\"BIZ_FIELD\\\",\\\"fieldId\\\":1437},{\\\"fieldName\\\":\\\"隐含分期\\\",\\\"functions\\\":{},\\\"import\\\":false,\\\"fieldCode\\\":\\\"yhperiod\\\",\\\"isKey\\\":false,\\\"relColumnCode\\\":\\\"yhperiod\\\",\\\"resourceObjectVersionId\\\":105091,\\\"priority\\\":0,\\\"recorded\\\":false,\\\"resourceObjectId\\\":1281,\\\"fieldState\\\":\\\"ACTIVE\\\",\\\"objectType\\\":\\\"DATA\\\",\\\"updateMode\\\":\\\"OVERWRITE_ALL\\\",\\\"valueType\\\":\\\"DOUBLE\\\",\\\"columnCode\\\":\\\"yhperiod\\\",\\\"position\\\":0,\\\"bid\\\":\\\"o100178\\\",\\\"fieldType\\\":\\\"CONDITION_FIELD\\\",\\\"deriveContent\\\":\\\"{\\\\\\\"valueConditions\\\\\\\":[{\\\\\\\"default\\\\\\\":true,\\\\\\\"defaultKey\\\\\\\":\\\\\\\"default\\\\\\\",\\\\\\\"expressionUnits\\\\\\\":[{\\\\\\\"type\\\\\\\":\\\\\\\"input\\\\\\\",\\\\\\\"value\\\\\\\":\\\\\\\"0\\\\\\\"}],\\\\\\\"functions\\\\\\\":{},\\\\\\\"groupOrder\\\\\\\":1,\\\\\\\"key\\\\\\\":\\\\\\\"default\\\\\\\",\\\\\\\"referFunctionCodes\\\\\\\":[]},{\\\\\\\"conditionGroup\\\\\\\":{\\\\\\\"betweenType\\\\\\\":\\\\\\\"LCRO\\\\\\\",\\\\\\\"betweenType2\\\\\\\":\\\\\\\"LCRO\\\\\\\",\\\\\\\"children\\\\\\\":[{\\\\\\\"betweenType\\\\\\\":\\\\\\\"LCRO\\\\\\\",\\\\\\\"betweenType2\\\\\\\":\\\\\\\"LCRO\\\\\\\",\\\\\\\"fieldBid\\\\\\\":\\\\\\\"f66755\\\\\\\",\\\\\\\"fieldBidValue\\\\\\\":\\\\\\\"\\\\\\\",\\\\\\\"fieldName\\\\\\\":\\\\\\\"隐含分期_计算\\\\\\\",\\\\\\\"fieldNameValue\\\\\\\":\\\\\\\"\\\\\\\",\\\\\\\"hit\\\\\\\":false,\\\\\\\"id\\\\\\\":407492,\\\\\\\"listItemType\\\\\\\":\\\\\\\"STRING\\\\\\\",\\\\\\\"listValue\\\\\\\":\\\\\\\"\\\\\\\",\\\\\\\"nodeName\\\\\\\":\\\\\\\"隐含分期_计算 小于 0\\\\\\\",\\\\\\\"nodeNo\\\\\\\":\\\\\\\"1\\\\\\\",\\\\\\\"nodeType\\\\\\\":\\\\\\\"item\\\\\\\",\\\\\\\"operateType\\\\\\\":\\\\\\\"LT\\\\\\\",\\\\\\\"parentId\\\\\\\":407490,\\\\\\\"value\\\\\\\":\\\\\\\"0\\\\\\\",\\\\\\\"valueType\\\\\\\":\\\\\\\"DOUBLE\\\\\\\"}],\\\\\\\"hit\\\\\\\":false,\\\\\\\"id\\\\\\\":407490,\\\\\\\"listItemType\\\\\\\":\\\\\\\"STRING\\\\\\\",\\\\\\\"nodeName\\\\\\\":\\\\\\\"满足其中一条\\\\\\\",\\\\\\\"nodeNo\\\\\\\":\\\\\\\"1\\\\\\\",\\\\\\\"nodeType\\\\\\\":\\\\\\\"group\\\\\\\",\\\\\\\"parentId\\\\\\\":0,\\\\\\\"relation\\\\\\\":\\\\\\\"or\\\\\\\"},\\\\\\\"default\\\\\\\":false,\\\\\\\"defaultKey\\\\\\\":\\\\\\\"default\\\\\\\",\\\\\\\"expressionUnits\\\\\\\":[{\\\\\\\"type\\\\\\\":\\\\\\\"input\\\\\\\",\\\\\\\"value\\\\\\\":\\\\\\\"0\\\\\\\"}],\\\\\\\"functions\\\\\\\":{},\\\\\\\"groupOrder\\\\\\\":2,\\\\\\\"key\\\\\\\":\\\\\\\"1\\\\\\\",\\\\\\\"referFieldBids\\\\\\\":[\\\\\\\"f66755\\\\\\\"],\\\\\\\"referFunctionCodes\\\\\\\":[]},{\\\\\\\"conditionGroup\\\\\\\":{\\\\\\\"betweenType\\\\\\\":\\\\\\\"LCRO\\\\\\\",\\\\\\\"betweenType2\\\\\\\":\\\\\\\"LCRO\\\\\\\",\\\\\\\"children\\\\\\\":[{\\\\\\\"betweenType\\\\\\\":\\\\\\\"LCRO\\\\\\\",\\\\\\\"betweenType2\\\\\\\":\\\\\\\"LCRO\\\\\\\",\\\\\\\"fieldBid\\\\\\\":\\\\\\\"f66755\\\\\\\",\\\\\\\"fieldBidValue\\\\\\\":\\\\\\\"\\\\\\\",\\\\\\\"fieldName\\\\\\\":\\\\\\\"隐含分期_计算\\\\\\\",\\\\\\\"fieldNameValue\\\\\\\":\\\\\\\"\\\\\\\",\\\\\\\"hit\\\\\\\":false,\\\\\\\"id\\\\\\\":407517,\\\\\\\"listItemType\\\\\\\":\\\\\\\"STRING\\\\\\\",\\\\\\\"listValue\\\\\\\":\\\\\\\"\\\\\\\",\\\\\\\"nodeName\\\\\\\":\\\\\\\"隐含分期_计算 大于等于 0\\\\\\\",\\\\\\\"nodeNo\\\\\\\":\\\\\\\"1\\\\\\\",\\\\\\\"nodeType\\\\\\\":\\\\\\\"item\\\\\\\",\\\\\\\"operateType\\\\\\\":\\\\\\\"GE\\\\\\\",\\\\\\\"parentId\\\\\\\":407516,\\\\\\\"value\\\\\\\":\\\\\\\"0\\\\\\\",\\\\\\\"valueType\\\\\\\":\\\\\\\"DOUBLE\\\\\\\"}],\\\\\\\"hit\\\\\\\":false,\\\\\\\"id\\\\\\\":407516,\\\\\\\"listItemType\\\\\\\":\\\\\\\"STRING\\\\\\\",\\\\\\\"nodeName\\\\\\\":\\\\\\\"满足其中一条\\\\\\\",\\\\\\\"nodeNo\\\\\\\":\\\\\\\"1\\\\\\\",\\\\\\\"nodeType\\\\\\\":\\\\\\\"group\\\\\\\",\\\\\\\"parentId\\\\\\\":0,\\\\\\\"relation\\\\\\\":\\\\\\\"or\\\\\\\"},\\\\\\\"default\\\\\\\":false,\\\\\\\"defaultKey\\\\\\\":\\\\\\\"default\\\\\\\",\\\\\\\"expressionUnits\\\\\\\":[{\\\\\\\"code\\\\\\\":\\\\\\\"f66755\\\\\\\",\\\\\\\"name\\\\\\\":\\\\\\\"隐含分期_计算\\\\\\\",\\\\\\\"type\\\\\\\":\\\\\\\"field\\\\\\\"}],\\\\\\\"functions\\\\\\\":{},\\\\\\\"groupOrder\\\\\\\":3,\\\\\\\"key\\\\\\\":\\\\\\\"2\\\\\\\",\\\\\\\"referFieldBids\\\\\\\":[\\\\\\\"f66755\\\\\\\"],\\\\\\\"referFunctionCodes\\\\\\\":[]}]}\\\",\\\"fieldId\\\":100178}],\\\"expressionUnits\\\":[{\\\"code\\\":\\\"b1437\\\",\\\"name\\\":\\\"贷记卡近6个月平均使用额度\\\",\\\"type\\\":\\\"field\\\"},{\\\"term\\\":\\\"-\\\",\\\"type\\\":\\\"term\\\"},{\\\"code\\\":\\\"o100178\\\",\\\"name\\\":\\\"隐含分期\\\",\\\"type\\\":\\\"field\\\"}],\\\"resourceObjectId\\\":\\\"meta_data_object_info@db_chengdeBankQueryCreditReport@meta_data_source_info@third_API\\\",\\\"fieldState\\\":\\\"ACTIVE\\\",\\\"objectType\\\":\\\"DATA\\\",\\\"updateMode\\\":\\\"OVERWRITE_ALL\\\",\\\"valueType\\\":\\\"DOUBLE\\\",\\\"columnCode\\\":\\\"minyhze\\\",\\\"deriveContent\\\":\\\"{\\\\\\\"expressionUnits\\\\\\\":[{\\\\\\\"code\\\\\\\":\\\\\\\"b1437\\\\\\\",\\\\\\\"name\\\\\\\":\\\\\\\"贷记卡近6个月平均使用额度\\\\\\\",\\\\\\\"type\\\\\\\":\\\\\\\"field\\\\\\\"},{\\\\\\\"term\\\\\\\":\\\\\\\"-\\\\\\\",\\\\\\\"type\\\\\\\":\\\\\\\"term\\\\\\\"},{\\\\\\\"code\\\\\\\":\\\\\\\"o100178\\\\\\\",\\\\\\\"name\\\\\\\":\\\\\\\"隐含分期\\\\\\\",\\\\\\\"type\\\\\\\":\\\\\\\"field\\\\\\\"}],\\\\\\\"referFieldBids\\\\\\\":\\\\\\\"b1437,o100178\\\\\\\"}\\\",\\\"fieldId\\\":\\\"meta_object_field@minyhze@meta_data_object_info@db_chengdeBankQueryCreditReport@meta_data_source_info@third_API\\\",\\\"fieldCode\\\":\\\"minyhze\\\",\\\"referFunctionCodes\\\":\\\"\\\",\\\"isKey\\\":false,\\\"objectInfoCode\\\":\\\"db_chengdeBankQueryCreditReport\\\",\\\"relColumnCode\\\":\\\"minyhze\\\",\\\"resourceObjectVersionId\\\":105107,\\\"priority\\\":0,\\\"recorded\\\":false,\\\"referFieldBids\\\":\\\"b1437,o100178\\\",\\\"position\\\":0,\\\"bid\\\":\\\"meta_object_field@minyhze@meta_data_object_info@db_chengdeBankQueryCreditReport@meta_data_source_info@third_APIf\\\",\\\"fieldType\\\":\\\"FORMULA_FIELD\\\"},\\\"expressionUnits\\\":[]}],\\\"missOutputList\\\":[],\\\"resourceType\\\":\\\"RULE\\\"}\",\"node_key\":\"105766_46502\",\"node_type\":\"RULE\",\"strategy_id\":\"res_strategy_info@gjpojlcshmlvnitx\",\"text\":\"输出接口条件字段默认值4\",\"width\":150,\"x\":75,\"y\":925}";
        JSONObject jsonObject = JSON.parseObject(val);
        Object property = DataConverRuleEngineUtils.getTableProperty(jsonObject, "node_content.hitOutputList.outField.valueConditions.conditionGroup.children.fieldBid");
        Object property1 = DataConverRuleEngineUtils.getTableProperty(jsonObject, "node_content.hitOutputList.outField.referFields.fieldId");
        System.out.println(JSON.toJSONString(property));
        System.out.println(JSON.toJSONString(property1));

        Map m = new HashMap();
        m.put(100178,"klfjds");
        m.put(1437,"fdf");
         DataConverRuleEngineUtils.setPropertyTable(jsonObject, "node_content.hitOutputList.outField.referFields.fieldId",null,m,null);
        Object property12 = DataConverRuleEngineUtils.getTableProperty(jsonObject, "node_content.hitOutputList.outField.referFields.fieldId");
        System.out.println(JSON.toJSONString(property12));

    }
}