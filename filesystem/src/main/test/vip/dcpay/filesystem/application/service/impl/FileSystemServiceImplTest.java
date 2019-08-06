package vip.dcpay.filesystem.application.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import vip.dcpay.enums.filesystem.FileGroupEnum;
import vip.dcpay.filesystem.RunApplication;
import vip.dcpay.filesystem.application.dto.FileMediaDto;
import vip.dcpay.filesystem.application.service.FileSystemService;
import vip.dcpay.vo.basic.Result;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RunApplication.class)
public class FileSystemServiceImplTest {
    @Reference
    private FileSystemService fileSystemService;

    @Test
    public void download() {
            Result<FileMediaDto> fileMediaDtoResult = fileSystemService.downLoadFileMedia(1l);
            System.out.println(JSON.toJSONString(fileMediaDtoResult));
    }
    @Test
    public void upload() {
        Result<Long> fileMediaDtoResult = fileSystemService.uploadFileMedia(FileMediaDto.builder().fileName("abcd").fileExtName("txt").fileBuff("fdkajkfdla12334".getBytes()).groupName(FileGroupEnum.PERSISTENCE).build());
        System.out.println(JSON.toJSONString(fileMediaDtoResult));
    }
}