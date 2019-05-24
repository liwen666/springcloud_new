package com.example.thymeleafdemo.config;

import com.alibaba.fastjson.JSONObject;
import com.baidu.ueditor.ActionEnter;
import com.baidu.ueditor.PrimaryKeyGeneratorByUuid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import static org.apache.coyote.http11.Constants.a;

@RestController
@RequestMapping("/static/ueditor")
public class UeduitorController {
    private static Logger logger = LoggerFactory.getLogger(UeduitorController.class);

    @RequestMapping("uconfig")
    public Object findConfig(String fileName, Boolean delete, HttpServletResponse response, HttpServletRequest request) throws IOException {
        Resource ueConfig = findUeConfig();
        InputStream inputStream = ueConfig.getInputStream();
        byte[] config = new byte[1024*10];
        int read = inputStream.read(config);
        String javaEncode = EncodingDetect.getJavaEncode(config);
        System.out.println(javaEncode);
        String configJson = new String(config,0,read,javaEncode);
//        System.out.println(configJson);
//        删除 java 注释 /* */：/\*{1,2}[\s\S]*?\*/
//
//        删除 java 注释 //：//[\s\S]*?\n
//
//        删除xml注释：<!-[\s\S]*?-->
//
//                删除空白行：^\s*\n
         configJson = configJson.replaceAll("/\\*{1,2}[\\s\\S]*?\\*/", "");
        request.setCharacterEncoding( "utf-8" );
        response.setHeader("Content-Type" , "text/html");

        String rootPath = "E:\\github_program\\demo\\springcloud_new\\html\\src\\main\\resources";
        System.out.println(rootPath);
        String exec = new ActionEnter(request, rootPath).exec();
        System.out.println(exec);
//        System.out.println(configJson);
        return new ActionEnter(request, rootPath).exec();
    }
    @RequestMapping("/config")
    public void uEditorConfig( HttpServletResponse response, HttpServletRequest request) throws IOException {
        request.setCharacterEncoding( "utf-8" );
        response.setContentType("application/json");
        String rootPath = "";
        System.out.println(rootPath);
        String exec = new ActionEnter(request, rootPath).exec();
        System.out.println(exec);
        JSONObject jsonObject = JSONObject.parseObject(exec);
        if(!StringUtils.isEmpty(jsonObject.get("url"))){
            String serverPath=jsonObject.get("url").toString().substring(0,jsonObject.get("url").toString().lastIndexOf("/"));
            jsonObject.put("url",serverPath+"?imageId="+ jsonObject.get("guid"));
        }

        PrintWriter writer = response.getWriter();
        System.out.println(jsonObject.toJSONString());
        writer.write(jsonObject.toJSONString());
        writer.flush();
        writer.close();
    }
    @RequestMapping("/image")
    public void imageUpload( HttpServletResponse response, HttpServletRequest request) throws IOException {
        String imageId = request.getParameter("imageId");
        System.out.println("获取资源图片"+imageId);
        System.out.println("------------------------------");
    }
    private Resource findUeConfig() {
        String packageSearchPath = "classpath*:static/ueditor/jsp" + "/**/*";
        Resource[] resources = new Resource[0];
        try {
            resources = new PathMatchingResourcePatternResolver().getResources(packageSearchPath);
        } catch (IOException e) {
            System.out.println("查找编辑器配置失败");
        }
        for (Resource resource : resources) {
            if (resource.getFilename().equals("config.json")) {
                return resource;
            }
        }
        return null;
    }
}
