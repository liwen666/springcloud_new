package jrx.batch.dataflow.demo;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import jrx.batch.dataflow.infrastructure.dao.TaskTaskBatchMapper;
import jrx.batch.dataflow.infrastructure.model.TaskTaskBatch;
import jrx.batch.dataflow.util.FileRecursionScan;
import jrx.batch.dataflow.util.JsonResult;
import lombok.Cleanup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.deployer.spi.local.LocalDeployerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;

/**
 * <p>
 * 描述
 * </p>
 *
 * @author tx
 * @since 2019/5/26 23:40
 */
@RestController
@RequestMapping("/test")
public class TestControllerDemo {

    @Autowired
    LocalDeployerProperties localDeployerProperties;

    @Autowired(required = false)
    private TaskTaskBatchMapper taskTaskBatchMapper;

    @RequestMapping("/get/{id}")
    public Object testMethod(@PathVariable Long id) {
        System.out.println("系统启动成功");
        return taskTaskBatchMapper.selectOne(Wrappers.<TaskTaskBatch>lambdaQuery().eq(TaskTaskBatch::getJobExecutionId, id));

    }


    @Value("${test.demo.value:123}")
    private String testProperties;

    public void setTestProperties(String testProperties) {
        this.testProperties = testProperties;
    }

    @GetMapping("/test")
    public String test() {


        return testProperties;
    }

    @GetMapping("/test1")
    public User testq() {

        User user = new User("小明fdajjjj解决", "xxx");

        return user;
    }

    @ResponseBody
    @GetMapping("/base")
    public JsonResult test2() {
        String a = "启动";
        return JsonResult.success(a);
    }

    @ResponseBody
    @GetMapping("/getlogs/{uuid}")
    public void getLog(HttpServletResponse response, @PathVariable  String uuid) throws IOException {
        Path workingDirectoriesRoot = localDeployerProperties.getWorkingDirectoriesRoot();
        File f = new File(workingDirectoriesRoot.toUri());
        File file = FileRecursionScan.getFileByName(f, uuid, "stdout.log");
        if (null != file) {
            @Cleanup FileInputStream fileInputStream = new FileInputStream(file);
            byte [] cache = new byte[1024*1024];
            int len=0;
            while((len=fileInputStream.read(cache))!=-1){
                response.getOutputStream().write(cache,0,len);
            }
        }
    }

}
