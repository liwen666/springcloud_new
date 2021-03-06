package com.temp.base.log;

import ch.qos.logback.core.PropertyDefinerBase;

import java.net.URL;

public class LogHomePath extends PropertyDefinerBase {
    private String dataPath;

    @Override
    public String getPropertyValue() {
        URL resource = LogHomePath.class.getResource("");
        String path = resource.getPath();
       String dataPath= path.substring(1,path.length()-8)+"logdata";
        System.out.println(dataPath);
//        return "D:/component/component/coding_tx/src/main/java/com/temp/common/base/log/logdata";
        System.out.println(System.getProperty("user.dir")+"=========   user.dirs");
//        使用系统目录存放日志信息
        return System.getProperty("user.dir");
    }

    public static void main(String[] args) {
        URL resource = LogHomePath.class.getResource("");
        String dataPath = resource.getPath();
        System.out.println(dataPath);
        System.out.println(dataPath.substring(1,dataPath.length()-8)+"logdata");
        String path=System.getProperty("user.dir");
        System.out.println(path);
        System.out.println(System.getProperty("user.home"));
    }
}
