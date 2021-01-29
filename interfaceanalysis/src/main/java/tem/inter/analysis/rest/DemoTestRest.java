package tem.inter.analysis.rest;

import jrx.data.hub.core.utils.JsonUtils;
import org.h2.engine.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tem.inter.analysis.aop.annotation.InterfaceAnalysisRetry;
import tem.inter.analysis.utils.TablePropertiesThreadLocalHolder;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>
 * 描述
 * </p>
 *
 * @author LW
 * @since 2021/1/27  17:37
 */
@RestController
@RequestMapping("/test")
public class DemoTestRest {
    @Autowired
    private ServiceTest serviceTest;
    @GetMapping("/demo/{aaa}")
    @InterfaceAnalysisRetry(description = "测试",param = {"className","method","param"})
    public String demo(@PathVariable String aaa){
        System.out.println(aaa);
//        serviceTest.test(new Date(),2,3l, User.AGGREGATE);
//        serviceTest.test2();
        getAppName(new Date());
        return "demo";
    }

    public String getAppName(Date date) {
        System.out.println(JsonUtils.obj2StringPretty(date));
        return "cc";
    }

}
