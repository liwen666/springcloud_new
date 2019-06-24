package com.temp.springcloud.environment.util;//package com.example.thymeleafdemo.util;
//
//import javax.imageio.ImageIO;
//import javax.servlet.ServletOutputStream;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.OutputStream;
//import java.util.HashMap;
//import java.util.Map;
//
//public class ImageUtils {
//    private static Map imageContentType = new HashMap<>();
//
//    static {
//        imageContentType.put("jpg", "image/jpeg");
//        imageContentType.put("jpeg", "image/jpeg");
//        imageContentType.put("png", "image/png");
//        imageContentType.put("tif", "image/tiff");
//        imageContentType.put("tiff", "image/tiff");
//        imageContentType.put("ico", "image/x-icon");
//        imageContentType.put("bmp", "image/bmp");
//        imageContentType.put("gif", "image/gif");
//    }
//
//    public void imageUpload(HttpServletResponse httpServletResponse, HttpServletRequest request) throws IOException {
//        httpServletResponse.setDateHeader("Expires", 0L);
//        httpServletResponse.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
//        httpServletResponse.addHeader("Cache-Control", "post-check=0, pre-check=0");
//        httpServletResponse.setHeader("Pragma", "no-cache");
//        httpServletResponse.setContentType("image/jpeg");
//        ServletOutputStream out = null;
//
//        /*****method一：由于ImageIO.read不支持较大尺寸有时候会有错误*************/
//        BufferedImage image = ImageIO.read(new File(filePath));
//        out = httpServletResponse.getOutputStream();
//        ImageIO.write(image, "jpg", out);
//        out.flush();
//        /*****method二：直接用文件流来处理有更好的通用性*******************************************************/
//        File images = new File(filePath);
//        FileInputStream inputStream = new FileInputStream(images);
//        int length = inputStream.available();
//        byte data[] = new byte[length];
//        httpServletResponse.setContentLength(length);
//        String fileName = images.getName();
//        String fileType = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
//        httpServletResponse.setContentType(FormatUtils.imageContentType.get(fileType));
//        inputStream.read(data);
//        OutputStream toClient = httpServletResponse.getOutputStream();
//        toClient.write(data);
//        toClient.flush();
//    }
//}

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
 class ImageTest {

    public static void main(String [] args){

        try{

            BufferedImage originalImage =ImageIO.read(new File("D:\\IdeaProjects\\springcloud_new\\html\\src\\main\\java\\com\\example\\thymeleafdemo\\util\\企业微信截图_155860397622.png"));
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write( originalImage, "png", baos );
            baos.flush();
            //使用toByteArray()方法转换成字节数组
            byte[] imageInByte = baos.toByteArray();
            BufferedImage read = ImageIO.read(new ByteArrayInputStream(imageInByte));
            baos.close();

        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
}