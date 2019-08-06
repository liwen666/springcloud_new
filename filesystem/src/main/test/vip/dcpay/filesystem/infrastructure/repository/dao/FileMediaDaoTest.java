package vip.dcpay.filesystem.infrastructure.repository.dao;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import vip.dcpay.filesystem.RunApplication;
import vip.dcpay.filesystem.application.dto.FileMediaDto;
import vip.dcpay.filesystem.application.service.FileSystemService;
import vip.dcpay.filesystem.infrastructure.repository.model.FileMedia;
import vip.dcpay.filesystem.util.FileIdGenerator;
import vip.dcpay.vo.basic.Result;

import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RunApplication.class)
public class FileMediaDaoTest {
    @Autowired(required = false)
    private FileMediaDao fileMediaDao;
    @Reference
    private FileSystemService fileSystemService;

    @Test
    public void name() {
        fileMediaDao.insert(FileMedia.builder().fileName("test").groupName("group1").uid(FileIdGenerator.getNextLong()).createTime(new Date()).build());
    }

    @Test
    public void update() {
        int update = fileMediaDao.update(FileMedia.builder().fileName("测试表").build(), new UpdateWrapper<FileMedia>().eq("uid", 4081156660041670656l));
        System.out.println("=========================================================================================");
        System.out.println(update);
    }

    @Test
    public void testdubbo() {

        Result<FileMediaDto> fileMediaDtoResult = fileSystemService.downLoadFileMedia(1l);
        System.out.println(JSON.toJSONString(fileMediaDtoResult));
    }
}