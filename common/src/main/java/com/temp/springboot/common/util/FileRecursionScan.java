package com.temp.springboot.common.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 描述
 * </p>
 *
 * @author lw
 * @since 2019/11/16 15:53
 */
public class FileRecursionScan {
    public static void main(String[] args) {
        List<String> paths = new ArrayList<String>();
        paths = getAllFilePaths(new File("C:/Users/liwen/Desktop/jrx/worktask"), paths);
        for (String path : paths) {
            System.out.println(path);
        }
        File f = new File("C:/Users/liwen/Desktop/jrx/worktask");
        File fileByName = getFileByName(f, "test_node-aafe7419-8f28-49a5-b0a3-18ad3d43aa9c", "stdout.log");
        if (fileByName != null) {
            System.out.println("******************************************************");
            System.out.println(fileByName.getName());

        }
    }

    /**
     * 查找路径下所有文件名
     *
     * @param filePath
     * @param filePaths
     * @return
     */
    public static List<String> getAllFilePaths(File filePath, List<String> filePaths) {
        File[] files = filePath.listFiles();
        if (files == null) {
            return filePaths;
        }
        for (File f : files) {
            if (f.isDirectory()) {
                filePaths.add(f.getPath());
                getAllFilePaths(f, filePaths);
            } else {
                filePaths.add(f.getPath());
            }
        }
        return filePaths;
    }

    /**
     * 查找指定路径下的指定文件
     */

    /**
     * 查找路径下所有文件名
     *
     * @return
     */
    public static File getFileByName(File file, String filePackage, String fileName) {
        if (!file.exists()) {
            return null;
        }
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f : files) {
                File fileByName = getFileByName(f, filePackage, fileName);
                if (null != fileByName) {
                    return fileByName;
                }
            }
        } else {
            if (file.getName().equals(fileName) && file.getPath().contains(filePackage)) {
                return file;
            }
        }
        return null;
    }
}