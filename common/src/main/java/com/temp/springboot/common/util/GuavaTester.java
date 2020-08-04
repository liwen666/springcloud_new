package com.temp.springboot.common.util;

import com.google.common.base.CaseFormat;
import com.google.common.base.CharMatcher;
import com.google.common.base.Splitter;
import org.junit.Test;

import java.util.List;
import java.util.Map;

/**
 * 字符串驼峰转换
 */
public class GuavaTester {

    @Test
    public void test() {
        System.out.println(CaseFormat.LOWER_HYPHEN.to(CaseFormat.LOWER_CAMEL, "test-data"));//testData
        System.out.println(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, "test_data"));//testData
        System.out.println(CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, "test_data"));//TestData

        System.out.println(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, "testdata"));//testdata
        System.out.println(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, "TestData"));//test_data
        System.out.println(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_HYPHEN, "testData"));//test-data

        System.out.println("******************************************************");

        System.out.println(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, "testData"));//test_data
        System.out.println(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, "test_data"));//test_data
        System.out.println("******************************************************");


        System.out.println(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL,CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, "testData")));//test_data
        System.out.println(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL,CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, "test_data")));//test_data

        System.out.println("******************************************************");

        System.out.println(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, "test_data"));//testData
        System.out.println(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, "testData"));//testData


    }


    @Test
    public void splitterTest(){
        List<String> list = Splitter.on(",").splitToList("a,b,c,d");
        System.out.println(list);

        Splitter.on(",").split("a,b,c,d").forEach( s -> {
            System.out.println(s);
        });

        //过滤掉空字符串，但不包括空格
        List<String> list1 = Splitter.on(",").omitEmptyStrings().splitToList("a,b,, ,c,d");
        System.out.println("list1:" + list1);  //输出：[a, b,  ,c, d]

        //去掉字符串中的空格，再进行过滤空元素
        List<String> list2 = Splitter.on(",").omitEmptyStrings().trimResults().splitToList("a,b,, ,c,d");
        System.out.println("list2:" + list2); //输出 [a, b, c, d]

        //limit表示最多把字符串分隔成多少份
        List<String> list3 = Splitter.on("#").omitEmptyStrings().trimResults().limit(2).splitToList("a#b#c#d");
        System.out.println("list3:" + list3);

        //将字串还原成map，是Joiner的逆向操作，注意：字符串的格式必须满足“a:1#b:2”这种格式，格式不对会导致还原map失败
        Map<String,String> map = Splitter.on("#").omitEmptyStrings().trimResults().withKeyValueSeparator(":").split("a:1#b:2");
        System.out.println("map:" + map);



        String s = "er 3j6o  3k  ,)$ wt@ wr4576je  ow3453535345irjew jwfel ";
        //返回只保留a-z的字母的字符中
        String str = CharMatcher.inRange('a','z').retainFrom(s);
        System.out.println(str);

        String s1 = "er 3j6o  3k  ,)$ wt@ wr4576je  ow3453535345irjew jwfel ";
        //去掉字符串中数字
        String str1 = CharMatcher.digit().removeFrom(s1);
        System.out.println(str1);
    }

}