package nacos.test.intr;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 描述
 * </p>
 *
 * @author LW
 * @since 2021/1/24  9:58
 */
@RequestMapping("/test")
@FeignClient("nacos-server")
public interface ITestController {
    @GetMapping("/test/get")
    void test();

    @GetMapping("/getName/{name}")
    String getName(@PathVariable String name);
}
