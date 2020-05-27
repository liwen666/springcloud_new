package com.temp.jpa.jpa.enums;


import com.temp.jpa.jpa.enums.ValueType;
import com.temp.jpa.jpa.util.ValueUtils;

/**
 * 字段数据更新方式
 * @author yxy
 * @version 1.0
 * @date 2019/4/13
 */
public enum UpdateMode {

    OVERWRITE_IF_NULL("为空时覆盖更新"),
    OVERWRITE_IF_NOT_NULL("不为空时覆盖更新"),
    OVERWRITE_IF_GREATER("大于时覆盖更新"),
    OVERWRITE_IF_LESS("小于时覆盖更新"),
    OVERWRITE_ALL("始终覆盖更新"),
    OVERWRITE_DISTINCT("去重更新"),
    ACCUMULATE("累计更新"),
    PERIOD_UPDATE("周期性更新"),
    //暂不实现基于函数的更新，后续考虑
    UPDATE_FUNCTION("基于函数更新");


    private String desc;

    UpdateMode(String desc){
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    /**
     * 值更新方式
     * @param oValue 旧值
     * @param nValue 新值
     * @param valueType 值的类型，两个值的类型应该一致
     * @return
     */
    public Object update(Object oValue, Object nValue, ValueType valueType){
        switch (this){
            case OVERWRITE_IF_GREATER:
                // 新值为空不进行覆盖
                if (nValue == null) {
                    nValue = oValue;
                } else {
                    //新值比存储值大时，覆盖
                    if (nValue instanceof Comparable && oValue instanceof Comparable) {
                        //存储的数据类型格式和预期的不一致，使用normalizeValue 格式化
                        oValue = ValueType.normalizeValue(valueType,oValue);
                        Comparable c1 = (Comparable) nValue;
                        Comparable c2 = (Comparable) oValue;
                        int i = c1.compareTo(c2);
                        if (i <= 0) {
                            nValue = null;
                        }
                    }
                }
                break;
            case OVERWRITE_IF_LESS:
                // 新值为空不进行覆盖
                if (nValue == null) {
                    nValue = oValue;
                } else {
                    //新值比存储值小时，覆盖
                    if (nValue instanceof Comparable && oValue instanceof Comparable) {
                        //存储的数据类型格式和预期的不一致，使用normalizeValue 格式化
                        oValue = ValueType.normalizeValue(valueType,oValue);
                        Comparable c1 = (Comparable) nValue;
                        Comparable c2 = (Comparable) oValue;
                        int i = c1.compareTo(c2);
                        if (i >= 0) {
                            nValue = null;
                        }
                    }
                }
                break;
            case ACCUMULATE:
                if(valueType == ValueType.INTEGER ||
                        valueType == ValueType.Currency ||
                        valueType == ValueType.DOUBLE ||
                        valueType == ValueType.LONG
                ){
                    //数值类型累加
                    nValue = ValueUtils.plus(nValue, oValue);
                }else if(valueType == ValueType.STRING || valueType == ValueType.Lists || valueType == ValueType.DICTIONARY){
                    //字符串类型,枚举类型，列表类型,使用逗号拼接
                    //旧值为空、空字符串不进行拼接
                    if(oValue != null && !oValue.toString().equals("")){
                        //新增为空、空字符串直接返回旧值
                        if(nValue != null && !nValue.toString().equals("")){
                            nValue = oValue + "," + nValue;
                        }else {
                            nValue = oValue;
                        }
                    }
                }

                break;
            case OVERWRITE_DISTINCT:
                //去重计算
                nValue = ValueUtils.distinct(nValue,oValue);
                break;
            case OVERWRITE_IF_NULL:
                //TODO 数值没值的时候是0，进入不了这个判断
                // 如果新值为空
                if (nValue == null) {
                    //新值为空
                } else {
                    if(oValue == null || (oValue instanceof String && "".equals(oValue))){
                        // 如果新值为不为空，旧值为空，则使用新值
                    } else {
                        // 如果旧值不为空，则返回null，即不覆盖
                        nValue = null;
                    }
                }
                break;
            case OVERWRITE_IF_NOT_NULL:
                // 此逻辑不再使用
                // 新值为空不进行覆盖
                if (nValue == null) {
                } else {
                    // 旧值为空，返回旧值
                    if(oValue == null || (oValue instanceof String && oValue.equals(""))){
                        nValue = null;
                    } else {
                    }
                }
                break;
            case PERIOD_UPDATE:
            case OVERWRITE_ALL:
                // 新值为空不进行覆盖
                if (nValue == null) {
                    nValue = oValue;
                }
                break;
            case UPDATE_FUNCTION:
                //函数更新后续实现
                default:break;
        }

        return nValue;
    }
}
