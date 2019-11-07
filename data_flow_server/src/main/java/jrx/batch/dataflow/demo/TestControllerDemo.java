package jrx.batch.dataflow.demo;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import jrx.batch.dataflow.infrastructure.dao.TaskTaskBatchMapper;
import jrx.batch.dataflow.infrastructure.model.TaskTaskBatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @Autowired(required = false)
    private TaskTaskBatchMapper taskTaskBatchMapper;
    @RequestMapping("/get/{id}")
    public  Object testMethod(@PathVariable Long id){
        System.out.println("系统启动成功");
        return taskTaskBatchMapper.selectOne(Wrappers.<TaskTaskBatch>lambdaQuery().eq(TaskTaskBatch::getJobExecutionId,id));

    }


    @Value("${test.demo.value:123}")
    private String testProperties;

    public void setTestProperties(String testProperties){
        this.testProperties = testProperties;
    }

    @GetMapping("/test")
    public String test(){
        return testProperties;
    }
}
