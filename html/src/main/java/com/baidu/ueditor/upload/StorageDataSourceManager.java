package com.baidu.ueditor.upload;

import com.baidu.ueditor.Entity.ImageEntity;
import com.baidu.ueditor.PrimaryKeyGeneratorByUuid;
import com.baidu.ueditor.define.AppInfo;
import com.baidu.ueditor.define.BaseState;
import com.baidu.ueditor.define.State;
import com.example.thymeleafdemo.config.UeduitorManager;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;

public class StorageDataSourceManager {

    public static final int BUFFER_SIZE = 8192;

    public StorageDataSourceManager() {
    }

    public  State saveBinaryFile(byte[] data, String path) {
        File file = new File(path);

        State state = valid(file);

        if (!state.isSuccess()) {
            return state;
        }

        try {
            BufferedOutputStream bos = new BufferedOutputStream(
                    new FileOutputStream(file));
            bos.write(data);
            bos.flush();
            bos.close();
        } catch (IOException ioe) {
            return new BaseState(false, AppInfo.IO_ERROR);
        }

        state = new BaseState(true, file.getAbsolutePath());
        state.putInfo("size", data.length);
        state.putInfo("title", file.getName());
        return state;
    }

    public static State saveFileByInputStream(String imageName, InputStream is,
                                              long maxSize) {
        State state = null;

    /**
     * 设置最大上传的文件大小 10
     */
        byte[] dataBuf = new byte[1024 * 1024*5];
        BufferedInputStream bis = new BufferedInputStream(is);

        try {
            int read = bis.read(dataBuf);
            byte[]imageByte = Arrays.copyOfRange(dataBuf,0,read);
            if (read > maxSize) {
                return new BaseState(false, AppInfo.MAX_SIZE);
            }
            ImageEntity imageEntity = new ImageEntity();
            imageEntity.setId(PrimaryKeyGeneratorByUuid.getNext()+"");
            imageEntity.setName(imageName);
            imageEntity.setBytes(imageByte);
            imageEntity.setCreateTime(new Timestamp(new Date().getTime()));
            state = saveToDataSource(imageEntity);

            return state;

        } catch (IOException e) {
        }
        return new BaseState(false, AppInfo.IO_ERROR);
    }

    private static State saveToDataSource(ImageEntity imageEntity) {
        boolean result =true ;
        try {
            UeduitorManager.insertObj(imageEntity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(result){
            return new BaseState(true, AppInfo.SUCCESS);
        }
           return new BaseState(false, AppInfo.IO_ERROR);
    }


    private static State saveTmpFile(File tmpFile, String path) {
        State state = null;
        File targetFile = new File(path);

        if (targetFile.canWrite()) {
            return new BaseState(false, AppInfo.PERMISSION_DENIED);
        }
        try {
            FileUtils.moveFile(tmpFile, targetFile);
        } catch (IOException e) {
            return new BaseState(false, AppInfo.IO_ERROR);
        }

        state = new BaseState(true);
        state.putInfo("size", targetFile.length());
        state.putInfo("title", targetFile.getName());

        return state;
    }

    private static State valid(File file) {
        File parentPath = file.getParentFile();

        if ((!parentPath.exists()) && (!parentPath.mkdirs())) {
            return new BaseState(false, AppInfo.FAILED_CREATE_FILE);
        }

        if (!parentPath.canWrite()) {
            return new BaseState(false, AppInfo.PERMISSION_DENIED);
        }

        return new BaseState(true);
    }
}
