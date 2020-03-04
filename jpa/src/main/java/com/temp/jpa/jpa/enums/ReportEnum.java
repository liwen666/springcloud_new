package com.temp.jpa.jpa.enums;
/**
 * <p>
 *  描述
 * </p>
 * 枚举的属性位置确定之后不能修改，否则引起数据库数据匹配不上
 * 需要添加枚举类型，直接在下面位置一次添加
 * @author lw
 * @since  2020/2/25 16:04
 */
public enum ReportEnum {

    category,//字段为分类字段
    REPORT_FIELD,//字段数据处理为报表类型
    value;//字段为计算字段
}
