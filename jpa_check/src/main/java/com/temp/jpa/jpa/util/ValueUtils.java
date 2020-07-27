package com.temp.jpa.jpa.util;

import com.temp.jpa.jpa.enums.DataTimeFormat;
import com.temp.jpa.jpa.enums.ValueType;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 值计算
 * @author
 * @version 1.
 * @date 2019/4/28
 */
public class ValueUtils {


    /**
     * 累加记
     *
     * @param stcV
     * @param aggV
     * @return
     */
    public static Object plus(Object stcV, Object aggV) {
        if(stcV==null){
            return aggV;
        }
        if (aggV != null) {
            if(stcV == null){
                return aggV;
            }
            if(stcV instanceof Integer){
                stcV = (Integer) stcV + (Integer) aggV;
            }else if(stcV instanceof BigDecimal){
                if(aggV instanceof BigDecimal){
                    stcV = ((BigDecimal)aggV).add((BigDecimal) stcV);
                }else{
                    aggV = BigDecimal.valueOf((Double) aggV);
                    stcV = ((BigDecimal)aggV).add((BigDecimal) stcV);
                }
            } else if (stcV instanceof Double) {
                if(aggV instanceof BigDecimal){
                    stcV = ((BigDecimal)aggV).add(BigDecimal.valueOf((Double) stcV));
                } else{
                    stcV = (Double) stcV + (Double) aggV;
                }
            } else if (stcV instanceof Long) {
                stcV = (Long) stcV + (Long) aggV;
            } else if (stcV instanceof Float) {
                if(aggV instanceof BigDecimal){
                    stcV = ((BigDecimal)aggV).add(BigDecimal.valueOf((Float) stcV));
                }else{
                    stcV = (Float) stcV + (Float) aggV;
                }
            }else if(stcV instanceof Number){
                stcV = ((Number) stcV).doubleValue() + ((Number)aggV).doubleValue();
            }else {
                throw new RuntimeException("计算数值类型出错："+stcV+","+aggV);
            }
        }
        return stcV;
    }

    /**
     * 字段去重
     *
     * @param stcV
     * @param aggV
     * @return
     */
    public static Object distinct(Object stcV, Object aggV) {
        if (aggV != null) {
            Set<String> disSet=null;
            if(aggV instanceof Set){
                disSet = (Set<String>) aggV;
            }else{
                disSet= Arrays.stream(aggV.toString().split(",")).collect(Collectors.toSet());
            }
            String v = stcV.toString();
            if (!disSet.contains(v)) {
                disSet.add(v);
                stcV = String.join(",", disSet);
            }else{
                stcV = aggV;
            }
        }
        return stcV;
    }

    public static Object randomValue(ValueType valueType){
        Object value = null;

        switch (valueType){
            case MONTH:
                LocalDateTime localDateTime = LocalDateTime.now();
                value = localDateTime.getMonthValue();
                break;
            case Maps:
                Map<String,Object> map = new HashMap<>();
                map.put("int_v", RandomUtils.nextInt(1,9));
                map.put("date_v",new Date());
                map.put("double_v",RandomUtils.nextDouble(1,9));
                map.put("str_v", RandomStringUtils.randomAlphabetic(9));
                value = map;
                break;
            case DOUBLE:
                value = RandomUtils.nextDouble(1,9);
                break;
            case DATE:
                value = new Date();
                break;
            case DAY_OF_MONTH:
                value = LocalDateTime.now().minusDays(RandomUtils.nextLong(1,5)).getDayOfMonth();
                break;
            case TIMESTAMP:
                value = System.currentTimeMillis();
                break;
            case Boolean:
                value = RandomUtils.nextBoolean();
                break;
            case Currency:
                value = RandomUtils.nextDouble(20,50);
                break;
            case Lists:
                List<Object> l = new ArrayList<>();
                l.add(RandomStringUtils.randomAlphabetic(2,3));
                l.add(RandomStringUtils.randomAlphabetic(2,3));
                l.add(RandomStringUtils.randomAlphabetic(2,3));
                value = l;
                break;
            case DICTIONARY:
                value = RandomStringUtils.random(2,"abcdefg");
                break;
            case STRING:
                value = RandomStringUtils.randomAlphabetic(5);
                break;
            case INTEGER:
                value = RandomUtils.nextInt(2,90000);
                break;
            case YEAR_MONTH:
                LocalDateTime ldt1 = LocalDateTime.now().minusMonths(RandomUtils.nextInt(1,5));
                value = Integer.valueOf(DateTimeUtil.format2Target(ldt1, DataTimeFormat.YearMonthFormat));
                break;
            case YEAR_MONTH_DATE:
                value = Integer.valueOf(DateTimeUtil.format2Target(LocalDateTime.now().minusDays(RandomUtils.nextInt(1,19)),DataTimeFormat.DataTimeFormat9));
                break;
            case LONG:
                value = RandomUtils.nextLong(200,890000000);
                break;
            case ListItem:
                List<Map<String,Object>> ll = new ArrayList<>();
                Map<String,Object> m = new HashMap<>();
                m.put("int_v", RandomUtils.nextInt(1,9));
                m.put("date_v",new Date());
                m.put("double_v",RandomUtils.nextDouble(1,9));
                m.put("str_v", RandomStringUtils.randomAlphabetic(9));
                ll.add(m);
                m.put("int_v", RandomUtils.nextInt(1,9));
                m.put("date_v",new Date());
                m.put("double_v",RandomUtils.nextDouble(1,9));
                m.put("str_v", RandomStringUtils.randomAlphabetic(9));
                ll.add(m);
                m.put("int_v", RandomUtils.nextInt(1,9));
                m.put("date_v",new Date());
                m.put("double_v",RandomUtils.nextDouble(1,9));
                m.put("str_v", RandomStringUtils.randomAlphabetic(9));
                ll.add(m);
                value = ll;
                break;
            case MONTH_DATE:
                value = Integer.valueOf(DateTimeUtil.format2Target(LocalDateTime.now().minusDays(RandomUtils.nextInt(1,5)),DataTimeFormat.DataTimeFormat21));
                break;
                default:
        }

        return value;
    }
}
