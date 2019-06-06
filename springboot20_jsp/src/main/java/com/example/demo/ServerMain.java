package com.example.demo;

import com.jsp.bean.UploadInfo;
import com.jsp.bean.UploadMetadata;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 *
 * @author Pierantonio Cangianiello
 */
@RestController
@EnableAutoConfiguration
public class ServerMain  {

    private int i = 0;
    @GetMapping(value = "test/{prevonce}/all")
    @ResponseBody
    Map<String, Object> listUserByZoning(@PathVariable(name = "prevonce") String param){
        System.out.println("======================================================");
        System.out.println(param);
        System.out.println("======================================================");
        Map map = new HashMap();
        map.put("name","lw");
        return map;
    }

    @PostMapping(value = "listUser/{username}/all")
    @ResponseBody
    Map<String, Object> listUser(@PathVariable(name = "username") String username){
        System.out.println("======================================================");
        UploadInfo uploadInfo = new UploadInfo();
        uploadInfo.setId(1111);
        uploadInfo.setName("upload");
        System.out.println(username);
        System.out.println("======================================================");
        Map map = new HashMap();
        map.put(username,uploadInfo);
        return map;
    }
    @RequestMapping(path = "/test", method = POST, consumes = "application/json")
    public HttpEntity<UploadInfo> upload(@RequestBody UploadMetadata metadata) {
        return ResponseEntity.ok(new UploadInfo(i++, 0, "dummy.tmp"));
    }

    @RequestMapping(path = "/upload/{folder}", method = POST)
    public HttpEntity<UploadInfo> upload(@PathVariable String folder, @RequestParam MultipartFile file, @RequestParam UploadMetadata metadata) {
        return ResponseEntity.ok(new UploadInfo(i++, file.getSize(), folder + "/" + file.getOriginalFilename()));
    }

    @RequestMapping(path = "/uploadSimple/{folder}", method = POST)
    public HttpEntity<UploadInfo> uploadSimple(@PathVariable String folder, @RequestPart MultipartFile file) {
        return ResponseEntity.ok(new UploadInfo(i++, file.getSize(), folder + "/" + file.getOriginalFilename()));
    }

    @RequestMapping(path = "/uploadArray/{folder}", method = POST)
    public HttpEntity<List<UploadInfo>> uploadArray(@PathVariable String folder, @RequestPart MultipartFile[] files, @RequestPart UploadMetadata metadata) {
        List<UploadInfo> response = new ArrayList<>();
        for (MultipartFile file : files) {
            response.add(new UploadInfo(i++, file.getSize(), folder + "/" + file.getOriginalFilename()));
        }
        return ResponseEntity.ok(response);
    }
    @RequestMapping(path = "/abc", method = GET)
    public HttpEntity<String> abc(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if(cookies!=null){
            for(Cookie c :cookies){
                System.out.println(c.getName()+"-->>"  +c.getValue());
            }
        }

        return ResponseEntity.ok("跨域请求cookie");
    }
    public static void main(String[] args) throws Exception {
        SpringApplication.run(ServerMain.class, args);
    }

}
