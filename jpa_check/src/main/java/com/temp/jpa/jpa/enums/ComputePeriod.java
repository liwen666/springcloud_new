package com.temp.jpa.jpa.enums;

import java.util.Calendar;

/**
 * 字段计算周期
 *
 * @author
 * @version 1.0
 * @date 2019/8/3
 */
public enum ComputePeriod {

    MONTH_OF_CALENDAR("公历月"),
    YEAR_OF_CALENDAR("公历年"),
    HALF_OF_YEAR("公历半年"),
    WEEK_OF_CALENDAR("公历周"),
    PER_DAY("每日"),
    MONTH_OF_BILL("账单月"),
    ACCUMULATE("累计"),
    NONE("无");

    private String desc;

    ComputePeriod(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    /**
     * 如果是日历周期
     */
    public boolean isCalendarPeriod(){
        return this == MONTH_OF_CALENDAR || this == YEAR_OF_CALENDAR || this == HALF_OF_YEAR || this == WEEK_OF_CALENDAR || this == PER_DAY;
    }

    /**
     * 是否为新周期
     *
     * @param oldDate 格式为yyyyMM,或yyyyMMdd
     * @param newDate 格式为yyyyMMdd
     * @return
     */
    public boolean isNewPeriod(Integer oldDate, Integer newDate) {
        boolean nPeriod = false;
        switch (this) {
            case MONTH_OF_CALENDAR:
                oldDate = oldDate/100;
                newDate = newDate/100;
                nPeriod = !oldDate.equals(newDate);
                break;
            case PER_DAY:
                nPeriod = !oldDate.equals(newDate);
                break;
            case MONTH_OF_BILL:
                // 账单月
                if(oldDate > 10000000){
                    //格式为yyyyMMdd，账单日
                    oldDate = oldDate/100;
                }
                newDate = newDate/100;
                nPeriod = !oldDate.equals(newDate);
                break;
            case WEEK_OF_CALENDAR:
                Calendar calendar = Calendar.getInstance();
                int year = oldDate / 10000;
                int mon = (oldDate % 10000) / 100;
                int day = oldDate % 100;
                calendar.set(year, mon - 1, day);
                int day_of_week = calendar.get(Calendar.DAY_OF_WEEK);
                day_of_week = day_of_week < 2 ? day_of_week + 5 : day_of_week-2 ;
                calendar.add(Calendar.DAY_OF_YEAR, -day_of_week);
                int minDate = calendar.get(Calendar.YEAR) * 10000 + (calendar.get(Calendar.MONTH) + 1) * 100 + calendar.get(Calendar.DAY_OF_MONTH);
                calendar.add(Calendar.DAY_OF_YEAR, 6);
                int maxDate = calendar.get(Calendar.YEAR) * 10000 + (calendar.get(Calendar.MONTH) + 1) * 100 + calendar.get(Calendar.DAY_OF_MONTH);
                //判断是否在oldDate的周内
                if(newDate < minDate || newDate > maxDate){
                    nPeriod = true;
                }
                break;
            case HALF_OF_YEAR:
                oldDate = oldDate/100;
                newDate = newDate/100;
                int m = oldDate - newDate;
                nPeriod = m > 6;
                break;
            case YEAR_OF_CALENDAR:
                oldDate = oldDate/10000;
                newDate = newDate/10000;
                nPeriod = !oldDate.equals(newDate);
                break;
            case ACCUMULATE:
            case NONE:
            default:
        }

        return nPeriod;
    }
}
