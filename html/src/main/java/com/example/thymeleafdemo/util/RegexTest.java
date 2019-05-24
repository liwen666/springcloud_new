package com.example.thymeleafdemo.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexTest {
    public static final String P_NAMED = "(?<year>\\d{4})-(?<md>(?<month>\\d{2})-(?<date>\\d{2}))";
    public static final String DATE_STRING = "2017-04-25";
    public static final String URL = "https://blog.csdn.net/just4you/article/details/70767928";
    public static final String URL_PATTERN = "(?<url>/.*)(?<name>/)";

    public static void main(String[] args) {

        Pattern pattern = Pattern.compile(P_NAMED);
        Matcher matcher = pattern.matcher(DATE_STRING);
        matcher.find();
        System.out.printf("\n===========使用名称获取=============");
        System.out.printf("\nmatcher.group(0) value:%s", matcher.group(0));
        System.out.printf("\n matcher.group('year') value:%s", matcher.group("year"));
        System.out.printf("\nmatcher.group('md') value:%s", matcher.group("md"));
        System.out.printf("\nmatcher.group('month') value:%s", matcher.group("month"));
        System.out.printf("\nmatcher.group('date') value:%s", matcher.group("date"));
        matcher.reset();
        System.out.printf("\n===========使用编号获取=============");
        matcher.find();
        System.out.printf("\nmatcher.group(0) value:%s", matcher.group(0));
        System.out.printf("\nmatcher.group(1) value:%s", matcher.group(1));
        System.out.printf("\nmatcher.group(2) value:%s", matcher.group(2));
        System.out.printf("\nmatcher.group(3) value:%s", matcher.group(3));
        System.out.printf("\nmatcher.group(4) value:%s", matcher.group(4));


        Pattern urlpattern = Pattern.compile(URL_PATTERN);
        Matcher url = urlpattern.matcher(URL);
        matcher.find();
        System.out.printf("\n===========使用名称获取=============");
        System.out.printf("\nmatcher.group(0) value:%s", url.group(0));
        System.out.printf("\n matcher.group('url') value:%s", url.group("url"));
        System.out.printf("\nmatcher.group('name') value:%s", url.group("name"));
        matcher.reset();
        System.out.printf("\n===========使用编号获取=============");
        matcher.find();
        System.out.printf("\nmatcher.group(0) value:%s", matcher.group(0));
        System.out.printf("\nmatcher.group(1) value:%s", matcher.group(1));
        System.out.printf("\nmatcher.group(2) value:%s", matcher.group(2));


    }
}
