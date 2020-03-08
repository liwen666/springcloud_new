package com.temp.springboot.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

public class PackageScanUtil {
    public static Logger logger = LoggerFactory.getLogger(PackageScanUtil.class);
    static ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();

    public static void main(String[] args) throws IOException {

//        findCandidateComponents("com");
        findResource("com.temp");

    }

    public static void findCandidateComponents(String basePackage) throws IOException {
        LinkedHashSet candidates = new LinkedHashSet();

//            String packageSearchPath = "classpath*:com/temp/springcloud/io/**/*";
        String packageSearchPath = "classpath*:com/temp/springcloud/sqlscript/**/*";
//            String packageSearchPath = "classpath*:com/temp/springcloud/io/**/*.class";
        Resource[] resources = PackageScanUtil.resourcePatternResolver.getResources(packageSearchPath);
        boolean traceEnabled = PackageScanUtil.logger.isTraceEnabled();
        boolean debugEnabled = PackageScanUtil.logger.isDebugEnabled();
        Resource[] arr$ = resources;
        int len$ = resources.length;
        for (Resource resource : resources) {
            File file = resource.getFile();
            System.out.println("是否是文件" + file.isFile());
            System.out.println(resource.getFilename());
            System.out.println(resource.getURI());
            System.out.println(resource.getURL());
//            System.out.println(resource.isFile());
        }


    }

    public static Resource[] findResource(String packageParten) throws IOException {
        String packageSearchPath = "classpath*:" + packageParten.replaceAll("\\.", "/") + "/**/*";
        Resource[] resources = new PathMatchingResourcePatternResolver().getResources(packageSearchPath);
        return resources;
    }

    private static void showFile2(File file) {
        logger.info(file.getName() + "-------------循环");
        boolean flag = true;
        List<File> dir = new ArrayList<>();
        int boot=0;
        while (flag) {
            if (file.isDirectory()) {
                if(dir.size()>boot){
                    file= dir.get(boot);
                    boot+=1;
                }
                File[] files = file.listFiles();
                for (int i = 0; i < files.length; i++) {
                    System.out.println(files[i].getName()+"循环---------------");
                    if (files[i].isDirectory()) {
                        dir.add(files[i]);
                    }
                }
                if(!(dir.size()>boot)){
                    flag=false;
                }
            } else {
                flag = false;
            }
        }
    }

    private static void showFile(File file) {
        logger.info(file.getName()+"-------------递归");
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f : files) {
                if (f.isDirectory()) {
                    showFile(f);
                } else {
                    logger.info(f.getName()+"-------------递归");
                }
            }
        }
    }
}
