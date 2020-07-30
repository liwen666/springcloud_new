package com.temp.jpa.service;

import com.temp.jpa.jpa.dao.TableCodeConfigRepository;
import com.temp.jpa.jpa.dao.TableCodeRelationRepository;
import com.temp.jpa.jpa.dao.TableConversionKeyRepository;
import com.temp.jpa.jpa.dto.TableDataImportOrExpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * <p>
 * 描述
 * </p>
 * 数据导入导出
 *
 * @author lw
 * @since 2019/5/26 23:40
 */

@Service
public class TableDataExpOrImpService {

    @Autowired
    private TableCodeConfigRepository tableCodeConfigRepository;

    @Autowired
    private TableCodeRelationRepository tableCodeRelationRepository;
    @Autowired
    private TableConversionKeyRepository tableConversionKeyRepository;




    public static Logger logger = LoggerFactory.getLogger(TableDataExpOrImpService.class);



    /**
     * 缓存上传校验后的数据
     */


    public static void expData(FileOutputStream outputStream, Map<String, String> data) {
        {

            ZipOutputStream zipOut = null;
            try {
                zipOut = new ZipOutputStream(outputStream);
                for(Map.Entry<String,String> entry : data.entrySet()) {
                    zipOut.putNextEntry(new ZipEntry(entry.getKey()));
                    zipOut.write(entry.getValue().getBytes(StandardCharsets.UTF_8));
                }

            }catch (IOException e){
                logger.error(e.getMessage(), e);
            }finally{
                try {
                    if(zipOut != null){
                        zipOut.closeEntry();
                        zipOut.close();
                    }
                    if(null!=outputStream){
                        outputStream.flush();
                        outputStream.close();
                    }
                }catch (IOException ex){

                }
            }
        }
    }

    public static Map<String, String> importData(InputStream fileInputStream) {
        Map<String, String> data = new ConcurrentHashMap<>();
        ZipInputStream zipInputStream = null;
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            zipInputStream = new ZipInputStream(fileInputStream);
            ZipEntry entry = zipInputStream.getNextEntry();

            byteArrayOutputStream = new ByteArrayOutputStream();
            while (entry != null) {
                String name = entry.getName();
                byte[] cache = new byte[1024];
                int read = zipInputStream.read(cache);
                while (read != -1) {
                    byteArrayOutputStream.write(cache, 0, read);
                    read = zipInputStream.read(cache);
                }
                data.put(name, new String(byteArrayOutputStream.toByteArray(), Charset.forName(StandardCharsets.UTF_8.name())));
                zipInputStream.closeEntry();
                byteArrayOutputStream.reset();
                entry = zipInputStream.getNextEntry();

            }
        } catch (IOException ex) {
            logger.error("解析上传文件出错！");
        } finally {
            if (null != zipInputStream) {
                try {
                    zipInputStream.close();

                } catch (IOException e) {
                }
            }
            if (null != byteArrayOutputStream) {
                try {
                    zipInputStream.close();

                } catch (IOException e) {
                }
            }
            if (null != fileInputStream) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                }
            }

        }
        return data;
    }

    public List<TableDataImportOrExpResult> initCodeCache() {

        return null;
    }
}
