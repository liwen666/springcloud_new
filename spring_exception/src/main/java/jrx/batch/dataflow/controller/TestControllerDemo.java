package jrx.batch.dataflow.controller;

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


    @RequestMapping("/init")
    public Object testMethod() {
        System.out.println("系统启动成功");
        return JsonResult.success("初始化成功");
    }

    @RequestMapping("/error")
    public Object errorMethoe() {
        System.out.println("模拟异常");
        throw new PlanExecutionException("异常测试");
    }


}
