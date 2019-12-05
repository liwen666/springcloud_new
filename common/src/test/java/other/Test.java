package other;

import com.alibaba.fastjson.JSON;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p>
 * 描述
 * </p>
 *
 * @author lw
 * @since 2019/5/26 23:40
 */
public class Test {
        @org.junit.Test
        public void date() throws ParseException {
            java.util.Date date = new Date();

            System.out.println(JSON.toJSONString(date));
            System.out.println(JSON.toJSONString(date.toString()));

            TimeZone aDefault = TimeZone.getDefault();
            System.out.println(aDefault);

            String date1 ="2019-11-19T09:17:25.000+0800";
            TimeZone utc = TimeZone.getTimeZone("UTC");
            System.out.println(utc
            );


            DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSSZ");

            System.out.println("yyyy-MM-dd'T'hh:mm:ss.SSSZ".length());
            System.out.println("yyyy-MM-dd HH:mm:ss".length());
            DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        df2.setTimeZone(TimeZone.getTimeZone("GMT"));
            df2.setTimeZone(TimeZone.getTimeZone("UTC"));
            date = df.parse(date1);
            String value = df2.format(date);
            System.out.println(JSON.toJSONString(date));
    }

    @org.junit.Test
    public void iftest() {

        List<String> list = new ArrayList<>(
        );
        list.add("1");
        list.add("2");
        list.add("3");
        if(list.contains("1")){
            System.out.println(11111111);
        }else if(list.contains("2")){
            System.out.println(222);

        }else if(list.contains("3")){
            System.out.println("333333");
        }else {
            System.out.println("--------");
        }

    }

    @org.junit.Test
    public void set() {
        Set s = new HashSet<>();
        s.add("11");
        System.out.println(s);
    }
}
