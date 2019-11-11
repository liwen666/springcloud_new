package jrx.batch.dataflow.application;

import jrx.batch.dataflow.domain.config.batch.JrxBatchProperties;
import jrx.batch.dataflow.domain.enums.CodeEnums;
import jrx.batch.dataflow.domain.service.AppRegisterService;
import jrx.batch.dataflow.util.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * 文件上传
 */
@Slf4j
@RestController
@RequestMapping("/batch/node")
public class AppRegisterController {
    @Autowired
    private AppRegisterService appRegisterService;

    @GetMapping(value = "/file")
    public String file() {
        return "file";
    }

    @PostMapping(value = "/registerapp")
    public JsonResult fileUpload(@RequestParam(value = "file") MultipartFile file, Model model, HttpServletRequest request) {
        if (file.isEmpty()) {
            return JsonResult.error(CodeEnums.FILE_NULL.getCode(),CodeEnums.FILE_NULL.getCnDesc());
        }
        String fileName = file.getOriginalFilename();  // 文件名
        log.info("====上传app   fileName: {}",fileName);
        String suffixName = fileName.substring(fileName.lastIndexOf("."));  // 后缀名
        if(!".jar".equals(suffixName)){
            return JsonResult.error(CodeEnums.FILE_NOT_ACCEPT.getCode(),CodeEnums.FILE_NOT_ACCEPT.getCnDesc());
        }
        String filePath = JrxBatchProperties.properties.get("jarhome"); // 上传后的路径
        if(!filePath.endsWith("/")){
            filePath= filePath+"/";
        }
        File dest = new File(filePath + fileName);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return JsonResult.success("上传成功！");
    }
}