package com.jsp.config;

import com.alibaba.fastjson.JSON;
import com.jsp.bean.UploadInfo;
import com.jsp.bean.UploadMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static org.bouncycastle.asn1.x500.style.RFC4519Style.o;

@RestController
public class TestImpl {

    @Autowired
    private FaspService faspService;
    @Autowired
    private FeignClientTest feignClientTest;


    @RequestMapping("/a")
    @ResponseBody
    public Map<String, Object> getUserInfo() {
        Map<String, Object> stringObjectMap = faspService.listRoleByYear("2018");
        System.out.println("stringObjectMap = " + stringObjectMap);
        return stringObjectMap;
    }
    @RequestMapping("/b")
    public Map<String, Object> feignTest() {
        Map<String, Object> jjj = feignClientTest.listUserByZoning("jjj");
        System.out.println("test: " + jjj);
        return jjj;
    }
    @RequestMapping("/c")
    public Map<String, Object> user() {
        Map<String, Object> jjj = feignClientTest.listUser("file");
        UploadInfo uploadInfo = JSON.parseObject(JSON.toJSONString(jjj.get("file")), UploadInfo.class);

        System.out.println("test: " + uploadInfo);
        return jjj;
    }
}
