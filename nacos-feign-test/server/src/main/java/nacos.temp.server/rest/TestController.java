package nacos.temp.server.rest;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import nacos.test.intr.ITestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * <p>
 * 描述
 * </p>
 *
 * @author LW
 * @since 2021/1/24  10:01
 */
@Slf4j
@RestController
public class TestController implements ITestController {
    @Override
    public void test() {

        log.info("-------------test controller---------");
    }

    @Override
    public String getName(String name) {
        log.info("---------name----------");
        return name;
    }

    @Override
    public Map<String, String> testJob(String jobResourceId) {
        System.out.println("mmmmmmmmmmmmm");
        return Maps.newHashMap();
    }
}
