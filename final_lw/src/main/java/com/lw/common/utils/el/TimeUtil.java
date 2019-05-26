package com.lw.common.utils.el;

import java.util.Calendar;
import java.util.Date;

/**
* Description:    java类作用描述
* author:     lw
* date:     2019/5/26 12:20
* Version:        1.0
*/
public class TimeUtil {

    public static String getWeekDay(){
        String[] weekDays = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());

        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0){
            w = 0;
        }
        return weekDays[w];
    }
}
