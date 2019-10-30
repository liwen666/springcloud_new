package com.temp.base.regex;

import org.junit.Test;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.*;

public class TestTest {

    @Test
    public void name() {
        Pattern p = Pattern.compile("([^: ]+):([^: ]+)(:([^: ]*)(:([^: ]+))?)?:([^: ]+)");
        String coordinates="maven://jrx.tutorial:b01-simple-job:jar:1.0.0";
        Matcher m = p.matcher(coordinates);
        Assert.isTrue(m.matches(), "Bad artifact coordinates " + coordinates + ", expected format is <groupId>:<artifactId>[:<extension>[:<classifier>]]:<version>");
        for(int i=0; i<10;i++){
           try {
               System.out.println(m.group(i));
           } catch (Exception e) {
               System.out.println("==========="+i);
           }
       }

    }

    @Test
    public void m1() {
//        Pattern p = Pattern.compile("([^: ]+):11([^: ]+)(:1([^: ]*)(:1([^: ]+))?)?:1([^: ]+)");
        Pattern p = Pattern.compile("([^: ]+):([^: ]+):([^: ]*):([^: ]+):([^: ]+)");

//        正则解释  每个小括号相当于一个整体
        String coordinates = "maven:11//jrx.tutorial:1b01-simple-job:1jar:11.0.0";
        Matcher m = p.matcher(coordinates);
        Assert.isTrue(m.matches(), "Bad artifact coordinates " + coordinates + ", expected format is <groupId>:<artifactId>[:<extension>[:<classifier>]]:<version>");
        for (int i = 0; i < 10; i++) {
            try {
                System.out.println(m.group(i));
            } catch (Exception e) {
                System.out.println("===========" + i);
            }
        }
    }
}