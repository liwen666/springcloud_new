package com.jsp.config;

import org.springframework.cloud.dataflow.core.ApplicationType;
import org.springframework.cloud.dataflow.rest.resource.DetailedAppRegistrationResource;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 *
 * @author Pierantonio Cangianiello
 */
@FeignClient(value = "feignClientNode", url = "http://localhost:8080")
@RequestMapping("/apps")
public interface FeignClientNode {


    @GetMapping(value = "/apps/task/simplejob")
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


    @RequestMapping(
            value = {"/{type}/{name}/{version:.+}"},
            method = {RequestMethod.GET}
    )
    @ResponseStatus(HttpStatus.OK)
    public DetailedAppRegistrationResource info(@PathVariable("type") ApplicationType type, @PathVariable("name") String name, @PathVariable("version") String version, @RequestParam(required = false,name = "exhaustive") boolean exhaustive) ;

}
