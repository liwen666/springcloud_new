package jrx.anyest.table.utils;

import jrx.anyest.table.service.TableDataExpOrImpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * <p>
 * 描述
 * </p>
 *
 * @author lw
 * @since 2019/5/26 23:40
 */
public class DownUploadUtils {

    public static Logger logger = LoggerFactory.getLogger(TableDataExpOrImpService.class);

    public static void expData(FileOutputStream outputStream, Map<String, String> data) {
        {

            ZipOutputStream zipOut = null;
            try {
                zipOut = new ZipOutputStream(outputStream);
                for (Map.Entry<String, String> entry : data.entrySet()) {
                    zipOut.putNextEntry(new ZipEntry(entry.getKey()));
                    zipOut.write(entry.getValue().getBytes(StandardCharsets.UTF_8));
                }

            } catch (IOException e) {
                logger.error(e.getMessage(), e);
            } finally {
                try {
                    if (zipOut != null) {
                        zipOut.closeEntry();
                        zipOut.close();
                    }
                    if (null != outputStream) {
                        outputStream.flush();
                        outputStream.close();
                    }
                } catch (IOException ex) {

                }
            }
        }
    }

    /**
     * 下载zip文件
     *
     * @param response
     * @param data
     * @param zipName
     */
    public static void downloadZip(HttpServletResponse response, Map<String, String> data, String zipName) {

        ZipOutputStream zipOut = null;
        ByteArrayInputStream bin = null;
        try {
            setFileHeader(response);
            response.setHeader("Content-Disposition", "attachment;filename=" + getFileName(zipName));

            zipOut = new ZipOutputStream(response.getOutputStream());

            for (Map.Entry<String, String> entry : data.entrySet()) {

                //bin = new ByteArrayInputStream(getCrypt(entry.getValue()).getBytes(UTF8));
                bin = new ByteArrayInputStream(entry.getValue().getBytes(StandardCharsets.UTF_8));
                zipOut.putNextEntry(new ZipEntry(entry.getKey()));

                byte[] buf = new byte[1024];
                int len;
                while ((len = bin.read(buf)) > 0) {
                    zipOut.write(buf, 0, len);
                }
                bin.close();
            }
            response.flushBuffer();

        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } finally {
            try {
                if (bin != null) {
                    bin.close();
                }
                if (zipOut != null) {
                    zipOut.closeEntry();
                    zipOut.close();
                }
            } catch (IOException ex) {

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

    private static void setFileHeader(HttpServletResponse response) {
        response.setHeader("content-type", "application/octet-stream");
        response.setContentType("application/octet-stream");
        response.setHeader("Set-Cookie", "fileDownload=true; path=/");
    }

    private static String getFileName(String name) throws UnsupportedEncodingException {
        return new String(name.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1);
    }

}
