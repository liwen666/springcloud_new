package vip.dcpay.filesystem.application.controller;

import lombok.Cleanup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vip.dcpay.filesystem.application.dto.FileMediaDto;
import vip.dcpay.filesystem.application.service.FileSystemService;
import vip.dcpay.filesystem.application.vo.FileMediaVO;
import vip.dcpay.log.sdk.MyLogManager;
import vip.dcpay.vo.basic.Result;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * author lw
 * date 2019/8/5 19:56
 */
@RestController
@RequestMapping("/file")
public class FileSystemController {
    @Autowired
    private FileSystemService fileSystemService;

    @PostMapping(value = "/test", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result<FileMediaVO> test(Long merchantId) {

        MyLogManager.develop("文件服务器运行正常: " + merchantId);

        return Result.success("访问成功");
    }

    @RequestMapping(value = "/image/{uid}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void getImage(@PathVariable long uid, HttpServletResponse response) {

        System.out.println("=========================================================================================");
        System.out.println(uid);
        Result<FileMediaDto> fileMediaDtoResult = fileSystemService.downLoadFileMedia(uid);
        response.setContentType("jpg");
        try {
            OutputStream out = response.getOutputStream();
            out.write(fileMediaDtoResult.getData().getFileBuff(), 0, fileMediaDtoResult.getData().getFileBuff().length);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @RequestMapping(value = "/download/{uid}")
    public void downloadResource(@PathVariable long uid, HttpServletResponse response) {
        Result<FileMediaDto> fileMediaDtoResult = fileSystemService.downLoadFileMedia(uid);
        FileMediaDto data = fileMediaDtoResult.getData();
        response.setContentType("application/octet-stream");
        response.addHeader("Content-Disposition", "attachment;filename="+data.getFileName()+"."+data.getFileExtName());
        try {
            @Cleanup OutputStream os = response.getOutputStream();
            os.write(fileMediaDtoResult.getData().getFileBuff());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
