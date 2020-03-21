package binlog;

import org.junit.Test;

import java.util.Date;

/**
 * <p>
 * 描述
 * </p>
 *
 * @author lw
 * @since 2019/5/26 23:40
 */
public class TimeConer {

    @Test
    public void name() {
        Date date = new Date(1584138284000l);
        String format = DateUtils.format(date, DateUtils.YYYY_MM_DD_HH_MM_SS);
        System.out.println(format);
        Date date2 = new Date(1584113087000l);
        String format2 = DateUtils.format(date2, DateUtils.YYYY_MM_DD_HH_MM_SS);
        System.out.println(format2);

//  2020-03-14 06:24:44
//2020-03-13 23:24:47
    }


    @Test
    public void name2() {
//        mysql比现在少15小时


        Date date = new Date(1584140513000l);
        String format = DateUtils.format(date, DateUtils.YYYY_MM_DD_HH_MM_SS);
        System.out.println(format);
        Date date2 = new Date(1584115316000l);
        String format2 = DateUtils.format(date2, DateUtils.YYYY_MM_DD_HH_MM_SS);
        System.out.println(format2);
//        2020-03-13 16:01:53,2020-03-13 16:01:56
//        2020-03-14 07:01:53
//        2020-03-14 00:01:56
    }
}
