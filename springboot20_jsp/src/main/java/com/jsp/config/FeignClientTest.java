package com.jsp.config;

import com.jsp.bean.UploadInfo;
import com.jsp.bean.UploadMetadata;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 *
 * @author Pierantonio Cangianiello
 */
@FeignClient(value = "feignClientTest", url = "http://localhost:8080")
public interface FeignClientTest {


    @GetMapping(value = "test/{prevonce}/all")
    @ResponseBody
    Map<String, Object> listUserByZoning(@PathVariable(name = "prevonce") String zoning);
    @PostMapping(value = "listUser/{username}/all")
    @ResponseBody
    Map<String, Object> listUser(@PathVariable(name = "username") String username);
//    @RequestLine("POST /test")
//    public UploadInfo test(UploadMetadata metadata);

//    @RequestLine("POST /upload/{folder}")
//    public UploadInfo upload(@Param("folder") String folder, @Param("file") MultipartFile file, @Param("metadata") UploadMetadata metadata);
//
//    @RequestLine("POST /uploadSimple/{folder}")
//    public UploadInfo uploadSimple(@Param("folder") String folder, @Param("file") MultipartFile file);
//
//    @RequestLine("POST /uploadArray/{folder}")
//    public List<UploadInfo> uploadArray(@Param("folder") String folder, @Param("files") MultipartFile[] files, @Param("metadata") UploadMetadata metadata);

}
