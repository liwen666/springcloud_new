package com.temp.base.regex;

import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>
 * 描述
 * </p>
 *
 * @author tx
 * @since 2019/5/26 23:40
 */
public class Test {
    public static void main(String[] args) {
        Pattern p = Pattern.compile("([^: ]+):([^: ]+)(:([^: ]*)(:([^: ]+))?)?:([^: ]+)");
        String coordinates="maven://jrx.tutorial:b01-simple-job:jar:1.0.0";
        Matcher m = p.matcher(coordinates);
        Assert.isTrue(m.matches(), "Bad artifact coordinates " + coordinates + ", expected format is <groupId>:<artifactId>[:<extension>[:<classifier>]]:<version>");
        String groupId = m.group(1);
        System.out.println(groupId);
        String artifactId = m.group(2);
        String extension = StringUtils.hasLength(m.group(4)) ? m.group(4) : "jar";
        String classifier = StringUtils.hasLength(m.group(6)) ? m.group(6) : "";
        String version = m.group(7);

    }


}
