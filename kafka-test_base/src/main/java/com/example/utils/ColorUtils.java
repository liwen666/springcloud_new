package com.example.utils;

import java.awt.*;
import java.util.Random;

/**
 * <p>
 * 描述
 * </p>
 *
 * @author lw
 * @since 2019/5/26 23:40
 */
public class ColorUtils {


    /**
     * 获取指定长度的16进制字符串.
     */
    public static String randomHexStr(int len) {
        try {
            StringBuffer result = new StringBuffer();
            for (int i = 0; i < len; i++) {
                //随机生成0-15的数值并转换成16进制
                result.append(Integer.toHexString(new Random().nextInt(16)));
            }
            return result.toString().toUpperCase();
        } catch (Exception e) {
            System.out.println("获取16进制字符串异常，返回默认...");
            return "00CCCC";
        }
    }

    public static Color randomColor() {
        int color = Integer.valueOf(randomHexStr(6), 16);
        return new Color(color);
    }

    public static Color randomColor2() {
        Random random = new Random();
        int r = random.nextInt(256);
        int g = random.nextInt(256);
        int b = random.nextInt(256);
        return new Color(r, g, b);
    }

    public static void main(String[] args) {
        String s = randomHexStr(6);
        System.out.println(s);
    }
}
