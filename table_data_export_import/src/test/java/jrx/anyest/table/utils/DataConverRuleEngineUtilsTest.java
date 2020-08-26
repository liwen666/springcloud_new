package jrx.anyest.table.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Expression;
import jrx.anyest.table.constant.TableConstants;
import org.junit.Test;

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

        String expression="(b842!='是')";
//        AviatorUtil.compute()
//        String regx=""

        Expression compile = AviatorEvaluator.compile(expression);
        Map map = Maps.newConcurrentMap();
        map.put("b842", "是");
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
}