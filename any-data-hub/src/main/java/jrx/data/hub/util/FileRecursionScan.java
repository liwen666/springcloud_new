package jrx.data.hub.util;

import java.util.*;
import java.io.*;

/**
 * <p>
 *  描述
 * </p>
 *
 * @author lw
 * @since  2020/10/22 16:27
 */
public class FileRecursionScan {

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